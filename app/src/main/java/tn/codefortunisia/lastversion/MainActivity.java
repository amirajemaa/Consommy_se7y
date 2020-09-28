package tn.codefortunisia.lastversion;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button scanBtn;
    final String dbName = "alimentt.db";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        scanBtn = findViewById(R.id.scanBtn);
        scanBtn.setOnClickListener(this);

    }
    //appuyer sur le bouton scanBtn conduit à commencer à scanner le code
    @Override
    public void onClick(View view) {
        scanCode();

    }

    //scanner le code à barre
    private void scanCode() {
        IntentIntegrator integrator = new IntentIntegrator(this);//intégration avec Barcode Scanner via Intent
        integrator.setCaptureActivity(Capture.class);
        integrator.setOrientationLocked(false);//ajuster l'orientation du téléphone lors de la capture du code à barres
        integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);//accepter tous les types du code à barre
        integrator.setPrompt("Scanning code");
        integrator.initiateScan();

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        final IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null)
        {
            if (result.getContents() != null) {
                LoadDatabse();
                if (TestDatabse(result.getContents()))
                { AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setMessage(result.getContents());
                    builder.setTitle("scanning Result");
                    builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            scanCode();
                            Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                            intent.putExtra("result", result.getContents());
                            startActivity(intent);
                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();

                }
                else
                {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setMessage("Désolé cet aliment n'est pas enregistré dans la base de données \n voulez vous l'ajouter?");
                    builder.setTitle("scanning Result");
                    builder.setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            Intent intent = new Intent(MainActivity.this,AjouterProduit.class);
                            intent.putExtra("result", result.getContents());
                            startActivity(intent);

                        }
                    });
                    builder.setNegativeButton("Non", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            onStop();
                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
            }

        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
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
    private boolean TestDatabse(String bar) {


        try {
            //charge SQLDRoid JBDC driver
            DriverManager.registerDriver((Driver) Class.forName("org.sqldroid.SQLDroidDriver").newInstance());
            //établir une connexion à notre base de données dans la mémoire interne
            String dbURL = "jdbc:sqldroid:" + getFilesDir() + "/" +dbName;
            Connection connection = DriverManager.getConnection(dbURL);
            //préparer la route à partir de la base de données
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("Select * from aliments WHERE  code ="+bar);
            //boucle à travers l'ensemble de résultats
            while(rs.next())
            {
                return true;
            }
            connection.close();
        }
        catch (Exception e)
        {
            return false;

        }
        return false;
    }
}