package graph.traverse;

import base.Iterator;
import graph.Edge;
import graph.Graph;
import graph.Vertex;

/**
 * Description: 图遍历父类
 *
 * @Author: dong
 * @Date: 2017-08-20
 * @Time: 20:50
 */
public abstract class GraphTraverse {
    protected final static int UNDISCOVERED = 0;//尚未被发现的顶点
    protected final static int DISCOVERED = 1;//已被发现的顶点
    protected final static int VISITED = 2;//已访问过的顶点

    protected final static int UNKNOWN = 0;//未知边
    protected final static int TREE = 1;//树边
    protected final static int CROSS = 2;//横跨边
    protected final static int FORWARD = 3;//前向跨边
    protected final static int BACKWARD = 4;//后向跨边

    protected Graph graph;

    public GraphTraverse(Graph g) {
        this.graph = g;
    }

    /**
     * 将G中各顶点的标志、各边的分类复位
     * 所有顶点的状态置为UNDISCOVERED，最短距离初始化为无穷大
     */
    protected void reset() {
        for (Iterator it = graph.vertices(); it.hasNext(); ) {
            Vertex v = (Vertex) it.getNext();
            v.setStatus(UNDISCOVERED);
            v.setDistance(Integer.MAX_VALUE);
        }
        for (Iterator it = graph.edges(); it.hasNext(); ) {
            ((Edge) it.getNext()).setType(UNKNOWN);//置为UNKNOWN
        }
    }

    /**
     * 遍历过程中对顶点v的具体访问操作的模板：取决于、服务于具体的算法algorithm()
     *
     * @param v
     * @param info
     * @return
     */
    protected abstract void visit(Vertex v, Object info);

    /**
     * 基于遍历操作实现的其它算法的模板：s为起始顶点，info向算法传递参数及保存算法的返回信息
     *
     * @param s
     * @param info
     * @return
     */
    public abstract void minDistance(Vertex s, Object info);

    /**
     * 遍历算法模板
     *
     * @param v
     * @param info
     * @return
     */
    protected abstract void traverse(Vertex v, Object info);//从顶点v出发做遍历

}
