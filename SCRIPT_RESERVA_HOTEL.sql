CREATE TABLE huesped(
    id_huesped SERIAL PRIMARY KEY NOT NULL,
	dni INTEGER NOT NULL,
	nombres VARCHAR(100) NOT NULL,
	apellidos VARCHAR(100) NOT NULL,
	fecha_naci TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	direccion VARCHAR(250) NOT NULL,
	telefono INTEGER NOT NULL,
	email VARCHAR(250),
	estado_auditoria CHARACTER(1) NOT NULL DEFAULT 1
);

CREATE TABLE habitacion(
    id_habitacion SERIAL PRIMARY KEY NOT NULL,
	nombre VARCHAR(250) NOT NULL,
	tipo VARCHAR(150) NOT NULL,
	piso_ubicacion INTEGER NOT NULL,
	precio DECIMAL(10, 2) NOT NULL,
	descripcion VARCHAR(500) NOT NULL,
	imagen_url VARCHAR (2500),
	latitud VARCHAR(200) NOT NULL,
	longitud VARCHAR(200) NOT NULL,
	estado_auditoria CHARACTER(1) NOT NULL DEFAULT 1
);

CREATE TABLE servicio_adicional(
    id_servicio SERIAL PRIMARY KEY NOT NULL,
	nombre VARCHAR(100) NOT NULL,
	descripcion VARCHAR(500) NOT NULL,
	precio NUMERIC(10,2) NOT NULL,
	estado_auditoria CHARACTER(1) NOT NULL DEFAULT 1
);

