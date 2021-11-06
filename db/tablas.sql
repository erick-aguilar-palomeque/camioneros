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
insert into tienda values(default,'perez hermanos','monterrey',now(),'DESARROLLO',null,null,default,'DESARROLLO','AC');
insert into tienda values(default,'perez hermanos','chiapas',now(),'DESARROLLO',null,null,default,'DESARROLLO','AC');
insert into tienda values(default,'perez hermanos mini','tlaxcala',now(),'DESARROLLO',null,null,default,'DESARROLLO','AC');


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

insert into almacen values(default,'central','tabasco',now(),'DESARROLLO',null,null,default,'DESARROLLO','AC');
insert into almacen values(default,'central','chiapas',now(),'DESARROLLO',null,null,default,'DESARROLLO','AC');
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

insert into camion(placas, volumen, peso_maximo, usuario_alta, fecha_alta, usuario_modificacion, fecha_modificacion, clave_estado)
values ('FGH-19-56', 1000, 1500, 'DESARROLLO', now(), 'DESAROLLO', now(), 'AC');

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
insert into viaje values(default,1,default,'DESARROLLO',null,null,default,'DESARROLLO',default);
insert into viaje values(default,2,default,'DESARROLLO',null,null,default,'DESARROLLO',default);
insert into viaje values(default,3,default,'DESARROLLO',null,null,default,'DESARROLLO',default);
insert into viaje values(default,4,default,'DESARROLLO',null,null,default,'DESARROLLO',default);
insert into viaje values(default,1,'11-10-2021','DESARROLLO',null,null,default,'DESARROLLO',default);
insert into viaje values(default,4,'12-10-2021','DESARROLLO',null,null,default,'DESARROLLO',default);
insert into viaje values(default,1,'25-09-2021','DESARROLLO',null,null,default,'DESARROLLO',default);

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
				
insert into envio values(default,1,1,1,580,150,default,'DESARROLLO',null,null,default,'DESARROLLO',default);
insert into envio values(default,1,1,1,200,380,default,'DESARROLLO',null,null,default,'DESARROLLO',default);
insert into envio values(default,1,2,1,6000,1800,default,'DESARROLLO',null,null,default,'DESARROLLO',default);

insert into envio values(default,2,1,2,1200,200,default,'DESARROLLO',null,null,default,'DESARROLLO',default);
insert into envio values(default,1,2,2,550,180,default,'DESARROLLO',null,null,default,'DESARROLLO',default);
insert into envio values(default,1,2,2,1600,90,default,'DESARROLLO',null,null,default,'DESARROLLO',default);
insert into envio values(default,2,3,2,5001,251,default,'DESARROLLO',null,null,default,'DESARROLLO',default);

insert into envio values(default,1,1,3,100,50,default,'DESARROLLO',null,null,default,'DESARROLLO',default);
insert into envio values(default,1,1,3,200,150,default,'DESARROLLO',null,null,default,'DESARROLLO',default);
insert into envio values(default,1,1,3,50,10,default,'DESARROLLO',null,null,default,'DESARROLLO',default);

insert into envio values(default,1,3,4,120,80,default,'DESARROLLO',null,null,default,'DESARROLLO',default);
insert into envio values(default,1,3,4,200,150,default,'DESARROLLO',null,null,default,'DESARROLLO',default);
insert into envio values(default,1,3,4,230,180,default,'DESARROLLO',null,null,default,'DESARROLLO',default);

insert into envio values(default,1,1,5,120,80,default,'DESARROLLO',null,null,default,'DESARROLLO',default);
insert into envio values(default,1,3,6,200,150,default,'DESARROLLO',null,null,default,'DESARROLLO',default);
insert into envio values(default,1,1,7,230,180,default,'DESARROLLO',null,null,default,'DESARROLLO',default);


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