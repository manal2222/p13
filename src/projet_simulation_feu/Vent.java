package projet_simulation_feu;

/*
 * Projet de simulation de feu de foret
 * Auteurs : Maxime Innocenti & Antoine Henriet
 * 01/02/2022
 * Codé en Java
*/

public class Vent {

    //Attributs
    private int force;
    private int orientation;
    private double [][] Mat_Vent;

    //---Matrices des vents pour un vent du NORD---\\
    private double [][] vent_nul = {{   0, 0.01, 0.01, 0.01,    0},
                                    {0.01,  0.2,  0.3,  0.2, 0.01},
                                    {0.01,  0.3,    1,  0.3, 0.01},
                                    {0.01,  0.2,  0.3,  0.2, 0.01},
                                    {   0, 0.01, 0.01, 0.01,    0}};

    private double [][] vent_modere = {{0,   0,     0,    0, 0},
                                       {0,  0.1,  0.2,  0.1, 0},
                                       {0,  0.3,    1,  0.3, 0},
                                       {0,  0.3,  0.4,  0.3, 0},
                                       {0, 0.02, 0.05, 0.02, 0}};

    private double [][] vent_fort = {{0, 0,    0,   0,    0, 0, 0},
                                     {0, 0,    0,   0,    0, 0, 0},
                                     {0, 0, 0.05, 0.1, 0.05, 0, 0},
                                     {0, 0, 0.25,   1, 0.25, 0, 0},
                                     {0, 0,  0.4, 0.5,  0.4, 0, 0},
                                     {0, 0, 0.05, 0.1, 0.05, 0, 0},
                                     {0, 0,    0, 0.01,   0, 0, 0}};

    private double [][] vent_violent = {{0, 0,    0,    0,    0, 0, 0},
                                        {0, 0,    0,    0,    0, 0, 0},
                                        {0, 0,    0,    0,    0, 0, 0},
                                        {0, 0,  0.1,    1,  0.1, 0, 0},
                                        {0, 0,  0.5,  0.7,  0.5, 0, 0},
                                        {0, 0,  0.2,  0.3,  0.2, 0, 0},
                                        {0, 0, 0.01, 0.05, 0.01, 0, 0}};

    //Getter & Setter
    public int getForce() {
        return force;
    }

    public void setForce(int force) {
        this.force = force;
    }

    public int getOrientation() {
        return orientation;
    }

    public void setOrientation(int orientation) {
        this.orientation = orientation;
        Mat_Vent = Rotation(SwitchCase(force), this.orientation, force);
        System.out.println("new");
        this.setMat_Vent(Mat_Vent);
    }
    
    public double[][] getMat_Vent() {
        return Mat_Vent;
    }

    public void setMat_Vent(double[][] mat_Vent){
        Mat_Vent = mat_Vent;
    }

    //Constructeurs
    public Vent(int force, int orientation) throws Erreur {
        if (force!=0 && force!=1 && force!=2 && force !=3){
            throw new Erreur("\nErreur, la force du vent n'est pas correcte !\n");
        }
        this.force = force;
        this.orientation = orientation;
        this.Mat_Vent = Rotation(SwitchCase(force), orientation, force);
    }

    //Methodes
    public double[][] SwitchCase(int force) {
        /*
            Cette méthode retourne la matrice du vent en fonction de la force de ce dernier.
        */
        switch (force) {
            case 0:
                return vent_nul;
            case 1:
                return vent_modere;
            case 2:
                return vent_fort;
            case 3:
                return vent_violent;
            default:
                return null;
        }
    }

