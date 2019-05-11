package com.hairul.myrecyclerview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class CardViewPresidentAdapter extends RecyclerView.Adapter<CardViewPresidentAdapter.CardViewviewHolder> {
    private Context context;
    private ArrayList<President> listPresident;
    private ArrayList<President> getListPresident() {
        return listPresident;
    }

    public void setListPresident(ArrayList<President> listPresident) {
        this.listPresident = listPresident;
    }
    public CardViewPresidentAdapter(Context context) {
        this.context = context;
    }
    @NonNull
    @Override
    public CardViewviewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_cardview_president,viewGroup, false);
        return new CardViewviewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewviewHolder cardViewviewHolder, int i) {
        President p =getListPresident().get(i);

        Glide.with(context)
                .load(p.getPhoto())
                .apply(new RequestOptions().override(350, 550))
                .into(cardViewviewHolder.imgPhoto);
        cardViewviewHolder.tvName.setText(p.getName());
        cardViewviewHolder.tvRemarks.setText(p.getRemarks());
        cardViewviewHolder.btnFavorite.setOnClickListener(new CustomOnItemClickListener(i, new CustomOnItemClickListener.OnItemClickCallback() {

            @Override
            public void onItemClicked(View view, int position) {
                Toast.makeText(context, "Presiden Favoriteku " + getListPresident().get(position).getName(), Toast.LENGTH_SHORT).show();;
            }
        }));

        cardViewviewHolder.btnShare.setOnClickListener((new CustomOnItemClickListener(i, new CustomOnItemClickListener.OnItemClickCallback() {
            @Override
            public void onItemClicked(View view, int position) {
                Toast.makeText(context, "Shared Presiden " + getListPresident().get(position).getName(), Toast.LENGTH_SHORT).show();
            }
        })));
    }


    @Override
    public int getItemCount() {
        return getListPresident().size();
    }

    public class CardViewviewHolder extends RecyclerView.ViewHolder {
        ImageView imgPhoto;
        TextView tvName,tvRemarks;
        Button btnFavorite,btnShare;
        public CardViewviewHolder(@NonNull View itemView) {
            super(itemView);
            imgPhoto = itemView.findViewById(R.id.img_item_photo);
            tvName = itemView.findViewById(R.id.tv_item_name);
            tvRemarks = itemView.findViewById(R.id.tv_item_remarks);
            btnFavorite = itemView.findViewById(R.id.btn_set_favorite);
            btnShare = itemView.findViewById(R.id.btn_set_share);
        }
    }
}
