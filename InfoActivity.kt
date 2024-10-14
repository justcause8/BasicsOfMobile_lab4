    package com.example.systemtrakingtransport
    
    import android.content.Intent
    import android.os.Bundle
    import android.view.Menu
    import android.view.MenuItem
    import android.widget.ArrayAdapter
    import android.widget.ListView
    import android.widget.TextView
    import androidx.appcompat.app.AppCompatActivity

    class InfoActivity : AppCompatActivity() {

        private lateinit var lvInfoResults: ListView
        private lateinit var vehicleInfoAdapter: ArrayAdapter<String>
        private val vehicleInfoList = mutableListOf<String>()

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_info)

            lvInfoResults = findViewById(R.id.lvInfoResults)

            vehicleInfoAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, vehicleInfoList)
            lvInfoResults.adapter = vehicleInfoAdapter

            // Получение переданных данных
            val receivedList = intent.getStringArrayListExtra("vehicleList")
            if (receivedList != null) {
                vehicleInfoList.addAll(receivedList)
                vehicleInfoAdapter.notifyDataSetChanged()
            }
        }

        // Создаем меню
        override fun onCreateOptionsMenu(menu: Menu?): Boolean {
            menuInflater.inflate(R.menu.main_menu, menu)
            return true
        }

        override fun onOptionsItemSelected(item: MenuItem): Boolean {
            when (item.itemId) {
                R.id.pageMain -> {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    return true
                }
                R.id.pageInfo -> {
                    return true
                }
            }
            return super.onOptionsItemSelected(item)
        }
    }
