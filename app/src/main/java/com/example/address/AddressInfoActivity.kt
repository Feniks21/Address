package com.example.address

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class AddressInfoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_address_info)

        val addressData = intent.getSerializableExtra("address_data") as? CleanAddressResponse
        val addressTextView: TextView = findViewById(R.id.address_text_view)
        val openMapButton: Button = findViewById(R.id.open_map_button)
        val backButton: Button = findViewById(R.id.back_button)

        addressData?.let { data ->
            val addressInfo = """
                Страна: ${data.country}
                Регион: ${data.region}
                Район: ${data.area}
                Город: ${data.city}
                Широта: ${data.geo_lat}
                Долгота: ${data.geo_lon}
            """.trimIndent()
            addressTextView.text = addressInfo

            openMapButton.setOnClickListener {
                val geoUri = "geo:${data.geo_lat},${data.geo_lon}"
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(geoUri))
                if (intent.resolveActivity(packageManager) != null) {
                    startActivity(intent)
                }
            }
        }

        backButton.setOnClickListener {
            finish()
        }
    }
}
