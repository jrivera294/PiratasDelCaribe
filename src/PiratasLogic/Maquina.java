package PiratasLogic;

import java.util.*;
import javax.xml.bind.annotation.*;
/**
 *
 * @author vicky
 */
@XmlRootElement
public class Maquina {
    private int id;
    private String ip;
    private List<String> ipRemota;
    private List<String> puntoSalida;
    private List<String> sitioRemoto;
    private List<Barco> barco;
    private List<Sitio> sitio;

    public List<Barco> getBarco() {
        return barco;
    }
    
    @XmlElement(name = "barco") 
    public void setBarco(List<Barco> barco) {
        this.barco = barco;
    }
    
    public List<Sitio> getSitio() {
        return sitio;
    }

    public List<String> getSitioRemoto() {
        return sitioRemoto;
    }

    @XmlElement(name = "sitioRemoto") 
    public void setSitioRemoto(List<String> sitioRemoto) {
        this.sitioRemoto = sitioRemoto;
    }
    
    @XmlElement(name = "sitio") 
    public void setSitio(List<Sitio> sitio) {
        this.sitio = sitio;
    }

    public int getId() {
        return id;
    }

    public String getIp() {
        return ip;
    }

    public String getPuntoSalida(String id) {
        for (String puntoSalida1 : this.puntoSalida) {
            if(puntoSalida1.split("-")[0].equals(id)){
                return puntoSalida1;
            }
        }
        return null;
    }

    public List<String> getPuntoSalida() {
        return puntoSalida;
    }

    @XmlElement(name = "ipRemota")
    public void setIpRemota(List<String> ipRemota) {
        this.ipRemota = ipRemota;
    }

    public List<String> getIpRemota() {
        return ipRemota;
    }
    
    @XmlAttribute
    public void setId(int id) {
        this.id = id;
    }

    @XmlElement(name = "ip")
    public void setIp(String ip) {
        this.ip = ip;
    }

    @XmlElement(name = "salida")
    public void setPuntoSalida(List<String> puntoSalida) {
        this.puntoSalida = puntoSalida;
    }
    
    
 
}
