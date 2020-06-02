/* Script popola database */
USE elowerprice;
/*Mi serve per poter popolare le immagini */
SHOW VARIABLES LIKE "secure_file_priv";
/*select LOAD_FILE('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/Logo IKEA.png');*/
SET @PATHTONY = 'C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/'; 

/* Query crea utente */
INSERT INTO UTENTE
VALUES('demo1','progettoTsw@outlook.it','0123456789ABCEFG', 'Antonello', 'Bello', 'c2lhbW9iZWxsaQ==', '0'); 
/* Amministratore */
INSERT INTO UTENTE
VALUES('admin','admin@admin.it','0523456789ABCEFG', 'Admin', 'Admin', 'c2lhbW9iZWxsaQ==', '1'); 
/* I campi in questo ordine -> username, email, cf, nome, cognome, password(BASE64) */

/* Query crea sconto */
INSERT INTO SCONTO
VALUES('demo1','Sconto_Bello','Sconto riservato ai clienti belli',30);
/* I campi in questo ordine -> utente_username, nome_sconto, descr_sconto, percentuale_sconto */

/* Query crea store */
SET @IMGPATH = CONCAT(@PATHTONY, 'logo-trony.png');
INSERT INTO STORE
VALUES('Trony', '11582600158', 0, LOAD_FILE(@IMGPATH));
SET @IMGPATH = CONCAT(@PATHTONY, 'Mediaworld.png');
INSERT INTO STORE
VALUES('MediaWorld', '02630120166', 0, LOAD_FILE(@IMGPATH));
SET @IMGPATH = CONCAT(@PATHTONY, 'expert.png');
INSERT INTO STORE
VALUES('Expert','08576060969', 0, LOAD_FILE(@IMGPATH));
SET @IMGPATH = CONCAT(@PATHTONY, 'amazon.png');
INSERT INTO STORE
VALUES('Amazon','08973230967',0, LOAD_FILE(@IMGPATH));
SET @IMGPATH = CONCAT(@PATHTONY, 'ebay.png');
INSERT INTO STORE
VALUES('Ebay','DE200081785',0, LOAD_FILE(@IMGPATH));
/* I campi in questo ordine -> partita_iva, nome_store, num_visite(inizialmente 0 per ovvie ragioni), logoStore(anche null) */

/* Query crea la città */
INSERT INTO CITY
VALUES('20143', 'Milano','MI');
INSERT INTO CITY
VALUES('20124', 'Milano','MI');
INSERT INTO CITY
VALUES('20843', 'Verano Brianza', 'MB');
INSERT INTO CITY
VALUES('20017', 'Rho', 'MI');
INSERT INTO CITY
VALUES('14532', 'Kleinmachnow','PM');
/* I campi in questo ordine-> cap, nome_città, provincia_sigla) */


/* Query crea indirizzo store */
INSERT INTO INDIRIZZO_STORE
VALUES('20143','Viale Cassala', 28, 'Trony');
INSERT INTO INDIRIZZO_STORE
VALUES('20843','Via Furlanelli', 69, 'MediaWorld');
INSERT INTO INDIRIZZO_STORE
VALUES('20017','Via Sesia', 5, 'Expert');
INSERT INTO INDIRIZZO_STORE
VALUES('20124','Viale Monte Grappa', 3, 'Amazon');
INSERT INTO INDIRIZZO_STORE
VALUES('14532','Albert-Einstein-Ring', 2, 'Ebay');
/* I campi in questo ordine -> cap_store(deve essere uguale ad uno di un indirizzo esistente), via_store, numero_civico_store, nome_store) */ 

/* Query crea indirizzo utente */
INSERT INTO INDIRIZZO_UTENTE
VALUES('20843','Via Luppolo', 12);
/* I campi in questo ordine -> cap_utente(deve essere uguale ad uno di un indirizzo esistente), via_utente, numero_civico_utente */

/* Query che crea la relazione tra utente e il suo indirizzo */
INSERT INTO ABITA
VALUES('20843','demo1');
/* I campi in questo ordine -> utente_cap, utente_username */ 

/* Query che crea una recensione per un dato store */
INSERT INTO RECENSIONE_STORE
VALUES('Trony', 'demo1', 'Store bellissimo, prodotti a prezzi ottimi! Consigliato da Antonello Luppolo', 5);
/* I campi in questo ordine -> nome_store, username_utente, commento, num_stelle */

/* Query che crea un prodotto */
/*Informatica*/
/*Notebook*/
SET @IMGPATH = CONCAT(@PATHTONY, 'Mediacom_M_SB145S1.png');
INSERT INTO PRODOTTO
VALUES('Mediacom M-SB145S1',0,LOAD_FILE(@IMGPATH));
SET @IMGPATH = CONCAT(@PATHTONY, 'notebookAsus.png');
INSERT INTO PRODOTTO
VALUES('ASUS X540NA-GQ017T', 0, LOAD_FILE(@IMGPATH));
/*VideoGame Console*/
SET @IMGPATH = CONCAT(@PATHTONY, 'ps4.png');
INSERT INTO PRODOTTO
VALUES('SONY ENTERTAINMENT PS4 500GB F CHASSIS BLACK',0,LOAD_FILE(@IMGPATH));
SET @IMGPATH = CONCAT(@PATHTONY, 'nintendo2ds.png');
INSERT INTO PRODOTTO
VALUES('NINTENDO 2DS XL 2209349', 0, LOAD_FILE(@IMGPATH));
SET @IMGPATH = CONCAT(@PATHTONY, 'nintendoSwitch.png');
INSERT INTO PRODOTTO
VALUES('NINTENDO SWITCH 2500166', 0, LOAD_FILE(@IMGPATH));
/*Scarpe*/
SET @IMGPATH = CONCAT(@PATHTONY, 'adidasScarpeDonna.png');
INSERT INTO PRODOTTO
VALUES('Adidas Superstar 80s W Scarpe da Donna. Sneakers', 0, LOAD_FILE(@IMGPATH));
SET @IMGPATH = CONCAT(@PATHTONY, 'scarpeNike.png');
INSERT INTO PRODOTTO
VALUES('Nike Tanjun, Scarpe da Ginnastica Basse Uomo', 0, LOAD_FILE(@IMGPATH));
SET @IMGPATH = CONCAT(@PATHTONY, 'converse.png');
INSERT INTO PRODOTTO
VALUES('CONVERSE ALL STAR HI SCARPE SPORTIVE ALTE BIANCHE 7650', 0, LOAD_FILE(@IMGPATH));
/*Smartphone*/
SET @IMGPATH = CONCAT(@PATHTONY, 'iphonenero.png');
INSERT INTO PRODOTTO
VALUES('Apple iPhone 7 Plus Nero (Nero Opaco) 128GB', 0, LOAD_FILE(@IMGPATH));
/*Tablet*/
SET @IMGPATH = CONCAT(@PATHTONY, 'tabletFire.png');
INSERT INTO PRODOTTO
VALUES('Tablet Fire HD 8', 0, LOAD_FILE(@IMGPATH));
SET @IMGPATH = CONCAT(@PATHTONY, 'tabletLenovo.png');
INSERT INTO PRODOTTO
VALUES('LENOVO TB-7304F ZA300141DE', 0, LOAD_FILE(@IMGPATH));
SET @IMGPATH = CONCAT(@PATHTONY, 'smartpad.png');
INSERT INTO PRODOTTO
VALUES('MEDIACOM SmartPad iyo10', 0, LOAD_FILE(@IMGPATH));
SET @IMGPATH = CONCAT(@PATHTONY, 'tablet samsung.png');
INSERT INTO PRODOTTO
VALUES('SAMSUNG SM-T510 SILVER', 0, LOAD_FILE(@IMGPATH));
SET @IMGPATH = CONCAT(@PATHTONY, 'iPad.png');
INSERT INTO PRODOTTO
VALUES('APPLE iPad Pro (12.9)', 0, LOAD_FILE(@IMGPATH));
/*Fotocamere*/
SET @IMGPATH = CONCAT(@PATHTONY, 'canon500D.png');
INSERT INTO PRODOTTO
VALUES('Canon EOS 500D fotocamera digitale', 0, LOAD_FILE(@IMGPATH));
/*T-Shirt*/
SET @IMGPATH = CONCAT(@PATHTONY, 't-shirt-levis.png');
INSERT INTO PRODOTTO
VALUES("Levi's Graphic Set-in Neck, T-Shirt Uomo", 0, LOAD_FILE(@IMGPATH));
SET @IMGPATH = CONCAT(@PATHTONY, 'magliaBecause.png');
INSERT INTO PRODOTTO
VALUES('Settantallora - T-Shirt Maglietta J624', 0, LOAD_FILE(@IMGPATH));
SET @IMGPATH = CONCAT(@PATHTONY, 'magliaNike.png');
INSERT INTO PRODOTTO
VALUES('Nike Park Vi, Maglietta Uomo', 0, LOAD_FILE(@IMGPATH));
SET @IMGPATH = CONCAT(@PATHTONY, 'magliaNapoli.png');
INSERT INTO PRODOTTO
VALUES('SSC napoli Maglia Gara Home 2019/2020 Uomo', 0, LOAD_FILE(@IMGPATH));
SET @IMGPATH = CONCAT(@PATHTONY, 'poloLacoste.png');
INSERT INTO PRODOTTO
VALUES('Lacoste Abbigliamento LACOSTE POLO PIQUET BORDEAUX', 0, LOAD_FILE(@IMGPATH));
SET @IMGPATH = CONCAT(@PATHTONY, 'magliakappa.png');
INSERT INTO PRODOTTO
VALUES('Kappa T-Shirts & Top Uomo Logo Amberis Allenamento T-Shirt', 0, LOAD_FILE(@IMGPATH));
SET @IMGPATH = CONCAT(@PATHTONY, 'magliaAdidas.png');
INSERT INTO PRODOTTO
VALUES('ADIDAS TREFOIL T-SHIRT UOMO GRIGIA', 0, LOAD_FILE(@IMGPATH));
/*Condizionatori*/
SET @IMGPATH = CONCAT(@PATHTONY, 'dakin.png');
INSERT INTO PRODOTTO
VALUES('DAIKIN ATXC25AARXCAKIT', 0, LOAD_FILE(@IMGPATH));
/*Hard disk*/
SET @IMGPATH = CONCAT(@PATHTONY, 'wdhard.png');
INSERT INTO PRODOTTO
VALUES('Western Digital WDBH2D0010HNC', 0, LOAD_FILE(@IMGPATH));
/*Monitor*/
SET @IMGPATH = CONCAT(@PATHTONY, 'monitorAcer.png');
INSERT INTO PRODOTTO
VALUES('ACER ET430KWMIIPPX', 0, LOAD_FILE(@IMGPATH));
/*Router*/
SET @IMGPATH = CONCAT(@PATHTONY, 'netGear.png');
INSERT INTO PRODOTTO
VALUES('NETGEAR R6220', 0, LOAD_FILE(@IMGPATH));
/*Mouse*/
SET @IMGPATH = CONCAT(@PATHTONY, 'mouseHp.png');
INSERT INTO PRODOTTO
VALUES('HP X900', 0, LOAD_FILE(@IMGPATH));
/*Strumenti a corde, strumenti musicali*/
SET @IMGPATH = CONCAT(@PATHTONY, 'chitarra.png');
INSERT INTO PRODOTTO
VALUES('Yamaha C40II Chitarra Classica', 0, LOAD_FILE(@IMGPATH));
/*Felpe*/
SET @IMGPATH = CONCAT(@PATHTONY, 'felpaSalerno.png');
INSERT INTO PRODOTTO
VALUES('Settantallora - Felpa con Cappuccio KJ1755', 0, LOAD_FILE(@IMGPATH));
SET @IMGPATH = CONCAT(@PATHTONY, 'felpaNike.png');
INSERT INTO PRODOTTO
VALUES('Nike M NSW FZ FLC Club Felpa con cappuccio, uomo', 0, LOAD_FILE(@IMGPATH));
/*Cappelli*/
SET @IMGPATH = CONCAT(@PATHTONY, 'cappellinoNike.png');
INSERT INTO PRODOTTO
VALUES('Nike H86 cap Metal Swoosh', 0, LOAD_FILE(@IMGPATH));
/*Leggins*/
SET @IMGPATH = CONCAT(@PATHTONY, 'leggins.png');
INSERT INTO PRODOTTO
VALUES('Homebaby Vintage Yoga Leggings Sportivi Donna', 0, LOAD_FILE(@IMGPATH));
/* I campi in questo ordine-> nome_prodotto, num_visite, img_prodotto)*/

