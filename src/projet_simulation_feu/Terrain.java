package projet_simulation_feu;

/*
 * Projet de simulation de feu de foret
 * Auteurs : Maxime Innocenti & Antoine Henriet
 * 01/02/2022
 * Codé en Java
*/

// IMPORTATIONS
import java.awt.image.BufferedImage;
import java.io.*;
import java.text.NumberFormat;

import javax.swing.*;
import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import java.util.Random;
import java.util.Arrays;
import java.util.ArrayList;

public class Terrain {

    //Attributs
    private Case [][] grille;
    private Vent vent;
    private Humidite niveau;
    private String [] tab_densite_vegetation = {"Clairsemee","Espacee","Touffue","Continue"}; // 4 densites possibles
    private int densite;
    private int nombre_intacte = 0;
    private int nombre_enflammee = 0;
    private int nombre_case_brule_chaud = 0;
    private int nombre_case_brule_froid = 0;
    private int nombre_case_cendre = 0;
    private DiamondSquare DS;
    private ArrayList <Case> Intacte = new ArrayList<>();
    private ArrayList <Case> Enflammee = new ArrayList<>();
    private ArrayList <Case> Brule_chaud = new ArrayList<>();
    private ArrayList <Case> Brule_froid = new ArrayList<>();
    private ArrayList <Case> Cendre = new ArrayList<>();
    private ArrayList <Case> ProbaPrendreFeu = new ArrayList<>();


    //Getter & Setter
    public DiamondSquare getDS() {
        return DS;
    }

    public void setDS(DiamondSquare dS) {
        this.DS = dS;
    }

    public String[] getTab_densite_vegetation() {
        return tab_densite_vegetation;
    }

    public void setTab_densite_vegetation(String[] tab_densite_vegetation) {
        this.tab_densite_vegetation = tab_densite_vegetation;
    }

    public Case[][] getGrille() {
        return grille;
    }

    public void setGrille(Case[][] grille) {
        this.grille = grille;
    }

    public Vent getVent() {
        return vent;
    }

    public void setVent(Vent vent) {
        this.vent = vent;
    }

    public Humidite getNiveau() {
        return niveau;
    }

    public void setNiveau(Humidite niveau) {
        this.niveau = niveau;
    }

    public int getDensite() {
        return densite;
    }

    public void setDensite(int densite) {
        this.densite = densite;
    }

    public int getNombre_intacte() {
        return nombre_intacte;
    }    

    public void setNombre_intacte(int nombre_intacte) {
        this.nombre_intacte = nombre_intacte;
    }

    public int getNombre_enflammee() {
        return nombre_enflammee;
    }

    public void setNombre_enflammee(int nombre_enflammee) {
        this.nombre_enflammee = nombre_enflammee;
    }

    public int getNombre_case_brule_chaud() {
        return nombre_case_brule_chaud;
    }

    public void setNombre_case_brule_chaud(int nombre_case_brule_chaud) {
        this.nombre_case_brule_chaud = nombre_case_brule_chaud;
    }

    public int getNombre_case_brule_froid() {
        return nombre_case_brule_froid;
    }

    public void setNombre_case_brule_froid(int nombre_case_brule_froid) {
        this.nombre_case_brule_froid = nombre_case_brule_froid;
    }

    public int getNombre_case_cendre() {
        return nombre_case_cendre;
    }

    public ArrayList<Case> getIntacte() {
        return Intacte;
    }

    public void setIntacte(ArrayList<Case> Intacte) {
        this.Intacte = Intacte;
    }

    public void setNombre_case_cendre(int nombre_case_cendre) {
        this.nombre_case_cendre = nombre_case_cendre;
    }

    
    
    

    //Constructeurs
    public Terrain(int i, Vent vent, Humidite niveau, int densite, DiamondSquare DS) {
        this.grille = new Case[i][i];
        this.vent = vent;
        this.niveau = niveau;
        this.densite = densite;
        this.DS = DS;
    }

    //Méthodes
    public long CalculVegetation(){
        /*
            Cette méthode calcul le nombre de case végétation à avoir pour respecter le critère de densité.
        */
        return Math.round(getDensite()*grille.length*grille[0].length/100);
    }

