package com.kost4n.baseball

import android.app.AlertDialog
import android.os.Bundle
import android.os.CountDownTimer
import android.text.Editable
import android.util.Log
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import com.kost4n.baseball.databinding.ActivityPlugBinding


class PlugActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPlugBinding

    private var correctAnswer = ""
    private var countAnswers = 0
    private var countCorrectAnswers = 0
    private val arrayQuestions = mapOf(
        R.drawable.arizona_diamondbacks to "arizona diamondbacks",
        R.drawable.atlanta_braves to "atlanta braves",
        R.drawable.baltimore_orioles to "baltimore orioles",
        R.drawable.boston_red_sox to "boston red sox",
        R.drawable.chicago_cubs to "chicago cubs",
        R.drawable.chicago_white_sox to "chicago white sox",
        R.drawable.cincinnati_reds to "cincinnati reds",
        R.drawable.cleveland_guardians to "cleveland guardians",
        R.drawable.colorado_rockies to "colorado rockies",
        R.drawable.detroit_tigers to "detroit tigers",
        R.drawable.houston_astros to "houston astros",
        R.drawable.los_angeles_angels to "los angeles angels",
        R.drawable.los_angeles_dodgers to "los angeles dodgers",
        R.drawable.miami_marlins to "miami marlins",
        R.drawable.minnesota_twins to "minnesota twins",
        R.drawable.new_york_mets to "new york mets",
        R.drawable.oakland_athletics to "oakland athletics",
        R.drawable.philadelphia_phillies to "philadelphia phillies",
        R.drawable.san_diego_padres to "san diego padres",
        R.drawable.seattle_mariners to "seattle mariners"
        )
    private lateinit var array: Array<Int>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlugBinding.inflate(layoutInflater)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(binding.root)


        array = arrayQuestions.keys.shuffled().toTypedArray()
        startGame()
        binding.buttonCheck.setOnClickListener {
            checkAnswer(binding.edittextAnswer.text.toString().toLowerCase())
            binding.edittextAnswer.text = Editable.Factory.getInstance().newEditable("")
        }
    }

    private fun checkAnswer(answer: String) {
        if (answer == correctAnswer)
            countCorrectAnswers++
        countAnswers++
        startGame()
    }

    private fun startGame() {
        if (countAnswers < arrayQuestions.size) {
            binding.image.setImageResource(array[countAnswers])
            correctAnswer = arrayQuestions[array[countAnswers]].toString()
            Log.d("PA", "-------------------$correctAnswer----------------------")
        } else {
            val title = if (countCorrectAnswers == 20) "You win!" else "You lost"
            val builder = AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage("Correct answers $countCorrectAnswers out of 20. Do you want to repeat it?")
                .setPositiveButton("Yes") { dialog, _ ->
                    dialog.dismiss()
                    val intent = intent
                    finish()
                    startActivity(intent)
                }
                .show()
        }
    }
}