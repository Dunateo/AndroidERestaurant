package fr.isen.bru.androiderestaurant.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import fr.isen.bru.androiderestaurant.DetailActivity
import fr.isen.bru.androiderestaurant.R
import fr.isen.bru.androiderestaurant.domain.FoodData
import java.lang.StringBuilder


class FoodAdaptater(
    private val data: List<FoodData>,
    private val context: Context
) : RecyclerView.Adapter<FoodAdaptater.ViewHolder>() {

    inner class ViewHolder(dataView: View) : RecyclerView.ViewHolder(dataView) {
        val text: TextView = dataView.findViewById(R.id.tvTitle)
        val desc: TextView = dataView.findViewById(R.id.tvDescription)
        val price: TextView = dataView.findViewById(R.id.tvPrice)
        val img:ImageView = dataView.findViewById(R.id.ivImage)
        val card: CardView = dataView.findViewById(R.id.myCardView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodAdaptater.ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)

        val contactView = inflater.inflate(R.layout.data_layout, parent, false)

        return ViewHolder(contactView)
    }


    override fun onBindViewHolder(viewHolder: FoodAdaptater.ViewHolder, position: Int) {

        val data: FoodData = data[position]

        val desc = viewHolder.desc
        desc.text = data.categ_name_fr

        val price = viewHolder.price
        val pr :StringBuilder =StringBuilder(data.prices[0].price.toString())
        pr.append( " Rs")
        price.text = pr

        val textView = viewHolder.text
        textView.text = data.name_fr

        val img = viewHolder.img
        val picasso = Picasso.get()
        if(data.images[0].isNotEmpty()){
            picasso.load(data.images[0]).into(img)
        }else{
            picasso.load(R.drawable.crocodile).into(img)
        }

        val card = viewHolder.card
        card.setOnClickListener {

            val intent = Intent(context, DetailActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            intent.putExtra("Desc", data)
            intent.putExtra("price", pr.toString())
            intent.putExtra("RecipeName", data.name_fr)
            intent.putExtra("Image", data.images[0])
            intent.putExtra("keyValue", data.id)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

}