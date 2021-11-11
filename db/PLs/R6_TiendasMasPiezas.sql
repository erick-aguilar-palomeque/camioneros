CREATE OR REPLACE FUNCTION  R6_TiendasMasPiezas(volumen_ingresado float)
returns table(
id_tienda int,
nombre text,
direccion text,
num_envios bigint
)
as
$func$
BEGIN
	RETURN QUERY
	select 
	t.id_tienda,
	t.nombre,
	t.direccion,
	count(e.id_envio) as num_envios
	from envio as e
	inner join tienda t on t.id_tienda=e.id_tienda
	where volumen>volumen_ingresado
	group by(t.id_tienda)
	order by(t.id_tienda) asc ;
	
END
$func$
Language plpgsql;