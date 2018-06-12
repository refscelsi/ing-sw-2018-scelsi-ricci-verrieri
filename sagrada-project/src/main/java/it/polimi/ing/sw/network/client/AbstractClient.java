package it.polimi.ing.sw.network.client;

/**
 *
 * Classe che rappresenta l'astrazione necessaria per le comunicazioni col
 * server. Estendendo questa classe e' possibile utilizzare qualsiasi tipo di
 * connessione (es. RMI o Socket). L'interfaccia {@link IClient} funziona come
 * controller client e callback handler.
 */

public abstract class AbstractClient {
    /**
     * Token che identifica in modo univoco il giocatore sul Server.
     */
    protected String sessionToken;

    /**
     * Interfaccia utilizzata per comunicare con il client.
     */
    private final IClient controller;

    /**
     * Indirizzo del server.
     */
    private final String address;

    /**
     * Porta usata dal server per la comunicazione.
     */
    private final int port;

    /**
     * Costruttore astratto.
     *
     * @param controller
     *            client controller.
     * @param address
     *            indirizzo del server.
     * @param port
     *            porta del server.
     */
    public AbstractClient(IClient controller, String address, int port) {
        this.controller = controller;
        this.address = address;
        this.port = port;
    }

    /**
     * Ritorna l'indirizzo del server.
     *
     * @return l'indirizzo del server.
     */
    protected String getAddress() {
        return address;
    }

    /**
     * Ritorna la porta usata dal server per la comunicazione.
     *
     * @return porta del server.
     */
    protected int getPort() {
        return port;
    }

    /**
     * Ritorna il client controller per potere (scrivere/inviare) richieste sul
     * (canale di comunicazione/oggetto remoto).
     *
     * @return client controller (es. {@link Client}).
     */
    protected IClient getController() {
        return controller;
    }

    public abstract void connect();

    public abstract void sendLoginRequest();

    public abstract void sendActionRequest();

}
