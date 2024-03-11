package com.advaitvedant.audioplayer

import android.content.Context
import android.media.AudioAttributes
import android.media.SoundPool
import android.os.Handler
import android.os.Looper
import androidx.annotation.RawRes
import dagger.hilt.android.qualifiers.ApplicationContext




class SoundPlayer(@ApplicationContext val context: Context){
    private val soundPool: SoundPool
    private val soundMap: MutableMap<String, Int> = mutableMapOf()
    private val soundQueue: MutableList<String> = mutableListOf()

    init {
        val audioAttributes = AudioAttributes.Builder()
            .setUsage(AudioAttributes.USAGE_GAME)
            .setContentType(AudioAttributes.CONTENT_TYPE_SPEECH)
            .build()
        soundPool = SoundPool.Builder()
            .setMaxStreams(MAX_STREAMS)
            .setAudioAttributes(audioAttributes)
            .build()
    }

    fun preload(@RawRes soundResId: Int){
        val soundId  = soundPool.load(context, soundResId, PRIORITY)
        soundMap[soundResId.toString()] = soundId
    }
    fun preload(file: String){
        val soundId = soundPool.load(file, PRIORITY)
        soundMap[file] = soundId
    }

    fun enqueue(@RawRes soundResId: Int){
        enqueue(soundResId.toString())
    }
    fun enqueue(file: String){
        soundQueue.add(file)
        if (soundQueue.size == 1){
            playNext()
        }
    }

    private fun playNext(){
        if (soundQueue.isNotEmpty()){
            val soundId = soundQueue.removeFirst()
            soundMap[soundId]?.let { sound ->
                soundPool.play(sound, VOLUME, VOLUME, PRIORITY, LOOP, RATE)
            }
            if (soundQueue.isNotEmpty()){
                Handler(Looper.getMainLooper()).postDelayed({
                    playNext()
                }, DELAY)
            }
        }
    }

    fun stop(){
        soundPool.autoPause()
        soundQueue.clear()
    }
    fun release(){
        soundPool.release()
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