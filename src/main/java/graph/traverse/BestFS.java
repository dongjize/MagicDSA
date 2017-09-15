package graph.traverse;

import base.Iterator;
import graph.Edge;
import graph.Graph;
import graph.Vertex;

/*
 * Description: 有向带权图最佳优先遍历
 *
 *
 * 算法：BestFS(G, s)
    输入：有向图G及其中的顶点s
    输出：从s出发，对G做最佳优先遍历，并对访问到的边进行分类
    假设：调用之前，所有顶点的状态都已置为UNDISCOVERED，所有边的分类置为UNKNOWN
    {
        若s已被访问（status[s] != UNDISCOVERED），则直接返回;
        令Distance(s) = 0; //最近 = 最佳
        不断地 {
             在所有尚未访问过的顶点中，找出距离已访问点集最近的顶点v;
             调用visit(v)，对v进行访问，并将其加入已访问点集（将其状态设置为status[v] = VISITED）;
             调用updateDistanceAfter(v)，更新各顶点到已访问点集的最短距离;
         }
         直到所有顶点都已被访问
    }
 *
 *
 * @Author: dong
 * @Date: 2017-08-21
 * @Time: 20:32
 */
public abstract class BestFS extends GraphTraverse {
    public BestFS(Graph g) {
        super(g);
    }

    /**
     * 更新尚未访问的顶点到已访问点集的最短距离（取决于具体的算法）
     *
     * @param v
     */
    protected abstract void updateDistanceAfter(Vertex v);

    /**
     * 最佳优先遍历算法
     *
     * @param v
     * @param info
     */
    @Override
    protected void traverse(Vertex v, Object info) {
        if (v.getStatus() != Vertex.UNDISCOVERED) {
            return;
        }
        v.setDistance(0);
        Vertex bestVertex;
        while ((bestVertex = bestVertex()) != null) { //只要还有最佳顶点
            visit(bestVertex, null); //在发现并访问vertex之后
            updateDistanceAfter(bestVertex); //更新尚未访问的顶点到已访问集的最短距离
        }
    }

    @Override
    protected void visit(Vertex v, Object info) {
        v.setStatus(Vertex.VISITED);
    }

    @Override
    public void minDistance(Vertex s, Object info) {
        reset();
        traverse(s, info);
    }

    protected Vertex bestVertex() {
        int bestValue = Integer.MAX_VALUE;
        Vertex bestVertex = null;//最佳顶点
        for (Iterator iterator = graph.vertices(); iterator.hasNext(); ) {
            Vertex u = (Vertex) iterator.getNext();
            if (u.getStatus() == Vertex.UNDISCOVERED) {
                if (u.getDistance() < bestValue) {
                    bestValue = u.getDistance();
                    bestVertex = u;
                }
            }
        }
        if (null != bestVertex && null != bestVertex.getBFSParent()) {
            // 最佳顶点与父亲之间的联边被标记为TREE
            graph.edgeFromTo(bestVertex.getBFSParent(), bestVertex).setType(Edge.TREE);
        }

        return bestVertex;
    }
}
