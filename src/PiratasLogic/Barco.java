package PiratasLogic;

import PiratasGUI.BarcoGUI;
import PiratasGUI.PiratasGUI;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author vicky
 */
public class Barco implements java.io.Serializable{
    private String nombre, rutaOrigen;
    private int tripulacion;
    private int comida;
    private int municiones;
    private int tipo;
    private Cofre cofre;
    private int maxTripulacion;
    private int maxComida;
    private int maxMuniciones;
    private transient BarcoGUI barcoGUI;
    private transient PiratasGUI graphicInterface;
    private boolean pelear;

    public void reabastecer(){
        this.setTripulacion(this.getMaxTripulacion());
        this.setComida(this.getMaxComida());
        this.setMuniciones(this.getMaxMuniciones());
    }

    public void setBarcoGUI(BarcoGUI barcoGUI) {
        this.barcoGUI = barcoGUI;
    }

    public void setGraphicInterface(PiratasGUI graphicInterface) {
        this.graphicInterface = graphicInterface;
    }
    
    public void setMaxTripulacion(int maxTripulacion) {
        this.maxTripulacion = maxTripulacion;
    }

    public String getRutaOrigen() {
        return rutaOrigen;
    }

    public void setRutaOrigen(String rutaOrigen) {
        this.rutaOrigen = rutaOrigen;
    }
   
    public void setMaxComida(int maxComida) {
        this.maxComida = maxComida;
    }

    public void setMaxMuniciones(int maxMuniciones) {
        this.maxMuniciones = maxMuniciones;
    }
    
    public int getMaxTripulacion() {
        return maxTripulacion;
    }

    public int getMaxComida() {
        return maxComida;
    }

    public int getMaxMuniciones() {
        return maxMuniciones;
    }
    
    public boolean irBatalla(Sitio sitio){
        int bajaTripulacion=0, bajaMunicion=0, tesoro = 0;
        Barco barcoOponente = new Barco();
        
        for (int i=0; i < sitio.getBarcoLocal().size(); i++){
            if (this.getNombre().equals(sitio.getBarcoLocal().get(i).getNombre()) == false){
                barcoOponente = sitio.getBarcoLocal().get(i);
                break;
            }
        }
        //Muestro ventana de si deseo pelear o no
        //si la respuesta es OK peleo, sino no.
        if (pelear == true){
            //Quito recursos de cada barco.
            
            //Bajas de Tripulacion y Municiones = A la mitad de sus diferencias.
            bajaTripulacion = (this.getTripulacion() - barcoOponente.getTripulacion())/2;
            bajaMunicion = (this.getMuniciones() - barcoOponente.getTripulacion())/2;
            
            //Seteo los valores de los barcos luego de las bajas.
            this.setTripulacion(this.getTripulacion() - bajaTripulacion);
            barcoOponente.setTripulacion(barcoOponente.getTripulacion() - bajaTripulacion);
            this.setMuniciones(this.getMuniciones() - bajaMunicion);
            barcoOponente.setMuniciones(barcoOponente.getMuniciones() - bajaMunicion);
            
            //Si la tripulacion en alguno de los barcos es < 1/3
            if (this.getTripulacion() < (1/3)){
                tesoro = (this.getCofre().getCapacidadActual())/2;
                sitio.getCofre().setCapacidad(sitio.getCofre().getCapacidadActual()+tesoro);
                
                //Va a su puerto de Origen a reabastecer
                this.irASitio(this.getRutaOrigen());
                
            }else if (barcoOponente.getTripulacion() < (1/3)){
                tesoro = (barcoOponente.getCofre().getCapacidadActual())/2;
                sitio.getCofre().setCapacidad(sitio.getCofre().getCapacidadActual()+tesoro);
                
                //Va a su puerto de Origen a reabastecer
                barcoOponente.irASitio(barcoOponente.getRutaOrigen());
            }
            
            //actualizo labels.
        }else{
            this.getCofre().getMapa().setSitioActual();
            String nextSitio = this.cofre.getMapa().getRuta().get(this.getCofre().getMapa().getSitioActual());
            this.irASitio(nextSitio);
        }
    return true;    
    }
       
