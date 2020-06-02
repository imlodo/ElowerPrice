/* Cancella il database se già esistente */
DROP DATABASE IF EXISTS elowerprice;
/* Crea il database*/
CREATE DATABASE elowerprice;
/* Gli indica che deve usare il database elowerprice */
USE elowerprice;
/*Creo le varie entità che necessitano di salvataggio di dati(persistenza dei dati) */
CREATE TABLE UTENTE(
	username VARCHAR(64) NOT NULL,
	email VARCHAR(254) NOT NULL,
	cf VARCHAR(16) NOT NULL,
	nome VARCHAR(20) NOT NULL,
    cognome VARCHAR(20) NOT NULL,
    password VARCHAR(64) NOT NULL,
	tipo ENUM('0','1') NOT NULL,
	PRIMARY KEY (username)
)ENGINE=INNODB;

CREATE TABLE SCONTO(
	utente_username VARCHAR(64) NOT NULL,
    nome_sconto VARCHAR(30) NOT NULL,
	descr_sconto VARCHAR(300) NOT NULL,
    perc_sconto INT NOT NULL,
	PRIMARY KEY (utente_username),
	FOREIGN KEY (utente_username) REFERENCES UTENTE(username)
				ON DELETE CASCADE
				ON UPDATE CASCADE
)ENGINE=INNODB;

CREATE TABLE STORE(
    nome_store VARCHAR(30) NOT NULL,
    p_iva VARCHAR(11) NOT NULL,
    num_visite INT NOT NULL,
    logo_Store LONGBLOB,
    PRIMARY KEY (nome_store)
)ENGINE=INNODB;

CREATE TABLE CITY(
	cap VARCHAR(5) NOT NULL,
    city VARCHAR(60) NOT NULL,
    prov VARCHAR(2) NOT NULL,
    PRIMARY KEY (cap)
)ENGINE=INNODB;

CREATE TABLE INDIRIZZO_STORE(
	cap_store VARCHAR(5) NOT NULL,
    via_store VARCHAR(60) NOT NULL,
    numero_civico_store INT NOT NULL,
    nome_store_r VARCHAR(30) NOT NULL,
    PRIMARY KEY (cap_store,nome_store_r),
    FOREIGN KEY (cap_store) REFERENCES CITY(cap)
				ON DELETE CASCADE
				ON UPDATE CASCADE,
    FOREIGN KEY (nome_store_r) REFERENCES STORE(nome_store)
				ON DELETE CASCADE
				ON UPDATE CASCADE
)ENGINE=INNODB;

CREATE TABLE INDIRIZZO_UTENTE(
	cap_utente VARCHAR(5) NOT NULL,
    via_utente VARCHAR(60) NOT NULL,
    numero_civico_utente INT NOT NULL,
    PRIMARY KEY (cap_utente),
    FOREIGN KEY (cap_utente) REFERENCES CITY(cap)
				ON DELETE CASCADE
				ON UPDATE CASCADE
)ENGINE=INNODB;

CREATE TABLE RECENSIONE_STORE(
    nome_store_r VARCHAR(30) NOT NULL,
    utente_username VARCHAR(64) NOT NULL,
    commento VARCHAR(500) NOT NULL,
    num_stelle INT NOT NULL,
	PRIMARY KEY (utente_username,nome_store_r),
	FOREIGN KEY (nome_store_r) REFERENCES STORE(nome_store)
				ON DELETE CASCADE
				ON UPDATE CASCADE,
	FOREIGN KEY (utente_username) REFERENCES UTENTE(username)
				ON DELETE CASCADE
				ON UPDATE CASCADE
)ENGINE=INNODB;

CREATE TABLE PRODOTTO(
    prod_name VARCHAR(240) NOT NULL,
    num_ricerche INT NOT NULL,
    img_prodotto LONGBLOB,
    PRIMARY KEY(prod_name)
)ENGINE=INNODB;

CREATE TABLE CATEGORIA(
	nome_categoria VARCHAR(30) NOT NULL,
    PRIMARY KEY(nome_categoria)
)ENGINE=INNODB;

CREATE TABLE SPECIFICA_TECNICA(
	nome_specifica VARCHAR(30) NOT NULL,
    PRIMARY KEY(nome_specifica)
)ENGINE=INNODB;

CREATE TABLE RECENSIONE_PRODOTTO(
	prodotto_name VARCHAR(240) NOT NULL,
    utente_username VARCHAR(64) NOT NULL,
    commento VARCHAR(500) NOT NULL,
    num_stelle INT NOT NULL,
	PRIMARY KEY (utente_username,prodotto_name),
	FOREIGN KEY (utente_username) REFERENCES UTENTE(username)
				ON DELETE CASCADE
				ON UPDATE CASCADE,
	FOREIGN KEY (prodotto_name) REFERENCES PRODOTTO(prod_name)
				ON DELETE CASCADE
				ON UPDATE CASCADE
)ENGINE=INNODB;

