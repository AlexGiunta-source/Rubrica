/** Elementi principali del DOM
 * @type {HTMLFormElement} formNumeroTelefono
 * @type {HTMLInputElement} inputCercaNumeroTelefono
 */
const formNumeroTelefono = document.getElementById('formNumeroTelefono');
const inputCercaNumeroTelefono = document.getElementById('cercaNumeroTelefono');

/** Event listener per il form.
 *  previene il caricamento automatico della pagina dato dal form
 *  chiama la funzione funzioneCercaPerCognome(); una volta compilato il form
 */
formNumeroTelefono.addEventListener('submit', (event) => {
    event.preventDefault();
    funzioneCercaPerNumeroTelefono();
})
/** Funzione cerca per numeroTelefono
 * Recupera i dati dal form, e invia una richiesta di tipo GET all'end point /cercaContattoPerNumeroTelefono/
 * mostra notifiche di successo o di errore
 * @function 
 * @returns {void}
 */
const funzioneCercaPerNumeroTelefono = () => {
    
    if (inputCercaNumeroTelefono.value === '') {
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
        fetch(`http://localhost:8080/cercaContattoPerNumeroTelefono/${inputCercaNumeroTelefono.value}`, {
            method: 'GET',
            credentials: 'include',
            headers: {
                'Content-Type': 'application/json'
            }
        }).then(response => response.json())
            .then((data) => {
                if (data.statusCode === 'OK') {
                    Toastify({
                        text: `${data.message}`,
                        duration: 1000,
                        gravity: 'top',
                        position: 'center',
                        style: {
                            background: 'green'
                        }
                    }).showToast();
                } else {
                    Toastify({
                        text: `${data.message}`,
                        duration: 1000,
                        gravity: 'top',
                        position: 'center',
                        style: {
                            background: 'red'
                        }
                    }).showToast();
                }
            }).catch((errore) => {
                console.log(`${errore.message}`)
            })
    }
}