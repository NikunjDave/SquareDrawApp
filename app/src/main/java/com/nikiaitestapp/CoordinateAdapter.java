package com.nikiaitestapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.nikiaitestapp.custom.Square;

import java.util.List;

public class CoordinateAdapter extends RecyclerView.Adapter<CoordinateAdapter.ItemViewHolder> {

    private final String open = "(";
    private final String close = ")";
    private final String comma = ",";


    private List<Square> squareList;

    public CoordinateAdapter(List<Square> _squareList) {
        this.squareList = _squareList;
    }


    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_coords, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        Square square = squareList.get(position);

        float[] points = square.getSquarePoints();

        String strCords = "Square " + (position+1) + " coordinates : \n"
                + braces(points[0], points[1]).concat(comma)
                .concat(braces(points[2], points[3])).concat(comma)
                .concat(braces(points[4], points[5])).concat(comma)
                .concat(braces(points[6], points[7]));

        holder.tvCoords.setText(strCords);

    }

    @Override
    public int getItemCount() {
        return squareList.size();
    }

    private String braces(float p1, float p2) {
        return open + p1 + comma + p2 + close;
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {

        public TextView tvCoords;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCoords = itemView.findViewById(R.id.tvCoords);
        }
    }
}
