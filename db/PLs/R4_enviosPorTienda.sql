CREATE OR REPLACE FUNCTION  R4_enviosPorTienda(id int)
returns table(
id_envio int,
volumen float,
peso_maximo float, 
id_viaje int,
id_camion int,
placa text
)
as
$func$
BEGIN
	RETURN QUERY
	select e.id_envio,
	e.volumen,
	e.peso_maximo,
	v.id_viaje,
	c.id_camion,
	c.placas
	from envio as e
	inner join tienda t on t.id_tienda=e.id_tienda
	inner join viaje v on v.id_viaje=e.id_viaje
	inner join camion c on c.id_camion=v.id_camion
	where t.id_tienda=id and e.clave_estado='EN';
	
END
$func$
Language plpgsql;
