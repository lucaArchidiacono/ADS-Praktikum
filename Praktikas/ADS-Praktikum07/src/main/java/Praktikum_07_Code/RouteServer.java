package Praktikum_07_Code;

import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.function.Consumer;
import java.util.regex.Pattern;

public class RouteServer implements CommandExecutor {
    Graph<DijkstraNode, Edge> graph;

    @Override
    public String execute(String command) {
        String[] lines = command.split("\n");
        graph = new AdjListGraph<>(DijkstraNode.class, Edge.class);
        try {
            for (String line : lines) {
                String[] datas = line.split(" ");
                graph.addEdge(datas[0],datas[1],Double.parseDouble(datas[2]));
                graph.addEdge(datas[1],datas[0],Double.parseDouble(datas[2]));
            }
        } catch (Throwable t) {
            t.printStackTrace();
        }

        String start = "Lugano";
        String goal = "Winterthur";
        DijkstraNode node = findShortestDistance(start, goal);

        StringBuilder sb = new StringBuilder();
        double destDist = node.dist;
        while(node!=null){
            sb.insert(0, " "+node.name);
            node = node.prev;
        }

        sb.insert(0, "distance = " + destDist + "\n");
        return sb.toString();
    }

    private DijkstraNode findShortestDistance(String start, String goal) {
        Queue<DijkstraNode> pq = new PriorityQueue<>();
        DijkstraNode currentNode = graph.findNode(start);
        DijkstraNode goalNode = graph.findNode(goal);
        double dist;
        currentNode.dist = 0;
        pq.add(currentNode);

        while(!pq.isEmpty()) {
            currentNode = pq.remove();
            if (currentNode.name.equals(goalNode.name)) {
                return currentNode;
            }
            currentNode.mark = true;

            for (Edge edge: (List<Edge>)currentNode.edges) {
                DijkstraNode n = (DijkstraNode) edge.dest;
                n.dist = Integer.MAX_VALUE;
                if (!n.getMark()) {
                     dist = edge.weight + currentNode.dist;
                    if (dist < n.dist) {
                        n.dist = dist;
                        n.prev = currentNode;
                        pq.remove(n);
                        pq.add(n);
                    }
                }
            }
        }
        return null;
    }
}
