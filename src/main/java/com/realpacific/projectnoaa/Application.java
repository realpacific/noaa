package com.realpacific.projectnoaa;

import com.realpacific.projectnoaa.entities.Gsod;
import com.realpacific.projectnoaa.entities.Station;
import com.realpacific.projectnoaa.runner.GsodRunner;
import com.realpacific.projectnoaa.runner.Runner;
import com.realpacific.projectnoaa.runner.StationRunner;

public class Application {
    public static void main(String[] args) {
        System.out.println(System.getProperty("noaa.Main"));

        Runner<Gsod> runner = new GsodRunner();
        runner.run();

       /* Runner<Station> runner2 = new StationRunner();
        runner2.run();*/
    }

}
