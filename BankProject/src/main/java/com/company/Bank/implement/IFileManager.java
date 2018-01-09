package com.company.Bank.implement;

import java.io.IOException;
import java.util.List;

public interface IFileManager {
    boolean isFileExist(String path);

    boolean removeFile(String path);

    void openFile(String path) throws IOException;

    void saveToFile(String path, String contents) throws IOException;

    List<String> readFromFile(String path) throws IOException;

}
