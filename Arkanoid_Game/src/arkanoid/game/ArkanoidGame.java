package arkanoid.game;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.*;

public class ArkanoidGame {

    public static final int WIDTH= 900,HEIGHT=800;
    public static void main(String[] args)
    {
        JFrame arkanoidFrame=new JFrame("Classic Arkanoid");
       
        arkanoidFrame.setSize(WIDTH, HEIGHT);
        
        arkanoidFrame.setLocationRelativeTo(null);
        
        arkanoidFrame.setResizable(false);
        
        brickPanel bp=new brickPanel();
        arkanoidFrame.add(bp);
        
        arkanoidFrame.setVisible(true);
        arkanoidFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
         bp.run();
         
         
         if(bp.levelPassed()){
             enemyPanel ep=new enemyPanel();
             arkanoidFrame.add(ep);
             arkanoidFrame.setVisible(true);
             arkanoidFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
             ep.run();
             
         }
    }
    
}
