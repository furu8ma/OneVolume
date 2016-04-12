package jp.hateblo.furu8ma.onevolume

import android.app.Activity
import android.os.Bundle
import android.widget.Toast

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val sp = getSharedPreferences("ONE_VOLUME", MODE_PRIVATE)

        val oneVolumeEnabled = sp.getBoolean("ONE_VOLUME_ENABLED", false)
        if ( oneVolumeEnabled) {
            Toast.makeText(this, "ONE VOLUME OFF", Toast.LENGTH_SHORT).show()
            sp.edit().putBoolean("ONE_VOLUME_ENABLED", false).commit()
        } else {
            Toast.makeText(this, "ONE VOLUME ON", Toast.LENGTH_SHORT).show()
            sp.edit().putBoolean("ONE_VOLUME_ENABLED", true).commit()
        }

        finish()
    }

}
