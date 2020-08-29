package br.com.barros.aco.activity

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import br.com.barros.aco.R
import br.com.barros.aco.base.ViewModelFactory
import br.com.barros.aco.viewmodel.CepViewModel
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: CepViewModel
    private lateinit var btBuscar: Button
    private lateinit var tvResult: TextView
    private lateinit var etPostalCode: EditText

    private lateinit var adView: AdView

    val TAG: String = "Log"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this, ViewModelFactory.getInstance(application)).get(CepViewModel::class.java)
        initViews()
        initObservables()
        setListeners()

        MobileAds.initialize(this) {}

        adView = findViewById(R.id.ad_view)
        var adRequest = AdRequest.Builder().build()
        adRequest.isTestDevice(this)
        adView.loadAd(adRequest)


        admodClick(adView)
    }

    private fun admodClick(banner: AdView) {
        banner.adListener = object: AdListener() {
            override fun onAdLoaded() {
                // Code to be executed when an ad finishes loading.
                Log.d(TAG, "MainActivity - onAdLoaded() called")
            }

            override fun onAdFailedToLoad(errorCode : Int) {
                // Code to be executed when an ad request fails.
                Log.d(TAG, "MainActivity - onAdFailedToLoad() called")
            }

            override fun onAdOpened() {
                // Code to be executed when an ad opens an overlay that
                // covers the screen.
                Log.d(TAG, "MainActivity - onAdOpened() called")
            }

            override fun onAdClicked() {
                // Code to be executed when the user clicks on an ad.
                Log.d(TAG, "MainActivity - onAdClicked() called")
            }

            override fun onAdLeftApplication() {
                // Code to be executed when the user has left the app.
                Log.d(TAG, "MainActivity - onAdLeftApplication() called")
            }

            override fun onAdClosed() {
                // Code to be executed when the user is about to return
                // to the app after tapping on an ad.
                Log.d(TAG, "MainActivity - onAdClosed() called")
            }
        }

    }

    private fun initViews() {
        btBuscar = findViewById(R.id.btBuscar)
        tvResult = findViewById(R.id.tvResult)
        etPostalCode = findViewById(R.id.etPostalCode)
    }

    private fun initObservables() {
        viewModel.getPostalCode.observe(this, Observer {
            if(it != null) {
                showLoading(false)
                val cep = it
                tvResult.text = cep.toString()
            }
        })

        viewModel.getError.observe(this, Observer {
            if(it != null) {
                showLoading(false)
                tvResult.text = it
            }
        })
    }

    private fun setListeners() {
        btBuscar.setOnClickListener { searchPostalCode() }
    }

    private fun searchPostalCode() {
        if(!etPostalCode.editableText.isEmpty() && etPostalCode.editableText.count() == 8) {
            showLoading(true)
            viewModel.searchPostalCode(etPostalCode.editableText.toString())
        } else {
            etPostalCode.error = getString(R.string.error_incorrect_zip)
        }
    }

    private fun showLoading(loop: Boolean) {
        if(loop) av_from_code.setAnimation("loading.json")
        av_from_code.playAnimation()
        av_from_code.loop(loop)
        if(!loop) av_from_code.visibility = View.INVISIBLE
    }
}
