package com.emmahogan.gedcourse

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView

class list_articles : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_articles)

        displayArticles()
    }

    fun displayArticles() {

        val articles = ArrayList<Article>()
        articles.add(Article("10 GED Math Test Tips", R.drawable.study1, "10 Tips for the GED Math Test from bestgedclasses.org", "1. Read all the data carefully before solving a problem\n" +
                "\n" +
                "On the GED Math test, there’s quite some emphasis on Data Analysis topics. You may come across questions that have tables, charts, or graphs.\n" +
                "\n" +
                "Read the question carefully twice! Don’t just jump straight to a question. Take a moment to really understand the question and the data presented before trying to answer the question.\n" +
                "\n" +
                "2. Translate word problems cautiously\n" +
                "\n" +
                "When taking the GED Math subtest, be sure to understand the mathematical concepts that are underlying the question. Translate a word problem carefully and cautiously, especially because they are very prevalent on the math test.\n" +
                "\n" +
                "One or two different words can change the meaning radically. There’s no need to rush these challenging math questions, even when the math seems pretty obvious to you.\n" +
                "\n" +
                "3. Don’t see your answer option? Rewrite it\n" +
                "\n" +
                "Sometimes, the GED math answer options express correct answers not in their simplest form, or as expressions. You may know the correct answer, even though it doesn’t show in the answer options.\n" +
                "\n" +
                "So when you know you’ve got the answer right and don’t see that option, try to rewrite it. If you express it in a different way, it may match one of the answer options. For instance: \$50 equals \$15 + \$35, and 52 equals 25.\n" +
                "\n" +
                "4. Practice using a calculator\n" +
                "\n" +
                "On the GED math test, you’re not expected to memorize complex formulas or to do complex arithmetic calculations in your head.\n" +
                "\n" +
                "In part two of the GED math section, you are allowed to use a calculator. You can bring your own TI-30XS scientific calculator, but there will also be an on-screen calculator provided.\n" +
                "\n" +
                "So get familiar with the on-screen GED calculator or the TI-30XS well before taking the actual test. This will help you ace the math test! Learn how to deal with basic functions like exponents and square roots on the calculator.\n" +
                "\n" +
                "5. Time yourself\n" +
                "\n" +
                "The GED subtests are all timed tests. The pressure of taking a test within a limited time frame is for many math test-takers a source of stress and anxiety.\n" +
                "\n" +
                "You can very well manage this phenomenon by taking multiple timed practice tests. You can check your results, learn what fields need improvement, and gain self-confidence to complete the GED math test within the given amount of time.\n" +
                "\n" +
                "6. Don’t waste time\n" +
                "\n" +
                "You shouldn’t waste much time on questions that you don’t know the answer to immediately or that you are not sure about.\n" +
                "\n" +
                "If you have no clue about the correct answer option, skip that question and move on to the next problem. Many test-takers feel they wasted too much time dwelling on questions they didn’t know the answer to.\n" +
                "\n" +
                "When you feel you’ll need lots of time to figure out an answer, flag that question and move ahead to those questions that’ll take less time. You may always come back later and try to find the right answer.\n" +
                "\n" +
                "When you’ve finished the test and have some time left, go over these questions once more and perhaps the other questions may have put you on the right track.\n" +
                "\n" +
                "Also, eliminate those answers that you think are definitely wrong and then come up with an educated guess. It’s probably easier to choose from two than from four options, right?\n" +
                "\n" +
                "7. Eliminate obviously wrong answer options\n" +
                "\n" +
                "As stated in tip #6, elimination obviously wrong answer choices is a very useful strategy. Oftentimes, one or two of the answer options on the GED math section do not make any sense at all.\n" +
                "\n" +
                "They may not refer to the provided data or be totally irrelevant. When you eliminate these obviously wrong answers, you’ll be left with a smaller number of answers which will make an educated guess considerably easier.\n" +
                "\n" +
                "8. Simplicity is the best\n" +
                "\n" +
                "Oftentimes, there’s more than one way of working around a problem. You may solve a problem, even with a given function, differently.\n" +
                "\n" +
                "If you happen to see another way for solving the problem, just use it, but don’t forget to check if your answer is correct. When dealing with combined figures, remember that the rule to solve it is to do it as easily as you possibly can. The answer is always the same, regardless of how you’ll break the figure down.\n" +
                "\n" +
                "9. Remember the Exponent Rules\n" +
                "\n" +
                "When multiplying two terms with identical bases, you simply add the exponents. When dividing two terms with identical bases, you simply subtract the denominator’s exponent from the numerator’s exponent.\n" +
                "\n" +
                "When a parenthesis separates two exponents, you can simply multiply the exponents. You can try to simplify an exponent by rewriting a number in terms of its exponent. For instance, 27 equals 3^3.\n" +
                "\n" +
                "10. Trust your first answer\n" +
                "\n" +
                "When you’ve read a question carefully twice, go to the answers options and read these also carefully twice. Then, when possible, choose your best answer option.\n" +
                "\n" +
                "If, in the end, you’ll have some time left to review your answers, don’t change your answers unless you’ll come across some obvious error that you’re absolutely sure about. Keep in mind that usually, your first answer is the right answer!"))

        articles.add(Article("Format of the GED Math Test", R.drawable.study2, "Information about the format of the GED Math test found on bestgedclasses.org.", "On the GED Math test, you will find various question types:\n" +
                "\n" +
                "Multiple-choice – this is the question type you’ll see most. You’ll have to choose the correct answer or the best answer from 4 or 5 options.\n" +
                "\n" +
                "Multiple-select – with multiple-select questions, you need to choose all correct answers from several options. Instead of having just 1 correct answer option, there can be more correct answer choices.\n" +
                "\n" +
                "Draggable – this type of question includes “draggable” answer options. You can click and hold an option and drag it to a target place on your screen. There could be more than one target place.\n" +
                "\n" +
                "Hot-spot – this is like draggable answers, but you’ll have to select just 1 area, the “hot-spot”.\n" +
                "\n" +
                "Fill-in-the-blank – with this sort of question, you need to type your answer into an empty space, either after the question or in a sentence. On the Math subtest, the answer is generally numerical but you may also be asked to write a short phrase or a word.\n" +
                "\n" +
                "Matching – with matching questions, you’ll have to choose the answer option that’s matching a piece of information. You can be asked, for example, to read information and then decide whether that information is true or false.\n" +
                "\n" +
                "On the GED Math subtest, you’ll be handed out a formula sheet that you can use during your test. So there’s no need to memorizes all sorts of complex formulas.\n" +
                "\n" +
                "You may not need all those formulas, and you may also not require a formula for all of the questions. So train yourself in knowing what formula is used to solve what problem, and when no formula is needed at all."))

        val adapter = ArticleAdapter(this@list_articles, articles)

        val list = findViewById<ListView>(R.id.articles_list)

        list.adapter = adapter

        list.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            val itemData = adapter.getItem(position)

            val intent = Intent(this@list_articles, read_article::class.java)
            intent.putExtra("title", itemData?.title)
            intent.putExtra("subtitle", itemData?.subtitle)
            intent.putExtra("content", itemData?.content)
            intent.putExtra("header_image", itemData?.header_image)

            startActivity(intent)
        }
    }
}