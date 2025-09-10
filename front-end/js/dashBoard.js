/** Elementi principali del DOM
 * @type {HTMLDivElement}  dashBoardContatti
 * @type {HTMLTableCellElement} cellaIdContatto
 * @type {HTMLTableCellElement} cellaNome
 * @type {HTMLTableCellElement} cellaCognome
 * @type {HTMLTableCellElement} cellaNumeroTelefono
 * @type {HTMLTableElement} tabellaContatti
 * @type {HTMLButtonElement} bottonePagina
 */
const dashBoardContatti = document.getElementById('dashBoard-contatto');
const cellaIdContatto = document.getElementById('IdContatto');
const cellaNome = document.getElementById('nomeContatto');
const cellaCognome = document.getElementById('cognomeContatto');
const cellaNumeroTelefono = document.getElementById('numeroTelefonoContatto');
const tabellaContatti = document.getElementById('tabellaContatti');
const bottonePagina = document.getElementById('bottoneHome')

/** Event listener per il DOM
 *  chiama la funzione funzioneMostraContatti(); una volta caricata la pagina
 */
document.addEventListener("DOMContentLoaded", () => {
    funzioneMostraContatti();
})
/** Event listener per il bottone
 *  una volta cliccato, chiama la funzione funzioneRitornaHome(); e ritorna alla home della pagina
 */
bottonePagina.addEventListener('click', () => {
    funzioneRitornaHome();
})
/** funzioneMostraContatti 
 *  recupera tutti i contatti collegati all'utente presenti nella rubrica e li mostra
 * @function 
 * @returns {void}
 */
const funzioneMostraContatti = () => {
    fetch('http://localhost:8080/mostraListaContatti/', {
        method: 'GET',
        credentials: 'include',
        headers: {
            'Content-Type': 'application/json'
        }
    }).then(response => response.json())
        .then((data) => {
            console.log(data);
            if (data.statusCode === 'OK') {
                console.log(data);
                data.data.forEach((contatto) => {
                    let id = document.createElement('th')
                    let nome = document.createElement('th');
                    let cognome = document.createElement('th');
                    let numeroTelefono = document.createElement('th');
                    let rigaTabella = document.createElement('tr')
                    id.textContent = `${contatto.id}`
                    nome.textContent = `${contatto.nome}`
                    cognome.textContent = `${contatto.cognome}`
                    numeroTelefono.textContent = `${contatto.numeroTelefono}`
                    tabellaContatti.append(id, nome, cognome, numeroTelefono);
                    tabellaContatti.appendChild(rigaTabella);
                })
            }
        }).catch((errore) => {
            console.log(`Qualcosa è andato storto: ${errore}`)
        })
}
/** funzioneRitornaHome
 * reindirizza alla home
 */
const funzioneRitornaHome = () => {
    window.location.href = 'rubrica.html'
}