package com.company.Bank.Implements;

import java.io.IOException;

public interface IFileManager {
    void openFile(String path) throws IOException;

    void saveToFile(String path, String contents) throws IOException;

    void readFromFile(String path) throws IOException;

    boolean isFileExist(String path);

    boolean removeFile(String path);

    void updateFile(String path, int value, String number) throws IOException;

}
