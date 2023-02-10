package comp208.deans;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Score extends AppCompatActivity {

    // create new mainActivity to use values from within the MainActivity.java file
    MainActivity mainActivity = new MainActivity();

    // create text view
    TextView noGuessesText;

    int noGuesses = 0;
    /*
     * @Override annotation used to override a method of supertype
     */
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        // create new intent/ bundle of type int
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        // if bundle is not null then set noGuesses from flip count received from MainActivity.java
        if (bundle != null) {
            noGuesses = bundle.getInt("flips");
            noGuessesText = findViewById(R.id.noGuessesTxt);
            setText();
        }

        // intent to launch game on button press
        configureMainActivityPage();

        // initialize variables with ids to close application on button press
        // private button used to close application once pressed
        Button closeApplicationBtn = findViewById(R.id.endBtn);

        // on below line we are adding click listener for our button to close the application
        closeApplicationBtn.setOnClickListener(v -> {
            // on below line we are finishing activity.
            Score.this.finish();
            // call onClose method () from MainActivity and close application
            mainActivity.onClose();
            // on below line we are exiting our activity
            System.exit(0);
        });
    }

    // configureMainActivityPage method () to launch application on button press
    public void configureMainActivityPage() {
        Button playAgainButton = findViewById(R.id.playAgainBtn);
        playAgainButton.setOnClickListener(view -> startActivity(new Intent(Score.this, MainActivity.class)));
    }

    // set text method () sent in from intent
    public void setText() {
        noGuessesText.setText("Number of Guesses: " + noGuesses);
    }
}