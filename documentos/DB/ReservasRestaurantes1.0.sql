/*
NOTA: se modifico toda la base de datos eliminando tabla direcciones, adminmaster
y agregando otras tablas 
*/

CREATE DATABASE RESTAURANTES;
USE RESTAURANTES;

CREATE TABLE DEPARTAMENTOS(
id_departamento int not null auto_increment primary key,
departamento varchar(60)
);

CREATE TABLE MUNICIPIOS(
id_municipio int not null auto_increment primary key,
municipio varchar(40),
departamento int,
foreign key (departamento) references departamentos(id_departamento)
);


-- juan: agrege campo de horarios para apertura y cierre de sucursal
CREATE TABLE SUCURSALES(-- !!!! EN MODIFICACIÓN
id_sucursal int primary key auto_increment not null,
sucursal varchar(50),-- nombre de la sucursal
municipio int,-- FK de municipios
direccion varchar(50),
hora_apertura time not null,
hora_cierre time not null,
telefono varchar(9),
foreign key (municipio) references Municipios(id_municipio)
);

CREATE TABLE AREAS_SUCURSALES(-- ESTA TABLE EXPERESA LAS DIFERENTES AREAS DENTRO DE LOS RESTAURANES PARA COMER COMO BARRA, INFANTIL, VIP
id_area int not null auto_increment primary key,
area varchar(50),
id_sucursal int not null,
foreign key(id_sucursal) references SUCURSALES(id_sucursal)
);

CREATE TABLE RESTAURANTES(
id_restaurante int not null auto_increment primary key,
nombre_sociedad varchar(50), -- ejemplo sa de cv
nombre_empresa varchar(50) , -- el pollo campero
telefono_colcenter varchar(9),
direccion_oficina varchar(50)
);

CREATE TABLE SUCURSALES_RESTAURANTES(-- TABLA PIVOTE PARA RESTAURANTES Y SUCURSALES de esta tabla sacaremos la sucursal a la que pertence X restaurante
id_pivo_sucursal_restaurante int not null auto_increment primary key,
id_restaurante int,
id_sucursal int,
foreign key (id_restaurante) references restaurantes (id_restaurante),
foreign key (id_sucursal) references SUCURSALES (id_sucursal)
);


CREATE TABLE tipo_personas(
id_tipo_per int not null auto_increment primary key,
tipo_per varchar(50)-- sera clientes, empleados, sub gerente, gerente.
);

create table personas(
id_persona int not null auto_increment primary key,
nombres varchar(50),
apellidos varchar(50),
edad int,
id_tipo_persona int not null,
foreign key(id_tipo_persona) references tipo_personas(id_tipo_per)
);

create table clientes(
id_cliente int not null auto_increment primary key,
Email varchar(50),
telefono varchar(9),
id_persona int not null,
foreign key (id_persona) references personas(id_persona)
);

create table empleados(
id_empleado int not null auto_increment primary key,
id_persona int not null,
id_gerente int null,
foreign key (id_persona) references personas(id_persona),
foreign key (id_gerente) references empleados(id_empleado)
);

CREATE TABLE USUARIOS(
id_usuario int not null auto_increment primary key,
Nickname varchar(50),
pass varchar(50),
id_persona int default 0, -- 0: cliente , 1: empleado, 2: gerente
foreign key (id_persona) references personas(id_persona)-- FK de persona
);


CREATE TABLE RESERVAS( -- PEDIREMOS OTRO NUMERO E EMAIL DE CONTACTO A PARTE DE LOS QUE TIENE EL CLIENTE EN SU REGISTRO NORMAL PARA CADA RESERVA
id_reserva int not null auto_increment primary key,
id_cliente int not null,
fecha_reserva date,
sucursal int, -- FK de sucursales
cantidad_comensales int,
numero_contacto varchar(9),
email varchar(50),
estado varchar(10) default 'activo', -- permitira que el empleado pueda poner si esta finalizada o en proceso, cancelada
area_restaurante varchar(25),-- AREAS_RESTAURANTE
foreign key(id_cliente) references clientes (id_cliente),
foreign key(sucursal) references sucursales (id_sucursal)
);

create table empleados_sucursales(
id_empleado_sucursal int not null primary key auto_increment,
id_empleado int not null,
id_sucursal int not null,
hora_entrada time not null,
hora_salida time not null,
foreign key (id_empleado) references empleados(id_empleado),
foreign key (id_sucursal) references sucursales(id_sucursal)
);