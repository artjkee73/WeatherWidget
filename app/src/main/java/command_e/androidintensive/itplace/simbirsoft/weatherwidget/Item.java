package command_e.androidintensive.itplace.simbirsoft.weatherwidget;

import io.realm.RealmObject;

/**
 * Created by artemqa on 02.10.2017.
 */

public class Item extends RealmObject {

    private int pictureId;
    private String dayOfWeek;
    private String date;
    private String temperature;
    private String weatherCharacter;

    public Item(){

    }

    public int getPictureId() {
        return pictureId;
    }

    public void setPictureId(int pictureId) {
        this.pictureId = pictureId;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getWeatherCharacter() {
        return weatherCharacter;
    }

    public void setWeatherCharacter(String weatherCharacter) {
        this.weatherCharacter = weatherCharacter;
    }
}
