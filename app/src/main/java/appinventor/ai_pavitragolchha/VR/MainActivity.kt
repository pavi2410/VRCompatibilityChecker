package appinventor.ai_pavitragolchha.VR

import android.hardware.Sensor
import android.hardware.Sensor.TYPE_ACCELEROMETER
import android.hardware.Sensor.TYPE_GRAVITY
import android.os.Build
import android.os.Bundle
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(findViewById(R.id.my_toolbar))

        val pi = PhoneInfo(this)
        val accelerometer = pi.checkSensor(TYPE_ACCELEROMETER)
        val compass = pi.checkSensor(TYPE_GRAVITY)
        val gyro = pi.checkSensor(Sensor.TYPE_GYROSCOPE)
        val screenDensity = pi.getScreenDensity()
        val screenSize = pi.getScreenSize().toDouble().round()
        val (w, h) = pi.getScreenRes()
        val androidVersion = Build.VERSION.SDK_INT
        val ram = (pi.getRam() / (1024.0 * 1024.0 * 1024.0)).round()

        val results = ArrayList<Item>()
        results.run {
            add(Item(ItemType.Check, R.drawable.ic_launcher_foreground, "Accelerometer", accelerometer))
            add(Item(ItemType.Check, R.drawable.ic_launcher_foreground, "Compass", compass))
            add(Item(ItemType.Check, R.drawable.ic_launcher_foreground, "Gyroscope", gyro))
            add(Item(ItemType.Check, R.drawable.ic_launcher_foreground, "Screen Density", "$screenDensity dpi"))
            add(Item(ItemType.Check, R.drawable.ic_launcher_foreground, "Screen Size", "$screenSize\""))
            add(Item(ItemType.Check, R.drawable.ic_launcher_foreground, "Screen Resolution", "$w ✕ $h"))
            add(Item(ItemType.Check, R.drawable.ic_launcher_foreground, "Android Version", androidVersion.toString()))
            add(Item(ItemType.Check, R.drawable.ic_launcher_foreground, "RAM", "$ram GB"))
        }


        recyclerView = details_list.apply {
            setHasFixedSize(true)

            layoutManager = LinearLayoutManager(context)

            adapter = DetailsListAdapter(results)
        }
    }
}

enum class ItemType {
    Check, Text
}

data class Item(val itemType: ItemType, @DrawableRes val icon: Int, val name: String, val value: Any)


// Extension utils
private fun Double.round() = Math.round(this * 100) / 100.0