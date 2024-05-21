package com.example.milampara;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class ecran_accueil extends AppCompatActivity {

    Button btnRouge, btnBleu, btnVert, btnBlanc;
    private static final String TAG = "LampeMagique";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ecran_accueil);
        Log.i(TAG, "Activité : ecran_accueil, Méthode: onCreate()");//affiche dans la console un message précisant les noms de la méthode et de l'activité.

        //on recupère les boutons de la page d'accueil
        btnRouge = findViewById(R.id.button8);
        btnVert = findViewById(R.id.button9);
        btnBleu = findViewById(R.id.button10);
        btnBlanc = findViewById(R.id.button11);

        //quand on clique sur le bouton rouge la deuxième actvité est lancé avec comme
        // couleur initiale du bouton principal à rouge
        btnRouge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ecran_accueil.this, MainActivity.class);

                intent.putExtra("couleur", "rouge");
                startActivity(intent);

            }

        });

        //meme chose que le bouton rouge
        btnVert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ecran_accueil.this, MainActivity.class);

                intent.putExtra("couleur", "vert");
                startActivity(intent);

            }

        });

        //meme chose que le bouton rouge
        btnBleu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ecran_accueil.this, MainActivity.class);

                intent.putExtra("couleur", "bleu");
                startActivity(intent);
            }

        });


        //meme chose que le bouton rouge
        btnBlanc.setOnClickListener(new View.OnClickListener()
        {
        @Override
            public void onClick (View view){
                Intent intent = new Intent(ecran_accueil.this, MainActivity.class);

                intent.putExtra("couleur", "blanc");
                startActivity(intent);
            }
        });

}
//affiche dans la console un message précisant les noms de la méthode et de l'activité.
    protected void onStart(){
        super.onStart();
        Log.i(TAG,"Activité: ecran_accueil, Méthode: onStart()");

    }
    protected void onStop() {
        super.onStop();
        Log.i(TAG,"Activité: ecran_accueil, Méthode: onStop()");

    }
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG,"Activité: ecran_accueil, Méthode: onRestart()");

    }
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG,"Activité: ecran_accueil, Méthode: onDestroy()");

    }
    protected void onPause() {
        super.onPause();
        Log.i(TAG,"Activité: ecran_accueil, Méthode: onPause()");

    }
    protected void onResume() {
        super.onResume();
        Log.i(TAG,"Activité: ecran_accueil, Méthode: onResume()");

    }
}