/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PiratasGUI;

import AppPackage.AnimationClass;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    
    private JLabel labelPelea;
    
    /*Imagenes*/
    private ImageIcon imageBarco, kraken, tormenta, tornado ;
      
    public BarcoGUI(JPanel p, String nombreBarco){
        panel = p;
        barcoAnimated = new AnimationClass();
        labelBarco = new JLabel();
        
        labelPelea= new JLabel();
        
        /*Imagenes*/
        switch (nombreBarco){
            case "Venganza Errante":
                imageBarco = new ImageIcon(filePath + File.separator + "src" + File.separator + "images" + File.separator + "barcoPirata.png");
                kraken =  new ImageIcon(filePath + File.separator + "src" + File.separator + "images" + File.separator + "barcoPkraken.png");
                tornado = new ImageIcon(filePath + File.separator + "src" + File.separator + "images" + File.separator + "barcoPtornado.png");
                tormenta = new ImageIcon(filePath + File.separator + "src" + File.separator + "images" + File.separator + "barcoPtormenta.png");
                
                break;
            case "Invencible": 
                imageBarco = new ImageIcon(filePath + File.separator + "src" + File.separator + "images" + File.separator + "interceptorBarco.png");
                kraken =  new ImageIcon(filePath + File.separator + "src" + File.separator + "images" + File.separator + "intercepkraken.png");
                tornado = new ImageIcon(filePath + File.separator + "src" + File.separator + "images" + File.separator + "interceptornado.png");
                tormenta = new ImageIcon(filePath + File.separator + "src" + File.separator + "images" + File.separator + "interceptormenta.png");
                break;
            case "Interceptor": 
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
       panel.setLayout(null);
       panel.add(labelBarco);
       labelBarco.setBounds(x, y, 150, 136);
       labelBarco.setVisible(true);
       System.out.println("Aparecer X: "+x+" Y: "+y);

   }
   public void aparecerPelea(int x, int y){
       this.add(labelPelea);
        labelPelea.setIcon(new ImageIcon(filePath + File.separator + "src" + File.separator + "images" + File.separator + "pelea.png"));
        labelPelea.setBounds(x, y, 150, 136);
        labelPelea.setVisible(true);
       
   }
   
   public void ocultarPelea(){
       labelPelea.setVisible(false);
   }
   
   public void OcultarBarco(){
       labelBarco.setVisible(false);
       panel.remove(labelBarco);
   }
   
   public void MoverBarco(int posx1, int posy1){
//        int movX, movY;
//        int posx = this.labelBarco.getX();
//        int posy = this.labelBarco.getY();
        //System.out.println("Label origen x: "+posx+" y:"+posy);
        /*
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
            barcoAnimated.jLabelXRight(posx, posx1, 6, 1, labelBarco);   // Se mueve hacia la derecha
        }else
        if(movX == 2){
            barcoAnimated.jLabelXLeft(posx, posx1, 6, 1, labelBarco);    // Se mueve hacia la izquierda
        }    

        // MOVIMIENTO EN EL EJE Y
        if(movY == 1){
            barcoAnimated.jLabelYDown(posy, posy1, 6, 1, labelBarco);    // Se mueve hacia la abajo
        }else
        if(movY == 2){
            barcoAnimated.jLabelYUp(posy, posy1, 6, 1, labelBarco);      // Se mueve hacia la arriba
        }
        
        int sw=0;
        while (sw == 0){
            if((labelBarco.getX() == posx1) && (labelBarco.getY() == posy1)){
                sw = 1;
            } 
            //System.out.print("");
            //System.out.print("x: "+labelBarco.getX()+" y:"+labelBarco.getY());
        }*/
        
        int x1,y1,absX,absY,dirX=0,dirY=0;

        x1 = labelBarco.getX()-posx1;
        y1 = labelBarco.getY()-posy1;
        
        System.out.println("Desde X: "+labelBarco.getX()+" Y: "+labelBarco.getY());
        System.out.println("Hasta X: "+posx1+" Y: "+posy1);
        
        absX = Math.abs(x1);
        absY = Math.abs(y1);
        
        if(x1!=0){
            dirX = (x1/absX)*-1;
        }
        if(y1!=0){
            dirY = (y1/absY)*-1;
        }

        
        //System.out.println("X: "+dirX+" Y: "+dirY);
        
        System.out.println("EMPECE A MOVERME");
        
        while(absX!=0 || absY!=0){
            if(absX!=0){
                absX--;
            }else{
                dirX = 0;
            }
            if(absY!=0){
                absY--;
            }else{
                dirY = 0;
            }
            labelBarco.setLocation(labelBarco.getX()+dirX, labelBarco.getY()+dirY);
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                Logger.getLogger(BarcoGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        

        System.out.println("TERMINE DE MOVERME");
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
