package fr.isen.bru.androiderestaurant.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import fr.isen.bru.androiderestaurant.DetailActivity
import fr.isen.bru.androiderestaurant.R
import fr.isen.bru.androiderestaurant.domain.FoodData


class FoodAdaptater(
    private val data: List<FoodData>,
    private val context: Context
) : RecyclerView.Adapter<FoodAdaptater.ViewHolder>() {

    inner class ViewHolder(dataView: View) : RecyclerView.ViewHolder(dataView) {
        val text: TextView = dataView.findViewById(R.id.tvTitle)
        val desc: TextView = dataView.findViewById(R.id.tvDescription)
        val price: TextView = dataView.findViewById(R.id.tvPrice)
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
        desc.text = data.itemDescription

        val price = viewHolder.price
        price.text = data.itemPrice

        val textView = viewHolder.text
        textView.text = data.itemName

        textView.setOnClickListener {

            val intent = Intent(context, DetailActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            intent.putExtra("Description", data.itemDescription)
            intent.putExtra("price", data.itemPrice)
            intent.putExtra("RecipeName", data.itemName)
            intent.putExtra("Image", data.itemImage)
            intent.putExtra("keyValue", data.key)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

}