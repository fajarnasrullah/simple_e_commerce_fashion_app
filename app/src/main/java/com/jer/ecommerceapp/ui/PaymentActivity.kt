package com.jer.ecommerceapp.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.jer.ecommerceapp.R
import com.jer.ecommerceapp.databinding.ActivityPaymentBinding
import com.midtrans.sdk.corekit.core.MidtransSDK
import com.midtrans.sdk.corekit.core.TransactionRequest
import com.midtrans.sdk.corekit.core.UIKitCustomSetting
import com.midtrans.sdk.corekit.models.CustomerDetails
import com.midtrans.sdk.uikit.SdkUIFlowBuilder
import com.midtrans.sdk.uikit.api.model.CustomColorTheme
import com.midtrans.sdk.uikit.api.model.ItemDetails
import com.midtrans.sdk.uikit.external.UiKitApi

class PaymentActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPaymentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPaymentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        UiKitApi.Builder()
            .withMerchantClientKey("SB-Mid-client-2nbc7P3NpjFmlDaU")
            .withContext(applicationContext)
            .withMerchantUrl(
//                "http://192.168.1.9:3000/"
//                "https://merchant-url-sandbox.com/"
//                "https://merchant-api-sandbox.shipper.id/"
                "https://api.sandbox.midtrans.com/v2/"
            )
            .enableLog(true)
            .withColorTheme(CustomColorTheme("#FFE51255", "#B61548", "#FFE51255"))
            .build()

        setLocaleNew("id")

        val uiKitCustomSetting = UIKitCustomSetting()
        uiKitCustomSetting.setShowPaymentStatus(false)
        MidtransSDK.getInstance().setUiKitCustomSetting(uiKitCustomSetting)


        val price = intent.getDoubleExtra("totalPrice", 0.0)

        val transactionRequest = TransactionRequest("order-id", price) // order-id unik dan jumlah transaksi
        transactionRequest.customerDetails = CustomerDetails().apply {
            customerIdentifier = "email@example.com"
            firstName = "Sumanto"
            lastName = "lapar"
            phone = "08123456789"
        }
        MidtransSDK.getInstance().transactionRequest = transactionRequest
        MidtransSDK.getInstance().startPaymentUiFlow(this)

    }

    private fun setLocaleNew(languageCode: String?) {
        val locales = LocaleListCompat.forLanguageTags(languageCode)
        AppCompatDelegate.setApplicationLocales(locales)
    }


}