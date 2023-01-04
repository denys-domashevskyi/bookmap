package org.example.model;

import java.util.ArrayList;
import java.util.List;

public class Bookmap {
    private List<Values> bids;
    private List<Values> asks;

    public Bookmap() {
        this.bids = new ArrayList<>();
        this.asks = new ArrayList<>();
    }

    public List<Values> getBids() {
        return bids;
    }

    public List<Values> getAsks() {
        return asks;
    }

    @Override
    public String toString() {
        return "Bookmap{"
                + "bids=" + bids
                + ", asks=" + asks
                + '}';
    }
}
