package appinventor.ai_pavitragolchha.VR

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Typeface
import android.hardware.Sensor
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        const val THUMBS_UP = "\uF164"
        const val THUMBS_DOWN = "\uF165"
        const val NEUTRAL_FACE = "\uF11A"
        const val CHECK = "\uF00C"
        const val CROSS = "\uF00D"
        const val SUCCESS_COMMENT = "Congratulations!!! Your device fully supports VR"
        const val SUCCESS_PARTIAL_COMMENT = "It's OK! Your device supports limited features of VR"
        const val FAIL_COMMENT = "Oops! Your device doesn't support VR"
        const val ROBOTO_THIN_FONT = "Roboto-Thin.ttf"
        const val FONTAWESOME_FONT = "fontawesome-webfont.ttf"
    }

    enum class Result {
        SUCCESS,
        SUCCESS_PARTIAL,
        FAILED
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val views1 = listOf(checkButton, resultComment, accText, gyroText, compassText, screenSizeText,
                screenSizeResult, screenResText, screenResResult, androidText, ramText)
        for (v in views1)
            v.typeface = Typeface.createFromAsset(assets, ROBOTO_THIN_FONT)

        val views2 = listOf(resultIcon, accResult, gyroResult, compassResult, androidResult, ramResult)
        for (v in views2)
            v.typeface = Typeface.createFromAsset(assets, FONTAWESOME_FONT)

        checkButton.setOnClickListener {
            checkButton.visibility = View.GONE
            resultBox.visibility = View.VISIBLE

            init()
        }
    }

    private fun init() {
        val result = check()
        resultIcon.text = when (result) {
            Result.SUCCESS -> THUMBS_UP
            Result.SUCCESS_PARTIAL -> NEUTRAL_FACE
            Result.FAILED -> THUMBS_DOWN
        }
        resultComment.text = when (result) {
            Result.SUCCESS -> SUCCESS_COMMENT
            Result.SUCCESS_PARTIAL -> SUCCESS_PARTIAL_COMMENT
            Result.FAILED -> FAIL_COMMENT
        }

        val pi = PhoneInfo(this)

        accResult.text = if (pi.checkSensor(Sensor.TYPE_ACCELEROMETER)) CHECK else CROSS
        gyroResult.text = if (pi.checkSensor(Sensor.TYPE_GYROSCOPE)) CHECK else CROSS
        compassResult.text = if (pi.checkSensor(Sensor.TYPE_MAGNETIC_FIELD)) CHECK else CROSS

        screenSizeResult.text = getString(R.string.screen_size_result).format(pi.getScreenSize())

        val (w, h) = pi.getScreenRes()
        screenResResult.text = getString(R.string.screen_res_result).format(w, h)

        androidResult.text = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) CHECK else CROSS

        ramResult.text = if (pi.getRam() > 1.5 * 1024 * 1024 * 1024) CHECK else CROSS
    }

    fun check(): Result {
        val pi = PhoneInfo(this)

        val acc = pi.checkSensor(Sensor.TYPE_ACCELEROMETER)
        val gyro = pi.checkSensor(Sensor.TYPE_GYROSCOPE)
        val compass = pi.checkSensor(Sensor.TYPE_MAGNETIC_FIELD)
        val android = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT

        return when {
            acc && gyro && compass && android -> Result.SUCCESS // All things available
            (acc || gyro || compass) && android -> Result.SUCCESS_PARTIAL // Something is available
            else -> Result.FAILED // Nothing is available
        }
    }

    private fun textToBitmap(text: String, textSize: Float, textColor: Int): Bitmap {
        val paint = Paint(Paint.ANTI_ALIAS_FLAG)
        paint.textSize = textSize
        paint.color = textColor
        paint.textAlign = Paint.Align.LEFT
        paint.typeface = Typeface.createFromAsset(assets, FONTAWESOME_FONT)
        val baseline = -paint.ascent()
        val w = paint.measureText(text)
        val h = paint.descent() + baseline

        val image = Bitmap.createBitmap(w.toInt(), h.toInt(), Bitmap.Config.ARGB_8888)

        val canvas = Canvas(image)
        canvas.drawText(text, 0f, baseline, paint)

        return image
    }
}
