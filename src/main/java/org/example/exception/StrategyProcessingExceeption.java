package org.example.exception;

public class StrategyProcessingExceeption extends RuntimeException {
    public StrategyProcessingExceeption(String message, Throwable cause) {
        super(message, cause);
    }

    public StrategyProcessingExceeption(String message) {
        super(message);
    }
}
