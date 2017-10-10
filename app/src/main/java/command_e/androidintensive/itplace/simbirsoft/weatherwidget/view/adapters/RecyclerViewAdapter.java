package command_e.androidintensive.itplace.simbirsoft.weatherwidget.view.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import command_e.androidintensive.itplace.simbirsoft.weatherwidget.R;
import command_e.androidintensive.itplace.simbirsoft.weatherwidget.realm.model.Day;
import command_e.androidintensive.itplace.simbirsoft.weatherwidget.view.activities.ItemActivity;
import io.realm.RealmResults;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ItemViewHolder> {

    private static final String LOG = "MyLog";

    private LayoutInflater inflater;
    private RealmResults<Day> results;

   public RecyclerViewAdapter(Context context, RealmResults<Day> results){
        this.inflater = LayoutInflater.from(context);
        this.results = results;
    }

    public void setData(RealmResults<Day> dayList){
        Log.d(LOG,"данные пришли в RecyclerViewAdapter");
        this.results = dayList;
        notifyDataSetChanged();
        Log.d(LOG,"После добавления данных список обновлён");
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.recycler_view_item,parent,false);

        return new ItemViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ItemViewHolder itemViewHolder, int position) {
        itemViewHolder.iconWeather.setImageResource(results.get(position).getPictureId());
        itemViewHolder.dayOfWeek.setText(results.get(position).getDayOfWeek());
        itemViewHolder.date.setText(results.get(position).getDate());
        itemViewHolder.temperature.setText(results.get(position).getTemperature());
        itemViewHolder.weatherCharacter.setText(results.get(position).getWeatherCharacter());


    }

    @Override
    public int getItemCount() {
        return results.size();
    }


    public static class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private static final String LOG = "MyLog";

        CardView cardView;
        ImageView iconWeather;
        TextView dayOfWeek;
        TextView date;
        TextView temperature;
        TextView weatherCharacter;


        ItemViewHolder(View view){
            super(view);

            iconWeather = (ImageView) view.findViewById(R.id.icon_weather);
            dayOfWeek = (TextView)view.findViewById(R.id.day_of_week);
            date = (TextView)view.findViewById(R.id.date);
            temperature = (TextView)view.findViewById(R.id.temperature);
            weatherCharacter = (TextView)view.findViewById(R.id.weather_characteristic);
            cardView = (CardView) itemView.findViewById(R.id.item_card_view);
            cardView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.item_card_view :
                    Bundle extras = new Bundle();
                    extras.putString("date", date.getText().toString()); //передача в Intent даты для последующей выборки и БД
                    Intent intent = new Intent(v.getContext(), ItemActivity.class);
                    intent.putExtras(extras);
                    v.getContext().startActivity(intent);
            }
        }
    }
}
