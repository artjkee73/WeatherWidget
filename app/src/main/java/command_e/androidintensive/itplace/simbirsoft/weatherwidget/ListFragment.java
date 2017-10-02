package command_e.androidintensive.itplace.simbirsoft.weatherwidget;

/**
 * Created by artemqa on 03.10.2017.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ListFragment extends Fragment {

    RecyclerView recyclerView ;
    MyAdapter recyclerViewAdapter;
    LinearLayoutManager recyclerViewLayoutManager;

    public ListFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ViewGroup viewGroup = (ViewGroup)inflater.inflate(R.layout.recycler_view, container, false);
        recyclerView = (RecyclerView) viewGroup.findViewById(R.id.recyclerView);

        recyclerViewLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(recyclerViewLayoutManager);

        recyclerViewAdapter = new MyAdapter(getContext(), Item.getFakeItems());
        recyclerView.setAdapter(recyclerViewAdapter);

        return viewGroup;
    }
}

