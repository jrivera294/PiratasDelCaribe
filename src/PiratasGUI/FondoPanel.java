/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package PiratasGUI;

import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author Victoria Fuenmayor
 */
public class FondoPanel extends JPanel{
    private Image fondoFrame;
    
     /**
      * Constructor que recibe el archivo de la imagen y crea el fondo.
      * Error en caso de no encontrar el archivo.
      * @param archivo 
      */
    public FondoPanel(String archivo) {
        this.setLayout(new FlowLayout());
        try {
            fondoFrame = new ImageIcon(getClass().getResource(archivo)).getImage();
        } catch (Exception e) {
            System.err.println("Error en el nombre del archivo.");
        }
    }

    @Override
    public void paintComponent(Graphics grafico) {
        super.paintComponent(grafico);
        grafico.drawImage(fondoFrame, 0, 0, null);
    }    
    
}
