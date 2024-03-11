package com.advaitvedant.audioplayer

import android.content.Context
import android.media.AudioAttributes
import android.media.SoundPool
import android.os.Handler
import android.os.Looper
import androidx.annotation.RawRes
import dagger.hilt.android.qualifiers.ApplicationContext




class SoundManager(@ApplicationContext val context: Context){
    private val soundPool: SoundPool
    private val soundMap: MutableMap<Int, Int> = mutableMapOf()
    private val soundQueue: MutableList<Int> = mutableListOf()

    init {
        val audioAttributes = AudioAttributes.Builder()
            .setUsage(AudioAttributes.USAGE_GAME)
            .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
            .build()
        soundPool = SoundPool.Builder()
            .setMaxStreams(MAX_STREAMS)
            .setAudioAttributes(audioAttributes)
            .build()
    }

    fun preload(@RawRes soundResId: Int){
        val soundId  = soundPool.load(context, soundResId, 1)
        soundMap[soundResId] = soundId
    }

    fun enqueue(@RawRes soundResId: Int){
        soundQueue.add(soundResId)
        if (soundQueue.size == 1){
            playNext()
        }
    }

    private fun playNext(){
        if (soundQueue.isNotEmpty()){
            val soundResId = soundQueue.removeFirst()
            soundMap[soundResId]?.let { sound ->
                soundPool.play(sound, VOLUME, VOLUME, PRIORITY, LOOP, RATE)
            }
            if (soundQueue.isNotEmpty()){
                Handler(Looper.getMainLooper()).postDelayed({
                    playNext()
                }, DELAY)
            }
        }
    }
    companion object {
        private const val MAX_STREAMS = 1
        private const val VOLUME = 1f
        private const val PRIORITY = 1
        private const val LOOP = 0
        private const val RATE = 1f
        private const val DELAY = 500L
    }
}