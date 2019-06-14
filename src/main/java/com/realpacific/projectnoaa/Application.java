package com.realpacific.projectnoaa;

import com.realpacific.projectnoaa.runner.ApplicationRunner;
import com.realpacific.projectnoaa.runner.ProjectNoaa;

public class Application {
    public static void main(String[] args) {
        ApplicationRunner runner = new ProjectNoaa();
        runner.run();
    }

}
