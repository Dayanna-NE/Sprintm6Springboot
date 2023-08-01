create database sprint6;
use sprint6;
-- ---------------------------------TABLA PERFIL----------------------------------------
create table Perfil(
id_perfil int primary key not null auto_increment,
descripcion varchar(50) not null,
estado boolean not null
);
-- ---------------------------------TABLA USUARIO----------------------------------------
create table Usuario(
id_usuario int primary key not null auto_increment,
run int not null unique,
clave varchar(200) not null,
nombre varchar(100) not null,
apellido_1 varchar(100) not null,
apellido_2 varchar(100),
id_perfil int not null,
email varchar(100) not null,
fecha_creacion datetime not null,
telefono bigint
);
-- ---------------------------------TABLA EMPLEADOR----------------------------------------
create table Empleador(
id_empleador int primary key not null auto_increment,
run int not null unique,
nombre varchar(100) not null,
apellido_1 varchar(100) not null,
apellido_2 varchar(100),
direccion text(500),
email varchar(100),
id_usuario int not null,
telefono bigint
);
-- ---------------------------------TABLA TRABAJADOR----------------------------------------
create table Trabajador(
id_trabajador int primary key not null auto_increment,
run int not null unique,
nombre varchar(100) not null,
apellido_1 varchar(100) not null,
apellido_2 varchar(100),
email varchar(100),
id_inst_prevision int not null,
id_inst_salud int not null,
telefono bigint not null
);
-- ---------------------------------TABLA DETALLE----------------------------------------
create table empl_trab(
id_empleador int not null,
id_trabajador int not null
);
-- ---------------------------------TABLA SALUD----------------------------------------
create table Institucion_salud(
id_inst_salud int primary key not null auto_increment,
descripcion varchar(100) not null,
proc_dcto float not null
);
-- ---------------------------------TABLA PREVISION----------------------------------------
create table Institucion_prevision(
id_inst_prevision int not null primary key auto_increment,
descripcion varchar(50) not null,
porc_dcto float not null
);
-- ---------------------------------TABLA LIQUIDACION----------------------------------------
create table Liquidacion(
id_liquidacion bigint primary key not null auto_increment,
id_trabajador int not null,
periodo date not null,
sueldo_imponible int not null,
sueldo_liquido int not null,
id_inst_salud int not null,
monto_inst_salud int not null,
id_inst_previsional int not null,
monto_inst_previsional int not null,
total_descuento int not null,
total_haberes int not null,
anticipo int not null
);

-- --------------------------------------------FK--------------------------------
-- --------------------------------------------FK Usuario-Perfil--------------------------------
Alter table Usuario
add constraint fk_usuario_perfil
foreign key (id_perfil)
references Perfil(id_perfil);
-- --------------------------------------------FK Empleador-Usuario--------------------------------
Alter table Empleador
add constraint fk_Empleador_Usuario
foreign key (id_usuario)
references Usuario(id_usuario);
-- --------------------------------------------FK Trabajador-Salud--------------------------------
Alter table Trabajador
add constraint FK_trabajador_instSalud
foreign key (id_inst_salud)
references Institucion_salud(id_inst_salud);
-- --------------------------------------------FK Trabajador-Prevision--------------------------------
Alter table Trabajador
add constraint FK_trabajador_instPrevision
foreign key (id_inst_prevision)
references Institucion_prevision(id_inst_prevision);
-- --------------------------------------------FK Liquidacion Trabajador--------------------------------
Alter table Liquidacion
add constraint FK_liquidacion_trabajador
foreign key (id_trabajador)
references Trabajador(id_trabajador);
-- --------------------------------------------FK Liquidacion Salud--------------------------------
Alter table Liquidacion
add constraint FK_liquidacion_instSalud
foreign key (id_inst_salud)
references Institucion_salud(id_inst_salud);
-- --------------------------------------------FK Liquidacion-Prevision--------------------------------
Alter table Liquidacion
add constraint FK_liquidacion_instPrevision
foreign key (id_inst_previsional)
references Institucion_prevision(id_inst_prevision);
-- --------------------------------------------FK empl_trab-Empleador--------------------------------
Alter table empl_trab
add constraint fk_empltrab_empleador
foreign key (id_empleador)
references Empleador(id_empleador)
on delete cascade;
-- --------------------------------------------FK empl_trab-Trabajador--------------------------------
Alter table empl_trab
add constraint fk_empltra_trabajador
foreign key (id_trabajador)
references Trabajador(id_trabajador)
on delete cascade;

-- >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>Son 9 FK <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

-- ----------------------------------Insert Perfil-----------------------------------
-- La tabla estado debe tener 3 perfiles:
-- 1 Administrador,
-- 2 Contador y
-- 3 empleador y
-- estado es 1 / true

insert into perfil values(1,'Administrador',true),
(2,'Contador',true),
(3,'Empleador',true);
select * from perfil;



-- ----------------------------------Insert Prevision-----------------------------------
insert into institucion_prevision values
(null,'Capital',11.44),
(null, 'Cuprum', 11.44),
(null, 'Habitat',11.27),
(null, 'PlanVital',11.16),
(null, 'ProVida',11.45),
(null,'Modelo',10.58),
(null,'Uno',10.69);


-- ----------------------------------Insert Salud-----------------------------------
-- id= 1 descripcion=Fonasa porc_dcto= 7%
insert into institucion_salud value(null,'Fonasa',0.07);


