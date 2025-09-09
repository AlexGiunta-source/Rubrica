/** Elementi principali del DOM
 * @type {HTMLFormElement} formCognome
 * @type {HTMLInputElement} idElimina
 */
const formEliminaContatto = document.getElementById('eliminaContatto');
const idElimina = document.getElementById('inputIdContatto');

/** Event listener per il form.
 *  previene il caricamento automatico della pagina dato dal form
 *  chiama la funzione funzioneEliminaContatto(); una volta compilato il form
 */
formEliminaContatto.addEventListener('submit', event => {
  event.preventDefault();
  funzioneEliminaContatto();
})

/** Funzione elimina contatto per id
 * Recupera i dati dal form, e invia una richiesta di tipo DELETE all'end point /eliminaContatto/
 *  mostra notifiche di successo o di errore
 * @function 
 * @returns {void}
 */
const funzioneEliminaContatto = () => {
  if (idElimina.value === '') {
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
    fetch(`http://localhost:8080/eliminaContatto/${idElimina.value}`, {
      method: 'DELETE',
      credentials: 'include',
      headers: {
        'Content-Type': 'application/json'
      }
    })
      .then(response => response.json())
      .then(data => {
        console.log(data)
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
      })
      .catch(errore => {
        console.log(`Errore: ${errore}`);
      });
  }
}