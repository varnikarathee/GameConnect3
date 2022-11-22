package com.example.gameconnect3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int[][] winningPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};
    int activeplayer = 0;
    boolean gameactive = true;


    public void dropIn(View view) {


        ImageView counter = (ImageView) view;

        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if (gameState[tappedCounter] == 2 && gameactive) {
            gameState[tappedCounter] = activeplayer;
            counter.setTranslationY(-1500);

            if (activeplayer == 0) {
                counter.setImageResource(R.drawable.yellow);

                activeplayer = 1;
            } else {
                counter.setImageResource(R.drawable.red);

                activeplayer = 0;
            }
            counter.animate().translationYBy(1500).rotation(3600).setDuration(300);

            for (int[] a : winningPositions) {
                if (gameState[a[0]] == gameState[a[1]] && gameState[a[1]] == gameState[a[2]] && gameState[a[0]] != 2) {

                    gameactive = false;
                    String winner = "";
                    if (activeplayer == 1) {
                        winner = "Yellow";
                    } else {
                        winner = "Red";
                    }
                    Button button = (Button) findViewById(R.id.button);
                    TextView textView2 = (TextView) findViewById(R.id.textView2);
                    textView2.setText(winner + " has won");
                    button.setVisibility(View.VISIBLE);
                    textView2.setVisibility(View.VISIBLE);
                }
            }
        }


    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void playAgain(View view) {
        Button button = (Button)findViewById(R.id.button);
        TextView textView2 = (TextView)findViewById(R.id.textView2);

        button.setVisibility(View.INVISIBLE);
        textView2.setVisibility(View.INVISIBLE);

        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);
        for (int i = 0; i < gridLayout.getChildCount(); i++) {
            ImageView imageView = (ImageView) gridLayout.getChildAt(i);

            imageView.setImageDrawable(null);
        }

        for (int i = 0; i < gameState.length; i++) {
            gameState[i] = 2;
        }
        gameactive = true;
        activeplayer = 0;
    }
}