    public void GenerationTerrainConsole() { 
        /*
            Cette méthode permet de créer le terrain de façon aléatoire le terrain avec la taille et la densité de végétation souhaitée. 
        */
        int tirage;
        long vegetation = CalculVegetation();                    //retourne le nb de cases qui doivent avoir de la vegetation

        if (getDensite() == 4){                                  //Densite Continue
            for (int i = 0; i < grille.length; i++){
                for (int j = 0; j < grille[i].length; j++){
                    tirage = new Random().nextInt(2);
                    switch(tirage){
                        case 0:     //Foret
                            grille[i][j] = new Foret(i,j,getNiveau().getNiveau());
                            Intacte.add(grille[i][j]);
                            nombre_intacte += 1;
                            break;
                        case 1:     //Prairie
                            grille[i][j] = new Prairie(i,j,getNiveau().getNiveau());
                            Intacte.add(grille[i][j]);
                            nombre_intacte += 1;
                            break;
                    }
                }
            }
        }
        else {
            long non_vegetation = grille.length*grille[0].length - vegetation;      //retourne le nb de cases qui ne doivent pas avoir de la vegetation
            ArrayList<int[]> coordonnee = Tirage(non_vegetation, "Terrain");        //coordonnée de toutes les futures cases de non vegetation
            for (int i = 0; i < coordonnee.size(); i++){                            //tirage du type de ces cases
                tirage = new Random().nextInt(2);
                switch(tirage){
                    case 0:     //eau
                        grille[coordonnee.get(i)[0]][coordonnee.get(i)[1]] = new Eau(coordonnee.get(i)[0],coordonnee.get(i)[1],getNiveau().getNiveau());
                        Intacte.add(grille[coordonnee.get(i)[0]][coordonnee.get(i)[1]]);
                        nombre_intacte += 1;
                        break;
                    case 1:     //terre
                        grille[coordonnee.get(i)[0]][coordonnee.get(i)[1]] = new Terre(coordonnee.get(i)[0],coordonnee.get(i)[1],getNiveau().getNiveau());
                        Intacte.add(grille[coordonnee.get(i)[0]][coordonnee.get(i)[1]]);
                        nombre_intacte += 1;
                        break;
                }
            }
            for (int i = 0; i < grille.length; i++){                   //remplissage des autres cases
                for (int j = 0; j < grille[i].length; j++){
                    if (grille[i][j] == null){                        // si la case n'a pas encore de type
                        tirage = new Random().nextInt(3);
                        switch(tirage){                               //tirage type de case
                            case 0:     //prairie
                                grille[i][j] = new Prairie(i,j,getNiveau().getNiveau());
                                Intacte.add(grille[i][j]);
                                nombre_intacte += 1;
                                vegetation -= 1;
                                break;
                            case 1:     //foret
                                grille[i][j] = new Foret(i,j,getNiveau().getNiveau());
                                Intacte.add(grille[i][j]);
                                nombre_intacte += 1;
                                vegetation -= 1;
                                break;
                            case 2:     //Maison
                                grille[i][j] = new Maison(i,j,getNiveau().getNiveau());
                                Intacte.add(grille[i][j]);
                                nombre_intacte += 1;
                                vegetation -= 1;
                                break;
                        }
                    }
                }
            }
        }
    }

