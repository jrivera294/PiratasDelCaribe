/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PiratasLogic;

import PiratasGUI.BarcoGUI;
import PiratasGUI.PiratasGUI;
import PiratasGUI.VentanaRutas;
import static java.lang.Integer.parseInt;
import java.rmi.*;
import java.rmi.server.*;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;


/**
 *
 * @author Jose Gabriel
 */
public class RemoteClass extends UnicastRemoteObject implements RMIInterface{
    private Semaphore mutex = new Semaphore(1);
    private Maquina maquina;
    private PiratasGUI graphicInterface;
    
    public RemoteClass(Maquina maquina, PiratasGUI graphicInterface) throws RemoteException{
        super();
        this.maquina = maquina;
        this.graphicInterface = graphicInterface;
    }
    
    @Override
    public boolean desembarcar(Barco barco, String nombreSitio, int idMaquina) throws RemoteException{

        Hilo hilo = new Hilo(barco,nombreSitio,this.graphicInterface,this.maquina, idMaquina);
        hilo.start();

        System.out.println("Objeto remoto enviado exitosamente");
        System.out.println("Nombre barco: "+barco.getNombre() +" -- Va: "+nombreSitio);

        return true;
    }
    
    public class Hilo extends Thread {
        private Barco barco;
        private String nombreSitio;
        private JPanel panelPrincipal;
        private PiratasGUI graphicInterface;
        private BarcoGUI barcoGUI;
        private Maquina maquina;
        private int idOrigen;

        public Hilo(Barco barco, String nombreSitio, PiratasGUI graphicInterface, Maquina maquina, int idOrigen) {
            this.barco = barco;
            this.nombreSitio = nombreSitio;
            this.graphicInterface = graphicInterface;
            this.panelPrincipal = graphicInterface.getPanelPrincipal();
            this.maquina = maquina;
            this.idOrigen = idOrigen;
            barcoGUI = new BarcoGUI(panelPrincipal,barco.getNombre());
        }

