package graph.traverse;

import graph.Graph;
import graph.Vertex;
import stack.ArrayStack;
import stack.Stack;

/**
 * Description: 有向图基于DFS模板的可达分量算法
 *
 * @Author: dong
 * @Date: 2017-08-21
 * @Time: 20:10
 */
public class DFSReachableComponent extends DFS {

    public DFSReachableComponent(Graph g) {
        super(g);
    }

    /**
     * DFS过程中对顶点v的具体访问操作（info实际上是一个栈，记录所有可达的顶点）
     *
     * @param v
     * @param info
     */
    @Override
    protected void visit(Vertex v, Object info) {
        ((Stack) info).push(v);
    }

    /**
     * 基于DFS的可达性算法：s为起始顶点（info实际上是一个栈，记录所有可达的顶点）
     *
     * @param s
     * @param info
     */
    @Override
    public void minDistance(Vertex s, Object info) {
        reset();
        Stack stack = new ArrayStack();
        traverse(s, info);
    }
}