    public double[] DeterminationVegetationProcedurale(){
        /*
            Cette méthode permet de déterminer le pourcentage de végétation à appliquer à la méthode GenerationTerrainProcedurale().
        */
        
        long vegetation = CalculVegetation();            //retourne le nb de cases qui doivent avoir de la vegetation
        double [] intervale = new double[] {-0.15,1.1};

        if (getDensite() == 100){
            intervale[0] = -0.05;             //Cette valeur car on evite d'avoir de l'eau de de la terre
            intervale[1] = 1;               //valeur egale à 1 pour ne pas avoir de ville
        }
        else {
            double cpt_vegetation = 0;

            while ((cpt_vegetation < vegetation*0.95) || (vegetation*1.05 < cpt_vegetation)){
                cpt_vegetation = 0;
                for(int x = 0; x < getDS().getDATA_SIZE(); x++){
                    for(int y = 0; y < getDS().getDATA_SIZE(); y++){	
                        if (getDS().getData()[x][y] < intervale[0]){                                  //eau
                            //non vegetation
                        }
                        else if (getDS().getData()[x][y] >= intervale[0] && getDS().getData()[x][y] < (intervale[0] + 0.05)){   //terre
                            //non vegetation
                        }
                        else if (getDS().getData()[x][y] >= (intervale[0] + 0.05) && getDS().getData()[x][y] < 0.4){   //prairie
                            cpt_vegetation ++;
                        }
                        else if (getDS().getData()[x][y] >= 0.4 && getDS().getData()[x][y] < (intervale[1] - 0.1)){     //foret
                            cpt_vegetation ++;
                        }
                        else if (getDS().getData()[x][y] >= (intervale[1] - 0.1) && getDS().getData()[x][y] <= intervale[1]){      //prairie
                            cpt_vegetation ++;
                        }else {                                                      //ville
                            //non vegetation
                        }
                    }
                }
                intervale[0] += 0.007;
                intervale[1] -= 0.005;
            }
        }

        return intervale;
    }
    
    public void GenerationTerrainProcedurale() {
        /*
            Cette méthode permet de créer le terrain en faisant appel a une classe de génération procédurale.
        */
        
        double [] intervale = DeterminationVegetationProcedurale();
        Intacte = new ArrayList<>();                //reset données pour le graphique car changement de type de terrin ajoute tout ca ensemnble
        nombre_intacte = 0;

        for(int x = 0; x < getDS().getDATA_SIZE(); x++){
            for(int y = 0; y < getDS().getDATA_SIZE(); y++){	
                if (getDS().getData()[x][y] < intervale[0]){                                  //eau
                    grille[x][y] = new Eau(x,y,getNiveau().getNiveau());
                    Intacte.add(grille[x][y]);
                    nombre_intacte += 1;
                }
                else if (getDS().getData()[x][y] >= intervale[0] && getDS().getData()[x][y] < (intervale[0] + 0.05)){   //terre
                    grille[x][y] = new Terre(x,y,getNiveau().getNiveau());
                    Intacte.add(grille[x][y]);
                    nombre_intacte += 1;
                }
                else if (getDS().getData()[x][y] >= (intervale[0] + 0.05) && getDS().getData()[x][y] < 0.4){   //prairie
                    grille[x][y] = new Prairie(x,y,getNiveau().getNiveau());
                    Intacte.add(grille[x][y]);
                    nombre_intacte += 1;
                }
                else if (getDS().getData()[x][y] >= 0.4 && getDS().getData()[x][y] < (intervale[1] - 0.1)){     //foret
                    grille[x][y] = new Foret(x,y,getNiveau().getNiveau());
                    Intacte.add(grille[x][y]);
                    nombre_intacte += 1;
                }
                else if (getDS().getData()[x][y] >= (intervale[1] - 0.1) && getDS().getData()[x][y] <= intervale[1]){      //prairie
                    grille[x][y] = new Prairie(x,y,getNiveau().getNiveau());
                    Intacte.add(grille[x][y]);
                    nombre_intacte += 1;
                }else {                                                      //ville
                    grille[x][y] = new Maison(x,y,getNiveau().getNiveau());
                    Intacte.add(grille[x][y]);
                    nombre_intacte += 1;
                }
            }
        }
    }
    
