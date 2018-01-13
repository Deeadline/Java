package com.company.bank.controllers;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileManager {
    private static FileManager file;

    private FileManager() {
    }

    public static FileManager getFile() {
        if (file == null) {
            file = new FileManager();
        }
        return file;
    }

    public boolean removeFile(String path) {
        File file = new File(path);
        return file.delete();
    }

    public boolean isFileExist(String path) {
        File file = new File(path);
        return file.exists() && file.isFile();
    }

    public void openFile(String path) throws IOException {
        File file = new File(path);
        file.createNewFile();
    }

    public void saveToFile(String path, String contents) throws IOException {
        try {
            FileWriter writer = new FileWriter(path, true);
            PrintWriter printWriter = new PrintWriter(writer);
            printWriter.write(contents + "\r\n");
            printWriter.close();
        } catch (IOException ex) {
            throw ex;
        }
    }

    public List<String> readFromFile(String path) throws IOException {
        List<String> content = new ArrayList<>();
        try {
            FileReader reader = new FileReader(path);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                content.add(line);
            }
            bufferedReader.close();
        } catch (IOException ex) {
            throw ex;
        }
        return content;
    }
}
