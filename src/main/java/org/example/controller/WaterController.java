package org.example.controller;

import org.example.algorithm.DijkstraRouter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WaterController {

    private final DijkstraRouter dijkstraRouter;

    public WaterController(DijkstraRouter dijkstraRouter) {
        this.dijkstraRouter = dijkstraRouter;
    }

    @GetMapping("/route")
    public List<Long> getRoute(@RequestParam Long start, @RequestParam Long target) {
        return dijkstraRouter.findAlternatePath(start, target);
    }
}