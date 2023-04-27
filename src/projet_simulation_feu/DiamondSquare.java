package projet_simulation_feu;

/*
 * Projet de simulation de feu de foret
 * Auteurs : Maxime Innocenti & Antoine Henriet
 * 01/02/2022
 * Codé en Java
*/

import java.util.Random;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.imageio.ImageIO;
import java.io.*;


public class DiamondSquare {

    private static float[][] data;
    static int width;
    static int height;
    private long seed;
    private int DATA_SIZE;

    
    //Constructeur
    public DiamondSquare (int n, long seed){
        this.seed = seed;//(long) new Random().nextLong();
        DATA_SIZE = n;
        data = CreationDiamondSquare(getSeed(), getDATA_SIZE());
    }

    //Getter & Setter
    public float[][] getData() {
        return data;
    }

    public void setData(float[][] data) {
        DiamondSquare.data = data;
    }

    public long getSeed() {
        return seed;
    }

    public void setSeed(long seed) {
        this.seed = seed;
    }

    public int getDATA_SIZE() {
        return DATA_SIZE;
    }

    public void setDATA_SIZE(int dATA_SIZE) {
        DATA_SIZE = dATA_SIZE;
    }


    public float [][] CreationDiamondSquare(long seed , int DATA_SIZE) {
        int width = DATA_SIZE;
        int height = DATA_SIZE;
        final float SEED = 1000.0f;
        data = new float[DATA_SIZE][DATA_SIZE];
        data[0][0] = new Random().nextFloat();
        data[0][DATA_SIZE-1] = new Random().nextFloat();
        data[DATA_SIZE-1][0] = new Random().nextFloat();
        data[DATA_SIZE-1][DATA_SIZE-1] = new Random().nextFloat();
        //System.out.println(data[0][0] +" "+ data[0][DATA_SIZE-1] +" "+ data[DATA_SIZE-1][0] +" "+ data[DATA_SIZE-1][DATA_SIZE-1]);

        float valmin = Float.MAX_VALUE;
        float valmax = -valmin;

        float h = 1000.0f;
        Random r = new Random(seed);
        //System.out.println(r);
        for(int sideLength = DATA_SIZE-1;
                sideLength >= 2;
                sideLength /=2, h/= 2.0){
            int halfSide = sideLength/2;

            for(int x=0;x<DATA_SIZE-1;x+=sideLength){
                for(int y=0;y<DATA_SIZE-1;y+=sideLength){
                    float avg = data[x][y] + //top left
                            data[x+sideLength][y] +//top right
                            data[x][y+sideLength] + //lower left
                            data[x+sideLength][y+sideLength];//lower right
                    avg /= 4.0;

                    float i = (r.nextFloat()*2*h) - h;
    
                    data[x+halfSide][y+halfSide] = 
                            avg + i;

                    valmax = Math.max(valmax, data[x+halfSide][y+halfSide]);
                    valmin = Math.min(valmin, data[x+halfSide][y+halfSide]);
                }
            }
            for(int x=0;x<DATA_SIZE-1;x+=halfSide){
                for(int y=(x+halfSide)%sideLength;y<DATA_SIZE-1;y+=sideLength){
                    float avg = 
                            data[(x-halfSide+DATA_SIZE-1)%(DATA_SIZE-1)][y] + //left of center
                            data[(x+halfSide)%(DATA_SIZE-1)][y] + //right of center
                            data[x][(y+halfSide)%(DATA_SIZE-1)] + //below center
                            data[x][(y-halfSide+DATA_SIZE-1)%(DATA_SIZE-1)]; //above center
                    avg /= 4.0;
                    float i = (r.nextFloat()*2*h) - h;
                    avg = avg + i;
                    data[x][y] = avg;

                    valmax = Math.max(valmax, avg);
                    valmin = Math.min(valmin, avg);

                    if(x == 0)  data[DATA_SIZE-1][y] = avg;
                    if(y == 0)  data[x][DATA_SIZE-1] = avg;
                }
            }
        }
        for(int i=0; i<width; i++) {
            for(int j=0; j<height; j++) { 
                data[i][j] = (data[i][j] - valmin) / (valmax - valmin); 
                data[i][j] = data[i][j];
            } 
        }
        return data;
    }



    public void AffichageDimondSquare(float [][] heights) throws IOException {
        JFrame frame = new JFrame("Diamond Square Test");
        Game g = new Game();
    
        int DATA_SIZE = heights.length;

        BufferedImage image = new BufferedImage(DATA_SIZE, DATA_SIZE, BufferedImage.TYPE_INT_RGB);

        for(int x = 0; x < DATA_SIZE; x++){
            for(int y = 0; y < DATA_SIZE; y++){	
                if (heights[x][y] < 0.125){                                  //eau
                    int rgb = Color.blue.getRGB();
                    image.setRGB(x, y, rgb);
                }
                else if (heights[x][y] >= 0.125 && heights[x][y] < 0.175){   //terre
                    int rgb = new Color(139,69,19).getRGB();
                    image.setRGB(x, y, rgb);
                }
                else if (heights[x][y] >= 0.175 && heights[x][y] < 0.4){   //prairie
                    int rgb = new Color(127,255,0).getRGB();
                    image.setRGB(x, y, rgb);
                }
                else if (heights[x][y] >= 0.4 && heights[x][y] < 0.75){     //foret
                int rgb = new Color(0,100,0).getRGB();
                image.setRGB(x, y, rgb);
                }
                else if (heights[x][y] >= 0.75 && heights[x][y] < 0.85){      //prairie
                    int rgb = new Color(127,255,0).getRGB();
                    image.setRGB(x, y, rgb);
                }else {                                                      //ville
                    int rgb = new Color(119,136,153).getRGB();
                    image.setRGB(x, y, rgb);
                }
            }
        }
        ImageIO.write(image, "png", new File("noise.png"));
    }	
}

class Game extends Canvas{

	private static final long serialVersionUID = 5986085377374594305L;
	
	public BufferedImage img = new BufferedImage(900, 900, BufferedImage.TYPE_INT_RGB);
	
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(img, 0, 0, null);
	}
	
	public void setPixel(int x, int y, int height){
		final int size = 10;
		Graphics g = img.getGraphics();
		g.setColor(new Color(height, height, height));
		g.fillRect(x * size, y * size, size, size);
		g.dispose();
	}
}

        