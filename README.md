# Rubrica
## Documentazione 
Per consultare ulteriore documetazione dettagliata, consultare il pdf.

## Una semplice rubrica online che permette di aggiungere, cercare ed eliminare contatti
## Caratteristiche principali:
- Aggiungere un contatto alla rubrica
- Cercare un contatto per nome 
- Cercare un contatto per numero di telefono
- Eliminare un contatto
- Mostrare tutti i contatti presenti in rubrica
## Requisiti:
- Java 17+
- Maven (incluso nel progetto come wrapper: `./mvnw`)
- Spring Boot
- Browser moderno (per la parte frontend in JS, HTML, CSS)
## Installazione:
Clona la repository ed entra nella cartella del progetto:
```bash
git clone https://github.com/AlexGiunta-source/rubrica.git
cd rubrica 
```
## Compila ed esegui l’applicazione:
```bash
./mvnw spring-boot:run
```
## Apri il browser e vai all’indirizzo:
```
http://localhost:8080
```
## Utilizzo:
Una volta aperta la pagina del browser, devi: 
- **Registrarti** come nuovo utente
- Effettuare il **login** con le credenziali usate nella registrazione <br>

Una volta effettuato l'accesso potrai:
- Aggiungere un nuovo contatto
- Cercare un contatto per nome
- Cercare un contatto per cognome
- Cercare un contatto per numero di telefono
- Mostrare la lista di contatti presenti nella TUA rubrica
- Eliminare un contatto prendendo l'id del contatto mostrato nella lista contatti
## Licenza:
Questo progetto è distribuito sotto licenza MIT.
## Contatti:
Autore: Alex Giunta <br> 
GitHub: @AlexGiunta-source <br> 
Email: alexgiunta7@gmail.com <br> 
