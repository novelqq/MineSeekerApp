package com.example.ryan.mineseeker.model;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by ryan on 14/02/18.
 */

public class MineField {
    private int rows = 4;
    private int cols = 6;
    private int mines = 6;
    private int gameTotal=0;
    private int highscores[][][] = new int[3][3][4];
    private Box Field[][];
    

    private static MineField instance;

    private MineField(){}

    private static MineField getInstance(){
        if(instance == null){
            instance = new MineField();
        }
        return instance;
    }
    private int getBoxMine(int row, int col){
        return Field[row][col].getIsMine();
    }

    public Box getBox(int row, int col){
        return Field[row][col];
    }

    public int getMines() {
        return mines;
    }

    public void setMines(int mines) {
        this.mines = mines;
    }

    public int getCols() {
        return cols;
    }

    public void setCols(int cols) {
        this.cols = cols;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int numRows) {
        this.rows = numRows;
    }

    public void setupGame(){
        Field = new Box[rows][cols];
        List<Box> tileList = new ArrayList<>();
        for(int i = 0; i<rows;i++) {
            for(int j = 0; j<cols; j++) {
                Field[i][j] = new Box(j, i, 0);
                tileList.add(Field[i][j]);
            }
        }
        java.util.Collections.shuffle(tileList);

        for(int i = 0; i < mines; i++){
            tileList.get(i).setIsMine(1);
        }
    }

    public String scanField(int row, int col) {
        int countx = 0, county = 0;
        for(int i = 0; i<rows; i++){
            if(i!=row && this.getBoxMine(i,col)==1 && getBox(i, col).getIsReveal()==0){
                county++;
            }
        }
        for(int j = 0; j<cols; j++){
            if(j!=col && this.getBoxMine(row, j)==1 && getBox(row, j).getIsReveal()==0){
                countx++;
            }
        }
        countx+=county;
        return ""+ countx;
    }


    public int getHighscore(int row, int col, int numMines) {
        int i = -1, j = -1, k = -1;
        if(row==4){
            i=0;
        }
        else if(row==5){
            i=1;
        }
        else if(row==6){
            i=2;
        }
        if(col==6){
            j=0;
        }
        else if(col==10){
            j=1;
        }
        else if(col==15){
            j=2;
        }
        if(numMines==6){
            k=0;
        }
        else if(numMines==10){
            k=1;
        }
        else if(numMines==15){
            k=2;
        }
        else if(numMines==20){
            k = 3;
        }

        return highscores[i][j][k];
    }

    public void setHighscore(int row, int col, int numMines, int score) {
        int i = -1, j = -1, k = -1;
        if(row==4){
            i=0;
        }
        else if(row==5){
            i=1;
        }
        else if(row==6){
            i=2;
        }
        if(col==6){
            j=0;
        }
        else if(col==10){
            j=1;
        }
        else if(col==15){
            j=2;
        }
        if(numMines==6){
            k=0;
        }
        else if(numMines==10){
            k=1;
        }
        else if(numMines==15){
            k=2;
        }
        else if(numMines==20){
            k = 3;
        }

        if(highscores[i][j][k]==0) {
            highscores[i][j][k] = score;
        }
        if(highscores[i][j][k]!=0 && score<highscores[i][j][k]){
            highscores[i][j][k] = score;
        }
    }
    public void storeMineField(Context context){
        SharedPreferences prefs = context.getSharedPreferences("MineSeeker", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(this); // myObject - instance of MyObject
        editor.putString("MineField", json);
        editor.commit();
    }
    public static MineField getMinefield(Context context){
        SharedPreferences prefs = context.getSharedPreferences("MineSeeker", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        String json = prefs.getString("MineField", "");
        if(json==""){
            return MineField.getInstance();
        }
        else {
            return gson.fromJson(json, MineField.class);
        }
    }


    public void setGameTotal(int gameTotal) {
        this.gameTotal = gameTotal;
    }

    public void deleteHighScores() {
        highscores = new int[3][3][4];
    }

    public int getGameTotal() {
        return gameTotal;
    }

}
