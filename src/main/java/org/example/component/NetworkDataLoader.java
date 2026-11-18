package org.example.component;

import org.example.graph.WaterNetworkGraph;
import org.example.model.Node;
import org.example.model.Pipe;
import org.example.repository.NodeRepository;
import org.example.repository.PipeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class NetworkDataLoader implements CommandLineRunner {

    private final NodeRepository nodeRepository;
    private final PipeRepository pipeRepository;
    private final WaterNetworkGraph waterNetworkGraph;

    public NetworkDataLoader(NodeRepository nodeRepository, PipeRepository pipeRepository, WaterNetworkGraph waterNetworkGraph) {
        this.nodeRepository = nodeRepository;
        this.pipeRepository = pipeRepository;
        this.waterNetworkGraph = waterNetworkGraph;
    }

    @Override
    public void run(String... args) throws Exception {
        // 1. Load all nodes from database into memory graph
        for (Node node : nodeRepository.findAll()) {
            waterNetworkGraph.addNode(node.getId());
        }

        // 2. Load all pipes from database into memory graph
        for (Pipe pipe : pipeRepository.findAll()) {
            waterNetworkGraph.addPipe(pipe);
        }

        System.out.println(">>> Water network graph successfully loaded into memory! <<<");
    }
}