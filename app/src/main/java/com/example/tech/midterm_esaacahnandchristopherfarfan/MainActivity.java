package com.example.tech.midterm_esaacahnandchristopherfarfan;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends Activity {

    public ImageButton card11ImgBtn, card12ImgBtn, card13ImgBtn, card21ImgBtn, card22ImgBtn,
            card23ImgBtn, card31ImgBtn, card32ImgBtn, card33ImgBtn;

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

    void setUpCardGame(){
        // Random number between 1-52 (inclusive) used to setup card pairs
        int selectedCards[] = new int[9];
        boolean hasBeenSelected[] = new boolean[9];
        int leftoverCard = (int) Math.floor((Math.random() * 52)+ 1);

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





    }

    boolean haveTwoCardsBeenFlipped(){
        if (numOfFlippedCards >= 2){
            return true;
        } else{
            return false;
        }
    }

    void flipCard(int row, int col, ImageButton card){
        card.setImageResource(cardMatrix[row][col]);
        flippedCards[row][col] = true;
        numOfFlippedCards++;
    }

    void unflipCard(int row, int col , ImageButton card){
        card.setImageResource(cardImages[0]);
        flippedCards[row][col] = false;
        numOfFlippedCards--;
    }


    void checkMatch(){

    }
    //Event handler for when a card is clicked
    public void cardClicked(View view){
        if (view.equals(card11ImgBtn)){
            if (!flippedCards[0][0]) {
                flipCard(0,0,card11ImgBtn);
            }else{
                unflipCard(0,0,card11ImgBtn);
            }
        } else if (view.equals(card12ImgBtn)){
            if (!flippedCards[0][1]) {
                flipCard(0,1,card12ImgBtn);
            }else{
                unflipCard(0,1,card12ImgBtn);
            }
        } else if (view.equals(card13ImgBtn)){
            if (!flippedCards[0][2]) {
                flipCard(0,2,card13ImgBtn);
            }else{
                unflipCard(0,2,card13ImgBtn);
            }
        } else if (view.equals(card21ImgBtn)){
            if (!flippedCards[1][0]) {
                flipCard(1,0,card21ImgBtn);
            }else{
                unflipCard(1,0,card21ImgBtn);
            }
        } else if (view.equals(card22ImgBtn)){
            if (!flippedCards[1][1]) {
                flipCard(1,1,card22ImgBtn);
            }else{
                unflipCard(1,1,card22ImgBtn);
            }
        } else if (view.equals(card23ImgBtn)){
            if (!flippedCards[1][2]) {
                flipCard(1,2,card23ImgBtn);
            }else{
                unflipCard(1,2,card23ImgBtn);
            }
        } else if (view.equals(card31ImgBtn)){
            if (!flippedCards[2][0]) {
                flipCard(2,0,card31ImgBtn);
            }else{
                unflipCard(2,0,card31ImgBtn);
            }
        } else if (view.equals(card32ImgBtn)){
            if (!flippedCards[2][1]) {
                flipCard(2,1,card32ImgBtn);
            }else{
                unflipCard(2,1,card32ImgBtn);
            }
        } else if (view.equals(card33ImgBtn)) {
            if (!flippedCards[2][2]) {
                flipCard(2,2,card33ImgBtn);
            }else{
                unflipCard(2,2,card33ImgBtn);
            }
        }

        if (haveTwoCardsBeenFlipped()){
            checkMatch();
        }

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

        setUpCardGame();

    }

    void gameLoop(){

    }
}
