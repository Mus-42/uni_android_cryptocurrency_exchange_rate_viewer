package knu.mus.cryptocurrency_exchange_rate_viewer.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import knu.mus.cryptocurrency_exchange_rate_viewer.R
import knu.mus.cryptocurrency_exchange_rate_viewer.databinding.Fragment1Binding
import knu.mus.cryptocurrency_exchange_rate_viewer.domain.CoinItem
import knu.mus.cryptocurrency_exchange_rate_viewer.domain.ListItem

class Fragment1 : Fragment() {

    private var _binding: Fragment1Binding? = null

    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = Fragment1Binding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = Adapter(arrayOf(
            CoinItem(
                "BTC",
                "Bitcoin",
                "https://www.cryptocompare.com/media/37746251/btc.png",
                1.0F,
                2.0F,
                0.0F,

                123
            )
        ))

        val recyclerView: RecyclerView = binding.RecyclerView

        recyclerView.layoutManager = LinearLayoutManager(context)

        recyclerView.adapter = adapter

        adapter.itemsInteractionListener = object : Adapter.ItemsInteractionListener {
            override fun onClick(coinItem: CoinItem) {
                (activity as MainActivity).startFragment2(coinItem)

            }
        }
    }

}