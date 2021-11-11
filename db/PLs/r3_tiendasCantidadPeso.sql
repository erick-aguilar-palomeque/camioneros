CREATE OR REPLACE FUNCTION  R3_TiendasCantidadPeso(peso float, volumen_enviado float)
returns table(
nombre text
)
as
$func$
BEGIN
	RETURN QUERY
	select t.nombre
	from 
	tienda as t 
	inner join envio e on e.id_tienda=t.id_tienda
	where e.peso_maximo>peso and e.volumen>volumen_enviado and e.clave_estado='EN'
	group by (t.nombre);
	
END
$func$
Language plpgsql;

--select * from R3_TiendasCantidadPeso(250,5000)