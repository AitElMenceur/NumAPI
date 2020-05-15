package com.example.projetandroid.presentation.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.projetandroid.R;

import java.util.List;
import java.util.Random;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
    private List<String> values;
    private OnItemClickListener listener;
    private ImageView imageView;

    public interface OnItemClickListener {
        void onItemClick(String item);
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    class ViewHolder extends RecyclerView.ViewHolder {
        // Object qui reppresente un object visuel de la liste
        TextView txtHeader;
        TextView txtFooter;
        View layout;

        ViewHolder(View v) {
            super(v);
            layout = v;
            txtHeader = (TextView) v.findViewById(R.id.firstLine);
            imageView = v.findViewById(R.id.imageView);
            txtHeader.setText("lol");
        }
    }

    /*
     * Ajouter un element à la liste
     * */
    public void add(int position, String item) {
        values.add(position, item);

        notifyItemInserted(position);
    }

    /*
     * Supprimer un element à la liste
     * */
    public void remove(int position) {
        values.remove(position);
        notifyItemRemoved(position);
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public ListAdapter(List<String> myDataset, OnItemClickListener listener) {
        this.values = myDataset;
        this.listener = listener;
    }

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }
    // Create new views (invoked by the layout manager)
    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        // create a new view
        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());
        View v =
                inflater.inflate(R.layout.row_layout, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        Random random = new Random();
        imageView.setColorFilter(random.nextInt());
        return vh;
    }

    // Remplace les elements visible de la liste (Scrolling)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final String name = values.get(position);

        holder.txtHeader.setText(name);
        holder.txtHeader.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(name);
            }
        });

    }


    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return values.size();
    }

}