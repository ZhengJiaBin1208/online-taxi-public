package com.zjb.servicepassengeruser.service.impl;

/**
 * @ClassName RangeBoxConfigFileCopier
 * @Description TODO
 * @Author zhengjiabin
 * @Date 2023/8/24 17:43
 * @Version 1.0
 **/
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

public class RangeBoxConfigFileCopier {

    public static void main(String[] args) {
        String sourceRootFolder = "D:\\WeChat Files\\wxid_epilgk10co3u12\\FileStorage\\File\\2023-08\\8K转码素材 - 副本";
        String destinationFolder = "C:\\Users\\zhengjiabin\\Desktop\\test";
        String targetExtension = ".rangeboxconfig";

        List<File> sourceFiles = findFilesWithExtension(new File(sourceRootFolder), targetExtension);
        copyFilesToDestination(sourceFiles, new File(destinationFolder));
    }

    private static List<File> findFilesWithExtension(File folder, String extension) {
        List<File> fileList = new ArrayList<>();
        File[] files = folder.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    fileList.addAll(findFilesWithExtension(file, extension));
                } else if (file.getName().endsWith(extension)) {
                    fileList.add(file);
                }
            }
        }

        return fileList;
    }

    private static void copyFilesToDestination(List<File> sourceFiles, File destinationFolder) {
        if (!destinationFolder.exists()) {
            destinationFolder.mkdirs();
        }

        for (File sourceFile : sourceFiles) {
            Path sourcePath = sourceFile.toPath();

            // Get the parent folder's name
            String parentFolderName = sourceFile.getParentFile().getName();
            String newFileName = parentFolderName + "_" + sourceFile.getName();

            Path destinationPath = new File(destinationFolder, newFileName).toPath();

            try {
                Files.copy(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
                System.out.println("Copied: " + sourcePath + " -> " + destinationPath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

