CREATE OR REPLACE FUNCTION  R10_ViajesCamionPorFecha(fecha_inicio date, fecha_fin date)
returns table(
id_camion int,
id_viaje int,
id_envio int,
volumen float,
id_tienda int
)
as
$func$
BEGIN

	--Se realiza implementan dos subconsultas para soportar "empates"
	RETURN QUERY
	select 
	c.id_camion,
	v.id_viaje,
	e.id_envio,
	e.volumen,
	t.id_tienda
	from camion as c
	inner join viaje v on v.id_camion=c.id_camion
	inner join envio e on e.id_viaje=v.id_viaje
	inner join tienda t on t.id_tienda=e.id_tienda
	where v.fecha_alta>=fecha_inicio and v.fecha_alta<=(fecha_fin + CAST('1 DAYS' AS INTERVAL))
	order by c.id_camion,v.id_viaje,e.volumen;
	
	
	
END
$func$
Language plpgsql;
--select * from  R10_ViajesCamionPorFecha('05/09/2021', '05/11/2021')
--drop function R10_ViajesCamionPorFecha