package tn.codefortunisia.lastversion;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener{

    Button gluten,lactose,poisson,oeuf,fruit,arachides,lupin,moutarde,soja,valider,btn; ////la liste des allergènes
    final String dbName = "alimentt.db";//une base de données stockant toutes les informations alimentaires
    String energie,sodium,acide,sucre,nom,composants,additifs,catégorie,fibres,proteins,quantite;// les informations nutritionnelles
    ArrayList<String> arrayList = new ArrayList<String>();//une liste de tous les allergènes sélectionnés y sera enregistrée


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        //charger la base de données
        LoadDatabse();
        //extraire toutes les informations nutritionnelles du produit scanné
        TestDatabse();
        gluten = findViewById(R.id.gluten);//findViewById connecte votre code backend aux éléments de l'interface utilisateur
        gluten.setOnClickListener(this);//setOnClickListener pour lier listner à certains attributs
        lactose = findViewById(R.id.lactose);
        lactose.setOnClickListener(this);
        oeuf = findViewById(R.id.oeuf);
        oeuf.setOnClickListener(this);
        poisson = findViewById(R.id.poisson);
        poisson.setOnClickListener(this);
        soja = findViewById(R.id.soja);
        soja.setOnClickListener(this);
        arachides = findViewById(R.id.arachides);
        arachides.setOnClickListener(this);
        fruit = findViewById(R.id.fruit);
        fruit.setOnClickListener(this);
        soja = findViewById(R.id.soja);
        soja.setOnClickListener(this);
        lupin = findViewById(R.id.lupin);
        lupin.setOnClickListener(this);
        moutarde = findViewById(R.id.moutarde);
        moutarde.setOnClickListener(this);
        valider = findViewById(R.id.valider);
        valider.setOnClickListener(this);
        btn = findViewById(R.id.button);
        btn.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
    //choix des allergènes dont l'utilisateur a une hypersensibilité
        switch (v.getId()) {

            case R.id.gluten: {
                //colorer la case choisie et l'enregistrer dans la liste
                if (Integer.parseInt(gluten.getTag().toString()) == 0) {
                    arrayList.add("gluten");
                    gluten.setBackgroundColor(Color.parseColor("#1AE37A7A"));
                    gluten.setTag(1);
                }
                //décolorer la case si l'utilisateur change son avis et le retirer de la liste
                else {
                    gluten.setBackgroundColor(Color.TRANSPARENT);
                    gluten.setTag(0);
                    arrayList.remove("gluten");
                }
                break;
            }
            case R.id.lactose: {
                if (Integer.parseInt(lactose.getTag().toString()) == 0) {
                    lactose.setBackgroundColor(Color.parseColor("#1AE37A7A"));
                    //tous les dérivés du lait
                    arrayList.add("lactose");
                    arrayList.add("lait en poudre");
                    arrayList.add("lait");
                    arrayList.add("crème de lait");
                    arrayList.add("protéines laitières");
                    arrayList.add("lait frais");
                    arrayList.add("lait écrémé en poudre");
                    arrayList.add("beurre de lait");
                    arrayList.add("lait demi-écrémé");
                    arrayList.add("lait entier");
                    arrayList.add("ferment lactique");
                    arrayList.add("ferment lactique de yaourt");
                    arrayList.add("ferment lactique sélectionné");
                    arrayList.add("graisse de beurre pur");
                    arrayList.add("beurre");
                    lactose.setTag(1);

                }
                else{
                    lactose.setBackgroundColor(Color.TRANSPARENT);
                    lactose.setTag(0);
                    arrayList.remove("lactose");
                    arrayList.remove("lait en poudre");
                    arrayList.remove("lait");
                    arrayList.remove("crème de lait");
                    arrayList.remove("protéines laitières");
                    arrayList.remove("lait frais");
                    arrayList.remove("lait écrémé en poudre");
                    arrayList.remove("beurre de lait");
                    arrayList.remove("lait demi-écrémé");
                    arrayList.remove("lait entier");
                    arrayList.remove("ferment lactique");
                    arrayList.remove("ferment lactique de yaourt");
                    arrayList.remove("ferment lactique sélectionné");
                    arrayList.remove("graisse de beurre pur");
                    arrayList.remove("beurre");
                    lactose.setTag(0);
                }
                break;
            }
            case R.id.oeuf:{
                if (Integer.parseInt(oeuf.getTag().toString()) == 0) {
                    oeuf.setBackgroundColor(Color.parseColor("#1AE37A7A"));
                    arrayList.add("oeuf");
                    oeuf.setTag(1);
                }
                else {
                    oeuf.setBackgroundColor(Color.TRANSPARENT);
                    oeuf.setTag(0);
                    arrayList.remove("oeuf");
                }
                break;
            }
            case R.id.poisson:{

                if(Integer.parseInt(poisson.getTag().toString()) == 0) {
                    arrayList.add("poisson");
                    poisson.setBackgroundColor(Color.parseColor("#1AE37A7A"));
                    poisson.setTag(1);
                }
                else {
                    arrayList.remove("poisson");
                    poisson.setBackgroundColor(Color.TRANSPARENT);
                    poisson.setTag(0);
                }
                break;
            }
            case R.id.soja:{
                if(Integer.parseInt(soja.getTag().toString()) == 0) {
                    arrayList.add("soja");
                    soja.setBackgroundColor(Color.parseColor("#1AE37A7A"));
                    soja.setTag(1);
                }
                else {
                    arrayList.remove("soja");
                    soja.setBackgroundColor(Color.TRANSPARENT);
                    soja.setTag(0);
                }
                break;
            }
            case R.id.fruit:{
                if(Integer.parseInt(fruit.getTag().toString()) == 0) {
                    arrayList.add("fruit à coques");
                    fruit.setBackgroundColor(Color.parseColor("#1AE37A7A"));
                    fruit.setTag(1);
                }
                else {
                    arrayList.remove("fruit à coques");
                    fruit.setBackgroundColor(Color.TRANSPARENT);
                    fruit.setTag(0);
                }
                break;}
            case R.id.arachides:{
                if(Integer.parseInt(arachides.getTag().toString()) == 0) {
                    arrayList.add("arachides");
                    arachides.setBackgroundColor(Color.parseColor("#1AE37A7A"));
                    arachides.setTag(1);
                }
                else {
                    arrayList.remove("arachides");
                    arachides.setBackgroundColor(Color.TRANSPARENT);
                    arachides.setTag(0);
                }
                break;}
            case R.id.lupin: {
                if(Integer.parseInt(lupin.getTag().toString()) == 0) {
                    arrayList.add("lupin");
                    lupin.setBackgroundColor(Color.parseColor("#1AE37A7A"));
                    lupin.setTag(1);
                }
                else {
                    arrayList.remove("lupin");
                    lupin.setBackgroundColor(Color.TRANSPARENT);
                    lupin.setTag(0);
                }
                break;}
            case R.id.moutarde:
            {
                if(Integer.parseInt(moutarde.getTag().toString()) == 0) {
                    arrayList.add("moutarde");
                    moutarde.setBackgroundColor(Color.parseColor("#1AE37A7A"));
                    moutarde.setTag(1);
                }
                else {
                    arrayList.remove("moutarde");
                    moutarde.setBackgroundColor(Color.TRANSPARENT);
                    moutarde.setTag(0);
                }
                break;
            }
            //en cliquant sur le bouton valider toutes les informations concernant le produit scanné seront transférées à la page suivante
            case R.id.valider:
            {
                String taballerg[] = new String[20];
                taballerg = composants.split(",");
                String ch= "";
                assert arrayList != null;
                if (arrayList.size()!=0)
                {
                    for (int i = 0; i < taballerg.length; i++) {
                        if ((arrayList).contains(taballerg[i])){
                            ch += "*"+taballerg[i]+"\n";
                        }

                    }
                }




                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                if ((ch.length()!=0)) {
                    builder.setMessage("Attention ! ce produit contient :\n \n" + ch + "\n voulez vous voir le nutri-score du produit quand méme ?");


                    builder.setTitle("Allergènes : ");
                    builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent intent = new Intent(Main2Activity.this, page4.class);
                            intent.putExtra("allergenes", arrayList);
                            intent.putExtra("result", getIntent().getStringExtra("result"));
                            intent.putExtra("energie", energie);
                            intent.putExtra("sucre", sucre);
                            intent.putExtra("sodium", sodium);
                            intent.putExtra("acide", acide);
                            intent.putExtra("fibres", fibres);
                            intent.putExtra("proteins", proteins);
                            intent.putExtra("nom", nom);
                            intent.putExtra("catégorie", catégorie);
                            intent.putExtra("additifs", additifs);
                            intent.putExtra("composants", composants);
                            intent.putExtra("quantite", quantite);
                            startActivity(intent);
                        }
                    });
                    builder.setNegativeButton("non", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent intent = new Intent(Main2Activity.this, MainActivity.class);
                            startActivity(intent);
                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
                else {
                    Intent intent = new Intent(Main2Activity.this, page4.class);
                    intent.putExtra("allergenes", arrayList);
                    intent.putExtra("result", getIntent().getStringExtra("result"));
                    intent.putExtra("energie", energie);
                    intent.putExtra("sucre", sucre);
                    intent.putExtra("sodium", sodium);
                    intent.putExtra("acide", acide);
                    intent.putExtra("fibres", fibres);
                    intent.putExtra("proteins", proteins);
                    intent.putExtra("nom", nom);
                    intent.putExtra("catégorie", catégorie);
                    intent.putExtra("additifs", additifs);
                    intent.putExtra("composants", composants);
                    intent.putExtra("quantite", quantite);
                    startActivity(intent);
                }


                break;
            }

            //Ce bouton est réservé à ceux qui ne connaissent pas les composants qui leur causent des allergies
            case R.id.button:{
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("si vous ne connaissez pas les composants qui vous causent des allergies, essayez de choisir les aliments qui n'étaient pas bons pour vous.\n Nous en conclurons les allergènes qui peuvent être nocifs pour votre santé ");
                builder.setTitle("Allergènes : ");
                builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //toutes les informations concernant le produit scanné seront transférées à la page suivante
                        Intent intent = new Intent(Main2Activity.this, page3.class);
                        intent.putExtra("allergenes",arrayList);
                        intent.putExtra("composants",composants);
                        intent.putExtra("energie",energie);
                        intent.putExtra("sucre",sucre);
                        intent.putExtra("sodium",sodium);
                        intent.putExtra("acide",acide);
                        intent.putExtra("fibres",fibres);
                        intent.putExtra("proteins",proteins);
                        intent.putExtra("nom",nom);
                        intent.putExtra("catégorie",catégorie);
                        intent.putExtra("additifs",additifs);
                        intent.putExtra("composants",composants);
                        intent.putExtra("quantite",quantite);
                        startActivity(intent);
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();

                break;
            }




        }}
    private void TestDatabse() {

        String bar;
        try {
            //charge SQLDRoid JBDC driver
            DriverManager.registerDriver((Driver) Class.forName("org.sqldroid.SQLDroidDriver").newInstance());
            //établir une connexion à notre base de données dans la mémoire interne
            String dbURL = "jdbc:sqldroid:" + getFilesDir() + "/" +dbName;
            Connection connection = DriverManager.getConnection(dbURL);
            //préparer la route à partir de la base de données
            Statement stmt = connection.createStatement();
            //extraire le code à barre
            bar=getIntent().getStringExtra("result");
            //boucle à travers l'ensemble de résultats
            ResultSet rs = stmt.executeQuery("Select * from aliments WHERE  code ="+bar);
            while (rs.next())
            {
                //extraire toutes les informations nutritionnelles
                energie=rs.getString("energie");
                sucre=rs.getString("sucres");
                sodium=rs.getString("sodium");
                acide=rs.getString("acides");
                nom=rs.getString("nom");
                composants=rs.getString("composants");
                additifs=rs.getString("additifs");
                catégorie=rs.getString("catégorie");
                proteins=rs.getString("proteins");
                fibres=rs.getString("fibres");
                quantite= rs.getString("quantite");
            }
            connection.close();
        }
        catch (Exception e)
        {
            Toast.makeText(getApplicationContext(), "error :" + e.getMessage(),Toast.LENGTH_LONG).show();

        }}
    private void LoadDatabse()
    {
        File checkDB = null;//le fichier que nous essaierons d'écrire dans la mémoire interne
        try {
            //  obtenir un pointeur dans notre base de données
            checkDB = new File(getFilesDir()+"/"+dbName);
            if(!checkDB.exists())
            {
                //copier dans la base de données existante à partir du dossier assets
                InputStream myInput = getApplicationContext().getAssets().open(dbName);
                //le réécrire dans la mémoire interne
                OutputStream myOutput = new FileOutputStream(getFilesDir()+"/"+dbName);
                //transférer les bytes du fichier d'entrée vers le fichier de sortie
                byte[] buffer = new byte[1024];
                int length;
                while (( length = myInput.read(buffer))>0)
                {
                    myOutput.write(buffer,0,length);
                }
                myOutput.flush();
                myOutput.close();
                myInput.close();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}