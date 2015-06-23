package PiratasLogic;

import static java.lang.Integer.parseInt;
import java.util.*;

/**
 *
 * @author vicky
 */
public class Mapa implements java.io.Serializable{
    private List<String> ruta; //posicion es el sitio, elemento dentro del ArrayList es IP
    private int sitioActual;

    public Mapa() {
        this.ruta = null;
        this.sitioActual = 0;
    }
    
    public Mapa(List<String> ruta) {
        this.ruta = ruta;
        this.sitioActual = 0;
    }
    
    /**
     * Retorna true si debe viajar a otro continente, false si el destino es local
     * @param destino
     * @param idLocal
     * @return
     */
    public boolean evaluarDestino(String destino, int idLocal){
        String[] idRemoto;
        
        idRemoto = destino.split("-");
        
        if (parseInt(idRemoto[0]) == idLocal){
            return false;
        }
        return true;
    }

    public void setSitioActual(int sitioActual) {
        this.sitioActual = sitioActual;
    }
    
    public void setSitioActual() {
        this.sitioActual++;
    }

    public int getSitioActual() {
        return sitioActual;
    }

    public void setRuta(List<String> ruta) {
        this.ruta = ruta;
    }

    public List<String> getRuta() {
        return ruta;
    }  
    
}
