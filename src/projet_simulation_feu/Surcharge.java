package projet_simulation_feu;

/*
 * Projet de simulation de feu de foret
 * Auteurs : Maxime Innocenti & Antoine Henriet
 * 01/02/2022
 * Codé en Java
*/

// IMPORTATIONS 
import java.awt.Point;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class Surcharge {

    private ArrayList<Point> points = new ArrayList<>();

    private float t_step_input;
    private int taille = 100;
    private ArrayList<Point> Est_surcharge;
    int nb_point;

    //Constructeur
    public Surcharge() {
        points = new ArrayList<>();
        t_step_input = 0.001f;
        Est_surcharge = new ArrayList<>();
        nb_point = 0;
    }

    //Getter and setter
    public int getNb_point() {
        return nb_point;
    }
    
    
    public void drawCurvePixel(float t, Terrain terrain, int dimension){
        /*
            Cette methode dessine la courbe de surcharge selon le modele de la courbe de Bezier
        */

        float[] b_poly = new float[points.size()];
        int n = points.size() - 1;
        for(int i = 0; i <= n; i ++){
            b_poly[i] = (Fac(n)/(Fac(i)*(Fac(n-i))))*(float)(Math.pow((1-t), (n-i)))*(float)(Math.pow(t, i));
        }

        float sumX = 0, sumY = 0;
        for(int a = 0; a<= n; a++){
            sumX += b_poly[a]*points.get(a).x;
            sumY += b_poly[a]*points.get(a).y;
        }
        int x = (int)Math.round(sumX);
        int y = (int)Math.round(sumY);

        
        
        for(int i = 0; i < dimension; i++){
            for(int j = 0; j < dimension; j++){
                if((x+i >= 0)&&(x+i < terrain.getGrille().length)){
                    if((y+j >= 0)&&(y+j < terrain.getGrille().length)){
                        if((terrain.getGrille()[y+j][x+i].isInflammable())&&(terrain.getGrille()[y+j][x+i].getType_case() != 3)){   //Si case n terre, ni maison, ni eau
                            terrain.getGrille()[y+j][x+i].setSurcharge(true);
                            Est_surcharge.add(new Point(x+i,y+j));
                        }
                    }
                } 
            }
        }
    }

    public int Fac(int n){
        if (n==0){
            return 1;
        }
        else{
            for (int x = n-1; x> 0; x--){
                n*=x;
            }
            return n;
        }
    }
    

    public void SurchargeConsole(Terrain terrain, String type_affichage) throws IOException{
        /*
            Cette methode permert de surcharger le terrain console
        */
        Scanner sc = new Scanner(System.in);
        int nb_point = 0;
        int x;
        int y;

        if ("Console".equals(type_affichage)){
            terrain.AffichageFeuConsole();
        }
        else {terrain.AffichageFeuProcedurale();}
        
        while (true){
            if (nb_point < 2){
                System.out.println("Entrer le "+ (nb_point+1) +" point");
                x = sc.nextInt();
                y = sc.nextInt();
            }
            else {
                System.out.println("Entrer le "+ (nb_point+1) +" point");
                System.out.println("ou taper -1 pour arreter");
                x = sc.nextInt();
                if (x == -1){
                    return;     //le return fait sortir de la while !! ET !! de la Methode 
                }
                y = sc.nextInt();
                for(int i = 0; i < Est_surcharge.size(); i++){
                    terrain.getGrille()[(int) Est_surcharge.get(i).getY()][(int) Est_surcharge.get(i).getX()].setSurcharge(false); 
                }
                Est_surcharge = new ArrayList<>();
            }
            nb_point++;

            Point p = new Point(x,y);
            points.add(p);
            if (nb_point >= 2){
                float t = 0;
                while (t<=1){
                    drawCurvePixel(t,terrain,1);
                    t += t_step_input;
                }
                if ("Console".equals(type_affichage)){
                terrain.AffichageFeuConsole();
                }
                else {terrain.AffichageFeuProcedurale();}
            }
        }
    }
    
    public void SurchargeGraphique(Terrain terrain, int[]coord, int dimension){
        /*
            Cette methode permert de surcharger le terrain console
        */
        
        int x = coord[1];
        int y = coord[0];

        nb_point++;
        
        if (nb_point >= 2){

            for(int i = 0; i < Est_surcharge.size(); i++){
                terrain.getGrille()[(int) Est_surcharge.get(i).getY()][(int) Est_surcharge.get(i).getX()].setSurcharge(false); 
            }
            Est_surcharge = new ArrayList<>();

            
            Point coordonnee = new Point(x,y);
            points.add(coordonnee);
            float t = 0;
            while (t<=1){
                drawCurvePixel(t,terrain,dimension);
                t += t_step_input;
            }
            
        }
    }
    
    public void CreationTerre(Terrain terrain){
        /*
            Cette methode permert de creer le chemin de terre
        */
        for(int i = 0; i < terrain.getGrille().length; i++){
            for(int j = 0; j < terrain.getGrille()[0].length; j++){
                if(terrain.getGrille()[i][j].isSurcharge()){
                    terrain.getGrille()[i][j].setSurcharge(false);
                    terrain.getGrille()[i][j] = new Terre(j,i,terrain.getNiveau().getNiveau());
                }
            }
        }
    }
    
    public void Suppression(Terrain terrain){
        /*
            Cette methode permert de supprimer la surcharge
        */
        for(int i = 0; i < terrain.getGrille().length; i++){
            for(int j = 0; j < terrain.getGrille()[0].length; j++){
                if(terrain.getGrille()[i][j].isSurcharge()){
                    terrain.getGrille()[i][j].setSurcharge(false);
                }
            }
        }
    } 
}

