package com.realpacific.projectnoaa.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileUtils {

    public static File getFile(String path) {
        Path overallPath = getFullPathFromHome(path);
        File file = overallPath.toFile();
        if (file.exists()) return file;
        else return null;
    }

    public static Path getFullPathFromHome(String path) {
        String home = System.getProperty("user.home");
        return Paths.get(home, path);
    }

    public static boolean exists(String path) {
        Path overallPath = getFullPathFromHome(path);
        return Files.exists(overallPath);
    }

    /**
     * Get list of files that matches the given description
     *
     * @param path        path from home
     * @param description begin with * to search using extensions or full file name
     * @return the list of files
     */
    public static File[] getFilesWithNamesMatchingDescriptionAt(String path, String description) {
        Path overallPath = getFullPathFromHome(path);
        return new File(overallPath.toString()).listFiles((dir, name) -> {
            if (description.startsWith("*")) {
                String extension = description.replaceAll("\\*", "");
                return name.endsWith(extension);
            } else {
                return name.equals(description);
            }
        });
    }

    public static boolean move(String source, String destination) {
        String home = System.getProperty("user.home");
        try {
            if (!FileUtils.exists(destination)) {
                Files.createDirectories(Paths.get(home, destination));
            }

            Path overallPathToSource = Paths.get(source);

            if (!overallPathToSource.isAbsolute())
                overallPathToSource = Paths.get(home, source);
            Files.move(overallPathToSource, Paths.get(home, destination).resolve(overallPathToSource.getFileName()));
            System.out.println("Files moved to " + destination);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
