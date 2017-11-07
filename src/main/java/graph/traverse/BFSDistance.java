package graph.traverse;

import graph.Graph;
import graph.Vertex;

/**
 * Description: 
 *
 * @Author: dong
 * @Date: 2017-08-20
 * @Time: 23:30
 */
public class BFSDistance extends BFS {

    public BFSDistance(Graph g) {
        super(g);
    }

    /**
     * 顶点访问操作：在本算法中，info是顶点v的前驱
     *
     * @param v
     * @param info
     */
    @Override
    protected void visit(Vertex v, Object info) {
        if (null == info) {//v为BFS的起始顶点
            v.setDistance(0);
        } else {
            v.setDistance(((Vertex) info).getDistance() + 1);//设置v到s的距离 = 前驱的距离+1
        }
    }

    /**
     * 基于BFS实现的最短距离算法：s为起始顶点，info向算法传递参数
     *
     * @param s
     * @param info
     */
    @Override
    public void minDistance(Vertex s, Object info) {
        reset();
        traverse(s, info);
    }
}