    /**
     * Realiza la llamada RMI a la máquina remota, recibe la IP, el nombre del sitio
     * al que va a dirigirse en la otra máquina y el ID de la máquina que hace
     * la llamada.
     * @param ip
     * @param nombreSitio
     * @param idMaquina
     * @return 
     */
    public boolean llamadaRMI(String ip, String nombreSitio, int idMaquina){
        try{
            int puerto = 8050;
            String URLRegistro = "rmi://"+ip+":"+puerto+"/barco";
            
            InputStreamReader ent = new InputStreamReader(System.in);
            BufferedReader buf = new BufferedReader(ent);
                        
            RMIInterface objetoRemoto = (RMIInterface)Naming.lookup(URLRegistro);
            if(objetoRemoto.desembarcar(this, nombreSitio, idMaquina)){
                System.out.println("Barco "+this.nombre+" ha cruzado a la maquina "
                        +idMaquina+" con destino "+ nombreSitio);
                return true;
            }else{
                System.out.println("Error al cruzar de nodo.");
            }
        }catch(NotBoundException e){
            System.out.println("Error al conectar: "+e);
        } catch (MalformedURLException e) {
            System.out.println("Error al conectar: "+e);
        } catch (RemoteException e) {
            System.out.println("Error al conectar: "+e);
        }
        return false;
    }
    
    /**
     *  Función recoger, recoge y deja objetos en el cofre dependiendo del valor.
     *  Evalua si cambiar el mapa actual por el que está en el cofre, si lo hay.
     *  Retorna true si encontró el corazon de la princesa.
     * @param sitio
     * @return 
     */
    public boolean recoger(Sitio sitio){
        Mapa auxMapa;
        int nroObjetos;
        
        //Recoger todo lo que entre en el cofre. Si el corazón de la princesa
        //no entra en el cofre dejar algo.
    
        //Corazon
        if(sitio.getCofre().getCorazonPrincesa()>0){
            if(sitio.getCofre().getCapacidadActual() + Cofre.pesoCorazon >= sitio.getCofre().getCapacidad()){
                Cofre.liberarEspacio((int)Cofre.pesoCorazon, this.cofre, sitio.getCofre());
            }
            sitio.getCofre().setCorazonPrincesa(0);
            this.cofre.setCorazonPrincesa((int)Cofre.pesoCorazon);
            this.graphicInterface.setEstadoBarcos(this.nombre, this.tripulacion, this.municiones, this.comida, this.cofre.getCapacidadActual());
            return true;
        }
    
        //Oro
        if(sitio.getCofre().getOro() > 0 && 
        (this.cofre.getCapacidadActual()+Cofre.pesoOro) <= this.cofre.getCapacidad()){
            nroObjetos = sitio.getCofre().getOro()/(int)Cofre.pesoOro;
            this.cofre.setOro(this.cofre.getOro()+((int)Cofre.pesoOro*nroObjetos));
            sitio.getCofre().setOro(sitio.getCofre().getOro()-((int)Cofre.pesoOro*nroObjetos));
        }
    
        //Plata
        if(sitio.getCofre().getPlata() > 0 && 
        (this.cofre.getCapacidadActual()+Cofre.pesoPlata) <= this.cofre.getCapacidad()){
            nroObjetos = sitio.getCofre().getPlata()/(int)Cofre.pesoPlata;
            this.cofre.setPlata(this.cofre.getPlata()+((int)Cofre.pesoPlata*nroObjetos));
            sitio.getCofre().setPlata(sitio.getCofre().getPlata()-((int)Cofre.pesoPlata*nroObjetos));
        }
    
        //Perlas
        if(sitio.getCofre().getPerlas() > 0 && 
        (this.cofre.getCapacidadActual()+Cofre.pesoPlata) <= this.cofre.getCapacidad()){
            nroObjetos = sitio.getCofre().getPerlas()/(int)Cofre.pesoPlata;
            this.cofre.setPerlas(this.cofre.getPerlas()+((int)Cofre.pesoPlata*nroObjetos));
            sitio.getCofre().setPerlas(sitio.getCofre().getPerlas()-((int)Cofre.pesoPlata*nroObjetos));
        }
    
        //Monedas
        if(sitio.getCofre().getMonedasOro() > 0 && 
        (this.cofre.getCapacidadActual()+Cofre.pesoPlata) <= this.cofre.getCapacidad()){
            nroObjetos = sitio.getCofre().getMonedasOro()/(int)Cofre.pesoPlata;
            this.cofre.setMonedasOro(this.cofre.getMonedasOro()+((int)Cofre.pesoPlata*nroObjetos));
            sitio.getCofre().setMonedasOro(sitio.getCofre().getMonedasOro()-((int)Cofre.pesoPlata*nroObjetos));
        }
    
        //Joyas
        if(sitio.getCofre().getJoyas() > 0 && 
        (this.cofre.getCapacidadActual()+Cofre.pesoPlata) <= this.cofre.getCapacidad()){
            nroObjetos = sitio.getCofre().getJoyas()/(int)Cofre.pesoPlata;
            this.cofre.setJoyas(this.cofre.getJoyas()+((int)Cofre.pesoPlata*nroObjetos));
            sitio.getCofre().setJoyas(sitio.getCofre().getJoyas()-((int)Cofre.pesoPlata*nroObjetos));
        }
    
        //Piedras
        if(sitio.getCofre().getPiedrasPreciosas() > 0 && 
        (this.cofre.getCapacidadActual()+Cofre.pesoPlata) <= this.cofre.getCapacidad()){
            nroObjetos = sitio.getCofre().getPiedrasPreciosas()/(int)Cofre.pesoPlata;
            this.cofre.setPiedrasPreciosas(this.cofre.getPiedrasPreciosas()+((int)Cofre.pesoPlata*nroObjetos));
            sitio.getCofre().setPiedrasPreciosas(sitio.getCofre().getPiedrasPreciosas()-((int)Cofre.pesoPlata*nroObjetos));
        }        
        
        //Recoger mapa
        if(sitio.getCofre().getMapa() != null){
            //Si el barco no tiene mapa recogelo, si tiene mapa intercambialo, si le conviene
            if(this.cofre.getMapa()==null){
                this.cofre.setMapa(sitio.getCofre().getMapa());
                sitio.getCofre().setMapa(null);
                System.out.println("Barco: "+this.nombre+" recogió un mapa");
            }else{
                if(this.cofre.getMapa().getRuta().size()-this.cofre.getMapa().getSitioActual() > sitio.getCofre().getMapa().getRuta().size()){
                    auxMapa = this.cofre.getMapa();
                    this.cofre.setMapa(sitio.getCofre().getMapa());
                    sitio.getCofre().setMapa(auxMapa);
                    System.out.println("Barco: "+this.nombre+" intercambió Mapa");
                }
            }
        }
        
        this.graphicInterface.setEstadoBarcos(this.nombre, this.tripulacion, this.municiones, this.comida, this.cofre.getCapacidadActual());

        return false;
    }
    
