package com.realpacific.projectnoaa.utils;

import org.junit.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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

}