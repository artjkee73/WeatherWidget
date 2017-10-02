package command_e.androidintensive.itplace.simbirsoft.weatherwidget;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by artemqa on 02.10.2017.
 */

public class Item {


    private int pictureId;
    private String dayOfWeek;
    private String date;
    private String temperature;
    private String weatherCharacter;

    Item(int pictureId,String dayOfWeek, String date, String temperature,String weatherCharacter){
        this.pictureId = pictureId;
        this.dayOfWeek = dayOfWeek;
        this.date = date;
        this.temperature = temperature;
        this.weatherCharacter = weatherCharacter;
    }

    public int getPictureId() {
        return pictureId;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public String getDate() {
        return date;
    }

    public String getTemperature() {
        return temperature;
    }

    public String getWeatherCharacter() {
        return weatherCharacter;
    }

    public static List<Item> getFakeItems(){
        ArrayList<Item> itemsList = new ArrayList<>();
        for(int i= 1;i<11;i++){

            itemsList.add(new Item(R.drawable.sunny,"Monday",i + ".01.2017","+20","Sunny"));

        }
        return itemsList;
    }

}
