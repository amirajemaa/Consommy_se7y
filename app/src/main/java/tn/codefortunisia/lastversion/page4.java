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
    String  composants_aliment,s;
    LinearLayout parent;
    ArrayList<String> noms = new ArrayList<>();
    ArrayList<String> allerg;
    ArrayList<String> allerg_proposé = new ArrayList<>();
    ArrayList<String> similar_composant = new ArrayList<>() ;

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
            verif_allerg();
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
        intent.putExtra("allerg_proposé",allerg_proposé);
        startActivity(intent);

    }
    private void TestDatabse() {


        try {
            DriverManager.registerDriver((Driver) Class.forName("org.sqldroid.SQLDroidDriver").newInstance());
            String dbURL = "jdbc:sqldroid:" + getFilesDir() + "/" + dbName;
            Connection connection = DriverManager.getConnection(dbURL);
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("Select * from aliments Order by nom");
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
                    composants_aliment = rs.getString("composants");
                }
            }
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "error :" + e.getMessage(), Toast.LENGTH_LONG).show();

        }

        String tab_composants[] = new String[30];
        String tab_composants_aliment[] = new String[30];
        tab_composants_aliment = composants_aliment.split(",");
        tab_composants = composants.split(",");
        for (int i = 0; i < tab_composants_aliment.length; i++) {
            for (int j = 0; j < tab_composants.length; j++) {
                if ((tab_composants_aliment[i]).equals(tab_composants[j])) {
                    similar_composant.add(tab_composants[j]);

                }


            }
        }
    }
    private void verif_allerg(){
        for(int i =0 ; i< similar_composant.size() ; i++){
            if(similar_composant.get(i).equals("lactose")|| similar_composant.get(i).equals("lait en poudre") || similar_composant.get(i).equals("lait") ||similar_composant.get(i).equals("crème de lait") ||
                    similar_composant.get(i).equals("protéines laitières") || similar_composant.get(i).equals("lait frais") || similar_composant.get(i).equals("lait écrémé en poudre") || similar_composant.get(i).equals("beurre de lait")
                    || similar_composant.get(i).equals("lait demi-écrémé") || similar_composant.get(i).equals("lait entier") || similar_composant.get(i).equals("ferment lactique") || similar_composant.get(i).equals("ferment lactique de yaourt")
                    || similar_composant.get(i).equals("ferment lactique sélectionné") || similar_composant.get(i).equals("graisse de beurre pur") || similar_composant.get(i).equals("beurre") || similar_composant.get(i).equals("oeuf")
                    || similar_composant.get(i).equals("poisson") || similar_composant.get(i).equals("soja") || similar_composant.get(i).equals("fruit à coques") || similar_composant.get(i).equals("archides")
                    || similar_composant.get(i).equals("lupin") || similar_composant.get(i).equals("moutarde")){
                if(allerg_proposé.contains(similar_composant.get(i))==false)
                    allerg_proposé.add(similar_composant.get(i));


            }
        }}

}
