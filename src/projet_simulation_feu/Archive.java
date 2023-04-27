package projet_simulation_feu;

/*
 * Projet de simulation de feu de foret
 * Auteurs : Maxime Innocenti & Antoine Henriet
 * 01/02/2022
 * Codé en Java
*/

// IMPORTATIONS
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;



public class Archive {
    
    //Attributs
    private Terrain t;
    private Vent v;
    private Humidite h;
    private DiamondSquare DS = null;

    public Terrain getT() {
        return t;
    }

    public void setT(Terrain t) {
        this.t = t;
    }

    public Vent getV() {
        return v;
    }

    public void setV(Vent v) {
        this.v = v;
    }

    public Humidite getH() {
        return h;
    }

    public void setH(Humidite h) {
        this.h = h;
    }

    public DiamondSquare getDS() {
        return DS;
    }

    public void setDS(DiamondSquare DS) {
        this.DS = DS;
    }
    
    //Constructeurs
    public Archive(){   
    }
        
    //Methodes
    
    // CONSOLE \\
    public void versFichier(String nom_fichier, Terrain terrain) throws IOException{
        /*
            Cette méthode permet de récupérer le terrain créé dans un fichier texte.
        */
        FileWriter fw = new FileWriter("./"+nom_fichier+".txt");
        fw.write(nom_fichier+".txt"+"\n"+terrain.getGrille().length+"\n");
        fw.write(terrain.getVent().getForce()+"/"+terrain.getVent().getOrientation()+"/"+terrain.getNiveau().getNiveau()+"/"+terrain.getDensite()+"\n");
        for (int i = 0; i<terrain.getGrille().length; i++){
            for (int j = 0; j<terrain.getGrille().length; j++){
                fw.write(terrain.getGrille()[i][j].getType_case()+"!");
            }
            fw.write("\n");
        }
        fw.close();
    }

    public Terrain depuisFichier(String nom_fichier) throws IOException, Erreur {
        /*
            Cette méthode permet de créer un terrain à partir d’un fichier texte.
        */
        File file = new File(nom_fichier);
        String emplacement_fichier = file.getAbsolutePath();
        BufferedReader br = new BufferedReader(new FileReader(emplacement_fichier));
        
        String Nom_du_fichier = br.readLine();  //Recuperation du nom du fichier
        int taille_terrain = Integer.parseInt(br.readLine());   //Recuperation de la taille de la grille
        
        String [] tab_infos = br.readLine().split("/"); //Recuperation des infos du terrain dans un tableau
        int force_vent = Integer.parseInt(tab_infos[0]);
        Double orientation_vent = Double.parseDouble(tab_infos[1]);
        Double niveau_humidite = Double.parseDouble(tab_infos[2]);
        int densite_terrain = Integer.parseInt(tab_infos[3]);
        
        //v = new Vent(force_vent,orientation_vent);
        h = new Humidite(niveau_humidite);
        t = new Terrain(taille_terrain,v, h, densite_terrain, DS);
        
        for (int i = 0; i<taille_terrain; i++){
            String ligne [] = br.readLine().split("!");
            for (int j = 0; j<taille_terrain; j++){
                t.Generation_terrain_depuis_fichier(i,j,Integer.parseInt(ligne[j]));
            }
        }
        return t;
    }
    
    // GRAPHIQUE \\
    public void versFichierGraphique(String adresse, Terrain terrain) throws IOException{
        /*
            Cette méthode permet de récupérer le terrain créé dans un fichier texte.
        */
        FileWriter fw = new FileWriter(adresse);
        fw.write(terrain.getGrille().length+"\n");
        fw.write(terrain.getVent().getForce()+"/"+terrain.getVent().getOrientation()+"/"+terrain.getNiveau().getNiveau()+"/"+terrain.getDensite()+"\n");
        for (int i = 0; i<terrain.getGrille().length; i++){
            for (int j = 0; j<terrain.getGrille().length; j++){
                fw.write(terrain.getGrille()[i][j].getType_case()+"!");
            }
            fw.write("\n");
        }
        fw.close();
    }
    
    public Terrain depuisFichierGraphique(String emplacement_fichier) throws IOException, Erreur {
        /*
            Cette méthode permet de créer un terrain à partir d’un fichier texte.
        */
        File file = new File(emplacement_fichier);
        BufferedReader br = new BufferedReader(new FileReader(emplacement_fichier));
      
        int taille_terrain = Integer.parseInt(br.readLine());   //Recuperation de la taille de la grille
        
        String [] tab_infos = br.readLine().split("/"); //Recuperation des infos du terrain dans un tableau
        int force_vent = Integer.parseInt(tab_infos[0]);
        int orientation_vent = Integer.parseInt(tab_infos[1]);
        Double niveau_humidite = Double.parseDouble(tab_infos[2]);
        int densite_terrain = Integer.parseInt(tab_infos[3]);
        
        v = new Vent(force_vent,orientation_vent);
        h = new Humidite(niveau_humidite);
        t = new Terrain(taille_terrain,v, h, densite_terrain, DS);
        
        for (int i = 0; i<taille_terrain; i++){
            String ligne [] = br.readLine().split("!");
            for (int j = 0; j<taille_terrain; j++){
                t.Generation_terrain_depuis_fichier(i,j,Integer.parseInt(ligne[j]));
            }
        }
        return t;
    }
}
