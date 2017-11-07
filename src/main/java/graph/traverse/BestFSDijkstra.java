package graph.traverse;

import base.Iterator;
import graph.Edge;
import graph.Graph;
import graph.Vertex;

/**
 * Description:（有向）带权图的单源点最短路径算法
 *
 * @Author: dong
 * @Date: 2017-08-21
 * @Time: 20:52
 */
public class BestFSDijkstra extends BestFS {

    public BestFSDijkstra(Graph g) {
        super(g);
    }

    /**
     * 更新尚未访问的顶点到源点的最短距离
     *
     * @param v
     */
    @Override
    protected void updateDistanceAfter(Vertex v) {
        for (Iterator it = v.outEdges(); it.hasNext(); ) {//检查与顶点v
            Edge e = (Edge) it.getNext();//通过边e = (v, w)
            Vertex w = (Vertex) e.getVPosInV(1).getElem();//相联的每一顶点w
            int weight = ((Integer) e.getWeight()).intValue();//根据边(v, w)的权重
            if (w.getDistance() > v.getDistance() + weight) {//取原距离与新距离中的小者
                w.setDistance(v.getDistance() + weight);
                w.setBFSParent(v);
            }
        }
    }
}
