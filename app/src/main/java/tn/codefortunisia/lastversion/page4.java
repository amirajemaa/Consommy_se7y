package tn.codefortunisia.lastversion;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;

import static java.lang.Math.round;

public class page4 extends AppCompatActivity implements  View.OnClickListener {
    private TextView energie, sodium, sucres, nom, acides, additifs, allergenes, what;
    private HashMap additif;
    NatureAliment aliment;

    ConstraintLayout r;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page3);
        energie = findViewById(R.id.energie);
        energie.setText(String.format("   %s \n kj/100g", getIntent().getStringExtra("energie")));
        sodium = findViewById(R.id.sodium);
        sodium.setText(String.format("        %s \n mg/100g", getIntent().getStringExtra("sodium")));
        sucres = findViewById(R.id.sucres);
        sucres.setText(String.format("   %s \n g/100g", getIntent().getStringExtra("sucre")));
        acides = findViewById(R.id.acides);
        acides.setText("   " + getIntent().getStringExtra("acide") + " \n g/100g");
        nom = findViewById(R.id.nom);
        nom.setText(getIntent().getStringExtra("nom"));
        ArrayList<String> allergpropose = (ArrayList<String>) getIntent().getSerializableExtra("allerg_proposé");
        r = findViewById(R.id.page3);
        additifs=findViewById(R.id.additifs);
        additifs.setOnClickListener(this);

        aliment = new NatureAliment(getIntent().getStringExtra("energie"),
                getIntent().getStringExtra("sucre"), getIntent().getStringExtra("acide"),
                "0.0", getIntent().getStringExtra("proteins"),
                getIntent().getStringExtra("fibres"), getIntent().getStringExtra("quantite"),
                getIntent().getStringExtra("composants"), getIntent().getStringExtra("catégorie"));
        if (getIntent().getStringExtra("catégorie") == "jus" || getIntent().getStringExtra("catégorie") == "Boissons gazeuses") {
            if (aliment.calculScore() < -1) {
                r.setBackgroundResource(R.drawable.bb);
            } else if (aliment.calculScore() <= 5 && aliment.calculScore() >= 2) {
                r.setBackgroundResource(R.drawable.cc);
            } else if (aliment.calculScore() <= 9) {
                r.setBackgroundResource(R.drawable.dd);
            } else {
                r.setBackgroundResource(R.drawable.ee);
            }
        } else {
            if (aliment.calculScore() < -1) {
                r.setBackgroundResource(R.drawable.aa);
            } else if (aliment.calculScore() <= 2) {
                r.setBackgroundResource(R.drawable.bb);
            } else if (aliment.calculScore() <= 10) {
                r.setBackgroundResource(R.drawable.cc);
            } else if (aliment.calculScore() <= 18) {
                r.setBackgroundResource(R.drawable.dd);
            } else {
                r.setBackgroundResource(R.drawable.ee);
            }
        }
        ArrayList<String> allerg = (ArrayList<String>) getIntent().getSerializableExtra("allergenes");
        String ch= "";
        String taballerg[] = new String[20];
        taballerg = getIntent().getStringExtra("composants").split(",");
        if (allerg.size()!=0)
        {for (int i = 0; i < taballerg.length; i++) {
            for (int j = 0; j < allerg.size(); j++) {
                if ((taballerg[i]).equals(allerg.get(j)))
                    ch += "*"+taballerg[i]+"\n";
            }

        }}
        String ch1 = "";
        if (allergpropose.size()!=0 ) {

            for (int i = 0; i < allergpropose.size(); i++) {
                if(!(allerg.contains(allergpropose.get(i)))){
                    Toast.makeText(this, "no results" + allerg.contains(allergpropose.get(i)) , Toast.LENGTH_LONG).show();
                    ch1 += "*" + allergpropose.get(i) + "\n";}
            }
        }


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        if ((ch.length()!=0)&&(ch1.length()==0))
            builder.setMessage("Attention ! ce produit contient :\n \n"+ch);
        else if (ch1.length()!=0 ) {
            if (ch.length()!=0) {
                {
                    builder.setMessage("Attention ! ce produit contient :\n \n" + ch + "\n " + "En outre, en fonction de ce que vous avez choisi comme aliments qui ne sont pas bons pour vous, cet aliment contient \n"+ ch1+ "qui peut nuire à votre santé");
                }
            } else if (ch.length() == 0)
                builder.setMessage("\n" +
                        "en fonction de ce que vous avez choisi comme aliments qui ne sont pas bons pour vous, cet aliment contient \n" + ch1 + "qui peut nuire à votre santé");
        }
        else
            builder.setMessage("\n" +"Ce produit ne contient aucun élément auquel vous êtes allergique");

        builder.setTitle("Allergènes : ");
        builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                onStop();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
        additif = new HashMap<>();
        additif.put("E104","à eviter");
        additif.put("E133","Tolérable, vigilance pour certaines populations ");
        additif.put("E101","Acceptable");
        additif.put("E162","Acceptable");
        additif.put("E160","Tolérable, vigilance pour certaines populations");
        additif.put("E202","Acceptable");
        additif.put("E211","à eviter");
        additif.put("E300","Acceptable");
        additif.put("E330","Tolérable, vigilance pour certaines populations");
        additif.put("E339","Peu recommandable");
        additif.put("E331","Tolérable, vigilance pour certaines populations");
        additif.put("E336","Acceptable");
        additif.put("E322","Tolérable, vigilance pour certaines populations");
        additif.put("E440","Acceptable");
        additif.put("E466","Peu recommandable");
        additif.put("E410","Tolérable, vigilance pour certaines populations");
        additif.put("E471","Peu recommandable");
        additif.put("E446","");
        additif.put("E450","Peu recommandable");
        additif.put("E452","Peu recommandable");
        additif.put("E420","Tolérable, vigilance pour certaines populations");
        additif.put("E422","Tolérable, vigilance pour certaines populations");
        additif.put("E475","Peu recommandable");
        additif.put("E516","Acceptable");
        additif.put("E500","Acceptable");
        additif.put("E503","Acceptable");

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.additifs:
            {

                String tabadditifs[] = new String[20];
                tabadditifs = getIntent().getStringExtra("additifs").split(",");
                String ch1= "";
                String ch2= "";
                String ch3= "";
                String ch4= "";
                for (int i = 0; i < tabadditifs.length; i++) {
                    if(additif.get(tabadditifs[i]) == "Acceptable")
                        ch1= tabadditifs[i] + " \n" + ch1 ;
                }
                if (!ch1.isEmpty()) {ch1 += " : Acceptable" ; }
                for (int i = 0; i < tabadditifs.length; i++) {
                    if(additif.get(tabadditifs[i]) == "Tolérable, vigilance pour certaines populations")
                        ch2= tabadditifs[i] + " \n" + ch2   ;
                }
                if (!ch2.isEmpty()) {ch2 += " : Tolérable, vigilance pour certaines populations" ; }
                for (int i = 0; i < tabadditifs.length; i++) {
                    if(additif.get(tabadditifs[i]) == "Peu recommandable")
                        ch3= tabadditifs[i] + " \n " + ch3  ;
                }
                if (!ch3.isEmpty()) {ch3 += " :Peu recommandable" ; }
                for (int i = 0; i < tabadditifs.length; i++) {
                    if(additif.get(tabadditifs[i]) == "à eviter")
                        ch4= tabadditifs[i] + " \n " + ch4 ;
                }
                if (!ch4.isEmpty()) {ch4 += " : à eviter" ; }
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage(Html.fromHtml("<font> les additifs sont :  </font> <br/> <font color= #118d51>"+ch1+"</font> <br/> <font color= #fecb27>"+ch2+" </font> <br/> <font color= #f58024>"+ch3+" </font> <br/> <font color= #ed3b23>"+ch4+" </font>"));
                builder.setTitle("Additifs : ");
                builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        onStop();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }

        }
//
//
//
//
//
//
//    }


    }
}