    public void Affichage(double [][] m) {
        /*
            Cette méthode affiche la matrice du vent.
        */
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[0].length; j++) {
                System.out.print(m[i][j] + "\t");
            }
            System.out.println();
        }
    }

    public double[][] Rotation(double[][] Matrice_vent, int rot, int force){
        /* 
            Cette methode permet de faire tourner les composantes des matrices de vent selon l'orientation du vent choisie
        */
        int debut;
        int tour = rot/45;          //Rotation de la matrice de 45° en 45°
        int reste = rot%45;         //Si cets pas un multiple de 45 on fera une rotation de l'angle qu'il reste
        int cpt = tour;             //compteur de tour a faire pour faire tourner la matrice
        double r;
        double [][] temp = new double [Matrice_vent.length][Matrice_vent.length];
        final int longueur = 2;

        if(Matrice_vent.length == 7){       //recuperation de la taile de la matrice
            debut = 2;                      //permet de positionner le debut de la rotation
        }else{debut=1;}

        for(int x = 0; x < (tour+1); x++){

            temp = Actualisation(Matrice_vent, temp); //actualisation de la matrice en cours de rotation a chaque tour

            if(cpt == 0){           //si la matrice doit tourner d'une valeur inferieur a 45°
                if (reste == 0){
                    break;
                }
                r = reste/45.0;
            }else { r = 1;}         //la matrice tourne de 45° car on ne peut tourner que de 45° maximum a chaque itération de la boucle for

            Matrice_vent = rotation_couronne(Matrice_vent, temp, debut,r,longueur);         //rotation de la premiere couronne à l'interieur de la matrice
            Matrice_vent = rotation_couronne(Matrice_vent, temp, debut-1,r,longueur*2);     //rotation de la deuxieme couronne a l'interieur de la matrice
            
            if(Matrice_vent.length == 7){                                                   //si la matrice est une 7x7
                Matrice_vent = rotation_couronne(Matrice_vent, temp, debut-2,r,longueur*3); //rotation de la troisieme couronne a l'interieur de la matrice
            }
            
            temp = Actualisation(Matrice_vent, temp);   //actualisation de la matrice en cours de rotation a chaque tour

            Matrice_vent = rotation_couronne(Matrice_vent, temp, debut-1,r,longueur*2);     //rotation de la deuxieme couronne a l'interieur de la matrice
            if(Matrice_vent.length == 7){
                Matrice_vent = rotation_couronne(Matrice_vent, temp, debut-2,r,longueur*3); //rotation de la troisieme couronne a l'interieur de la matrice

                temp = Actualisation(Matrice_vent, temp);    //actualisation de la matrice en cours de rotation a chaque tour
                
                Matrice_vent = rotation_couronne(Matrice_vent, temp, debut-2,r,longueur*3);  //rotation de la troisieme couronne a l'interieur de la matrice
            }
            
            cpt -=1;
        }
        return Matrice_vent;
    }

    public static double[][] rotation_couronne(double[][] Matrice_vent, double[][] temp, int debut, double r, int couronne) {
        /*
            Cette methode permet de faire "tourner" chaque composantes de la matrices de vent
        */
        for(int i = debut+1; i <= debut+couronne; i++){
            Matrice_vent[debut][i] =  temp[debut][i-1]*r +  temp[debut][i]*(1-r);
            Matrice_vent[i][debut+couronne] =  temp[i-1][debut+couronne]*r +  temp[i][debut+couronne]*(1-r);
            Matrice_vent[debut+couronne][i-1] =  temp[debut+couronne][i]*r +  temp[debut+couronne][i-1]*(1-r);
            Matrice_vent[i-1][debut] =  temp[i][debut]*r +  temp[i-1][debut]*(1-r);
        }
        return Matrice_vent;
    }

    public static double[][] Actualisation(double[][] Matrice_vent, double[][] temp) {
        /*
            Cette methode remplace l'ancienne matrice de vent par la nouvelle matrice de vent avec la nouvelle orientation de vent
        */
        for(int i = 0; i < Matrice_vent.length; i++){
            for(int j = 0; j < Matrice_vent.length; j++){
                temp[i][j] = Matrice_vent[i][j];
            }
        }
        return temp;
    }
}
