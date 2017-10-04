package command_e.androidintensive.itplace.simbirsoft.weatherwidget;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.widget.ImageView;
import android.widget.TextView;

public class ItemView extends AppCompatActivity {
    ImageView iconWeather;
    TextView dayOfWeek;
    TextView date;
    TextView temperature;
    TextView weatherCharacter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_view);

        iconWeather = (ImageView) findViewById(R.id.icon_weather);
        dayOfWeek = (TextView)findViewById(R.id.day_of_week);
        date = (TextView)findViewById(R.id.date);
        temperature = (TextView)findViewById(R.id.temperature);
        weatherCharacter = (TextView)findViewById(R.id.weather_characteristic);
        
        Intent intent = getIntent();
        String dayOfWeekI = intent.getStringExtra("dayOfWeek");
        String dateI = intent.getStringExtra("date");
        String temperatureI = intent.getStringExtra("temperature");
        String weatherCharacterI = intent.getStringExtra("weatherCharacter");

        dayOfWeek.setText(dayOfWeekI);
        date.setText(dateI);
        temperature.setText(temperatureI);
        weatherCharacter.setText(weatherCharacterI);
    }
}
