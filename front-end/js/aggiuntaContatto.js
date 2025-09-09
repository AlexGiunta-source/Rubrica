/** Elementi principali del DOM
 * form per salvare un nuovo contatto nella rubrica
 * @type {HTMLFormElement}  
 */
const form = document.getElementById("aggiungi");

/** Event listener per il form. 
 * previene il caricamento automatico della pagina dato dal form
 * chiama la funzione funzioneAggiungiContatto(); una volta compilato il form
 */
form.addEventListener('submit', (event) => {
    event.preventDefault();
    funzioneAggiungiContatto();
})
/** Funzione AggiungiContatto
 * Recupera i dati dal form, e invia una richiesta di tipo POST all'end point /aggiungiContattoUtente/
 * mostra notifiche di successo o di errore
 * @function 
 * @returns {void}
 */
const funzioneAggiungiContatto = () => {
    const nuovoContatto = {
        nome: document.getElementById('nome').value,
        cognome: document.getElementById('cognome').value,
        numeroTelefono: document.getElementById('numeroTelefono').value
    };
    if (nome.value === '' || cognome.value === '' || numeroTelefono.value === '') {
        Toastify({
            text: `Uno o più campi sono vuoti, riprova`,
            duration: '1000',
            gravity: 'top',
            position: 'center',
            style: {
                background: 'red'
            }
        }).showToast();
        return
    }
    fetch('http://localhost:8080/aggiungiContattoUtente/', {
        method: 'POST',
        credentials: 'include',
        body: JSON.stringify(nuovoContatto),
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then(response => response.json().then(data => ({ response, data })))
        .then(({ response, data }) => {
            if (data.statusCode == 'OK') {
                Toastify({
                    text: `${data.message}`,
                    duration: '1000',
                    gravity: 'top',
                    position: 'center',
                    style: {
                        background: 'green'
                    }
                }).showToast();
            } else {
                Toastify({
                    text: `${data.message}`,
                    duration: '1000',
                    gravity: 'top',
                    position: 'center',
                    style: {
                        background: 'red'
                    }
                }).showToast();
            }
        }).catch((errore) => {
            console.log(`Errore nella post: ${errore}`)
        })
}


