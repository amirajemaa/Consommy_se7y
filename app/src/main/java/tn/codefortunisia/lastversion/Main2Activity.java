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
    Button gluten,lactose,poisson,oeuf,fruit,arachides,lupin,moutarde,soja,valider,btn;
    final String dbName = "alimentt.db";
    String energie,sodium,acide,sucre,nom,composants,additifs,catégorie,fibres,proteins,quantite;
    ArrayList<String> arrayList = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        //ouvrir la base de données
        LoadDatabse();
        //extraire les données
        TestDatabse();
        //faire interconncter les bouttons du page .xml avec le fichier.java pour les manipuler
        gluten = findViewById(R.id.gluten);
        gluten.setOnClickListener(this);
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
        //selon allegéne choisis le boutton change de couleur el allergéne sera enregistré dans la list des allergénes
        switch (v.getId()) {

            case R.id.gluten: {
                //si le boutton n'est pas ciqué
                if (Integer.parseInt(gluten.getTag().toString()) == 0) {
                    arrayList.add("gluten");
                    gluten.setBackgroundColor(Color.parseColor("#1AE37A7A"));
                    gluten.setTag(1);
                }
                //si le boutton est cliqué
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
                    // ajouter tout produit en relation avec le lactose
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
                    arrayList.add("archides");
                    arachides.setBackgroundColor(Color.parseColor("#1AE37A7A"));
                    arachides.setTag(1);
                }
                else {
                    arrayList.remove("archides");
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
            case R.id.valider:
            {
                //verifier si le composant est allergétique
                String taballerg[] = new String[20];
                taballerg = composants.split(",");
                String ch= "";
                assert arrayList != null;
                //comparer les allergénes avec les composants
                if (arrayList.size()!=0)
                {
                    for (int i = 0; i < taballerg.length; i++) {
                        if ((arrayList).contains(taballerg[i])){
                            ch += "*"+taballerg[i]+"\n";
                        }

                    }
                }




                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                // si le produit est allergétique
                if ((ch.length()!=0)) {
                    builder.setMessage("Attention ! ce produit contient :\n \n" + ch + "\n voulez vous voir le nutri-score du produit quand méme ?");


                    builder.setTitle("Allergènes : ");
                    //si on veut avoir le nutri-score du produit on passe à la 4éme page en passant les données
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
                    //si on ne veut pas voir le nutri-score puisque le produit est allergétique
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

            // si on est pas sure des allergénes on passe à 3éme page
            case R.id.button:{
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("si vous ne connaissez pas les composants qui vous causent des allergies, essayez de choisir les aliments qui n'étaient pas bons pour vous.\n Nous en conclurons les allergènes qui peuvent être nocifs pour votre santé ");
                builder.setTitle("Allergènes : ");
                builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
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
            DriverManager.registerDriver((Driver) Class.forName("org.sqldroid.SQLDroidDriver").newInstance());
            String dbURL = "jdbc:sqldroid:" + getFilesDir() + "/" +dbName;
            Connection connection = DriverManager.getConnection(dbURL);
            Statement stmt = connection.createStatement();
            bar=getIntent().getStringExtra("result");
            ResultSet rs = stmt.executeQuery("Select * from aliments WHERE  code ="+bar);
            //extraire les données du produit
            while (rs.next())
            {
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
        File checkDB = null;
        try {
            checkDB = new File(getFilesDir()+"/"+dbName);
            if(!checkDB.exists())
            {
                InputStream myInput = getApplicationContext().getAssets().open(dbName);
                OutputStream myOutput = new FileOutputStream(getFilesDir()+"/"+dbName);
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