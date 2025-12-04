package knu.mus.cryptocurrency_exchange_rate_viewer.presentation

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import knu.mus.cryptocurrency_exchange_rate_viewer.R
import knu.mus.cryptocurrency_exchange_rate_viewer.databinding.Fragment1Binding
import knu.mus.cryptocurrency_exchange_rate_viewer.domain.CoinItem
import knu.mus.cryptocurrency_exchange_rate_viewer.domain.ListItem
import knu.mus.cryptocurrency_exchange_rate_viewer.presentation.MainActivity.Companion.TAG
import kotlin.getValue
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import kotlin.getValue

@AndroidEntryPoint
class Fragment1 : Fragment() {

    val exchangeRatesViewModel by viewModels<ExchangeRatesViewModel>()

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

        val adapter = Adapter()


        val recyclerView: RecyclerView = binding.RecyclerView

        exchangeRatesViewModel.exchangeRates.observe(viewLifecycleOwner) {
            Log.d(TAG, "exchange rates list: ${it}")

            adapter.submitList(it)
        }

        recyclerView.layoutManager = LinearLayoutManager(context)

        recyclerView.adapter = adapter



        adapter.itemsInteractionListener = object : Adapter.ItemsInteractionListener {
            override fun onClick(coinItem: CoinItem) {
                (activity as MainActivity).startFragment2(coinItem)

            }
        }
    }

    companion object {
        const val TAG = "Fragment1";
    }
}
