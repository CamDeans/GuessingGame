package comp208.deans;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    // create new game to use values from within the Game.java file
    Game game = new Game();

    // initialize table layout of type board
    TableLayout board;

    // create ImageView of selected images and assign them to null value
    ImageView[] selectedImages = {null,null};

    // set int value for each card selected
    int noCardsSelected = 0;

    // create handler to handle flipping each card back to the back face value if no match is found
    Handler flipCardHandler = new Handler();

    // set text view for both flip count and matches
    // dynamically pass the text value as needed
    TextView flipsText;
    TextView matchesText;

    //  create an on click listener to handle card selection by the user
    View.OnClickListener clickListener = new View.OnClickListener() {
        @SuppressLint("SetTextI18n")
        @Override
        public void onClick(View view) {
            // create an image view and assign value for each card selection
            ImageView iv = (ImageView) view;
            //  create new card and use assigned tag to locate card in the deck
            Card card = (Card) iv.getTag();
            // if card is not showing face value, boolean method () is set to return false
            if (card.isFaceUp()) return;
            // handle card selection by count increments
            // the user can only flip (2) cards at one time
            selectedImages[noCardsSelected++] = iv;
            // pass new card value to selected card method () within the Game.java file
            game.selectedCard(card);
            // set card value using the setImageResource method()
            iv.setImageResource(card.frontFace);
            if (noCardsSelected == 2) {
                // compare values of the (2) card selections and turn cards down if no match is found
                Card card0 = (Card)selectedImages[0].getTag();
                Card card1 = (Card)selectedImages[1].getTag();
                if (card0.frontFace != card1.frontFace) {
                    turnCardsDown(500);
                }
                // reset selected card count to zero
                noCardsSelected = 0;
            }
            // set int value for matches/ flips found
            // set text value for both matches and flips
            int matches = game.getPairsMatched();
            matchesText.setText("Matches: " + matches);
            int flips = game.getFlips();
            flipsText.setText("Flips: " + flips);
            if (matches == 6) {
                // intent used to move to the Score activity
                // putExtra used to send flips count and display
                Intent intent = new Intent(MainActivity.this, Score.class);
                intent.putExtra("flips", flips);
                startActivity(intent);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // find view by id and set text value dynamically
        flipsText = findViewById(R.id.textView1);
        matchesText = findViewById(R.id.textView2);

        // table layout of type board to get child in code below
        board = findViewById(R.id.mgBoard);

        // call the startGame method ()
        startGame();
    }

    // start the game function
    private void startGame() {
        // call the initGame method ()
        game.initGame();
        // set int for rows on the board
        int noRows = board.getChildCount();
        // use a nested for loop to iterate through board and set cards to back face values
        for (int rowIndex = 0; rowIndex < noRows; rowIndex++) {
            TableRow tr = (TableRow) board.getChildAt(rowIndex);
            int noCols = tr.getChildCount();
            for (int colIndex = 0; colIndex < noCols; colIndex++) {
                ImageView iv = (ImageView) tr.getChildAt(colIndex);
                iv.setOnClickListener((clickListener));
                Card card = game.deck[rowIndex][colIndex];
                iv.setTag(card);
                iv.setImageResource(game.backFace);
            }
        }
    }

    // turnCardsDown method () and pass delay
    // call the Runnable handler and flip cards to show card back face values
    public void turnCardsDown(int delay) {
        flipCardHandler.postDelayed(flipCardsDown, delay);
    }
    Runnable flipCardsDown = ()-> {
        selectedImages[0].setImageResource(R.drawable.yoda);
        selectedImages[1].setImageResource(R.drawable.yoda);
        };

    // onClose method () used to close activity on button press to shut down application
    public void onClose(){
        // on below line we are initializing variables with ids.
        View closeApplicationBtn = findViewById(R.id.endBtn);

        // on below line we are adding click listener for our button
        closeApplicationBtn.setOnClickListener(v -> {
            // on below line we are finishing activity.
            MainActivity.this.finish();

            // on below line we are exiting our activity
            System.exit(0);
        });
    }
}