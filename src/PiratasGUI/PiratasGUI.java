/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PiratasGUI;

import PiratasLogic.Barco;
import PiratasLogic.Maquina;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author Personal
 */
public class PiratasGUI extends javax.swing.JFrame {
    /**
     * Creates new form PiratasGUI
     */
    private JPanel PanelPrincipal;
    private Maquina maquina;
    private String destino, destino2;
    private VentanaRutas rutas;
    private String filePath = new File("").getAbsolutePath();
    
    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getDestino2() {
        return destino2;
    }

    public void setDestino2(String destino2) {
        this.destino2 = destino2;
    }
//    private JLabel labelBarco;
    public PiratasGUI(Maquina maquina){
        initComponents();
        /*Ventana*/
        String filePath = new File("").getAbsolutePath();
        System.out.println(filePath + "\\src\\images\\maquina2.png");
        //rutas = new VentanaRutas(this, false);
         
        /*Segun la maquina hay que cambiar el fondo*/
        /*Segun la maquina. Si es 1, es el pirata, si es 2, son los dos barcos reales*/
         
        if (maquina.getId() == 1){
            PanelPrincipal = new FondoPanel("/images/maquina1.png");
//            rutas.getjLabelBarco().setText("Venganza Errante");
//            rutas.getjComboBoxDestino2().setVisible(false);
        }else if (maquina.getId() == 2){
            PanelPrincipal = new FondoPanel("/images/maquina2.png");
//            rutas.getjLabelBarco().setText("El Interceptor");
//            rutas.getjLabelBarco2().setText("El Invencible");
        }else if (maquina.getId() == 3){
            PanelPrincipal = new FondoPanel("/images/maquina3.png");
        }else if (maquina.getId() == 4){
            PanelPrincipal = new FondoPanel("/images/maquina4.png");
        }
        
        PanelPrincipal.setBounds(170, 0, 1196, 768);
        
        add(PanelPrincipal);
        
        /*Estadisticas en nulo. Imagenes con las X*/
        piratax.setIcon(new ImageIcon(filePath + File.separator + "src" + File.separator + "images" + File.separator + "pirataX.png"));
        piratax.setVisible(true);
        reinax.setIcon(new ImageIcon(filePath + File.separator + "src" + File.separator + "images" + File.separator + "interceptorX.png"));
        reinax.setVisible(true);
        reinax2.setIcon(new ImageIcon(filePath + File.separator + "src" + File.separator + "images" + File.separator + "invencibleX.png"));
        reinax2.setVisible(true);
        
        /*Valores en cero*/
        this.barcoPirata_Tripulacion.setText("0");
        this.barcoPirata_Municiones.setText("0");
        this.barcoPirata_Comida.setText("0");
        this.barcoPirata_Cofre.setText("0");
        this.barcoInterceptor_Tripulacion.setText("0");
        this.barcoInterceptor_Municiones.setText("0");
        this.barcoInterceptor_Comida.setText("0");
        this.barcoInterceptor_Cofre.setText("0");
        this.barcoInvencible_Tripulacion.setText("0");
        this.barcoInvencible_Municiones.setText("0");
        this.barcoInvencible_Comida.setText("0");
        this.barcoInvencible_Cofre.setText("0");
        PanelPrincipal.setVisible(true);
        this.setBounds(0, 0, 1366, 768);
        
     //  setBackgroundMusic("/images/musica.wav");
        

        //BarcoMovement = new Barco(PanelPrincipal, "Venganza Errante");
    }
    
public void setBackgroundMusic(){
        URL url = PiratasGUI.class.getResource(filePath + File.separator + "src" + File.separator + "images" + File.separator + "musica.wav");
            AudioClip clip = Applet.newAudioClip(url);
            AudioClip clip2 = Applet.newAudioClip(url);
            clip.play(); 
           System.out.println("end");
 }
    
