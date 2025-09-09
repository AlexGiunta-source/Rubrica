/** Elementi principali del DOM
 * @type {HTMLFormElement} formNome
 * @type {HTMLInputElement} inputCercaNome
 */
const formNome = document.getElementById('form-nome');
const inputCercaNome = document.getElementById('cercaNome');

/** Event listener per il form.
 *  previene il caricamento automatico della pagina dato dal form
 *  chiama la funzione funzioneCercaPerNome(); una volta compilato il form
 */
formNome.addEventListener('submit', (event) => {
    event.preventDefault();
    funzioneCercaPerNome();
})
/** Funzione cerca per nome
 * Recupera i dati dal form, e invia una richiesta di tipo GET all'end point /cercaContattoPerNome/
 *  mostra notifiche di successo o di errore
 * @function 
 * @returns {void}
 */
const funzioneCercaPerNome = () => {
    
    if (inputCercaNome.value === '') {
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
        fetch(`http://localhost:8080/cercaContattoPerNome/${inputCercaNome.value}`, {
            method: 'GET',
            credentials: 'include',
            headers: {
                "Content-Type": "application/json"
            }
        }).then(response => response.json())
            .then((data) => {
                console.log(data);
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