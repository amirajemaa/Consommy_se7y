package tn.codefortunisia.lastversion;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
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

public class page3 extends AppCompatActivity implements View.OnClickListener{
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
        //ouvrir la base de données
        LoadDatabse();
        //lister toute les élements de la base en checkbox
        TestDatabse();

    }
    @Override
    public void onClick(View view) {
        // ArrayList<String> arrayList = (ArrayList<String>) getIntent().getSerializableExtra("allergenes");
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
        // vérifier pour chaque élémnet coché s'il contient des coposants allérgétiques similaire au produit
        for (int i = 0; i < noms.size(); i++) {
            LoadDatabse();
            s = noms.get(i);
            verif();
            verifAllerg();
        }
        // vérifier si le produit contient des allergénes choisis depuis la page précédente on le met dans ch
        ArrayList<String> allerg = (ArrayList<String>) getIntent().getSerializableExtra("allergenes");
        String ch= "";
        String taballerg[] = new String[20];
        taballerg = getIntent().getStringExtra("composants").split(",");
        if (allerg.size()!=0)
        {for (int i = 0; i < taballerg.length; i++) {
            if (allerg.contains(taballerg[i])){
                ch += "*"+taballerg[i]+"\n";
            }

        }}
        //si à partir des produits choisis on a conclut des allergénes possible on le met dans ch1
        String ch1 = "";
        if (allergproposé.size()!=0 ) {

            for (int i = 0; i < allergproposé.size(); i++) {
                if(!(allerg.contains(allergproposé.get(i)))){
                    ch1 += "*" + allergproposé.get(i) + "\n";}
            }
        }

        // faire apparaitre des alertes en considérant si ch1 et ch vide ou non
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        if (ch.length()!=0 || ch1.length()!=0  ) {
            if ((ch.length() != 0) && (ch1.length() == 0))
                builder.setMessage("Attention ! ce produit contient :\n \n" + ch + "voulez vous voir le nutri-score du produit quand méme ?");
            else if (ch1.length() != 0) {
                if (ch.length() != 0) {
                    {
                        builder.setMessage("Attention ! ce produit contient :\n \n" + ch + "\n " + "En outre, en fonction de ce que vous avez choisi comme aliments qui ne sont pas bons pour vous, cet aliment contient \n" + ch1 + "qui peut nuire à votre santé" + "\n voulez vous voir le nutri-score du produit quand méme ?");
                    }
                } else if (ch.length() == 0)
                    builder.setMessage("\n" +
                            "en fonction de ce que vous avez choisi comme aliments qui ne sont pas bons pour vous, cet aliment contient \n" + ch1 + "qui peut nuire à votre santé" + "\n voulez vous voir le nutri-score du produit quand méme ?");
            }

            builder.setTitle("Allergènes : ");
            //si on veut voir le nutri-score du produit quand méme
            builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    // passer les valeurs à la page suivante
                    Intent intent = new Intent(page3.this, page4.class);
                    //intent.putExtra("allergenes", allerg);
                    intent.putExtra("composants", composants);
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
                    //intent.putExtra("allerg_proposé", allergproposé);
                    startActivity(intent);
                }
            });
            //si on ne veut pas connaitre le nutri-score puisque l'élement est energétique
            builder.setNegativeButton("non", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Intent intent = new Intent(page3.this, MainActivity.class);
                    startActivity(intent);
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        }
        else {
            Intent intent = new Intent(page3.this,page4.class);
            // intent.putExtra("allergenes",arrayList);
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


    }
    private void TestDatabse() {


        try {
            //connexion a la base et extraire les élements
            DriverManager.registerDriver((Driver) Class.forName("org.sqldroid.SQLDroidDriver").newInstance());
            String database = "jdbc:sqldroid:" + getFilesDir() + "/" + dbName;
            Connection connection = DriverManager.getConnection(database);
            Statement st = connection.createStatement();
            ResultSet result = st.executeQuery("Select * from aliments order by nom");
            // construire le checkbox avec le nom de chaque produit
            while (result.next()) {
                nom = result.getString("nom") ;
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
    private void verif() {
        String bar ;
        try {
            //connexion à la base de données
            DriverManager.registerDriver((Driver) Class.forName("org.sqldroid.SQLDroidDriver").newInstance());
            String dbURL = "jdbc:sqldroid:" + getFilesDir() + "/" + dbName;
            Connection connection = DriverManager.getConnection(dbURL);
            Statement stmt = connection.createStatement();
            bar = s;
            ResultSet rs = stmt.executeQuery("Select * from aliments");
            // extraire les composants de l'élément
            while (rs.next()) {
                if(rs.getString("nom").equals(bar)) {
                    composantsaliment = rs.getString("composants");
                }
            }
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "error :" + e.getMessage(), Toast.LENGTH_LONG).show();

        }

        String tab_composants[] = new String[30]; // composants du produit à tester
        String tab_composants_aliment[] = new String[30]; //composants du produit coché
        tab_composants_aliment = composantsaliment.split(",");
        tab_composants = composants.split(",");
        // obtenir les composants similare
        for (int i = 0; i < tab_composants_aliment.length; i++) {
            for (int j = 0; j < tab_composants.length; j++) {
                if ((tab_composants_aliment[i]).equals(tab_composants[j])) {
                    similarcomposant.add(tab_composants[j]);

                }


            }
        }
    }
    private void verifAllerg(){
        // verifier pour chaque coposant similaire s'il est allégétique
        for(int i =0 ; i< similarcomposant.size() ; i++){
            if(similarcomposant.get(i)=="lactose"|| similarcomposant.get(i).equals("lait en poudre") || similarcomposant.get(i).equals("lait") ||similarcomposant.get(i).equals("crème de lait") ||
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