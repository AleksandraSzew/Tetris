import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Game extends JPanel implements KeyListener, ActionListener {
    private boolean play =false;
    private int score = 0;

    private int maxBricks = 20;
    private Timer timer;
    private int delay = 8;

    private int positionX = 310;
    private int ballPosX =120;
    private int ballPosY= 350;
    private int ballDirX = -1;
    private int ballDirY = -2;

    private BricksGenerate bricksGenerate;
    public Game(){
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        bricksGenerate = new BricksGenerate(3,7);
        timer = new Timer(delay, this);
        timer.start();
    }

    public void paint(Graphics graphics){
        //tlo
        graphics.setColor(Color.BLACK);
        graphics.fillRect(1,1,692,592);


        //kafelki
        bricksGenerate.draw((Graphics2D) graphics,Color.red);
        //granice
        graphics.setColor(Color.red);
        graphics.fillRect(0,0,3,592 );
        graphics.fillRect(0,0,692,3 );
        graphics.fillRect(691,0,3,592 );

        //paletka
        graphics.setColor(Color.green);
        graphics.fillRect(positionX,550,100,8);

        //pileczka
        graphics.setColor(Color.CYAN);
        graphics.fillOval(ballPosX,ballPosY,15,15);

        //wynik
        graphics.setColor(Color.white);
        graphics.setFont(new Font("TimesRoman", Font.BOLD,20));
        graphics.drawString("Punkty: "+score,590,40);

        if(ballPosY > 570){
            play= false;
            ballDirX = 0;
            ballDirY = 0;
            graphics.setColor(Color.white);
            graphics.setFont(new Font("TimesRoman", Font.BOLD,50));
            graphics.drawString("KONIEC GRY",190,300);

            graphics.setFont(new Font("TimesRoman", Font.BOLD,20));
            graphics.drawString("Wcisnij Enter by rozpoczac ponownie",170,350);
        }
        //wygrana
        if(maxBricks == 0){
            play= false;
            ballDirX = 0;
            ballDirY = 0;
            graphics.setColor(Color.white);
            graphics.setFont(new Font("TimesRoman", Font.BOLD,50));
            graphics.drawString("WYGRANA",190,300);

            graphics.setFont(new Font("TimesRoman", Font.BOLD,20));
            graphics.drawString("Wcisnij Enter by rozpoczac ponownie",170,350);
        }
    }
    public void moveLeft(){
        play =true;
        positionX -=20;
    }

    public void moveRight(){
        play=true;
        positionX +=20;
    }
    @Override
    public void keyTyped(KeyEvent e) { }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_LEFT){
            if(positionX < 10){
                positionX =10;
            } else moveLeft();

        }
        else if(e.getKeyCode()==KeyEvent.VK_RIGHT){
            if(positionX >= 580){
                positionX =580;
            } else moveRight();
        }

        if(e.getKeyCode() == KeyEvent.VK_ENTER){
            if(!play){
                play = true;
                ballPosX =120;
                ballPosY= 350;
                ballDirX = -1;
                ballDirY = -2;
                positionX =310;
                score=0;
                maxBricks = 20;
                bricksGenerate = new BricksGenerate(3,7);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) { }

    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();
        if(play){

            if(new Rectangle(ballPosX,ballPosY,15,15).intersects(new Rectangle(positionX,550,100,8))){
                ballDirY = -ballDirY;

            }
            //sprawdzanie pozycji kafelka
          A:  for(int i=0; i<bricksGenerate.bricks.length; i++){
                for(int j=0; j<bricksGenerate.bricks[0].length; j++){
                    if(bricksGenerate.bricks[i][j] > 0){
                        int brickX= j*bricksGenerate.bWidth + 80;
                        int brickY= i*bricksGenerate.bHeight +50;
                        int brickWidth = bricksGenerate.bWidth;
                        int brickHeight= bricksGenerate.bHeight;
                        Rectangle rectangle = new Rectangle(brickX,brickY,brickWidth,brickHeight);
                        Rectangle ballR = new Rectangle(ballPosX,ballPosY,15,15);
                        Rectangle brickR = rectangle;

                        if(ballR.intersects(brickR)){
                            bricksGenerate.setBricks(0,i,j);
                            maxBricks--;
                            score +=5;

                            if(ballPosX + 14 <= brickR.x || ballPosY +1 >= brickR.x + brickR.width){
                                ballDirX = -ballDirX;
                            }
                            else
                                ballDirY = -ballDirY;
                        break A;
                        }
                    }
                }
            }

            ballPosX += ballDirX;
            ballPosY += ballDirY;
            if(ballPosX < 0) {
                ballDirX = - ballDirX;
            }
            if(ballPosY < 0) {
                ballDirY = - ballDirY;
            }
            if(ballPosX > 670) {
                ballDirX = - ballDirX;
            }
        }
        repaint();
    }
}