    public void Generation_terrain_depuis_fichier (int i, int j, int type_terrain) {
        /*
            Cette méthode créée les cases du terrain à l'aide des informations d'un fichier texte.
        */
        switch(type_terrain){
            case -1 :
                grille[i][j] = new Eau(i,j,getNiveau().getNiveau());
                Intacte.add(grille[i][j]);
                nombre_intacte += 1;
                break;
                
            case 0 :
                grille[i][j] = new Terre(i,j,getNiveau().getNiveau());
                Intacte.add(grille[i][j]);
                nombre_intacte += 1;
                break;
                
            case 1 :
                grille[i][j] = new Prairie(i,j,getNiveau().getNiveau());
                Intacte.add(grille[i][j]);
                nombre_intacte += 1;
                break;
                
            case 2 :
                grille[i][j] = new Foret(i,j,getNiveau().getNiveau());
                Intacte.add(grille[i][j]);
                nombre_intacte += 1;
                break;
                
            case 3 :
                grille[i][j] = new Maison(i,j,getNiveau().getNiveau());
                Intacte.add(grille[i][j]);
                nombre_intacte += 1;
                break;
        }
    }

    public ArrayList<int[]> DepartFeuSimple(){
        /*
            Cette méthode détermine un départ de feu aléatoire sur le terrain.
        */
        int [] reference = {-1,-1};
        int [] tab = {-1,-1};
        ArrayList<int[]> departFeu = new ArrayList<int[]>(0);
        int x = new Random().nextInt(grille[0].length);    //Attention colonnes
        int y = new Random().nextInt(grille.length);       //Attention lignes
        int [] case1 = {y,x};                              //creation premiere case 
        
        departFeu.add(case1);

        while (Arrays.equals(reference,tab)){
            int cas = new Random().nextInt(4);
            switch(cas){
                case 0:         //case a gauche
                    if(x-1 >= 0){
                        tab[0] = y;
                        tab[1] = x-1;
                    }
                    break;
                case 1:         //case a droite
                    if(x+1 >= 0){
                        tab[0] = y;
                        tab[1] = x+1;
                    }
                    break;
                case 2:         //case en bas
                    if(y-1 >= 0){
                        tab[0] = y-1;
                        tab[1] = x;
                    }
                    break;
                case 3:         //case en haut
                    if(y+1 >= 0){
                        tab[0] = y+1;
                        tab[1] = x;
                    }
                    break;
            }
        }
        departFeu.add(tab);
        return departFeu;
    }

    public ArrayList<int[]> DepartFeuMultiple(int Nb_depart){
        /*
            Cette méthode détermine plusieurs départs de feu aléatoires sur le terrain.
        */
        return TirageFeu(Nb_depart, "Feu");
    }
    
    public int ValidationFeuManuel(int[] caseFeu){
        /*
            Cette méthode defini si la case eput etre enflammée
        */
        
        int contains = 1;

        if (grille[caseFeu[0]][caseFeu[1]].getEtat_flamme().equals("Enflammee")){ //Si la case n'est pas inflammable, on refait un tirage
            contains = 2;
        }
        
        if (grille[caseFeu[0]][caseFeu[1]].isInflammable() == false){
                contains = 3;
        }
        return contains;
    }
    
    public void CreationFeu(int choixDepart, int Nb_depart){            //A FAIRE CHOIX Nb_Depart 1 OU 2 !!!!
        /*
            Cette méthode permet de savoir l'on veut un ou plusieurs départs de feu.
        */
        ArrayList<int[]> departFeu;
        setNombre_enflammee(Nb_depart);
        
        if(choixDepart == 1){
            departFeu = DepartFeuSimple();
        }
        else {
            departFeu = DepartFeuMultiple(Nb_depart);
        }
        
        for(int i = 0; i < departFeu.size(); i++){
            grille[departFeu.get(i)[0]][departFeu.get(i)[1]].setEtat_flamme("Enflammee");  //Set etat enflammee
            int index = Intacte.indexOf(grille[departFeu.get(i)[0]][departFeu.get(i)[1]]);
            Intacte.remove(index);
            nombre_intacte -= 1;
            Enflammee.add(grille[departFeu.get(i)[0]][departFeu.get(i)[1]]);
        }
    }
    
    public void CreationFeuManuelle(int[] departFeu){            //A FAIRE CHOIX Nb_Depart 1 OU 2 !!!!
        /*
            Cette méthode permet de un départs de feu depuis un click sur un Jpanel.
        */        
        
        int index = Intacte.indexOf(grille[departFeu[0]][departFeu[1]]);
        if(index != -1){                                //verfie que la case n'a pas deja ete supprimée
            grille[departFeu[0]][departFeu[1]].setEtat_flamme("Enflammee");  //Set etat enflammee
            setNombre_enflammee(getNombre_enflammee()+1);
            Intacte.remove(index);
            nombre_intacte -= 1;
            Enflammee.add(grille[departFeu[0]][departFeu[1]]);
        }
    }

