package it.polimi.ing.sw.client;

import it.polimi.ing.sw.util.Constants;
import org.apache.commons.lang.StringUtils;
import org.codehaus.plexus.util.CollectionUtils;

import java.rmi.RemoteException;

/**
 * Classe che lancia classe il client e istanzia la View, su cui chiama @start()
 */
public class LaunchClient {
    public static void main(String[] args) {
        try {
            String ip;
            if(( null!=args)){
                ip=Constants.SERVER_ADDRESS;
            }else{
                ip=args[0];
            }
            new View(ip).start();

        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

}