     public void setEstadoBarcoReset(Barco barco){
        switch(barco.getNombre()){
            case "Venganza Errante":
                piratax.setVisible(true);
                this.barcoPirata_Tripulacion.setText("0"+ " / "+ barco.getMaxTripulacion());
                this.barcoPirata_Municiones.setText("0"+ " / "+ barco.getMaxMuniciones());
                this.barcoPirata_Comida.setText("0"+ " / "+ barco.getMaxComida());
                this.barcoPirata_Cofre.setText("0"+" / "+  barco.getCofre().getCapacidad());
                break;
                
            case "Interceptor":   
                reinax.setVisible(true);
                this.barcoInterceptor_Tripulacion.setText("0"+ " / "+ barco.getMaxTripulacion());
                this.barcoInterceptor_Municiones.setText("0"+ " / "+ barco.getMaxMuniciones());
                this.barcoInterceptor_Comida.setText("0"+ " / "+ barco.getMaxComida());
                this.barcoInterceptor_Cofre.setText("0"+" / "+  barco.getCofre().getCapacidad());
                break;
                
            case "Invencible":   
                reinax2.setVisible(true);
                this.barcoInvencible_Tripulacion.setText("0"+ " / "+ barco.getMaxTripulacion());
                this.barcoInvencible_Municiones.setText("0"+ " / "+ barco.getMaxMuniciones());
                this.barcoInvencible_Comida.setText("0"+ " / "+ barco.getMaxComida());
                this.barcoInvencible_Cofre.setText("0"+ " / "+  barco.getCofre().getCapacidad());
                break;
            default:
                reinax2.setVisible(true);
                this.barcoInvencible_Tripulacion.setText("0"+ " / "+ barco.getMaxTripulacion());
                this.barcoInvencible_Municiones.setText("0"+ " / "+ barco.getMaxMuniciones());
                this.barcoInvencible_Comida.setText("0"+ " / "+ barco.getMaxComida());
                this.barcoInvencible_Cofre.setText("0"+" / "+  barco.getCofre().getCapacidad());
                reinax.setVisible(true);
                this.barcoInterceptor_Tripulacion.setText("0"+ " / "+ barco.getMaxTripulacion());
                this.barcoInterceptor_Municiones.setText("0"+ " / "+ barco.getMaxMuniciones());
                this.barcoInterceptor_Comida.setText("0"+ " / "+ barco.getMaxComida());
                this.barcoInterceptor_Cofre.setText("0"+" / "+  barco.getCofre().getCapacidad());
                piratax.setVisible(true);
                this.barcoPirata_Tripulacion.setText("0"+ " / "+ barco.getMaxTripulacion());
                this.barcoPirata_Municiones.setText("0"+ " / "+ barco.getMaxMuniciones());
                this.barcoPirata_Comida.setText("0"+ " / "+ barco.getMaxComida());
                this.barcoPirata_Cofre.setText("0"+" / "+  barco.getCofre().getCapacidad());
                break;
        }
        this.repaint();
        
    }
    public void setEstadoBarcos(Barco barco){
        switch(barco.getNombre()){
            case "Venganza Errante":
                piratax.setVisible(false);
                this.barcoPirata_Tripulacion.setText(barco.getTripulacion() + " / "+ barco.getMaxTripulacion());
                this.barcoPirata_Municiones.setText(barco.getMuniciones() + " / "+ barco.getMaxMuniciones());
                this.barcoPirata_Comida.setText(barco.getComida() + " / "+ barco.getMaxComida());
                this.barcoPirata_Cofre.setText(barco.getCofre().getCapacidadActual() + " / "+ barco.getCofre().getCapacidad());
                break;
                
            case "Interceptor":   
                reinax.setVisible(false);
                this.barcoInterceptor_Tripulacion.setText(barco.getTripulacion() + " / "+ barco.getMaxTripulacion());
                this.barcoInterceptor_Municiones.setText(barco.getMuniciones() + " / "+ barco.getMaxMuniciones());
                this.barcoInterceptor_Comida.setText(barco.getComida() + " / "+ barco.getMaxComida());
                this.barcoInterceptor_Cofre.setText(barco.getCofre().getCapacidadActual() + " / "+ barco.getCofre().getCapacidad());
                break;
                
            case "Invencible":   
                reinax2.setVisible(false);
                this.barcoInvencible_Tripulacion.setText(barco.getTripulacion() + " / "+ barco.getMaxTripulacion());
                this.barcoInvencible_Municiones.setText(barco.getMuniciones() + " / "+ barco.getMaxMuniciones());
                this.barcoInvencible_Comida.setText(barco.getComida() + " / "+ barco.getMaxComida());
                this.barcoInvencible_Cofre.setText(barco.getCofre().getCapacidadActual() + " / "+ barco.getCofre().getCapacidad());
                break;    
        }
        this.repaint();
        
    }