    public void AffichageFeuConsole(){
        /*
            Cette méthode affiche le terrain en prenant en compte la progression du feu.
        */
        String ANSI_RESET = "\033[0m";

        String VertClair = "\033[38;5;112m" + "\033[48;5;112m";  //arbre
        String VertFonce = "\033[38;5;28m" + "\033[48;5;28m";  //prairie
        String Maison = "\033[38;5;139m" + "\033[48;5;139m";  //maison
        String Bleu = "\033[38;5;39m" + "\033[48;5;39m";  //eau
        String Marron = "\033[38;5;94m" + "\033[48;5;94m";//terre
        String Enflammee = "\033[38;5;196m" + "\033[48;5;196m"; //Rouge
        String BruleChaud = "\033[38;5;208m" + "\033[48;5;208m"; //Orange
        String BruleFroid3 = "\033[38;5;214m" + "\033[48;5;214m";//Orange clair
        String BruleFroid2 = "\033[38;5;221m" + "\033[48;5;221m";//jaune fonce
        String BruleFroid1 = "\033[38;5;228m" + "\033[48;5;228m";//jaune clair
        String Cendre = "\033[38;5;240m" + "\033[48;5;240m";     //gris

        for (int i = 0; i < grille.length; i++) {
            for (int j = 0; j < grille[i].length; j++) {
                switch(grille[i][j].getType_case()){
                    case 0:     //eau
                        System.out.print(Bleu + " "+ grille[i][j].getType_case() + " " + ANSI_RESET);
                        break;
                    case -1:     //terre
                        System.out.print(Marron + grille[i][j].getType_case() + " " + ANSI_RESET);
                        break;
                    default :     //Prairie & Foret & Maison
                        switch (grille[i][j].getEtat_flamme()){
                            case "Intacte":
                                switch (grille[i][j].getType_case()){
                                    case 1:
                                        System.out.print(VertClair + " "+ grille[i][j].getType_case() + " " + ANSI_RESET);
                                        break;
                                    case 2:
                                        System.out.print(VertFonce + " "+ grille[i][j].getType_case() + " " + ANSI_RESET);
                                        break;
                                    case 3:
                                        System.out.print(Maison + " "+ grille[i][j].getType_case() + " " + ANSI_RESET);
                                        break;
                                }
                                
                                break;
                                case "Enflammee":
                                System.out.print(Enflammee  + " "+ 4 + " " + ANSI_RESET);
                                break;
                            case "Brule chaud":
                                System.out.print(BruleChaud  + " "+  5 + " " + ANSI_RESET);
                                break;
                            case "Brule froid":
                                switch (grille[i][j].getBrule_froid()){
                                    case 3:
                                        System.out.print(BruleFroid3  + " "+  6 + " " + ANSI_RESET);
                                        break;
                                    case 2:
                                        System.out.print(BruleFroid2  + " "+  7 + " " + ANSI_RESET);
                                        break;
                                    case 1:
                                        System.out.print(BruleFroid1 + " "+ 8 + " " + ANSI_RESET);
                                        break;
                                }
                                break;
                            case "Cendre":
                                System.out.print(Cendre + " "+ 9 + " " + ANSI_RESET);
                                break;
                        }
                        break;
                }
            }
            System.out.println();
        }
        System.out.println("\n\n\n\n\n");
    }

