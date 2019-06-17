package com.realpacific.projectnoaa;

import com.realpacific.projectnoaa.entities.Gsod;
import com.realpacific.projectnoaa.entities.Record;
import com.realpacific.projectnoaa.runner.*;

public class Application {
    public static void main(String[] args) {
        System.out.println(System.getProperty("noaa.Main"));

      /*  ApplicationRunner runner = new ProjectNoaa();
        runner.run();*/

        Runner<Gsod> runner = new GsodRunner();
        runner.run();
/*
        Runner<Record> runner = new StationRunner();
        runner.run();*/
    }

}