/* Da qui creo quello che serve per creare le relazioni tra podotto-categoria e prodotto-specifica_tecnica */

/* Query che crea una categoria */
INSERT INTO CATEGORIA
VALUES('scarpe');
INSERT INTO CATEGORIA
VALUES('smartphone');
INSERT INTO CATEGORIA
VALUES('tablet');
INSERT INTO CATEGORIA
VALUES('fotocamere');
INSERT INTO CATEGORIA
VALUES('magliette');
INSERT INTO CATEGORIA
VALUES('condizionatori');
INSERT INTO CATEGORIA
VALUE('notebook');
INSERT INTO CATEGORIA
VALUE('console videogiochi');
INSERT INTO CATEGORIA
VALUE('informatica');
INSERT INTO CATEGORIA
VALUE('t-shirt');
INSERT INTO CATEGORIA
VALUE('hard-disk');
INSERT INTO CATEGORIA
VALUE('monitor');
INSERT INTO CATEGORIA
VALUE('router');
INSERT INTO CATEGORIA
VALUE('mouse');
INSERT INTO CATEGORIA
VALUE('strumenti a corde');
INSERT INTO CATEGORIA
VALUE('strumenti musicali');
INSERT INTO CATEGORIA
VALUE('felpe');
INSERT INTO CATEGORIA
VALUE('cappelli');
INSERT INTO CATEGORIA
VALUE('leggins');
/* I campi in questo ordine-> nome_categoria) */

/* Query che crea le specifiche tecniche */
INSERT INTO SPECIFICA_TECNICA
VALUES('Fotocamera');
INSERT INTO SPECIFICA_TECNICA
VALUES('Materiale esterno');
INSERT INTO SPECIFICA_TECNICA
VALUES('Fodera');
INSERT INTO SPECIFICA_TECNICA
VALUES('Marca');
INSERT INTO SPECIFICA_TECNICA
VALUES('Dimensioni');
INSERT INTO SPECIFICA_TECNICA
VALUES('Peso');
INSERT INTO SPECIFICA_TECNICA
VALUES('');
INSERT INTO SPECIFICA_TECNICA
VALUES('Marca processore');
INSERT INTO SPECIFICA_TECNICA
VALUES('Sigla Processore');
INSERT INTO SPECIFICA_TECNICA
VALUES('Capacità hard-disk (GB)');
INSERT INTO SPECIFICA_TECNICA
VALUES('Capacità RAM (GB)');
INSERT INTO SPECIFICA_TECNICA
VALUES('Peso (Kg)');
INSERT INTO SPECIFICA_TECNICA
VALUES('Tipologia di PC');
INSERT INTO SPECIFICA_TECNICA
VALUES('Sistema operativo');
INSERT INTO SPECIFICA_TECNICA
VALUES('Scheda grafica');
INSERT INTO SPECIFICA_TECNICA
VALUES('Tipologia console');
/* I campi in questo ordine-> nome_specifica */

