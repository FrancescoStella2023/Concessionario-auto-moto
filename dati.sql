-- INSERT Club
INSERT INTO Club (nome_club, percentuale_di_sconto) VALUES
('Vip', 30),
('LuxuryClub', 15),
('EcoDrive', 8),
('SpeedZone', 12),
('CityCarClub', 5),
('GreenWay', 20),
('FamilyDrive', 18),
('SUVLovers', 10),
('MotoMania', 7),
('TurboTeam', 14);

-- INSERT Clienti
INSERT INTO Clienti (c_id_club, nome, cognome, email, numero_di_telefono, comune, num_civico, indirizzo) VALUES
(1, 'Mario', 'Rossi', 'mario.rossi@example.com', '1234567890', 'Milano', 10, 'Via Roma'),
(2, 'Luca', 'Verdi', 'luca.verdi@example.com', '2234567890', 'Torino', 20, 'Corso Italia'),
(3, 'Anna', 'Bianchi', 'anna.bianchi@example.com', '3234567890', 'Napoli', 5, 'Via Napoli'),
(4, 'Laura', 'Neri', 'laura.neri@example.com', '4234567890', 'Roma', 7, 'Via Appia'),
(5, 'Sara', 'Gialli', 'sara.gialli@example.com', '5234567890', 'Bologna', 3, 'Via Indipendenza'),
(6, 'Giulia', 'Rosa', 'giulia.rosa@example.com', '6234567890', 'Firenze', 9, 'Via Dante'),
(7, 'Alberto', 'Blu', 'alberto.blu@example.com', '7234567890', 'Genova', 11, 'Piazza Matteotti'),
(8, 'Simone', 'Viola', 'simone.viola@example.com', '8234567890', 'Padova', 14, 'Via Venezia'),
(9, 'Chiara', 'Marrone', 'chiara.marrone@example.com', '9234567890', 'Lecce', 22, 'Via Lecce'),
(10, 'Elena', 'Azzurri', 'elena.azzurri@example.com', '1023456789', 'Verona', 6, 'Via Arena');

-- INSERT Magazzini
INSERT INTO Magazzini (indirizzo) VALUES
('Via Torino 1'),
('Via Milano 2'),
('Via Napoli 3'),
('Via Roma 4'),
('Via Firenze 5'),
('Via Genova 6'),
('Via Bari 7'),
('Via Cagliari 8'),
('Via Palermo 9'),
('Via Trento 10');

-- INSERT Dipendenti
INSERT INTO Dipendenti (nome, cognome, password, is_admin) VALUES
('Paolo', 'Conti', 'passwordfortepaolo1', TRUE),
('Lucia', 'Mori', 'passwordlucia12345', FALSE),
('Marco', 'Dini', 'marcodinisecret12', FALSE),
('Gianni', 'Basso', 'bassogianni2022', TRUE),
('Elisa', 'Fonti', 'fontielisaaaaaa', FALSE),
('Davide', 'Luna', 'lunadavidepass', FALSE),
('Carla', 'Valli', 'vallipass2023x', TRUE),
('Giorgio', 'Toni', 'toni1999secure', FALSE),
('Irene', 'Bello', 'belloirene!1234', FALSE),
('Fabio', 'Galli', 'gallifabioxd456', FALSE);

-- INSERT Veicolo
INSERT INTO Veicolo (numero_telaio, v_id_magazzino, marca, modello, colore, prezzo, tipo, numero_porte, tipologia_cambio, numero_airbag, altezza_seggiolino) VALUES
(1001, 1, 'Fiat', 'Panda', 'Bianco', 10000.00, 'a', 5, 'm', 4, 40.0),
(1002, 2, 'BMW', 'X3', 'Nero', 35000.00, 'a', 5, 'a', 6, 45.5),
(1003, 3, 'Audi', 'A3', 'Rosso', 28000.00, 'a', 5, 'm', 6, 42.0),
(1004, 4, 'Piaggio', 'Vespa', 'Blu', 4000.00, 'm', NULL, 'm', NULL, NULL),
(1005, 5, 'Honda', 'CB500', 'Grigio', 6000.00, 'm', NULL, 'm', NULL, NULL),
(1006, 6, 'Toyota', 'Yaris', 'Verde', 15000.00, 'a', 5, 'a', 6, 41.5),
(1007, 7, 'Renault', 'Clio', 'Blu', 12000.00, 'a', 5, 'm', 4, 40.0),
(1008, 8, 'Ducati', 'Monster', 'Rosso', 11000.00, 'm', NULL, 'm', NULL, NULL),
(1009, 9, 'Mercedes', 'Classe A', 'Argento', 30000.00, 'a', 5, 'a', 6, 43.0),
(1010, 10, 'Ford', 'Fiesta', 'Giallo', 13000.00, 'a', 5, 'm', 4, 39.5);

-- INSERT Vendite (senza restituzione)
INSERT INTO Vendite (v_numero_telaio, v_id_dipendente, v_id_cliente, data_vendita) VALUES
(1001, 1, 1, '2025-07-01'),
(1002, 2, 2, '2025-07-03'),
(1003, 3, 3, '2025-07-05'),
(1004, 4, 4, '2025-07-06'),
(1005, 5, 5, '2025-07-07'),
(1009, 9, 9, '2025-07-02'),
(1010, 10, 10, '2025-07-04');

-- INSERT Vendite (con restituzione entro 14 giorni)
INSERT INTO Vendite (v_numero_telaio, v_id_dipendente, v_id_cliente, data_vendita, data_restituzione) VALUES
(1006, 6, 6, '2025-06-25', '2025-07-05'),
(1007, 7, 7, '2025-06-28', '2025-07-03'),
(1008, 8, 8, '2025-06-29', '2025-07-01');
