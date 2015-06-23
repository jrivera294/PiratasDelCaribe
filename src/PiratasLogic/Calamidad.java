package PiratasLogic;

/**
 *
 * @author vicky
 */
public class Calamidad {
    private String nombre;
    private int costoHombres;
    private int costoMuniciones;
    private int costoComida;

    public Calamidad() {
        this.nombre = null;
        this.costoHombres = 0;
        this.costoMuniciones = 0;
        this.costoComida = 0;
    }
    
    public Calamidad(String nombre, int costoHombres, int costoMuniciones, int costoComida) {
        this.nombre = nombre;
        this.costoHombres = costoHombres;
        this.costoMuniciones = costoMuniciones;
        this.costoComida = costoComida;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCostoHombres(int costoHombres) {
        this.costoHombres = costoHombres;
    }

    public void setCostoMuniciones(int costoMuniciones) {
        this.costoMuniciones = costoMuniciones;
    }

    public void setCostoComida(int costoComida) {
        this.costoComida = costoComida;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCostoHombres() {
        return costoHombres;
    }

    public int getCostoMuniciones() {
        return costoMuniciones;
    }

    public int getCostoComida() {
        return costoComida;
    }
    
}
