CREATE FUNCTION fnUpdateEntrega()
RETURNS TRIGGER
AS 
$$
BEGIN 
    update envio set clave_estado = 'EN' where id_envio = NEW.id_envio;
    return new;
END
$$
LANGUAGE  plpgsql;

CREATE TRIGGER trInsertEntrega
BEFORE INSERT
ON Entrega
FOR EACH ROW 
EXECUTE FUNCTION fnUpdateEntrega();