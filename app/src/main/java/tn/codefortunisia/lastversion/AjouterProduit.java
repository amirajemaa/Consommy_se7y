package tn.codefortunisia.lastversion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.Statement;

public class AjouterProduit extends AppCompatActivity implements View.OnClickListener {
    EditText code,nom,catégorie,composants, additifs , energie , sucre , acide , soduim  , protein , fibre ,quantité ;
    final String dbName = "alimentt.db";
    Button ok , annuler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouter_produit);
        //faire récuperer les editText et les bouttons du page .xml
        nom = findViewById(R.id.nom);
        catégorie = findViewById(R.id.catégorie);
        composants = findViewById(R.id.composants);
        additifs = findViewById(R.id.additifs);
        energie = findViewById(R.id.energie);
        sucre = findViewById(R.id.sucre);
        acide = findViewById(R.id.acide);
        soduim = findViewById(R.id.soduim);
        protein = findViewById(R.id.protein);
        fibre = findViewById(R.id.fibre);
        quantité = findViewById(R.id.quantité);
        code = findViewById(R.id.code);
        code.setText(getIntent().getStringExtra("result"));
        ok = findViewById(R.id.ok);
        annuler = findViewById(R.id.annuler);
        ok.setOnClickListener(this);
        annuler.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.ok: {
                //ouvrir la base de données
                LoadDatabse();
                //inserer le produit
                if(TestDatabse()){
                    Intent intent = new Intent(AjouterProduit.this, Main2Activity.class);
                    intent.putExtra("result",getIntent().getStringExtra("result"));
                    startActivity(intent);
                    break;
                }
                else {
                    break;
                }
            }
            case R.id.annuler :{
                Intent intent = new Intent(AjouterProduit.this,MainActivity.class);
                startActivity(intent);
                break;

            }
        }
    }
    private void LoadDatabse()
    {
        File database = null;//le fichier que nous essaierons d'écrire dans la mémoire interne
        try {
            //  obtenir un pointeur dans notre base de données
            database = new File(getFilesDir()+"/"+dbName);
            if(!database.exists())
            {
                //copier dans la base de données existante à partir du dossier assets
                InputStream entree = getApplicationContext().getAssets().open(dbName);
                //le réécrire dans la mémoire interne
                OutputStream sortie = new FileOutputStream(getFilesDir()+"/"+dbName);
                //transférer les bytes du fichier d'entrée vers le fichier de sortie
                byte[] b = new byte[1024];
                int longueur;
                while (( longueur = entree.read(b))>0)
                {
                    sortie.write(b,0,longueur);
                }
                sortie.flush();
                sortie.close();
                entree.close();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    private boolean TestDatabse() {

        try {

            DriverManager.registerDriver((Driver) Class.forName("org.sqldroid.SQLDroidDriver").newInstance());
            String database = "jdbc:sqldroid:" + getFilesDir() + "/" +dbName;
            Connection connection = DriverManager.getConnection(database);
            Statement st = connection.createStatement();
            if ( nom.getText().toString().isEmpty() || catégorie.getText().toString().isEmpty()|| additifs.getText().toString().isEmpty() || composants.getText().toString().isEmpty() || energie.getText().toString().isEmpty() || sucre.getText().toString().isEmpty() || acide.getText().toString().isEmpty() || soduim.getText().toString().isEmpty() || protein.getText().toString().isEmpty() || fibre.getText().toString().isEmpty() || quantité.getText().toString().isEmpty()){
                Toast.makeText(getApplicationContext(), "remplir tous les données méme avec \"\" où avec des zéros" ,Toast.LENGTH_LONG).show();
                connection.close();
                return false;

            }
            else {
                //inserer le produit
                st.executeUpdate("Insert into aliments(code,nom,catégorie,additifs,composants,energie,sucres,acides,sodium,proteins,fibres,quantite) values(" + getIntent().getStringExtra("result") + "," + nom.getText().toString() + "," + catégorie.getText().toString() + "," + additifs.getText().toString() + "," + composants.getText().toString() + "," + energie.getText() + "," + sucre.getText() + "," + acide.getText() + "," + soduim.getText() + "," + protein.getText() + "," + fibre.getText() + "," + quantité.getText() + ");");


                Toast.makeText(getApplicationContext(), "produit enregistré", Toast.LENGTH_LONG).show();
                connection.close();
                return true;

            }



        }
        catch (Exception e)
        {
            Toast.makeText(getApplicationContext(), "error :" + e.getMessage(),Toast.LENGTH_LONG).show();
            return false;

        }}
}