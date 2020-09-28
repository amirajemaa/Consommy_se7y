package tn.codefortunisia.lastversion;

import android.widget.Toast;

import java.util.Arrays;
import java.text.DecimalFormat;
import static java.lang.Math.round;


public class NatureAliment {
    double energy;
    double sugar;
    double acide;
    double soduim;
    double fruit;
    double driedfruit;
    double nonfruit;
    double protein;
    double fibre;
    double quantité;
    String ingrédients;
    String catégorie;

    String fruits[] = {
            "pomme", "poire", "coing", "néflier", "datte", "litchi", "kaki", "baies", "raisin", "cerise", "cassis", "fraise", "rouge raisin de corinthe", "mûre", "canneberge", "myrtille", "citron", "orange", "pamplemousse", "kumquat", "mandarine", "banane", "kiwi", "ananas", "melon", "figue", "mangue", "fruit de la passion", "goyave", "papaye", "grenade", "carambole", "durian", "ramboutan", "bonbon", "figue de barbarie", "sapotille", "fruit à pain", "tamarillo", "tamarin",

            "endive", "laitue", "épinard", "laitue d'agneau", "pissenlit", "ortie", "betterave", "oseille", "chou", "céleri", "fenouil", "rhubarbe", "asperge", "chicorée", "artichaut globe", "curs de palmier", "pousse de bambou", "pousse de taro", "oignon", "échalote", "poireau", "ail", "ciboulette", "persil", "carotte", "salsifis", "céleri-rave", "radis", "panais", "betterave", "racine de chicorée", "tomate", "aubergine", "concombre", "courgette", "sucré poivre", "piment rouge", "courge", "banane verte", "plantain", "avocat", "olive", "cornichon", "pois", "fève", "maïs sucré", "soja", "champignons comestibles", "algues",
            "pois", "haricot", "lentille", "niébé", "caroube", "fève",
            "arachide", "châtaignes",
            "Colza", "huile d'olive", "poudre de cacao"
    };
    String fruitsec[] = {"amande", "cacahuète", "raisin sec", "châtaigne", "noisette", "noisette chilienne", "noix", "noix de cajou", "noix de coco", "noix de macadamia", "noix de pécan", "noix du Brésil", "pignon de pin", "pistache", "abricot", "datte seche", "figue", "pruneau"};

    NatureAliment(double energy, double sugar, double acide, double soduim, double protein, double fibre, double quantité, String ingrédients,String catégorie) {
        this.energy = energy;
        this.sugar = sugar;
        this.acide = acide;
        this.soduim = soduim;
        this.fibre = fibre;
        this.protein = protein;
        this.quantité = quantité;
        this.ingrédients = ingrédients;
        this.catégorie = catégorie ;
        fruit = 0;
        driedfruit = 0;
        nonfruit = 0;
    }

