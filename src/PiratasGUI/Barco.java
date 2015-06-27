/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PiratasGUI;

import AppPackage.AnimationClass;
import java.awt.Color;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Mary S. Gonzalez
 */
public class Barco extends javax.swing.JLabel{
    private final AnimationClass barcoAnimated;
    private JLabel labelBarco;
    public Hilo movimiento;
    public int llegada;
    private JPanel panel;
    private int posx, posy, posx1, posy1;
    private int movX, movY;
    private String filePath = new File("").getAbsolutePath();
    
    /*Imagenes*/
    private ImageIcon imageBarco, kraken, tormenta, tornado ;

    class Hilo extends Thread {
        private BarcoGUI ship;
        private JPanel pan;
        
        public Hilo(int x, int y, int x1, int y1, JPanel p){
            posx = x;
            posx1 = x1;
            posy = y;
            posy1 = y1;
            pan = p;
            ship = new BarcoGUI(pan, "Venganza Errante");
        }

        public void run() {
            ship.AparecerBarco(posx, posy);
//            if(ship.MoverBarco(posx1, posy1)){
//                System.out.println("Termino 1er mov");
//            }
//            if(ship.MoverBarco(60, 200)){
//                System.out.println("Termino 2do mov");
//            }
//            if(ship.MoverBarco(300, 200)){
//                System.out.println("Termino 3er mov");
//            }
            ship.setCalamidadBarco("Tornado");
        //    ship.OcultarBarco();
        }
    }
    
    public void MoverBarco(){
            if(posx1 >= posx){
                movX = 1;   // El punto al que queremos movernos esta a la derecha
            }else
            if(posx1 < posx){
                movX = 2;   // El punto al que queremos movernos esta a la izquierda
            } 
            if(posy1 >= posy){
                movY = 1;   // El punto al que queremos movernos esta hacia abajo
            }else
            if(posy1 < posy){
                movY = 2;   // El punto al que queremos movernos esta hacia arriba
            }
            
            System.out.println("EMPECE A MOVERME  "+movX +" "+ movY);
            
            // MOVIMIENTO EN EL EJE X
            if(movX == 1){
                System.out.println("Entre en x1");
                barcoAnimated.jLabelXRight(posx, posx1, 100, 20, labelBarco);   // Se mueve hacia la derecha
            }else
            if(movX == 2){
                System.out.println("Entre en x2");
                barcoAnimated.jLabelXLeft(posx, posx1, 100, 20, labelBarco);    // Se mueve hacia la izquierda
            }    
            
            // MOVIMIENTO EN EL EJE Y
            if(movY == 1){
                System.out.println("Entre en y1");
                barcoAnimated.jLabelYDown(posy, posy1, 100, 20, labelBarco);    // Se mueve hacia la abajo
            }else
            if(movY == 2){
                System.out.println("Entre en y2");
                barcoAnimated.jLabelYUp(posy, posy1, 100, 20, labelBarco);      // Se mueve hacia la arriba
            }
            int sw=0;
            System.out.println("\nprime X:"+labelBarco.getX()+"\n"+"Y:"+labelBarco.getY());
            while (sw == 0){
            //    System.out.println("\nX:"+labelBarco.getX()+"\n"+"Y:"+labelBarco.getY());
                if((labelBarco.getX() == posx1) && (labelBarco.getY() == posy1)){
                 
                    sw = 1;
                }  
                System.out.print("");
            }
            System.out.println("TERMINE DE MOVERME");
           // labelBarco.setBackground(Color.yellow);
            
           // labelBarco.setVisible(false);
            System.out.println("X nuevo = " + labelBarco.getX());
            System.out.println("Y nuevo = " + labelBarco.getY());
            labelBarco.setLocation(labelBarco.getX(), labelBarco.getY());
            
            
            /*Al llegar, evaluar si el sitio tiene una calamaidad. Si tiene, llamar a setCalamidadBarco*/
            setCalamidadBarco("Kraken");
            
            
          //  labelBarco.setVisible(true);
            System.out.println("X nuevo2 = " + labelBarco.getX());
            System.out.println("Y nuevo2 = " + labelBarco.getY());
        }
        
        public void setCalamidadBarco(String calamidad){
            switch (calamidad){
               case "Kraken": labelBarco.setIcon(kraken); break;
               case "Tornado": labelBarco.setIcon(tornado); break;
               case "Tormenta": labelBarco.setIcon(tormenta); break;    
               default:labelBarco.setIcon(imageBarco); break;    
           }
        }
    
        
    public Barco(JPanel p, String nombreBarco){
        panel = p;
        barcoAnimated = new AnimationClass();
        labelBarco = new JLabel();
        
        /*Imagenes*/
        switch (nombreBarco){
            case "Venganza Errante":
                imageBarco = new ImageIcon(filePath + File.separator + "src" + File.separator + "images" + File.separator + "barcoPirata.png");
                kraken =  new ImageIcon(filePath + File.separator + "src" + File.separator + "images" + File.separator + "barcoPkraken.png");
                tornado = new ImageIcon(filePath + File.separator + "src" + File.separator + "images" + File.separator + "barcoPtornado.png");
                tormenta = new ImageIcon(filePath + File.separator + "src" + File.separator + "images" + File.separator + "barcoPtormenta.png");
                
                break;
            case "Interceptor": 
                imageBarco = new ImageIcon(filePath + File.separator + "src" + File.separator + "images" + File.separator + "interceptorBarco.png");
                kraken =  new ImageIcon(filePath + File.separator + "src" + File.separator + "images" + File.separator + "intercepkraken.png");
                tornado = new ImageIcon(filePath + File.separator + "src" + File.separator + "images" + File.separator + "interceptornado.png");
                tormenta = new ImageIcon(filePath + File.separator + "src" + File.separator + "images" + File.separator + "interceptormenta.png");
                break;
            case "Invencible": 
                imageBarco = new ImageIcon(filePath + File.separator + "src" + File.separator + "images" + File.separator + "invenBarco.png");
                kraken =  new ImageIcon(filePath + File.separator + "src" + File.separator + "images" + File.separator + "invenkraken.png");
                tornado = new ImageIcon(filePath + File.separator + "src" + File.separator + "images" + File.separator + "inventornado.png");
                tormenta = new ImageIcon(filePath + File.separator + "src" + File.separator + "images" + File.separator + "inventormenta.png");
                break;    
        }
        labelBarco.setIcon(this.imageBarco);
    }

   /**
    * Crea el hilo que realizara el movimiento del barco
    * 
    * @param x1 Posicion en el eje X de la pantalla donde el Barco Terminara el movimiento
    * @param y1 Posicion en el eje Y de la pantalla donde el Barco Terminara el movimiento
    */
   public void CrearHilo(int x, int y, int x1, int y1){ 
       movimiento = new Hilo(x,y,x1,y1, panel);
   }
   
   /**
    * Recibe las posicion en la que apararecera el barco e iniciara el movimiento
    * 
    * @param x Posicion en el eje X de la pantalla donde el Barco iniciara el movimiento
    * @param y Posicion en el eje Y de la pantalla donde el Barco iniciara el movimiento
    */
   public void AparecerBarco(int x, int y){
       panel.add(labelBarco);
       labelBarco.setBounds(x, y, 150, 136);
       labelBarco.setVisible(true);
   }
   
}


