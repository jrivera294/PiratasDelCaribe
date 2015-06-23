/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PiratasLogic;

import PiratasGUI.BarcoGUI;
import static java.lang.Integer.parseInt;
import java.rmi.*;
import java.rmi.server.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;


/**
 *
 * @author Jose Gabriel
 */
public class RemoteClass extends UnicastRemoteObject implements RMIInterface{
    private Maquina maquina;
    private final JPanel panelPrincipal;
    
    public RemoteClass(Maquina maquina, JPanel panelPrincipal) throws RemoteException{
        super();
        this.maquina = maquina;
        this.panelPrincipal = panelPrincipal;
    }
    
    @Override
    public boolean desembarcar(Barco barco, String nombreSitio, int idMaquina) throws RemoteException{

        Hilo hilo = new Hilo(barco,nombreSitio,this.panelPrincipal,this.maquina);
        hilo.start();

        System.out.println("Objeto remoto enviado exitosamente");
        System.out.println("Nombre barco: "+barco.getNombre() +" -- Va: "+nombreSitio);

        return true;
    }
    
    public class Hilo extends Thread {
        private Barco barco;
        private String nombreSitio;
        private JPanel panelPrincipal;
        private BarcoGUI barcoGUI;
        private Maquina maquina;

        public Hilo(Barco barco, String nombreSitio, JPanel panelPrincipal, Maquina maquina) {
            this.barco = barco;
            this.nombreSitio = nombreSitio;
            this.panelPrincipal = panelPrincipal;
            this.maquina = maquina;
            barcoGUI = new BarcoGUI(panelPrincipal,barco.getNombre());
        }

        @Override
        public void run(){
            String dato[];
            dato = nombreSitio.split("-");
            Boolean flag = false; 
            int xOrigen, yOrigen;
            String idOrigen;
            
            //Obtener el id de la maquina de donde viene el barco
            if(barco.getCofre().getMapa() == null){
                idOrigen = barco.getRutaOrigen().split("-")[0];
            }else{
                idOrigen = barco.getCofre().getMapa().getRuta().get(barco.getCofre()
                        .getMapa().getSitioActual()-1).split("-")[0];
            }
            
            // Obtener el punto donde debe aparecer el barco
            xOrigen = parseInt(maquina.getPuntoSalida(idOrigen).split("-")[0].split(",")[0]);
            yOrigen = parseInt(maquina.getPuntoSalida(idOrigen).split("-")[0].split(",")[1]);
            
            barcoGUI.AparecerBarco(xOrigen, yOrigen);
            
            //Determinar si viene a la base
            if (nombreSitio.equals(barco.getRutaOrigen()) == true){                
                //mover a origen
                //Esperar que el barco termine de moverse
                    
                if (barco.getCofre().getCorazonPrincesa() != 0){
                    
                    System.out.println("GANASTE: Finalizo el juego.");
                    
                    //avisar a las demas maquinas que se termino el juego
                    return;
                }else{
                    barco.reabastecer();
                    try {
                        System.out.println("Barco: Reabasteciendo.");
                        Thread.sleep(5000); 
                    } catch (InterruptedException ex) {
                        Logger.getLogger(RemoteClass.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    dato = barco.getCofre().getMapa().getRuta().get(barco.getCofre().getMapa().getSitioActual()).split("-");
                }   
            }
                
            while(true){
                //Si son diferentes es Remoto, sino es Local
                if (parseInt(dato[0]) != maquina.getId()){
                    //Mover al limite
                    for (int i=0; i < maquina.getSitioRemoto().size(); i++){
                        String ip[];
                        ip = maquina.getIpRemota().get(i).split("-");
                        if (dato[0].equals(ip[0]) == true){
                            if (barco.llamadaRMI(ip[1], dato[0]+"-"+dato[1], maquina.getId()) == false){
                                System.out.println("Error LlamadaRMI: No se pudo enviar el Barco.");
                                flag = true;
                            }
                            break;
                        }
                    }
                    
                    //Desaparecer barco
                    return;
                }
                //mover al sitio
                //Esperar retorno funcion. Mover Sitio
                if (flag == true){
                    for (int i=0; i < maquina.getSitio().size(); i++){
                        if (dato[1].equals(maquina.getSitio().get(i).getNombreSitio()) == true){
                            //Retorna true: Cuando se queda sin recursos.
                            if (barco.descontarRecursos(maquina.getSitio().get(i)) == true){
                                //Hacer llamada RMI al punto de origen de barco.
                                return;

                            //Recoger: True si encontro el corazon de la princesa    
                            }else if (barco.recoger(maquina.getSitio().get(i)) == true){
                                //Hacer llamada RMI al punto de origen de barco.
                                return;
                            }
                            break;
                        }
                    }
                }
                barco.getCofre().getMapa().setSitioActual();
                dato = barco.getCofre().getMapa().getRuta().get(barco.getCofre().getMapa().getSitioActual()).split("-");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(RemoteClass.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        }         
    }
}
