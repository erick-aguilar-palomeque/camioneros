CREATE OR REPLACE FUNCTION  R2_EnviosDadoPlacas(placa text)
returns table(
id_envio int,
id_almacen int,
id_tienda int,
id_viaje int,
volumen float,
peso_maximo float,
descripcion text
)
as
$func$
BEGIN
	RETURN QUERY
	select 
	e.id_envio,
	e.id_almacen,
	e.id_tienda,
	e.id_viaje,
	e.volumen,
	e.peso_maximo,
	es.descripcion
	from envio e
	inner join viaje v on e.id_viaje=v.id_viaje
	inner join camion c on v.id_camion=c.id_camion
	inner join estado es on es.clave_estado = e.clave_estado
	where c.placas=placa;
END
$func$
Language plpgsql;