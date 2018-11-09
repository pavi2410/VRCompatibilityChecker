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

        setSupportActionBar(findViewById(R.id.my_toolbar))

        val results: ArrayList<String> = ArrayList()

        val pi = PhoneInfo(this)
        val accelerometer = pi.checkSensor(TYPE_ACCELEROMETER)
        val compass = pi.checkSensor(TYPE_GRAVITY)
        val gyro = pi.checkSensor(Sensor.TYPE_GYROSCOPE)
        val screenDensity = pi.getScreenDensity()
        val screenSize = pi.getScreenSize().toDouble().round()
        val (w, h) = pi.getScreenRes()
        val androidVersion = Build.VERSION.SDK_INT
        val ram = (pi.getRam() / (1024.0 * 1024.0 * 1024.0)).round()

        results.add(accelerometer.toYesNo())
        results.add(compass.toYesNo())
        results.add(gyro.toYesNo())
        results.add("$screenDensity dpi")
        results.add("$screenSize\"")
        results.add("$w ✕ $h")
        results.add(androidVersion.toString())
        results.add("$ram GB")


        recyclerView = details_list.apply {
            setHasFixedSize(true)

            layoutManager = LinearLayoutManager(context)

            adapter = MyAdapter(results)
        }
    }

    // Extension utils
    private fun Double.round() = Math.round(this * 100) / 100.0
    private fun Boolean.toYesNo() = if (this) "Yes" else "No"
}