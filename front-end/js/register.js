/** Elementi principali del DOM
 * @type {HTMLFormElement}  formRegistrazione
 * @type {HTMLInputElement} inputEmail
 * @type {HTMLInputElement} inputPassword
 */
const formRegistrazione = document.getElementById('form-registrazione');
const inputEmail = document.getElementById('emailRegistrazione');
const inputPassword = document.getElementById('passwordRegistrazione');

/** Event listener per il form
 *  previene il caricamento automatico della pagina dato dal form
 *  chiama la funzione funzioneRegister(); una volta compilato il form
 */
formRegistrazione.addEventListener('submit', (event) => {
    event.preventDefault();
    funzioneRegister();
})
/** Esegue la registrazione dell'utente.
 * Recupera email e password dai campi del form, valida i dati e invia
 * una richiesta POST all'endpoint `/auth/login`. Mostra messaggi di
 * successo o errore e reindirizza l'utente in caso di successo.
 * @function
 * @returns {void}
 */
const funzioneRegister = () => {
    if (inputEmail.value === '' || inputPassword === '') {
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
        const utenteRegistrato = {
            email: inputEmail.value,
            password: inputPassword.value
        }
        fetch('http://localhost:8080/auth/register', {
            method: 'POST',
            body: JSON.stringify(utenteRegistrato),
            headers: {
                'Content-Type': 'application/json'
            }
        }).then(response => response.json().then(data => ({ response, data })))
            .then(({ response, data }) => {
                console.log(response);
                console.log(data);
                if (data.statusCode === "OK") {
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
                        window.location.href = 'login.html';
                    }, 1500); 
                }else{
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