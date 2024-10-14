package com.example.systemtrakingtransport

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var etBrand: EditText
    private lateinit var etModel: EditText
    private lateinit var etYear: EditText
    private lateinit var rgVehicleType: RadioGroup
    private lateinit var btnSubmit: Button
    private val vehicleInfoList = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etBrand = findViewById(R.id.etBrand)
        etModel = findViewById(R.id.etModel)
        etYear = findViewById(R.id.etYear)
        rgVehicleType = findViewById(R.id.rgVehicleType)
        btnSubmit = findViewById(R.id.btnSubmit)

        btnSubmit.setOnClickListener {
            val brand = etBrand.text.toString().trim()
            val model = etModel.text.toString().trim()
            val year = etYear.text.toString().trim()

            if (brand.isEmpty() || model.isEmpty() || year.isEmpty()) {
                Toast.makeText(this, "Заполните все поля", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val vehicleType = when (rgVehicleType.checkedRadioButtonId) {
                R.id.rbSedan -> "Седан"
                R.id.rbWagon -> "Универсал"
                R.id.rbSuv -> "Внедорожник"
                else -> "Неизвестно"
            }

            val resultText = "Марка: $brand\nМодель: $model\nГод выпуска: $year\nТип автомобиля: $vehicleType"
            vehicleInfoList.add(resultText)

            etBrand.text.clear()
            etModel.text.clear()
            etYear.text.clear()
            rgVehicleType.clearCheck()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.pageMain -> {
                return true // Оставляем на MainActivity
            }
            R.id.pageInfo -> {
                val intent = Intent(this, InfoActivity::class.java)
                intent.putStringArrayListExtra("vehicleList", ArrayList(vehicleInfoList)) // Передача списка
                startActivity(intent)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}