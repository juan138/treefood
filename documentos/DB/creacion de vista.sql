USE RESTAURANTES;

/*
create view validar_view
as 
	SELECT isnull('id') as id,per.id_persona, us.Nickname,us.pass,per.id_tipo_persona FROM usuarios as us 
    inner join personas as per on us.id_persona=per.id_persona;
    
    select*from validar_view where nickname='juank' and pass='123';
**/
insert into restaurantes (nombre_sociedad,nombre_empresa,telefono_colcenter,direccion_oficina) values ('Panda Express SA de CV','Panda Restaurant Group', '2411 8000','');
select * from restaurantes;

insert into tipo_personas(tipo_per)value('cliente'),('empleado'),('gerente');
select*from tipo_personas;

-- ////////////////////******** PROCEDIMIENTOS PARA CLIENTES *********////////////////////////

-- uso de procedimiento almacenado para crear nuevo cliente

delimiter $$  
CREATE PROCEDURE insertCliente(IN usua varchar(50), IN pass varchar(50),
						IN nombre varchar(50), IN apellido varchar(50), IN edad int, IN tp int,
                        IN correo varchar(50), IN tel varchar(9))
BEGIN
START transaction;

	insert into personas(nombres,apellidos,edad,id_tipo_persona)value(nombre,apellido,edad,tp);
    
	insert into usuarios(nickname,pass,id_persona) value(usua,pass,last_insert_id());
    
    insert into clientes(email,telefono,id_persona) value (correo,tel,last_insert_id());
    

END $$

-- procedimiento almacenado para actualizar datos personales del cliente
delimiter $$
create procedure updateCliente(in idper int, in nombre varchar(50),in apellido varchar(50),
								in edad int, in correo varchar(50), in tel varchar(9))
begin 
start transaction;
	UPDATE personas SET nombres = nombre, apellidos = apellido, edad = edad WHERE (id_persona = idper);
	UPDATE clientes SET Email = correo, telefono = tel WHERE (id_persona = idper);

end $$



-- ////////////////////////////************ PROCEDIMIENTOS PARA EMPLEADOS O GERENTES ***************/////////////////////////////////////

-- procedimiento almacenado para un nuevo empleado o gerente


delimiter $$
create procedure insertEmpleado(in usua varchar(50), in pass varchar(50),
								in nombre varchar(50), in apellido varchar(50), in eda int, in tipoper int,
                                in idgerente int, in suc int, in hent varchar(10), in hsal varchar(10))
begin
start transaction;

	insert into personas(nombres,apellidos,edad,id_tipo_persona)value(nombre,apellido,eda,tipoper);
	insert into usuarios(nickname,pass,id_persona) value(usua,pass,last_insert_id());
    insert into empleados(id_persona,id_gerente) value(last_insert_id(),idgerente);
    INSERT INTO empleados_sucursales (id_empleado, id_sucursal, hora_entrada, hora_salida) VALUES (last_insert_id(), suc, hent, hsal);

end $$

delimiter $$
CREATE PROCEDURE deleteEmpleado(in idpersona int)
begin
start transaction;

	DELETE FROM empleados_sucursales WHERE (id_empleado =(select id_empleado from empleados where id_persona=idpersona));
	delete from usuarios where (id_persona=idpersona);
    delete from empleados where (id_persona = idpersona);
    delete from personas where (id_persona=idpersona);
    
end $$


-- //////////////////////// ************************* PROCEDIMIENTO PARA SUCURSALES Y RESTAURANTES *****************////////////////////

-- procedimiento almacenado para ingresar una nueva sucursal
delimiter $$
create procedure insertSucursal(in nombresuc varchar(50), in munsuc int, in dir varchar(50), in aperura time, in cierre time, in cel varchar(9),
								in idrest int)
begin
start transaction;
	insert into sucursales(sucursal, municipio, direccion, hora_apertura, hora_cierre, telefono) value(nombresuc,munsuc,dir,aperura,cierre,cel);
    insert into sucursales_restaurantes(id_restaurante,id_sucursal) value(idrest,last_insert_id());
end $$

-- ingresamos sucursal asignandole su restaurante correspondiente
call restaurantes.insertSucursal('panda expres',105,'metro sur','10:00', '18:30', '7562-4589',1);

-- ingresamos nuevo cliente con su usuario
call restaurantes.insertCliente('juan318', '321', 'juan', 'serrano', 23, 1,'sejuancarlos@outlock.com','7898-1235');
-- actualizamos datos personales de cliente
call restaurantes.updateCliente(1, 'chepe', 'perez', 23,'sejuancarlos@gmail.com','7898-1235');

-- ingresamos empleado con su usario ydatos prsonales y gefe
call restaurantes.insertEmpleado('juan317', '321', 'juan', 'serrano', 23, 3,null,1,'10:00', '18:30');

 insert into areas_sucursales(area,id_sucursal) value('infantil',1);

