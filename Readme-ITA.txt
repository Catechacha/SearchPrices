Trovaprezzi: sviluppare un programma che implementa un servizio trova prezzi 
e i suoi clienti.
Consideriamo 3 negozi web diversi che vendono telefonini e smartphone. 
Ogni negozio ha una lista di prodotti da vendere. Ogni prodotto � descritto 
da: nome del produttore, modello, prezzo, negozio in cui e venduto. Ogni 
negozio mette a disposizione un server che offre la lista completa dei 
prodotti, su richiesta. Tutti e tre i negozi offrono la stessa API e usano 
socket per ricevere le richieste. (Tip: scrivere una classe che modella i 
server e avviare 3 istanze diverse, una per ogni negozio)
Un server trova prezzi mantiene una lista di prodotti ed il negozio in cui 
sono venduti. All�avvio del server, contatta i tre negozi per avere tutte le 
liste. Ogni giorno il server trova prezzi ricontatta i negozi per aggiornare 
la lista di prodotti di ognuno. Il server trova prezzi offre un API per i 
clienti che cercano un prodotto. Un cliente invia il nome di un prodotto al 
server, che risponde con una lista di prodotti con negozio e prezzo, in ordine 
crescente. Implementare il server ed i clienti, usando socket. Il server deve 
essere in grado di gestire pi� clienti alla volta. L�aggiornamento della lista 
di prodotti si fa ogni 24 secondi (invece di ogni 24 ore).
Attenzione: l�accesso alla lista di prodotti del server trova prezzi deve 
essere thread safe.  Usare serializzazione per scambiare i prodotti.