    public void AffichageFeuProcedurale() throws IOException{
        /*
            Cette méthode permet de créer un png du terrain. (Cette méthode sera utilisée ultérieurement pour le graphique).
        */

        int DATA_SIZE = grille.length;
        int rgb;

        BufferedImage image = new BufferedImage(DATA_SIZE, DATA_SIZE, BufferedImage.TYPE_INT_RGB);
        

        for (int y = 0; y < DATA_SIZE; y++) {                   // y = ligne = ordonnees
            for (int x = 0; x < DATA_SIZE; x++) {               // x = colonne = abscisses
                if(grille[y][x].isSurcharge() == true){         //terre car surchargé
                    rgb = new Color(139,69,19).getRGB();
                    image.setRGB(x, y, rgb);
                }
                else{
                    switch(grille[y][x].getType_case()){
                        case -1:     //eau
                            rgb = Color.blue.getRGB();
                            image.setRGB(x, y, rgb);
                            break;
                        case 0:     //terre
                            rgb = new Color(139,69,19).getRGB();
                            image.setRGB(x, y, rgb);
                            break;
                        default :     //Prairie & Foret & Maison
                            switch (grille[y][x].getEtat_flamme()){
                                case "Intacte":
                                    switch (grille[y][x].getType_case()){
                                        case 1:
                                            rgb = new Color(127,255,0).getRGB();
                                            image.setRGB(x, y, rgb);
                                            break;
                                        case 2:
                                            rgb = new Color(0,100,0).getRGB();
                                            image.setRGB(x, y, rgb);
                                            break;
                                        case 3:
                                            rgb = new Color(119,136,153).getRGB();
                                            image.setRGB(x, y, rgb);
                                            break;
                                    }
                                    break;
                                case "Enflammee":
                                    rgb = new Color(255,0,0).getRGB();
                                    image.setRGB(x, y, rgb);
                                    break;
                                case "Brule chaud":
                                    rgb = new Color(255,69,0).getRGB();
                                    image.setRGB(x, y, rgb);
                                    break;
                                case "Brule froid":
                                    switch (grille[y][x].getBrule_froid()){
                                        case 3:
                                            rgb = new Color(255,215,0).getRGB();
                                            image.setRGB(x, y, rgb);
                                            break;
                                        case 2:
                                            rgb = new Color(255,255,0).getRGB();
                                            image.setRGB(x, y, rgb);
                                            break;
                                        case 1:
                                            rgb = new Color(255,255,102).getRGB();
                                            image.setRGB(x, y, rgb);
                                            break;
                                        case 0:
                                            rgb = new Color(211,211,211).getRGB();
                                            image.setRGB(x, y, rgb);
                                            break;
                                    }
                                    break;
                                case "Cendre":
                                    rgb = new Color(211,211,211).getRGB();
                                    image.setRGB(x, y, rgb);
                                    break;
                            }
                            //System.out.print(" "+ Rouge + Test.getGrille()[i][j].getType_case() + ANSI_RESET +" ");
                            break;
                    }
                }
            }
        }
        ImageIO.write(image, "png", new File("noise.png"));
    }

