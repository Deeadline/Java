package com.company.Bank.controllers;

import com.company.Bank.implement.IFileManager;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileManager implements IFileManager {

    @Override
    public boolean removeFile(String path) {
        File file = new File(path);
        return file.delete();
    }

    @Override
    public boolean isFileExist(String path) {
        File file = new File(path);
        return file.exists() && file.isFile();
    }

    @Override
    public void openFile(String path) throws IOException {
        File file = new File(path);
        file.createNewFile();
    }

    @Override
    public void saveToFile(String path, String contents) throws IOException {
        PrintWriter printWriter = null;
        try {
            printWriter = new PrintWriter(new FileWriter(path, true));
            printWriter.write(contents + "\r\n");
        } finally {
            if (printWriter != null)
                printWriter.close();
        }
    }

/*
    @Override
    public void updateFile(String path) throws IOException {
        BufferedReader fileReader = null;
        PrintWriter fileWriter = null;
        List<String> lines = new ArrayList<>();
        try {
            fileReader = new BufferedReader(new FileReader(path));
            String line;
            while ((line = fileReader.readLine()) != null) {
                if (line.contains("'" + number + "'")) {
                    String lin = line.substring(line.indexOf("A"), line.lastIndexOf("=") + 1).concat(String.valueOf(value));
                    lines.add(lin);
                } else {
                    lines.add(line);
                }
            }
        } finally {
            if (fileReader != null)
                fileReader.close();
        }
        try {
            fileWriter = new PrintWriter(new FileWriter(path));
            for (String line : lines)
                fileWriter.write(line + "\r\n");
        } finally {
            if (fileWriter != null)
                fileWriter.close();
        }
    }
*/

    @Override
    public List<String> readFromFile(String path) throws IOException {
        BufferedReader fileReader = null;
        List<String> content = new ArrayList<>();
        try {
            fileReader = new BufferedReader(new FileReader(path));
            String line;
            while ((line = fileReader.readLine()) != null) {
                content.add(line);
            }
        } finally {
            if (fileReader != null)
                fileReader.close();
        }
        return content;
    }
}
