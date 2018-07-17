package xyz.megundo.aura.ui

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import xyz.megundo.aura.R
import xyz.megundo.aura.data.ApiResults

/*
based on the default Adapter provided on Android documentation
 */
class AnimeAdapter(private val myDataset: List<ApiResults>) :
        RecyclerView.Adapter<AnimeAdapter.ViewHolder>() {


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var animeName: TextView = itemView.findViewById<TextView>(R.id.tv_anime_name)
        var animeEpisode: TextView = itemView.findViewById<TextView>(R.id.tv_episode_number)
        var animeImage: ImageView = itemView.findViewById<ImageView>(R.id.iv_avatar)
        fun bind(item: ApiResults) = with(itemView) {
            animeName.text = item.name
            animeEpisode.text = "Episode: " + item.episode


            //Todo use glide to load image

            Glide.with(this)
                    .load(item.img)
                    .into(animeImage)



        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): AnimeAdapter.ViewHolder {
        // create a new view
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.list_items, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bind(myDataset[position])
    }

    override fun getItemCount() = myDataset.size

}