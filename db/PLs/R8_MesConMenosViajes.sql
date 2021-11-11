CREATE OR REPLACE FUNCTION  R8_MesConMenosViajes()
returns table(
mes text,
noviajes bigint
)
as
$func$
BEGIN

	--Se realiza implementan dos subconsultas para soportar "empates"
	RETURN QUERY
	select * from (
	select 
	to_char(viaje.fecha_alta, 'TMMonth') as mes,
	count(*) noviajes
	from viaje
	group by(to_char(viaje.fecha_alta, 'TMMonth'))) as sb
	where sb.noviajes=(select MIN(s.noviajes) from
	(select 
	to_char(viaje.fecha_alta, 'TMMonth') as mes,
	count(*) noviajes
	from viaje
	group by(to_char(viaje.fecha_alta, 'TMMonth'))) as s);	
END
$func$
Language plpgsql;
--select * from  R8_MesConMenosViajes()
--drop function R8_MesConMenosViajes()