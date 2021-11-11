CREATE OR REPLACE FUNCTION  R5_CamionConMasViajes(id int)
returns table(
id_camion int,
placas text,
volumen float,
peso_maximo float,
veces bigint
)
as
$func$
BEGIN
	RETURN QUERY
	select * from(
	select 
	c.id_camion,
	c.placas, 
	c.volumen,
	c.peso_maximo,
	count(c.id_camion) as veces
	from 
	viaje as v 
	inner join camion c on c.id_camion=v.id_camion
	inner join envio e on e.id_viaje=v.id_viaje
	inner join tienda t on  t.id_tienda=e.id_tienda
	where t.id_tienda=id
	group  by(c.id_camion)) as s 
	where s.veces=(
	select MAX(sb.veces) from(
		select 
		--v.id_viaje
		c.id_camion, 
		count(c.id_camion) as veces
		from 
		viaje as v 
		inner join camion c on c.id_camion=v.id_camion
		inner join envio e on e.id_viaje=v.id_viaje
		inner join tienda t on  t.id_tienda=e.id_tienda
		where t.id_tienda=id
		group  by(c.id_camion)
	) as sb );
	
END
$func$
Language plpgsql;

--select * from  R5_CamionConMasViajes();