package it.tino.adventofcode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class FileLinesReader {

    public List<String> getFileLines(String filename) {
        InputStream inputStream = this.getClass()
                .getClassLoader()
                .getResourceAsStream(filename);

        if (inputStream == null) {
            return Collections.emptyList();
        }

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        LinkedList<String> lines = new LinkedList<>();
        try {
            while (bufferedReader.ready()) {
                lines.addLast(bufferedReader.readLine());
            }
        } catch (IOException e) {
            return Collections.emptyList();
        }

        return lines;
    }
}
