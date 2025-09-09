/** Elementi principali del DOM
 * @type {HTMLFormElement}  formLogin
 * @type {HTMLInputElement} inputEmail
 * @type {HTMLInputElement} inputPassword
 */
const formLogin = document.getElementById('form-registrazione');
const inputEmail = document.getElementById('formEmail');
const inputPassword = document.getElementById('formPassword');

/** Event listener per il form
 *  previene il caricamento automatico della pagina dato dal form
 *  chiama la funzione funzioneLogin(); una volta compilato il form
 */
formLogin.addEventListener('submit', (event) => {
    event.preventDefault();
    funzioneLogin();
})
/** Esegue il login dell'utente.
 * Recupera email e password dai campi del form, valida i dati e invia
 * una richiesta POST all'endpoint `/auth/login`. Mostra messaggi di
 * successo o errore e reindirizza l'utente in caso di successo.
 * @function
 * @returns {void}
 */
const funzioneLogin = () => {
    if (inputEmail.value === '' || inputPassword.value === '') {
        Toastify({
            text: `Attenzione! uno o più  campi sono vuoti, riprova`,
            duration: '1000',
            gravity: 'top',
            position: 'center',
            style: {
                background: 'red'
            }
        }).showToast();
        return
    } else {
        const loginUtente = {
            email: inputEmail.value,
            password: inputPassword.value
        }
        fetch('http://localhost:8080/auth/login', {
            method: 'POST',
            credentials: 'include',
            body: JSON.stringify(loginUtente),
            headers: {
                'Content-type': 'application/json'
            }
        }).then(response => response.json())
            .then(data => {
                console.log(data);
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
                    setTimeout(() => {
                        window.location.href = 'rubrica.html';
                    }, 1500);
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
                console.log(`Qualcosa è andato storto: ${errore.message}`);
            })
    }
}