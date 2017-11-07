package graph.traverse;

import base.Iterator;
import graph.Edge;
import graph.Graph;
import graph.Vertex;
import queue.ListQueue;
import queue.Queue;

/**
 * Description:
 *
 * 算法：BFS(G, s)
 输入：有向图G及其中的顶点s
 输出：从s出发，对G做广度优先遍历，并对访问到的边进行分类
 假设：首次调用之前，所有顶点的状态都已置为UNDISCOVERED，所有边的分类置为UNKNOWN
 {
     创建一个空队列Q;
     将s标记为DISCOVERED，并将s加入队列Q;
     调用visit(s, null)对v进行访问;
     在Q变空之前，不断迭代 {
         从Q中取出队首顶点v;
         逐一检查v的每一后继顶点u {
             若u尚未被发现（即status[u]＝UNDISCOVERED），则
             将有向边(v, u)标记为树边（TREE）;
             将u标记为DISCOVERED;
             令u加入队列Q;
             调用visit(u, v)对u进行访问;
             否则
             将有向边(v, u)标记为跨边（CROSS）;
         }//至此，v的所有出边都已访问，故
         将v标记为VISITED;
     }
 }
 *
 * @Author: dong
 * @Date: 2017-08-20
 * @Time: 22:19
 */
public abstract class BFS extends GraphTraverse {

    public BFS(Graph g) {
        super(g);
    }

    /**
     * @param vertex
     * @param info
     */
    @Override
    protected void traverse(Vertex vertex, Object info) {
        //skip visited vertex
        if (Vertex.UNDISCOVERED != vertex.getStatus()) {
            return;
        }
        Queue queue = new ListQueue();
        //having discovered the vertex, set status UNDISCOVERED, enqueue it, and visit it
        vertex.setStatus(Vertex.UNDISCOVERED);
        queue.enqueue(vertex);
        visit(vertex, null); //left implemented for subclass

        while (!queue.isEmpty()) {
            Vertex v = (Vertex) queue.dequeue();
            for (Iterator iterator = v.outEdges(); iterator.hasNext(); ) {
                Edge edge = (Edge) iterator.getNext();
                Vertex u = (Vertex) edge.getVPosInV(1).getElem();
                if (Vertex.UNDISCOVERED == u.getStatus()) {
                    edge.setType(Edge.TREE);
                    u.setStatus(Vertex.DISCOVERED);
                    queue.enqueue(u);
                    visit(u, v);
                } else {
                    edge.setType(Edge.CROSS); //group edge as CROSS (跨边)
                }
            }
            v.setStatus(Vertex.VISITED);
        }
    }
}

