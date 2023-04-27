/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pompier;

import projet_simulation_feu.Canadair;
import static projet_simulation_feu.Canadair.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import static java.awt.event.KeyEvent.VK_D;
import static java.awt.event.KeyEvent.VK_J;
import static java.awt.event.KeyEvent.VK_K;
import static java.awt.event.KeyEvent.VK_Q;
import static java.awt.event.KeyEvent.VK_SPACE;
import static java.awt.event.KeyEvent.VK_Z;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JPanel;
import projet_simulation_feu.Terrain;

/**
 *
 * @author Think
 */
public class GridPompier extends javax.swing.JPanel {

    Terrain terrain;
    int pixel = 1;
    int taille  = 9;
    int x=0,y=0;
    
    private Graphics2D g2;
    private BufferedImage image;
    private int width = 512;
    private int height = 512;
    private Thread thread;
    private boolean start = true;
    private Key key;
    private ArrayList<Point> water = new ArrayList();
    private final int rayon = 20;
    
    // Game FPS
    private final int FPS = 60;
    private final int Target_Time = 1000000000/FPS; 
    //Game Object
    private Canadair canadair;
    private double center_x;
    private  double center_y;
    private float s = 1.0f;
    private int cpt_eau;
    private double humidite_eau = 1;
    private int arret_eau = 50;
    private JPanel pPompier;
    
    
    public GridPompier() {
        initComponents();
        Terrain terrain = null;
        image = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
        g2 = image.createGraphics();
        //g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        pPompier = null;
    }
    
    //Getter and Setter
    public void setTerrain(Terrain terrain) {
        this.terrain = terrain;
    }

    public Terrain getTerrain() {
        return terrain;
    }

    public void setTaille(int taille) {
        this.taille = taille;
    }

    public Thread getThread() {
        return thread;
    }

    public boolean isStart() {
        return start;
    }

    public void setThread(Thread thread) {
        this.thread = thread;
    }

    public void setWater(ArrayList<Point> water) {
        this.water = water;
    }

    public void setCpt_eau(int cpt_eau) {
        this.cpt_eau = cpt_eau;
    }

    public JPanel getpPompier() {
        return pPompier;
    }

    public void setpPompier(JPanel pPompier) {
        this.pPompier = pPompier;
    }

    public int getArret_eau() {
        return arret_eau;
    }

