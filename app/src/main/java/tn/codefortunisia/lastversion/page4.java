package tn.codefortunisia.lastversion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
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

public class page4 extends AppCompatActivity implements View.OnClickListener{
    Button btn;
    String energie,sodium,acide,sucre,nom,composants,additifs,catégorie,fibres,proteins,quantite;
    final String dbName = "alimentt.db";
    String  composantsaliment,s;
    LinearLayout parent;
    ArrayList<String> noms = new ArrayList<>();
    ArrayList<String> allerg;
    ArrayList<String> allergproposé = new ArrayList<>();
    ArrayList<String> similarcomposant = new ArrayList<>() ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page4);
        btn = findViewById(R.id.button3);
        btn.setOnClickListener(this);
        parent = findViewById(R.id.parent);
        composants = getIntent().getStringExtra("composants");
        LoadDatabse();
        TestDatabse();

    }
    @Override
    public void onClick(View view) {
        ArrayList<String> arrayList = (ArrayList<String>) getIntent().getSerializableExtra("allergenes");
        //  composants=getIntent().getStringExtra("composants");
        energie=getIntent().getStringExtra("energie");
        sucre=getIntent().getStringExtra("sucre");
        sodium=getIntent().getStringExtra("sodium");
        acide=getIntent().getStringExtra("acide");
        fibres=getIntent().getStringExtra("fibres");
        proteins=getIntent().getStringExtra("proteins");
        nom=getIntent().getStringExtra("nom");
        catégorie=getIntent().getStringExtra("catégorie");
        additifs=getIntent().getStringExtra("additifs");
        quantite=getIntent().getStringExtra("quantite");
        for (int i = 0; i < noms.size(); i++) {
            LoadDatabse();
            s = noms.get(i);
            verif();
            verifAllerg();
        }
        Intent intent = new Intent(page4.this,page3.class);
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
        intent.putExtra("allerg_proposé",allergproposé);
        startActivity(intent);

    }
    private void TestDatabse() {


        try {
            DriverManager.registerDriver((Driver) Class.forName("org.sqldroid.SQLDroidDriver").newInstance());
            String dbURL = "jdbc:sqldroid:" + getFilesDir() + "/" + dbName;
            Connection connection = DriverManager.getConnection(dbURL);
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("Select * from aliments");
            while (rs.next()) {
                nom = rs.getString("nom");
                CheckBox ch1;
                ch1 = new CheckBox(getApplicationContext());
                ch1.setText(nom);
                ch1.setTextColor(-16777216);
                parent.addView(ch1);
                ch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton Button, boolean isChecked) {
                        if (isChecked) {
                            noms.add(Button.getText().toString());
                        } else {
                            noms.remove(noms.indexOf(Button.getText().toString()));
                        }
                    }
                });
            }

            connection.close();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "error :" + e.getMessage(), Toast.LENGTH_LONG).show();

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

    private void verif() {
        String bar ;
        try {

            DriverManager.registerDriver((Driver) Class.forName("org.sqldroid.SQLDroidDriver").newInstance());
            String dbURL = "jdbc:sqldroid:" + getFilesDir() + "/" + dbName;
            Connection connection = DriverManager.getConnection(dbURL);
            Statement stmt = connection.createStatement();
            bar = s;
            ResultSet rs = stmt.executeQuery("Select * from aliments");
            while (rs.next()) {
                if(rs.getString("nom").equals(bar)) {
                    composantsaliment = rs.getString("composants");
                }
            }
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "error :" + e.getMessage(), Toast.LENGTH_LONG).show();

        }

        String tab_composants[] = new String[30];
        String tab_composants_aliment[] = new String[30];
        tab_composants_aliment = composantsaliment.split(",");
        tab_composants = composants.split(",");
        for (int i = 0; i < tab_composants_aliment.length; i++) {
            for (int j = 0; j < tab_composants.length; j++) {
                if ((tab_composants_aliment[i]).equals(tab_composants[j])) {
                    similarcomposant.add(tab_composants[j]);

                }


            }
        }
    }
    private void verifAllerg(){
        for(int i =0 ; i< similarcomposant.size() ; i++){
            if(similarcomposant.get(i).equals("lactose")|| similarcomposant.get(i).equals("lait en poudre") || similarcomposant.get(i).equals("lait") ||similarcomposant.get(i).equals("crème de lait") ||
                    similarcomposant.get(i).equals("protéines laitières") || similarcomposant.get(i).equals("lait frais") || similarcomposant.get(i).equals("lait écrémé en poudre") || similarcomposant.get(i).equals("beurre de lait")
                    || similarcomposant.get(i).equals("lait demi-écrémé") || similarcomposant.get(i).equals("lait entier") || similarcomposant.get(i).equals("ferment lactique") || similarcomposant.get(i).equals("ferment lactique de yaourt")
                    || similarcomposant.get(i).equals("ferment lactique sélectionné") || similarcomposant.get(i).equals("graisse de beurre pur") || similarcomposant.get(i).equals("beurre") || similarcomposant.get(i).equals("oeuf")
                    || similarcomposant.get(i).equals("poisson") || similarcomposant.get(i).equals("soja") || similarcomposant.get(i).equals("fruit à coques") || similarcomposant.get(i).equals("archides")
                    || similarcomposant.get(i).equals("lupin") || similarcomposant.get(i).equals("moutarde")){
                if(allergproposé.contains(similarcomposant.get(i))==false)
                    allergproposé.add(similarcomposant.get(i));


            }
        }}

}
