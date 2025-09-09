/** Elementi principali del DOM
 * @type {HTMLDivElement}  dashBoardContatti
 */
const dashBoardContatti = document.getElementById('dashBoard-contatto');
/** Event listener per il DOM
 *  chiama la funzione funzioneMostraContatti(); una volta caricata la pagina
 */
document.addEventListener("DOMContentLoaded", () => {
    funzioneMostraContatti();
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
                    let id = document.createElement('p')
                    let nome = document.createElement('p');
                    let cognome = document.createElement('p');
                    let numeroTelefono = document.createElement('p');
                    let infoContatto = document.createElement('h2');
                    infoContatto.textContent = `Informazioni del contatto:`
                    id.textContent = `ID: ${contatto.id}`
                    nome.textContent = `Nome: ${contatto.nome}`
                    cognome.textContent = `Cognome: ${contatto.cognome}`
                    numeroTelefono.textContent = `Numero di Telefono: ${contatto.numeroTelefono}`
                    dashBoardContatti.append(infoContatto ,id, nome, cognome, numeroTelefono);
                })
            }
        }).catch((errore) => {
            console.log(`Qualcosa è andato storto: ${errore}`)
        })
}