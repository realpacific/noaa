package com.realpacific.projectnoaa;

import com.realpacific.projectnoaa.config.ConfigurationManager;
import com.realpacific.projectnoaa.config.ConfigurationUtils;
import com.realpacific.projectnoaa.config.NoaaConfiguration;
import com.realpacific.projectnoaa.entities.Gsod;
import com.realpacific.projectnoaa.entities.Station;
import com.realpacific.projectnoaa.exceptions.InvalidConfigurationException;
import com.realpacific.projectnoaa.runner.GsodRunner;
import com.realpacific.projectnoaa.runner.Runner;
import com.realpacific.projectnoaa.runner.StationInMemoryRunner;
import com.realpacific.projectnoaa.runner.StationRunner;

public class Application {
    private static ConfigurationManager configurationManager = ConfigurationUtils.getConfigurationManager();
    private static String workingDirectory;
    private static String archiveDirectory;

    public static void main(String[] args) {
        NoaaConfiguration configuration = configurationManager.loadPropertyFile();
        if (configuration != null) {
            configuration.get(NoaaConfiguration.CONFIGURATION_WORKING_DIR)
                    .ifPresent(config -> workingDirectory = config.toString());
            configuration.get(NoaaConfiguration.CONFIGURATION_ARCHIVE_DIR)
                    .ifPresent(config -> archiveDirectory = config.toString());
        }
        System.out.format("Working directory= %s\nArchive Directory:%s\n", workingDirectory, archiveDirectory);

        Runner<Station> stationRunner = new StationRunner(workingDirectory, archiveDirectory);
        Runner<Gsod> gsodRunner = new GsodRunner(workingDirectory, archiveDirectory);

        String option = System.getProperty(NoaaConfiguration.CONFIGURATION_LAUNCH_OPTION);
        switch (option) {
            case "stations":
                System.out.println("Launching STATIONS menu");
                stationRunner.run();
                break;
            case "gsod":
                System.out.println("Launching GSOD menu");
                gsodRunner.run();
                break;
            case "inmemory":
                System.out.println("Launching In memory Stations menu");
                Runner<Station> inMemoryRunner = new StationInMemoryRunner();
                inMemoryRunner.run();
                break;
            default:
                System.out.println("Unknown option detected!");
                break;
        }
    }

}
