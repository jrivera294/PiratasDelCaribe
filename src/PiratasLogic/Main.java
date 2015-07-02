package PiratasLogic;

import PiratasGUI.PiratasGUI;
import java.io.File;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
//Imports RMI
import java.rmi.*;
import java.rmi.server.*;
import java.net.*;
import java.io.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author vicky
 */
public class Main {

    public static void main(String[] args) {
        int i, cofres;
        Maquina objeto = null;
        List<String> ruta;
        String nCalamidad;
        int oro, plata, perlas, monedas, joyas, piedras, corazon, capac;
        int cHombres, cComida, cMuniciones;
        //Variables Jose
        InputStreamReader ent = new InputStreamReader(System.in);
        BufferedReader buf = new BufferedReader(ent);
        String numPuerto, URLRegistro;
       
        System.setProperty("java.rmi.server.hostname", "192.168.110.121");
        
        try {
                Mapa mapa;
                
		File file = new File("src/configXML/maquina02.xml");
		JAXBContext jaxbContext = JAXBContext.newInstance(Maquina.class);
 
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		objeto = (Maquina) jaxbUnmarshaller.unmarshal(file);
                
                System.out.println(objeto.getSitioRemoto());
                
                for (i=0 ; i< objeto.getSitio().size(); i++){
                    oro = objeto.getSitio().get(i).getCofre().getOro();
                    plata = objeto.getSitio().get(i).getCofre().getPlata();
                    perlas = objeto.getSitio().get(i).getCofre().getPerlas();
                    monedas = objeto.getSitio().get(i).getCofre().getMonedasOro();
                    joyas = objeto.getSitio().get(i).getCofre().getJoyas();
                    piedras = objeto.getSitio().get(i).getCofre().getPiedrasPreciosas();
                    corazon = objeto.getSitio().get(i).getCofre().getCorazonPrincesa();
                    capac = objeto.getSitio().get(i).getCofre().getCapacidad();
                   
                    ruta = objeto.getSitio().get(i).getCofre().getMapa().getRuta();
                    
                    //System.out.println(objeto.getSitio().get(i).getCalamidad());
                    
                    if (objeto.getSitio().get(i).getCalamidad() != null){
                        nCalamidad = objeto.getSitio().get(i).getCalamidad().getNombre();
                        cHombres = objeto.getSitio().get(i).getCalamidad().getCostoHombres();
                        cMuniciones = objeto.getSitio().get(i).getCalamidad().getCostoMuniciones();
                        cComida = objeto.getSitio().get(i).getCalamidad().getCostoComida();

                        Calamidad calamidad = new Calamidad(nCalamidad, cHombres, cMuniciones, cComida);
                        objeto.getSitio().get(i).setCalamidad(calamidad);
                    }
                    
                    if(ruta == null){
                        mapa = null;
                    }else{
                        mapa = new Mapa(ruta);
                    }
                    

                    Cofre cofre = new Cofre(mapa, oro,plata,perlas,monedas,joyas,piedras,corazon,capac);
                    
                    objeto.getSitio().get(i).setCofre(cofre);
                    
                    //System.out.println(objeto.getSitio().get(i).getCofre());
                }
                
	  } catch (JAXBException e) {
		System.out.println("error");
	  }
        
        // Interfaz gráfica
        PiratasGUI graphicInterface = new PiratasGUI(objeto);
        graphicInterface.setVisible(true);
        
        // RMI Server
        try{
           numPuerto = Integer.toString(8050);
           System.out.println("Iniciando server RMI. Port:  "+numPuerto);
           arrancarRegistro(numPuerto);
           RemoteClass objetoRemoto = new RemoteClass(objeto,graphicInterface);
           URLRegistro = "rmi://192.168.110.121:"+numPuerto+"/barco";
           
           Naming.rebind(URLRegistro,objetoRemoto);
           System.out.println("Servidor registrado. El registro contiene actualmente:");
           listaRegistro(URLRegistro);
           System.out.println("Servidor barco preparado.");
       }catch(RemoteException excr){
           System.out.println("Excepción en el servidor.main:"+excr);
       } catch (MalformedURLException excr) {
           System.out.println("Excepción en el servidor.main:"+excr);
       }
        
        
        // Iniciar los barcos encontrados en el xml
        for(Barco barco : objeto.getBarco()){
            Cofre cBarco = new Cofre(barco.getCofre().getCapacidad());
            barco.setCofre(cBarco);
            barco.reabastecer();
            barco.llamadaRMI("192.168.110.121","",2);
        }
        
//        Barco barco = objeto.getBarco().get(0);
//        Cofre cBarco = new Cofre(barco.getCofre().getCapacidad());
//        barco.setCofre(cBarco);
//        barco.reabastecer();
//        barco.llamadaRMI("192.168.110.121","",2);
//        System.out.println("Barco: "+barco.getNombre());
        

    }

    private static void arrancarRegistro(String nroPuertoRMI) throws RemoteException{
        try{
            Registry registro = LocateRegistry.getRegistry(nroPuertoRMI);
            registro.list();
        }catch(RemoteException e){
            System.out.println("Registro RMI no encontrado en el puerto: "+nroPuertoRMI);
            Registry registro = LocateRegistry.createRegistry(Integer.parseInt(nroPuertoRMI));
            System.out.println("Registro RMI creado en el puerto "+nroPuertoRMI);
        }
    }
    
    private static void listaRegistro(String URLRegistro) 
            throws RemoteException, MalformedURLException{
        System.out.println("REgistro "+URLRegistro+" contiene: ");
        String [] nombres = Naming.list(URLRegistro);
        for (String nombre : nombres) {
            System.out.println(nombre);
        }
    }
}

