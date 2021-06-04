package com.example.news

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide



class Newslistadapter(private val listner:NewsItemClicked): RecyclerView.Adapter<newsViewHolder>() {
   private val items: ArrayList<News> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): newsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false)
        val viewHolder = newsViewHolder(view)
        view.setOnClickListener{
            listner.onItemClicked(items[viewHolder.getAbsoluteAdapterPosition()])
        }


        return viewHolder
    }

    override fun getItemCount(): Int {
        return items.size
    }
    override fun onBindViewHolder(holder: newsViewHolder, position: Int) {

        val currentItem = items[position]
        holder.titleView.text = currentItem.title
        holder.author.text = currentItem.author
        Glide.with(holder.itemView.context).load(currentItem.imageUrl).into(holder.image)
    }

   fun updateNews(updatedNews: ArrayList<News>){
       items.clear()
       items.addAll(updatedNews)

       notifyDataSetChanged()
   }

}
class newsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val titleView: TextView = itemView.findViewById(R.id.title)
    val image: ImageView = itemView.findViewById(R.id.image)
    val author: TextView = itemView.findViewById(R.id.author)
}

interface NewsItemClicked {
    fun onItemClicked(item: News)

}

