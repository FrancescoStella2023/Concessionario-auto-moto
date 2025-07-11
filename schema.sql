CREATE TABLE Club (
    id_club SERIAL PRIMARY KEY, 
    nome_club VARCHAR(25) NOT NULL,
    percentuale_di_sconto INT CHECK ( percentuale_di_sconto > 0 AND percentuale_di_sconto < 50) 
);
CREATE TABLE Clienti (
    id_cliente SERIAL PRIMARY KEY,
    c_id_club INT,
    nome VARCHAR(25) NOT NULL,
    cognome VARCHAR(25) NOT NULL,
    email VARCHAR(25) UNIQUE NOT NULL CHECK (email LIKE '%@%'),
    numero_di_telefono VARCHAR(25) UNIQUE NOT NULL,
    comune VARCHAR(25) NOT NULL,
    num_civico INT NOT NULL,
    indirizzo VARCHAR(25) NOT NULL,
    data_iscrizione DATE,
    data_scadenza DATE,
    FOREIGN KEY (c_id_club) REFERENCES Club(id_club) ON DELETE CASCADE ON UPDATE CASCADE
);
CREATE TABLE Magazzini (
    id_magazzino SERIAL PRIMARY KEY,
    indirizzo VARCHAR(25) NOT NULL
);
CREATE TABLE Dipendenti (
    id_dipendente SERIAL PRIMARY KEY,
    nome VARCHAR(25) NOT NULL,
    cognome VARCHAR(25) NOT NULL,
    password VARCHAR(25) NOT NULL CHECK(length(password) > 10),
    is_admin BOOL DEFAULT FALSE
);
CREATE TABLE Veicolo(
    numero_telaio INT PRIMARY KEY,
    v_id_magazzino INT NOT NULL,
    marca VARCHAR(25) NOT NULL, 
    modello VARCHAR(25) NOT NULL, 
    colore VARCHAR(25) NOT NULL, 
    prezzo DECIMAL(10, 2) NOT NULL CHECK(prezzo > 0),
    is_venduto BOOL DEFAULT FALSE, 
    tipo VARCHAR(1) NOT NULL CHECK (tipo = 'a' OR tipo = 'm'),
    numero_porte INT, 
    tipologia_cambio VARCHAR(1) NOT NULL CHECK (tipologia_cambio = 'a' OR tipologia_cambio = 'm'), 
    numero_airbag INT, 
    altezza_seggiolino DECIMAL(5, 2),
    FOREIGN KEY (v_id_magazzino) REFERENCES Magazzini(id_magazzino) ON DELETE CASCADE ON UPDATE CASCADE
);
CREATE TABLE Vendite(
    id_vendita SERIAL PRIMARY KEY,
    v_numero_telaio INT NOT NULL,
    v_id_dipendente INT NOT NULL,
    v_id_cliente INT NOT NULL, 
    data_vendita DATE NOT NULL DEFAULT CURRENT_DATE, 
    data_restituzione DATE, 
    prezzo_finale DECIMAL(10, 2) NOT NULL CHECK (prezzo_finale > 0),
    FOREIGN KEY (v_numero_telaio) REFERENCES Veicolo(numero_telaio) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (v_id_dipendente) REFERENCES Dipendenti(id_dipendente) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (v_id_cliente) REFERENCES Clienti(id_cliente) ON DELETE CASCADE ON UPDATE CASCADE
);



