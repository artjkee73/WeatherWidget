package command_e.androidintensive.itplace.simbirsoft.weatherwidget.view.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import command_e.androidintensive.itplace.simbirsoft.weatherwidget.R;
import command_e.androidintensive.itplace.simbirsoft.weatherwidget.model.Model;
import command_e.androidintensive.itplace.simbirsoft.weatherwidget.presenter.ItemActivityPresenter;
import command_e.androidintensive.itplace.simbirsoft.weatherwidget.presenter.MainActivityPresenter;
import command_e.androidintensive.itplace.simbirsoft.weatherwidget.realm.model.Day;
import command_e.androidintensive.itplace.simbirsoft.weatherwidget.view.activities.interfaces.ItemActivityView;
import io.realm.Realm;
import io.realm.RealmResults;

public class ItemActivity extends AppCompatActivity implements ItemActivityView {
    private static final String LOG = "MyLog";

    @BindView (R.id.icon_weather) ImageView iconWeather;
    @BindView (R.id.day_of_week) TextView dayOfWeek;
    @BindView (R.id.date) TextView date;
    @BindView (R.id.temperature) TextView temperature;
    @BindView (R.id.weather_characteristic) TextView weatherCharacter;

    ItemActivityPresenter presenter;
    Realm realm;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);
        ButterKnife.bind(this);

        realm = Realm.getDefaultInstance();
        Model model = new Model(realm);
        presenter = new ItemActivityPresenter(model);
        presenter.attachView(this);
        bindingDataFromDB(); //  приём даты и Bundle и выборка из бд по ней

    }

    public void bindingDataFromDB(){
        Intent intent = getIntent();
        String dateI = intent.getStringExtra("date");
        presenter.loadDay(dateI);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
        if (!realm.isClosed()) {
            realm.close();
        }
    }

    @Override
    public void showClickedDay(Day day) {
        dayOfWeek.setText(day.getDayOfWeek());
        date.setText(day.getDate());
        temperature.setText(day.getTemperature());
        weatherCharacter.setText(day.getWeatherCharacter());
    }
}
