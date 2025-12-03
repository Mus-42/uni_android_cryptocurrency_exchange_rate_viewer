package knu.mus.cryptocurrency_exchange_rate_viewer.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import knu.mus.cryptocurrency_exchange_rate_viewer.R
import knu.mus.cryptocurrency_exchange_rate_viewer.databinding.Fragment1Binding
import knu.mus.cryptocurrency_exchange_rate_viewer.databinding.Fragment2Binding
class Fragment2 : Fragment() {

    private var _binding: Fragment2Binding? = null

    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
        binding.textViewName.text = arguments?.getString("name")
        binding.textViewPrice.text = arguments?.getFloat("price").toString()
        binding.textViewMin.text = arguments?.getFloat("low").toString()
        binding.textViewMax.text = arguments?.getFloat("high").toString()
        binding.textViewUpdated.text = arguments?.getLong("date").toString()
    }
}