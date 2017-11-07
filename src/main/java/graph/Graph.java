package graph;

import base.Iterator;
import base.Position;

/**
 * Description:
 * ===== Directed Graph Interface =====
 * Undirected graphs can also be regarded as directed, if only replaceAt each undirected edge
 * with a pair of directed edges.
 *
 * @Author: dong
 * @Date: 2017-08-18
 * @Time: 21:50
 */
public interface Graph {
    int vNumber();

    int eNumber();

    Iterator vertices();

    Iterator vPositions();

    Iterator edges();

    Iterator ePositions();

    boolean areAdjacent(Vertex u, Vertex v);

    Edge edgeFromTo(Vertex u, Vertex v);

    //将顶点v从顶点集中删除，并返回之
    Vertex remove(Vertex v);

    //将边e从边集中删除，并返回之
    Edge remove(Edge e);

    //在顶点集V中插入新顶点v，并返回其位置
    Position insert(Vertex v);

    //在边集E中插入新边e，并返回其位置
    Position insert(Edge e);
}
