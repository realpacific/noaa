# Project NOAA

There are two branches in this project;
* enhancement-0 : contains reading Stations.txt and searching in-memory
* enhancement-1 / master : contains reading gsod & stations.txt and searching in database

### TO START
* Stations from In memory: -Dnoaa.Main=inmemory
* Stations from mysql db: -Dnoaa.Main=stations
* Gsod from mysql db: -Dnoaa.Main=gsod


### Search inputs for STATION:
* NAME: CWOS
* Country code: AF
* ID Range: 007047; 007094
* Geography : 68.304; -133.483

### Search inputs for GSOD:
* ID AND DATE: 690150; 20170901
* COUNTRY AND DATE: US; 20170901
* STATION NAME AND DATE: TWENTY NINE PALMS; 20170901


## Structure of `/resources` directory
* `config.properties` file contains the configuration properties like what columns to display, db connection, etc.
* `hibernate.cgf.xml` contains the default hibernate connection properties & will be overridden by values in `config.properties` at runtime if present;

 ### NOTES:
 * Some of the test cases I have written here, I've used it as a`learning test`
 * I've included the NOAA files in the /noaa-files folder of project root's directory. Please configure `config.properties` to point to the location of these files.
 * Default db url is `jdbc:mysql://localhost:3306/records_db` at database name **records_db**
 * Default hbm2dll property is set to `<property name="hbm2ddl.auto">create</property>` at `hibernate.cfg.xml`