/* Query che crea la relazione tra prodotto e categoria */
INSERT INTO APPARTIENE_A
VALUES('Mediacom M-SB145S1','notebook');
INSERT INTO APPARTIENE_A
VALUES('Mediacom M-SB145S1','informatica');
INSERT INTO APPARTIENE_A
VALUES('ASUS X540NA-GQ017T','notebook');
INSERT INTO APPARTIENE_A
VALUES('ASUS X540NA-GQ017T','informatica');
INSERT INTO APPARTIENE_A
VALUES('SONY ENTERTAINMENT PS4 500GB F CHASSIS BLACK','console videogiochi');
INSERT INTO APPARTIENE_A
VALUES('SONY ENTERTAINMENT PS4 500GB F CHASSIS BLACK','informatica');
INSERT INTO APPARTIENE_A
VALUES('NINTENDO 2DS XL 2209349','console videogiochi');
INSERT INTO APPARTIENE_A
VALUES('NINTENDO 2DS XL 2209349','informatica');
INSERT INTO APPARTIENE_A
VALUES('NINTENDO SWITCH 2500166','console videogiochi');
INSERT INTO APPARTIENE_A
VALUES('NINTENDO SWITCH 2500166','informatica');
INSERT INTO APPARTIENE_A
VALUES('Adidas Superstar 80s W Scarpe da Donna. Sneakers','scarpe');
INSERT INTO APPARTIENE_A
VALUES('Nike Tanjun, Scarpe da Ginnastica Basse Uomo','scarpe');
INSERT INTO APPARTIENE_A
VALUES('CONVERSE ALL STAR HI SCARPE SPORTIVE ALTE BIANCHE 7650','scarpe');
INSERT INTO APPARTIENE_A
VALUES('Apple iPhone 7 Plus Nero (Nero Opaco) 128GB','smartphone');
INSERT INTO APPARTIENE_A
VALUES('Apple iPhone 7 Plus Nero (Nero Opaco) 128GB','informatica');
INSERT INTO APPARTIENE_A
VALUES('Tablet Fire HD 8','tablet');
INSERT INTO APPARTIENE_A
VALUES('Tablet Fire HD 8','informatica');
INSERT INTO APPARTIENE_A
VALUES('LENOVO TB-7304F ZA300141DE','tablet');
INSERT INTO APPARTIENE_A
VALUES('LENOVO TB-7304F ZA300141DE','informatica');
INSERT INTO APPARTIENE_A
VALUES('MEDIACOM SmartPad iyo10','tablet');
INSERT INTO APPARTIENE_A
VALUES('MEDIACOM SmartPad iyo10','informatica');
INSERT INTO APPARTIENE_A
VALUES('SAMSUNG SM-T510 SILVER','tablet');
INSERT INTO APPARTIENE_A
VALUES('SAMSUNG SM-T510 SILVER','informatica');
INSERT INTO APPARTIENE_A
VALUES('APPLE iPad Pro (12.9)','tablet');
INSERT INTO APPARTIENE_A
VALUES('APPLE iPad Pro (12.9)','informatica');
INSERT INTO APPARTIENE_A
VALUES('Canon EOS 500D fotocamera digitale','fotocamere');
INSERT INTO APPARTIENE_A
VALUES('Canon EOS 500D fotocamera digitale','informatica');
INSERT INTO APPARTIENE_A
VALUES("Levi's Graphic Set-in Neck, T-Shirt Uomo",'t-shirt');
INSERT INTO APPARTIENE_A
VALUES("Settantallora - T-Shirt Maglietta J624",'t-shirt');
INSERT INTO APPARTIENE_A
VALUES("Nike Park Vi, Maglietta Uomo",'t-shirt');
INSERT INTO APPARTIENE_A
VALUES("SSC napoli Maglia Gara Home 2019/2020 Uomo",'t-shirt');
INSERT INTO APPARTIENE_A
VALUES("Lacoste Abbigliamento LACOSTE POLO PIQUET BORDEAUX",'t-shirt');
INSERT INTO APPARTIENE_A
VALUES("Kappa T-Shirts & Top Uomo Logo Amberis Allenamento T-Shirt",'t-shirt');
INSERT INTO APPARTIENE_A
VALUES("ADIDAS TREFOIL T-SHIRT UOMO GRIGIA",'t-shirt');
INSERT INTO APPARTIENE_A
VALUES('DAIKIN ATXC25AARXCAKIT','condizionatori');
INSERT INTO APPARTIENE_A
VALUES('DAIKIN ATXC25AARXCAKIT','informatica');
INSERT INTO APPARTIENE_A
VALUES('Western Digital WDBH2D0010HNC','hard-disk');
INSERT INTO APPARTIENE_A
VALUES('Western Digital WDBH2D0010HNC','informatica');
INSERT INTO APPARTIENE_A
VALUES('ACER ET430KWMIIPPX','monitor');
INSERT INTO APPARTIENE_A
VALUES('ACER ET430KWMIIPPX','informatica');
INSERT INTO APPARTIENE_A
VALUES('NETGEAR R6220','router');
INSERT INTO APPARTIENE_A
VALUES('NETGEAR R6220','informatica');
INSERT INTO APPARTIENE_A
VALUES('HP X900','mouse');
INSERT INTO APPARTIENE_A
VALUES('HP X900','informatica');
INSERT INTO APPARTIENE_A
VALUES('Yamaha C40II Chitarra Classica','strumenti a corde');
INSERT INTO APPARTIENE_A
VALUES('Yamaha C40II Chitarra Classica','strumenti musicali');
INSERT INTO APPARTIENE_A
VALUES('Settantallora - Felpa con Cappuccio KJ1755','felpe');
INSERT INTO APPARTIENE_A
VALUES('Nike M NSW FZ FLC Club Felpa con cappuccio, uomo','felpe');
INSERT INTO APPARTIENE_A
VALUES('Nike H86 cap Metal Swoosh','cappelli');
INSERT INTO APPARTIENE_A
VALUES('Homebaby Vintage Yoga Leggings Sportivi Donna','leggins');
/* I campi in questo ordine-> prodotto_name, categoria_name */

/* Query che crea la relazione tra prodotto e specifica_tecnica */
INSERT INTO POSSIEDE
VALUE('Apple iPhone 7 Plus Nero (Nero Opaco) 128GB', 'Fotocamera', '12 MPX');
INSERT INTO POSSIEDE
VALUE('Mediacom M-SB145S1', 'Marca processore', 'Intel');
INSERT INTO POSSIEDE
VALUE('Mediacom M-SB145S1', 'Sigla Processore', 'Celeron N3350');
INSERT INTO POSSIEDE
VALUE('Mediacom M-SB145S1', 'Sistema operativo', 'Windows 10');
INSERT INTO POSSIEDE
VALUE('SONY ENTERTAINMENT PS4 500GB F CHASSIS BLACK', 'Tipologia console', 'Fissa');
INSERT INTO POSSIEDE
VALUES('Adidas Superstar 80s W Scarpe da Donna. Sneakers','Materiale Esterno', 'pelle');
INSERT INTO POSSIEDE
VALUES('Adidas Superstar 80s W Scarpe da Donna. Sneakers','Fodera', 'tessuto');
INSERT INTO POSSIEDE
VALUES('Tablet Fire HD 8', 'Dimensioni', '215 x 128 x 9,7 mm');
INSERT INTO POSSIEDE
VALUES('Canon EOS 500D fotocamera digitale', 'Peso', '481 g');
INSERT INTO POSSIEDE
VALUES("Levi's Graphic Set-in Neck, T-Shirt Uomo", '', "Levi's Bianca Red Tab Crew Neck T-Shirt.");
INSERT INTO POSSIEDE
VALUES('DAIKIN ATXC25AARXCAKIT', '', 'classe caldo A+ fredda A++');
/* I campi in questo ordine-> nome_prodotto, nome_specifica, descr_specifica */

/* Query che crea la recensione_prodotto */
INSERT INTO RECENSIONE_PRODOTTO
VALUES('Mediacom M-SB145S1', 'demo1', 'Notebook nella media.', 3);
/* I campi in questo ordine -> name_prodotto, username_utente, commento, num_stelle */

