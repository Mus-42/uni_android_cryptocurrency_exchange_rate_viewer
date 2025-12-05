package knu.mus.cryptocurrency_exchange_rate_viewer.presentation

import android.os.Bundle
import android.transition.TransitionInflater
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import knu.mus.cryptocurrency_exchange_rate_viewer.R
import knu.mus.cryptocurrency_exchange_rate_viewer.databinding.Fragment1Binding
import knu.mus.cryptocurrency_exchange_rate_viewer.databinding.Fragment2Binding
import java.text.DateFormat

class Fragment2 : Fragment() {

    private var _binding: Fragment2Binding? = null

    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val inflater = TransitionInflater.from(requireContext())
        enterTransition = inflater.inflateTransition(R.transition.slide_right)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = Fragment2Binding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            val df = DateFormat.getTimeInstance()
            val lastUpdate = df.format(java.util.Date(requireArguments().getLong("date") * 1000))

            Picasso.get().load("https://www.cryptocompare.com${arguments?.getString("url")}").into(binding.imageView)
            binding.coinName.text = arguments?.getString("name")
            binding.textViewPrice.text = String.format(requireContext().getString(R.string.detailed_price), arguments?.getFloat("price").toString())
            binding.textViewMin.text = String.format(requireContext().getString(R.string.detailed_min), arguments?.getFloat("low").toString())
            binding.textViewMax.text = String.format(requireContext().getString(R.string.detailed_max), arguments?.getFloat("high").toString())
            binding.textViewUpdated.text = lastUpdate.toString()
    }
}