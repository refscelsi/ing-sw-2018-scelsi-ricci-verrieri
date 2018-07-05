# TEAM #
>Ricci Arianna MAT 846769 COD PERSONA 10536266

>Scelsi Riccardo MAT 845641 COD PERSONA 10521406

>Verrieri Ludovica MAT 846095 COD PERSONA 10520791

# COVERAGE TEST
[Coverage link](COVERAGE/)

# DIAGRAMMA UML https://www.draw.io/#G1PCbA6g2C3OemZky3b2PotWH-zAOn0Jcq
[UML link](DOCUMENTS/)

# Funzionalità implementate

>Regole complete

>Schemi customizzabili

>CLI

>GUI

>Socket

>RMI

### Manuale Utente:

### 1.0 Requisiti minimi dell’utente

Si presuppone che l’utente conosca completamente le [regole]
(https://github.com/refscelsi/ing-sw-2018-scelsi-ricci-verrieri/blob/master/SagradaRules.pdf) del gioco da tavolo 
[“Sagrada”].

Non è necessario, in quanto le descrizioni delle carte vengono sempre mostrate, conoscere le carte. Il consiglio è
comunque quello di leggerle, almeno prima di inziare a giocare per la prima volta:

> [carte utensili] (https://github.com/refscelsi/ing-sw-2018-scelsi-ricci-verrieri/blob/master/CarteUtensili.pdf)

> [carte obiettivo] (https://github.com/refscelsi/ing-sw-2018-scelsi-ricci-verrieri/blob/master/CarteObiettivo.pdf)


### 2.0 Utilizzo del gioco da Linea di Comando (CLI)

### 2.1 Connessione e login

Per una corretta configurazione della connessione è richiesto l’avvio precedente della
corrispettiva applicazione Server.
All’avvio del programma Client (LaunchClient) viene chiesto se utilizzare GUI o CLI: se l’utente vuole utilizzare GUI
digita`“g”` e preme invio, se vuole utilizzare la CLI digita `“c”` e preme invio.
Successivamente viene chiesto all'utente se vuole utilizzare la connessione realizzata tramite socket o RMI: digita
`“s”` per socket o `“r”` per RMI e di nuovo preme invio.
Di seguito viene chiesto all'utente di inserire un nickname: se questo nickname è già stato scelto da un altro giocatore
entrato in partita precedentemente, poiché il nickname deve essere univoco, viene chiesto di inserire un nuovo nickname.
Tali stringhe non sono suscettibili alle maiuscole.

### 2.2 Scelta dello schema

Al raggiungimento di 4 giocatori o in alternativa, dopo un certo tempo che 2 giocatori si sono loggati, parte la partita.
La prima mossa che viene chiesta ai giocatori in partita, è quella di scegliere uno schema tra 4 schemi disponibili per
ogni giocatore. Appena tutti i giocatori hanno scelto lo schema, ogni giocatore visualizza tutti gli elementi di
gioco e inizia il primo round.

### 2.3 Turno del giocatore

Ogni giocatore, durante il suo turno, può eseguire le seguenti azioni (eventualmente più di una, ma sempre in accordo con
le regole di gioco):

1) Piazzare un dado

2) Utilizzare una carta utensile

3) Visualizzare le informazioni degli altri giocatori

4) Saltare il turno

5) Uscire dalla partita

Se il giocatore non ha più azioni a disposizione, il turno passa automaticamente al giocatore successivo.


## Le Azioni

### 2.4 Piazzare un dado sul proprio schema

Nel caso in cui il giocatore scelga di voler piazzare un dado, gli viene chiesto di inserire il numero del dado che vuole 
posizionare e il numero della riga e della colonna dove vuole posizionarlo. La sua richiesta viene elaborata: 
se c'è stata un'infrazione del regolamento viene richiesto:

> Nel caso in cui il giocatore non possa piazzare un dado (ad esempio perché già ne ha piazzato uno nello stesso turno),
di scegliere nuovamente un'azione disponibile

> Nel caso in cui il giocatore non abbia rispettato le regole di piazzamento, di scegliere una casella valida

Se il piazzamento è andato a buon fine, viene visualizzato un aggiornamento della situazione.

### 2.5 Utilizzare una carta utensile

Nel caso il cui il giocatore decida di utilizzare una carta utensile, gli verrà chiesto che carta vuole utilizzare.
Una volta scelta la carta, vengono richieste le informazioni necessarie per utilizzare la carta. La richiesta viene
elaborata: se c'è stata un'infrazione del regolamento viene richiesto:

> Nel caso in cui il giocatore non possa utilizzare una carta obiettivo (ad esempio perché già ne ha utilizzata
una nello stesso turno), di scegliere nuovamente un'azione disponibile

> Nel caso in cui il giocatore non abbia rispettato le regole della carta, di inserire nuovamente le informazioni necessarie

Se la mossa è andata a buon fine, viene visualizzato un aggiornamento della situazione. Nell'eventualità in cui siano
necessarie altre informazioni per utilizzare la carta, vengono richieste in questo momento.

### 2.6 Visualizzare le informazioni degli altri giocatori.

Nel caso il cui il giocatore scelga questa azione, visualizza il nickname, i segnalini favore e lo schema degli altri
giocatori in partita.

### 2.7 Saltare il turno

Nel caso il cui il giocatore decida di passare il turno, passa il turno al giocatore successivo.

### 2.8 Uscire dalla partita

Nel caso il cui il giocatore scelga questa azione, esce dalla partita. Viene notificato che è l'azione è andata a buon
fine e premendo `“0”` può rientrare in partita in qualsiasi momento, ovviamente senza la possibilità di
recuperare i turni persi.

Gli altri giocatori continuano a giocare senza cambiamenti, ma il turno del giocatore viene saltato. Il suo punteggio
sarà comunque presente a fine partita e visualizzerà anch'egli la classifica finale.

Se rimane in partita un solo giocatore, il gioco termina e tutti i giocatori che hanno fatto parte della partita
ricevono la classifica finale.

### 2.9 Carte utensili utilizzabili solo se non si è già piazzato un dado

Alcune carte utensili si possono utilizzare solo se già non si è utilizzato un dado, dal momento che prevedono il
piazzamento di un dado. Queste carte sono:

> Lathekin

> Pennello per pasta salda

> Diluente per pasta salda

> Taglierina manuale

### 2.10 Fine della partita

Alla fine della partita, tutti i giocatori che hanno partecipato alla partita visualizzano la classifica finale.

# Scelte rilevanti dei programmatori e motivazioni

### Sulle mosse

Avendo la piena gestione dei controlli lato server, abbiamo deciso, a parte per le mosse complesse, di considerare ogni
mossa come qualcosa di indivisibile; ovvero il client invia al server la richiesta di fare la mossa solo quando l’utente
ha inserito tutte le informazioni necessarie per eseguirla. 
Probabilmente fare i controlli su ogni singolo input sarebbe stata la scelta ottimale dal punto di vista di praticità
del gioco per l’utente, ma nel nostro caso avrebbe sovraccaricato molto il traffico di rete e la leggibilità del codice.
Perciò abbiamo pensato che, dal momento che si suppone che l’utente nella maggior parte dei casi esegua mosse corrette
che vanno a buon fine, la nostra scelta di implementazione non facesse perdere qualità al gioco.

### Sul piazzamento di un dado

Quando il giocatore sbaglia il piazzamento di un dado infrangendo le regole di piazzamento, può scegliere una nuova
casella dove piazzare il dado scelto, senza però avere la possibilità di scegliere nuovamente quest’ultimo. 
Abbiamo preso questa scelta per aumentare leggermente la difficoltà del gioco.

### Sullo spostamento di un dado

Abbiamo deciso, confrontandoci anche con altri colleghi che stavano lavorando sullo stesso gioco, di dare al giocatore
la possibilità di spostare un dado anche in una casella non adiacente ad un altro dado. 
Quando il giocatore sbaglia lo spostamento di un dado infrangendo le regole di piazzamento, può ripetere l’operazione
da capo. Per uniformità con la scelta presa con il piazzamento, avremmo potuto non dare la possibilità di scegliere di
nuovo la casella di origine dello spostamento. Questo però avrebbe portato a dover gestire in modo differente la
possibilità che la casella di origine sia vuota. Facendo tutti i controlli lato server, questa scelta (e in generale
la scelta di inviare ogni input al server i con metodi delle tool ci sembrava parecchio onerosa sul server).

## Limitazioni del gioco

>Quando il giocatore esce dalla partita, viene notificato solo il giocatore uscito

>Quando un giocatore viene espulso dal gioco perché è da troppo tempo che non esegue una mossa, non può rientrare
in partita (nel caso in cui esca per sua spontanea volontà o perché ha perso la connessione, riesce a rientrare
in partita se e quando lo desidera)

>Pochi pop-up

>Non tutti gli eventi sono standardizzati, ciò porta ad avere errori di visualizzazione del messaggio in determinate
circostanze

>No dadi in grafica vettoriale

 
