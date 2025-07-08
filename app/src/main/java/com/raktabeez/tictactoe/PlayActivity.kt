package com.raktabeez.tictactoe

import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.WindowManager
import android.widget.ImageButton
import com.raktabeez.tictactoe.databinding.ActivityPlayBinding

class PlayActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityPlayBinding.inflate(layoutInflater)
    }
    private lateinit var currentPlayer:String
    private  var drawList=ArrayList<Int>()
    private  var celled=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        currentPlayer=intent.getStringExtra("first")!!
        if(currentPlayer=="o"){
            binding.yourTurn.setImageResource(R.drawable.o_shape)
        }
        if (Build.VERSION.SDK_INT >= 21) {
            val window = this.window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.statusBarColor = this.resources.getColor(R.color.light_grey)
        }

        binding.restart.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        }
        binding.drawButton.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        }


    }

    fun clickMe(view: View) {
        val selectedButton =view as ImageButton

        when(selectedButton.id){
            R.id.imageButton1->{celled=1; binding.imageButton1.isClickable=false}
            R.id.imageButton2->{celled=2; binding.imageButton2.isClickable=false}
            R.id.imageButton3->{celled=3; binding.imageButton3.isClickable=false}
            R.id.imageButton4->{celled=4; binding.imageButton4.isClickable=false}
            R.id.imageButton5->{celled=5; binding.imageButton5.isClickable=false}
            R.id.imageButton6->{celled=6; binding.imageButton6.isClickable=false}
            R.id.imageButton7->{celled=7; binding.imageButton7.isClickable=false}
            R.id.imageButton8->{celled=8; binding.imageButton8.isClickable=false}
            R.id.imageButton9->{celled=9; binding.imageButton9.isClickable=false}
        }

        drawList.add(celled)
        play(celled,selectedButton)
        var win =winner()
        if (win==false) {
            draw()

        }
    }

    private fun draw() {
        if(drawList.contains(1) && drawList.contains(2) && drawList.contains(3) && drawList.contains(4) && drawList.contains(5) && drawList.contains(6) && drawList.contains(7) && drawList.contains(8) && drawList.contains(9))
        {
            binding.drawLayout.visibility= View.VISIBLE

        }
    }

    private fun winner() : Boolean{
        var winner="none"
        //For Player O
        //For Column
        if (playerO.contains(1) && playerO.contains(2) && playerO.contains(3)){
            binding.row1.visibility= View.VISIBLE
            winner="o"
        }else if (playerO.contains(4) && playerO.contains(5) && playerO.contains(6)){
            binding.row2.visibility= View.VISIBLE
            winner="o"
        }else if (playerO.contains(7) && playerO.contains(8) && playerO.contains(9)){
            binding.row3.visibility= View.VISIBLE
            winner="o"
        }
        //For Column
        else if (playerO.contains(1) && playerO.contains(4) && playerO.contains(7)){
            binding.column1.visibility= View.VISIBLE
            winner="o"
        }else if (playerO.contains(2) && playerO.contains(5) && playerO.contains(8)){
            binding.column2.visibility= View.VISIBLE
            winner="o"
        }else if (playerO.contains(3) && playerO.contains(6) && playerO.contains(9)){
            binding.column3.visibility= View.VISIBLE
            winner="o"
        }
        //For Diagonal
        else if (playerO.contains(1) && playerO.contains(5) && playerO.contains(9)){
            binding.diagonal1.visibility= View.VISIBLE
            winner="o"
        }else if (playerO.contains(3) && playerO.contains(5) && playerO.contains(7)){
            binding.diagonal2.visibility= View.VISIBLE
            winner="o"
        }

        //For Player X
        //For Column
        if (playerX.contains(1) && playerX.contains(2) && playerX.contains(3)){
            binding.row1.visibility= View.VISIBLE
            winner="x"
        }else if (playerX.contains(4) && playerX.contains(5) && playerX.contains(6)){
            binding.row2.visibility= View.VISIBLE
            winner="x"
        }else if (playerX.contains(7) && playerX.contains(8) && playerX.contains(9)){
            binding.row3.visibility= View.VISIBLE
            winner="x"
        }
        //For Column
        else if (playerX.contains(1) && playerX.contains(4) && playerX.contains(7)){
            binding.column1.visibility= View.VISIBLE
            winner="x"
        }else if (playerX.contains(2) && playerX.contains(5) && playerX.contains(8)){
            binding.column2.visibility= View.VISIBLE
            winner="x"
        }else if (playerX.contains(3) && playerX.contains(6) && playerX.contains(9)){
            binding.column3.visibility= View.VISIBLE
            winner="x"
        }
        //For Diagonal
        else if (playerX.contains(1) && playerX.contains(5) && playerX.contains(9)){
            binding.diagonal1.visibility= View.VISIBLE
            winner="x"
        }else if (playerX.contains(3) && playerX.contains(5) && playerX.contains(7)){
            binding.diagonal2.visibility= View.VISIBLE
            winner="x"
        }

        if(winner == "o"){
            disableButtons()
            Handler(Looper.getMainLooper()).postDelayed({
                binding.playLayout.visibility= View.INVISIBLE
                binding.imageView4.setImageResource(R.drawable.background)
                binding.winningLayout.visibility= View.VISIBLE
                binding.rootLayout.background= getDrawable(R.drawable.win_background)
            }, 700)
            return true

        }else if (winner=="x"){
            disableButtons()
            Handler(Looper.getMainLooper()).postDelayed({
                binding.winningLayout.visibility= View.VISIBLE
                binding.imageView4.setImageResource(R.drawable.background)
                binding.wintext.setTextColor(Color.parseColor("#f77181"))
                binding.restart.setTextColor(Color.parseColor("#f77181"))
                binding.imageView5.setImageResource(R.drawable.x_shape)
                binding.wintext.text="CROSS WINS"
                binding.playLayout.visibility= View.INVISIBLE

                binding.rootLayout.background= getDrawable(R.drawable.win_background)
            }, 700)
            return true

        }
        return false
    }

    private fun disableButtons() {
        binding.imageButton1.isClickable=false
        binding.imageButton2.isClickable=false
        binding.imageButton3.isClickable=false
        binding.imageButton4.isClickable=false
        binding.imageButton5.isClickable=false
        binding.imageButton6.isClickable=false
        binding.imageButton7.isClickable=false
        binding.imageButton8.isClickable=false
        binding.imageButton9.isClickable=false
    }

    var playerX=ArrayList<Int>()
    var playerO=ArrayList<Int>()

    private fun play(celled: Int, selectedButton: ImageButton) {

        if(currentPlayer=="x"){
            selectedButton.setImageResource(R.drawable.x_shape)

            playerX.add(celled)
            binding.yourTurn.setImageResource(R.drawable.o_shape)
            currentPlayer="o"
        }else if(currentPlayer=="o"){


            playerO.add(celled)
            selectedButton.setImageResource(R.drawable.o_shape)
            binding.yourTurn.setImageResource(R.drawable.x_shape)
            currentPlayer="x"
        }

    }
}