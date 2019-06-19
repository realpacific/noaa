# Project NOAA

There are two branches in this project;
* enhancement-0 : contains reading Stations.txt and searching in-memory
* enhancement-1 : contains reading gsod & stations.txt and searching in database


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

 