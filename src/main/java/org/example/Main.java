package org.example;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//Directs and tells spring boot that this is the main configuration
@SpringBootApplication


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
//This runs the embedded server , connects to H2 database, and executes my NetworkLoader to fill the graph inn memory
        SpringApplication.run(Main.class, args);
    }
}