    public NatureAliment(String energy, String sugar, String acide, String soduim, String protein, String fibre, String quantité, String ingrédients ,String catégorie) {
        DecimalFormat df = new DecimalFormat("#####.0");
        this.energy = round(Double.parseDouble(energy));
        this.sugar = round(Double.parseDouble(sugar)*10)/10;
        this.acide = round(Double.parseDouble(acide));
        this.soduim = round(Double.parseDouble(soduim));
        this.fibre = round(Double.parseDouble(fibre)*10)/10;
        this.protein = round(Double.parseDouble(protein)*10)/10;
        this.ingrédients = ingrédients;
        this.catégorie = catégorie;
        this.quantité= Double.parseDouble(quantité);
        fruit = 0;
        driedfruit = 0;
        nonfruit = 0;
    }
    public NatureAliment(){}
    // retourne le score de l'energie à partir de la quantité d'energie offert par le produit
    int calculEnergy() {
        if (energy <= 335) {
            return 0;
        } else if (energy <= 670) {
            return 1;
        } else if (energy <= 1005) {
            return 2;
        } else if (energy <= 1340) {
            return 3;
        } else if (energy <= 1675) {
            return 4;
        } else if (energy <= 2010) {
            return 5;
        } else if (energy <= 2345) {
            return 6;
        } else if (energy <= 2680) {
            return 7;
        } else if (energy <= 3015) {
            return 8;
        } else if (energy <= 3350) {
            return 9;
        } else return 10;

    }
    // calculer le score du sucre à partir de la quantité du sucre dans le produit
    int calculSugar() {
        if (sugar <= 4.5) {
            return 0;
        } else if (sugar <= 9) {
            return 1;
        } else if (sugar <= 13.5) {
            return 2;
        } else if (sugar <= 18) {
            return 3;
        } else if (sugar <= 22.5) {
            return 4;
        } else if (sugar <= 27) {
            return 5;
        } else if (sugar <= 31) {
            return 6;
        } else if (sugar <= 36) {
            return 7;
        } else if (sugar <= 40) {
            return 8;
        } else if (sugar <= 45) {
            return 9;
        } else return 10;

    }
    // calculer le score de l'acide à partir de la quantité du acide dans le produit
    int calculAcide() {
        if (acide <= 1) {
            return 0;
        } else if (acide <= 2) {
            return 1;
        } else if (acide <= 3) {
            return 2;
        } else if (acide <= 4) {
            return 3;
        } else if (acide <= 5) {
            return 4;
        } else if (acide <= 6) {
            return 5;
        } else if (acide <= 7) {
            return 6;
        } else if (acide <= 8) {
            return 7;
        } else if (acide <= 9) {
            return 8;
        } else if (acide <= 10) {
            return 9;
        } else return 10;

    }
    // calculer le score du soduim à partir de la quantité du soduim dans le produit
    int calculSoduim() {
        if (soduim <= 90) {
            return 0;
        } else if (soduim <= 180) {
            return 1;
        } else if (soduim <= 270) {
            return 2;
        } else if (soduim <= 360) {
            return 3;
        } else if (soduim <= 450) {
            return 4;
        } else if (soduim <= 540) {
            return 5;
        } else if (soduim <= 630) {
            return 6;
        } else if (soduim <= 720) {
            return 7;
        } else if (soduim <= 810) {
            return 8;
        } else if (soduim <= 900) {
            return 9;
        } else return 10;

    }
    // calculer le score du fibre à partir de la quantité du fibre dans le produit
    int calculFibre() {
        if (fibre <= 0.9) {
            return 0;
        } else if (fibre <= 1.9) {
            return 1;
        } else if (fibre <= 2.8) {
            return 2;
        } else if (fibre <= 3.7) {
            return 3;
        } else if (fibre <= 4.7) {
            return 4;
        } else return 5;

    }
    // calculer le score du protein à partir de la quantité du protein dans le produit
    int calculProtein() {
        if (protein <= 1.6) {
            return 0;
        } else if (protein <= 3.2) {
            return 1;
        } else if (protein <= 4.8) {
            return 2;
        } else if (protein <= 6.4) {
            return 3;
        } else if (protein <= 8) {
            return 4;
        } else return 5;

    }
    // calculer le score du fruit à partir de la quantité du fruit dans le produit
    int calculFruit() {
        //faire extraire les composants et vérifier s'ils sont des fruits sec oU fraiche
        String tab_ingradiant[] = new String[100];
        tab_ingradiant = ingrédients.split(",");
        for (int i = 0; i < tab_ingradiant.length; i++) {
            if (tab_ingradiant[i].contains("/")) {
                String composant[] = new String[2];
                composant = tab_ingradiant[i].split("/");
                if (Arrays.asList(fruits).contains(composant[0])) {
                    fruit += Double.parseDouble(composant[1]) * quantité / 100;
                } else if (Arrays.asList(fruitsec).contains(composant[0])) {
                    driedfruit += Double.parseDouble(composant[1]) * quantité / 100;
                }

            }
        }
        nonfruit = quantité - round(fruit) - round(driedfruit);
        System.out.println(fruit);
        double x = 100 * (fruit + 2 * driedfruit) / (fruit + 2 * driedfruit + nonfruit);

        if (x <= 40) {
            return 0;
        } else if (x <= 60) {
            return 1;
        } else if (x <= 80) {
            return 2;
        } else return 5;

    }
    //calcul du score final
    int calculScore() {
        int n = calculEnergy() + calculSugar() + calculAcide() + calculSoduim();
        int p = calculFruit() + calculFibre() + calculProtein();
        if (n < 11) {
            return n - p;
        } else {
            if (calculFruit() == 5) {
                return n - p;
            } else {
                return n - p + calculProtein();
            }
        }
    }
}