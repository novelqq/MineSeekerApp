package com.example.ryan.mineseeker.model;

/**
 * Created by ryan on 16/02/18.
 */

public class Box {
    private int row;
    private int col;
    private int isMine;
    private int isReveal;

    public Box(int _row, int _col, int _isMine){
        row = _row;
        col = _col;
        isMine = _isMine;
        isReveal = 0;
    }

    public int getIsReveal() {
        return isReveal;
    }

    public void setIsReveal(int isReveal) {
        this.isReveal = isReveal;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int getIsMine() {
        return isMine;
    }

    public void setIsMine(int mine) {
        isMine = mine;
    }
}
