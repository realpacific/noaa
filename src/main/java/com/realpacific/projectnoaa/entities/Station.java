package com.realpacific.projectnoaa.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tbl_station")
public class Station {
    @Id
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

    @OneToMany(mappedBy = "station", fetch = FetchType.EAGER)
    private List<Gsod> gsods = new ArrayList<>();

    private Station() {
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

    public List<Gsod> getGsods() {
        return gsods;
    }

    public void setGsods(List<Gsod> gsods) {
        this.gsods = gsods;
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

        public Station build() {
            Station station = new Station();
            station.usafId = this.usafId;
            station.wban = this.wban;
            station.country = this.country;
            station.state = this.state;
            station.icao = this.icao;
            station.latitude = this.latitude;
            station.longitude = this.longitude;
            station.elevation = this.elevation;
            station.startDate = this.startDate;
            station.endDate = this.endDate;
            station.stationName = this.stationName;
            return station;
        }
    }


    @Override
    public String toString() {
        return "Station{" +
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
