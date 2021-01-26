package com.emmahogan.gedcourse.quiz

import android.content.Intent
import android.content.SharedPreferences
import android.content.res.ColorStateList
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.emmahogan.gedcourse.instruction.Lesson
import com.emmahogan.gedcourse.R
import java.util.*

class TakeQuiz : AppCompatActivity() {

    // Holds current lesson
    lateinit var lesson: Lesson

    // Used to access shared preferences for highscore
    val SHARED_PREFS: String = "sharedPrefs"
    val prefs: SharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE)

    // Keys for current unit and lesson numbers saved in shared prefs
    val KEY_CURR_UNIT: String = "KEY_CURR_UNIT"
    val KEY_CURR_LESSON: String = "KEY_CURR_LESSON"

    // All components in quiz layout
    lateinit var question_textview: TextView
    lateinit var score_textview: TextView
    lateinit var count_textview: TextView
    lateinit var rb_group: RadioGroup
    lateinit var rb_1: RadioButton
    lateinit var rb_2: RadioButton
    lateinit var rb_3: RadioButton
    lateinit var submit_btn: Button

    // To save default color for text, which will be changed to red or green depending on if the answer is correct
    lateinit var textColorDefRb: ColorStateList

    // Current question index
    var questionCounter: Int = 0

    // Total number of questions in quiz
    var questionTotal: Int = 0

    // Current question
    lateinit var currQuestion: MultipleChoiceQuestion

    // Current score
    var score: Int = 0

    // Whether current question has been answered
    var answered: Boolean = false

    // Variables needed to assist in getting quiz questions from DB
    lateinit var dbHelper: QuizDBHelper
    lateinit var questionsList: List<MultipleChoiceQuestion>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_take_quiz)

        // Get current unit and lesson numbers from shared preferences, or default 1.1
        var unit_num = prefs.getInt(KEY_CURR_UNIT, 1)
        var lesson_num = prefs.getInt(KEY_CURR_LESSON, 1)

        // Set current lesson
        lesson = Lesson(
            applicationContext,
            unit_num,
            lesson_num
        )

        question_textview = findViewById(R.id.quiz_question)
        score_textview = findViewById(R.id.quiz_score)
        count_textview = findViewById(R.id.quiz_question_count)
        rb_group = findViewById(R.id.radio_group)
        rb_1 = findViewById(R.id.radio_button1)
        rb_2 = findViewById(R.id.radio_button2)
        rb_3 = findViewById(R.id.radio_button3)
        submit_btn = findViewById(R.id.submit_answer_btn)

        // Get default text color from one of the radio buttons
        textColorDefRb = rb_1.textColors

        // Initialize database helper and put questions in question list var
        dbHelper = QuizDBHelper(applicationContext)
        questionsList = dbHelper.questions

        // Get the total number of questions
        questionTotal = questionsList.size

        // Shuffle questions so that they appear in a random order
        Collections.shuffle(questionsList)

        // Use external method to show the next question
        showNextQuestion();


        submit_btn.setOnClickListener {

            // If the question is not answered
            if(!answered) {

                // If any of the radio buttons are selected
                if(rb_1.isChecked || rb_2.isChecked || rb_3.isChecked) {

                    //Check if the answer is correct
                    checkAnswer();
                }
                // If no answer is selected, show toast messaage that an answer must be selected in order to submit
                else {
                    Toast.makeText(this@TakeQuiz, "An answer must be selected in order to submit.", Toast.LENGTH_SHORT).show()
                }
            } else {
                showNextQuestion()
            }
        }
    }

    /* Method to check the correctness of a confirmed answer */
    private fun checkAnswer() {
        answered = true

        // Get the selected option
        var selectedRB: RadioButton = findViewById(rb_group.checkedRadioButtonId)
        var selectedIndex: Int = rb_group.indexOfChild(selectedRB) + 1

        // If answer is correct
        if(selectedIndex == currQuestion.getAnswer_pos()) {

            // Increment score
            score++
            var newScoreText: String = "Score: " + score
            score_textview.text = newScoreText
        }

        showSolution();
    }

    /* Display solution of current question */
    private fun showSolution() {

        // Change text for all 3 options to red
        rb_1.setTextColor(Color.RED)
        rb_2.setTextColor(Color.RED)
        rb_3.setTextColor(Color.RED)

        // Set color of correct option to green
        when(currQuestion.getAnswer_pos()){
            1 -> {
                rb_1.setTextColor(Color.GREEN)
                question_textview.text = "Answer 1 is correct!"
            }
            2 -> {
                rb_2.setTextColor(Color.GREEN)
                question_textview.text = "Answer 2 is correct!"
            }
            3 -> {
                rb_3.setTextColor(Color.GREEN)
                question_textview.text = "Answer 3 is correct!"
            }
        }

        // If there are more questions, set button text to next
        if (questionCounter < questionTotal) {
            submit_btn.text = "Next Question"
        } else {
            submit_btn.text = "Finish"
        }
    }


    /* Displays the next question and it's associated options from the question list, unless there are no more questions */
    private fun showNextQuestion() {
        // Set text colors of radio buttons to the default
        rb_1.setTextColor(textColorDefRb)
        rb_2.setTextColor(textColorDefRb)
        rb_3.setTextColor(textColorDefRb)

        // Unselect all radiobuttons
        rb_group.clearCheck()

        // Check if there are more questions
        if(questionCounter < questionTotal) {

            // Set current question to the next in the question list
            currQuestion = questionsList.get(questionCounter)

            question_textview.setText(currQuestion.getQuestion())
            rb_1.setText(currQuestion.getOption1())
            rb_2.setText(currQuestion.getOption2())
            rb_3.setText(currQuestion.getOption3())

            // Increment question counters
            questionCounter++;
            count_textview.setText("Question: " + questionCounter + "/" + questionTotal)

            answered = false
            submit_btn.text = "Confirm"

        } else {
            endQuiz()
        }
    }

    /* Method to end quiz and start FinishQuiz activity, also sending the score and total number of questions */
    private fun endQuiz() {
        val intent = Intent(this@TakeQuiz, FinishQuiz::class.java)
        intent.putExtra("score", score)
        intent.putExtra("total", questionTotal)

        startActivity(intent)
    }
}