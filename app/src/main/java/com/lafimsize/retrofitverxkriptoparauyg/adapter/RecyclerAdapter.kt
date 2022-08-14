package com.lafimsize.retrofitverxkriptoparauyg.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lafimsize.retrofitverxkriptoparauyg.R
import com.lafimsize.retrofitverxkriptoparauyg.databinding.RecyclerRowBinding
import com.lafimsize.retrofitverxkriptoparauyg.model.CryptoModel

private lateinit var binding: RecyclerRowBinding

class RecyclerAdapter(val cryptoModel:List<CryptoModel>,private val listener:Listener):RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    interface Listener{
        fun onItemClick(cryptoModel: CryptoModel)
    }

    private val colors: Array<String> = arrayOf("#13bd27","#29c1e1","#b129e1","#d3df13","#f6bd0c","#a1fb93","#0d9de3","#ffe48f")

    class ViewHolder(val binding: RecyclerRowBinding):RecyclerView.ViewHolder(binding.root){

        fun setBackgroundColor(colors:Array<String>,position: Int){
            binding.recyclerRow.setBackgroundColor(Color.parseColor(colors.get(position%8)))
        }

        fun onClick(cryptoModel: CryptoModel, listener: Listener){
            listener.onItemClick(cryptoModel)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding=RecyclerRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.currency.text=cryptoModel.get(position).currency
        holder.binding.price.text=cryptoModel.get(position).price
        holder.setBackgroundColor(colors,position)

        holder.binding.recyclerRow.setOnClickListener {
            holder.onClick(cryptoModel.get(position),listener)
        }
    }

    override fun getItemCount(): Int {
        return cryptoModel.size
    }
}