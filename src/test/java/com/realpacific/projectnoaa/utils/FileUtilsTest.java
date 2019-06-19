package com.realpacific.projectnoaa.utils;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import static org.junit.Assert.*;

public class FileUtilsTest {
    private static final String[] FILE_NAMES = new String[]{"Hello.txt", "World.txt", "12.txt", "tset.gsod", "clusus.gsod"};
    private static final String BASE_PATH_FOR_TEST = "/testForFilesList/";

    @BeforeClass
    public static void setup() {
        for (String name : FILE_NAMES) {
            createTestFileAt(Paths.get(BASE_PATH_FOR_TEST, name).toString());
        }

    }

    @Test
    public void test() {
        String home = System.getProperty("user.home");

        for (String fileName : FILE_NAMES) {
            assertTrue(FileUtils.exists(Paths.get(BASE_PATH_FOR_TEST, fileName).toString()));
        }

        assertFalse(Paths.get(home, "/Downloads/NOA/DOESNOTEXIST.txt").toFile().exists());

    }

    @Test
    public void testForExistenceOfFile() {
        assertTrue(FileUtils.exists("/Downloads"));
        assertFalse(FileUtils.exists("/elfefdsklfjl"));
    }


    @Test
    public void testForFilesList_ShouldSupportRegexSearchAndNameSearch() {
        assertEquals(3, FileUtils.getFilesWithNamesMatchingDescriptionAt(BASE_PATH_FOR_TEST, "*.txt").length);
        assertEquals(2, FileUtils.getFilesWithNamesMatchingDescriptionAt(BASE_PATH_FOR_TEST, "*.gsod").length);
        assertEquals(1, FileUtils.getFilesWithNamesMatchingDescriptionAt(BASE_PATH_FOR_TEST, "clusus.gsod").length);

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

    private static void createTestFileAt(String path) {
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


    @AfterClass
    public static void cleanup() {
        try {
            Path pathOfTemporaryFolder = FileUtils.getFullPathFromHome(BASE_PATH_FOR_TEST);
            for (String fileName : FILE_NAMES) {
                Files.deleteIfExists(Paths.get(FileUtils.getFullPathFromHome(BASE_PATH_FOR_TEST).toString(), fileName));
            }
            Files.deleteIfExists(pathOfTemporaryFolder);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}