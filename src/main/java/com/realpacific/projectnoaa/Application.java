package com.realpacific.projectnoaa;

import com.realpacific.projectnoaa.runner.ApplicationRunner;
import com.realpacific.projectnoaa.runner.ProjectNoaa;

public class Application {
    public static void main(String[] args) {
        System.out.println(System.getProperty("noaa.Main"));

        ApplicationRunner runner = new ProjectNoaa();
        runner.run();
    }

}