    public JPanel getPanelPrincipal() {
        return PanelPrincipal;
    }
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        barcoPiratainf = new FondoPanel("/images/pirata.png");
        piratax = new javax.swing.JLabel();
        panelBarcoEstadistica = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        EstadisticaBarco = new javax.swing.JPanel();
        barcoPirata_Tripulacion = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        barcoPirata_Municiones = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        barcoPirata_Comida = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        barcoPirata_Cofre = new javax.swing.JLabel();
        barcoReinainf2 = new FondoPanel("/images/invencible.png");
        reinax2 = new javax.swing.JLabel();
        panelBarcoEstadistica2 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        EstadisticaBarco2 = new javax.swing.JPanel();
        barcoInvencible_Tripulacion = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        barcoInvencible_Municiones = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        barcoInvencible_Comida = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        barcoInvencible_Cofre = new javax.swing.JLabel();
        barcoReinainf = new FondoPanel("/images/interceptor.png");
        reinax = new javax.swing.JLabel();
        panelBarcoEstadistica1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        EstadisticaBarco1 = new javax.swing.JPanel();
        barcoInterceptor_Tripulacion = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        barcoInterceptor_Municiones = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        barcoInterceptor_Comida = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        barcoInterceptor_Cofre = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        iniciar = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 153, 255));
        setMaximumSize(new java.awt.Dimension(1366, 768));
        setMinimumSize(new java.awt.Dimension(1366, 768));
        setPreferredSize(new java.awt.Dimension(1366, 768));
        getContentPane().setLayout(null);

        barcoPiratainf.setMaximumSize(new java.awt.Dimension(158, 240));
        barcoPiratainf.setMinimumSize(new java.awt.Dimension(158, 240));
        barcoPiratainf.setPreferredSize(new java.awt.Dimension(158, 240));

        piratax.setAlignmentX(0.5F);

        panelBarcoEstadistica.setBackground(new Color(0,0,0,0));
        panelBarcoEstadistica.setMaximumSize(new java.awt.Dimension(90, 240));
        panelBarcoEstadistica.setMinimumSize(new java.awt.Dimension(90, 240));
        panelBarcoEstadistica.setPreferredSize(new java.awt.Dimension(90, 240));

        jLabel1.setFont(new java.awt.Font("Viner Hand ITC", 0, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("Tripulación:");

        jLabel2.setFont(new java.awt.Font("Viner Hand ITC", 0, 14)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Municiones:");

        jLabel3.setFont(new java.awt.Font("Viner Hand ITC", 0, 14)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Comida:");

        jLabel4.setFont(new java.awt.Font("Viner Hand ITC", 0, 14)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Cofre:");

        javax.swing.GroupLayout panelBarcoEstadisticaLayout = new javax.swing.GroupLayout(panelBarcoEstadistica);
        panelBarcoEstadistica.setLayout(panelBarcoEstadisticaLayout);
        panelBarcoEstadisticaLayout.setHorizontalGroup(
            panelBarcoEstadisticaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBarcoEstadisticaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBarcoEstadisticaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelBarcoEstadisticaLayout.setVerticalGroup(
            panelBarcoEstadisticaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBarcoEstadisticaLayout.createSequentialGroup()
                .addContainerGap(37, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );

        EstadisticaBarco.setBackground(new Color(0,0,0,0));
        EstadisticaBarco.setAlignmentX(0.0F);
        EstadisticaBarco.setAlignmentY(0.0F);
        EstadisticaBarco.setMaximumSize(new java.awt.Dimension(68, 240));
        EstadisticaBarco.setMinimumSize(new java.awt.Dimension(68, 240));
        EstadisticaBarco.setPreferredSize(new java.awt.Dimension(68, 240));

        barcoPirata_Tripulacion.setFont(new java.awt.Font("Viner Hand ITC", 0, 14)); // NOI18N
        barcoPirata_Tripulacion.setText("500");

        jLabel21.setFont(new java.awt.Font("Viner Hand ITC", 0, 14)); // NOI18N
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel21.setText("Hombres");

        jLabel24.setFont(new java.awt.Font("Viner Hand ITC", 0, 14)); // NOI18N
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel24.setText("Unidades");

        barcoPirata_Municiones.setFont(new java.awt.Font("Viner Hand ITC", 0, 14)); // NOI18N
        barcoPirata_Municiones.setText("500");

        jLabel26.setFont(new java.awt.Font("Viner Hand ITC", 0, 14)); // NOI18N
        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel26.setText("Libras");

        barcoPirata_Comida.setFont(new java.awt.Font("Viner Hand ITC", 0, 14)); // NOI18N
        barcoPirata_Comida.setText("500");

        jLabel28.setFont(new java.awt.Font("Viner Hand ITC", 0, 14)); // NOI18N
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel28.setText("Libras");

        barcoPirata_Cofre.setFont(new java.awt.Font("Viner Hand ITC", 0, 14)); // NOI18N
        barcoPirata_Cofre.setText("500");

        javax.swing.GroupLayout EstadisticaBarcoLayout = new javax.swing.GroupLayout(EstadisticaBarco);
        EstadisticaBarco.setLayout(EstadisticaBarcoLayout);
        EstadisticaBarcoLayout.setHorizontalGroup(
            EstadisticaBarcoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(EstadisticaBarcoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(EstadisticaBarcoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(barcoPirata_Tripulacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(barcoPirata_Municiones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(barcoPirata_Comida, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(barcoPirata_Cofre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        EstadisticaBarcoLayout.setVerticalGroup(
            EstadisticaBarcoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(EstadisticaBarcoLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(barcoPirata_Tripulacion, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(barcoPirata_Municiones, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(barcoPirata_Comida, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19)
                .addComponent(barcoPirata_Cofre, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout barcoPiratainfLayout = new javax.swing.GroupLayout(barcoPiratainf);
        barcoPiratainf.setLayout(barcoPiratainfLayout);
        barcoPiratainfLayout.setHorizontalGroup(
            barcoPiratainfLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(barcoPiratainfLayout.createSequentialGroup()
                .addComponent(panelBarcoEstadistica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(EstadisticaBarco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(barcoPiratainfLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(barcoPiratainfLayout.createSequentialGroup()
                    .addComponent(piratax, javax.swing.GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        barcoPiratainfLayout.setVerticalGroup(
            barcoPiratainfLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, barcoPiratainfLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(barcoPiratainfLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelBarcoEstadistica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(EstadisticaBarco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(barcoPiratainfLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(piratax, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE))
        );

        getContentPane().add(barcoPiratainf);
        barcoPiratainf.setBounds(10, 4, 158, 240);

        barcoReinainf2.setPreferredSize(new java.awt.Dimension(158, 240));

        reinax2.setAlignmentX(0.5F);

        panelBarcoEstadistica2.setBackground(new Color(0,0,0,0));
        panelBarcoEstadistica2.setMaximumSize(new java.awt.Dimension(90, 240));
        panelBarcoEstadistica2.setMinimumSize(new java.awt.Dimension(90, 240));

        jLabel9.setFont(new java.awt.Font("Viner Hand ITC", 0, 14)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel9.setText("Tripulación:");

        jLabel10.setFont(new java.awt.Font("Viner Hand ITC", 0, 14)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel10.setText("Municiones:");

        jLabel11.setFont(new java.awt.Font("Viner Hand ITC", 0, 14)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel11.setText("Comida:");

        jLabel12.setFont(new java.awt.Font("Viner Hand ITC", 0, 14)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel12.setText("Cofre:");

        javax.swing.GroupLayout panelBarcoEstadistica2Layout = new javax.swing.GroupLayout(panelBarcoEstadistica2);
        panelBarcoEstadistica2.setLayout(panelBarcoEstadistica2Layout);
        panelBarcoEstadistica2Layout.setHorizontalGroup(
            panelBarcoEstadistica2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBarcoEstadistica2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBarcoEstadistica2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );
        panelBarcoEstadistica2Layout.setVerticalGroup(
            panelBarcoEstadistica2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBarcoEstadistica2Layout.createSequentialGroup()
                .addContainerGap(45, Short.MAX_VALUE)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );

        EstadisticaBarco2.setBackground(new Color(0,0,0,0));
        EstadisticaBarco2.setAlignmentX(0.0F);
        EstadisticaBarco2.setAlignmentY(0.0F);
        EstadisticaBarco2.setMaximumSize(new java.awt.Dimension(68, 240));
        EstadisticaBarco2.setMinimumSize(new java.awt.Dimension(68, 240));

        barcoInvencible_Tripulacion.setFont(new java.awt.Font("Viner Hand ITC", 0, 14)); // NOI18N
        barcoInvencible_Tripulacion.setText("500");

        jLabel37.setFont(new java.awt.Font("Viner Hand ITC", 0, 14)); // NOI18N
        jLabel37.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel37.setText("Hombres");

        jLabel38.setFont(new java.awt.Font("Viner Hand ITC", 0, 14)); // NOI18N
        jLabel38.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel38.setText("Unidades");

        barcoInvencible_Municiones.setFont(new java.awt.Font("Viner Hand ITC", 0, 14)); // NOI18N
        barcoInvencible_Municiones.setText("500");

        jLabel40.setFont(new java.awt.Font("Viner Hand ITC", 0, 14)); // NOI18N
        jLabel40.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel40.setText("Libras");

        barcoInvencible_Comida.setFont(new java.awt.Font("Viner Hand ITC", 0, 14)); // NOI18N
        barcoInvencible_Comida.setText("500");

        jLabel42.setFont(new java.awt.Font("Viner Hand ITC", 0, 14)); // NOI18N
        jLabel42.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel42.setText("Libras");

        barcoInvencible_Cofre.setFont(new java.awt.Font("Viner Hand ITC", 0, 14)); // NOI18N
        barcoInvencible_Cofre.setText("500");

        javax.swing.GroupLayout EstadisticaBarco2Layout = new javax.swing.GroupLayout(EstadisticaBarco2);
        EstadisticaBarco2.setLayout(EstadisticaBarco2Layout);
        EstadisticaBarco2Layout.setHorizontalGroup(
            EstadisticaBarco2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(EstadisticaBarco2Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(EstadisticaBarco2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(barcoInvencible_Tripulacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel37, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(barcoInvencible_Municiones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel38, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(barcoInvencible_Comida, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel40, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(barcoInvencible_Cofre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel42, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        EstadisticaBarco2Layout.setVerticalGroup(
            EstadisticaBarco2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(EstadisticaBarco2Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(barcoInvencible_Tripulacion, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(barcoInvencible_Municiones, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(barcoInvencible_Comida, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19)
                .addComponent(barcoInvencible_Cofre, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout barcoReinainf2Layout = new javax.swing.GroupLayout(barcoReinainf2);
        barcoReinainf2.setLayout(barcoReinainf2Layout);
        barcoReinainf2Layout.setHorizontalGroup(
            barcoReinainf2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, barcoReinainf2Layout.createSequentialGroup()
                .addComponent(panelBarcoEstadistica2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(EstadisticaBarco2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51))
            .addGroup(barcoReinainf2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(barcoReinainf2Layout.createSequentialGroup()
                    .addComponent(reinax2, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 58, Short.MAX_VALUE)))
        );
        barcoReinainf2Layout.setVerticalGroup(
            barcoReinainf2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(barcoReinainf2Layout.createSequentialGroup()
                .addGroup(barcoReinainf2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelBarcoEstadistica2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(EstadisticaBarco2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(barcoReinainf2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(reinax2, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE))
        );

        getContentPane().add(barcoReinainf2);
        barcoReinainf2.setBounds(10, 490, 158, 240);

        barcoReinainf.setPreferredSize(new java.awt.Dimension(158, 240));

        reinax.setAlignmentX(0.5F);
        reinax.setPreferredSize(new java.awt.Dimension(158, 240));
        reinax.setRequestFocusEnabled(false);

        panelBarcoEstadistica1.setBackground(new Color(0,0,0,0));
        panelBarcoEstadistica1.setMaximumSize(new java.awt.Dimension(90, 240));
        panelBarcoEstadistica1.setMinimumSize(new java.awt.Dimension(90, 240));

        jLabel5.setFont(new java.awt.Font("Viner Hand ITC", 0, 14)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Tripulación:");

        jLabel6.setFont(new java.awt.Font("Viner Hand ITC", 0, 14)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("Municiones:");

        jLabel7.setFont(new java.awt.Font("Viner Hand ITC", 0, 14)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("Comida:");

        jLabel8.setFont(new java.awt.Font("Viner Hand ITC", 0, 14)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText("Cofre:");

        javax.swing.GroupLayout panelBarcoEstadistica1Layout = new javax.swing.GroupLayout(panelBarcoEstadistica1);
        panelBarcoEstadistica1.setLayout(panelBarcoEstadistica1Layout);
        panelBarcoEstadistica1Layout.setHorizontalGroup(
            panelBarcoEstadistica1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBarcoEstadistica1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBarcoEstadistica1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );
        panelBarcoEstadistica1Layout.setVerticalGroup(
            panelBarcoEstadistica1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBarcoEstadistica1Layout.createSequentialGroup()
                .addContainerGap(45, Short.MAX_VALUE)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );

        EstadisticaBarco1.setBackground(new Color(0,0,0,0));
        EstadisticaBarco1.setAlignmentX(0.0F);
        EstadisticaBarco1.setAlignmentY(0.0F);
        EstadisticaBarco1.setMaximumSize(new java.awt.Dimension(68, 240));
        EstadisticaBarco1.setMinimumSize(new java.awt.Dimension(68, 240));
        EstadisticaBarco1.setPreferredSize(new java.awt.Dimension(68, 240));

        barcoInterceptor_Tripulacion.setFont(new java.awt.Font("Viner Hand ITC", 0, 14)); // NOI18N
        barcoInterceptor_Tripulacion.setText("500");

        jLabel23.setFont(new java.awt.Font("Viner Hand ITC", 0, 14)); // NOI18N
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel23.setText("Hombres");

        jLabel30.setFont(new java.awt.Font("Viner Hand ITC", 0, 14)); // NOI18N
        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel30.setText("Unidades");

        barcoInterceptor_Municiones.setFont(new java.awt.Font("Viner Hand ITC", 0, 14)); // NOI18N
        barcoInterceptor_Municiones.setText("500");

        jLabel32.setFont(new java.awt.Font("Viner Hand ITC", 0, 14)); // NOI18N
        jLabel32.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel32.setText("Libras");

        barcoInterceptor_Comida.setFont(new java.awt.Font("Viner Hand ITC", 0, 14)); // NOI18N
        barcoInterceptor_Comida.setText("500");

        jLabel34.setFont(new java.awt.Font("Viner Hand ITC", 0, 14)); // NOI18N
        jLabel34.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel34.setText("Libras");

        barcoInterceptor_Cofre.setFont(new java.awt.Font("Viner Hand ITC", 0, 14)); // NOI18N
        barcoInterceptor_Cofre.setText("500");

        javax.swing.GroupLayout EstadisticaBarco1Layout = new javax.swing.GroupLayout(EstadisticaBarco1);
        EstadisticaBarco1.setLayout(EstadisticaBarco1Layout);
        EstadisticaBarco1Layout.setHorizontalGroup(
            EstadisticaBarco1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(EstadisticaBarco1Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(EstadisticaBarco1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(barcoInterceptor_Tripulacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(barcoInterceptor_Municiones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(barcoInterceptor_Comida, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(barcoInterceptor_Cofre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        EstadisticaBarco1Layout.setVerticalGroup(
            EstadisticaBarco1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(EstadisticaBarco1Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(barcoInterceptor_Tripulacion, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(barcoInterceptor_Municiones, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(barcoInterceptor_Comida, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19)
                .addComponent(barcoInterceptor_Cofre, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout barcoReinainfLayout = new javax.swing.GroupLayout(barcoReinainf);
        barcoReinainf.setLayout(barcoReinainfLayout);
        barcoReinainfLayout.setHorizontalGroup(
            barcoReinainfLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(barcoReinainfLayout.createSequentialGroup()
                .addComponent(panelBarcoEstadistica1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(EstadisticaBarco1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(barcoReinainfLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(barcoReinainfLayout.createSequentialGroup()
                    .addComponent(reinax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 17, Short.MAX_VALUE)))
        );
        barcoReinainfLayout.setVerticalGroup(
            barcoReinainfLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(barcoReinainfLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(panelBarcoEstadistica1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(barcoReinainfLayout.createSequentialGroup()
                .addComponent(EstadisticaBarco1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(barcoReinainfLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(reinax, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(barcoReinainf);
        barcoReinainf.setBounds(10, 247, 158, 240);

        iniciar.setText("Iniciar");
        iniciar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                iniciarMouseClicked(evt);
            }
        });
        jMenuBar1.add(iniciar);
        iniciar.getAccessibleContext().setAccessibleDescription("");

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void iniciarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_iniciarMouseClicked
        // TODO add your handling code here:
        //this.BarcoMovement.AparecerBarco(10, 100);
//        rutas.setVisible(true);
//        /*Cuando la ventana se cierra*/
//        rutas.addWindowListener(new java.awt.event.WindowAdapter() {
//            @Override
//            public void windowClosed(java.awt.event.WindowEvent e) {
//                System.out.print(destino);
//                /*Ya obtienes el destino, si es maquina 2 se utilizara el destino2 y destino)*/
////                   BarcoMovement.CrearHilo(0,324,625,324);   //Revisar
////                   BarcoMovement.movimiento.start();
//            }
//        });
    }//GEN-LAST:event_iniciarMouseClicked
/*Lista de String de sitios, leer cada una y setear*/
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PiratasGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PiratasGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PiratasGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PiratasGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel EstadisticaBarco;
    private javax.swing.JPanel EstadisticaBarco1;
    private javax.swing.JPanel EstadisticaBarco2;
    private javax.swing.JLabel barcoInterceptor_Cofre;
    private javax.swing.JLabel barcoInterceptor_Comida;
    private javax.swing.JLabel barcoInterceptor_Municiones;
    private javax.swing.JLabel barcoInterceptor_Tripulacion;
    private javax.swing.JLabel barcoInvencible_Cofre;
    private javax.swing.JLabel barcoInvencible_Comida;
    private javax.swing.JLabel barcoInvencible_Municiones;
    private javax.swing.JLabel barcoInvencible_Tripulacion;
    private javax.swing.JLabel barcoPirata_Cofre;
    private javax.swing.JLabel barcoPirata_Comida;
    private javax.swing.JLabel barcoPirata_Municiones;
    private javax.swing.JLabel barcoPirata_Tripulacion;
    private javax.swing.JPanel barcoPiratainf;
    private javax.swing.JPanel barcoReinainf;
    private javax.swing.JPanel barcoReinainf2;
    private javax.swing.JMenu iniciar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel panelBarcoEstadistica;
    private javax.swing.JPanel panelBarcoEstadistica1;
    private javax.swing.JPanel panelBarcoEstadistica2;
    private javax.swing.JLabel piratax;
    private javax.swing.JLabel reinax;
    private javax.swing.JLabel reinax2;
    // End of variables declaration//GEN-END:variables
}
