package tn.codefortunisia.lastversion;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
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
import java.sql.ResultSet;
import java.sql.Statement;

import static tn.codefortunisia.lastversion.R.id.energie;

public class Ajouter_Produit extends AppCompatActivity implements View.OnClickListener {
    final String dbName = "alimentt.db";
    EditText code,nom,catégorie,composants, additifs , energie , sucre , acide , soduim  , protein , fibre ,quantité ;
    String nom1, catégorie1 , composants1 , additifs1;
    Double energie1 , sucre1 , acide1 , soduim1  , protein1 , fibre1 ,quantité1 , code1;
    Button ok , annuler;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouter__produit);

         nom = findViewById(R.id.nom);
         catégorie = findViewById(R.id.catégorie);
         composants = findViewById(R.id.composants);
         additifs = findViewById(R.id.additifs);
         energie = findViewById(R.id.energie);
         sucre = findViewById(R.id.sucre);
         acide = findViewById(R.id.acide);
         soduim = findViewById(R.id.sodium);
         protein = findViewById(R.id.protein);
         fibre = findViewById(R.id.fibre);
         quantité = findViewById(R.id.quantité);
         code = findViewById(R.id.code);
         code.setText(getIntent().getStringExtra("result"));
         ok = findViewById(R.id.ok);
         annuler = findViewById(R.id.annuler);
         ok.setOnClickListener(this);



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
    private void TestDatabse() {

        try {

            DriverManager.registerDriver((Driver) Class.forName("org.sqldroid.SQLDroidDriver").newInstance());
            String dbURL = "jdbc:sqldroid:" + getFilesDir() + "/" +dbName;
            Connection connection = DriverManager.getConnection(dbURL);
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("Insert into aliments(code,nom,catégorie,composants,additifs,energie,sucres,acides,sodium,proteins,fibres,quantite) values("+Double.parseDouble(getIntent().getStringExtra("result"))+"," + nom.getText().toString() +",\"hh\",\"hh\",\"hh\",44,23,56,13,45,36,456);");


            Toast.makeText(getApplicationContext(), "produit enregistré",Toast.LENGTH_LONG).show();
            connection.close();
        }
        catch (Exception e)
        {
            Toast.makeText(getApplicationContext(), "error :" + e.getMessage(),Toast.LENGTH_LONG).show();

        }}
        public void onClick(View v) {
            switch (v.getId()) {

                case R.id.ok: {
//                    code1= Double.parseDouble(getIntent().getStringExtra("code"));
//                    nom1 = nom.getText().toString();
//                    catégorie1 = catégorie.getText().toString();
//                    composants1 = composants.getText().toString();
//                    additifs1 =additifs.getText().toString();
//                    energie1 =Double.parseDouble(energie.getText().toString());
//                    sucre1 = Double.parseDouble(sucre.getText().toString());
//                    acide1 = Double.parseDouble(acide.getText().toString());
//                    soduim1 = Double.parseDouble(soduim.getText().toString());
//                    protein1 = Double.parseDouble(protein.getText().toString());
//                    fibre1=Double.parseDouble(fibre.getText().toString());
//                    quantité1 = Double.parseDouble(quantité.getText().toString());
                    LoadDatabse();
                    TestDatabse();
                    //Intent intent = new Intent(Ajouter_Produit.this,Main2Activity.class);
                   // startActivity(intent);
                    break;
                }
                case R.id.annuler :{
                    Intent intent = new Intent(Ajouter_Produit.this,MainActivity.class);
                    startActivity(intent);

                }
                }
        }
}