/* Query che crea il prodotto fornito */
INSERT INTO PRODOTTO_FORNITO
VALUES('Mediacom M-SB145S1','Trony', 199.99, 230.00, 199.00, 199.00, 10, TRUE, 'Intel - Celeron N3350 - Capacità RAM (GB) 4 - Capacità hard-disk (GB) 32 - Capacità SSD (GB) 120', 'CONSEGNA_DOMICILIO', NULL, NULL, 'https://www.trony.it/online/informatica/computer-portatili/notebook/mediacom-m-sb145s1_sku-2190004318#ProductTabs');
INSERT INTO PRODOTTO_FORNITO
VALUES('SONY ENTERTAINMENT PS4 500GB F CHASSIS BLACK','Trony', 245.00, 259.99, 245.00, 245.00, 99, TRUE, "PS4 500GB F Chassis Black PACKAGE 3D Disponibile in Italia dal 16 settembre 2016, il nuovo modello più sottile e leggero del sistema PlayStation®4 (PS4?) (serie CUH-2000), ha un volume ridotto del 30% rispetto ai modelli precedenti e un peso diminuito del 25% rispetto al modello originale (serie CUH-1000) e del 16% rispetto al secondo modello (serie CUH-1200). Il nuovo sistema PS4 è inoltre più efficiente dal punto di vista energetico, dato che il consumo di elettricità è stato tagliato di più del 34% rispetto al modello originale e del 28% rispetto al secondo modello. Con lo sguardo rivolto al futuro della tecnologia dell'immagine, inoltre, tutti i sistemi PS4, compreso il nuovo modello più sottile e PlayStation®4 Pro supportano (tramite aggiornamento del software di sistema) la tecnologia HDR (High Dynamic Range)*¹, che consente una migliore riproduzione di luci e ombre e offre una gamma molto più ampia di colori. I possessori di una TV compatibile con lo stardad HDR potranno godersi giochi e altri contenuti supportati dalle immagini più realistiche, straordinariamente vivide e più fedeli al modo in cui effettivamente l'occhio umano vede il mondo.", 'CONSEGNA_DOMICILIO', NULL, 9.99, 'https://www.trony.it/online/console-giochi-tempo-libero/console/play-station-4/sony-entertainment-ps4-500gb-f-chassis-black_sku-2180021216');
INSERT INTO PRODOTTO_FORNITO
VALUES('Adidas Superstar 80s W Scarpe da Donna. Sneakers','Trony', 55.50, 59.99, 55.50, 52.50, 10, TRUE, 'SCARPE BELLE BELLISSIME', 'CONSEGNA_DOMICILIO', NULL, NULL, 'trony.it');
INSERT INTO PRODOTTO_FORNITO
VALUES('Tablet Fire HD 8','Trony', 85.50, 89.99, 95.50, 102.50, 5, TRUE, 'Tablet molto efficiente', 'RITIRO_IN_SEDE', NULL, NULL, 'trony.it');
INSERT INTO PRODOTTO_FORNITO
VALUES('Canon EOS 500D fotocamera digitale', 'MediaWorld', 685.50, 689.99, 695.50, 692.50, 8, TRUE, 'Tablet molto efficiente', 'CONSEGNA_DOMICILIO', NULL, NULL, 'google.com');
INSERT INTO PRODOTTO_FORNITO
VALUES('Apple iPhone 7 Plus Nero (Nero Opaco) 128GB', 'MediaWorld', 485.50, 389.99, 395.50, 472.50, 3, TRUE, "IPHONE 7 Tutto ciò che rende iPhone straordinario oggi fa uno straordinario passo avanti, con iPhone 7. Un nuovo, evoluto sistema di fotocamere. Più potenza e autonomia di ogni altro iPhone. Altoparlanti stereo per un suono più avvolgente. Un display più luminoso e più ricco di colori che mai. Protezione dall’acqua e dagli schizzi.", 'CONSEGNA_DOMICILIO', NULL, 4.99, 'https://www.mediaworld.it/product/p-726324/apple-iphone-7-32gb-nero-opaco');
INSERT INTO PRODOTTO_FORNITO
VALUES('Apple iPhone 7 Plus Nero (Nero Opaco) 128GB', 'Expert', 500.50, 499.99, 495.50, 492.50, 3, FALSE, "Sistema operativo iOS 10 - Display Retina HD 4,7 Pollici IPS - Risoluzione 1334x750 pixel a 326 ppi - Memoria 32 GB ", 'CONSEGNA_DOMICILIO', NULL, NULL, 'https://www.euronics.it/smartphone/tim/apple-iphone-7-32gb/eProd162015124/');
INSERT INTO PRODOTTO_FORNITO
VALUES('Apple iPhone 7 Plus Nero (Nero Opaco) 128GB', 'Ebay', 269.00, 299.99, 269.00, 269.00, 2, TRUE, "APPLE IPHONE 7 128GB MATTE BLACK NERO OPACO ricondizionato Grado A/B. Testato e perfettamente funzionante in tutte le sue componenti compreso il Touch ID. Spedito in 24h dall'ordine completo di scatola, alimentatore e cavo di connessione/ricarica.", 'CONSEGNA_DOMICILIO', NULL, NULL, 'https://www.ebay.it/itm/APPLE-IPHONE-7-128GB-MATTE-BLACK-NERO-OPACO-RICONDIZIONATO-GARANTITO/183258124776?hash=item2aab0911e8:g:298AAOSwZVJaz4Av');
INSERT INTO PRODOTTO_FORNITO
VALUES('Apple iPhone 7 Plus Nero (Nero Opaco) 128GB', 'Amazon', 468.63, 468.63, 468.63, 468.63, 1, TRUE, "APPLE IPHONE 7 PLUS 128GB BLACK RICONDIZIONATO CERTIFICATO GRADO A", 'CONSEGNA_DOMICILIO', NULL, NULL, 'https://www.amazon.it/Apple-iPhone-Plus-Opaco-Ricondizionato/dp/B06X1FCPR6/ref=sr_1_1?__mk_it_IT=%C3%85M%C3%85%C5%BD%C3%95%C3%91&keywords=APPLE+IPHONE+7+plus+128gb&qid=1564309675&s=gateway&sr=8-1');
INSERT INTO PRODOTTO_FORNITO
VALUES("Levi's Graphic Set-in Neck, T-Shirt Uomo", 'Expert', 15.50, 19.99, 15.50, 19.50, 23, TRUE, 'Levis sempre maglie al top', 'CONSEGNA_DOMICILIO', NULL, NULL, 'google.com');
INSERT INTO PRODOTTO_FORNITO
VALUES('DAIKIN ATXC25AARXCAKIT', 'Expert', 285.50, 289.99, 295.50, 292.50, 25, TRUE, 'Condizionatore per rinfrescarti in estate', 'CONSEGNA_DOMICILIO', NULL, NULL, 'google.com');
INSERT INTO PRODOTTO_FORNITO
VALUES('ASUS X540NA-GQ017T','Trony',341.00,341.00,299.00,299.00,10,TRUE,'Intel - Intel® Celeron®, N3350 (1.1 GHz) - Capacità RAM (GB) 4 - Capacità hard-disk (GB) 500 - Modello scheda grafica Intel® HD Graphics 500 Memoria: Condivisa Uscite: HDMI - Dimensione monitor (pollici) 15.600','CONSEGNA_DOMICILIO',NULL,NULL,'https://www.trony.it/online/informatica/computer-portatili/notebook/asus-x540na-gq017t_sku-2180018021');
INSERT INTO PRODOTTO_FORNITO
VALUES('ASUS X540NA-GQ017T','Amazon',369.00,369.00,369.00,199.98,1,TRUE,'Asus Laptop X540NA-GQ017T, 15.6 pollici HD LED, Processore Intel N3350, RAM 4 GB, Hard Disk 500GB, S-Multi DL, Windows 10.', 'CONSEGNA_DOMICILIO', NULL, 7.00, 'https://www.amazon.it/Asus-X540NA-GQ017T-Notebook-Processore-Chocolate/dp/B0796V7B7H/ref=sr_1_3?__mk_it_IT=%C3%85M%C3%85%C5%BD%C3%95%C3%91&keywords=ASUS+X540NA-GQ017T&qid=1564323715&s=gateway&sr=8-3');
INSERT INTO PRODOTTO_FORNITO
VALUES('ASUS X540NA-GQ017T','Ebay',229.00,229.00,229.00,229.00,10,TRUE,'Notebook VivoBook 15 X540NA con Processore : Intel Celeron N3350 Dual Core, 1.1 GHz frequenza di burst 2.4 GHz. Ram : 4 GB, LPDDR3. Storage : 500 GB HDD (Hard Disk Drive) SATA, 5400 rpm. Scheda grafica integrata : Intel HD Graphics 500. Unità ottica : DVD Super Multi DL. Webcam VGA frontale da 0.5 MP. Connettività Wireless LAN 802.11b / g / n e Bluetooth 4.0. Porte USB : 1 x 3.0, 2 x 2.0. Sistema operativo : Windows 10 Home, 64 bit. Monitor da 15.6" LED con risoluzione 1366x768 HD.', 'CONSEGNA_DOMICILIO', NULL, NULL, 'https://www.ebay.it/itm/Notebook-ASUS-X540NA-gq017t-N3350-500Gb-4Gb-Ram-64-bit/183844253384?hash=item2acdf8aec8:g:DHsAAOSwWz1c~8MB');
INSERT INTO PRODOTTO_FORNITO
VALUES('NINTENDO 2DS XL 2209349','Expert',156.00,156.00,156.00,156.00,5,TRUE,'console portatile wi-fi, micro sd / micro sdhc, display 4,88" touch, fotocamera, pad scorrevole, colore bianco-arancione','CONSEGNA_DOMICILIO', NULL, 4.90, 'https://www.expertonline.it/it-IT-it/Nintendo-2DS-XL-2209349_NTX14004.aspx');
INSERT INTO PRODOTTO_FORNITO
VALUES('NINTENDO 2DS XL 2209349','Ebay',123.00,123.00,123.00,123.00,10,TRUE,'NEW NINTENDO 2DS XL BIANCO ARANCIONE NUOVA SIGILLATA ORIGINALE','CONSEGNA_DOMICILIO', NULL, NULL,'https://www.ebay.it/itm/NEW-NINTENDO-2DS-XL-BIANCO-ARANCIONE-NUOVA-SIGILLATA-ORIGINALE/254306365063?hash=item3b35d72e87:g:p0MAAOSwiwZcD6W2');
INSERT INTO PRODOTTO_FORNITO
VALUES('NINTENDO 2DS XL 2209349','Amazon',119.00,119.00,119.00,119.00,1,TRUE,'Design ergonomico a conchiglia, più leggera e con la stessa potenza di New Nintendo 3DS XL','CONSEGNA_DOMICILIO', NULL, NULL, 'https://www.amazon.it/New-Nintendo-2DS-Bianco-Arancione/dp/B071HFQNJ8/ref=sr_1_5?__mk_it_IT=%C3%85M%C3%85%C5%BD%C3%95%C3%91&keywords=NINTENDO+2DS+XL&qid=1564324516&s=gateway&sr=8-5');
INSERT INTO PRODOTTO_FORNITO
VALUES('NINTENDO SWITCH 2500166','Expert',324.00,324.00,324.00,324.00,10,TRUE,'console portatile wi-fi','CONSEGNA_DOMICILIO', NULL, NULL,'https://www.expertonline.it/it-IT-it/Nintendo-2500166_NTNI2005.aspx');
INSERT INTO PRODOTTO_FORNITO
VALUES('NINTENDO SWITCH 2500166','Ebay',269.99,269.99,269.99,269.99,17,TRUE,'CONSOLE NINTENDO SWITCH SCHERMO 6,2" CON 2 JOYPAD GRIGI GARANZIA UFFICIALE ITA','CONSEGNA_DOMICILIO', NULL, NULL, 'https://www.ebay.it/itm/CONSOLE-NINTENDO-SWITCH-SCHERMO-6-2-CON-2-JOYPAD-GRIGI-GARANZIA-UFFICIALE-ITA/142381074212?hash=item212692c724:g:aU4AAOSww9xZFd31');
INSERT INTO PRODOTTO_FORNITO
VALUES('NINTENDO SWITCH 2500166','Amazon',329.99,329.99,329.99,304.49,30,TRUE,'La confezione contiene: Console Nintendo Switch, la base per Nintendo Switch, un Joy-Con sinistro (blu neon), un Joy-Con destro (rosso neon), impugnatura Joy-Con, alimentatore, un set di laccetti per Joy-Con e cavo HDMI','CONSEGNA_DOMICILIO',NULL,5.99, 'https://www.amazon.it/Nintendo-Switch-Blu-Rosso-Neon/dp/B01N5OPMJW/ref=sr_1_fkmr0_2?__mk_it_IT=%C3%85M%C3%85%C5%BD%C3%95%C3%91&keywords=NINTENDO+SWITCH+2500166&qid=1564325216&s=gateway&sr=8-2-fkmr0');
INSERT INTO PRODOTTO_FORNITO
VALUES('NINTENDO SWITCH 2500166','MediaWorld',319.00,375.99,319.00,319.00,10,TRUE,'Schermo 6,2 capacitivo multi touch Sensore NFC per utilizzo amiibo integrato nel controller destro', 'CONSEGNA_DOMICILIO', NULL, 7.99, 'https://www.mediaworld.it/product/p-103035/nintendo-switch-rosso-neonblu-neon-voucher-35eu-e-shop');
INSERT INTO PRODOTTO_FORNITO
VALUES('Nike Tanjun, Scarpe da Ginnastica Basse Uomo','Amazon',52.00,52.00,52.00 ,52.00 ,3,TRUE ,'Nike Tanjun 844887-010', 'CONSEGNA_DOMICILIO', NULL, 5.99, 'https://www.amazon.it/NIKE-Tanjun-844887-010-Sneaker-Mehrfarbig/dp/B078N1J87S/ref=sr_1_1?__mk_it_IT=%C3%85M%C3%85%C5%BD%C3%95%C3%91&keywords=Nike+Tanjun%2C+Scarpe+da+Ginnastica+Basse+Uomo&qid=1564325818&s=gateway&sr=8-1');
INSERT INTO PRODOTTO_FORNITO
VALUES('Nike Tanjun, Scarpe da Ginnastica Basse Uomo','Ebay',59.17,59.17 ,59.17 ,59.17 ,4,TRUE,'Nike Tanjun, Scarpe da Ginnastica Basse Uomo, Black (001 Black), 42 EU (v2V)', 'CONSEGNA_DOMICILIO', NULL,5.50, 'https://www.ebay.it/itm/Nike-Tanjun-Scarpe-da-Ginnastica-Basse-Uomo-Black-001-Black-42-EU-v2V/283494618407?hash=item4201989527:g:TKkAAOSwd15c51Jv');
INSERT INTO PRODOTTO_FORNITO
VALUES('CONVERSE ALL STAR HI SCARPE SPORTIVE ALTE BIANCHE 7650','Amazon',34.88,34.88 ,34.88 ,34.88 ,98,TRUE ,'Converse Chuck Taylor All Star Hi,Scarpe da Ginnastica Unisex Adulto', 'CONSEGNA_DOMICILIO', NULL, 5.99, 'https://www.amazon.it/Converse-Taylor-Scarpe-Ginnastica-Unisex/dp/B000OLTR8Q/ref=sr_1_fkmr0_1?__mk_it_IT=%C3%85M%C3%85%C5%BD%C3%95%C3%91&keywords=CONVERSE+ALL+STAR+HI+SCARPE+SPORTIVE+ALTE+BIANCHE+7650&qid=1564326211&s=gateway&sr=8-1-fkmr0');
INSERT INTO PRODOTTO_FORNITO
VALUES('CONVERSE ALL STAR HI SCARPE SPORTIVE ALTE BIANCHE 7650','Ebay',39.99 ,39.99 ,39.99 ,39.99 ,100 ,TRUE ,'CONVERSE ALL STAR HI SCARPE SPORTIVE ALTE BIANCHE 7650', 'CONSEGNA_DOMICILIO', NULL, NULL, 'https://www.ebay.it/itm/CONVERSE-ALL-STAR-HI-SCARPE-SPORTIVE-ALTE-BIANCHE-7650/163007059685?hash=item25f3fa56e5:m:mVyrS2n4JISfq5sZvKzKaDA');
INSERT INTO PRODOTTO_FORNITO
VALUES('LENOVO TB-7304F ZA300141DE','Amazon',67.90 ,99.90 ,67.90 ,67.90 ,12 ,TRUE,'Lenovo TAB 7 Tablet, Display 7" HD, Processore MediaTek,16GB espandibili fino a 128GB, RAM 1GB, WiFi, Android Nougat, Black', 'CONSEGNA_DOMICILIO', NULL, NULL, 'https://www.amazon.it/Lenovo-TB-7304F-Tablet-pixels-Android/dp/B079VJPZNZ/ref=sr_1_fkmr0_1?__mk_it_IT=%C3%85M%C3%85%C5%BD%C3%95%C3%91&keywords=LENOVO+TB-7304F+ZA300141DE&qid=1564326613&s=gateway&sr=8-1-fkmr0');
INSERT INTO PRODOTTO_FORNITO
VALUES('LENOVO TB-7304F ZA300141DE','Trony',79.00 ,79.00 ,79.00 ,79.00 ,19 ,TRUE,"Finalmente un ottimo tablet a un prezzo molto conveniente. Tab 7 Essential è il tablet perfetto per la famiglia, infatti grazie agli account multiutente i genitori possono personalizzare l'esperienza di utilizzo sia per loro stessi che per i propri figli. Inoltre è sottile e leggero: un tablet conveniente che può essere utilizzato da tutti i componenti della famiglia", 'CONSEGNA_DOMICILIO', NULL, NULL, 'https://www.trony.it/online/informatica/tablet/tablet/lenovo-tb-7304f-za300141de_sku-2180019556');
INSERT INTO PRODOTTO_FORNITO
VALUES('LENOVO TB-7304F ZA300141DE','Ebay', 105.62,105.62 ,105.62 ,105.62 ,10 ,TRUE,'Tablet 7 pollici Lenovo Touch 16GB Wifi Bluetooth GPS Android ZA300141DE TB7304F', 'CONSEGNA_DOMICILIO', NULL, NULL, 'https://www.ebay.it/itm/Tablet-7-pollici-Lenovo-Touch-16GB-Wifi-Bluetooth-GPS-Android-ZA300141DE-TB7304F/263939273903?hash=item3d7401b4af:g:a~YAAOSwuPZbnVWX');
INSERT INTO PRODOTTO_FORNITO
VALUES('MEDIACOM SmartPad iyo10','Amazon',79.00 ,79.00 ,79.00 ,79.00 ,5 , TRUE,'Mediacom Smartpad IYO 10 M-SP1AY 3G 8GB Tablet Computer', 'CONSEGNA_DOMICILIO', NULL, NULL, 'https://www.amazon.it/Mediacom-Smartpad-M-SP1AY-Tablet-Computer/dp/B07MXHW5DR/ref=sr_1_1?__mk_it_IT=%C3%85M%C3%85%C5%BD%C3%95%C3%91&keywords=mediacom+smartpad+iyo+10&qid=1564327043&s=gateway&sr=8-1');
INSERT INTO PRODOTTO_FORNITO
VALUES('MEDIACOM SmartPad iyo10','Ebay',83.99,99.98,83.99 ,83.99 , 3, TRUE,'Tablet Mediacom SmartPad IYO 10.0" pollici Bianco Android 8.1(go Edit) M-sp1ay', 'CONSEGNA_DOMICILIO', NULL, NULL, 'https://www.ebay.it/p/Tablet-Mediacom-SmartPad-IYO-10-0-pollici-Bianco-Android-8-1-go-Edit-M-sp1ay/24026161941');
INSERT INTO PRODOTTO_FORNITO
VALUES('MEDIACOM SmartPad iyo10','Trony',99.00 ,99.00 ,99.00 , 99.00, 16, TRUE,'Android - 10.100 - Modulo G (UMTS) 3G - Capacità hard disk (GB) 8 - RAM installata (GB) 1', 'CONSEGNA_DOMICILIO', NULL, NULL, 'https://www.trony.it/online/informatica/tablet/tablet/mediacom-smartpad-iyo10_sku-2190000078');
INSERT INTO PRODOTTO_FORNITO
VALUES('SAMSUNG SM-T510 SILVER','Expert',199.00 ,199.00 ,199.00 ,199.00 , 5, TRUE,'Divertimento alla portata di tutti. Abbiamo creato un tablet versatile e tecnologico, ma dal prezzo accessibile. Per offrire a tutti il piacere di essere al passo con la tecnologia . Con Galaxy Tab A (2019, 10.1”) sono le prestazioni ad essere al top, non il prezzo.', 'CONSEGNA_DOMICILIO', NULL, NULL, 'https://www.expertonline.it/it-IT-it/SAMSUNG-GALAXY-TAB-A-2019-10-1-WI-FI-32GB_235020.aspx');
INSERT INTO PRODOTTO_FORNITO
VALUES('SAMSUNG SM-T510 SILVER','Trony',178.00,178.00 ,178.00 , 178.00, 5, TRUE,'Divertimento alla portata di tutti.', 'CONSEGNA_DOMICILIO', NULL, NULL, 'https://www.trony.it/online/informatica/tablet/tablet/samsung-sm-t510-silver_sku-2190005537');
INSERT INTO PRODOTTO_FORNITO
VALUES('SAMSUNG SM-T510 SILVER','Amazon',267.98 ,267.98 ,267.98 ,267.98 , 5, TRUE,'Samsung Galaxy Tab A Wi Fi SM-T510 32GB Silver ES Version', 'CONSEGNA_DOMICILIO', NULL, NULL, 'https://www.amazon.it/Samsung-Galaxy-SM-T510-Silver-Version/dp/B07Q9SMPX9/ref=sr_1_3?__mk_it_IT=%C3%85M%C3%85%C5%BD%C3%95%C3%91&keywords=SAMSUNG+SM-T510+SILVER&qid=1564327422&s=gateway&sr=8-3');
INSERT INTO PRODOTTO_FORNITO
VALUES('SAMSUNG SM-T510 SILVER','MediaWorld',229.00 ,279.00 ,229.00 ,229.00, 5, TRUE,'Divertimento alla portata di tutti. Abbiamo creato un tablet versatile e tecnologico, ma dal prezzo accessibile. Per offrire a tutti il piacere di essere al passo con la tecnologia . Con Galaxy Tab A (2019, 10.1”) sono le prestazioni ad essere al top, non il prezzo.', 'CONSEGNA_DOMICILIO', NULL, NULL, 'https://www.mediaworld.it/product/p-992111/samsung-galaxy-tab-a-t515-2019-silver');
INSERT INTO PRODOTTO_FORNITO
VALUES('SAMSUNG SM-T510 SILVER','Ebay',174.00 ,174.00 ,174.00 ,174.00 , 5, TRUE,'SAMSUNG GALAXY TAB A 2019 SM- T510 10.1" 32GB SOLO WIFI SILVER ITALIA NO BRAND', 'CONSEGNA_DOMICILIO', NULL, NULL, 'https://www.ebay.it/itm/SAMSUNG-GALAXY-TAB-A-2019-SM-T510-10-1-32GB-SOLO-WIFI-SILVER-ITALIA-NO-BRAND/173975970586?hash=item2881c6a31a:g:JzwAAOSw3jFdOWTp');
INSERT INTO PRODOTTO_FORNITO
VALUES('APPLE iPad Pro (12.9)','Expert',1256.00 ,1256.00 ,1256.00 ,1256.00 , 4,TRUE ,'Tutto nuovo. Tutto schermo. Pronto a tutto. Completamente ridisegnato per racchiudere le nostre tecnologie più evolute, cambierà la tua idea di quel che può fare un iPad. Con il suo design all-screen, iPad Pro è come uno specchio magico pronto a realizzare i tuoi desideri.', 'CONSEGNA_DOMICILIO', NULL, NULL, 'https://www.expertonline.it/it-IT-it/APPLE-IPAD-PRO-12-9-WI-FI-CELL-64GB_221874.aspx');
INSERT INTO PRODOTTO_FORNITO
VALUES('APPLE iPad Pro (12.9)','Amazon',1233.38 ,1233.38 ,1233.38 ,1233.38 , 4,TRUE ,'Tutto nuovo. Tutto schermo. Pronto a tutto. Completamente ridisegnato per racchiudere le nostre tecnologie più evolute, cambierà la tua idea di quel che può fare un iPad. Con il suo design all-screen, iPad Pro è come uno specchio magico pronto a realizzare i tuoi desideri.', 'CONSEGNA_DOMICILIO', NULL, NULL, 'https://www.amazon.it/iPad-Pro-Wi-Fi-Cellular-64GB/dp/B07K2RW7P4/ref=sr_1_4?__mk_it_IT=%C3%85M%C3%85%C5%BD%C3%95%C3%91&keywords=APPLE+IPAD+PRO+12%2C9&qid=1564327806&s=gateway&sr=8-4');
INSERT INTO PRODOTTO_FORNITO
VALUES('APPLE iPad Pro (12.9)','Trony',1279.00 ,1279.00 ,1279.00 ,1279.00 , 4,TRUE ,'Tutto nuovo. Tutto schermo. Pronto a tutto. Completamente ridisegnato per racchiudere le nostre tecnologie più evolute, cambierà la tua idea di quel che può fare un iPad. Con il suo design all-screen, iPad Pro è come uno specchio magico pronto a realizzare i tuoi desideri.', 'CONSEGNA_DOMICILIO', NULL, NULL, 'https://www.trony.it/online/informatica/tablet/tablet/apple-ipad-pro-12-9-wifi-cellular-64gb-mthj2ty-a_sku-2180023086');
INSERT INTO PRODOTTO_FORNITO
VALUES('APPLE iPad Pro (12.9)','MediaWorld',1289.00,1289.00 ,1289.00 ,1289.00 , 4,TRUE ,'Tutto nuovo. Tutto schermo. Pronto a tutto. Completamente ridisegnato per racchiudere le nostre tecnologie più evolute, cambierà la tua idea di quel che può fare un iPad. Con il suo design all-screen, iPad Pro è come uno specchio magico pronto a realizzare i tuoi desideri.', 'CONSEGNA_DOMICILIO', NULL, NULL, 'https://www.mediaworld.it/product/p-988167/apple-ipad-pro-129-wi-fi-256-gb-grigio-siderale');
INSERT INTO PRODOTTO_FORNITO
VALUES('APPLE iPad Pro (12.9)','Ebay',1299.00 ,1299.00 ,1299.00 ,1299.00 , 4,TRUE ,'Tutto nuovo. Tutto schermo. Pronto a tutto. Completamente ridisegnato per racchiudere le nostre tecnologie più evolute, cambierà la tua idea di quel che può fare un iPad. Con il suo design all-screen, iPad Pro è come uno specchio magico pronto a realizzare i tuoi desideri.', 'CONSEGNA_DOMICILIO', NULL, NULL, 'https://www.ebay.it/p/Apple-%C3%82-iPadPro-Wi-fi-Cell-512gb-S-Mplk2ty-a/7011384463?iid=293115127726');
INSERT INTO PRODOTTO_FORNITO
VALUES('Settantallora - T-Shirt Maglietta J624','Amazon',14.99 ,14.99 ,14.99 ,14.99 , 10, TRUE,"Settantallora - T-Shirt Maglietta J624 Because I'm Happy Salernitana", 'CONSEGNA_DOMICILIO', NULL, 5.99, 'https://www.amazon.it/Settantallora-T-Shirt-Maglietta-Because-Salernitana/dp/B00YWUJIPW/ref=sr_1_1?__mk_it_IT=%C3%85M%C3%85%C5%BD%C3%95%C3%91&keywords=Settantallora+-+T-Shirt+Maglietta+J624&qid=1564328246&s=gateway&sr=8-1');
INSERT INTO PRODOTTO_FORNITO
VALUES('Nike Park Vi, Maglietta Uomo','Amazon',9.00 ,9.00 ,9.00 ,9.00 , 13,TRUE ,'Nike Park Vi, Maglietta Uomo', 'CONSEGNA_DOMICILIO', NULL, 3.99, 'https://www.amazon.it/Nike-Park-T-shirt-Black-White/dp/B01AC6P7OU/ref=sr_1_1?__mk_it_IT=%C3%85M%C3%85%C5%BD%C3%95%C3%91&keywords=Nike+Park+Vi%2C+Maglietta+Uomo&qid=1564328358&s=gateway&sr=8-1');
INSERT INTO PRODOTTO_FORNITO
VALUES('Nike Park Vi, Maglietta Uomo','Ebay',16.52,16.52 ,16.52 ,16.52 , 13,TRUE ,'Nike Park VI Maglietta Uomo M Nero (black/white)', 'CONSEGNA_DOMICILIO', NULL, NULL, 'https://www.ebay.it/p/Nike-Park-VI-Maglietta-Uomo-M-Nero-black-white/1471543758');
INSERT INTO PRODOTTO_FORNITO
VALUES('Western Digital WDBH2D0010HNC','Trony',50 ,50 ,50 ,50 , 12,TRUE ,'Gli hard disk WD Desktop tradizionali sono stati progettati e fabbricati con la pluripremiata tecnologia di WD. Offrono una solida affidabilità per le applicazioni web e le attività di routine delle aziende. Sono perfetti come extra storage per i PC desktop', 'CONSEGNA_DOMICILIO', NULL, 5.99, 'https://www.trony.it/online/informatica/hard-disk-e-chiavette-usb/hard-disk/wd-western-digital-wdbh2d0010hnc-ersn_sku-2140003511');
INSERT INTO PRODOTTO_FORNITO
VALUES('ACER ET430KWMIIPPX','Trony',621.00 ,621.00 ,621.00 ,621.00, 11, TRUE,'Acer ET Series IL TEATRO A CASA TUA Grandi? Senza limiti? 4k? OK Apprezza la visualizzazione brillante e sconfinata del monitor ET430K da 43": il cinema sembra davvero prendere vita a casa tua.', 'CONSEGNA_DOMICILIO', NULL, 14.99, 'https://www.trony.it/online/informatica/monitor-pc/monitor-pc/acer-et430kwmiippx_sku-2170020257');
INSERT INTO PRODOTTO_FORNITO
VALUES('NETGEAR R6220','Trony',50.00 ,50.00 ,50.00 ,50.00 , 9,TRUE ,"Streaming senza interruzioni su tutti i dispositivi WiFi grazie alla tecnologia WiFi AC che offre la velocità, la copertura e l'affidabilità necessarie.", "CONSEGNA_DOMICILIO", NULL, 5.99, "https://www.trony.it/online/informatica/wireless-e-networking/router-wifi/netgear-r6220_sku-2160010672");
INSERT INTO PRODOTTO_FORNITO
VALUES('HP X900','Trony',4.00 ,4.00 ,4.00 ,4.00 , 18,TRUE ,'Mouse cablato HP X900: Compatibilità hardware: Compatibile con tutte le piattaforme notebook e desktop con Windows ', 'CONSEGNA_DOMICILIO', NULL, 5.99, 'https://www.trony.it/online/informatica/accessori-pc-e-notebook/mouse/hp-x900_sku-2170018550');
INSERT INTO PRODOTTO_FORNITO
VALUES('Lacoste Abbigliamento LACOSTE POLO PIQUET BORDEAUX','Ebay',95.00 ,95.00 ,95.00 ,95.00 , 24,TRUE ,'Lacoste Abbigliamento LACOSTE POLO PIQUET BORDEAUX 1212 Bordeaux mod. 1212-476', 'CONSEGNA_DOMICILIO', NULL, NULL, 'https://www.ebay.it/itm/Lacoste-Abbigliamento-LACOSTE-POLO-PIQUET-BORDEAUX-1212-Bordeaux-mod-1212-476/351109246784?hash=item51bfbdef40:m:mnq_NkTqJCcZuzkejbkfnlg');
INSERT INTO PRODOTTO_FORNITO
VALUES('Kappa T-Shirts & Top Uomo Logo Amberis Allenamento T-Shirt','Ebay',9.99 ,9.99, 9.99, 9.99, 36,TRUE ,'Kappa T-Shirts & Top Uomo Logo Amberis Allenamento T-Shirt', 'CONSEGNA_DOMICILIO', NULL, 5.99, 'https://www.ebay.it/itm/Kappa-T-Shirts-Top-Uomo-Logo-Amberis-Allenamento-T-Shirt/372721934328?hash=item56c7f55ff8:m:mTHmNuMJoDubegJV3ME1QgA');
INSERT INTO PRODOTTO_FORNITO
VALUES('ADIDAS TREFOIL T-SHIRT UOMO GRIGIA','Ebay',19.90 ,19.90 ,19.90 ,19.90 , 38,TRUE ,'ADIDAS TREFOIL T-SHIRT UOMO GRIGIA', 'CONSEGNA_DOMICILIO', NULL, NULL, 'https://www.ebay.it/itm/ADIDAS-TREFOIL-T-SHIRT-UOMO-GRIGIA/163007063649?hash=item25f3fa6661:m:mNRLmDOt0MiXLQLcaFelhlA');
INSERT INTO PRODOTTO_FORNITO
VALUES('Settantallora - Felpa con Cappuccio KJ1755','Amazon',24.99 ,24.99 ,24.99 ,24.99 , 2, TRUE,'Felpa con Cappuccio', 'CONSEGNA_DOMICILIO', NULL, 5.99, 'https://www.amazon.it/Settantallora-Cappuccio-KJ1755-Stemma-Salerno/dp/B06XY95WT8/ref=sr_1_1?__mk_it_IT=%C3%85M%C3%85%C5%BD%C3%95%C3%91&keywords=Settantallora+-+Felpa+con+Cappuccio+KJ1755&qid=1564329248&s=gateway&sr=8-1');
INSERT INTO PRODOTTO_FORNITO
VALUES('Nike M NSW FZ FLC Club Felpa con cappuccio, uomo','Amazon',38.02, 38.02, 38.02, 38.02, 29, TRUE,'Nike M NSW FZ FLC Club Felpa con cappuccio, uomo', 'CONSEGNA_DOMICILIO', NULL, NULL, 'https://www.amazon.it/Nike-HOODIE-Cappuccio-Ossidiana-Bianco/dp/B0058YEMPW/ref=sr_1_1?__mk_it_IT=%C3%85M%C3%85%C5%BD%C3%95%C3%91&keywords=Nike+M+NSW+FZ+FLC+Club+Felpa+con+cappuccio&qid=1564329258&s=gateway&sr=8-1');
INSERT INTO PRODOTTO_FORNITO
VALUES('Nike H86 cap Metal Swoosh','Amazon',14.55 ,14.55 ,14.55 ,14.55 , 26, TRUE,'Occhielli ricamati traspiranti', 'CONSEGNA_DOMICILIO', NULL, 5.99, 'https://www.amazon.it/Nike-Cappellino-Baseball-Unisex-Adulto-Argento/dp/B077SWWTRG/ref=sr_1_1?__mk_it_IT=%C3%85M%C3%85%C5%BD%C3%95%C3%91&keywords=Nike+H86+cap+Metal+Swoosh&qid=1564329267&s=gateway&sr=8-1');
INSERT INTO PRODOTTO_FORNITO
VALUES('Homebaby Vintage Yoga Leggings Sportivi Donna','Amazon',5.32 ,5.32 ,5.32 ,5.32 ,23 , TRUE,'Morbido, largo, no-dig cintura: si trova piatta contro la pelle e previene "muffin top", sagomare le curve e razionalizzare la forma naturale', 'CONSEGNA_DOMICILIO', NULL, 5.99, 'https://www.amazon.it/Homebaby-Vintage-Leggings-Sportivi-Donna/dp/B079R1W89C/ref=sr_1_3_sspa?__mk_it_IT=%C3%85M%C3%85%C5%BD%C3%95%C3%91&keywords=Homebaby+Vintage+Yoga+Leggings+Sportivi+Donna&qid=1564329274&s=gateway&sr=8-3-spons&psc=1');
INSERT INTO PRODOTTO_FORNITO
VALUES('Yamaha C40II Chitarra Classica','Amazon',119.00,150.00 ,119.00 ,119.00 , 3, TRUE,'La chitarra classica entry level Yamaha C40 II è un modello tradizionale full-size che vanta sonorità eccezionali ed è pensata per chi si avvicina per la prima volta allo strumento', 'CONSEGNA_DOMICILIO', NULL, NULL, 'https://www.amazon.it/Yamaha-Chitarra-Classica-Colore-Naturale/dp/B003UGCUFE/ref=sr_1_1?__mk_it_IT=%C3%85M%C3%85%C5%BD%C3%95%C3%91&keywords=Yamaha+C40II+Chitarra+Classica&qid=1564329285&s=gateway&sr=8-1');
/* I campi in questo ordine -> nome_prodotto, nome_store, prezzo_ieri, prezzo_scorso_mese, prezzo_inizio_giorno, prezzo_attuale, quantity, disponibilita,descr_fornitore, opzione_acquisto, cod_ean, costi_spedizione, link_offerta  */

