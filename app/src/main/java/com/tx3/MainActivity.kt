package com.tx3

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.random.Random

import com.google.android.gms.ads.MobileAds
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private var myGame = Game()
    private var state = 0
    private var ai = false
    private var ai1st = false
    private var end = false

    private fun reset(){
        myGame.reset()
        state = 0
        ai = false
		ai1st = false
        end = false
        val board = findViewById<TextView>(R.id.textView)
        board.setText("Tap to Start")
        val ctrlBtn = findViewById<Button>(R.id.control_btn)
        ctrlBtn.backgroundTintList = ContextCompat.getColorStateList(this, R.color.start_btn)
		ctrlBtn.setText("Start")
        var btn : Button
        btn = findViewById<Button>(R.id.btn1)
        btn.setText("")
        btn.backgroundTintList = ContextCompat.getColorStateList(this, R.color.btn_color)
        btn = findViewById<Button>(R.id.btn2)
        btn.setText("")
        btn.backgroundTintList = ContextCompat.getColorStateList(this, R.color.btn_color)
        btn = findViewById<Button>(R.id.btn3)
        btn.setText("")
        btn.backgroundTintList = ContextCompat.getColorStateList(this, R.color.btn_color)
        btn = findViewById<Button>(R.id.btn4)
        btn.setText("")
        btn.backgroundTintList = ContextCompat.getColorStateList(this, R.color.btn_color)
        btn = findViewById<Button>(R.id.btn5)
        btn.setText("")
        btn.backgroundTintList = ContextCompat.getColorStateList(this, R.color.btn_color)
        btn = findViewById<Button>(R.id.btn6)
        btn.setText("")
        btn.backgroundTintList = ContextCompat.getColorStateList(this, R.color.btn_color)
        btn = findViewById<Button>(R.id.btn7)
        btn.setText("")
        btn.backgroundTintList = ContextCompat.getColorStateList(this, R.color.btn_color)
        btn = findViewById<Button>(R.id.btn8)
        btn.setText("")
        btn.backgroundTintList = ContextCompat.getColorStateList(this, R.color.btn_color)
        btn = findViewById<Button>(R.id.btn9)
        btn.setText("")
        btn.backgroundTintList = ContextCompat.getColorStateList(this, R.color.btn_color)
    }

    fun aiMove(){
        if (myGame.grid[4] == ' '){
            gameMove(4, findViewById(R.id.btn5))
        }	// 1st Row
        else if (myGame.grid[0] == myGame.grid[1] && myGame.grid[0] != ' ' && myGame.grid[2] == ' '){
			gameMove(2, findViewById(R.id.btn3))
		}else if (myGame.grid[0] == myGame.grid[2] && myGame.grid[0] != ' ' && myGame.grid[1] == ' '){
			gameMove(1, findViewById(R.id.btn2))
		}else if (myGame.grid[1] == myGame.grid[2] && myGame.grid[1] != ' ' && myGame.grid[0] == ' '){
			gameMove(0, findViewById(R.id.btn1))
		}	// 2nd Row
		else if (myGame.grid[3] == myGame.grid[4] && myGame.grid[3] != ' ' && myGame.grid[5] == ' '){
			gameMove(5, findViewById(R.id.btn6))
		}else if (myGame.grid[3] == myGame.grid[5] && myGame.grid[3] != ' ' && myGame.grid[4] == ' '){
			gameMove(4, findViewById(R.id.btn5))
		}else if (myGame.grid[4] == myGame.grid[5] && myGame.grid[4] != ' ' && myGame.grid[3] == ' '){
			gameMove(3, findViewById(R.id.btn4))
		}	// 3rd Row
		else if (myGame.grid[6] == myGame.grid[7] && myGame.grid[6] != ' ' && myGame.grid[8] == ' '){
			gameMove(8, findViewById(R.id.btn9))
		}else if (myGame.grid[6] == myGame.grid[8] && myGame.grid[6] != ' ' && myGame.grid[7] == ' '){
			gameMove(7, findViewById(R.id.btn8))
		}else if (myGame.grid[7] == myGame.grid[8] && myGame.grid[7] != ' ' && myGame.grid[6] == ' '){
			gameMove(6, findViewById(R.id.btn7))
		}	// 1st column
		else if (myGame.grid[0] == myGame.grid[3] && myGame.grid[0] != ' ' && myGame.grid[6] == ' '){
			gameMove(6, findViewById(R.id.btn7))
		}else if (myGame.grid[0] == myGame.grid[6] && myGame.grid[0] != ' ' && myGame.grid[3] == ' '){
			gameMove(3, findViewById(R.id.btn4))
		}else if (myGame.grid[3] == myGame.grid[6] && myGame.grid[8] != ' ' && myGame.grid[0] == ' '){
			gameMove(0, findViewById(R.id.btn1))
		}	// 2nd Column
		else if (myGame.grid[1] == myGame.grid[4] && myGame.grid[1] != ' ' && myGame.grid[7] == ' '){
			gameMove(7, findViewById(R.id.btn8))
		}else if (myGame.grid[1] == myGame.grid[7] && myGame.grid[1] != ' ' && myGame.grid[4] == ' '){
			gameMove(4, findViewById(R.id.btn5))
		}else if (myGame.grid[4] == myGame.grid[7] && myGame.grid[4] != ' ' && myGame.grid[1] == ' '){
			gameMove(1, findViewById(R.id.btn2))
		}	// 3rd Column
		else if (myGame.grid[2] == myGame.grid[5] && myGame.grid[2] != ' ' && myGame.grid[8] == ' '){
			gameMove(8, findViewById(R.id.btn9))
		}else if (myGame.grid[2] == myGame.grid[8] && myGame.grid[2] != ' ' && myGame.grid[5] == ' '){
			gameMove(5, findViewById(R.id.btn6))
		}else if (myGame.grid[5] == myGame.grid[8] && myGame.grid[5] != ' ' && myGame.grid[2] == ' '){
			gameMove(2, findViewById(R.id.btn3))
		}	// Top-left to bottom-Right
		else if (myGame.grid[0] == myGame.grid[4] && myGame.grid[0] != ' ' && myGame.grid[8] == ' '){
			gameMove(8, findViewById(R.id.btn9))
		}else if (myGame.grid[0] == myGame.grid[8] && myGame.grid[0] != ' ' && myGame.grid[4] == ' '){
			gameMove(4, findViewById(R.id.btn5))
		}else if (myGame.grid[4] == myGame.grid[8] && myGame.grid[4] != ' ' && myGame.grid[0] == ' '){
			gameMove(0, findViewById(R.id.btn1))
		}	// Top-right to bottom-left
		else if (myGame.grid[2] == myGame.grid[4] && myGame.grid[2] != ' ' && myGame.grid[6] == ' '){
			gameMove(6, findViewById(R.id.btn7))
		}else if (myGame.grid[2] == myGame.grid[6] && myGame.grid[2] != ' ' && myGame.grid[4] == ' '){
			gameMove(4, findViewById(R.id.btn5))
		}else if (myGame.grid[4] == myGame.grid[6] && myGame.grid[0] != ' ' && myGame.grid[2] == ' '){
			gameMove(2, findViewById(R.id.btn3))
		}
		else{
			var num : Int
			var btn : Button
			while(true){
				num = Random.nextInt(8)
				if(myGame.grid[num] == ' '){
					if (num == 0) {btn = findViewById(R.id.btn1)}
					else if (num == 1) {btn = findViewById(R.id.btn2)}
					else if (num == 2) {btn = findViewById(R.id.btn3)}
					else if (num == 3) {btn = findViewById(R.id.btn4)}
					else if (num == 4) {btn = findViewById(R.id.btn5)}
					else if (num == 5) {btn = findViewById(R.id.btn6)}
					else if (num == 6) {btn = findViewById(R.id.btn7)}
					else if (num == 7) {btn = findViewById(R.id.btn8)}
					else {btn = findViewById(R.id.btn9)}
					gameMove(num, btn)
					break
				}
			}
		}
    }

    fun isGameEnd(): Boolean{			// Function for checking game state
        if(myGame.grid[0] == myGame.grid[1] && myGame.grid[0] == myGame.grid[2] && myGame.grid[0] != ' '){
            var btn1 = findViewById<Button>(R.id.btn1)
            var btn2 = findViewById<Button>(R.id.btn2)
            var btn3 = findViewById<Button>(R.id.btn3)
            btn1.backgroundTintList = ContextCompat.getColorStateList(this, R.color.btn_win)
            btn2.backgroundTintList = ContextCompat.getColorStateList(this, R.color.btn_win)
            btn3.backgroundTintList = ContextCompat.getColorStateList(this, R.color.btn_win)
            myGame.winner = myGame.grid[0].digitToInt()
            return true
        }else if(myGame.grid[3] == myGame.grid[4] && myGame.grid[3] == myGame.grid[5] && myGame.grid[3] != ' '){
            var btn1 = findViewById<Button>(R.id.btn4)
            var btn2 = findViewById<Button>(R.id.btn5)
            var btn3 = findViewById<Button>(R.id.btn6)
            btn1.backgroundTintList = ContextCompat.getColorStateList(this, R.color.btn_win)
            btn2.backgroundTintList = ContextCompat.getColorStateList(this, R.color.btn_win)
            btn3.backgroundTintList = ContextCompat.getColorStateList(this, R.color.btn_win)
            myGame.winner = myGame.grid[3].digitToInt()
            return true
        }else if(myGame.grid[6] == myGame.grid[7] && myGame.grid[6] == myGame.grid[8] && myGame.grid[6] != ' '){
            var btn1 = findViewById<Button>(R.id.btn7)
            var btn2 = findViewById<Button>(R.id.btn8)
            var btn3 = findViewById<Button>(R.id.btn9)
            btn1.backgroundTintList = ContextCompat.getColorStateList(this, R.color.btn_win)
            btn2.backgroundTintList = ContextCompat.getColorStateList(this, R.color.btn_win)
            btn3.backgroundTintList = ContextCompat.getColorStateList(this, R.color.btn_win)
            myGame.winner = myGame.grid[6].digitToInt()
            return true
        }else if(myGame.grid[0] == myGame.grid[3] && myGame.grid[0] == myGame.grid[6] && myGame.grid[0] != ' '){
            var btn1 = findViewById<Button>(R.id.btn1)
            var btn2 = findViewById<Button>(R.id.btn4)
            var btn3 = findViewById<Button>(R.id.btn7)
            btn1.backgroundTintList = ContextCompat.getColorStateList(this, R.color.btn_win)
            btn2.backgroundTintList = ContextCompat.getColorStateList(this, R.color.btn_win)
            btn3.backgroundTintList = ContextCompat.getColorStateList(this, R.color.btn_win)
            myGame.winner = myGame.grid[0].digitToInt()
            return true
        }else if(myGame.grid[1] == myGame.grid[4] && myGame.grid[1] == myGame.grid[7] && myGame.grid[1] != ' '){
            var btn1 = findViewById<Button>(R.id.btn2)
            var btn2 = findViewById<Button>(R.id.btn5)
            var btn3 = findViewById<Button>(R.id.btn8)
            btn1.backgroundTintList = ContextCompat.getColorStateList(this, R.color.btn_win)
            btn2.backgroundTintList = ContextCompat.getColorStateList(this, R.color.btn_win)
            btn3.backgroundTintList = ContextCompat.getColorStateList(this, R.color.btn_win)
            myGame.winner = myGame.grid[1].digitToInt()
            return true
        }else if(myGame.grid[2] == myGame.grid[5] && myGame.grid[2] == myGame.grid[8] && myGame.grid[2] != ' '){
            var btn1 = findViewById<Button>(R.id.btn3)
            var btn2 = findViewById<Button>(R.id.btn6)
            var btn3 = findViewById<Button>(R.id.btn9)
            btn1.backgroundTintList = ContextCompat.getColorStateList(this, R.color.btn_win)
            btn2.backgroundTintList = ContextCompat.getColorStateList(this, R.color.btn_win)
            btn3.backgroundTintList = ContextCompat.getColorStateList(this, R.color.btn_win)
            myGame.winner = myGame.grid[2].digitToInt()
            return true
        }else if(myGame.grid[0] == myGame.grid[4] && myGame.grid[0] == myGame.grid[8] && myGame.grid[0] != ' '){
            var btn1 = findViewById<Button>(R.id.btn1)
            var btn2 = findViewById<Button>(R.id.btn5)
            var btn3 = findViewById<Button>(R.id.btn9)
            btn1.backgroundTintList = ContextCompat.getColorStateList(this, R.color.btn_win)
            btn2.backgroundTintList = ContextCompat.getColorStateList(this, R.color.btn_win)
            btn3.backgroundTintList = ContextCompat.getColorStateList(this, R.color.btn_win)
            myGame.winner = myGame.grid[0].digitToInt()
            return true
        }else if(myGame.grid[2] == myGame.grid[4] && myGame.grid[2] == myGame.grid[6] && myGame.grid[2] != ' '){
            var btn1 = findViewById<Button>(R.id.btn3)
            var btn2 = findViewById<Button>(R.id.btn5)
            var btn3 = findViewById<Button>(R.id.btn7)
            btn1.backgroundTintList = ContextCompat.getColorStateList(this, R.color.btn_win)
            btn2.backgroundTintList = ContextCompat.getColorStateList(this, R.color.btn_win)
            btn3.backgroundTintList = ContextCompat.getColorStateList(this, R.color.btn_win)
            myGame.winner = myGame.grid[2].digitToInt()
            return true
        }else{
            return myGame.isDraw()
        }
    }

    fun dealState(){
        var ctrlBtn = findViewById<Button>(R.id.control_btn)
        var board = findViewById<TextView>(R.id.textView)
        if(state == 0 && !ai1st){
            ctrlBtn.setText("AI Move")
			if(ai){
				board.setText("Your turn")
			}else{
            	board.setText("2nd Player turn")
			}
            state = 1
        }else if(state == 1 || ai1st){
            ctrlBtn.setText("Reset")
            ctrlBtn.backgroundTintList = ContextCompat.getColorStateList(this, R.color.reset_btn)
			if(ai){
				board.setText("Your turn")
			}else{
            	board.setText("1st Player turn")
			}
            state += 1
        }else{
            if(myGame.turn == 1 && !ai){
                board.setText("1st Player turn")
            }else if(!ai){
                board.setText("2nd Player turn")
            }
        }
    }

    fun dealEnd(){
        end = isGameEnd()
        if (end){
            var board = findViewById<TextView>(R.id.textView)
            if(myGame.winner == 1){
                if(ai && !ai1st){
                    board.setText("You Won")
                }else if(ai && ai1st){
					board.setText("You Lose")
				}else{
                    board.setText("1st player won")
                }
            }else if(myGame.winner == 2){
                if(ai && ai1st){
                    board.setText("You Won")
                }else if(ai && !ai1st){
					board.setText("You Lose")
				}else {
                    board.setText("2nd player won")
                }
            }else{
                board.setText("Match draw")
            }
        }
    }

    fun gameMove(index:Int,btn:Button){
        if(!end){
            if(myGame.move(index)) {
                if (myGame.grid[index] == '1') {
                    btn.setText("O")
                } else {
                    btn.setText("X")
                }
                btn.backgroundTintList = ContextCompat.getColorStateList(this, R.color.btn_use)
                dealState()
            }
            dealEnd()
			if(!end && ai){
				if (myGame.turn == 1 && ai1st){
					aiMove()
					dealEnd()
				}else if(myGame.turn == 2 && !ai1st){
					aiMove()
					dealEnd()
				}
			}
        }
    }

    // Button Events
    fun move1(view: View){
        val btn = findViewById<Button>(R.id.btn1)
        gameMove(0, btn)
    }
    fun move2(view: View){
        val btn = findViewById<Button>(R.id.btn2)
        gameMove(1, btn)
    }
    fun move3(view: View){
        val btn = findViewById<Button>(R.id.btn3)
        gameMove(2, btn)
    }
    fun move4(view: View){
        val btn = findViewById<Button>(R.id.btn4)
        gameMove(3, btn)
    }
    fun move5(view: View){
        val btn = findViewById<Button>(R.id.btn5)
        gameMove(4, btn)
    }
    fun move6(view: View){
        val btn = findViewById<Button>(R.id.btn6)
        gameMove(5, btn)
    }
    fun move7(view: View){
        val btn = findViewById<Button>(R.id.btn7)
        gameMove(6, btn)
    }
    fun move8(view: View){
        val btn = findViewById<Button>(R.id.btn8)
        gameMove(7, btn)
    }
    fun move9(view: View){
        val btn = findViewById<Button>(R.id.btn9)
        gameMove(8, btn)
    }
    fun controlBtn(view: View){
		if (state == 0) {
			ai = true
			ai1st = true
			aiMove()
		}else if(state <= 1){
            ai = true
            aiMove()
        }else{
            reset()
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val backgroundScope = CoroutineScope(Dispatchers.IO)
        backgroundScope.launch {
            // Initialize the Google Mobile Ads SDK on a background thread.
            MobileAds.initialize(this@MainActivity) {}
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}