    public void CalculPourcentageFeu(){
        /*
            Cette méthode se déplace sur le terrain et ajoute toutes les probabilités aux cases potentielles de prendre feu.
        */
        int debut;
        double propa_brule_chaud = 0.5*(1+2*getVent().getForce());             //peut erte modifier en graphique avec des valeurs differentes

        if(vent.getMat_Vent().length == 7){ //calcul de la taille de la matrice de vent
            debut = -3;
        }
        else {
            debut = -2;
        }

        for (int i = 0; i<grille.length; i++){ //Les deux premiers for permettent de se deplacer sur le terrain
            for (int j = 0; j<grille[0].length; j++){
                if("Enflammee".equals(grille[i][j].getEtat_flamme()) || "Brule chaud".equals(grille[i][j].getEtat_flamme())){  //Case qui peuvent propager le feu 
                    for (int ligne_matrice_vent = debut ; ligne_matrice_vent<=(-debut) ; ligne_matrice_vent++){  //Les deux for nous permettent de nous déplacer dans la matrice de vent
                        for (int colonne_matrice_vent = debut ; colonne_matrice_vent <= (-debut) ; colonne_matrice_vent++){
                            // Le if nous permet de ne pas sortir de la matrice de vent
                            if ( (i+ligne_matrice_vent>=0) && (i+ligne_matrice_vent<grille.length) && (j+colonne_matrice_vent>=0) && (j+colonne_matrice_vent<grille[0].length)) {
                                //regarde si sur laquelle on veut que le feu se propage est untacte
                                if("Intacte".equals(grille[i+ligne_matrice_vent][j+colonne_matrice_vent].getEtat_flamme())){
                                    //evite d'affecter les 0 des matrice de vent dans les probas du tirage de feu
                                    if(vent.getMat_Vent()[(ligne_matrice_vent-debut)][(colonne_matrice_vent-debut)] != 0.0){
                                        if("Enflammee".equals(grille[i][j].getEtat_flamme())){      //si case est enflammee
                                            grille[i+ligne_matrice_vent][j+colonne_matrice_vent]
                                                    .ajouter(vent.getMat_Vent()[(ligne_matrice_vent-debut)][(colonne_matrice_vent-debut)]
                                                        *grille[i+ligne_matrice_vent][j+colonne_matrice_vent].getHumidite_pourcent());  //Ajout humidite
                                        }
                                        else {
                                            grille[i+ligne_matrice_vent][j+colonne_matrice_vent]  
                                                    .ajouter(vent.getMat_Vent()[(ligne_matrice_vent-debut)][(colonne_matrice_vent-debut)]
                                                            *grille[i+ligne_matrice_vent][j+colonne_matrice_vent].getHumidite_pourcent()*propa_brule_chaud);  //Ajout humidite
                                        }
                                    }
                                }
                            }                            
                        }
                    }
                }
            }
        }

        NumberFormat format = NumberFormat.getInstance();
        format.setMinimumFractionDigits(1); //nb de chiffres apres la virgule
    }

    public void Update() {
        /*
            Cette méthode actualise à chaque tour le statut de chaque case du terrain.
        */

        for (int i = 0; i<grille.length; i++){
            for (int j = 0; j<grille[0].length ; j++){
                if(grille[i][j].getHumidite_pourcent() < getNiveau().getNiveau()){                  //on enleve progressivement l'humidité de l'eau des pompiers
                    grille[i][j].setHumidite_pourcent(grille[i][j].getHumidite_pourcent() + 0.01);
                }
                if (!grille[i][j].getPourcentageFeu().isEmpty()){                                   //si la grille a un pourcentage de s'enflammer
                    for (int nb = 0; nb<grille[i][j].getPourcentageFeu().size(); nb++){             //boucle parcourt le nb de proba total d'une case de s'enflammer
                        double proba = grille[i][j].getPourcentageFeu().get(nb);                    //recuperation de chaque probabilité
                        boolean a_pris_feu = grille[i][j].Tirage_Proba(proba);                      //tirage random pour savoir si la case s'est enflammée
                        if (a_pris_feu){                                                            //si ca case a pris feu
                            nombre_intacte -= 1;                                                    //la case est retirée de l'etat intacte 
                            grille[i][j].setEtat_flamme(grille[i][j].getTab_etat_flamme()[1]);      //la case s'enflamme
                            nombre_enflammee += 1;                                                  //la case est ajoutée à l'etat enflammee
                            break;
                        }
                    }
                    grille[i][j].setPourcentageFeu(new ArrayList<Double>());                        //reset des pourcentages de chaque case 
                }
                else {
                    if ("Enflammee".equals(grille[i][j].getEtat_flamme())){ //Permet de savoir quand on passe au statut brule chaud
                        grille[i][j].setCombustion(grille[i][j].getCombustion()-1);         //reduit le temps de combustion d'une case en fonction de son type
                        if (grille[i][j].getCombustion() <= 0){                             //si le temps de combustion est nul, passage a l'etat d'apres
                            grille[i][j].setEtat_flamme(grille[i][j].getTab_etat_flamme()[2]);
                            nombre_enflammee -= 1;
                            nombre_case_brule_chaud += 1;
                        }
                    }

                    else if ("Brule chaud".equals(grille[i][j].getEtat_flamme())){
                        boolean ne_brule_plus = grille[i][j].Tirage_Proba(0.4);     //proba que la case brule chaud passe en brule froid
                        if (ne_brule_plus){                                         //si la case ne brule plus
                            grille[i][j].setEtat_flamme(grille[i][j].getTab_etat_flamme()[3]); //changement de l'etat de la case
                            nombre_case_brule_chaud -= 1;
                            nombre_case_brule_froid += 1;
                        }
                    }

                    else if ("Brule froid".equals(grille[i][j].getEtat_flamme())){ //Permet de savoir quand on passe au statut cendre
                        grille[i][j].setBrule_froid(grille[i][j].getBrule_froid()-1);  //reduit le temps de brule froid de la case
                        if (grille[i][j].getBrule_froid() <= 0){                       //si le temps de brule froid est nul : changement d'etat
                            grille[i][j].setEtat_flamme(grille[i][j].getTab_etat_flamme()[4]); //changement de l'etat de la case
                            nombre_case_brule_froid -= 1;
                            nombre_case_cendre += 1;
                        }
                    }
                }
            }
        }
    }

