package com.tanker1;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TankFrame extends Frame {
    private int x = 200;
    private int y = 200;
    public TankFrame(){
        setSize(800,600);
        setResizable(false);
        setTitle("tank war");
        setVisible(true);
        addKeyListener(new MyKeyListener());
        addWindowListener(
                new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent event){
                        System.exit(0);
                    }
                }
        );
    }

    @Override
    public void paint(Graphics g){
        System.out.println("paint");
        g.fillRect(x,y,50,50);
        x+=10;
        if(x>1000){
            x=100;
        }
//        y+=10;
    }


    class MyKeyListener extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent event){
          //  x+=100;
          //  repaint();
//        y+=10;
        }

        @Override
        public void keyReleased(KeyEvent e) {
            System.out.println("key released");
        }
    }
}
