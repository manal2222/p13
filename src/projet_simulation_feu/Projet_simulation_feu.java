package projet_simulation_feu;

/*
 * Projet de simulation de feu de foret
 * Auteurs : Maxime Innocenti & Antoine Henriet
 * 01/02/2022
 * Codé en Java
*/






/*
 * CE FICHIER N EST PLUS UTILISE, IL EST REMPLACE PAR LA VERSION GRAPHIQUE.
 * IL EST CONSERVE DANS LE PROJET COMME ARCHIVE.
*/





// IMPORTATIONS 
import java.util.InputMismatchException;
import java.util.Scanner;
import java.awt.image.ImageObserver;
import java.awt.*;
import java.io.*;


public class Projet_simulation_feu {

    public void paint(Graphics g) {
		Toolkit t=Toolkit.getDefaultToolkit();
		Image i=t.getImage("noise.png");
		g.drawImage(i, 0, 0,(ImageObserver) this);
	}


//    public static <BufferedImage> void main(String[] args) throws Erreur, InterruptedException, IOException {
//        
//        Scanner sc = new Scanner(System.in);
//        Archive a = new Archive();
//        Verification v = new Verification();
//        Terrain terrain = null;
//        String nom_fichier = "";
//        
//        String type_terrain = "pro";
//        String type_affichage = "pro";
//        int taille_terrain = 0;
//        int seed = 1;
//        
//        boolean success = false;
//        
//        System.out.println("Que voulez-vous faire ? \n * 1 pour charger une carte existante\n * 2 pour creer une carte automatique\n * 3 pour creer une carte manuellement");
//        int choix = sc.nextInt();
//        switch(choix){
//            case 1 : 
//                System.out.print("Entrez le nom du fichier à charger (il faut respecter les majuscules) : ");
//                nom_fichier = sc.next();
//                while(!success){
//                    try {
//                        terrain = a.depuisFichier(nom_fichier+".txt");
//                        success = true;
//                    } catch (FileNotFoundException fnfe){
//                        System.out.println("Fichier "+nom_fichier+".txt introuvable...");
//                        sc.next();
//                    }
//                }
//                break;
//
//            case 2 : 
//                System.out.println("\nCréation automatique de la carte");
//                System.out.println("Quelle densite de vegetation de terrain voulez-vous ? \nTaper un nombre de 0 à 100");
//                int densite_vege = sc.nextInt();
//                Vent Vent = new Vent(3, 0);  /////////////////////////////////////////////////////////////////////////////////::
//                Humidite niveau = new Humidite(0);
//                DiamondSquare DS = null;
//
//                if (type_terrain != "Console"){
//                    int n = 5;
//                    taille_terrain = (1 << n) + 1;
//                    DS = new DiamondSquare(taille_terrain, seed);
//                }
//                else {
//                    System.out.println("Quelles sont les dimensions du terrain ?");
//                    taille_terrain = sc.nextInt();
//                }
//
//                terrain = new Terrain(taille_terrain, Vent, niveau, densite_vege, DS);
//
//                if (type_terrain == "Console"){
//
//                    terrain.GenerationTerrainConsole();
//                }
//                else {terrain.GenerationTerrainProcedurale();}
//
//                System.out.print("Quel nom voulez-vous donner à votre fichier : ");
//                String nom = sc.next();
//                a.versFichier(nom,terrain);       //CREATION DU FICHIER AVEC LA CARTE
//                break;
//
//            case 3 : 
//                //Densité
//                String [] numero = {"1","2","3","4"};
//                String densite;
//                /*///////////////////////////////////////////////////////////refaire
//                do {
//                    System.out.println("Quelle densite de vegetation de terrain voulez-vous ? \nTaper un nombre de 0 à 100");
//                    densite = sc.next();
//                }while(!v.validation(densite,numero));
//                */ 
//               
//                System.out.println("Quelle densite de vegetation de terrain voulez-vous ? \nTaper un nombre de 0 à 100");
//                densite = sc.next();
//                int densite_vegetation = Integer.parseInt(densite);
//
//                //Vent ////////////////////////////////////////////////////////////////////////////////////////////
//                
//                String [] valeur = {"0","1","2","3"};
//                /*String [] direction = {"nord","sud","est","ouest"};
//                String force_vent;
//                int direction_vent;
//                do {
//                    System.out.print("Quelle est la force du vent (0,1,2 ou 3) : ");
//                    force_vent = sc.next();
//                    System.out.println("Quelle est la direction du vent ? (0 à 360)");
//                    direction_vent = sc.nextInt();
//                    
//                }while(!v.validation(force_vent,valeur) || !v.validation(direction_vent,direction));
//                
//                Vent vent = new Vent(Integer.parseInt(force_vent), direction_vent);
//*/
//                Vent vent = new Vent(3, 30);  /////////////////////////////////////////////////////////////////////
//                //Humidite
//                String niveau_humidite;
//                do {
//                    System.out.print("Quel est le niveau d'humidité du terrain (0,1,2 ou 3) : ");
//                    niveau_humidite = sc.next();
//                }while(!v.validation(niveau_humidite, valeur));
//                
//                Humidite humidite = new Humidite(Integer.parseInt(niveau_humidite));
//                
//                DS = null;
//
//                //Taille Terrain
//                if (type_terrain != "Console"){
//                    int n = 9;
//                    taille_terrain = (1 << n) + 1;
//                    DS = new DiamondSquare(taille_terrain, seed);
//                }
//                else {
//                    do{
//                        try{
//                            System.out.println("Quelles sont les dimensions du terrain ?");
//                            taille_terrain = sc.nextInt();
//                            success = true;
//                        }catch (InputMismatchException ime){
//                            System.out.println("\nVous devez entrer un entier !\n");
//                            sc.next();
//                        }
//                    }while(!success);
//                }
//                System.out.println("1");
//                terrain = new Terrain(taille_terrain, vent, humidite, densite_vegetation, DS);
//                System.out.println("2");
//                if ("Console".equals(type_terrain)){
//                    terrain.GenerationTerrainConsole();
//                }
//                else {terrain.GenerationTerrainProcedurale();}
//
//                a.versFichier("azerty",terrain);       //CREATION DU FICHIER AVEC LA CARTE
//                
//                System.out.println("3");
//                ////////////////////////// Surcharge
//                Surcharge ajout = new Surcharge();
//                System.out.println("3");
//                ajout.SurchargeConsole(terrain,type_affichage);
//                System.out.println("3");
//                break;
//        } 
//
//        String [] valeur = {"1","2"};
//        String choix_depart;
//        do {
//            System.out.print("Taper '1' pour un depart de feu simple et '2' pour un depart de feu multiple: ");
//            choix_depart = sc.next();
//
//        }while(!v.validation(choix_depart,valeur));
//
//        terrain.CreationFeu(Integer.parseInt(choix_depart), 2);
//        
//        if ("Console".equals(type_affichage)){
//            terrain.AffichageFeuConsole();
//        }
//        else {terrain.AffichageFeuProcedurale();}
//
//        while ((terrain.getNombre_enflammee() != 0) || (terrain.getNombre_case_brule_chaud() != 0) || (terrain.getNombre_case_brule_froid() != 0)){
//
//            if ("Console".equals(type_affichage)){
//                terrain.AffichageFeuConsole();
//            }
//            else {terrain.AffichageFeuProcedurale();};
//            terrain.CalculPourcentageFeu();
//            terrain.Update();
//            Thread.sleep(20);
//        }
//
//        if (type_affichage == "Console"){
//            terrain.AffichageFeuConsole();
//        }
//        else {terrain.AffichageFeuProcedurale();}
//
//        terrain.Update();
//    }
}
