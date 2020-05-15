
insert into restaurantes (nombre_sociedad,nombre_empresa,telefono_colcenter,direccion_oficina) values ('Panda Express SA de CV','Panda Restaurant Group', '2411 8000','');
select * from restaurantes;

insert into sucursales(sucursal, municipio, direccion, hora_apertura, hora_cierre ,telefono)value('Panda Xpress', 1,'Plaza Mundo','10:30','22:30','2235-5682');
select * from sucursales;

insert into areas_sucursales(area,id_sucursal)value('infantil',1);
select*from areas_sucursales;

insert into tipo_personas(tipo_per)value('cliente'),('empleado'),('gerente');
select*from tipo_personas;

insert into personas(nombres,apellidos,edad,id_tipo_persona)value('juan','serrano',23,1);
select*from personas;

insert into usuarios(nickname,pass,id_persona) value('juank','123',1);
select*from usuarios;

insert into clientes(email,telefono,id_persona) value('sejuancarlos@hotmail.com','5555-5555',1);
select*from clientes;
