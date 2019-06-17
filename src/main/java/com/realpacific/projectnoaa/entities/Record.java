package com.realpacific.projectnoaa.entities;

import javax.persistence.*;

@Entity
@Table(name = "tbl_record")
public class Record {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String usafId;
    private String wban;
    private String stationName;
    private String country;
    private String state;
    private String icao;
    private String latitude;
    private String longitude;
    private String elevation;
    private String startDate;
    private String endDate;

    private Record() {
    }

    public Long getId() {
        return id;
    }

    public String getUsafId() {
        return usafId;
    }

    public String getWban() {
        return wban;
    }

    public String getStationName() {
        return stationName;
    }

    public String getCountry() {
        return country;
    }

    public String getState() {
        return state;
    }

    public String getIcao() {
        return icao;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getElevation() {
        return elevation;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public static class Builder {
        private String usafId;
        private String wban;
        private String stationName;
        private String country;
        private String state;
        private String icao;
        private String latitude;
        private String longitude;
        private String elevation;
        private String startDate;
        private String endDate;

        public Builder() {
        }

        public Builder setUsafId(String usafId) {
            this.usafId = usafId;
            return this;
        }

        public Builder setWban(String wban) {
            this.wban = wban;
            return this;
        }

        public Builder setStationName(String stationName) {
            this.stationName = stationName;
            return this;
        }

        public Builder setCountry(String country) {
            this.country = country;
            return this;
        }

        public Builder setState(String state) {
            this.state = state;
            return this;
        }

        public Builder setIcao(String icao) {
            this.icao = icao;
            return this;
        }

        public Builder setLatitude(String latitude) {
            this.latitude = latitude;
            return this;
        }

        public Builder setLongitude(String longitude) {
            this.longitude = longitude;
            return this;
        }

        public Builder setElevation(String elevation) {
            this.elevation = elevation;
            return this;
        }

        public Builder setStartDate(String startDate) {
            this.startDate = startDate;
            return this;
        }

        public Builder setEndDate(String endDate) {
            this.endDate = endDate;
            return this;
        }

        public Record build() {
            Record record = new Record();
            record.usafId = this.usafId;
            record.wban = this.wban;
            record.country = this.country;
            record.state = this.state;
            record.icao = this.icao;
            record.latitude = this.latitude;
            record.longitude = this.longitude;
            record.elevation = this.elevation;
            record.startDate = this.startDate;
            record.endDate = this.endDate;
            record.stationName = this.stationName;
            return record;
        }
    }


    @Override
    public String toString() {
        return "Record{" +
                "usafId='" + usafId + '\'' +
                ", wban='" + wban + '\'' +
                ", stationName='" + stationName + '\'' +
                ", country='" + country + '\'' +
                ", state='" + state + '\'' +
                ", icao='" + icao + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                ", elevation='" + elevation + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                '}';
    }
}
