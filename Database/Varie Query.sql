/* Query che restuiscono cose, in java usare il PreparedStatement */

/* Resituisce i prodotti per nome di un dato store */
SELECT prodotto_name 
FROM PRODOTTO_FORNITO 
WHERE nome_store_r = 'nomestore';

/* Restituisce 0 se l'username passato non esiste, 1 se esiste */
SELECT COUNT(*)
FROM UTENTE
WHERE username = 'nome_utente_passato';

/* Restituisce 0 se la password passata non esiste, 1 se esiste */
SELECT COUNT(*)
FROM UTENTE
WHERE password = 'password_passata'; /* La passord è in BASE64 */

/* Restituisce 0 se username e password passati non corrispondono, 1 se corrispondono */
SELECT COUNT(*)
FROM UTENTE
WHERE username = 'nome_utente_passato' && password = 'password_passata';

/* Restituisce una tabella con una colonna che contiene l'username */
SELECT username 
FROM UTENTE
WHERE username = 'nome_utente_passato';

/* Restituisce una tabella con una colonna che contiene la password di un dato username */
SELECT password 
FROM UTENTE
WHERE username = 'nome_utente_passato';


/* Restituisce come risultato una tabella con una colonna che contiene il tipo(stringa enum) di utente*/
SELECT tipo
FROM UTENTE
WHERE username = 'username_passato';

/* Inserisce un nuovo utente nel database */
INSERT INTO UTENTE
VALUES('username', 'email', 'cf', 'nome', 'cognome', 'password(BASE64)');
