/*
    create database camioneros;
    \c camioneros
*/
CREATE TABLE estado(
    clave_estado varchar(4) PRIMARY KEY,
    descripcion text NOT NULL
);
insert into estado values('AC', 'ACTIVO');
insert into estado values('BA', 'BAJA');
insert into estado values('EC', 'EN CAMINO');
insert into estado values('EN', 'ENTREGADO');
insert into estado values('BA', 'BAJA');

CREATE TABLE tienda(
    id_tienda serial PRIMARY KEY,
    nombre text NOT NULL,
    direccion text NOT NULL,
   --AUDITORIA
    fecha_alta timestamp NOT NULL default now(),
    usuario_alta text NOT NULL,
    fecha_baja timestamp,
    usuario_baja text,
    fecha_modificacion timestamp NOT NULL default now(),
    usuario_modificacion text NOT NULL,
    clave_estado varchar(4) references estado(clave_estado) default 'AC'
);

CREATE TABLE almacen(
    id_almacen serial PRIMARY KEY,
    nombre text NOT NULL,
    direccion text NOT NULL,
   --AUDITORIA
    fecha_alta timestamp NOT NULL default now(),
    usuario_alta text NOT NULL,
    fecha_baja timestamp,
    usuario_baja text,
    fecha_modificacion timestamp NOT NULL default now(),
    usuario_modificacion text NOT NULL,
    clave_estado varchar(4) references estado(clave_estado) default 'AC'
);

CREATE TABLE camion(
    id_camion serial PRIMARY KEY,
    placas text NOT NULL,
    volumen float NOT NULL,
    peso_maximo float NOT NULL,
    --AUDITORIA
    fecha_alta timestamp NOT NULL default now(),
    usuario_alta text NOT NULL,
    fecha_baja timestamp,
    usuario_baja text,
    fecha_modificacion timestamp NOT NULL default now(),
    usuario_modificacion text NOT NULL,
    clave_estado varchar(4) references estado(clave_estado) default 'AC'
);
insert into camion(placas, volumen, peso_maximo, usuario_alta, fecha_alta, usuario_modificacion, fecha_modificacion, clave_estado)
values
('PG-032', 1000, 1500, 'DESARROLLO', now(), 'DESAROLLO', now(), 'AC'),
('PG-033', 2000, 2500, 'DESARROLLO', now(), 'DESAROLLO', now(), 'AC'),
('PG-034', 3000, 3500, 'DESARROLLO', now(), 'DESAROLLO', now(), 'AC');



CREATE TABLE viaje(
    id_viaje serial PRIMARY KEY,
    id_camion int references camion(id_camion),
    --AUDITORIA
    fecha_alta timestamp NOT NULL default now(),
    usuario_alta text NOT NULL,
    fecha_baja timestamp,
    usuario_baja text,
    fecha_modificacion timestamp NOT NULL default now(),
    usuario_modificacion text NOT NULL,
    clave_estado varchar(4) references estado(clave_estado) default 'AC'
);

CREATE TABLE envio(
    id_envio serial PRIMARY KEY,
    id_almacen int references almacen(id_almacen),
    id_tienda int references tienda(id_tienda),
    id_viaje int references viaje(id_viaje),
    volumen float NOT NULL,
    peso_maximo float NOT NULL,
    --AUDITORIA
    fecha_alta timestamp NOT NULL default now(),
    usuario_alta text NOT NULL,
    fecha_baja timestamp,
    usuario_baja text,
    fecha_modificacion timestamp NOT NULL default now(),
    usuario_modificacion text NOT NULL,
    clave_estado varchar(4) references estado(clave_estado) default 'AC'
);

CREATE TABLE entrega(
    id_entrega serial PRIMARY KEY,
    id_envio int references envio(id_envio),
    fecha_entrega timestamp NOT NULL default now(),
    --AUDITORIA
    fecha_alta timestamp NOT NULL default now(),
    usuario_alta text NOT NULL,
    fecha_baja timestamp,
    usuario_baja text,
    fecha_modificacion timestamp NOT NULL default now(),
    usuario_modificacion text NOT NULL,
    clave_estado varchar(4) references estado(clave_estado) default 'AC'

);