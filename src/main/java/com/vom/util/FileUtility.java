package com.vom.util;

import java.io.*;
import java.nio.CharBuffer;

public class FileUtility {
    public String read(File file) {
        try {
            return readImpl(file.getName(), file.isFile(), file.isAbsolute(), new FileReader(file));
        } catch (FileNotFoundException e) {
            return "";
        }
    }

    String readImpl(String name, boolean isFile, boolean isAbsolute, Reader fileContents) {
        try {
            StringBuilder output = new StringBuilder();
            CharBuffer buf = CharBuffer.allocate(4096);
            while(fileContents.read(buf) >= 0) {
                output.append(buf.flip());
                buf.clear();
            }
            return output.toString();
        } catch (IOException e) {
            return "";
        }
    }

    public int write(File file, String data) {
        try {
            return writeImpl(file.getName(), file.isFile(), file.isAbsolute(), new FileWriter(file), data);
        } catch (IOException e) {
            return 0;
        }
    }

    int writeImpl(String name, boolean isFile, boolean isAbsolute, Writer fileContents, String data) {
        try {
            fileContents.write(data);
            fileContents.close();
        } catch (IOException e) {
            return 0;
        }
        return 0;
    }
}