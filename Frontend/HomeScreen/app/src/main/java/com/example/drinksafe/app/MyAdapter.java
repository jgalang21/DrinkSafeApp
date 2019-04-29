package com.example.drinksafe.app;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.drinksafe.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Vector;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private Vector<JSONObject> mDataset;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView drink_name;
        public TextView drink_percent;
        public MyViewHolder(View v) {
            super(v);
            drink_name = v.findViewById(R.id.ds_list_name);
            drink_percent = v.findViewById(R.id.ds_list_alc);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(Vector<JSONObject> myDataset) {
        mDataset = new Vector<>();
        for(int i = 0; i < myDataset.size(); i++) {
            mDataset.add(myDataset.get(i));
        }
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.drink_info, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        try {
            JSONObject drink = mDataset.get(position);
            holder.drink_name.setText(drink.getString("drinkid"));
            holder.drink_percent.setText(drink.getString("alcpercent"));
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}