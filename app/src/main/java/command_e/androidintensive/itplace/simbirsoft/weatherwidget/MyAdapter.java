package command_e.androidintensive.itplace.simbirsoft.weatherwidget;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ItemViewHolder> {

    private LayoutInflater inflater;
    private List<Item> listItems;

    MyAdapter(Context context, List<Item> listItems){
        this.inflater = LayoutInflater.from(context);
        this.listItems = listItems;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.recycler_view_item,parent,false);

        ItemViewHolder itemViewHolder = new ItemViewHolder(view);
        return itemViewHolder;
    }

    @Override
    public void onBindViewHolder(ItemViewHolder itemViewHolder, int position) {
        itemViewHolder.iconWeather.setImageResource(listItems.get(position).getPictureId());
        itemViewHolder.dayOfWeek.setText(listItems.get(position).getDayOfWeek());
        itemViewHolder.date.setText(listItems.get(position).getDate());
        itemViewHolder.temperature.setText(listItems.get(position).getTemperature());
        itemViewHolder.weatherCharacter.setText(listItems.get(position).getWeatherCharacter());

    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }


    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }


    public static class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

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
            Bundle extras = new Bundle();
            extras.putString("dayOfWeek", dayOfWeek.getText().toString());
            extras.putString("date", date.getText().toString());
            extras.putString("temperature", temperature.getText().toString());
            extras.putString("weatherCharacter",weatherCharacter.getText().toString());
            Intent intent = new Intent(v.getContext(), ItemView.class);
            intent.putExtras(extras);
            v.getContext().startActivity(intent);
        }
    }
}