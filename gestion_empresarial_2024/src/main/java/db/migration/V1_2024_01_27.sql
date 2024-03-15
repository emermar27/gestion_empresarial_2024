CREATE OR REPLACE FUNCTION validaIdPersona()
	RETURNS TRIGGER
	LANGUAGE PLPGSQL
AS $$
DECLARE existe integer;

BEGIN

	existe = (SELECT COUNT(1) FROM persona_fisica WHERE id = NEW.persona_id);
	IF (existe <= 0) THEN
		existe = (select COUNT(1) FROM persona_juridica WHERE id = NEW.persona_id);
		IF (existe <= 0) THEN
			RAISE EXCEPTION 'No fue encontrada el Id de Persona';
		end IF;
	end if;
	
	RETURN new;
end;
$$

CREATE TRIGGER validaIdPersonaUpdate
	BEFORE UPDATE
	ON evaluacion_producto
	FOR EACH ROW
	EXECUTE PROCEDURE validaIdPersona();
	
CREATE TRIGGER validaIdPersonaInsertar
	BEFORE INSERT
	ON evaluacion_producto
	FOR EACH ROW
	EXECUTE PROCEDURE validaIdPersona();

CREATE TRIGGER validaIdPersonaUpdate
	BEFORE UPDATE
	ON cuenta_pagar
	FOR EACH ROW
	EXECUTE PROCEDURE validaIdPersona();
	
CREATE TRIGGER validaIdPersonaInsertar
	BEFORE INSERT
	ON cuenta_pagar
	FOR EACH ROW
	EXECUTE PROCEDURE validaIdPersona();
	
CREATE TRIGGER validaIdPersonaUpdate
	BEFORE UPDATE
	ON cuenta_recibir
	FOR EACH ROW
	EXECUTE PROCEDURE validaIdPersona();
	
CREATE TRIGGER validaIdPersonaInsertar
	BEFORE INSERT
	ON cuenta_recibir
	FOR EACH ROW
	EXECUTE PROCEDURE validaIdPersona();
	
CREATE TRIGGER validaIdPersonaUpdate
	BEFORE UPDATE
	ON direccion
	FOR EACH ROW
	EXECUTE PROCEDURE validaIdPersona();
	
CREATE TRIGGER validaIdPersonaInsertar
	BEFORE INSERT
	ON direccion
	FOR EACH ROW
	EXECUTE PROCEDURE validaIdPersona();
	
CREATE TRIGGER validaIdPersonaUpdate
	BEFORE UPDATE
	ON factura_compra
	FOR EACH ROW
	EXECUTE PROCEDURE validaIdPersona();
	
CREATE TRIGGER validaIdPersonaInsertar
	BEFORE INSERT
	ON factura_compra
	FOR EACH ROW
	EXECUTE PROCEDURE validaIdPersona();
	
CREATE TRIGGER validaIdPersonaUpdate
	BEFORE UPDATE
	ON usuario
	FOR EACH ROW
	EXECUTE PROCEDURE validaIdPersona();
	
CREATE TRIGGER validaIdPersonaInsertar
	BEFORE INSERT
	ON usuario
	FOR EACH ROW
	EXECUTE PROCEDURE validaIdPersona();
	
CREATE TRIGGER validaIdPersonaUpdate
	BEFORE UPDATE
	ON vnt_cmpr_tnda_vrtl
	FOR EACH ROW
	EXECUTE PROCEDURE validaIdPersona();
	
CREATE TRIGGER validaIdPersonaInsertar
	BEFORE INSERT
	ON vnt_cmpr_tnda_vrtl
	FOR EACH ROW
	EXECUTE PROCEDURE validaIdPersona();

CREATE OR REPLACE FUNCTION validaIdPersonaCuentaPagar()
	RETURNS TRIGGER
	LANGUAGE PLPGSQL
AS $$
DECLARE existe integer;

BEGIN

	existe = (SELECT COUNT(1) FROM persona_fisica WHERE id = NEW.persona_proveedor_id);
	IF (existe <= 0) THEN
		existe = (select COUNT(1) FROM persona_juridica WHERE id = NEW.persona_proveedor_id);
		IF (existe <= 0) THEN
			RAISE EXCEPTION 'No fue encontrada el Id de Persona';
		end IF;
	end if;
	
	RETURN new;
end;
$$

CREATE TRIGGER validaIdPersonaUpdateCuentaPagar
	BEFORE UPDATE
	ON cuenta_pagar
	FOR EACH ROW
	EXECUTE PROCEDURE validaIdPersonaCuentaPagar();
	
CREATE TRIGGER validaIdPersonaInsertarCuentaPagar
	BEFORE INSERT
	ON cuenta_pagar
	FOR EACH ROW
	EXECUTE PROCEDURE validaIdPersonaCuentaPagar();