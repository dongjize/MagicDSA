package graph.traverse;

import base.Iterator;
import graph.Edge;
import graph.Graph;
import graph.Vertex;

/**
 * Description:
 *
 *
 * 算法：DFS(G, s)
输入：有向图G及其中的顶点s
输出：从s出发，对G做深度优先遍历，并对访问到的边进行分类，对访问到的顶点做时间标签
假设：首次调用之前，所有顶点的状态都已置为UNDISCOVERED，所有边的分类置为UNKNOWN，clock置为0
{
 dStamp[s] = clock++;
 将s标记为DISCOVERED;
 调用visit(s)对s进行访问;
 逐一检查s的每一条出边e = (s, u)，根据顶点u的不同状态分别处理 {
 c 若u尚未被发现（即status[u]＝UNDISCOVERED），则
 将e标记为树边（TREE）;
 调用DFS(G, u)递归搜索;
 d 若u已被发现但对其的访问尚未完成（即status[u]＝DISCOVERED），则
 将e标记为后向跨边（BACKWARD）;
 e 若对u的访问已经完成（即status[u]＝VISITED），则比较s和u的时间标签
 若u比s更早被发现（即dStamp(u)<dStamp(s)），则
 将e标记为横跨边（CROSS）;
 否则
 将e标记为前向跨边（FORWARD）;
 }//至此，s的所有出边都已访问，故在回溯之前令
 fStamp[s] = clock++;
将s标记为VISITED;
}
 *
 *
 * @Author: dong
 * @Date: 2017-08-20
 * @Time: 21:34
 */
public abstract class DFS extends GraphTraverse {

    protected static int clock = 0;//遍历过程中使用的计时钟

    public DFS(Graph g) {
        super(g);
    }

    /**
     * Depth first search minDistance
     *
     * @param v start from vertex v
     * @param info
     * @return
     */
    @Override
    protected void traverse(Vertex v, Object info) {
        //skip the vertex that's discovered or visited
        if (Vertex.UNDISCOVERED != v.getStatus()) {
            return;
        }
        v.setDStamp(clock++); //set time stamp
        v.setStatus(Vertex.DISCOVERED);
        visit(v, info); //left unimplemented for subclass
        for (Iterator iterator = v.outEdges(); iterator.hasNext(); ) {
            Edge edge = (Edge) iterator.getNext();
            Vertex u = (Vertex) edge.getVPosInV(1);
            switch (u.getStatus()) {
                case UNDISCOVERED:
                    edge.setType(Edge.TREE);
                    traverse(u, info);
                    break;
                case DISCOVERED:
                    edge.setType(Edge.BACKWARD);
                    break;
                case VISITED:
                    if (u.getDStamp() < v.getDStamp()) {
                        edge.setType(Edge.CROSS);
                    } else {
                        edge.setType(Edge.FORWARD);
                    }
                    break;
            }
        }
        v.setFStamp(clock++);
        v.setStatus(Vertex.VISITED);
    }
}
