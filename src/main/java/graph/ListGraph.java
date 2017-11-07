package graph;

import base.Iterator;
import base.Position;
import list.DLNodeList;
import list.List;

/**
 * Description: Graph implemented based on adjacency list.
 *
 * @Author: dong
 * @Date: 2017-08-20
 * @Time: 19:53
 */
public class ListGraph implements Graph {

    protected List<Edge> edges;
    protected List<Vertex> vertices;

    public ListGraph() {
        edges = new DLNodeList<>();
        vertices = new DLNodeList<>();
    }

    public ListGraph(List<Edge> edges, List<Vertex> vertices) {
        this.edges = edges;
        this.vertices = vertices;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public List<Vertex> getVertices() {
        return vertices;
    }

    @Override
    public int vNumber() {
        return vertices.getSize();
    }

    @Override
    public int eNumber() {
        return edges.getSize();
    }

    @Override
    public Iterator vertices() {
        return vertices.elements();
    }

    @Override
    public Iterator vPositions() {
        return vertices.positions();
    }

    @Override
    public Iterator edges() {
        return edges.elements();
    }

    @Override
    public Iterator ePositions() {
        return edges.positions();
    }

    /**
     * Check whether there's an edge from u to v.
     *
     * @param u
     * @param v
     * @return
     */
    @Override
    public boolean areAdjacent(Vertex u, Vertex v) {
        return edgeFromTo(u, v) != null;
    }

    /**
     * Get the edge from u to v. If there's not, return null.
     * 取顶点u的出边迭代器it;
     * 通过it逐一检查u的每一条出边e;
     * 一旦e的终点为v，则报告true;
     * 若e的所有出边都已检查过，则返回false;
     *
     * @param u
     * @param v
     * @return
     */
    @Override
    public Edge edgeFromTo(Vertex u, Vertex v) {
        for (Iterator iterator = u.outEdges(); iterator.hasNext(); ) {
            Edge edge = (Edge) iterator.getNext();
            if (v == edge.getVPosInV(1).getElem()) {
                return edge;
            }
        }
        return null;
    }

    /**
     * Remove v from vertices and return v.
     * 扫描v的出边邻接表，（调用removeEdge()算法）将所有边逐一删除;
     * 扫描v的入边邻接表，（调用removeEdge()算法）将所有边逐一删除;
     *
     * @param v
     * @return
     */
    @Override
    public Vertex remove(Vertex v) {
        while (0 < v.outDegree()) {
            remove((Edge) (((ListVertex) v).outEdges.first()).getElem());
        }
        while (0 < v.inDegree()) {
            remove((Edge) (((ListVertex) v).inEdges.first()).getElem());
        }
        return vertices.remove(v.getVPosInV());
    }

    /**
     * Remove e from edges and return e.
     * 从起点u的出边邻接表中删除e;
     * 从终点v的入边邻接表中删除e;
     * 从边表E中删除e;
     *
     * @param e
     * @return
     */
    @Override
    public Edge remove(Edge e) {
        //从起点的出边表中删除e
        ((ListVertex) e.getVPosInV(0).getElem()).outEdges.remove(e.getEPosInI(0));
        //从终点的入边表中删除e
        ((ListVertex) e.getVPosInV(1).getElem()).inEdges.remove(e.getEPosInI(1));
        //从边表中删除e
        return edges.remove(e.getEPosInE());
    }

    /**
     * Insert the new vertex v to vertices, then return its position.
     *
     * @param v
     * @return
     */
    @Override
    public Position insert(Vertex v) {
        return vertices.insertLast(v);
    }

    /**
     * Insert the new edge e to vertices, then return its position.
     *
     * @param e
     * @return
     */
    @Override
    public Position insert(Edge e) {
        return edges.insertLast(e);
    }
}
