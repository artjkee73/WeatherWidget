package command_e.androidintensive.itplace.simbirsoft.weatherwidget.realm.model;

import io.realm.RealmObject;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by artemqa on 02.10.2017.
 */

@NoArgsConstructor
@AllArgsConstructor
public class Day extends RealmObject {

    @Getter @Setter private int pictureId;
    @Getter @Setter private String dayOfWeek;
    @Getter @Setter private String date;
    @Getter @Setter private String temperature;
    @Getter @Setter private String weatherCharacter;


//    public int getPictureId() {
//        return pictureId;
//    }
//
//    public void setPictureId(int pictureId) {
//        this.pictureId = pictureId;
//    }
//
//    public String getDayOfWeek() {
//        return dayOfWeek;
//    }
//
//    public void setDayOfWeek(String dayOfWeek) {
//        this.dayOfWeek = dayOfWeek;
//    }
//
//    public String getDate() {
//        return date;
//    }
//
//    public void setDate(String date) {
//        this.date = date;
//    }
//
//    public String getTemperature() {
//        return temperature;
//    }
//
//    public void setTemperature(String temperature) {
//        this.temperature = temperature;
//    }
//
//    public String getWeatherCharacter() {
//        return weatherCharacter;
//    }
//
//    public void setWeatherCharacter(String weatherCharacter) {
//        this.weatherCharacter = weatherCharacter;
//    }
}