CREATE TABLE reserva(
    id_reserva SERIAL PRIMARY KEY NOT NULL,
	fecha_inicio TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	fecha_fin TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	cantidad_dias INTEGER CHECK(cantidad_dias>0),
	id_habitacion INTEGER REFERENCES habitacion(id_habitacion),
	id_huesped INTEGER REFERENCES huesped(id_huesped),
	cantidad_personas INTEGER CHECK(cantidad_personas>0),
	monto_total NUMERIC(10,2) CHECK(monto_total>=0),
	estado VARCHAR(200) NOT NULL,
	fecha_registro TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE reserva_servicio(
    id_reserva_servicio SERIAL PRIMARY KEY NOT NULL,
	id_reserva INTEGER REFERENCES reserva(id_reserva),
	id_servicio INTEGER REFERENCES servicio_adicional(id_servicio),
	fecha TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    cantidad INTEGER CHECK (cantidad > 0)
);


CREATE TABLE empleados (
    id_empleado SERIAL PRIMARY KEY NOT NULL,
    nombre VARCHAR(100) NOT NULL,
    puesto VARCHAR(50) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    telefono VARCHAR(15)
);

CREATE TABLE promociones (
    id_promocion SERIAL PRIMARY KEY NOT NULL,
    nombre VARCHAR(100) NOT NULL,
    descripcion VARCHAR(500) NOT NULL,
    descuento NUMERIC(5, 2) NOT NULL,
    fecha_inicio TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    fecha_fin TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE inventario (
    id_inventario SERIAL PRIMARY KEY NOT NULL,
    nombre VARCHAR(100) NOT NULL,
    cantidad INT NOT NULL,
    proveedor VARCHAR(100),
    fecha_entrada TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE comentarios (
    id_comentario SERIAL PRIMARY KEY NOT NULL,
    id_huesped INT REFERENCES huesped(id_huesped),
    id_habitacion INT REFERENCES habitacion(id_habitacion),
    comentario VARCHAR(500) NOT NULL,
    valoracion INT CHECK (valoracion BETWEEN 1 AND 5),
    fecha TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE pagos (
    id_pago SERIAL PRIMARY KEY NOT NULL,
    id_reserva INT REFERENCES reserva(id_reserva),
    metodo_pago VARCHAR(50) NOT NULL,
    fecha_pago TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    monto NUMERIC(10, 2) NOT NULL,
    estado VARCHAR(20) NOT NULL
);

CREATE TABLE metodos_pago (
    id_metodo_pago SERIAL PRIMARY KEY NOT NULL,
	id_pago INT REFERENCES pagos(id_pago),
    nombre VARCHAR(50) NOT NULL
);



select * from HABITACION

INSERT INTO public.habitacion (nombre, tipo, piso_ubicacion, precio, descripcion,imagen_url, latitud,longitud)
VALUES 
('Suite Presidencial', 'Suite', 10, 500.00, 'Habitación de lujo con vista al mar','https://caminopalmero.com/wp-content/uploads/2024/06/Hotel-Camino-Palmero-Presidencial-01.webp','-16.3875336','-71.5445838'),
('Habitación Doble', 'Doble', 5, 150.00, 'Habitación con dos camas individuales','https://www.hotelpresidente.com.bo/images/slider-hdoble-2.jpg','-15.6499298','-71.6707959'),
('Habitación Individual', 'Individual', 3, 80.00, 'Habitación con una cama individual','https://www.central-hotel-paris.com/_novaimg/4899571-1480323_0_0_1600_1066_1080_720.jpg','-15.6499298','-71.6707959'),
('Habitación Familiar', 'Familiar', 2, 200.00, 'Habitación con capacidad para cuatro personas','https://cdn.easy-rez.com/production/hotels/8700696094450c97c00b7f5c1e216f47/uploads/.rooms/0627388001658265921.jpg','-15.6499298','-71.6707959');

select * from huesped

INSERT INTO public.huesped (dni, nombres, apellidos, fecha_naci, direccion, telefono, email)
VALUES 
('12345678', 'Juan', 'Pérez', '1985-05-15', 'Calle Falsa 123', '987654321', 'juan.perez@example.com'),
('87654321', 'María', 'González', '1990-08-22', 'Avenida Siempre Viva 456', '912345678', 'maria.gonzalez@example.com'),
('11223344', 'Carlos', 'Ramírez', '1978-12-30', 'Boulevard de los Sueños 789', '923456789', 'carlos.ramirez@example.com'),
('55667788', 'Ana', 'Martínez', '1982-03-10', 'Calle de la Luna 101', '934567890', 'ana.martinez@example.com');

select * from reserva

INSERT INTO public.reserva (fecha_inicio, fecha_fin, cantidad_dias, id_habitacion, id_huesped, cantidad_personas, monto_total, estado, fecha_registro)
VALUES 
('2024-09-25 14:00:00', '2024-09-30 12:00:00', 5, 1, 1, 2, 2500.00, 'Confirmada', '2024-09-25 10:00:00'),
('2024-10-01 14:00:00', '2024-10-05 12:00:00', 4, 2, 2, 1, 600.00, 'Pendiente', '2024-10-01 09:00:00'),
('2024-11-10 14:00:00', '2024-11-15 12:00:00', 5, 3, 3, 1, 400.00, 'Confirmada', '2024-11-10 08:00:00'),
('2024-12-01 14:00:00', '2024-12-07 12:00:00', 6, 4, 4, 4, 1200.00, 'Confirmada', '2024-12-01 07:00:00');

select * from servicio_adicional

INSERT INTO servicio_adicional (nombre, descripcion, precio)
VALUES 
('Spa', 'Acceso al spa y tratamientos de bienestar', 50.00),
('Lavandería', 'Servicio de lavandería y planchado', 20.00),
('Traslado al aeropuerto', 'Servicio de transporte al aeropuerto', 30.00),
('Tour local', 'Tour guiado por la ciudad', 40.00);

select * from reserva_servicio

INSERT INTO public.reserva_servicio (id_reserva, id_servicio, fecha, cantidad)
VALUES 
(1, 1, '2024-09-25 15:00:00', 2),
(2, 2, '2024-10-01 16:00:00', 1),
(3, 3, '2024-11-10 17:00:00', 3),
(4, 4, '2024-12-01 18:00:00', 1);

select * from pagos

INSERT INTO pagos (id_reserva, metodo_pago, fecha_pago, monto, estado)
VALUES 
(1, 'Efectivo', '2024-09-25', 2500.00, 'Pagado'),
(2, 'Tarjeta de Crédito', '2024-10-01', 600.00, 'Pendiente'),
(3, 'Transferencia Bancaria', '2024-11-10', 400.00, 'Pagado'),
(4, 'Efectivo', '2024-12-01', 1200.00, 'Pagado');

select * from empleados

INSERT INTO empleados (nombre, puesto, email, telefono)
VALUES 
('Laura Martínez', 'Recepcionista', 'laura.martinez@example.com', '987654321'),
('Carlos Gómez', 'Gerente', 'carlos.gomez@example.com', '912345678'),
('Ana Torres', 'Cocinera', 'ana.torres@example.com', '923456789'),
('Miguel Sánchez', 'Mantenimiento', 'miguel.sanchez@example.com', '934567890');

select * from promociones

INSERT INTO promociones (nombre, descripcion, descuento, fecha_inicio, fecha_fin)
VALUES 
('Descuento de Verano', 'Descuento del 20% en todas las habitaciones durante el verano', 20.00, '2024-06-01', '2024-08-31'),
('Promoción de Año Nuevo', 'Descuento del 15% en reservas realizadas para el Año Nuevo', 15.00, '2024-12-20', '2025-01-05'),
('Oferta de Fin de Semana', 'Descuento del 10% en estancias de fin de semana', 10.00, '2024-09-01', '2024-12-31'),
('Descuento para Clientes Frecuentes', 'Descuento del 5% para clientes que han realizado más de 3 reservas', 5.00, '2024-01-01', '2024-12-31');

select * from comentarios

INSERT INTO comentarios (id_huesped ,id_habitacion, comentario, valoracion, fecha)
VALUES 
(1, 1, 'Excelente servicio y atención', 5, '2024-09-25'),
(2, 2, 'Buena experiencia, pero la habitación podría ser mejor', 4, '2024-09-26'),
(3, 3, 'Regular, el servicio de limpieza no fue el mejor', 3, '2024-09-27'),
(4, 4, 'No me gustó la comida del restaurante', 2, '2024-09-28');

select * from metodos_pago

INSERT INTO metodos_pago (nombre)
VALUES 
('Efectivo'),
('Tarjeta de Crédito'),
('PayPal'),
('Transferencia Bancaria');

select * from inventario

INSERT INTO public.inventario (nombre, cantidad, proveedor, fecha_entrada)
VALUES 
('Toallas', 100, 'Proveedor A', '2024-09-25'),
('Sábanas', 200, 'Proveedor B', '2024-09-26'),
('Jabón', 300, 'Proveedor C', '2024-09-27'),
('Shampoo', 150, 'Proveedor D', '2024-09-28');

select * from comentarios;
select * from empleados;
select * from habitacion;
select * from huesped;
select * from inventario;
select * from metodos_pago;
select * from pagos;
select * from promociones;
select * from reserva;
select * from reserva_servicio;
select * from servicio_adicional;



