CREATE OR REPLACE FUNCTION  R1_TiendasPorCamion()
returns table(
id_camion int,
placas text,
id_tienda int,
nombre text,
direccion text
)
as
$func$
BEGIN
	RETURN QUERY
	select 
	c.id_camion,
	c.placas,
	t.id_tienda,
	t.nombre,
	t.direccion
	from camion as c 
	inner join viaje v on v.id_camion=c.id_camion
	inner join envio e on e.id_viaje=v.id_viaje
	inner join tienda t on t.id_tienda=e.id_tienda
	where e.clave_estado='EN'
	group by c.id_camion, t.id_tienda
	order by c.id_camion;
END
$func$
Language plpgsql;

--select * from R1_TiendasPorCamion();