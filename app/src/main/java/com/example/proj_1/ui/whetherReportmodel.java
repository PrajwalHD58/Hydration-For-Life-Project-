package com.example.proj_1.ui;

public class whetherReportmodel {
//    "id":6438381849411584,
    private  String weather_state_name;
    private  String weather_abbr;
    private float min_temp;
    private float max_temp;
    private float the_temp;
    private float wind_speed;
    private float predictability;
    private float humidity;


    public whetherReportmodel() {

    }




    public String getWeather_state_name() {
        return weather_state_name;
    }

    public void setWeather_state_name(String weather_state_name) {
        this.weather_state_name = weather_state_name;
    }

   public float getWind_speed() {
        return wind_speed;
    }

    public void setWind_speed(float wind_speed) {
        this.wind_speed = wind_speed;
    }

    public String getWeather_abbr() {
        return weather_abbr;
    }

    public void setWeather_abbr(String weather_state_name) {
        this.weather_abbr = weather_state_name;
    }

    public float getMin_temp() {
        return min_temp;
    }

    public void setMin_temp(float min_temp) {
        this.min_temp = min_temp;
    }

    public float getMax_temp() {
        return max_temp;
    }

    public void setMax_temp(Float max_temp) {
        this.max_temp = max_temp;
    }

    public float getThe_temp() {
        return the_temp;
    }

    public void setThe_temp(float the_temp) {
        this.the_temp = the_temp;
    }

    public float getHumidity() {
        return humidity;
    }

    public void setHumidity(float humidity) {
        this.humidity = humidity;
    }

    public float getPredictability() {
        return predictability;
    }

    public void setPredictability(float predictability) {
        this.predictability = predictability;
    }


}
