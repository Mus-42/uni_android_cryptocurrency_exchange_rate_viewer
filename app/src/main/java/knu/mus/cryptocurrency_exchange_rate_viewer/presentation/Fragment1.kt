package knu.mus.cryptocurrency_exchange_rate_viewer.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import knu.mus.cryptocurrency_exchange_rate_viewer.R
import knu.mus.cryptocurrency_exchange_rate_viewer.domain.ListItem

class Fragment1 : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_1, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = Adapter(arrayOf(ListItem("BTC","https://www.cryptocompare.com/media/37746251/btc.png",1.0,"tomorrow")))

        val recyclerView: RecyclerView =view.findViewById(R.id.RecyclerView)

        recyclerView.layoutManager = LinearLayoutManager(context)

        recyclerView.adapter = adapter
    }

}