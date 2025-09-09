/** Elementi principali del DOM
 * @type {HTMLFormElement} formCognome
 * @type {HTMLInputElement} inputCercaCognome
 */
const formCognome = document.getElementById('formCognome');
const inputCercaCognome = document.getElementById('cercaCognome');
/** Event listener per il form.
 *  previene il caricamento automatico della pagina dato dal form
 *  chiama la funzione funzioneCercaPerCognome(); una volta compilato il form
 */
formCognome.addEventListener('submit', (event) => {
    event.preventDefault();
    funzioneCercaPerCognome();
})
/** Funzione cerca per cognome
 * Recupera i dati dal form, e invia una richiesta di tipo GET all'end point /cercaContattoPerCognome/
 *  mostra notifiche di successo o di errore
 * @function 
 * @returns {void}
 */
const funzioneCercaPerCognome = () => {
    if (inputCercaCognome.value === '') {
        Toastify({
            text: `Attenzione! il campo è vuoto, riprova`,
            duration: '1000',
            gravity: 'top',
            position: 'center',
            style: {
                background: 'red'
            }
        }).showToast();
        return
    } else {
        fetch(`http://localhost:8080/cercaContattoPerCognome/${inputCercaCognome.value}`, {
            method: 'GET',
            credentials: 'include',
            headers: {
                'content-Type': 'application/json'
            }
        }).then(response => response.json())
            .then((data) => {
                console.log('primo console log');
                if (data.statusCode === "OK") {
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
                console.log(`Errore: ${errore.message}`);
            })
    }
}