/* Query che crea il prodotto interno */
INSERT INTO PRODOTTO_INTERNO
VALUES('Mediacom M-SB145S1', 230.99, 5, TRUE, 'Intel - Celeron N3350 - Capacità RAM (GB) 4 - Capacità hard-disk (GB) 32 - Capacità SSD (GB) 120', 'CONSEGNA_DOMICILIO', NULL);
INSERT INTO PRODOTTO_INTERNO
VALUES('SONY ENTERTAINMENT PS4 500GB F CHASSIS BLACK', 239.99, 10, TRUE, "PS4 500GB F Chassis Black PACKAGE 3D Disponibile in Italia dal 16 settembre 2016, il nuovo modello più sottile e leggero del sistema PlayStation®4 (PS4) (serie CUH-2000), ha un volume ridotto del 30% rispetto ai modelli precedenti e un peso diminuito del 25% rispetto al modello originale.", 'CONSEGNA_DOMICILIO', NULL);
INSERT INTO PRODOTTO_INTERNO
VALUES('Apple iPhone 7 Plus Nero (Nero Opaco) 128GB', 599.50, 0, FALSE, 'Apple iPhone 7 Plus Nero (Nero Opaco) 128GB per foto spettacolari', 'RITIRO_IN_SEDE', NULL);
INSERT INTO PRODOTTO_INTERNO
VALUES('ASUS X540NA-GQ017T', 340.50, 5, TRUE, 'Intel - Intel® Celeron®, N3350 (1.1 GHz) - Capacità RAM (GB) 4 - Capacità hard-disk (GB) 500 - Modello scheda grafica Intel® HD Graphics 500 Memoria: Condivisa Uscite: HDMI - Dimensione monitor (pollici) 15.600', 'CONSEGNA_DOMICILIO', NULL);
INSERT INTO PRODOTTO_INTERNO
VALUES('NINTENDO 2DS XL 2209349', 149.99, 2, TRUE, 'Gioca e divertiti con nintendo 2ds', 'CONSEGNA_DOMICILIO', NULL);
INSERT INTO PRODOTTO_INTERNO
VALUES('NINTENDO SWITCH 2500166', 249.99, 20, TRUE, 'Gioca e divertiti con nintendo switch', 'CONSEGNA_DOMICILIO', NULL);
INSERT INTO PRODOTTO_INTERNO
VALUES('Adidas Superstar 80s W Scarpe da Donna. Sneakers', 88.99, 0, FALSE,'Scarpe molto confortevoli', 'RITIRO_IN_SEDE', NULL);
INSERT INTO PRODOTTO_INTERNO
VALUES('Nike Tanjun, Scarpe da Ginnastica Basse Uomo', 79.99, 50, TRUE, 'Scarpe molto confortevoli', 'CONSEGNA_DOMICILIO', NULL);
INSERT INTO PRODOTTO_INTERNO
VALUES('CONVERSE ALL STAR HI SCARPE SPORTIVE ALTE BIANCHE 7650', 49.99, 1, TRUE, 'Scarpe molto confortevoli', 'CONSEGNA_DOMICILIO', NULL);
INSERT INTO PRODOTTO_INTERNO
VALUES('Tablet Fire HD 8', 99.99, 5, TRUE, 'Il tablet più bello di sempre', 'CONSEGNA_DOMICILIO', NULL);
INSERT INTO PRODOTTO_INTERNO
VALUES('LENOVO TB-7304F ZA300141DE', 149.99, 0, FALSE,'Finalmente un tablet lenovo molto veloce', 'RITIRO_IN_SEDE', NULL);
INSERT INTO PRODOTTO_INTERNO
VALUES('MEDIACOM SmartPad iyo10', 59.99, 50, TRUE, 'Costo basto e consumi ridotti', 'CONSEGNA_DOMICILIO', NULL);
INSERT INTO PRODOTTO_INTERNO
VALUES('SAMSUNG SM-T510 SILVER', 249.99, 11, TRUE, 'Samsung non delude mai.', 'CONSEGNA_DOMICILIO', NULL);
INSERT INTO PRODOTTO_INTERNO
VALUES('APPLE iPad Pro (12.9)', 1249.99, 10, TRUE, 'Il tablet più potente mai creato.', 'CONSEGNA_DOMICILIO', NULL);
INSERT INTO PRODOTTO_INTERNO
VALUES('Canon EOS 500D fotocamera digitale', 488.99, 0, FALSE,'Fotocamera con ottime foto anche a lunghe distanze.', 'RITIRO_IN_SEDE', NULL);
INSERT INTO PRODOTTO_INTERNO
VALUES('ACER ET430KWMIIPPX', 479.99, 50, TRUE, 'Il miglior monitor per pc.', 'CONSEGNA_DOMICILIO', NULL);
INSERT INTO PRODOTTO_INTERNO
VALUES('NETGEAR R6220', 249.99, 1, TRUE, 'Router con velocità fino a 1Gbps.', 'CONSEGNA_DOMICILIO', NULL);
INSERT INTO PRODOTTO_INTERNO
VALUES('HP X900', 4.99, 5, TRUE, 'Mouse economico.', 'CONSEGNA_DOMICILIO', NULL);
INSERT INTO PRODOTTO_INTERNO
VALUES('Yamaha C40II Chitarra Classica', 139.99, 0, FALSE,'Chitarra professionale', 'RITIRO_IN_SEDE', NULL);
INSERT INTO PRODOTTO_INTERNO
VALUES('Settantallora - Felpa con Cappuccio KJ1755', 39.99, 50, TRUE, 'Felpa Salerno, ottima qualità.', 'CONSEGNA_DOMICILIO', NULL);
INSERT INTO PRODOTTO_INTERNO
VALUES('Nike M NSW FZ FLC Club Felpa con cappuccio, uomo', 59.99, 5, TRUE, 'Felpa Nike per veri atleti', 'CONSEGNA_DOMICILIO', NULL);
INSERT INTO PRODOTTO_INTERNO
VALUES('Nike H86 cap Metal Swoosh', 19.99, 0, FALSE,'Cappellino nike molto elegante.', 'RITIRO_IN_SEDE', NULL);
INSERT INTO PRODOTTO_INTERNO
VALUES('Homebaby Vintage Yoga Leggings Sportivi Donna', 39.99, 50, TRUE, 'Leggins super comodi.', 'CONSEGNA_DOMICILIO', NULL);
/* I campi in questo ordine -> nome_prodotto, prezzo, quantity, disponibilita, descrizione, opzione_acquisto,cod_ean */

/* Query che crea il voto di una recensione store */
INSERT INTO VOTA
VALUES(TRUE,'Trony','demo1');
/* I campi in questo ordine -> utile(TRUE = SI, FALSE = NO), username_recensione, name_store_recensione */

/* Query che crea l'acquisto di un prodotto fornito internamente da noi */
INSERT INTO ACQUISTA
VALUES('demo1', 'Mediacom M-SB145S1',230.99,'CONSEGNA_DOMICILIO');
