package it.tino.adventofcode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class ResourcesFileReader {

    /**
     * Easily read project resources files.
     * @param filename The name of the file inside the resources'
     *                 directory.
     * @return The file lines, or an empty list if the file doesn't
     * exist or an i/o error occurred.
     */
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
