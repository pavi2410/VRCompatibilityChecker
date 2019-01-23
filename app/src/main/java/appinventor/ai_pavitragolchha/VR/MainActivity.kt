package appinventor.ai_pavitragolchha.VR

import android.hardware.Sensor.*
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
        val gyro = phoneInfo.checkSensor(TYPE_GYROSCOPE)
        val screenSize = phoneInfo.getScreenSize().toDouble().round()
        val ram = phoneInfo.getRam()

        /*
            Accelerometer = required
            Gyro = required
            Screen Size >= 5"
            Android >= KitKat
            RAM >= 2 GB
        */
        if (accelerometer and gyro and (screenSize >= 5) and (ram >= 2.GB)) {
            message.text = getString(R.string.success_message)
            icon.setImageResource(R.drawable.check)
        } else {
            message.text = getString(R.string.fail_message)
            icon.setImageResource(R.drawable.cross)
        }

        val results = arrayListOf(
                Item("Accelerometer", accelerometer),
                Item("Compass", compass),
                Item("Gyroscope", gyro),
                Item("Screen Size", screenSize >= 5.0),
                Item("RAM", ram >= 2.GB))

        recyclerView = details_list.apply {
            setHasFixedSize(true)

            layoutManager = LinearLayoutManager(context)

            adapter = DetailsListAdapter(results)
        }
    }
}

data class Item(val name: String, val value: Boolean)

// Extension utils
private fun Double.round() = Math.round(this * 10) / 10

private val Int.GB: Int
    get() = this * 1_000_000_000