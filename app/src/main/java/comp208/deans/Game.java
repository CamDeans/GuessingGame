package comp208.deans;

import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class Game extends AppCompatActivity {
    // setup random for shuffling the deck
    Random rand = new Random();

    // set int value for row and col coordinates of type card
    int col = 3;
    int row = 4;

    // set int values for cards that are selected, pairs that are matched and flip counts
    int cardsSelected = 0;
    int pairsMatched = 0;
    int flips = 0;

    // initialize a 2d array to hold card values in the deck
    Card[][] deck = new Card[row][col];
    // initialize a card value for the each card selection
    Card selected;

    // initialize an array of card front face values
    // use the the same (6) cards twice
    int[] frontFaces = {
            R.drawable.yodacard1,
            R.drawable.yodacard2,
            R.drawable.yodacard3,
            R.drawable.yodacard4,
            R.drawable.yodacard5,
            R.drawable.yodacard6,
            R.drawable.yodacard1,
            R.drawable.yodacard2,
            R.drawable.yodacard3,
            R.drawable.yodacard4,
            R.drawable.yodacard5,
            R.drawable.yodacard6
    };

    // set int to hold ard back face values
    int backFace = R.drawable.yoda;

    // initialize our game method () and call both the shuffle and deal methods ()
    public void initGame() {
        initDeck(frontFaces);
        shuffle();
        deal();
    }

    // initialize the deck of cards method ()
    // pass in the front face values of each card
    // assign values to back face of cards
    private void initDeck(int[] frontFaces) {
        int frontFaceIndex = 0;
        for(int rowIndex = 0; rowIndex < deck.length; rowIndex++) {
            Card[] row = deck[rowIndex];
            for (int colIndex = 0; colIndex < row.length; colIndex ++) {
                int frontFace = frontFaces[frontFaceIndex++];
                Card card = new Card(backFace, frontFace);
                card.col = colIndex;
                card.row = rowIndex;
                deck[rowIndex][colIndex] = card;
            }
        }
    }

    // shuffle deck of cards at random
    public void shuffle() {
        final int noCards = 12;
        for (int i = 0; i < 100; i++) {
            int rand1 = rand.nextInt(noCards);
            int rand2 = rand.nextInt(noCards);
            Card tempCard = deck[rand1/3][rand1%3];
            deck[rand1/3][rand1%3] = deck[rand2/3][rand2%3];
            deck[rand2/3][rand2%3] = tempCard;
        }
    }

    // deal the cards face down
    public void deal() {
        for (Card[] cards : this.deck) {
            for (Card card : cards) {
                card.faceUp = false;
            }
        }
    }

    // selected cards method () used to compare cards
    // count all cards being flipped
    // count all pairs found
    public void selectedCard(Card card) {
        if (cardsSelected == 0) {
            selected = card;
            cardsSelected = 1;
            card.faceUp = true;
        } else {
            flips++;
            if (card.frontFace == selected.frontFace) {
                pairsMatched++;
                card.faceUp = true;
            } else {
                card.faceUp = false;
            }
            cardsSelected = 0;
        }
    }

    // getter used to get pairs found and return value
    public int getPairsMatched() {
        return pairsMatched;
    }

    // getter used to get flip count and return value
    public int getFlips() {
        return flips;
    }
}