package com.example.ryan.mineseeker.model;


import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by ryan on 18/02/18.
 */

public class GamePlay {

    private static int scansUsed;
    private static int foundMines;

    public static int getScansUsed() {
        return scansUsed;
    }

    public static int getFoundMines() {
        return foundMines;
    }

    private MineField playField;
    public GamePlay(MineField _playField){
        playField = _playField;
        scansUsed = 0;
        foundMines = 0;
    }

    public int getClickResult(int col, int row){
        int ret = -1;
        if(playField.getBox(row, col).getIsReveal()==0){
            if(playField.getBox(row, col).getIsMine()==1){
                foundMines++;
                ret = 0;
            }
            else{
                scansUsed++;
                ret = 1;
            }
            playField.getBox(row, col).setIsReveal(1);
        }
        else{
            if(playField.getBox(row, col).getIsMine()==1){
                playField.getBox(row, col).setIsMine(0);
                ret = 1;
            }
        }
        return ret;
    }

    public boolean checkIfRevealedScan(int i, int j){
        return (playField.getBox(i, j).getIsReveal()==1 && playField.getBox(i, j).getIsMine()==0);
    }
    public boolean checkGameProgress(){
        if(playField.getMines()==foundMines){
            playField.setGameTotal(playField.getGameTotal() + 1);
            playField.setHighscore(playField.getRows(), playField.getCols(), playField.getMines(), scansUsed);
            //check score, set high scores etc
            return true;
        }
        else{
            return false;
        }
    }
}
