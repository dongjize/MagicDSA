package graph;

import base.Iterator;
import graph.traverse.BestFSDijkstra;
import stack.ListStack;
import stack.Stack;

/**
 * Description: 
 *
 * @Author: dong
 * @Date: 2017-08-25
 * @Time: 22:50
 */
public class GraphTest {
    public static void main(String[] args) {
        Graph graph = new ListGraph();
        ListVertex v1 = new ListVertex(graph, 1);
        ListVertex v2 = new ListVertex(graph, 2);
        ListVertex v3 = new ListVertex(graph, 3);
        ListVertex v4 = new ListVertex(graph, 4);
        ListVertex v5 = new ListVertex(graph, 5);
        ListVertex v6 = new ListVertex(graph, 6);

        ListEdge e12 = new ListEdge(graph, v1, v2, 1);
        ListEdge e13 = new ListEdge(graph, v1, v3, 12);
        ListEdge e23 = new ListEdge(graph, v2, v3, 9);
        ListEdge e24 = new ListEdge(graph, v2, v4, 3);
        ListEdge e35 = new ListEdge(graph, v3, v5, 5);
        ListEdge e43 = new ListEdge(graph, v4, v3, 4);
        ListEdge e45 = new ListEdge(graph, v4, v5, 9);
        ListEdge e46 = new ListEdge(graph, v4, v6, 15);
        ListEdge e56 = new ListEdge(graph, v5, v6, 4);

        for (Iterator iterator = graph.vertices(); iterator.hasNext(); ) {
            Vertex v = (Vertex) (iterator.getNext());
            System.out.println(v.getInfo());
            for (Iterator it = v.outEdges(); it.hasNext(); ) {
                Edge edge = (Edge) it.getNext();
                System.out.print("weight: " + edge.getWeight() + " ");
                System.out.println("info: " + (int) ((ListVertex) (edge.getVPosInV(1).getElem())).getInfo());
            }

        }

        BestFSDijkstra dijkstra = new BestFSDijkstra(graph);
        dijkstra.minDistance(v1, null);


        System.out.println("v1 -> v6: " + v6.getDistance());

        System.out.println("v1 parent: " + v1.getBFSParent());
        System.out.println("v6 parent: " + v6.getBFSParent().getBFSParent().getInfo());
        Stack<Integer> stack = new ListStack();
        reverseList(stack, v6);
        while (stack.getSize() > 0) {
            System.out.print(stack.pop() + " ");
        }
    }

    static void reverseList(Stack<Integer> stack, Vertex v) {
        if (v == null) {
            return;
        }
        stack.push((Integer) v.getInfo());
        if (v.getBFSParent() != null) {
            reverseList(stack, v.getBFSParent());
        }
    }
}
