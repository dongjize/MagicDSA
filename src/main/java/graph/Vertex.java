package graph;

import base.Iterator;
import base.Position;

/*
 * Description:
 * ===== Vertex Interface of a Graph =====
 *
 * @Author: dong
 * @Date: 2017-08-18
 * @Time: 21:52
 */
public interface Vertex {
    int UNDISCOVERED = 0;
    int DISCOVERED = 1;
    int VISITED = 2;

    Object getInfo();

    void setInfo(Object x);

    int outDegree();

    int inDegree();

    //return the iterator for the edges and edge positions of a vertex
    Iterator inEdges();

    Iterator inEdgePositions();

    Iterator outEdges();

    Iterator outEdgePositions();

    //取当前顶点在所属的图的顶点集V中的位置
    Position getVPosInV();

    //读取、设置顶点的状态（DFS + BFS）
    int getStatus();

    void setStatus(int s);

    //读取、设置顶点的时间标签（DFS）
    int getDStamp();

    void setDStamp(int s);

    int getFStamp();

    void setFStamp(int s);

    //读取、设置顶点至起点的最短距离（BFS或BestFS）
    int getDistance();

    void setDistance(int s);

    //读取、设置顶点在的DFS、BFS、BestFS或MST树中的父亲
    Vertex getBFSParent();

    Vertex setBFSParent(Vertex s);

}
