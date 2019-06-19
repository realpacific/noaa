package com.realpacific.projectnoaa.utils;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

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
    public void testForFilesList() {
        assertTrue(FileUtils.exists("/Downloads/NOA"));
        assertEquals(2, FileUtils.getFilesAt("/Downloads/NOA", "gsod").length);
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

}