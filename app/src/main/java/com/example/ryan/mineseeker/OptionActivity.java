package com.example.ryan.mineseeker;


import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.ryan.mineseeker.model.MineField;

public class OptionActivity extends AppCompatActivity {
    private MineField playField;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        playField=MineField.getMinefield(OptionActivity.this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option);
        createMineAmountButtons();
        createRowAmountButtons();
        createColAmountButtons();
        setupDeleteGameButton();
        setupDeleteHighScores();
    }

    private void setupDeleteHighScores() {
        Button btn = (Button) findViewById(R.id.deletescores);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playField.deleteHighScores();
                Toast.makeText(OptionActivity.this, "High scores deleted", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setupDeleteGameButton() {
        Button btn = (Button) findViewById(R.id.delete_games);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playField.setGameTotal(0);
                Toast.makeText(OptionActivity.this, "Game Total Deleted", Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        playField.storeMineField(OptionActivity.this);
    }

    private void createMineAmountButtons() {
        RadioGroup group = (RadioGroup) findViewById(R.id.radio_group_mines);

        int[] numMines = getResources().getIntArray(R.array.num_mines);

        for(int i = 0; i< numMines.length;i++){
            final int numMine = numMines[i];
            RadioButton button = new RadioButton(this);
            button.setText(getString(R.string.num_mines, numMine));
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    playField.setMines(numMine);
                }
            });

            group.addView(button);
        }
    }
    private void createRowAmountButtons() {
        RadioGroup group = (RadioGroup) findViewById(R.id.radio_group_rows);

        int[] numRows = getResources().getIntArray(R.array.num_rows);

        for(int i = 0; i< numRows.length;i++){
            final int numRow = numRows[i];
            RadioButton button = new RadioButton(this);
            button.setText(getString(R.string.num_rows, numRow));
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    playField.setRows(numRow);
                }
            });

            group.addView(button);
        }
    }
    private void createColAmountButtons() {
        RadioGroup group = (RadioGroup) findViewById(R.id.radio_group_cols);

        int[] numCols = getResources().getIntArray(R.array.num_cols);

        for(int i = 0; i< numCols.length;i++){
            final int numCol = numCols[i];
            RadioButton button = new RadioButton(this);
            button.setText(getString(R.string.num_cols, numCol));
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    playField.setCols(numCol);
                }
            });

            group.addView(button);
        }
    }

}
