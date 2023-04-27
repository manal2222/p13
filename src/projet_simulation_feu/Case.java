package projet_simulation_feu;

/*
 * Projet de simulation de feu de foret
 * Auteurs : Maxime Innocenti & Antoine Henriet
 * 01/02/2022
 * Codé en Java
*/

// IMPORTATIONS
import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Case {

    //Attributs
    private String [] tab_etat_flamme = {"Intacte","Enflammee","Brule chaud","Brule froid","Cendre","//"}; // 5 etats possibles
    private String [] tab_type_terrain = {"Eau","Terre","Prairie","Foret","Maison"}; // 5 types de terrains possibles
    private String densite_vegetation; /////
    private String etat_flamme;
    private ArrayList <Double> PourcentageFeu;
    private int combustion;
    private int type_case;
    private int brule_froid;
    private boolean inflammable;
    private Point coordonnee;
    private boolean surcharge;
    private boolean bombarde_eau;
    private double humidite_pourcent;
    
    

    //Getter & Setter
    public boolean isSurcharge() {
        return surcharge;
    }
    
    public void setSurcharge(boolean surcharge) {    
        this.surcharge = surcharge;
    }

    public String[] getTab_etat_flamme() {
        return tab_etat_flamme;
    }

    public Point getCoordonnee() {
        return coordonnee;
    }

    public void setCoordonnee(Point coordonnee) {
        this.coordonnee = coordonnee;
    }

    public void setTab_etat_flamme(String[] tab_etat_flamme) {
        this.tab_etat_flamme = tab_etat_flamme;
    }

    public String[] getTab_type_terrain() {
        return tab_type_terrain;
    }

    public void setTab_type_terrain(String[] tab_type_terrain) {
        this.tab_type_terrain = tab_type_terrain;
    }

    public String getDensite_vegetation() {
        return densite_vegetation;
    }

    public void setDensite_vegetation(String densite_vegetation) {
        this.densite_vegetation = densite_vegetation;
    }

    public String getEtat_flamme() {
        return etat_flamme;
    }

    public void setEtat_flamme(String etat_flamme) {
        this.etat_flamme = etat_flamme;
    }

    public ArrayList<Double> getPourcentageFeu() {
        return PourcentageFeu;
    }

    public void setPourcentageFeu(ArrayList<Double> pourcentageFeu) {
        PourcentageFeu = pourcentageFeu;
    }

    public int getCombustion() {
        return combustion;
    }

    public void setCombustion(int combustion) {
        this.combustion = combustion;
    }
    
    public int getType_case() {
        return type_case;
    }

    public void setType_case(int type_case) {
        this.type_case = type_case;
    }
    
    public boolean isInflammable() {
        return inflammable;
    }

    public void setInflammable(boolean inflammable) {
        this.inflammable = inflammable;
    }

    public int getBrule_froid() {
        return brule_froid;
    }

    public void setBrule_froid(int brule_froid) {
        this.brule_froid = brule_froid;
    }

    public boolean isBombarde_eau() {
        return bombarde_eau;
    }

    public void setBombarde_eau(boolean bombarde_eau) {
        this.bombarde_eau = bombarde_eau;
    }
    
    public double getHumidite_pourcent() {
        return humidite_pourcent;
    }

    public void setHumidite_pourcent(double humidite_pourcent) {
        this.humidite_pourcent = humidite_pourcent;
    }

    //Constructeurs
    public Case(int i, int j, double hum) {
        this.etat_flamme = tab_etat_flamme[0];
        PourcentageFeu = new ArrayList<>(0);   //initialisation tableau calcul pourcentage feu
        this.brule_froid = 3;
        this.setCoordonnee(new Point(i,j));
        this.surcharge = false;
        this.bombarde_eau = false;
        this.humidite_pourcent = hum;
        
    }

    public String toString() {
        return "Case [PourcentageFeu=" + PourcentageFeu + ", brule_froid=" + brule_froid + ", combustion=" + combustion
                + ", densite_vegetation=" + densite_vegetation + ", etat_flamme=" + etat_flamme + ", inflammable="
                + inflammable + ", tab_etat_flamme=" + Arrays.toString(tab_etat_flamme) + ", tab_type_terrain="
                + Arrays.toString(tab_type_terrain) + ", type_case=" + type_case + "]";
    }

    //Méthodes
    public boolean Tirage_Proba(double proba) {
        /*
            Permet de savoir si une case intacte devient enflammée.
        */
        Random rand = new Random();
        double nb_aleatoire = rand.nextDouble();
        if (nb_aleatoire<proba) {
            return true;
        } else {
            return false;
        }
    }

    public void ajouter(double x){
        /*
            Ajoute un pourcentage de s'enflammer au tableau des probabilités de chacune des cases.
        */
        PourcentageFeu.add(x);
    }
}
