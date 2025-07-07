package com.adamweb.diceroller

import android.media.MediaPlayer
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.adamweb.diceroller.databinding.ActivityMainBinding
import kotlin.random.Random
import kotlin.random.nextInt

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var rollSound : MediaPlayer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        rollSound = MediaPlayer.create(this, R.raw.dice_roller_sound)
        binding.myBtn.setOnClickListener {
            rollSound.start()
            val diceRoll = Random.nextInt(1..6)
            val diceImages = when (diceRoll){
                1 -> R.drawable.dice_six_faces_one
                2 -> R.drawable.dice_six_faces_two
                3 -> R.drawable.dice_six_faces_three
                4 -> R.drawable.dice_six_faces_four
                5 -> R.drawable.dice_six_faces_five
                else -> R.drawable.dice_six_faces_six
            }
            binding.myImg.setImageResource(diceImages)
            binding.myText.text = "NO. Of Face(s): $diceRoll"
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        rollSound.release()
    }
}