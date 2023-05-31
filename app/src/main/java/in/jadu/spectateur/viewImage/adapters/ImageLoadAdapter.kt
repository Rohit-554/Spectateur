package `in`.jadu.spectateur.viewImage.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import `in`.jadu.spectateur.R
import `in`.jadu.spectateur.viewImage.models.remote.dtos.ImageItem

class ImageLoadAdapter: androidx.recyclerview.widget.RecyclerView.Adapter<ImageLoadAdapter.ImageLoadViewHolder>() {
    var itemList:List<ImageItem> = emptyList()
    class ImageLoadViewHolder(itemView:View):RecyclerView.ViewHolder(itemView) {
        val image:ImageView = itemView.findViewById(R.id.image_item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageLoadViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.imageitemlayout,parent,false)
        return ImageLoadViewHolder(view)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: ImageLoadViewHolder, position: Int) {
        val currentItem = itemList[position]
        Glide.with(holder.itemView.context).load(currentItem.url).into(holder.image)
    }

}