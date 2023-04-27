package projet_simulation_feu;

/*
 * Projet de simulation de feu de foret
 * Auteurs : Maxime Innocenti & Antoine Henriet
 * 01/02/2022
 * Codé en Java
*/

public class Humidite {

    //Attributs
    private double niveau;

    //Getter & Setter
    public double getNiveau() {
        return niveau;
    }

    public void setNiveau(double niveau) {
        this.niveau = niveau;
    }

    //Constructeur
    public Humidite(double valeur) {
        this.niveau = valeur;
    }
    
    
}