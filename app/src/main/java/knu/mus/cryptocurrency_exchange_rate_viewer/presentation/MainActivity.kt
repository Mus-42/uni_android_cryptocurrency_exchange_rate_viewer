package knu.mus.cryptocurrency_exchange_rate_viewer.presentation

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import dagger.hilt.android.AndroidEntryPoint
import knu.mus.cryptocurrency_exchange_rate_viewer.R
import knu.mus.cryptocurrency_exchange_rate_viewer.databinding.ActivityMainBinding
import knu.mus.cryptocurrency_exchange_rate_viewer.data.ExchangeRatesDataSource
import knu.mus.cryptocurrency_exchange_rate_viewer.data.ExchangeRatesList
import knu.mus.cryptocurrency_exchange_rate_viewer.domain.CoinItem
import knu.mus.cryptocurrency_exchange_rate_viewer.presentation.ExchangeRatesViewModel

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    //val exchangeRatesViewModel by viewModels<ExchangeRatesViewModel>()

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            val fragmentManager = supportFragmentManager
            if (fragmentManager.backStackEntryCount != 0 &&
                fragmentManager.getBackStackEntryAt(fragmentManager.backStackEntryCount - 1).name == "THE_BIG_ONE"){
                fragmentManager.popBackStack()
            }
        }

        // dummy example to check that it works
        //exchangeRatesViewModel.exchangeRates.observe(this) {
        //    Log.d(TAG, "exchange rates list: ${it}")
       // }
        //exchangeRatesViewModel.refreshRates();


    }


    companion object {
        const val TAG = "MainActivity";
    }

    fun startFragment2(coinItem: CoinItem){
        val fragment2 = Fragment2()
        val bundle = Bundle()
        bundle.putString("name", coinItem.shortName)
        bundle.putString("url", coinItem.imageUrl)
        bundle.putFloat("price", coinItem.price)
        bundle.putFloat("low", coinItem.priceLow24Hour)
        bundle.putFloat("high", coinItem.priceHigh24Hour)
        bundle.putLong("date", coinItem.lastUpdate)
        fragment2.arguments = bundle

        Log.d(TAG, coinItem.priceHigh24Hour.toString())
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        binding.fragmentContainerView?.id?.let {

            fragmentTransaction.replace(binding.fragmentContainerView!!.id, fragment2)
            fragmentTransaction.addToBackStack("THE_BIG_ONE")
        }
        binding.fragmentContainerView3?.id?.let {
            fragmentTransaction.replace(binding.fragmentContainerView3!!.id, fragment2)
            fragmentTransaction.addToBackStack("THE_SMALL_ONE")
        }


        fragmentTransaction.commit()


    }
}
