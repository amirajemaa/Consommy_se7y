package tn.codefortunisia.lastversion;

import java.util.Arrays;
import java.text.DecimalFormat;
import static java.lang.Math.round;


public class NatureAliment {
    double energy;
    double sugar;
    double acide;
    double soduim;
    double fruit;
    double dried_fruit;
    double non_fruit;
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
    String fruit_sec[] = {"amande", "cacahuète", "raisin sec", "châtaigne", "noisette", "noisette chilienne", "noix", "noix de cajou", "noix de coco", "noix de macadamia", "noix de pécan", "noix du Brésil", "pignon de pin", "pistache", "abricot", "datte seche", "figue", "pruneau"};

    NatureAliment(double energy, double sugar, double acide, double soduim, double protein, double fibre, double quantité, String ingrédients) {
        this.energy = energy;
        this.sugar = sugar;
        this.acide = acide;
        this.soduim = soduim;
        this.fibre = fibre;
        this.protein = protein;
        this.quantité = quantité;
        this.ingrédients = ingrédients;
        fruit = 0;
        dried_fruit = 0;
        non_fruit = 0;
    }

    public NatureAliment(String energy, String sugar, String acide, String soduim, String protein, String fibre, String quantité, String ingrédients ,String catégorie) {
        DecimalFormat df = new DecimalFormat("#####.0");
        this.energy = round(Double.parseDouble(energy));
        this.sugar = Double.parseDouble(df.format(Double.parseDouble(sugar)));
        this.acide = round(Double.parseDouble(acide));
        this.soduim = round(Double.parseDouble(soduim));
        this.fibre = Double.parseDouble(df.format(Double.parseDouble(fibre)));
        this.protein = Double.parseDouble(df.format(Double.parseDouble(protein)));
        this.ingrédients = ingrédients;
        this.catégorie = catégorie;
        fruit = 0;
        dried_fruit = 0;
        non_fruit = 0;
    }

    int calcul_energy() {
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

    int calcul_sugar() {
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

    int calcul_acide() {
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

    int calcul_soduim() {
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

    int calcul_fibre() {
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

    int calcul_protein() {
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

    int calcul_fruit() {
        String tab_ingradiant[] = new String[100];
        tab_ingradiant = ingrédients.split(",");
        for (int i = 0; i < tab_ingradiant.length; i++) {
            if (tab_ingradiant[i].contains("/")) {
                String composant[] = new String[2];
                composant = tab_ingradiant[i].split("/");
                if (Arrays.asList(fruits).contains(composant[0])) {
                    fruit += Double.parseDouble(composant[1]) * quantité / 100;
                } else if (Arrays.asList(fruit_sec).contains(composant[0])) {
                    dried_fruit += Double.parseDouble(composant[1]) * quantité / 100;
                }

            }
        }
        non_fruit = quantité - round(fruit) - round(dried_fruit);
        System.out.println(fruit);
        double x = 100 * (fruit + 2 * dried_fruit) / (fruit + 2 * dried_fruit + non_fruit);

        if (x <= 40) {
            return 0;
        } else if (x <= 60) {
            return 1;
        } else if (x <= 80) {
            return 2;
        } else return 5;

    }

    int calcul_score() {
        int n = calcul_energy() + calcul_sugar() + calcul_acide() + calcul_soduim();
        int p = calcul_fruit() + calcul_fibre() + calcul_protein();
        if (n < 11) {
            return n - p;
        } else {
            if (calcul_fruit() == 5) {
                return n - p;
            } else {
                return n - p + calcul_protein();
            }
        }
    }
}

