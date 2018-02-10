package com.example.felixhankel.charactersheetds;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onStart(){
        super.onStart();

        final File dir = getFilesDir();

        load();

        ListAdpaterSetUp();

        InitiateLists();

        TextView Namensschild = (TextView) (findViewById(R.id.textView));
        Namensschild.setText(Stats.name);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button D20 = (findViewById(R.id.buttonD20));
        Button D6 = (findViewById(R.id.buttonD6));


        D20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Random r = new Random();
                String rand =String.valueOf( r.nextInt(21-1)+1);
                Toast.makeText(getApplicationContext(),rand,Toast.LENGTH_LONG).show();
            }
        });

        D6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Random r = new Random();
                String rand =String.valueOf( r.nextInt(7-1)+1);
                Toast.makeText(getApplicationContext(),rand,Toast.LENGTH_LONG).show();
            }
        });

        final File file = new File(getFilesDir(), "char.txt");

        if (file.exists()){}
        else{
            Intent myIntent = new Intent(this,Create_Character.class);
            startActivityForResult(myIntent,0);
            Toast.makeText(this, "keinen gespeicherten Character gefunden", Toast.LENGTH_LONG).show();
        }

        Button buttondel = (findViewById(R.id.button2));

        buttondel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder;
                builder = new AlertDialog.Builder(MainActivity.this, android.R.style.Theme_Material_Dialog_Alert);
                builder.setTitle("Character Löschen")
                        .setMessage("Bist du dir sicher, dass du deinen Character löschen möchtest um einen neuen zu erstellen?")
                        .setPositiveButton("JA", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                boolean deleted = file.delete();
                                Intent myIntent = new Intent(getApplicationContext(), Create_Character.class);
                                startActivityForResult(myIntent, 0);
                            }
                        })
                        .setNegativeButton("Nein", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        //.setIcon(android.R.drawable.ic_dialog_alert)
                        .show();





            }
        });
        TextView Namensschild = (TextView) (findViewById(R.id.textView));
        Namensschild.setText(Stats.name);

        Spinner spinner = (Spinner) (findViewById(R.id.Activityspinner));
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.CharacterSpinner, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        // Setting up the Lists
        InitiateLists();

        // Setting up the List Adapter
        ListAdpaterSetUp();
    }
    public void InitiateLists(){

        Lists.haupt.clear();
        Lists.haupt.add("Bewegung " + String.valueOf(Stats.bwg));
        Lists.haupt.add("Charisma " + String.valueOf(Stats.ch));
        Lists.haupt.add("Fingerfertigkeit " + String.valueOf(Stats.ff));
        Lists.haupt.add("Geist " + String.valueOf(Stats.gei));
        Lists.haupt.add("Gewandheit " + String.valueOf(Stats.ge));
        Lists.haupt.add("Intuition " + String.valueOf(Stats.in));
        Lists.haupt.add("Klugheit " + String.valueOf(Stats.kl));
        Lists.haupt.add("Konstitution " + String.valueOf(Stats.ko));
        Lists.haupt.add("Körper "+ String.valueOf(Stats.koer));
        Lists.haupt.add("Körperkraft " + String.valueOf(Stats.kk));
        Lists.haupt.add("Mut " + String.valueOf(Stats.mu));
        Lists.haupt.add("Willenskraft " + String.valueOf(Stats.wil));

        Stats.lvl = 1;
        int le = ((Stats.koer+Stats.ko)*2+Stats.rmhealth);
        int ae = ((Stats.gei+Stats.ch)*2+18);
        int ke = ((Stats.wil+Stats.in)*2+Stats.lvl);
        int gs = (Stats.bwg+1+Stats.rmspeed);
        int ini = (Stats.bwg+Stats.mu+Stats.in);
        int pa = (Stats.koer+Stats.ge+Stats.in);
        int aus = (Stats.bwg+Stats.ge+Stats.in); //Mi
        int at = (Stats.koer+Stats.kk+Stats.mu);
        int fk = (Stats.bwg+Stats.ge+Stats.ff);
        int zau = (Stats.gei+Stats.ch+Stats.kl);
        int lit = (Stats.wil+Stats.ch+Stats.in);
        int re = ((Stats.koer+Stats.ko)-1);
        int mr = ((Stats.wil+Stats.kl)-1);

        Lists.grund.clear();
        Lists.grund.add("Astralenergie " + String.valueOf(ae));
        Lists.grund.add("Attackebasis " + String.valueOf(at));
        Lists.grund.add("Ausweichen " + String.valueOf(aus));
        Lists.grund.add("Fernkampfbasis " + String.valueOf(fk));
        Lists.grund.add("Geschwindigkeit " + String.valueOf(gs));
        Lists.grund.add("Initiative " + String.valueOf(ini));
        Lists.grund.add("Karmaenergie " + String.valueOf(ke));
        Lists.grund.add("Lebensenergie " + String.valueOf(le));
        Lists.grund.add("Liturgiebasis " + String.valueOf(lit));
        Lists.grund.add("Magieresistenz " + String.valueOf(mr));
        Lists.grund.add("Paradebasis " + String.valueOf(pa));
        Lists.grund.add("Resistenz " + String.valueOf(re));
        Lists.grund.add("Zauberbasis " + String.valueOf(zau));

    }

    public void load(){
        FileInputStream fis = null;
        try {
            fis = openFileInput("char.txt");
            InputStreamReader isr= new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String text;

            String[] Buffer = new String[15];

            for (int i=0;i<15;i++){
                Buffer[i] = br.readLine();
            }


            Stats.name = Buffer[0];
            Stats.koer = Integer.parseInt(Buffer[1]);
            Stats.bwg = Integer.parseInt(Buffer[2]);
            Stats.wil = Integer.parseInt(Buffer[3]);
            Stats.gei = Integer.parseInt(Buffer[4]);
            Stats.kk = Integer.parseInt(Buffer[5]);
            Stats.ge = Integer.parseInt(Buffer[6]);
            Stats.mu = Integer.parseInt(Buffer[7]);
            Stats.kl = Integer.parseInt(Buffer[8]);
            Stats.ko = Integer.parseInt(Buffer[9]);
            Stats.ff = Integer.parseInt(Buffer[10]);
            Stats.ch = Integer.parseInt(Buffer[11]);
            Stats.in = Integer.parseInt(Buffer[12]);
            Stats.rmhealth = Integer.parseInt(Buffer[13]);
            Stats.rmspeed = Integer.parseInt(Buffer[14]);


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null){
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

   public void ListAdpaterSetUp(){
       ListAdapter HauptAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, Lists.haupt);
       ListView ExampleListView = findViewById(R.id.HauptattributeList);
       ExampleListView.setAdapter(HauptAdapter);
       ListAdapter GrundAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, Lists.grund);
       ListView ExampleListView2 = findViewById(R.id.GrundwerteList);
       ExampleListView2.setAdapter(GrundAdapter);
    }
}