    /**
     * Descuenta los recursos del barco, según los costos del sitio, 
     * y de alguna calamidad. Retorna true si luego de descontar queda con
     * menos de 1/3 de los recursos
     * @param sitio
     * @return 
     */
    public boolean descontarRecursos(Sitio sitio){
        this.comida -= sitio.getCostoComida();
        this.tripulacion -= sitio.getCostoTripulacion();
        
        if(sitio.getCalamidad() != null){
            this.comida -= sitio.getCalamidad().getCostoComida();
            this.municiones -= sitio.getCalamidad().getCostoMuniciones();
            this.tripulacion -= sitio.getCalamidad().getCostoHombres();
            
            this.barcoGUI.setCalamidadBarco(sitio.getCalamidad().getNombre());
            try {
                Thread.sleep(5000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Barco.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.barcoGUI.setCalamidadBarco("");
        }
        
        this.graphicInterface.setEstadoBarcos(this.nombre, this.tripulacion, this.municiones, this.comida, this.cofre.getCapacidadActual());
        
        if(this.comida <= this.maxComida/3 || this.municiones <= this.maxMuniciones/3
           || this.tripulacion <= this.maxTripulacion/3){
            return true;
        }
        //Si tiene menos de 1/3 de recursos retornar true
        return false;
    }
    
    /**
     * Si el sitio es local, se mueve al sitio, sino se mueve al límite del mapa
     * correspondiente, hace llamada RMI y se mueve al sitio remoto. Luego
     * ejecuta los métodos correspondientes.
     * @param sitio
     */
    public void irASitio(String sitio){
        
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTripulacion(int tripulacion) {
        this.tripulacion = tripulacion;
    }

    public void setComida(int comida) {
        this.comida = comida;
    }

    public void setMuniciones(int municiones) {
        this.municiones = municiones;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public void setCofre(Cofre cofre) {
        this.cofre = cofre;
    }

    public int getTripulacion() {
        return tripulacion;
    }

    public int getComida() {
        return comida;
    }

    public int getMuniciones() {
        return municiones;
    }

    public int getTipo() {
        return tipo;
    }

    public Cofre getCofre() {
        return cofre;
    }
    
}
