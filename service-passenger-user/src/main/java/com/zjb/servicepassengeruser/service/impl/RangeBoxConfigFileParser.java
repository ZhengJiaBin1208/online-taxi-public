package com.zjb.servicepassengeruser.service.impl;

/**
 * @ClassName RangeBoxConfigFileParser
 * @Description TODO
 * @Author zhengjiabin
 * @Date 2023/8/24 17:28
 * @Version 1.0
 **/
import java.io.*;
import java.util.*;

public class RangeBoxConfigFileParser {

    public static void main(String[] args) {
        String folderPath = "D:\\WeChat Files\\wxid_epilgk10co3u12\\FileStorage\\File\\2023-08\\8K转码素材 - 副本";
        List<File> files = findRangeBoxConfigFiles(folderPath);

        List<String> fileContents = new ArrayList<>();
        for (File file : files) {
            String fileContent = readFromFile(file);
            fileContents.add(fileContent);
        }

        writeToFile(fileContents, "RangeBoxConfigContents.txt");
    }

    public static List<File> findRangeBoxConfigFiles(String folderPath) {
        List<File> files = new ArrayList<>();
        File folder = new File(folderPath);

        if (folder.exists() && folder.isDirectory()) {
            findFilesRecursively(files, folder);
        }

        return files;
    }

    public static void findFilesRecursively(List<File> files, File folder) {
        File[] fileList = folder.listFiles();
        if (fileList != null) {
            for (File file : fileList) {
                if (file.isDirectory()) {
                    findFilesRecursively(files, file);
                } else if (file.getName().endsWith("rangeboxconfig")) {
                    files.add(file);
                }
            }
        }
    }

    public static String readFromFile(File file) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            StringBuilder content = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append(System.lineSeparator());
            }
            reader.close();
            return content.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static void writeToFile(List<String> fileContents, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (String content : fileContents) {
                writer.write(content);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

