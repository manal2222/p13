package projet_simulation_feu;

/*
 * Projet de simulation de feu de foret
 * Auteurs : Maxime Innocenti & Antoine Henriet
 * 01/02/2022
 * Codé en Java
*/

// IMPORTATIONSS
import java.util.Arrays;

public class Verification {
    
    //Attributs
    
    //Constructeurs
    public Verification(){
        
    }
    
    //Méthodes
    public boolean validation(String val, String [] tab){
        return Arrays.stream(tab).anyMatch(val::equals);
    }
}
