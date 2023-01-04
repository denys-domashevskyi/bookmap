package org.example;

import java.util.List;
import org.example.model.Bookmap;
import org.example.strategy.Strategy;
import org.example.strategy.StrategyImpl;
import org.example.util.FileReader;
import org.example.util.FileWriter;

public class Main {
    public static void main(String[] args) {
        final long startTime = System.currentTimeMillis();
        String inputPath = "input.txt";
        String outputPath = "output.txt";
        Bookmap bookmap = new Bookmap();
        List<String> result = FileReader.readFrom(inputPath);
        Strategy strategy = new StrategyImpl(bookmap);
        for (String res : result) {
            strategy.lineStrategy(res);
        }
        FileWriter.writeTo(outputPath, strategy.getResult());
        final long endTime = System.currentTimeMillis();
        System.out.println("Total execution time: " + (endTime - startTime) + " milliseconds");
    }
}
