//***********************************************************************************************
// App Name: MatchCards
// Authors: Christopher Farfan (101067074) & Esaac Ahn (100836038)
// Date: March 11th, 2018
// Midterm submission.
//
// Description:
// A simple game of memory that requires the user to match cards against a time limit
//
//  Revision History:
//  Can be found on https://github.com/bluCraze/MatchCards/commits/master
//***********************************************************************************************

package com.example.tech.midterm_esaacahnandchristopherfarfan;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.media.Image;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends Activity {

    public ImageButton card11ImgBtn, card12ImgBtn, card13ImgBtn, card21ImgBtn, card22ImgBtn,
            card23ImgBtn, card31ImgBtn, card32ImgBtn, card33ImgBtn;

    public TextView gameMessageText, gameTimeLeftText, gameScoreText;

    public ImageButton firstCard;

    public int score, time;

    public static int[] cardImages = {R.drawable.cardback, R.drawable.card_1c,R.drawable.card_1d,
            R.drawable.card_1h, R.drawable.card_1s, R.drawable.card_2c, R.drawable.card_2d,
            R.drawable.card_2h, R.drawable.card_2s, R.drawable.card_3c, R.drawable.card_3d,
            R.drawable.card_3h, R.drawable.card_3s, R.drawable.card_4c, R.drawable.card_4d,
            R.drawable.card_4h, R.drawable.card_4s, R.drawable.card_5c, R.drawable.card_5d,
            R.drawable.card_5h, R.drawable.card_5s, R.drawable.card_6c, R.drawable.card_6d,
            R.drawable.card_6h, R.drawable.card_6s, R.drawable.card_7c, R.drawable.card_7d,
            R.drawable.card_7h, R.drawable.card_7s, R.drawable.card_8c, R.drawable.card_8d,
            R.drawable.card_8h, R.drawable.card_8s, R.drawable.card_9c, R.drawable.card_9d,
            R.drawable.card_9h, R.drawable.card_9s, R.drawable.card_10c, R.drawable.card_10d,
            R.drawable.card_10h, R.drawable.card_10s, R.drawable.card_11c, R.drawable.card_11d,
            R.drawable.card_11h, R.drawable.card_11s, R.drawable.card_12c, R.drawable.card_12d,
            R.drawable.card_12h, R.drawable.card_12s, R.drawable.card_13c, R.drawable.card_13d,
            R.drawable.card_13h, R.drawable.card_13s};

    private int cardMatrix[][] = new int[3][3];
    private boolean flippedCards[][] = new boolean[3][3];
    private int numOfFlippedCards = 0;
    private int cardsToCompare[] = new int[2];

    //Sets up the game board by picking random cards and positions
    void setUpCardGame(){
        // Random number between 1-52 (inclusive) used to setup card pairs
        int selectedCards[] = new int[9];
        boolean hasBeenSelected[] = new boolean[9];
        int leftoverCard = (int) Math.floor((Math.random() * 52)+ 1);
        score = 0;
        time = 30;

        for (int x = 0; x < 7; x = x + 2){
            selectedCards[x] = (int) Math.floor((Math.random() * 52) + 1);
            selectedCards[x+1] = selectedCards[x];
        }

        selectedCards[8] = leftoverCard;

        for (int i = 0; i < 3 ; i++){
            for (int j = 0; j < 3; j++){
                int randSelection = (int) Math.floor((Math.random() * 9));
                if (hasBeenSelected[randSelection] == false){
                    cardMatrix[i][j] = cardImages[selectedCards[randSelection]];
                    hasBeenSelected[randSelection] = true;
                } else {
                    int nextAvailableCard = 0;
                    for (int k = 0; k < 9; k++){
                        if(hasBeenSelected[k] == false){
                            nextAvailableCard = k;
                            hasBeenSelected[k] = true;
                            break;
                        }
                    }
                    cardMatrix[i][j] = cardImages[selectedCards[nextAvailableCard]];
                }

            }
        }

        new CountDownTimer(30000, 1000) {

            public void onTick(long millisUntilFinished) {
                gameTimeLeftText.setText(millisUntilFinished / 1000+" sec");
            }

            public void onFinish() {
                gameEnd ();
            }
        }.start();





    }

    //Flips a card by changing the image to the corresponding card in the matrix
    void flipCard(final int row, final int col, final ImageButton card){
        card.setImageResource(cardMatrix[row][col]);
        flippedCards[row][col] = true;
        numOfFlippedCards++;
        if (numOfFlippedCards == 1){
            cardsToCompare[0] = cardMatrix[row][col];
            firstCard = card;
            gameMessageText.setText("Now pick another card");
        } else if (numOfFlippedCards == 2){
            cardsToCompare[1] = cardMatrix[row][col];
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    checkMatch(row, col, card);
                }
            }, 500);

        }

    }
    //Unflips a card by changing the image back to the cardback
    void unflipCard(int row, int col , ImageButton card){
        card.setImageResource(cardImages[0]);
        flippedCards[row][col] = false;
        numOfFlippedCards--;
    }

    //updates score when two cards are matched
    void updateScore() {
        score++;
        gameScoreText.setText(score + " ");
    }

    //Checks if the two cards clicked are matching
    void checkMatch(int rowOfSecondCard, int colOfSecondCard, ImageButton secondCard){
        if (cardsToCompare[0] == cardsToCompare[1]){
            gameMessageText.setText("Right!");
            firstCard.setVisibility(View.INVISIBLE);
            secondCard.setVisibility(View.INVISIBLE);
            numOfFlippedCards = 0;
            updateScore();
        }else{
            gameMessageText.setText("Wrong! Pick again...");

            unflipCard(rowOfSecondCard, colOfSecondCard, secondCard);
        }
    }

    //Event handler for when a card is clicked, decides whether it should flip the card or unflip
    public void cardClicked(View view){
        if (numOfFlippedCards < 2) {
            if (view.equals(card11ImgBtn)) {
                if (!flippedCards[0][0]) {
                    flipCard(0, 0, card11ImgBtn);
                } else {
                    unflipCard(0, 0, card11ImgBtn);
                }
            } else if (view.equals(card12ImgBtn)) {
                if (!flippedCards[0][1]) {
                    flipCard(0, 1, card12ImgBtn);
                } else {
                    unflipCard(0, 1, card12ImgBtn);
                }
            } else if (view.equals(card13ImgBtn)) {
                if (!flippedCards[0][2]) {
                    flipCard(0, 2, card13ImgBtn);
                } else {
                    unflipCard(0, 2, card13ImgBtn);
                }
            } else if (view.equals(card21ImgBtn)) {
                if (!flippedCards[1][0]) {
                    flipCard(1, 0, card21ImgBtn);
                } else {
                    unflipCard(1, 0, card21ImgBtn);
                }
            } else if (view.equals(card22ImgBtn)) {
                if (!flippedCards[1][1]) {
                    flipCard(1, 1, card22ImgBtn);
                } else {
                    unflipCard(1, 1, card22ImgBtn);
                }
            } else if (view.equals(card23ImgBtn)) {
                if (!flippedCards[1][2]) {
                    flipCard(1, 2, card23ImgBtn);
                } else {
                    unflipCard(1, 2, card23ImgBtn);
                }
            } else if (view.equals(card31ImgBtn)) {
                if (!flippedCards[2][0]) {
                    flipCard(2, 0, card31ImgBtn);
                } else {
                    unflipCard(2, 0, card31ImgBtn);
                }
            } else if (view.equals(card32ImgBtn)) {
                if (!flippedCards[2][1]) {
                    flipCard(2, 1, card32ImgBtn);
                } else {
                    unflipCard(2, 1, card32ImgBtn);
                }
            } else if (view.equals(card33ImgBtn)) {
                if (!flippedCards[2][2]) {
                    flipCard(2, 2, card33ImgBtn);
                } else {
                    unflipCard(2, 2, card33ImgBtn);
                }
            }
        }
    }

    //Called when the game finishes i.e. when the timer hits 0
    //Creates an alert dialog asking the user if they wish to play again
    public void gameEnd()
    {
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
        alertBuilder.setTitle("Time's up!");
        alertBuilder.setMessage("Score: " + score +"! " + " Do you want to play again?");
        alertBuilder.setCancelable(false);

        alertBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener()
        {

            @Override
            public void onClick(DialogInterface arg0, int arg1)
            {
                MainActivity.super.recreate();
            }
        });

        alertBuilder.setNegativeButton("No", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                finish();
            }
        });

        alertBuilder.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        card11ImgBtn = findViewById(R.id.Card11);
        card12ImgBtn = findViewById(R.id.Card12);
        card13ImgBtn = findViewById(R.id.Card13);
        card21ImgBtn = findViewById(R.id.Card21);
        card22ImgBtn = findViewById(R.id.Card22);
        card23ImgBtn = findViewById(R.id.Card23);
        card31ImgBtn = findViewById(R.id.Card31);
        card32ImgBtn = findViewById(R.id.Card32);
        card33ImgBtn = findViewById(R.id.Card33);

        gameMessageText = findViewById(R.id.messageText);
        gameScoreText = findViewById(R.id.gameScore);
        gameTimeLeftText = findViewById(R.id.timeLeft);

        setUpCardGame();

    }

}
