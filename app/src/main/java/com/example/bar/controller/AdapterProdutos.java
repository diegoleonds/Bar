package com.example.bar.controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bar.R;
import com.example.bar.model.Model;
import com.example.bar.model.Produto;

import java.util.ArrayList;

public class AdapterProdutos extends RecyclerView.Adapter<AdapterProdutos.ProdutosViewHolder>{

    private ArrayList<Produto> produtos;
    private Context context;
    private String fkBar;

    public AdapterProdutos(Context context, String fkBar){

        this.context = context;

        produtos = new ArrayList<Produto>();
        this.fkBar = fkBar;
    }

    @NonNull
    @Override
    public ProdutosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(
                context).inflate(R.layout.item_produto,
                parent, false);

        return new ProdutosViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProdutosViewHolder holder, int position) {

        Produto aux = produtos.get(position);

        holder.nome.setText(aux.getNome());

        holder.preco.setText(Model.getValorFormatado(context, fkBar,
                String.valueOf(aux.getId())));

        holder.foto.setImageResource(aux.getIdImagem());
    }

    ArrayList<Produto> getProdutos() { return this.produtos; }

    @Override
    public int getItemCount() {
        return produtos.size();
    }

    public void setProdutos(){ }

    public class ProdutosViewHolder extends RecyclerView.ViewHolder {

        private final TextView nome;
        private final TextView preco;
        private final ImageView foto;

        public ProdutosViewHolder(@NonNull final View itemView) {
            super(itemView);

            this.nome = itemView.findViewById(R.id.nome_item_produto);
            this.preco = itemView.findViewById(R.id.foto_produto);
            this.foto = itemView.findViewById(R.id.preco_produto);
        }

        public TextView getNome() {
            return nome;
        }

        public TextView getPreco() {
            return preco;
        }

        public ImageView getFoto() {
            return foto;
        }

    }
}
