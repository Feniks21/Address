package com.example.address

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.Serializable

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val addressInput: EditText = findViewById(R.id.address_input)
        val recognizeButton: Button = findViewById(R.id.recognize_button)

        recognizeButton.setOnClickListener {
            val address = addressInput.text.toString()
            if (address.isNotBlank()) {
                cleanAddress(address)
            } else {
                Toast.makeText(this, "Введите адрес", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun cleanAddress(address: String) {
        val request = listOf(address)
        ApiClient.api.cleanAddress(request).enqueue(object : Callback<List<CleanAddressResponse>> {
            override fun onResponse(call: Call<List<CleanAddressResponse>>, response: Response<List<CleanAddressResponse>>) {
                if (response.isSuccessful) {
                    val cleanedAddresses = response.body()
                    if (!cleanedAddresses.isNullOrEmpty()) {
                        val addressData = cleanedAddresses[0]
                        val intent = Intent(this@MainActivity, AddressInfoActivity::class.java).apply {
                            putExtra("address_data", addressData as Serializable)
                        }
                        startActivity(intent)
                    } else {
                        Toast.makeText(this@MainActivity, "Адрес не найден", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this@MainActivity, "Ошибка: ${response.message()}", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<CleanAddressResponse>>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Ошибка: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
