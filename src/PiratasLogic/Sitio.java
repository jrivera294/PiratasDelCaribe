package PiratasLogic;

import java.util.List;
import javax.xml.bind.annotation.*;
/**
 *
 * @author vicky
 */

public class Sitio {
    private Barco barcoPirata, barcoNaval;
    private String isla;
    private String nombreSitio;
    private Cofre cofre;
    private int costoTripulacion;
    private int costoComida;
    private Calamidad calamidad;
    private int posX;
    private int posY;
    
    public Sitio() {
        this.isla = null;
        this.nombreSitio = null;
        this.cofre = null;
        this.costoTripulacion = 0;
        this.costoComida = 0;
        this.calamidad = null;
    }
     
    public Sitio(String isla, String nombreSitio, int costoTripulacion, int costoComida) {
        this.isla = isla;
        this.nombreSitio = nombreSitio;
        this.costoTripulacion = costoTripulacion;
        this.costoComida = costoComida;
    }
    
    public Sitio(String nombreSitio, int costoTripulacion, int costoComida) {
        this.nombreSitio = nombreSitio;
        this.costoTripulacion = costoTripulacion;
        this.costoComida = costoComida;
    }

    public Barco getBarcoPirata() {
        return barcoPirata;
    }

    public void setBarcoPirata(Barco barcoPirata) {
        this.barcoPirata = barcoPirata;
    }

    public Barco getBarcoNaval() {
        return barcoNaval;
    }

    public void setBarcoNaval(Barco naval) {
        this.barcoNaval = naval;
    }
    
    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }
        
    public String getNombreSitio() {
        return nombreSitio;
    }

    public void setNombreSitio(String nombreSitio) {
        this.nombreSitio = nombreSitio;
    }

    public void setIsla(String isla) {
        this.isla = isla;
    }

    public void setCofre(Cofre cofre) {
        this.cofre = cofre;
    }

    public void setCostoTripulacion(int costoTripulacion) {
        this.costoTripulacion = costoTripulacion;
    }

    public void setCostoComida(int costoComida) {
        this.costoComida = costoComida;
    }

    public void setCalamidad(Calamidad calamidad) {
        this.calamidad = calamidad;
    }

    public String getIsla() {
        return isla;
    }

    public Cofre getCofre() {
        return cofre;
    }

    public int getCostoTripulacion() {
        return costoTripulacion;
    }

    public int getCostoComida() {
        return costoComida;
    }

    public Calamidad getCalamidad() {
        return calamidad;
    }
    
    
}
