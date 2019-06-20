package com.realpacific.projectnoaa;

import com.realpacific.projectnoaa.config.ConfigurationManager;
import com.realpacific.projectnoaa.config.ConfigurationUtils;
import com.realpacific.projectnoaa.config.NoaaConfiguration;
import com.realpacific.projectnoaa.entities.Gsod;
import com.realpacific.projectnoaa.entities.Station;
import com.realpacific.projectnoaa.runner.GsodRunner;
import com.realpacific.projectnoaa.runner.Runner;
import com.realpacific.projectnoaa.runner.StationInMemoryRunner;
import com.realpacific.projectnoaa.runner.StationRunner;

public class Application {
    private static final String ARGUMENT_STATIONS = "stations";
    private static final String ARGUMENT_GSOD = "gsod";
    private static final String ARGUMENT_INMEMORY = "inmemory";
    private static ConfigurationManager configurationManager = ConfigurationUtils.getConfigurationManager();
    private static String workingDirectory;
    private static String archiveDirectory;
    private static Runner<Station> stationRunner;
    private static Runner<Gsod> gsodRunner;

    public static void main(String[] args) {
        NoaaConfiguration configuration = configurationManager.loadPropertyFile();
        loadConfigurationForDirectories(configuration);
        System.out.format("Working directory= %s\nArchive Directory:%s\n", workingDirectory, archiveDirectory);

        stationRunner = new StationRunner(workingDirectory, archiveDirectory);
        gsodRunner = new GsodRunner(workingDirectory, archiveDirectory);

        String option = getValueFromCommandLineArgument();
        launchRunner(option);
    }

    private static void loadConfigurationForDirectories(NoaaConfiguration configuration) {
        if (configuration != null) {
            configuration.get(NoaaConfiguration.CONFIGURATION_WORKING_DIR)
                    .ifPresent(config -> workingDirectory = config.toString());
            configuration.get(NoaaConfiguration.CONFIGURATION_ARCHIVE_DIR)
                    .ifPresent(config -> archiveDirectory = config.toString());
        }
    }

    private static String getValueFromCommandLineArgument() {
        return System.getProperty(NoaaConfiguration.CONFIGURATION_LAUNCH_OPTION);
    }

    private static void launchRunner(String option) {
        switch (option) {
            case ARGUMENT_STATIONS:
                System.out.println("Launching STATIONS menu");
                stationRunner.run();
                break;
            case ARGUMENT_GSOD:
                System.out.println("Launching GSOD menu");
                gsodRunner.run();
                break;
            case ARGUMENT_INMEMORY:
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
