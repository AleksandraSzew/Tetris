import java.awt.*;
import java.util.Random;

public class BricksGenerate {
    public int [][] bricks;
    public int bWidth;
    public int bHeight;
    private Color color;

    private int randRed() {
        int red;
        Random randomNumber = new Random();
        red = randomNumber.nextInt(255);
        return red;
    }

    private int randGreen() {
        int green;
        Random randomNumber = new Random();
        green = randomNumber.nextInt(255);
        return green;
    }

    private int randBlue() {
        int blue;
        Random randomNumber = new Random();
        blue = randomNumber.nextInt(255);
        return blue;
    }

    public BricksGenerate(int row, int col){
        bricks = new int[row][col];
            for(int i=0; i<bricks.length;i++){
                for (int j=0; j< bricks[0].length; j++){

                    bricks[i][j] = 1; //do sprawdzenia 1 - blok istnieje

                }
            }
            bWidth = 540/col;
            bHeight = 150/row;
    }
    public Color generateColor(Color color){
        randRed();
        randGreen();
        randBlue();
        this.color = new Color(randRed(),randGreen(),randBlue());
        return color;
    }
    public void draw(Graphics2D graphics,Color color){

        for(int i=0; i<bricks.length;i++){
            for (int j=0; j< bricks[0].length; j++){
                if(bricks[i][j] > 0){

                    //generateColor(color);
                    graphics.setColor(color);
                    graphics.fillRect(j*bWidth + 80, i* bHeight + 50, bWidth,bHeight);
                    graphics.setStroke(new BasicStroke(3));
                    graphics.setColor(Color.black);
                    graphics.drawRect(j*bWidth + 80, i* bHeight + 50, bWidth,bHeight);
                }

                }

            }
        }

        public void setBricks(int value, int row, int col){
        bricks[row][col] = value;
        }


}
