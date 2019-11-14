package com.example.ryan.mineseeker;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;


import com.example.ryan.mineseeker.model.GamePlay;
import com.example.ryan.mineseeker.model.MessageFragment;
import com.example.ryan.mineseeker.model.MineField;

public class PlayActivity extends AppCompatActivity {

    private MineField playField;
    private GamePlay game;
    private Button buttons[][];
    private MediaPlayer mp;
    private MediaPlayer mp1;
    private MediaPlayer mp2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        mp = MediaPlayer.create(this, R.raw.clap);//sound fx ripped and modified by me
        mp1 = MediaPlayer.create(this, R.raw.yay);
        mp2 = MediaPlayer.create(this, R.raw.er);
        playField = MineField.getMinefield(PlayActivity.this);
        game = new GamePlay(playField);
        buttons = new Button[playField.getRows()][playField.getCols()];
        refreshGameInfo();
        playField.setupGame();
        populateBoard();
        setupGamesPlayed();
        setupHighscore();
    }

    private void setupHighscore() {
        TextView t1 = (TextView) findViewById(R.id.highscore);
        t1.setText(getString(R.string.high_score, playField.getHighscore(playField.getRows(), playField.getCols(), playField.getMines())));
    }

    private void setupGamesPlayed() {
        TextView t1 = (TextView) findViewById(R.id.gamesplayed);
        t1.setText(getString(R.string.games_played, playField.getGameTotal()));
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        playField.storeMineField(PlayActivity.this);
    }

    private void refreshGameInfo(){
        TextView scanview = (TextView) findViewById(R.id.scan_view);
        TextView mineview = (TextView) findViewById(R.id.board_desc);
        scanview.setText("# of scans used: " + game.getScansUsed());
        mineview.setText("Found "+ game.getFoundMines() + " of "+playField.getMines()+" Mines");
        if(game.checkGameProgress()){
            mp.start();
            //create window for winning
            android.support.v4.app.FragmentManager manager = getSupportFragmentManager();
            MessageFragment dialog = new MessageFragment();
            dialog.show(manager, "MessageDialog");

            Log.i("PlayActivity", "Showed dialog");
        }
    }
    private void populateBoard() {
        TableLayout table = (TableLayout) findViewById(R.id.tableForButtons);
        for(int row = 0; row < playField.getRows(); row++){
            TableRow tableRow = new TableRow(this);
            tableRow.setLayoutParams(new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.MATCH_PARENT,
                    1.0f));
            table.addView(tableRow);
            for(int col = 0; col < playField.getCols(); col++){
                final int FINAL_COL = col;
                final int FINAL_ROW = row;
                Button button = new Button(this);
                button.setLayoutParams(new TableRow.LayoutParams(
                        TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.MATCH_PARENT,
                        1.0f));
                tableRow.addView(button);
                buttons[row][col] = button;
                button.setPadding(0, 0,0,0);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        gridButtonClicked(FINAL_COL, FINAL_ROW);
                    }
                });
            }
        }
    }

    private void gridButtonClicked(int col, int row){

        Button btn = buttons[row][col];
        lockButtonSizes();
        if(game.getClickResult(col, row)==0){
            mp1.start();
            int newWidth = btn.getWidth();
            int newHeight = btn.getHeight();
            Bitmap originalBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.chaika);
            Bitmap scaledBitmap = Bitmap.createScaledBitmap(originalBitmap, newWidth, newHeight,true);
            Resources resource = getResources();
            btn.setBackground(new BitmapDrawable(resource, scaledBitmap));
        }
        else if(game.getClickResult(col, row)==1){
            btn.setText(playField.scanField(row,col));
        }
        else {
            mp2.start();
        }
        refreshScans();
        refreshGameInfo();
    }

    private void refreshScans() {
        for(int i = 0; i<playField.getRows(); i++) {
            for (int j = 0; j < playField.getCols(); j++) {
                if(game.checkIfRevealedScan(i, j)){
                    Button currentButton = buttons[i][j];
                    currentButton.setText(playField.scanField(i,j));
                }
            }
        }
    }

    private void lockButtonSizes() {
        for(int row = 0; row<playField.getRows(); row++){
            for(int col = 0; col<playField.getCols(); col++){
                Button button = buttons[row][col];

                int width = button.getWidth();
                button.setMinWidth(width);
                button.setMaxWidth(width);

                int height = button.getHeight();
                button.setMinHeight(height);
                button.setMaxHeight(height);

            }
        }
    }

}
