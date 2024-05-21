package com.example.milampara;

import static android.graphics.Color.luminance;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.drawable.DrawableCompat;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button button, button2, button3, button4, button5, button6, button7;
    private static final String TAG = "MainActivity";
    //couleur du bouton principal qu'on modifie à chaque clique
    int colorButtonPrinc = Color.rgb(0,0,0);
    //composantes rgb de colorButtonPrinc
    int red;
    int green ;
    int blue;


    //index du tableau contenant les couleurs de l'arc en ciel
    private int colorIndex;
    //couleurs de l'arc en ciel
    int rouge = Color.rgb(255, 0,0);
    int orange = Color.rgb(255,165,0);
    int jaune = Color.rgb(255,255,0);
    int vert = Color.rgb(0,255,0);
    int bleu = Color.rgb(0,0,255);
    int indigo = Color.rgb(75,0,130);
    int violet = Color.rgb(138,43,226);
    boolean isRunning = false;// initialisation du boolean pour savoir si le thread est lancé ou non
    private int[] colors = {rouge, orange, jaune, vert, bleu, indigo, violet}; // couleurs utilisées pour le thread
    ChangeButtonColorThread thread = new ChangeButtonColorThread(); // déclaration du thread
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "Activité : MainActivity, Méthode: onCreate()");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //on recupère le bouton principale de l'interface et on lui ajoute un écouteur
        button = findViewById(R.id.button);
        button.setOnClickListener(this);

        //on definit la couleur du bouton principale
        Intent intent = getIntent();
        String color = intent.getStringExtra("couleur");

        //on définie la couleur initiale du bouton principale selon la couleur du bouton cliqué dans l'écran d'accueil
        switch (color) {
            case "rouge":
                colorButtonPrinc = Color.rgb(255,0,0);
                button.setBackgroundColor(colorButtonPrinc);
                button.setText("Rouge : "+Color.red(colorButtonPrinc)+ ", Vert : "+ Color.green(colorButtonPrinc)+ ", Bleu : "+Color.blue(colorButtonPrinc)) ;
                luminance();
                break;
            case "vert":
                colorButtonPrinc = Color.rgb(0,255,0);
                button.setBackgroundColor(colorButtonPrinc);
                button.setText("Rouge : "+Color.red(colorButtonPrinc)+ ", Vert : "+ Color.green(colorButtonPrinc)+ ", Bleu : "+Color.blue(colorButtonPrinc)) ;
                luminance();
                break;
            case "bleu":
                colorButtonPrinc = Color.rgb(0,0,255);
                button.setBackgroundColor(colorButtonPrinc);
                button.setText("Rouge : "+Color.red(colorButtonPrinc)+ ", Vert : "+ Color.green(colorButtonPrinc)+ ", Bleu : "+Color.blue(colorButtonPrinc)) ;
                luminance();
                break;
            case "blanc":
                colorButtonPrinc = Color.rgb(255,255,255);
                button.setBackgroundColor(colorButtonPrinc);
                button.setText("Rouge : "+Color.red(colorButtonPrinc)+ ", Vert : "+ Color.green(colorButtonPrinc)+ ", Bleu : "+Color.blue(colorButtonPrinc)) ;
                luminance();
                break;
        }

        //on récupère les autres boutons de l'interface et on leur ajoute un écouteur
        button2 = findViewById(R.id.button2);
        button2.setOnClickListener(this);

        button3 = findViewById(R.id.button3);
        button3.setOnClickListener(this);

        button4 = findViewById(R.id.button4);
        button4.setOnClickListener(this);

        button5 = findViewById(R.id.button5);
        button5.setOnClickListener(this);

        button6 = findViewById(R.id.button6);
        button6.setOnClickListener(this);

        button7 = findViewById(R.id.button7);
        button7.setOnClickListener(this);

    }

    //on restaure la couleur et le texte du bouton principal lors d'un changement d'orientation d'ecran
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        colorButtonPrinc = savedInstanceState.getInt("button"); // restaurer la couleur du bouton principal
        button.setBackgroundColor(colorButtonPrinc);
        String texte = savedInstanceState.getString("buttonText");
        button.setText(texte);
        
    }

    //exécute la tâche de changement de couleur de fond du bouton principal
    class ChangeButtonColorThread extends Thread {
        @Override
        public void run() {
            while (isRunning) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        button.setBackgroundColor(colors[colorIndex]); // changer la couleur de fond du bouton

                    }
                });
                try {
                    Thread.sleep(2000); // délai pour simuler une tâche longue
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                colorIndex = (colorIndex + 1) % colors.length; // passer à la couleur suivante
            }
        }
    }
    // change la couleur du bouton principal lorsqu'on click sur un des autres boutons et
    // on lance et arrete le thread en cliquant sur le bouton principal
    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.button)
        {
            if (isRunning) {

                isRunning = false;
                thread.interrupt();
                button.setBackgroundColor(colorButtonPrinc);

            }
            else {
                isRunning = true;
                thread.start();
            }

        }

        // button + rouge
        if (view.getId() == R.id.button2)
        {

            red = Color.red(colorButtonPrinc);
            green = Color.green(colorButtonPrinc);
            blue = Color.blue(colorButtonPrinc);

            if (red <= 255 && red >=0)
            {
                red   += 16;
                if (red >255)
                {
                    red =255;
                }
                colorButtonPrinc = Color.rgb(red,green,blue);
                button.setBackgroundColor(colorButtonPrinc);
                button.setText("Rouge : "+Color.red(colorButtonPrinc)+ ", Vert : "+ Color.green(colorButtonPrinc)+ ", Bleu : "+Color.blue(colorButtonPrinc)) ;
                luminance();
            }


        }

        //button -rouge
        if (view.getId() == R.id.button5) {

                red = Color.red(colorButtonPrinc);
                green = Color.green(colorButtonPrinc);
                blue = Color.blue(colorButtonPrinc);

                if (red >=0 && red <=255) {
                    red -= 16;
                    if (red <0)
                    {
                        red =0;
                    }
                    colorButtonPrinc = Color.rgb(red, green, blue);
                    button.setBackgroundColor(colorButtonPrinc);
                    button.setText("Rouge : "+Color.red(colorButtonPrinc)+ ", Vert : "+ Color.green(colorButtonPrinc)+ ", Bleu : "+Color.blue(colorButtonPrinc)) ;
                    luminance();
                }
            }

        //button +vert
        if (view.getId() == R.id.button3) {
            red = Color.red(colorButtonPrinc);
            green = Color.green(colorButtonPrinc);
            blue = Color.blue(colorButtonPrinc);
            if (green <= 255 && green >= 0)
            {
                green   += 16;
                if (green > 255)
                {
                    green = 255;
                }
                colorButtonPrinc = Color.rgb(red,green,blue);
                button.setBackgroundColor(colorButtonPrinc);
                button.setText("Rouge : "+Color.red(colorButtonPrinc)+ ", Vert : "+ Color.green(colorButtonPrinc)+ ", Bleu : "+Color.blue(colorButtonPrinc)) ;
                luminance();
            }
        }

        //button -vert
        if (view.getId() == R.id.button6) {

            red = Color.red(colorButtonPrinc);
            green = Color.green(colorButtonPrinc);
            blue = Color.blue(colorButtonPrinc);

            if (green >= 0 && green <=255) {
                green -= 16;
                if (green < 0)
                {
                    green = 0;
                }
                colorButtonPrinc = Color.rgb(red, green, blue);
                button.setBackgroundColor(colorButtonPrinc);
                button.setText("Rouge : "+Color.red(colorButtonPrinc)+ ", Vert : "+ Color.green(colorButtonPrinc)+ ", Bleu : "+Color.blue(colorButtonPrinc)) ;
                luminance();
            }
        }

        //button +bleu
        if (view.getId() == R.id.button4) {

            red = Color.red(colorButtonPrinc);
            green = Color.green(colorButtonPrinc);
            blue = Color.blue(colorButtonPrinc);

            if (blue <= 255 && blue >= 0)
            {
                blue   += 16;
                if (blue > 255)
                {
                    blue = 255;
                }
                colorButtonPrinc = Color.rgb(red,green,blue);
                button.setBackgroundColor(colorButtonPrinc);
                button.setText("Rouge : "+Color.red(colorButtonPrinc)+ ", Vert : "+ Color.green(colorButtonPrinc)+ ", Bleu : "+Color.blue(colorButtonPrinc)) ;
                luminance();
            }
        }

        //button -bleu
        if (view.getId() == R.id.button7) {

            red = Color.red(colorButtonPrinc);
            green = Color.green(colorButtonPrinc);
            blue = Color.blue(colorButtonPrinc);

            if (blue >= 0 && blue <=255) {
                blue -= 16;
                if (blue <0)
                {
                    blue = 0;
                }
                colorButtonPrinc = Color.rgb(red, green, blue);
                button.setBackgroundColor(colorButtonPrinc);
                button.setText("Rouge : "+Color.red(colorButtonPrinc)+ ", Vert : "+ Color.green(colorButtonPrinc)+ ", Bleu : "+Color.blue(colorButtonPrinc)) ;
                luminance();
            }
        }

        new socketThread().start();

    }

    //change la couleur du texte du bouton principal en fonction de la luminosité de ce bouton
    public void luminance ()
    {
        if(Color.luminance(colorButtonPrinc) <=0.5 )
        {
            button.setTextColor(Color.WHITE);
        }
        else
        {
            button.setTextColor(Color.BLACK);
        }
    }


    //sauvegarde la couleur du bouton principal et le texte
    public void onSaveInstanceState (Bundle outState) {
        super.onSaveInstanceState(outState);
        // Sauvegarder la couleur du bouton
        outState.putInt("button", colorButtonPrinc);
        outState.putString("buttonText", (String) button.getText());
    }

    //affiche dans la console un message précisant les noms de la méthode et de l'activité.
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "Activité: MainActivity, Méthode: onStart()");

    }

    protected void onStop() {
        super.onStop();
        Log.i(TAG, "Activité: MainActivity, Méthode: onStop()");

    }

    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "Activité: MainActivity, Méthode: onRestart()");

    }

    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "Activité: MainActivity,Méthode: onDestroy()");

    }

    protected void onPause() {
        super.onPause();
        Log.i(TAG, "Activité: MainActivity, Méthode: onPause()");

    }

    protected void onResume() {
        super.onResume();
        Log.i(TAG, "Activité: MainActivity, Méthode: onResume()");
    }

    //partie 6
    class socketThread extends Thread
    {
        //connexion au site

        public void run()

        {
            try {
                Socket socket = new Socket("chadok.info", 9998);
                PrintWriter writer;
                writer = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String numLampe = "07";
                String couleurRouge = Integer.toHexString(Color.red(colorButtonPrinc));
                if(couleurRouge.length() < 2) {
                    couleurRouge = "0" + couleurRouge;
                }
                String couleurVert = Integer.toHexString(Color.green(colorButtonPrinc));
                if(couleurVert.length() < 2) {
                    couleurVert = "0" + couleurVert;
                }
                String couleurBleu = Integer.toHexString(Color.blue(colorButtonPrinc));
                if(couleurBleu.length() < 2) {
                    couleurBleu = "0" + couleurBleu;
                }
                String strFinal = numLampe + couleurRouge + couleurVert + couleurBleu;
                writer.println(strFinal);
                String reply = reader.readLine();
                Log.i("msg", "Réponse "+ reply);
                socket.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }



    }

    }



