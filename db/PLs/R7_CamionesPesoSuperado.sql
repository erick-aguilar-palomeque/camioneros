CREATE OR REPLACE FUNCTION  R7_CamionesPesoSuperado()
returns table(
id_camion int,
placas text,
peso_maximo float,
veces bigint
)
as
$func$
BEGIN
	RETURN QUERY
	select 
	s.id_camion,
	s.placas, 
	s.peso_maximo,
	s.veces
	from
	(select 
	c.id_camion,
	c.placas,
	c.peso_maximo,
	count(e.peso_maximo>c.peso_maximo) as veces
	from envio as e 
	inner join viaje v on v.id_viaje=e.id_viaje
	inner join camion c on c.id_camion=v.id_camion
	where e.peso_maximo>c.peso_maximo 
	group by(c.id_camion)) as s
	where s.veces>=4;
	
END
$func$
Language plpgsql;
--select * from R7_CamionesPesoSuperado()