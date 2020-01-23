package com.example.bar.controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bar.R;
import com.example.bar.model.Bar;

import java.util.ArrayList;
import java.util.List;

public class AdapterBares extends RecyclerView.Adapter<AdapterBares.BaresViewHolder>{

    private ArrayList<Bar> bares;
    private Context c;
    private Click click;



    public AdapterBares(Context c, Click click ) {

        this.bares = new ArrayList<Bar>();

        this.c = c;

        this.click = click;


    }

    @NonNull
    @Override
    public AdapterBares.BaresViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(
                c).inflate(R.layout.item_bar,
                parent, false);

        return new BaresViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterBares.BaresViewHolder holder, int position) {

        if (holder != null) {

            if (bares.size() > 0){
                holder.tv.setText(bares.get(position).getNome());
            }
        }
    }

    @Override
    public int getItemCount() {
        return bares.size();
    }

    private void animacao(View itemView) {


        /*
        boolean isNotFirstItem = i == -1;
        i++;
        itemView.setAlpha(0.f);
        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator animator = ObjectAnimator.ofFloat(itemView, "alpha", 0.f, 0.5f, 1.0f);
        ObjectAnimator.ofFloat(itemView, "alpha", 0.f).start();
        animator.setStartDelay(isNotFirstItem ? 150 / 2 : (i * 150 / 3));
        animator.setDuration(500);
        animatorSet.play(animator);
        animator.start();
         */
    }

    public ArrayList<Bar> getBares() {
        return bares;
    }

    public class BaresViewHolder extends RecyclerView.ViewHolder {

        private final TextView tv;

        public BaresViewHolder(@NonNull final View itemView) {
            super(itemView);

            this.tv = itemView.findViewById(R.id.nome);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    click.clicou(bares.get(getAdapterPosition()).getId());
                }
            });


        }

        public TextView getTv() {
            return tv;
        }
    }
}

