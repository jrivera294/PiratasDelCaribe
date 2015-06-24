/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PiratasGUI;

import AppPackage.AnimationClass;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Personal
 */
public class BarcoGUI extends javax.swing.JLabel{
    private final AnimationClass barcoAnimated;
    private JLabel labelBarco;
    public int llegada;
    private JPanel panel;
    private String filePath = new File("").getAbsolutePath();
    
    /*Imagenes*/
    private ImageIcon imageBarco, kraken, tormenta, tornado ;
      
    public BarcoGUI(JPanel p, String nombreBarco){
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
   
   public void OcultarBarco(){
       labelBarco.setVisible(false);
       panel.remove(labelBarco);
   }
   
   public boolean MoverBarco(int posx1, int posy1){
        int movX, movY;
        int posx = this.labelBarco.getX();
        int posy = this.labelBarco.getY();
        System.out.println("Label origen x: "+posx+" y:"+posy);
        
        if(posx1 > posx){
            movX = 1;   // El punto al que queremos movernos esta a la derecha
        }else if(posx1 < posx){
            movX = 2;   // El punto al que queremos movernos esta a la izquierda
        }else{
            movX = 3;   // El punto al que queremos movernos esta al mismo nivel del eje x
        }

        if(posy1 > posy){
            movY = 1;   // El punto al que queremos movernos esta hacia abajo
        }else if(posy1 < posy){
            movY = 2;   // El punto al que queremos movernos esta hacia arriba
        }else{
            movY = 3;   // El punto al que queremos movernos esta al mismo nivel del eje y
        }

        System.out.println("EMPECE A MOVERME");

        // MOVIMIENTO EN EL EJE X
        if(movX == 1){
            barcoAnimated.jLabelXRight(posx, posx1, 100, 20, labelBarco);   // Se mueve hacia la derecha
        }else
        if(movX == 2){
            barcoAnimated.jLabelXLeft(posx, posx1, 100, 20, labelBarco);    // Se mueve hacia la izquierda
        }    

        // MOVIMIENTO EN EL EJE Y
        if(movY == 1){
            barcoAnimated.jLabelYDown(posy, posy1, 100, 20, labelBarco);    // Se mueve hacia la abajo
        }else
        if(movY == 2){
            barcoAnimated.jLabelYUp(posy, posy1, 100, 20, labelBarco);      // Se mueve hacia la arriba
        }
        
        int sw=0;
        while (sw == 0){
            if((labelBarco.getX() == posx1) && (labelBarco.getY() == posy1)){
                sw = 1;
            }  
            System.out.print("x: "+labelBarco.getX()+" y:"+labelBarco.getY());
        }
        
        System.out.println("TERMINE DE MOVERME");
        return true;
    }
        
    public void setCalamidadBarco(String calamidad){
        switch (calamidad){
           case "Kraken": labelBarco.setIcon(kraken); break;
           case "Tornado": labelBarco.setIcon(tornado); break;
           case "Tormenta": labelBarco.setIcon(tormenta); break;    
           default:labelBarco.setIcon(imageBarco); break;    
       }
    }
   
}
