/** Elementi principali del DOM
 * @type {HTMLButtonElement}  pulsanteLogout
 */
let pulsanteLogout = document.getElementById('logout');
/** Event listener per il pulsante
 *  chiama la funzione funzioneLogout(); una volta premuto il pulsante
 */
pulsanteLogout.addEventListener('click', () => {
    funzioneLogout();
})
/** Esegue il logout dell'utente.
 * invalida la sessione ed effettua il logout indirizzando l'utente alla pagina di login
 *  esegue una richiesta di tipo POST all'endpoint `/auth/logout`.  
 * @function
 * @returns {void}
 */
const funzioneLogout = () => {
fetch('http://localhost:8080/auth/logout', {
        method : 'POST',
        credentials : 'include',
        redirect : 'manual',
        headers : {
            'Content-Type': 'application/x-www-form-urlencoded',
        } 
    }).then(() => {
        Toastify({
            text : 'Logout effettuato con successo',
            duration : 1000, 
            position : 'center', 
            gravity : 'top', 
            style: {
                background : 'red'
            }
        }).showToast();
        setTimeout(() => {
            window.location.href = 'login.html'
        }, 1500);
    }).catch((errore) => {
        console.log(`Errore: ${errore.message}`);
    })
}