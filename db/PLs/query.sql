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
CREATE OR REPLACE FUNCTION  R9_MesConMasViajes()
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
	where sb.noviajes=(select MAX(s.noviajes) from
	(select 
	to_char(viaje.fecha_alta, 'TMMonth') as mes,
	count(*) noviajes
	from viaje
	group by(to_char(viaje.fecha_alta, 'TMMonth'))) as s);	
END
$func$
Language plpgsql;
--select * from  R9_MesConMasViajes()
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
