package jp.hateblo.furu8ma.onevolume

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.media.AudioManager

/**
 * Created by furuyamah on 16/04/11.
 */
class VolumeReceiver : BroadcastReceiver() {

    val RING_VOLUME = "RING_VOLUME"

    override fun onReceive(context: Context?, intent: Intent?) {
        if (context == null ) return
        if (intent == null) return

        val oneVolumeEnabled = getSP(context).getBoolean("ONE_VOLUME_ENABLED", false)
        if (!oneVolumeEnabled) {
            return
        }

        if (intent.action.equals("android.media.VOLUME_CHANGED_ACTION")) {
            changeAllVolume(context)
        }
    }

    fun changeAllVolume(context: Context?) {
        if (context == null) return

        val am = context.getSystemService(Context.AUDIO_SERVICE) as AudioManager

        val prevRingVolume = getSP(context).getFloat(RING_VOLUME, 0f)
        val currentRingVolume = am.getStreamVolume(AudioManager.STREAM_RING).toFloat()

        if(currentRingVolume == prevRingVolume){
            // not change ring volume
            return
        }
        getSP(context).edit().putFloat(RING_VOLUME, currentRingVolume).commit()

        val maxRingVolume = am.getStreamMaxVolume(AudioManager.STREAM_RING).toFloat()
        val maxMusicVolume = am.getStreamMaxVolume(AudioManager.STREAM_MUSIC).toFloat()
        val maxAlermVolume = am.getStreamMaxVolume(AudioManager.STREAM_ALARM).toFloat()

        val perVolume = (currentRingVolume / maxRingVolume)

        am.setStreamVolume(AudioManager.STREAM_MUSIC, (maxMusicVolume * perVolume).toInt(), 0)
        am.setStreamVolume(AudioManager.STREAM_ALARM, (maxAlermVolume * perVolume).toInt(), 0)
    }

    fun getSP(context: Context): SharedPreferences {
        return context.getSharedPreferences("ONE_VOLUME", Context.MODE_PRIVATE)
    }

}