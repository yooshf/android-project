package com.example.recyclevapp;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CustomeAdapter extends RecyclerView.Adapter<CustomeAdapter.ViewHolder> implements Filterable {

    private List<PokemonModel> dataSet;
    private List<PokemonModel> dataSetFull; // Full dataset to use for filtering
    private Context context;

    public CustomeAdapter(List<PokemonModel> dataSet, Context context) {
        this.dataSet = dataSet;
        this.dataSetFull = new ArrayList<>(dataSet);
        this.context = context;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView pokemonImage;
        private TextView pokemonName;
        private TextView pokemonType;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            pokemonImage = itemView.findViewById(R.id.pokemonImage);
            pokemonName = itemView.findViewById(R.id.pokemonName);
            pokemonType = itemView.findViewById(R.id.pokemonType);
        }

        void bind(PokemonModel pokemon) {
            pokemonImage.setImageResource(pokemon.getImageResId());
            pokemonName.setText(pokemon.getName());
            pokemonType.setText(pokemon.getType());
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pokemon, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(dataSet.get(position));

        // Set click listener for the item
        holder.itemView.setOnClickListener(v -> {
            // Show the dialog with Pokemon details
            showPokemonDetailsDialog(dataSet.get(position));
        });
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String filterPattern = constraint.toString().toLowerCase().trim();

                List<PokemonModel> filteredData = new ArrayList<>();
                for (PokemonModel item : dataSetFull) {
                    if (item.getName().toLowerCase().contains(filterPattern)) {
                        filteredData.add(item);
                    }
                }

                FilterResults results = new FilterResults();
                results.values = filteredData;
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                dataSet.clear();
                dataSet.addAll((List<PokemonModel>) results.values);
                notifyDataSetChanged();
            }
        };
    }

    // Method to show the dialog with Pokemon details
    private void showPokemonDetailsDialog(PokemonModel pokemon) {
        // Create the dialog
        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_pokemon_details);

        // Find views in the dialog
        ImageView dialogPokemonImage = dialog.findViewById(R.id.dialogPokemonImage);
        TextView dialogPokemonName = dialog.findViewById(R.id.dialogPokemonName);

        // Set Pokemon details in the dialog
        dialogPokemonImage.setImageResource(pokemon.getImageResId());
        dialogPokemonName.setText(pokemon.getName());

        // Show the dialog
        dialog.show();
    }

}