-- ----------------------------------Insert Usuario-----------------------------------
insert into Usuario value
(null, 1,'1','Dayanna','Nuñez','',1,'dayanna@gmail.com','2023-07-14',12345678);
INSERT INTO Usuario (run, clave, nombre, apellido_1, apellido_2, id_perfil, email, fecha_creacion, telefono)
VALUES
  (12345678, 'clave1', 'John', 'Doe', 'Smith', 3, 'johndoe@gmail.com', '2023-01-01 00:00:00', 1234567890),
  (23456789, 'clave2', 'Jane', 'Smith', 'Doe', 2, 'janesmith@gmail.com', '2023-02-02 00:00:00', 2345678901),
  (34567890, 'clave3', 'Michael', 'Johnson', NULL, 3, 'michaeljohnson@gmail.com', '2023-03-03 00:00:00', 3456789012),
  (45678901, 'clave4', 'Emily', 'Brown', 'Davis', 2, 'emilybrown@gmail.com', '2023-04-04 00:00:00', 4567890123),
  (56789012, 'clave5', 'Sophia', 'Miller', 'Wilson', 3, 'sophiamiller@gmail.com', '2023-05-05 00:00:00', 5678901234),
  (67890123, 'clave6', 'William', 'Anderson', 'Clark', 2, 'williamanderson@gmail.com', '2023-06-06 00:00:00', 6789012345),
  (78901234, 'clave7', 'Olivia', 'Lee', NULL, 3, 'olivialee@gmail.com', '2023-07-07 00:00:00', 7890123456),
  (89012345, 'clave8', 'James', 'Martin', 'White', 2, 'jamesmartin@gmail.com', '2023-08-08 00:00:00', 8901234567),
  (90123456, 'clave9', 'Isabella', 'Thompson', 'Moore', 3, 'isabellathompson@gmail.com', '2023-09-09 00:00:00', 9012345678),
  (12345098, 'clave10', 'Benjamin', 'Harris', NULL, 2, 'benjaminharris@gmail.com', '2023-10-10 00:00:00', 1234509876),
  (23456109, 'clave11', 'Mia', 'Hall', 'Turner', 3, 'miahall@gmail.com', '2023-11-11 00:00:00', 2345610987),
  (34567210, 'clave12', 'Elijah', 'Young', 'Parker', 2, 'elijahyoung@gmail.com', '2023-12-12 00:00:00', 3456721098),
  (45678321, 'clave13', 'Ava', 'Walker', 'Stewart', 3, 'avawalker@gmail.com', '2024-01-01 00:00:00', 4567832109),
  (56789432, 'clave14', 'Lucas', 'Cook', NULL, 2, 'lucascook@gmail.com', '2024-02-02 00:00:00', 5678943210),
  (67890543, 'clave15', 'Lily', 'Baker', 'Gray', 3, 'lilybaker@gmail.com', '2024-03-03 00:00:00', 6789054321),
  (78901654, 'clave16', 'Henry', 'Rivera', 'Bell', 2, 'henryrivera@gmail.com', '2024-04-04 00:00:00', 7890165432),
  (89012765, 'clave17', 'Sophie', 'Gonzalez', NULL, 3, 'sophiegonzalez@gmail.com', '2024-05-05 00:00:00', 8901276543),
  (90123876, 'clave18', 'Alexander', 'Moore', 'Cruz', 2, 'alexandermoore@gmail.com', '2024-06-06 00:00:00', 9012387654),
  (12345987, 'clave19', 'Chloe', 'Perez', 'Sanchez', 3, 'chloeperez@gmail.com', '2024-07-07 00:00:00', 1234598765),
  (23456098, 'clave20', 'Daniel', 'Jones', 'Robinson', 2, 'danieljones@gmail.com', '2024-08-08 00:00:00', 2345609876);

-- ----------------------------------Insert Empleador-----------------------------------
Insert into Empleador(run,nombre,apellido_1,apellido_2,direccion,email,id_usuario,telefono)
VALUES
  (12345678,  'John', 'Doe', 'Smith', 'Calle viva', 'johndoe@gmail.com', 2, 1234567890),
  (34567890, 'Michael', 'Johnson', NULL,null, 'michaeljohnson@gmail.com', 4, 3456789012),
  (56789012, 'Sophia', 'Miller', 'Wilson', null, 'sophiamiller@gmail.com', 6, 5678901234),
  (78901234,  'Olivia', 'Lee', NULL, null, 'olivialee@gmail.com', 8, 7890123456),
  (90123456,  'Isabella', 'Thompson', 'Moore', null, 'isabellathompson@gmail.com', 10, 9012345678),
  (23456109,  'Mia', 'Hall', 'Turner', null, 'miahall@gmail.com', 12, 2345610987),
  (45678321,  'Ava', 'Walker', 'Stewart', null, 'avawalker@gmail.com', 14, 4567832109),
  (67890543,  'Lily', 'Baker', 'Gray', null, 'lilybaker@gmail.com', 16, 6789054321),
  (89012765,  'Sophie', 'Gonzalez', NULL, null, 'sophiegonzalez@gmail.com', 18, 8901276543),
  (12345987,  'Chloe', 'Perez', 'Sanchez', null, 'chloeperez@gmail.com',20, 1234598765);