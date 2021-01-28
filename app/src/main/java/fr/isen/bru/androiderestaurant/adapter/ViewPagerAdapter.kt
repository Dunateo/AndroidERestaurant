package fr.isen.bru.androiderestaurant.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.viewpager.widget.PagerAdapter
import com.squareup.picasso.Picasso
import fr.isen.bru.androiderestaurant.R
import fr.isen.bru.androiderestaurant.domain.IngreData
import kotlinx.android.synthetic.main.image_layout.view.*
import java.util.*


internal class ViewPagerAdapter(context: Context, images: List<String>) :
    PagerAdapter() {
    // Context object
    var context: Context = context

    // Array of images
    var images: List<String> = images

    // Layout Inflater
    var mLayoutInflater: LayoutInflater = LayoutInflater.from(context)
    override fun getCount(): Int {
        // return the number of images
        return images.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object` as LinearLayout
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        // inflating
        val itemView: View = mLayoutInflater.inflate(R.layout.image_layout, container, false)

        // referencing the image view from the item.xml file
        val imageView: ImageView = itemView.findViewById(R.id.imageLayout) as ImageView

        // setting the image in the imageView
        val picasso = Picasso.get()
        if(images[position].isEmpty()){
            picasso.load(R.drawable.crocodile).into(imageView)
        }else{
            picasso.load(images[position]).into(imageView)
        }

        // Adding the View
        Objects.requireNonNull(container).addView(itemView)
        return itemView
    }

    override fun destroyItem(
        container: ViewGroup,
        position: Int,
        `object`: Any
    ) {
        container.removeView(`object` as LinearLayout)
    }

}