package fr.josselin.memo.memo;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import static android.support.v4.content.ContextCompat.startActivity;

/**
 * Created by jos_b on 22/02/2018.
 */

public class MainAdapter extends RecyclerView.Adapter<NameViewHolder> {

    private List<Memo> names = new ArrayList<>();
    private LayoutInflater lif;
    private Context context;
    private View itemview;

    public MainAdapter (Context context, LayoutInflater layoutinflater) {
        this.lif = layoutinflater;
        this.context = context;
    }
    @Override
    public NameViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        itemview = lif.inflate(R.layout.item_name, parent, false);

        return new NameViewHolder(context, itemview);
    }

    @Override
    public void onBindViewHolder(NameViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    @Override
    public int getItemCount() {
        return names.size();
    }

    public void add(Memo memo){
        names.add(memo);
        notifyDataSetChanged();
    }

    public void delete(String name) {
        names.remove(name);
        notifyDataSetChanged();
    }

    public Memo getItem(int position) {
        return names.get(position);
    }
}