    public int getCpt_eau() {
        return cpt_eau;
    }
    
    
    
    
    public void start(){
        start = true;
        
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                water = new ArrayList();
                int[] tab;
                cpt_eau = 0;
                while(start){
                    long startTime = System.nanoTime();
                    center_x = canadair.getX() + Player_size_x/2 - rayon /2 + Player_size_x*Math.cos(Math.toRadians(canadair.getAngle()+180))/2;
                    center_y = canadair.getY() + Player_size_y/2 - rayon /2 + Player_size_y*Math.sin(Math.toRadians(canadair.getAngle()+180))/2;
                    
                    //getpPompier().getLpReservoir();
                    drawBackground();
                    drawGame();
                    render();
                    if(key.isKey_space() && cpt_eau < arret_eau){
                        cpt_eau ++;
                        if(cpt_eau > 15){
                            water.remove(0);
                        }
                        
                        Point pt = new Point((int) center_x,(int) center_y);
                        CalculDistance(pt);
                        water.add(pt);
                    }
                    if((cpt_eau>= arret_eau)&& (!water.isEmpty())){
                        water.remove(0);
                    }
                    
                    tab = new int[] {(int)center_x,(int) center_y};
                    tab = CalculCoord(tab);
                    if(tab[0] >= 0 && tab[0] < terrain.getGrille().length){   //verifie si cest dans la grille
                        if(tab[1] >= 0 && tab[1] < terrain.getGrille().length){
                            if(terrain.getGrille()[tab[1]][tab[0]].getType_case() == -1){
                                cpt_eau = 0;
                                water.clear();
                            }
                        }
                    }
                    
                    if ((center_x < 0 || center_x > width) || (center_y < 0 || center_y > height)) {
                        start = false;
                        drawBackground();
                        break;
                    }
                    
                    long time = System.nanoTime() - startTime;
                    if(time < Target_Time){
                        long sleep = (Target_Time-time) / 1000000;
                        sleep(sleep);
                    }
                }
            }
        });
        initObjectGame();
        initKeyboard();
        //repaint();
        drawBackground();
        thread.start(); 
    }
    
    public void stop(){
        thread = null; 
   }
    
    private void initObjectGame(){
        canadair = new Canadair();
        canadair.changeLocation(100, 10);
    }
    
    public void initKeyboard(){
        key = new Key();
        requestFocus();
        
        addKeyListener(new KeyAdapter(){
            @Override
            public void keyPressed(KeyEvent e){
                if(e.getKeyCode() == VK_Q){
                    key.setKey_left(true);
                }
                else if(e.getKeyCode() == VK_D){
                    key.setKey_right(true);
                }
                else if(e.getKeyCode() == VK_Z){
                    key.setKey_z(true);
                }
                else if(e.getKeyCode() == VK_SPACE){
                    key.setKey_space(true);
                                       
                }else if(e.getKeyCode() == VK_J){
                    key.setKey_j(true);
                }
                else if(e.getKeyCode() == VK_K){
                    key.setKey_k(true);
                    start = false;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if(e.getKeyCode() == VK_Q){
                    key.setKey_left(false);
                }
                else if(e.getKeyCode() == VK_D){
                    key.setKey_right(false);
                }
                else if(e.getKeyCode() == VK_Z){
                    key.setKey_z(false);
                }
                else if(e.getKeyCode() == VK_SPACE){
                    key.setKey_space(false);
                }
                else if(e.getKeyCode() == VK_J){
                    key.setKey_j(false);
                }
                else if(e.getKeyCode() == VK_K){
                    key.setKey_k(false);
                }
            }
        });
        new Thread(new Runnable() {
            @Override
            public void run() {

                double x = canadair.getX();
                double y = canadair.getY();
                while(start){
                    float angle = canadair.getAngle();
                    if(key.isKey_left()){
                        angle -= s;
                    }
                    if(key.isKey_right()){
                        angle += s;
                    }
                    
                    x = (canadair.getX() + s*Math.cos(Math.toRadians(angle)));
                    y = (canadair.getY() + s*Math.sin(Math.toRadians(angle)));
                    
                    canadair.changeAngle(angle);
                    canadair.changeLocation(x, y);
                    sleep(10);
                }
            }
        }).start();
    }
    
    public void drawBackground(){
        pixel = 9-taille;
        pixel = 1 << pixel;
        if(terrain == null){
            g2.setColor(Color.GRAY);
            g2.fillRect(x, y, 519, 519);
        }
        else {
            for (int j = 0; j < terrain.getGrille().length; j++) {
                y = j*pixel;
                for (int i = 0; i < terrain.getGrille().length; i++){
                    x = i*pixel;
                    if(terrain.getGrille()[j][i].isSurcharge() == true){         //terre car surchargé
                        g2.setColor(new Color(138,114,92));
                        g2.fillRect(x, y, pixel, pixel);
                    }
//                    if(terrain.getGrille()[j][i].isBombarde_eau()== true){         //eau car bombardé
//                        g2.setColor(Color.BLUE);
//                        g2.fillRect(x, y, pixel, pixel);
//                    }
                    else{
                        switch(terrain.getGrille()[j][i].getType_case()){
                            case -1:     //eau
                                g2.setColor(new Color(77,97,156));
                                g2.fillRect(x, y, pixel, pixel);
                                break;
                            case 0:     //terre
                                g2.setColor(new Color(138,114,92));
                                g2.fillRect(x, y, pixel, pixel);
                                break;
                            default :     //Prairie & Foret & Maison
                                switch (terrain.getGrille()[j][i].getEtat_flamme()){
                                    case "Intacte":
                                        switch (terrain.getGrille()[j][i].getType_case()){
                                            case 1:
                                                g2.setColor(new Color(65,91,66));
                                                g2.fillRect(x, y, pixel, pixel);
                                                break;
                                            case 2:
                                                g2.setColor(new Color(34,52,35));
                                                g2.fillRect(x, y, pixel, pixel);
                                                break;
                                            case 3:
                                                g2.setColor(new Color(127,132,125));
                                                g2.fillRect(x, y, pixel, pixel);
                                                break;
                                        }
                                        break;
                                    case "Enflammee":
                                        g2.setColor(new Color(255,0,0));
                                        g2.fillRect(x, y, pixel, pixel);
                                        break;
                                    case "Brule chaud":
                                        g2.setColor(new Color(255,69,0));
                                        g2.fillRect(x, y, pixel, pixel);
                                        break;
                                    case "Brule froid":
                                        switch (terrain.getGrille()[j][i].getBrule_froid()){
                                            case 3:
                                                g2.setColor(new Color(255,215,0));
                                                g2.fillRect(x, y, pixel, pixel);
                                                break;
                                            case 2:
                                                g2.setColor(new Color(255,255,0));
                                                g2.fillRect(x, y, pixel, pixel);
                                                break;
                                            case 1:
                                                g2.setColor(new Color(255,255,102));
                                                g2.fillRect(x, y, pixel, pixel);
                                                break;
                                            case 0:
                                                g2.setColor(new Color(211,211,211));
                                                g2.fillRect(x, y, pixel, pixel);
                                                break;
                                        }
                                        break;
                                    case "Cendre":
                                        g2.setColor(new Color(200,194,182));
                                        g2.fillRect(x, y, pixel, pixel);
                                        break;
                                }
                                break;
                        }
                    }
                }
            }
        } 
    }  
    
    public void drawGame(){
        canadair.draw(g2);
        pointBlue();
    }
    
    public void pointBlue(){
        if(water.size() != 0){
            g2.setColor(new Color(3,30,250));
            for(int i = 0; i < water.size(); i++){
                g2.fillOval((int) water.get(i).getX() - rayon /2, (int) water.get(i).getY() - rayon /2,rayon*2, rayon*2);
            }
        }
    }
    
    public void render(){
        Graphics g = getGraphics();
        g.drawImage(image, 0, 0, null);
        g.dispose();
    }
    
    public void sleep(long speed){
        try {
            Thread.sleep(speed);
        } catch (InterruptedException ex) {
            System.err.println(ex);
        }
    }
    
    public void CalculDistance(Point pt){      //calcul les cases a colorier en fonction de la position de la souris et du rayon du cercle demandé          
        double distance_X;
        double distance_Y;
        int x = (int) pt.getX() + rayon /2;
        int y = (int) pt.getY() + rayon /2;
        double rayon_Carre = Math.pow(rayon, 2); //met au carrre la valeur
        int[] valeurs;
        int[] recup;
        Random rand = new Random();
        double proba;
         
        for (int j = y-rayon; j < y+rayon; j++) {       //definit un carre de cote rayon autour du clique de souris
            for(int i = x-rayon; i < x+rayon; i++){
                if(i >= 0 && i < width){   //verifie si cest dans la grille
                    if(j >= 0 && j < height){
                        distance_X = Math.pow((i-x), 2);  //calcul les distance au carre entre le centre du cercle et les point contenu dqns le carre
                        distance_Y = Math.pow((j-y), 2);
                        if((distance_X+distance_Y) <= rayon_Carre){  //selectionne les points dans le cercle
                            valeurs = new int[] {i,j};
                            recup = CalculCoord(valeurs);
                            if(!terrain.getGrille()[recup[1]][recup[0]].isBombarde_eau()){                          //si la case n'est pas encore bmbardée
                                if(terrain.getGrille()[recup[1]][recup[0]].getEtat_flamme() == "Intacte"){          //si intacte, son pourcentage d'humidité augmente
                                    terrain.getGrille()[recup[1]][recup[0]].setHumidite_pourcent(1-humidite_eau);
                                }
                                else{
                                    proba = rand.nextDouble();                      //si elle est en feu, proba d'éteindre le feu
                                    if(proba < 0.9){
                                        switch(terrain.getGrille()[recup[1]][recup[0]].getEtat_flamme()){
                                            case "Enflammee":
                                                terrain.getGrille()[recup[1]][recup[0]].setEtat_flamme(terrain.getGrille()[recup[1]][recup[0]].getTab_etat_flamme()[4]);
                                                terrain.setNombre_enflammee(terrain.getNombre_enflammee()-1);
                                                terrain.setNombre_case_cendre(terrain.getNombre_case_cendre()+1);
                                                break;
                                            case "Brule chaud":
                                                terrain.getGrille()[recup[1]][recup[0]].setEtat_flamme(terrain.getGrille()[recup[1]][recup[0]].getTab_etat_flamme()[4]);
                                                terrain.setNombre_case_brule_chaud(terrain.getNombre_case_brule_chaud()-1);
                                                terrain.setNombre_case_cendre(terrain.getNombre_case_cendre()+1);
                                                break;
                                            case "Brule froid":
                                                terrain.getGrille()[recup[1]][recup[0]].setEtat_flamme(terrain.getGrille()[recup[1]][recup[0]].getTab_etat_flamme()[4]);
                                                terrain.setNombre_case_brule_froid(terrain.getNombre_case_brule_froid()-1);
                                                terrain.setNombre_case_cendre(terrain.getNombre_case_cendre()+1);
                                                break;
                                        }
                                    }
                                }
                            }
                            terrain.getGrille()[recup[1]][recup[0]].setBombarde_eau(true); //si ils appartiennent au cercle on les dessine
                        }
                    }
                }
            }
        }
        
    }
    
    public int[] CalculCoord(int[] valeurs){
        int [] tab = {-1,-1};
        int taille_pixel = 1 << (9-taille);
        tab[0] = valeurs[0] / taille_pixel;
        tab[1] = valeurs[1] / taille_pixel;
        return tab;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setMaximumSize(new java.awt.Dimension(512, 512));
        setMinimumSize(new java.awt.Dimension(512, 512));
        setPreferredSize(new java.awt.Dimension(512, 512));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 513, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 513, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
