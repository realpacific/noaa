package com.realpacific.projectnoaa.utils;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import static org.junit.Assert.*;

public class FileUtilsTest {


    @Test
    public void test() {
        String home = System.getProperty("user.home");
        Path path = Paths.get(home, "/Downloads/NOA/Stations.txt");
        System.out.println(path.toFile().exists());
        assertTrue(path.toFile().exists());

        assertFalse(Paths.get(home, "/Downloads/NOA/DOESNOTEXIST.txt").toFile().exists());
        System.out.println(Paths.get(home, "/Downloads/NOA/DOESNOTEXIST.txt").toFile().exists());

    }

    @Test
    public void testForExistenceOfFile() {
        assertTrue(FileUtils.exists("/Downloads"));
        assertFalse(FileUtils.exists("/elfefdsklfjl"));
    }


    @Test
    public void testForFilesList_ShouldSupportRegexSearchAndNameSearch() {
        String basePathForCurrentTest = "/testForFilesList/";
        String[] fileNames = new String[]{"Hello.txt", "World.txt", "12.txt", "tset.gsod", "clusus.gsod"};

        for (String name : fileNames) {
            createTestFileAt(Paths.get(basePathForCurrentTest, name).toString());
        }

        assertEquals(3, FileUtils.getFilesWithNamesMatchingDescriptionAt(basePathForCurrentTest, "*.txt").length);
        assertEquals(2, FileUtils.getFilesWithNamesMatchingDescriptionAt(basePathForCurrentTest, "*.gsod").length);
        assertEquals(1, FileUtils.getFilesWithNamesMatchingDescriptionAt(basePathForCurrentTest, "clusus.gsod").length);
        try {
            Files.delete(FileUtils.getFullPathFromHome(basePathForCurrentTest));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testForSuccessfulMove() {
        String sourcePath = "/test/Hello.txt";

        if (!FileUtils.exists(sourcePath)) {
            createTestFileAt(sourcePath);
        }
        assertNotNull(FileUtils.getFile(sourcePath));
        String destinationPath = "/test/new/";
        FileUtils.move(sourcePath, destinationPath);

        assertTrue(FileUtils.exists(Paths.get(destinationPath, Paths.get(sourcePath).getFileName().toString()).toString()));
    }

    private void createTestFileAt(String path) {
        try {
            String home = System.getProperty("user.home");
            Paths.get(home, path).getParent().toFile().mkdirs();
            File file = new File(Paths.get(home, path).toString());
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testForBehaviourOf() {
        String path = "/test/";
        createTestFileAt(Paths.get(path, "move_test.txt").toString());
        System.out.println(Arrays.toString(FileUtils.getFilesWithNamesMatchingDescriptionAt(path, "move_test.txt")));
    }
}