package com.realpacific.projectnoaa.utils;

import com.realpacific.projectnoaa.exceptions.InvalidInputException;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileUtils {

    public static File createFile(String path) {
        String home = System.getProperty("user.home");
        Path overallPath = Paths.get(home, path);
        File file = overallPath.toFile();
        if (file.exists()) return file;
        else throw new InvalidInputException("The file associated with path " + path + " was not found.");
    }
}
