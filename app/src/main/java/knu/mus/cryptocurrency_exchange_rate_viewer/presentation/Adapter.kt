package knu.mus.cryptocurrency_exchange_rate_viewer.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import knu.mus.cryptocurrency_exchange_rate_viewer.R
import knu.mus.cryptocurrency_exchange_rate_viewer.domain.CoinItem

class Adapter(private val dataSet: Array<CoinItem>) :
    RecyclerView.Adapter<Adapter.ViewHolder>() {

    interface ItemsInteractionListener {
        fun onClick(coinItem: CoinItem)
    }

    var itemsInteractionListener: ItemsInteractionListener? = null

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val cardView = view.findViewById<CardView>(R.id.cardViewCoinItem)

        val image = view.findViewById<ImageView>(R.id.ImageView)
        val cur = view.findViewById<TextView>(R.id.textViewCur)
        val exchangeRate = view.findViewById<TextView>(R.id.textViewExchangeRate)
        val date = view.findViewById<TextView>(R.id.textViewDate)


    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.coin_item, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        val coinItem = dataSet[position]
        Picasso.get().load(coinItem.imageUrl).into(viewHolder.image)
        viewHolder.cur.text = coinItem.shortName
        viewHolder.exchangeRate.text = coinItem.price.toString()
        viewHolder.date.text = coinItem.lastUpdate.toString()
        viewHolder.cardView.setOnClickListener {
            itemsInteractionListener?.onClick(coinItem)
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size
}