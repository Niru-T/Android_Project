package com.example.myfirstapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
    private List<Country> values;
    private Context context;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    class ViewHolder extends RecyclerView.ViewHolder {

        // each data item is just a string in this case
        TextView country;
        ImageView flagView;
        View layout;

        ViewHolder(View v) {
            super(v);
            layout = v;
            country = (TextView) v.findViewById(R.id.country);
            flagView = (ImageView) v.findViewById(R.id.icon);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public ListAdapter(List<Country> myDataset,Context activity) {
        values = myDataset;
        context = activity;

    }

    // Create new views (invoked by the layout manager)
    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.row_layout, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        final Country countryValues = values.get(position);
        holder.country.setText(countryValues.getName());
        Utils.fetchSvg(context, countryValues.getFlag(), holder.flagView);
        holder.country.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent descriptionPage = new Intent(context,CountryDescription.class);
                descriptionPage.putExtra("capital",countryValues.getCapital());
                descriptionPage.putExtra("region",countryValues.getRegion());
                descriptionPage.putExtra("population",countryValues.getPopulation());
                descriptionPage.putExtra("alpha3Code",countryValues.getAlpha3Code());
                context.startActivity(descriptionPage);
            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return values.size();
    }

}