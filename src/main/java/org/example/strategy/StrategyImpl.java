package org.example.strategy;

import java.util.Comparator;
import org.example.exception.StrategyProcessingExceeption;
import org.example.model.Bookmap;
import org.example.model.Values;

public class StrategyImpl implements Strategy {
    private Bookmap bookmap;
    private Values bestAsk;
    private Values bestBid;
    private StringBuilder builder = new StringBuilder();

    public StrategyImpl(Bookmap bookmap) {
        this.bookmap = bookmap;
    }

    @Override
    public void lineStrategy(String line) {
        char operation = line.charAt(0);
        switch (operation) {
            case 'u':
                updateStrategy(line);
                return;
            case 'q':
                queryStrategy(line);
                return;
            case 'o':
                orderStrategy(line);
                return;
            default:
                throw new StrategyProcessingExceeption(
                        String.format("There is no such strategy %s!", line));
        }
    }

    @Override
    public String getResult() {
        return builder.toString();
    }

    private void queryStrategy(String line) {
        if (line.contains("best_bid")) {
            bestBid = bookmap.getBids().stream()
                    .sorted(Comparator.comparing(Values::getPrice)
                            .thenComparing(Values::getQuantity)
                            .reversed())
                    .findFirst()
                    .orElseThrow(() -> new StrategyProcessingExceeption("Can't get best bid!"));
            builder.append(bestBid.getPrice() + "," + bestBid.getQuantity() + "\n");
        } else if (line.contains("best_ask")) {
            bestAsk = bookmap.getBids().stream()
                    .sorted(Comparator.comparing(Values::getPrice)
                            .thenComparing(Values::getQuantity))
                    .findFirst()
                    .orElseThrow(() -> new StrategyProcessingExceeption("Can't get best ask!"));
            builder.append(bestAsk.getPrice() + "," + bestAsk.getQuantity() + "\n");
        } else if (line.contains("size")) {
            int price = Integer.parseInt(line.substring(7));
            long asksWithSize = bookmap.getAsks().stream()
                    .filter(e -> e.getPrice() == price)
                    .count();
            long bidsWithSize = bookmap.getBids().stream()
                    .filter(e -> e.getPrice() == price)
                    .count();
            builder.append(asksWithSize + bidsWithSize + "\n");
        }
    }

    private void orderStrategy(String line) {
        if (line.contains("sell")) {
            bestBid.setQuantity(bestBid.getQuantity()
                    - Integer.parseInt(line.substring(7)));
        } else {
            bestAsk.setQuantity(bestAsk.getQuantity()
                    - Integer.parseInt(line.substring(7)));
        }
    }

    private void updateStrategy(String line) {
        String[] split = line.split(",");
        Values values = new Values(Integer.parseInt(split[1]), Integer.parseInt(split[2]));
        if (line.contains("ask")) {
            bookmap.getAsks().add(values);
        } else {
            bookmap.getBids().add(values);
        }
    }
}
