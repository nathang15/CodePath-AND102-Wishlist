package com.example.wishlist

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.wishlist.WishListAdapter.*

class WishListAdapter(wish: MainActivity, private val wishes: List<Wish>) : RecyclerView.Adapter<ViewHolder>(){
    class ViewHolder (itemView: View):  RecyclerView.ViewHolder (itemView){
        val tvName: TextView
        val tvPrice: TextView
        val tvLink: TextView

        init {
            tvName = itemView.findViewById(R.id.tvName)
            tvPrice = itemView.findViewById(R.id.tvPrice)
            tvLink = itemView.findViewById(R.id.tvLink)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val contactView = inflater.inflate(R.layout.item_wish,parent, false)
        return ViewHolder(contactView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val wish = wishes[position]
        holder.tvName.text = wish.name
        holder.tvPrice.text = wish.price
        holder.tvLink.text = wish.link
        holder.tvLink.setOnClickListener {
            try {
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(wish.link))
                ContextCompat.startActivity(it.context, browserIntent, null)
            } catch (e: ActivityNotFoundException) {
                Toast.makeText(it.context, "Invalid URL for " + wish.name, Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun getItemCount(): Int {
        return wishes.size
    }

}