    public ArrayList<int[]> Tirage(long Nb_depart, String Fonction){
        /*
            Cette methode permet d’effectuer un tirage aléatoire pour CreationFeu(int choixDepart, int Nb_depart) et GenerationTerrainConsole().
        */
        ArrayList<int[]> Tableau = new ArrayList<>(1);
        boolean contains = false;
        int cpt;

        for (int i = 0; i < Nb_depart; i++){
            do{
                contains = false;
                int x2 = new Random().nextInt(grille[0].length);    //Attention colonnes
                int y2 = new Random().nextInt(grille.length);       //Attention lignes
                int [] test = {y2,x2};                              //Affectation coordonnees a un tableau

                if (Tableau.isEmpty()){                            //Creation compteur car ArrayList.size = 0 au debut
                    cpt = 1;
                }else {
                    cpt = Tableau.size();
                }

                for(int j = 0; j < cpt; j++){                       //verification que la case n'est pas deja choisie. si c'est le cas, on recommence
                    if (!Tableau.isEmpty()){                       //Verifie que la liste en non vide pour recuperer la valeur
                        if ((Arrays.equals(Tableau.get(j), test) == true)){ //Si la case n'est pas inflammable, on refait un tirage
                        contains = true;
                        }
                    }
                    if (("Feu".equals(Fonction)) && (grille[y2][x2].isInflammable() == false)){
                        contains = true;
                    }
                }
                if (contains == false){                             //Affectation coordonnees au tableau
                    Tableau.add(test);
                }
            }while(contains == true);
        }
        return Tableau;
    }
    
    public ArrayList<int[]> TirageFeu(long Nb_depart, String Fonction){
        /*
            Cette methode permet d’effectuer un tirage aléatoire pour CreationFeu(int choixDepart, int Nb_depart) et GenerationTerrainConsole().
        */
        ArrayList<int[]> Tableau = new ArrayList<>(1);
        boolean contains = false;
        int cpt;

        for (int i = 0; i < Nb_depart; i++){
            do{
                contains = false;
                int x2 = new Random().nextInt(grille[0].length-1);    //car le panel a 1 colonnes de moins
                int y2 = new Random().nextInt(grille.length-1);       //car le panel a 1 lignes de moins
                int [] test = {y2,x2};                              //Affectation coordonnees a un tableau

                if (Tableau.isEmpty()){                            //Creation compteur car ArrayList.size = 0 au debut
                    cpt = 1;
                }else {
                    cpt = Tableau.size();
                }

                for(int j = 0; j < cpt; j++){                       //verification que la case n'est pas deja choisie. si c'est le cas, on recommence
                    if (!Tableau.isEmpty()){                       //Verifie que la liste en non vide pour recuperer la valeur
                        if ((Arrays.equals(Tableau.get(j), test) == true)){ //Si la case n'est pas inflammable, on refait un tirage
                        contains = true;
                        }
                    }
                }
                if (("Feu".equals(Fonction)) && (grille[y2][x2].isInflammable() == false)){
                        contains = true;
                }
                if (contains == false){                             //Affectation coordonnees au tableau
                    Tableau.add(test);
                }
            }while(contains == true);
        }
        return Tableau;
    }
}
