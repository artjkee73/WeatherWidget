package command_e.androidintensive.itplace.simbirsoft.weatherwidget;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.widget.ImageView;
import android.widget.TextView;

import io.realm.Realm;
import io.realm.RealmResults;

public class ItemView extends AppCompatActivity {
    ImageView iconWeather;
    TextView dayOfWeek;
    TextView date;
    TextView temperature;
    TextView weatherCharacter;
    Realm realm;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_view);

        iconWeather = (ImageView) findViewById(R.id.icon_weather);
        dayOfWeek = (TextView)findViewById(R.id.day_of_week);
        date = (TextView)findViewById(R.id.date);
        temperature = (TextView)findViewById(R.id.temperature);
        weatherCharacter = (TextView)findViewById(R.id.weather_characteristic);
        bindingDataFromDB(); //  приём даты и Bundle и выборка из бд по ней

    }

    public void bindingDataFromDB(){
        Intent intent = getIntent();
        String dateI = intent.getStringExtra("date");
        realm = Realm.getDefaultInstance();
        RealmResults<Item> results = realm.where(Item.class)
                .equalTo("date" , dateI).findAll();
        Item dayItem = results.where()
                .equalTo("date" , dateI).findFirst();

        dayOfWeek.setText(dayItem.getDayOfWeek());
        date.setText(dayItem.getDate());
        temperature.setText(dayItem.getTemperature());
        weatherCharacter.setText(dayItem.getWeatherCharacter());
    }

}
