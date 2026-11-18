package org.example.algorithm;

import org.example.graph.WaterNetworkGraph;
import org.example.model.Pipe;
import org.example.model.PipeStatus;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class DijkstraRouter {

    private final WaterNetworkGraph networkGraph;

    public DijkstraRouter(WaterNetworkGraph networkGraph) {
        this.networkGraph = networkGraph;
    }

    /**
     * Finds the shortest alternate path (by pipe length) from a start node to a target node,
     * ignoring any pipes that have burst.
     */
    public List<Long> findAlternatePath(Long startNodeId, Long targetNodeId) {
        Map<Long, List<Pipe>> adjList = networkGraph.getAdjacencyList();

        // Stores the shortest distance (total pipe length) to each node
        Map<Long, Double> distances = new HashMap<>();
        // Stores the path history to reconstruct the route later
        Map<Long, Long> previousNodes = new HashMap<>();

        // Priority queue to visit the node with the smallest accumulated distance next
        PriorityQueue<NodeDistance> pq = new PriorityQueue<>(Comparator.comparingDouble(nd -> nd.distance));

        for (Long nodeId : adjList.keySet()) {
            distances.put(nodeId, Double.MAX_VALUE);
        }

        distances.put(startNodeId, 0.0);
        pq.add(new NodeDistance(startNodeId, 0.0));

        while (!pq.isEmpty()) {
            NodeDistance current = pq.poll();
            Long currentNodeId = current.nodeId;

            // If we reached our target, stop early
            if (currentNodeId.equals(targetNodeId)) {
                break;
            }

            if (current.distance > distances.getOrDefault(currentNodeId, Double.MAX_VALUE)) {
                continue;
            }

            // Check all outgoing pipes (edges) from this node
            for (Pipe pipe : adjList.getOrDefault(currentNodeId, Collections.emptyList())) {
                // Skip pipes that are burst!
                if (pipe.getStatus() == PipeStatus.BURST) {
                    continue;
                }

                Long neighborNodeId = pipe.getToNodeId();
                double newDist = distances.get(currentNodeId) + pipe.getLength();

                if (newDist < distances.getOrDefault(neighborNodeId, Double.MAX_VALUE)) {
                    distances.put(neighborNodeId, newDist);
                    previousNodes.put(neighborNodeId, currentNodeId);
                    pq.add(new NodeDistance(neighborNodeId, newDist));
                }
            }
        }

        // Reconstruct path from target back to start
        LinkedList<Long> path = new LinkedList<>();
        Long curr = targetNodeId;

        if (!previousNodes.containsKey(curr) && !curr.equals(startNodeId)) {
            return Collections.emptyList(); // No path found (disconnected)
        }

        while (curr != null) {
            path.addFirst(curr);
            curr = previousNodes.get(curr);
        }

        return path;
    }

    // Helper class to hold node ID and current distance for the Priority Queue
    private static class NodeDistance {
        Long nodeId;
        double distance;

        public NodeDistance(Long nodeId, double distance) {
            this.nodeId = nodeId;
            this.distance = distance;
        }
    }
}