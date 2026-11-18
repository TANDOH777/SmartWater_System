package org.example.graph;

import org.example.model.Pipe;
import org.springframework.stereotype.Component;
import java.util.*;

@Component // Tells Spring to manage this class globally in memory
public class WaterNetworkGraph {

    // An adjacency list: Think of this as a phone book where every node ID
    // points to a list of connected pipes.
    private final Map<Long, List<Pipe>> adjacencyList = new HashMap<>();

    public void addNode(Long nodeId) {
        adjacencyList.putIfAbsent(nodeId, new ArrayList<>());
    }

    public void addPipe(Pipe pipe) {
        adjacencyList.putIfAbsent(pipe.getFromNodeId(), new ArrayList<>());
        adjacencyList.get(pipe.getFromNodeId()).add(pipe);
    }

    public Map<Long, List<Pipe>> getAdjacencyList() {
        return adjacencyList;
    }
}