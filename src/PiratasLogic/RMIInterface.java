package PiratasLogic;
import java.rmi.*;

/**
 *
 * @author Jose Gabriel
 */
public interface RMIInterface extends Remote{
    public boolean desembarcar(Barco barco, String nombreSitio, int idMaquina)
            throws java.rmi.RemoteException;
}
