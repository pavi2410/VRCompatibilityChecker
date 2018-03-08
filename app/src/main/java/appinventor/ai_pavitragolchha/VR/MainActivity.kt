package appinventor.ai_pavitragolchha.VR

import android.hardware.Sensor
import android.os.Build
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        // TODO: Replace Emoji with FontAwesome
        // TODO: Use Roboto Thin font
        const val THUMBS_UP = "👍"
        const val THUMBS_DOWN = "👎"
        const val NEUTRAL_FACE = "~"
        const val CHECK = "✔"
        const val CROSS = "❌"
        const val SUCCESS_COMMENT = "Congratulations!!! Your device fully supports VR"
        const val SUCCESS_PARTIAL_COMMENT = "It's OK! Your device partially supports VR"
        const val FAIL_COMMENT = "Oops! Your device doesn't support VR"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        fab.setOnLongClickListener {
            Toast.makeText(this, "Share Result", Toast.LENGTH_SHORT).show()
            true
        }

        checkButton.setOnClickListener {
            checkButton.visibility = View.GONE
            resultBox.visibility = View.VISIBLE
            fab.visibility = View.VISIBLE

            init()
        }
    }

    fun init() {
        val result = check()
        resultIcon.text = when (result) {
            0 -> Companion.THUMBS_UP
            1 -> Companion.THUMBS_DOWN
            2 -> Companion.NEUTRAL_FACE
            else -> "error"
        }
        resultComment.text = when (result) {
            0 -> Companion.SUCCESS_COMMENT
            1 -> Companion.SUCCESS_PARTIAL_COMMENT
            2 -> Companion.FAIL_COMMENT
            else -> "error"
        }

        val pi = PhoneInfo(this)

        accResult.text = if (pi.checkSensor(Sensor.TYPE_ACCELEROMETER)) Companion.CHECK else Companion.CROSS
        gyroResult.text = if (pi.checkSensor(Sensor.TYPE_GYROSCOPE)) Companion.CHECK else Companion.CROSS
        compassResult.text = if (pi.checkSensor(Sensor.TYPE_MAGNETIC_FIELD)) Companion.CHECK else Companion.CROSS

        screenSizeResult.text = "${"%.1f".format(pi.getScreenSize())}\""

        val (w, h) = pi.getScreenRes()
        screenResResult.text = "${w}x$h"

        androidResult.text = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) Companion.CHECK else Companion.CROSS

        ramResult.text = if (pi.getRam() > 1.5 * 1024 * 1024 * 1024) Companion.CHECK else Companion.CROSS
    }

    fun check(): Int {
        val pi = PhoneInfo(this)

        val acc = pi.checkSensor(Sensor.TYPE_ACCELEROMETER)
        val gyro = pi.checkSensor(Sensor.TYPE_GYROSCOPE)
        val compass = pi.checkSensor(Sensor.TYPE_MAGNETIC_FIELD)
        val android = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT

        return when {
            acc && gyro && compass && android -> 0 // All things available
            (acc || gyro || compass) && android -> 1 // Something is available
            else -> 2 // Nothing is available
        }
    }
}
