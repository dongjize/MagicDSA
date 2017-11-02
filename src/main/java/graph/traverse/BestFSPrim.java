package graph.traverse;

import base.Iterator;
import graph.Edge;
import graph.Graph;
import graph.Vertex;

/**
 * Description:（有向）带权图最小生成树的Prim-Jarnik算法
 *
 * @Author: dong
 * @Date: 2017-10-10
 * @Time: 23:15
 */
public class BestFSPrim extends BestFS {
    public BestFSPrim(Graph g) {
        super(g);
    }

    @Override
    protected void updateDistanceAfter(Vertex v) {
        for (Iterator it = v.outEdges(); it.hasNext(); ) {
            Edge e = (Edge) it.getNext();
            Vertex w = (Vertex) e.getVPosInV(1).getElem();
            int weight = e.getWeight();
            if (weight < w.getDistance()) {
                w.setDistance(weight);
                w.setBFSParent(v);
            }
        }
    }
}