        @Override
        public void run(){
            String dato[];
            dato = nombreSitio.split("-");
            Boolean flag = false; 
            int xOrigen, yOrigen, xSalida,ySalida;
            boolean volverOrigen = false;
            
            barco.setBarcoGUI(barcoGUI);
            barco.setGraphicInterface(graphicInterface);
            this.graphicInterface.setEstadoBarcos(barco.getNombre(), barco.getTripulacion(), barco.getMuniciones(), barco.getComida(), barco.getCofre().getCapacidadActual());
            
            //Obtener el id de la maquina de donde viene el barco
            /*if(barco.getCofre().getMapa() == null){
                idOrigen = barco.getRutaOrigen().split("-")[0];
            }else{
                idOrigen = barco.getCofre().getMapa().getRuta().get(barco.getCofre()
                        .getMapa().getSitioActual()-1).split("-")[0];
            }*/
           
            
            System.out.println("idOrigen: "+idOrigen);
            
            if(idOrigen == maquina.getId()){
                System.out.println("Barco aparece en el sitio de origen.");
                for(Sitio sitio : maquina.getSitio()){
                    if(sitio.getNombreSitio().equals(barco.getRutaOrigen().split("-")[1])){
                        xOrigen = sitio.getPosX();
                        yOrigen = sitio.getPosY();
                        System.out.println("xOrigen: "+xOrigen+ " yOrigen: "+yOrigen);
                        barcoGUI.AparecerBarco(xOrigen, yOrigen);
                        
                        //Preguntar por sitio inicial
                        //dato = el valor que me pase la ventana, luego de hacerle split("-")
                        VentanaRutas ventana = new VentanaRutas(null,true,maquina.getSitioRemoto());
                        ventana.setVisible(true);
                        
                        ventana.addWindowListener(null);
                        System.out.println("Ruta: "+ventana.getjComboBoxDestino().getSelectedItem().toString());
                        dato = ventana.getjComboBoxDestino().getSelectedItem().toString().split("-");
                        break;
                    }
                }
            }else{
                System.out.println("Barco aparece en un punto de entrada");
                // Obtener el punto donde debe aparecer el barco
                xOrigen = parseInt(maquina.getPuntoSalida(idOrigen+"").split("-")[1].split(",")[0]);
                yOrigen = parseInt(maquina.getPuntoSalida(idOrigen+"").split("-")[1].split(",")[1]);

                barcoGUI.AparecerBarco(xOrigen, yOrigen);

                
                /*
                //Determinar si viene a la base
                if (nombreSitio.equals(barco.getRutaOrigen()) == true){
                    for(Sitio sitio : maquina.getSitio()){
                        if(sitio.getNombreSitio().equals(nombreSitio)){
                            barcoGUI.MoverBarco(sitio.getPosX(), sitio.getPosY());
                            break;
                        }
                    }
                    //Esperar que el barco termine de moverse

                    if (barco.getCofre().getCorazonPrincesa() != 0){

                        System.out.println("GANASTE: Finalizo el juego.");

                        //avisar a las demas maquinas que se termino el juego
                        return;
                    }else{
                        barco.reabastecer();
                        this.graphicInterface.setEstadoBarcos(barco.getNombre(), barco.getTripulacion(), barco.getMuniciones(), barco.getComida(), barco.getCofre().getCapacidadActual());
                        try {
                            System.out.println("Barco: Reabasteciendo.");
                            Thread.sleep(5000); 
                        } catch (InterruptedException ex) {
                            Logger.getLogger(RemoteClass.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        dato = barco.getCofre().getMapa().getRuta().get(barco.getCofre().getMapa().getSitioActual()).split("-");
                    }   
                }*/
            }
            while(true){
                //Si son diferentes es Remoto, sino es Local
                if (parseInt(dato[0]) != maquina.getId()){
                    //Mover el barco al punto de salida
                    xSalida = parseInt(maquina.getPuntoSalida(dato[0]).split("-")[1].split(",")[0]);
                    ySalida = parseInt(maquina.getPuntoSalida(dato[0]).split("-")[1].split(",")[1]);
                    //System.out.println("Barco moviendose al punto de salida x:"+xSalida+" y:"+ySalida);
                    barcoGUI.MoverBarco(xSalida, ySalida);                    
                    
                    for (int i=0; i < maquina.getSitioRemoto().size(); i++){
                        String ip[];
                        ip = maquina.getIpRemota().get(i).split("-");
                        if (dato[0].equals(ip[0]) == true){
                            if (barco.llamadaRMI(ip[1], dato[0]+"-"+dato[1], maquina.getId()) == false){
                                System.out.println("Error LlamadaRMI: No se pudo enviar el Barco.");
                                System.out.println("Buscando siguiente sitio");
                                
                                try{
                                    barco.getCofre().getMapa().setSitioActual();
                                    dato = barco.getCofre().getMapa().getRuta().get(barco.getCofre()
                                            .getMapa().getSitioActual()).split("-");
                                }catch(Exception e){
                                    dato = barco.getRutaOrigen().split("-");
                                }
                                
                                flag = true;
                            }else{
                                System.out.println("Barco enviado exitosamente");
                                barcoGUI.OcultarBarco();
                                graphicInterface.setEstadoBarcoReset(barco.getNombre());
                                
                                return;
                            }
                            break;
                        }
                    }
                }
                if (flag == false){
                    // Mover al sitio de destino
                    for(Sitio sitio : maquina.getSitio()){
                        if(sitio.getNombreSitio().equals(dato[1])){
                            System.out.println("Barco moviendose a: "+sitio.getNombreSitio());
                            barcoGUI.MoverBarco(sitio.getPosX(), sitio.getPosY());
                            
                            //Reviso el tipo de barco, si es Pirata (1) o Naval (2)
                            if (barco.getTipo() == 1){
                                sitio.setBarcoPirata(barco);
                            }else if (barco.getTipo() == 2){
                                sitio.setBarcoNaval(barco);
                            }
                            
                            
                            break;
                        }
                    }      
                    
                    
                    //Determinar si se dirige al origen
                    if (dato[1].equals(barco.getRutaOrigen().split("-")[1]) == true){
                        System.out.println("Voy al origen");
                        /*for(Sitio sitio : maquina.getSitio()){
                            if(sitio.getNombreSitio().equals(nombreSitio)){
                                barcoGUI.MoverBarco(sitio.getPosX(), sitio.getPosY());
                                break;
                            }
                        }*/
                        //Esperar que el barco termine de moverse

                        if (barco.getCofre().getCorazonPrincesa() != 0){

                            System.out.println("GANASTE: Finalizo el juego.");

                            //avisar a las demas maquinas que se termino el juego
                            return;
                        }else{
                            barco.reabastecer();
                            this.graphicInterface.setEstadoBarcos(barco.getNombre(), barco.getTripulacion(), barco.getMuniciones(), barco.getComida(), barco.getCofre().getCapacidadActual());
                            try {
                                System.out.println("Barco: Reabasteciendo.");
                                Thread.sleep(5000); 
                            } catch (InterruptedException ex) {
                                Logger.getLogger(RemoteClass.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
                            try{
                                barco.getCofre().getMapa().setSitioActual();
                                dato = barco.getCofre().getMapa().getRuta().get(barco.getCofre()
                                        .getMapa().getSitioActual()).split("-");
                            }catch(Exception e){
                                //dato = barco.getRutaOrigen().split("-");
                                //System.out.println("Esperando por ventana de marianny");
                                VentanaRutas ventana = new VentanaRutas(null,true,maquina.getSitioRemoto());
                                ventana.setVisible(true);
                                System.out.println("Ruta: "+ventana.getjComboBoxDestino().getSelectedItem().toString());
                                dato = ventana.getjComboBoxDestino().getSelectedItem().toString().split("-");
                                //System.out.println("Ruta seleccionada: "+graphicInterface.getDestino());
                            }
                        }   
                    }else{
                        for (int i=0; i < maquina.getSitio().size(); i++){
                            if (dato[1].equals(maquina.getSitio().get(i).getNombreSitio()) == true){
                                //Retorna true: Cuando se queda sin recursos.
                                if (barco.descontarRecursos(maquina.getSitio().get(i)) == true){
                                    //Hacer llamada RMI al punto de origen de barco.
                                    for(String ipRemota: maquina.getIpRemota()){
                                        if(barco.getRutaOrigen().split("-")[0].equals(ipRemota.split("-")[0])){
                                            //this.barco.llamadaRMI(ipRemota.split("-")[1], barco.getRutaOrigen().split("-")[0], maquina.getId());
                                            //barcoGUI.OcultarBarco();
                                            volverOrigen = true;
                                        }
                                    }
                                //Recoger: True si encontro el corazon de la princesa    
                                }else if (barco.recoger(maquina.getSitio().get(i)) == true){
                                    //Hacer llamada RMI al punto de origen de barco.
                                    for(String ipRemota: maquina.getIpRemota()){
                                        if(barco.getRutaOrigen().split("-")[0].equals(ipRemota.split("-")[0])){
                                            //this.barco.llamadaRMI(ipRemota.split("-")[1], barco.getRutaOrigen().split("-")[0], maquina.getId());
                                            //barcoGUI.OcultarBarco();
                                            volverOrigen = true;
                                        }
                                    }
                                }
                                break;
                            }
                        }
                        if(volverOrigen){
                            dato = barco.getRutaOrigen().split("-");
                        }else{
                            try{
                                barco.getCofre().getMapa().setSitioActual();
                                dato = barco.getCofre().getMapa().getRuta().get(barco.getCofre()
                                        .getMapa().getSitioActual()).split("-");
                            }catch(Exception e){
                                dato = barco.getRutaOrigen().split("-");
                            }
                        }

                        System.out.println("Ruta: "+dato[1]);
                    }
                }
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(RemoteClass.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }         
    }
}
