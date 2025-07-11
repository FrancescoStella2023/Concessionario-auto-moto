CREATE OR REPLACE FUNCTION func_stato_veicolo()
RETURNS TRIGGER AS $$

BEGIN
   IF new.data_restituzione IS NOT NULL THEN 
       UPDATE veicolo SET is_venduto = FALSE WHERE numero_telaio = new.v_numero_telaio;
 ELSIF new.data_vendita IS NOT NULL THEN 
     UPDATE veicolo SET is_venduto = TRUE WHERE numero_telaio = new.v_numero_telaio;
 RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE TRIGGER func_stato_veicolo_trg
AFTER INSERT OR UPDATE ON vendite
FOR EACH ROW
EXECUTE FUNCTION func_stato_veicolo();

CREATE OR REPLACE FUNCTION func_check_date_restituzione()
RETURNS TRIGGER AS $$

BEGIN
    IF NEW.data_restituzione IS NOT NULL AND NEW.data_restituzione < NEW.data_vendita THEN
        RAISE EXCEPTION 'La data di restituzione (%), non può essere precedente alla vendita (%)', NEW.data_restituzione, NEW.data_vendita;
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER func_check_date_restituzione_trg
BEFORE UPDATE ON Vendite
FOR EACH ROW
EXECUTE FUNCTION func_check_date_restituzione();

CREATE OR REPLACE FUNCTION func_check_is_venduto()
RETURNS TRIGGER AS $$
BEGIN
    -- Controllo se è già venduto
    IF (SELECT is_venduto FROM Veicolo WHERE numero_telaio = NEW.v_numero_telaio) IS TRUE THEN
        RAISE EXCEPTION 'Veicolo già venduto.';
    END IF;

    -- Controllo se è mai stato restituito (presente in Vendite con data_restituzione non nulla)
    IF EXISTS (
        SELECT 1 FROM Vendite
        WHERE v_numero_telaio = NEW.v_numero_telaio
        AND data_restituzione IS NOT NULL
    ) THEN
        RAISE EXCEPTION 'Veicolo già restituito: non può essere rivenduto.';
    END IF;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;


CREATE TRIGGER func_check_is_venduto_trg
BEFORE INSERT ON Vendite
FOR EACH ROW
EXECUTE FUNCTION func_check_is_venduto();

CREATE OR REPLACE FUNCTION func_applica_sconto_se_cliente_club()
RETURNS TRIGGER
AS $$
DECLARE
  sconto INT := 0;
  prezzo_base DECIMAL(10,2);
BEGIN
  -- Prende il prezzo del veicolo venduto
  SELECT prezzo INTO prezzo_base
  FROM Veicolo
  WHERE numero_telaio = NEW.v_numero_telaio;

  -- Recupera lo sconto associato al cliente (se fa parte di un club)
  SELECT c.percentuale_di_sconto INTO sconto
  FROM Clienti cl
  JOIN Club c ON cl.c_id_club = c.id_club
  WHERE cl.id_cliente = NEW.v_id_cliente;

  -- Calcola il prezzo finale applicando lo sconto (se c'è)
  NEW.prezzo_finale := prezzo_base * (1 - sconto / 100.0);

  RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER func_applica_sconto_trg
BEFORE INSERT ON Vendite
FOR EACH ROW
EXECUTE FUNCTION func_applica_sconto_se_cliente_club();

CREATE OR REPLACE FUNCTION func_blocca_delete_se_venduto()
RETURNS TRIGGER
AS $$
BEGIN
  IF EXISTS (
    SELECT 1 FROM Vendite
    WHERE v_numero_telaio = OLD.numero_telaio
  ) THEN
    RAISE EXCEPTION 'Impossibile eliminare: il veicolo è stato venduto almeno una volta (numero_telaio = %)', OLD.numero_telaio;
  END IF;

  RETURN OLD;  
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE TRIGGER func_blocca_delete_veicolo_trg
BEFORE DELETE ON Veicolo
FOR EACH ROW
EXECUTE FUNCTION func_blocca_delete_se_venduto();

CREATE OR REPLACE FUNCTION func_aggiorna_date_cliente()
RETURNS TRIGGER AS $$
BEGIN
    IF NEW.c_id_club IS NOT NULL THEN
        NEW.data_iscrizione := CURRENT_DATE;
        NEW.data_scadenza := CURRENT_DATE + INTERVAL '5 years';
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;
CREATE OR REPLACE TRIGGER data_cliente_trg 
BEFORE INSERT ON Clienti
FOR EACH ROW EXECUTE FUNCTION func_aggiorna_date_cliente();

CREATE OR REPLACE FUNCTION func_controlla_restituzione_veicolo()
RETURNS TRIGGER
AS $$
BEGIN
  -- Se l'utente sta inserendo o modificando la data di restituzione
  IF NEW.data_restituzione IS NOT NULL THEN

    -- Se era già presente una data di restituzione → blocca
    IF OLD.data_restituzione IS NOT NULL THEN
      RAISE EXCEPTION 'Restituzione già effettuata. Può essere fatta una sola volta.';
    END IF;

    -- Se la data di restituzione è oltre i 14 giorni dalla data di vendita → blocca
    IF NEW.data_restituzione > NEW.data_vendita + INTERVAL '14 days' THEN
      RAISE EXCEPTION 'Restituzione non valida: oltre i 14 giorni dalla vendita (%).', NEW.data_vendita;
    END IF;

  END IF;

  RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER func_controlla_restituzione_trg
BEFORE UPDATE ON Vendite
FOR EACH ROW
EXECUTE FUNCTION func_controlla_restituzione_veicolo();