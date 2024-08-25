package com.tx3

class Game {
    var turn=1;     // Turn decides which player makes move
    var grid = arrayOf(' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ') // Grid is the main game area
	var winner = 0

    fun move(index:Int): Boolean{   // Function for making a move
        if(grid[index] == ' '){
            if(turn == 1){
                grid[index] = '1'
                turn = 2
            }else{
                grid[index] = '2'
                turn = 1
            }
            return true
        }else{
            return false
        }
    }

    fun isDraw() : Boolean{         // Checks is match draw
        for (i in grid){
            if(i == ' '){
                return false
            }
        }
        return true
    }

    fun reset(){        // Reset
        turn = 1
        winner = 0
        var i =0;
        while(i<9){
            grid[i] = ' '
            ++i
        }
    }
}