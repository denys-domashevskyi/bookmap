package org.example.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileWriter {
    public static boolean writeTo(String path, String result) {
        try {
            Files.write(Path.of(path), result.getBytes());
            return true;
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file by path: " + path, e);
        }
    }
}
