package fr.isen.bru.androiderestaurant.adapter

import android.content.Context
import android.content.Intent
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
import fr.isen.bru.androiderestaurant.domain.OrderData


class CartAdapter(
    private val data: MutableList<OrderData>,
    private val context: Context
) : RecyclerView.Adapter<CartAdapter.ViewHolder>() {

    inner class ViewHolder(dataView: View) : RecyclerView.ViewHolder(dataView) {
        val text: TextView = dataView.findViewById(R.id.tvTitle)
        val desc: TextView = dataView.findViewById(R.id.tvDescription)
        val price: TextView = dataView.findViewById(R.id.tvPrice)
        val img: ImageView = dataView.findViewById(R.id.ivImage)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartAdapter.ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)

        val contactView = inflater.inflate(R.layout.data_layout, parent, false)

        return ViewHolder(contactView)
    }


    override fun onBindViewHolder(viewHolder: CartAdapter.ViewHolder, position: Int) {

        val data: OrderData = data[position]

        val desc = viewHolder.desc
        desc.text = data.quantity.toString()

        val price = viewHolder.price
        val pr: java.lang.StringBuilder = StringBuilder((data.price * data.quantity).toString())
        pr.append(" Rs")
        price.text = pr

        val textView = viewHolder.text
        textView.text = data.item.name_fr

        val img = viewHolder.img
        val picasso = Picasso.get()
        if (data.item.images[0].isNotEmpty()) {
            picasso.load(data.item.images[0]).into(img)
        } else {
            picasso.load(R.drawable.crocodile).into(img)
        }

    }

    override fun getItemCount(): Int {
        return data.size
    }
}

