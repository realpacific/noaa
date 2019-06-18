package com.realpacific.projectnoaa;

import com.realpacific.projectnoaa.entities.Gsod;
import com.realpacific.projectnoaa.runner.GsodRunner;
import com.realpacific.projectnoaa.runner.Runner;

public class Application {
    public static void main(String[] args) {
        System.out.println(System.getProperty("noaa.Main"));

      /*  ApplicationRunner runner = new ProjectNoaa();
        runner.run();*/

        Runner<Gsod> runner = new GsodRunner();
        runner.run();

       /* Runner<Record> runner2 = new StationRunner();
        runner2.run();*/
    }

}
