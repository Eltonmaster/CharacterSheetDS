package com.example.felixhankel.charactersheetds;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Create_Character extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create__character);
        Button submit = findViewById(R.id.button);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final EditText Edit1 = (EditText) (findViewById(R.id.editText));
                final EditText Edit2 = (EditText) (findViewById(R.id.editText2));
                final EditText Edit3 = (EditText) (findViewById(R.id.editText3));
                final EditText Edit4 = (EditText) (findViewById(R.id.editText4));
                final EditText Edit5 = (EditText) (findViewById(R.id.editText5));
                final EditText Edit6 = (EditText) (findViewById(R.id.editText6));
                final EditText Edit7 = (EditText) (findViewById(R.id.editText7));
                final EditText Edit8 = (EditText) (findViewById(R.id.editText8));
                final EditText Edit9 = (EditText) (findViewById(R.id.editText9));
                final EditText Edit10 = (EditText) (findViewById(R.id.editText10));
                final EditText Edit11 = (EditText) (findViewById(R.id.editText11));
                final EditText Edit12 = (EditText) (findViewById(R.id.editText12));
                final EditText Edit13 = (EditText) (findViewById(R.id.editText13));


                Stats.name = Edit1.getText().toString();
                String test = Edit2.getText().toString();
                Stats.koer = Integer.parseInt(test);
                test = Edit3.getText().toString();
                Stats.bwg = Integer.parseInt(test);
                test = Edit4.getText().toString();
                Stats.wil = Integer.parseInt(test);
                test = Edit5.getText().toString();
                Stats.gei = Integer.parseInt(test);
                test = Edit6.getText().toString();
                Stats.kk = Integer.parseInt(test);
                test = Edit7.getText().toString();
                Stats.ge = Integer.parseInt(test);
                test = Edit8.getText().toString();
                Stats.mu = Integer.parseInt(test);
                test = Edit9.getText().toString();
                Stats.kl = Integer.parseInt(test);
                test = Edit10.getText().toString();
                Stats.ko = Integer.parseInt(test);
                test = Edit11.getText().toString();
                Stats.ff = Integer.parseInt(test);
                test = Edit12.getText().toString();
                Stats.ch = Integer.parseInt(test);
                test = Edit13.getText().toString();
                Stats.in = Integer.parseInt(test);

                AlertDialog.Builder alert = new AlertDialog.Builder(Create_Character.this);

                Context context = Create_Character.this;
                LinearLayout layout = new LinearLayout(context);
                layout.setOrientation(LinearLayout.VERTICAL);

                // Add a TextView here for the "Title" label, as noted in the comments
                final EditText rmhealth = new EditText(context);
                rmhealth.setHint("Lebensbonus");
                rmhealth.setInputType(InputType.TYPE_CLASS_NUMBER);
                layout.addView(rmhealth); // Notice this is an add method

                // Add another TextView here for the "Description" label
                final EditText rmspeed = new EditText(context);
                rmspeed.setHint("Geschwindigkeitsbonus");
                rmspeed.setInputType(InputType.TYPE_CLASS_NUMBER);

                layout.addView(rmspeed);

                alert.setView(layout);

                alert.setPositiveButton("Character Erstellen", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (rmhealth.length() < 1) {
                            Stats.rmhealth = Integer.parseInt(rmhealth.getText().toString());
                        }
                        else {Stats.rmhealth= 1;}
                        if (rmspeed.length() < 1) {
                            Stats.rmspeed = Integer.parseInt(rmspeed.getText().toString());
                        }
                        else {Stats.rmspeed=  1 ;}

                        Toast.makeText(Create_Character.this, "Character "+ Stats.name+" angelegt", Toast.LENGTH_SHORT).show();

                        String auslese = Edit1.getText().toString();
                        auslese += "\n";
                        auslese += Edit2.getText().toString();
                        auslese += "\n";
                        auslese +=Edit3.getText().toString();
                        auslese += "\n";
                        auslese +=Edit4.getText().toString();
                        auslese += "\n";
                        auslese +=Edit5.getText().toString();
                        auslese += "\n";
                        auslese +=Edit6.getText().toString();
                        auslese += "\n";
                        auslese +=Edit7.getText().toString();
                        auslese += "\n";
                        auslese +=Edit8.getText().toString();
                        auslese += "\n";
                        auslese +=Edit9.getText().toString();
                        auslese += "\n";
                        auslese +=Edit10.getText().toString();
                        auslese += "\n";
                        auslese +=Edit11.getText().toString();
                        auslese += "\n";
                        auslese +=Edit12.getText().toString();
                        auslese += "\n";
                        auslese +=Edit13.getText().toString();
                        auslese += "\n";
                        auslese +=rmhealth.getText().toString();
                        auslese += "\n";
                        auslese +=rmspeed.getText().toString();
                        FileOutputStream fos = null;
                        try {
                            fos = openFileOutput("char.txt",MODE_PRIVATE);
                            fos.write(auslese.getBytes());
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        } finally {
                            if(fos != null){
                                try {
                                    fos.close();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }finish();
                    }
                });
                alert.show();

            }
        });
    }

}