package com.realpacific.projectnoaa.entities;

import javax.persistence.*;

@Entity
@Table(name = "tbl_gsod")
public class Gsod {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String stationNumber;
    private String wban;
    private String date;
    private String temperature;
    private String dewPoint;
    private String seaLevelPressure;
    private String stationPressure;
    private String visibility;
    private String windSpeed;
    private String maxSpeed;
    private String gust;
    private String maxTemperature;
    private String minTemperature;
    private String precipitation;
    private String snowDepth;
    private String frshtt;

    protected Gsod() {
    }

    public Long getId() {
        return id;
    }

    public String getStationNumber() {
        return stationNumber;
    }

    public String getWban() {
        return wban;
    }

    public String getDate() {
        return date;
    }

    public String getTemperature() {
        return temperature;
    }

    public String getDewPoint() {
        return dewPoint;
    }

    public String getSeaLevelPressure() {
        return seaLevelPressure;
    }

    public String getStationPressure() {
        return stationPressure;
    }

    public String getVisibility() {
        return visibility;
    }

    public String getWindSpeed() {
        return windSpeed;
    }

    public String getMaxSpeed() {
        return maxSpeed;
    }

    public String getGust() {
        return gust;
    }

    public String getMaxTemperature() {
        return maxTemperature;
    }

    public String getMinTemperature() {
        return minTemperature;
    }

    public String getPrecipitation() {
        return precipitation;
    }

    public String getSnowDepth() {
        return snowDepth;
    }

    public String getFrshtt() {
        return frshtt;
    }

    public static class Builder {
        String stationNumber;
        String wban;
        String date;
        String temperature;
        String dewPoint;
        String seaLevelPressure;
        String stationPressure;
        String visibility;
        String windSpeed;
        String maxSpeed;
        String gust;
        String maxTemperature;
        String minTemperature;
        String precipitation;
        String snowDepth;
        String frshtt;

        public Builder() {
        }

        public Builder setStationNumber(String stationNumber) {
            this.stationNumber = stationNumber;
            return this;
        }

        public Builder setWban(String wban) {
            this.wban = wban;
            return this;
        }

        public Builder setDate(String date) {
            this.date = date;
            return this;
        }

        public Builder setTemperature(String temperature) {
            this.temperature = temperature;
            return this;
        }

        public Builder setDewPoint(String dewPoint) {
            this.dewPoint = dewPoint;
            return this;
        }

        public Builder setSeaLevelPressure(String seaLevelPressure) {
            this.seaLevelPressure = seaLevelPressure;
            return this;
        }

        public Builder setStationPressure(String stationPressure) {
            this.stationPressure = stationPressure;
            return this;
        }

        public Builder setVisibility(String visibility) {
            this.visibility = visibility;
            return this;
        }

        public Builder setWindSpeed(String windSpeed) {
            this.windSpeed = windSpeed;
            return this;
        }

        public Builder setMaxSpeed(String maxSpeed) {
            this.maxSpeed = maxSpeed;
            return this;
        }

        public Builder setGust(String gust) {
            this.gust = gust;
            return this;
        }

        public Builder setMaxTemperature(String maxTemperature) {
            this.maxTemperature = maxTemperature;
            return this;
        }

        public Builder setMinTemperature(String minTemperature) {
            this.minTemperature = minTemperature;
            return this;
        }

        public Builder setPrecipitation(String precipitation) {
            this.precipitation = precipitation;
            return this;
        }

        public Builder setSnowDepth(String snowDepth) {
            this.snowDepth = snowDepth;
            return this;
        }

        public Builder setFrshtt(String frshtt) {
            this.frshtt = frshtt;
            return this;
        }

        public Gsod build() {
            Gsod gsod = new Gsod();
            gsod.stationNumber = stationNumber;
            gsod.wban = wban;
            gsod.date = date;
            gsod.temperature = temperature;
            gsod.dewPoint = dewPoint;
            gsod.seaLevelPressure = seaLevelPressure;
            gsod.stationPressure = stationPressure;
            gsod.visibility = visibility;
            gsod.windSpeed = windSpeed;
            gsod.maxSpeed = maxSpeed;
            gsod.gust = gust;
            gsod.maxTemperature = maxTemperature;
            gsod.minTemperature = minTemperature;
            gsod.precipitation = precipitation;
            gsod.snowDepth = snowDepth;
            gsod.frshtt = frshtt;
            return gsod;
        }
    }

    @Override
    public String toString() {
        return "Gsod{" +
                "stationNumber='" + stationNumber + '\'' +
                ", wban='" + wban + '\'' +
                ", date='" + date + '\'' +
                ", temperature='" + temperature + '\'' +
                ", dewPoint='" + dewPoint + '\'' +
                ", seaLevelPressure='" + seaLevelPressure + '\'' +
                ", stationPressure='" + stationPressure + '\'' +
                ", visibility='" + visibility + '\'' +
                ", windSpeed='" + windSpeed + '\'' +
                ", maxSpeed='" + maxSpeed + '\'' +
                ", gust='" + gust + '\'' +
                ", maxTemperature='" + maxTemperature + '\'' +
                ", minTemperature='" + minTemperature + '\'' +
                ", precipitation='" + precipitation + '\'' +
                ", snowDepth='" + snowDepth + '\'' +
                ", frshtt='" + frshtt + '\'' +
                '}';
    }
}
