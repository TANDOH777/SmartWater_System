package org.example.component;

import org.example.model.Node;
import org.example.model.Pipe;
import org.example.model.PipeStatus;
import org.example.repository.NodeRepository;
import org.example.repository.PipeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataSeeder implements CommandLineRunner {

    private final NodeRepository nodeRepository;
    private final PipeRepository pipeRepository;

    public DataSeeder(NodeRepository nodeRepository, PipeRepository pipeRepository) {
        this.nodeRepository = nodeRepository;
        this.pipeRepository = pipeRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (nodeRepository.count() == 0) {
            // Create Nodes: 1 Source, 1 Junction, 1 Community
            Node source = new Node();
            source.setName("Main Reservoir");
            source.setType(NodeType.SOURCE);
            nodeRepository.save(source);

            Node junction = new Node();
            junction.setName("Central Junction");
            junction.setType(NodeType.JUNCTION);
            nodeRepository.save(junction);

            Node community = new Node();
            community.setName("Downtown Community");
            community.setType(NodeType.COMMUNITY);
            community.setPopulation(5000);
            community.setDemand(150.0);
            nodeRepository.save(community);

            // Create Pipes connecting them
            Pipe pipe1 = new Pipe();
            pipe1.setFromNodeId(source.getId());
            pipe1.setToNodeId(junction.getId());
            pipe1.setLength(10.0);
            pipe1.setCapacity(500.0);
            pipe1.setStatus(PipeStatus.OK);
            pipeRepository.save(pipe1);

            Pipe pipe2 = new Pipe();
            pipe2.setFromNodeId(junction.getId());
            pipe2.setToNodeId(community.getId());
            pipe2.setLength(15.0);
            pipe2.setCapacity(300.0);
            pipe2.setStatus(PipeStatus.OK);
            pipeRepository.save(pipe2);

            System.out.println(">>> Sample water network data seeded into database! <<<");
        }
    }
}