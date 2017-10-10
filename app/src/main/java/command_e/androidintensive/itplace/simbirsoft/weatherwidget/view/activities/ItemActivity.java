package command_e.androidintensive.itplace.simbirsoft.weatherwidget.view.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import command_e.androidintensive.itplace.simbirsoft.weatherwidget.R;
import command_e.androidintensive.itplace.simbirsoft.weatherwidget.realm.model.Day;
import io.realm.Realm;
import io.realm.RealmResults;

public class ItemActivity extends AppCompatActivity {
    private static final String LOG = "MyLog";

    ImageView iconWeather;
    TextView dayOfWeek;
    TextView date;
    TextView temperature;
    TextView weatherCharacter;
    Realm realm;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

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
        RealmResults<Day> results = realm.where(Day.class)
                .equalTo("date" , dateI).findAll();
        Day day = results.where()
                .equalTo("date" , dateI).findFirst();

        dayOfWeek.setText(day.getDayOfWeek());
        date.setText(day.getDate());
        temperature.setText(day.getTemperature());
        weatherCharacter.setText(day.getWeatherCharacter());
    }

}
