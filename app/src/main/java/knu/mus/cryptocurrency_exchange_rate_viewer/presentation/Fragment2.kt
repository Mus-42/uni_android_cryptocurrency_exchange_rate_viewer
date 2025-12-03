package knu.mus.cryptocurrency_exchange_rate_viewer.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import knu.mus.cryptocurrency_exchange_rate_viewer.R

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class Fragment2 : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_2, container, false)
    }
}