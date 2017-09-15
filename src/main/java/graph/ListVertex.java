package graph;


import base.Iterator;
import base.Position;
import list.DLNodeList;
import list.List;

/*
 * Description: Vertex implemented based on edge adjacency list
 *
 * @Author: dong
 * @Date: 2017-08-19
 * @Time: 22:54
 */
public class ListVertex implements Vertex {

    //当前顶点中存放的数据元素
    protected Object info;
    //当前顶点在所属的图的顶点表V中的位置
    protected Position vPosInV;
    // stores position of each edge whose tail is this vertex
    protected List<Edge> outEdges;
    // stores position of each edge whose head is this vertex
    protected List<Edge> inEdges;
    // status of vertex (while traversing the graph)
    protected int status;
    //时间标签：DFS过程中该顶点被发现时的时刻
    protected int dStamp;
    //时间标签：DFS过程中该顶点被访问结束时的时刻
    protected int fStamp;
    //到指定起点的距离：BFS、Dijkstra等算法所确定该顶点到起点的距离
    protected int distance;
    //在最短距离树（BFS或BestFS）中的父亲
    protected Vertex bfsParent;

    public ListVertex(Graph graph, Object x) {
        info = x;
        vPosInV = graph.insert(this);
        outEdges = new DLNodeList<>();
        inEdges = new DLNodeList<>();
        status = UNDISCOVERED;
        dStamp = fStamp = Integer.MAX_VALUE;
        distance = Integer.MAX_VALUE;
        bfsParent = null;
    }

    @Override
    public Object getInfo() {
        return info;
    }

    @Override
    public void setInfo(Object x) {
        Object e = info;
        info = x;
    }

    @Override
    public int outDegree() {
        return outEdges.getSize();
    }

    @Override
    public int inDegree() {
        return inEdges.getSize();
    }

    @Override
    public Iterator inEdges() {
        return inEdges.elements();
    }

    @Override
    public Iterator inEdgePositions() {
        return inEdges.positions();
    }

    @Override
    public Iterator outEdges() {
        return outEdges.elements();
    }

    @Override
    public Iterator outEdgePositions() {
        return outEdges.positions();
    }

    @Override
    public Position getVPosInV() {
        return vPosInV;
    }

    @Override
    public int getStatus() {
        return status;
    }

    @Override
    public void setStatus(int s) {
        status = s;
    }

    @Override
    public int getDStamp() {
        return dStamp;
    }

    @Override
    public void setDStamp(int s) {
        dStamp = s;
    }

    @Override
    public int getFStamp() {
        return fStamp;
    }

    @Override
    public void setFStamp(int s) {
        fStamp = s;
    }

    @Override
    public int getDistance() {
        return distance;
    }

    @Override
    public void setDistance(int s) {
        distance = s;
    }

    /**
     * 读取、设置顶点至起点的最短距离（BFS）
     *
     * @return
     */
    @Override
    public Vertex getBFSParent() {
        return bfsParent;
    }

    /**
     * 读取、设置顶点在的DFS、BFS、BestFS或MST树中的父亲
     *
     * @param s
     * @return
     */
    @Override
    public Vertex setBFSParent(Vertex s) {
        Vertex parent = bfsParent;
        bfsParent = s;
        return parent;
    }
}
