/*elimancion de las tablas*/

drop table cliente cascade constraints ;

drop table detalle_venta cascade constraints ;
drop table producto cascade constraints ;
drop table venta cascade constraints ;
drop table stock cascade constraints;
drop table tipo_producto cascade constraints;



/* creacion y elimacion de las secuencias*/
drop SEQUENCE seq_venta;
drop SEQUENCE seq_detalle;
drop SEQUENCE seq_producto;
drop SEQUENCE seq_stock;


CREATE SEQUENCE seq_venta 
increment by 1
start with 1
nomaxvalue;

CREATE SEQUENCE seq_producto
increment by 1
start with 1
nomaxvalue;

CREATE SEQUENCE seq_detalle 
increment by 1
start with 1
nomaxvalue;
alter SEQUENCE seq_producto increment by 1;

CREATE SEQUENCE seq_stock
increment by 1
start with 1
nomaxvalue;
alter SEQUENCE seq_stock increment by 1;


/*creacion de las tablas*/




CREATE TABLE cliente (
    rut    VARCHAR2(12) NOT NULL,
    nombre VARCHAR2(25) NOT NULL
);

ALTER TABLE cliente ADD CONSTRAINT cliente_pk PRIMARY KEY ( rut );

CREATE TABLE detalle_venta (
    id_detalle_venta INTEGER NOT NULL,
    cantidad         INTEGER NOT NULL,
 id_producto   INTEGER NOT NULL,
    id_venta         INTEGER NOT NULL
);

ALTER TABLE detalle_venta ADD CONSTRAINT detalle_venta_pk PRIMARY KEY ( id_detalle_venta );

CREATE TABLE producto (
    id_producto INTEGER NOT NULL,
    marca       VARCHAR2(35),
    precio      INTEGER NOT NULL,
    nombre      VARCHAR2(30) NOT NULL,
    id_tipopr   INTEGER NOT NULL,
    stock       INTEGER NOT NULL,
    azucar      VARCHAR2(2),
    lata        VARCHAR2(2),
    fch_vnc     DATE,
    grasas      VARCHAR2(2),
    peso        INTEGER,
    color       VARCHAR2(20)
);

ALTER TABLE producto ADD CONSTRAINT producto_pk PRIMARY KEY ( id_producto );

CREATE TABLE stock (
    id_stock INTEGER NOT NULL,
    cantidad INTEGER NOT NULL
);

ALTER TABLE stock ADD CONSTRAINT stock_pk PRIMARY KEY ( id_stock );

CREATE TABLE tipo_producto (
    id_tipopr   INTEGER NOT NULL,
    descripcion VARCHAR2(120) NOT NULL
);

ALTER TABLE tipo_producto ADD CONSTRAINT tipo_producto_pk PRIMARY KEY ( id_tipopr );

CREATE TABLE venta (
    id_venta    INTEGER NOT NULL,
    fecha       DATE NOT NULL,
    total       INTEGER,
    cliente_rut VARCHAR2(12) NOT NULL
);

ALTER TABLE venta ADD CONSTRAINT venta_pk PRIMARY KEY ( id_venta );

ALTER TABLE detalle_venta
    ADD CONSTRAINT detalle_venta_producto_fk FOREIGN KEY (id_producto )
        REFERENCES producto ( id_producto );

ALTER TABLE detalle_venta
    ADD CONSTRAINT detalle_venta_venta_fk FOREIGN KEY ( id_venta )
        REFERENCES venta ( id_venta );

ALTER TABLE producto
    ADD CONSTRAINT producto_tipo_producto_fk FOREIGN KEY ( id_tipopr )
        REFERENCES tipo_producto ( id_tipopr );

ALTER TABLE venta
    ADD CONSTRAINT venta_cliente_fk FOREIGN KEY ( cliente_rut )
        REFERENCES cliente ( rut );





ALTER table detalle_venta
modify id_detalle_venta default seq_detalle.nextval;



ALTER table producto
modify  id_producto default seq_producto.nextval;





/* inserta los productos*/




insert into tipo_producto (id_tipopr,descripcion) values(1,'bebestible');
insert into tipo_producto (id_tipopr,descripcion) values(2,'lacteo');
insert into tipo_producto (id_tipopr,descripcion) values(3,'fruta');

insert into producto(marca,precio,nombre,id_tipopr,stock,azucar,lata) 
values ('coca',900,'coca cero',1,37,'no','si');



insert into producto(marca,precio,nombre,id_tipopr,stock,azucar,lata) 
values ('coca',1600,'coca normal',1,56,'si','no');



insert into producto(marca,precio,nombre,id_tipopr,stock,azucar,lata) 
values ('pepsi',1650,'pepsi normal',1,90,'si','no');



insert into producto(marca,precio,nombre,id_tipopr,stock,azucar,lata) 
values ('pepsi',750,'pepsi light',1,40,'no','si');



insert into producto(marca,precio,nombre,id_tipopr,stock,fch_vnc,grasas) 
values ('colun',260,'colun de mora',2,34,sysdate+33,'si');




insert into producto(marca,precio,nombre,id_tipopr,stock,fch_vnc,grasas) 
values ('soprole',570,'protein',2,33,sysdate+33,'si');



insert into producto(marca,precio,nombre,id_tipopr,stock,fch_vnc,grasas) 
values ('Colun',300,'Colun de frutilla',2,35,sysdate+35,'si');

insert into producto(marca,precio,nombre,id_tipopr,stock,fch_vnc,grasas) 
values ('Soprole',450,'Soprole natural',2,32,sysdate+37,'no');

insert into producto(marca,precio,nombre,id_tipopr,stock,fch_vnc,grasas) 
values ('Nestlé',350,'Nestlé con cereales',2,36,sysdate+34,'si');

insert into producto(marca,precio,nombre,id_tipopr,stock,fch_vnc,grasas) 
values ('Ben O  Jerrys',650,'Ben O Jerrys de chocolate',2,37,sysdate+33,'no');

insert into producto(marca,precio,nombre,id_tipopr,stock,fch_vnc,grasas) 
values ('Dolce de Leche',280,'Dolce de Leche natural',2,32,sysdate+33,'no');




-- Inserción 1
INSERT INTO producto (precio, nombre, id_tipopr, stock,color, peso)
VALUES (450, 'manzana', 3, 58,'roja', 180);



-- Inserción 2
INSERT INTO producto (precio, nombre, id_tipopr, stock,color, peso)
VALUES (390, 'manzana', 3, 58,'verde', 210);





insert into cliente (rut, nombre) values ('57-078-7780', 'Merv');
insert into cliente (rut, nombre) values ('97-771-7800', 'Mattie');
insert into cliente (rut, nombre) values ('15-761-1475', 'Olivette');
insert into cliente (rut, nombre) values ('15-505-6054', 'Waylan');
insert into cliente (rut, nombre) values ('53-899-2714', 'Anne-corinne');
insert into cliente (rut, nombre) values ('41-458-3926', 'Bobbye');
insert into cliente (rut, nombre) values ('61-269-2954', 'Linnet');
insert into cliente (rut, nombre) values ('09-567-7672', 'Willem');
insert into cliente (rut, nombre) values ('83-123-2168', 'Leontyne');
insert into cliente (rut, nombre) values ('16-827-2818', 'Filberte');



COMMIT;

COMMIT;





