package graph;

import base.Position;
import linkedList.DLNode;

/*
 * Description: 
 *
 * @Author: dong
 * @Date: 2017-08-20
 * @Time: 18:54
 */
public class ListEdge implements Edge {
    // weight information of the edge
    protected int weight;
    // current edge's position in the edge list
    protected Position ePosInE;
    // positions of current edge's two vertices
    protected Position[] vPosInV;
    // position TODO
    protected Position[] ePosInI;
    // the type after traversal
    protected int type;

    /**
     * Generate a new edge from u to v (Assume the edge doesn't exist)
     *
     * @param graph
     * @param u
     * @param v
     * @param w
     */
    public ListEdge(Graph graph, ListVertex u, ListVertex v, int w) {
        weight = w;
        ePosInE = graph.insert(this);
        vPosInV = new DLNode[2];
        vPosInV[0] = u.getVPosInV();
        vPosInV[1] = v.getVPosInV();
        ePosInI = new DLNode[2];
        ePosInI[0] = u.outEdges.insertLast(this);
        ePosInI[1] = v.inEdges.insertLast(this);
        type = UNKNOWN;
    }


    /**
     * the info of current edge
     *
     * @return
     */
    @Override
    public int getWeight() {
        return weight;
    }

    /**
     * update the edge information to x
     *
     * @param x
     * @return the former information
     */
    @Override
    public void setWeight(int x) {
        weight = x;
    }

    @Override
    public Position getEPosInE() {
        return ePosInE;
    }

    @Override
    public Position getVPosInV(int i) {
        return vPosInV[i];
    }

    @Override
    public Position getEPosInI(int i) {
        return ePosInI[i];
    }

    @Override
    public int getType() {
        return type;
    }

    @Override
    public int setType(int t) {
        int oldType = type;
        type = t;
        return oldType;
    }
}
