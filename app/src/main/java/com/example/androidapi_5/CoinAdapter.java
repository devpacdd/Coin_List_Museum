package com.example.androidapi_5;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

// esta clase se utiliza para crear la lista de monedas.

public class CoinAdapter extends RecyclerView.Adapter<CoinAdapter.MyViewHolder> {

    private Context mContext; //contexto
    private List<Coin> coinList; //lista monedas
    private OnClickListenerCoin mOnClickListenerCoin;

    //-----------------------Constructor--------------------------------//


    public CoinAdapter(Context mContext, List<Coin> coinList, OnClickListenerCoin onClickListenerCoin) {
        this.mContext = mContext;
        this.coinList = coinList;
        this.mOnClickListenerCoin = onClickListenerCoin;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        v = inflater.inflate(R.layout.coins_view,  parent, false);

        return new MyViewHolder(v, mOnClickListenerCoin);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.id.setText(coinList.get(position).getId());
        holder.mint.setText(coinList.get(position).getMint());
        holder.number.setText(coinList.get(position).getNumber());
        holder.material.setText(coinList.get(position).getMaterial());
        holder.denomination.setText(coinList.get(position).getDenomination());

        Glide.with(mContext).load(coinList.get(position).getImage_obverse()).into(holder.image_obverse);
        Glide.with(mContext).load(coinList.get(position).getImage_reverse()).into(holder.image_reverse);

    }

    @Override
    public int getItemCount() {
        return coinList.size();
    }

    public void refresh(List<Coin> monedasFiltradas) {
        coinList.clear();
        coinList.addAll(monedasFiltradas);
        notifyDataSetChanged();

    }


    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView id;
        TextView mint;
        TextView number;
        TextView material;
        TextView denomination;
        ImageView image_obverse;
        ImageView image_reverse;


        OnClickListenerCoin onClickListenerCoin;

        public MyViewHolder(@NonNull View itemView, OnClickListenerCoin onClickListenerCoin) {
            super(itemView);
            this.onClickListenerCoin = onClickListenerCoin;

            id = itemView.findViewById(R.id.textView_ID);
            mint = itemView.findViewById(R.id.textView_mint);
            number = itemView.findViewById(R.id.textView_number);
            material = itemView.findViewById(R.id.textView_material);
            denomination = itemView.findViewById(R.id.textView_denomination);
            image_obverse = itemView.findViewById(R.id.imageView_obverse);
            image_reverse = itemView.findViewById(R.id.imageView_reverse);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onClickListenerCoin.onClickCoin(getAdapterPosition());
        }
    }

    public interface OnClickListenerCoin{
        void onClickCoin(int position);
    }


}


