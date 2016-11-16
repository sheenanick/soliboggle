package com.example.guest.boggle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import butterknife.Bind;
import butterknife.ButterKnife;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.playButton) Button mPlayButton;
    @Bind(R.id.submitWordButton) Button mSubmitWordButton;
    @Bind(R.id.gameGridView) GridView mGameGridView;
    @Bind(R.id.timerBar) ProgressBar mTimerBar;
    @Bind(R.id.wordInputTextView) TextView mWordInputTextView;
    @Bind(R.id.scoreTextView) TextView mScoreTextView;

    Random random = new Random();
    String word = "";
    int score = 0;

    String[][] diceArray = new String[][] {
            {"R", "I", "F", "O", "B", "X"},
            {"I", "F", "E", "H", "E", "Y"},
            {"D", "E", "N", "O", "W", "S"},
            {"U", "T", "O", "K", "N", "D"},
            {"H", "M", "S", "R", "A", "O"},
            {"L", "U", "P", "E", "T", "S"},
            {"A", "C", "I", "T", "O", "A"},
            {"Y", "L", "G", "K", "U", "E"},
            {"QU", "B", "M", "J", "O", "A"},
            {"E", "H", "I", "S", "P", "N"},
            {"V", "E", "T", "I", "G", "N"},
            {"B", "A", "L", "I", "Y", "T"},
            {"E", "Z", "A", "V", "N", "D"},
            {"R", "A", "L", "E", "S", "C"},
            {"U", "W", "I", "L", "R", "G"},
            {"P", "A", "C", "E", "M", "D"},
    };

    String[] thisRoundLetters = new String[16];
    List<String> thisRoundLettersArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        ButterKnife.bind(this);

        mPlayButton.setOnClickListener(this);
        mSubmitWordButton.setOnClickListener(this);

        for(int i = 0; i < thisRoundLetters.length; i++) {
            thisRoundLetters[i] = diceArray[i][rollD6()];
        }

        thisRoundLettersArrayList = Arrays.asList(thisRoundLetters);
        Collections.shuffle(thisRoundLettersArrayList, random);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, thisRoundLetters);
        mGameGridView.setAdapter(adapter);

        mGameGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String value = (String)parent.getItemAtPosition(position);
                word += value;
                mWordInputTextView.setText(word);
                Log.d("GameActivity", word);
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v == mPlayButton) {
            Log.d("GameActivity", "Play!");
        } else if (v == mSubmitWordButton) {
            if (checkWord(word)){
                scoreWord(word);
            }
            Log.d("GameActivity", mWordInputTextView.getText().toString());
        }
    }

    public boolean checkWord(String word) {
        return !word.equals("");
    }

    public int scoreWord(String word) {
        score = word.length();
        String displayScore = "Score: " + score;
        mScoreTextView.setText(displayScore);
        return score;
    }

    public int rollD6() {
        return random.nextInt(6);
    }
}
