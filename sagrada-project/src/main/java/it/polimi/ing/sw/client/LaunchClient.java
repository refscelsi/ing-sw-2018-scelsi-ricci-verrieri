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
            if(( null!=args)){
                Constants.SERVER_ADDRESS=args[0];
            }
            new View().start();

        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

}
