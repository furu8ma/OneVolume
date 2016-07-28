package jp.hateblo.furu8ma.onevolume

import android.app.Activity
import android.os.Bundle
import android.view.Gravity
import android.widget.Toast

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val sp = getSharedPreferences("ONE_VOLUME", MODE_PRIVATE)

        var message = "UNKNOWN"

        val oneVolumeEnabled = sp.getBoolean("ONE_VOLUME_ENABLED", false)
        if (oneVolumeEnabled) {
            message = "ONE VOLUME OFF"
            sp.edit().putBoolean("ONE_VOLUME_ENABLED", false).commit()
        } else {
            message = "ONE VOLUME ON"
            sp.edit().putBoolean("ONE_VOLUME_ENABLED", true).commit()
        }
        val toast = Toast.makeText(this, "\n\n　　　" + message + "　　　\n\n", Toast.LENGTH_SHORT)
        toast.setGravity(Gravity.CENTER, 0, 0)
        toast.show()

        finish()
    }

}
