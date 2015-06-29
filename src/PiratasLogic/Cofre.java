package PiratasLogic;

import javax.xml.bind.annotation.*;
/**
 *
 * @author vicky
 */

public class Cofre implements java.io.Serializable{
    private Mapa mapa;
    private int oro;
    private int plata;
    private int perlas;
    private int monedasOro;
    private int joyas;
    private int piedrasPreciosas;
    private int corazonPrincesa;
    private int capacidad;
    private int totalActual;
    public static double pesoOro = 25.0;
    public static double pesoPlata = 50.0;
    public static double pesoPerlas = 10.0;
    public static double pesoMonedas = 15.0;
    public static double pesoJoyas = 15.0;
    public static double pesoPiedras = 10.0;
    public static double pesoCorazon = 5.0;

    public Cofre() {
        this.mapa = null;
        this.oro = 0;
        this.plata = 0;
        this.perlas = 0;
        this.monedasOro = 0;
        this.joyas = 0;
        this.piedrasPreciosas = 0;
        this.corazonPrincesa = 0;
        this.capacidad = 0;
    }
    
    public Cofre(int capacidad) {
        this.mapa = null;
        this.oro = 0;
        this.plata = 0;
        this.perlas = 0;
        this.monedasOro = 0;
        this.joyas = 0;
        this.piedrasPreciosas = 0;
        this.corazonPrincesa = 0;
        this.capacidad = capacidad;
    }
    
    public Cofre(Mapa mapa,int oro, int plata, int perlas, int monedasOro, int joyas, int piedrasPreciosas, int corazonPrincesa, int capacidad) {
        this.mapa = mapa;
        this.oro = oro*(int)pesoOro;
        this.plata = plata*(int)pesoPlata;
        this.perlas = perlas*(int)pesoPerlas;
        this.monedasOro = monedasOro*(int)pesoMonedas;
        this.joyas = joyas*(int)pesoJoyas;
        this.piedrasPreciosas = piedrasPreciosas*(int)pesoPiedras;
        this.corazonPrincesa = corazonPrincesa*(int)pesoCorazon;
        this.capacidad = capacidad;
    }
    
    public int getCapacidadActual(){
        this.totalActual = this.oro + this.plata + this.perlas + this.monedasOro + this.joyas + this.piedrasPreciosas + this.corazonPrincesa;
        
        return totalActual;
    }
    
    /* NOTA: Arreglar constructor, definir si todo Double o INT */
    //If lo que tengo de oro es mayor a lo q libero.
            //Cantidad a liberar ENTRE peso por obj
                //Si es entero tomo el numero, si decimales redondear al sig.
                    //Seteo los valores cofreOrigen y cofreDestino
    /**
     * Libera espacio en libras de un cofreOrigen a uno destino
     * @param libras
     * @param cofreOrigen
     * @param cofreDestino 
     */
    public static void liberarEspacio(int libras, Cofre cofreOrigen, Cofre cofreDestino){
        double div, decimal;
        int entero;
        int unidCofre, unidRemove;
        
        if (cofreOrigen.getOro() >= libras){
            div = (libras/pesoOro);
            decimal = div - Math.floor(div);

            entero = (int)div;
            
            if (decimal > 0){
                unidCofre = cofreOrigen.getOro()/(int)pesoOro;
                unidRemove = unidCofre - (entero+1);
                cofreOrigen.setOro(unidRemove*(int)pesoOro);
            }else{
                cofreOrigen.setOro(entero*(int)pesoOro);
            }
            
        }else if (cofreOrigen.getPlata() >= libras){
            div = (libras/pesoPlata);
            decimal = div - Math.floor(div);

            entero = (int)div;
            
            if (decimal > 0){
                unidCofre = cofreOrigen.getPlata()/(int)pesoPlata;
                unidRemove = unidCofre - (entero+1);
                cofreOrigen.setPlata(unidRemove*(int)pesoPlata);
            }else{
                cofreOrigen.setPlata(entero*(int)pesoPlata);
            }
            
        }else if (cofreOrigen.getPerlas() >= libras){
            div = (libras/pesoPerlas);
            decimal = div - Math.floor(div);

            entero = (int)div;
            
            if (decimal > 0){
                unidCofre = cofreOrigen.getPerlas()/(int)pesoPerlas;
                unidRemove = unidCofre - (entero+1);
                cofreOrigen.setPerlas(unidRemove*(int)pesoPerlas);
            }else{
                cofreOrigen.setPerlas(entero*(int)pesoPerlas);
            }
            
        }else if (cofreOrigen.getJoyas() >= libras){
            div = (libras/pesoJoyas);
            decimal = div - Math.floor(div);

            entero = (int)div;
            
            if (decimal > 0){
                unidCofre = cofreOrigen.getJoyas()/(int)pesoJoyas;
                unidRemove = unidCofre - (entero+1);
                cofreOrigen.setJoyas(unidRemove*(int)pesoJoyas);
            }else{
                cofreOrigen.setJoyas(entero*(int)pesoJoyas);
            }
            
        }else if (cofreOrigen.getMonedasOro() >= libras){
            div = (libras/pesoMonedas);
            decimal = div - Math.floor(div);

            entero = (int)div;
            
            if (decimal > 0){
                unidCofre = cofreOrigen.getMonedasOro()/(int)pesoMonedas;
                unidRemove = unidCofre - (entero+1);
                cofreOrigen.setMonedasOro(unidRemove*(int)pesoMonedas);
            }else{
                cofreOrigen.setMonedasOro(entero*(int)pesoMonedas);
            }
            
        }else if (cofreOrigen.getPiedrasPreciosas() >= libras){
            div = (libras/pesoPiedras);
            decimal = div - Math.floor(div);

            entero = (int)div;
            
            if (decimal > 0){
                unidCofre = cofreOrigen.getPiedrasPreciosas()/(int)pesoPiedras;
                unidRemove = unidCofre - (entero+1);
                cofreOrigen.setPiedrasPreciosas(unidRemove*(int)pesoPiedras);
            }else{
                cofreOrigen.setPiedrasPreciosas(entero*(int)pesoPiedras);
            }
        }     
    }
    
    public void setMapa(Mapa mapa) {
        this.mapa = mapa;
    }

    public void setOro(int oro) {
        this.oro = oro;
    }

    public void setPlata(int plata) {
        this.plata = plata;
    }

    public void setPerlas(int perlas) {
        this.perlas = perlas;
    }

    public void setMonedasOro(int monedasOro) {
        this.monedasOro = monedasOro;
    }

    public void setJoyas(int joyas) {
        this.joyas = joyas;
    }

    public void setPiedrasPreciosas(int piedrasPreciosas) {
        this.piedrasPreciosas = piedrasPreciosas;
    }

    public void setCorazonPrincesa(int corazonPrincesa) {
        this.corazonPrincesa = corazonPrincesa;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public Mapa getMapa() {
        return mapa;
    }

    public int getOro() {
        return oro;
    }

    public int getPlata() {
        return plata;
    }

    public int getPerlas() {
        return perlas;
    }

    public int getMonedasOro() {
        return monedasOro;
    }

    public int getJoyas() {
        return joyas;
    }

    public int getPiedrasPreciosas() {
        return piedrasPreciosas;
    }

    public int getCorazonPrincesa() {
        return corazonPrincesa;
    }

    public int getCapacidad() {
        return capacidad;
    }
    
}
