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
    private void LoadDatabse() {
        File checkDB = null;
        try {
            checkDB = new File(getFilesDir() + "/" + dbName);
            if (!checkDB.exists()) {
                InputStream myInput = getApplicationContext().getAssets().open(dbName);
                OutputStream myOutput = new FileOutputStream(getFilesDir() + "/" + dbName);
                byte[] buffer = new byte[1024];
                int length;
                while ((length = myInput.read(buffer)) > 0) {
                    myOutput.write(buffer, 0, length);
                }
                myOutput.flush();
                myOutput.close();
                myInput.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private boolean TestDatabse() {

        try {

            DriverManager.registerDriver((Driver) Class.forName("org.sqldroid.SQLDroidDriver").newInstance());
            String dbURL = "jdbc:sqldroid:" + getFilesDir() + "/" +dbName;
            Connection connection = DriverManager.getConnection(dbURL);
            Statement stmt = connection.createStatement();
            if ( nom.getText().toString().isEmpty() || catégorie.getText().toString().isEmpty()|| additifs.getText().toString().isEmpty() || composants.getText().toString().isEmpty() || energie.getText().toString().isEmpty() || sucre.getText().toString().isEmpty() || acide.getText().toString().isEmpty() || soduim.getText().toString().isEmpty() || protein.getText().toString().isEmpty() || fibre.getText().toString().isEmpty() || quantité.getText().toString().isEmpty()){
                Toast.makeText(getApplicationContext(), "remplir tous les données méme avec \"\" oû avec des zéros" ,Toast.LENGTH_LONG).show();
                connection.close();
                return false;

            }
            else {
                //inserer le produit
                stmt.executeUpdate("Insert into aliments(code,nom,catégorie,additifs,composants,energie,sucres,acides,sodium,proteins,fibres,quantite) values(" + getIntent().getStringExtra("result") + "," + nom.getText().toString() + "," + catégorie.getText().toString() + "," + additifs.getText().toString() + "," + composants.getText().toString() + "," + energie.getText() + "," + sucre.getText() + "," + acide.getText() + "," + soduim.getText() + "," + protein.getText() + "," + fibre.getText() + "," + quantité.getText() + ");");


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
