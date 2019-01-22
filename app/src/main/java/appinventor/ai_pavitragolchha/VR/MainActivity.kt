package appinventor.ai_pavitragolchha.VR

import android.hardware.Sensor
import android.hardware.Sensor.TYPE_ACCELEROMETER
import android.hardware.Sensor.TYPE_GRAVITY
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(my_toolbar)

        val phoneInfo = PhoneInfo(this)
        val accelerometer = phoneInfo.checkSensor(TYPE_ACCELEROMETER)
        val compass = phoneInfo.checkSensor(TYPE_GRAVITY)
        val gyro = phoneInfo.checkSensor(Sensor.TYPE_GYROSCOPE)
        val screenSize = phoneInfo.getScreenSize().toDouble().round()
        val (width, height) = phoneInfo.getScreenRes()
        val androidVersion = Build.VERSION.RELEASE
        val ram = (phoneInfo.getRam() / (1024.0 * 1024.0 * 1024.0)).round()

        val results = ArrayList<Item>()
        results.run {
            add(Item(ItemType.CHECK, "Accelerometer", accelerometer))
            add(Item(ItemType.CHECK, "Compass", compass))
            add(Item(ItemType.CHECK, "Gyroscope", gyro))
            add(Item(ItemType.TEXT, "Screen Size", "$screenSize\""))
            add(Item(ItemType.TEXT, "Screen Resolution", "$width ✕ $height"))
            add(Item(ItemType.TEXT, "Android Version", androidVersion))
            add(Item(ItemType.TEXT, "RAM", "$ram GB"))
        }

        recyclerView = details_list.apply {
            setHasFixedSize(true)

            layoutManager = LinearLayoutManager(context)

            adapter = DetailsListAdapter(results)
        }
    }
}

enum class ItemType {
    CHECK, TEXT
}

data class Item(val itemType: ItemType, val name: String, val value: Any)

// Extension utils
private fun Double.round() = Math.round(this * 100) / 100.0