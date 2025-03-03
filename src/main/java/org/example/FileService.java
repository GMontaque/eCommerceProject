package org.example;

import java.io.*;

public class FileService {

    private final String path;

    public FileService(String path) {
        this.path = path;
    }

    public String readFile() {
        String content = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));

            String line = br.readLine();
            while (line != null) {
                content += line + "\n";
                line = br.readLine();
            }
            br.close();
        } catch (IOException e) {
            System.out.println("Error: file could not be read");
        }
        return content;
    }


    public void writeFile(String content) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(path, true));
            writer.write(content);

            writer.close();
        } catch (IOException ioe) {
            System.out.println("Couldn't write to file");
        }
    }
}