CREATE TABLE PRODOTTO_FORNITO(
	prodotto_name VARCHAR(240) NOT NULL,
    nome_store_r VARCHAR(30) NOT NULL,
	prezzo_ieri DOUBLE PRECISION(6, 2) NOT NULL,
    prezzo_scorso_mese DOUBLE PRECISION(6, 2) NOT NULL,
    prezzo_inizio_giorno DOUBLE PRECISION(6, 2) NOT NULL,
    prezzo_attuale DOUBLE PRECISION(6, 2) NOT NULL,
    quantity INT NOT NULL,
    availability BOOL NOT NULL,
    descr_fornitore VARCHAR(9000) NOT NULL,
    opzione_acquisto ENUM('RITIRO_IN_SEDE', 'CONSEGNA_DOMICILIO') NOT NULL,
	cod_ean VARCHAR(13),
    costo_spedizione DOUBLE PRECISION(6, 2),
    link_offerta VARCHAR(256) NOT NULL,
	PRIMARY KEY (nome_store_r,prodotto_name),
	FOREIGN KEY (nome_store_r) REFERENCES STORE(nome_store)
				ON DELETE CASCADE
				ON UPDATE CASCADE,
	FOREIGN KEY (prodotto_name) REFERENCES PRODOTTO(prod_name)
				ON DELETE CASCADE
				ON UPDATE CASCADE
)ENGINE=INNODB;

CREATE TABLE PRODOTTO_INTERNO(
    prodotto_name VARCHAR(240) NOT NULL,
	prezzo DOUBLE PRECISION(6, 2) NOT NULL,
    quantity INT NOT NULL,
    availability BOOL NOT NULL,
    descr VARCHAR(9000) NOT NULL,
    opzione_acquisto ENUM('RITIRO_IN_SEDE', 'CONSEGNA_DOMICILIO') NOT NULL,
    cod_ean VARCHAR(13),
	PRIMARY KEY (prodotto_name),
	FOREIGN KEY (prodotto_name) REFERENCES PRODOTTO(prod_name)
				ON DELETE CASCADE
				ON UPDATE CASCADE
)ENGINE=INNODB;

/* Qui invece le relazioni tra le entità */
CREATE TABLE VOTA(
	utile BOOL NOT NULL,
    nome_store_recensione VARCHAR(30) NOT NULL,
    utente_username_recensione VARCHAR(64) NOT NULL,
    PRIMARY KEY (utente_username_recensione,nome_store_recensione),
	FOREIGN KEY (utente_username_recensione,nome_store_recensione) REFERENCES RECENSIONE_STORE(utente_username,nome_store_r)
				ON DELETE CASCADE
				ON UPDATE CASCADE
)ENGINE=INNODB;

CREATE TABLE ACQUISTA(
    utente_username VARCHAR(64) NOT NULL,
    prodotto_name VARCHAR(240) NOT NULL,
    prezzo_acquisto DOUBLE PRECISION(6, 2) NOT NULL,
    tipo_acquisto ENUM('RITIRO_IN_SEDE', 'CONSEGNA_DOMICILIO') NOT NULL,
    PRIMARY KEY (utente_username, prodotto_name),
	FOREIGN KEY (utente_username) REFERENCES UTENTE(username)
				ON DELETE CASCADE
				ON UPDATE CASCADE,
	FOREIGN KEY (prodotto_name) REFERENCES PRODOTTO_INTERNO(prodotto_name)
				ON DELETE CASCADE
				ON UPDATE CASCADE
)ENGINE=INNODB;

CREATE TABLE APPARTIENE_A(
   prodotto_name VARCHAR(240) NOT NULL,
   categoria_name VARCHAR(30) NOT NULL,
   PRIMARY KEY (prodotto_name, categoria_name),
   FOREIGN KEY (prodotto_name) REFERENCES PRODOTTO(prod_name)
				ON DELETE CASCADE
				ON UPDATE CASCADE,
   FOREIGN KEY (categoria_name) REFERENCES CATEGORIA(nome_categoria)
				ON DELETE CASCADE
				ON UPDATE CASCADE
)ENGINE=INNODB;

CREATE TABLE POSSIEDE(
   prodotto_name VARCHAR(240) NOT NULL,
   specifica_name VARCHAR(30) NOT NULL,
   desc_specifica VARCHAR(1000) NOT NULL,
   PRIMARY KEY (prodotto_name, specifica_name),
   FOREIGN KEY (prodotto_name) REFERENCES PRODOTTO(prod_name)
				ON DELETE CASCADE
				ON UPDATE CASCADE,
   FOREIGN KEY (specifica_name) REFERENCES SPECIFICA_TECNICA(nome_specifica)
				ON DELETE CASCADE
				ON UPDATE CASCADE
)ENGINE=INNODB;

CREATE TABLE ABITA(
	utente_cap VARCHAR(5) NOT NULL,
    utente_username VARCHAR(64) NOT NULL,
    PRIMARY KEY (utente_username,utente_cap),
	FOREIGN KEY (utente_username) REFERENCES UTENTE(username)
				ON UPDATE CASCADE,
	FOREIGN KEY (utente_cap) REFERENCES INDIRIZZO_UTENTE(cap_utente)
				ON DELETE CASCADE
				ON UPDATE CASCADE
)ENGINE=INNODB;
