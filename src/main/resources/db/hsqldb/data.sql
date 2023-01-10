-- One admin user, named admin1 with passwor 4dm1n and authority admin
INSERT INTO users(username,password,enabled) VALUES ('admin1','4dm1n',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (1,'admin1','admin');
-- One owner user, named owner1 with passwor 0wn3r
INSERT INTO users(username,password,enabled) VALUES ('owner1','0wn3r',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (2,'owner1','owner');
-- One vet user, named vet1 with passwor v3t
INSERT INTO users(username,password,enabled) VALUES ('vet1','v3t',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (3,'vet1','veterinarian');
-- One owner user, named frafermot with passwor fra1
INSERT INTO users(username,password,enabled) VALUES ('frafermot','fra1',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (5,'frafermot','owner');

INSERT INTO users(username,password,enabled) VALUES ('antpelmor','ant1',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (4,'antpelmor','owner');

INSERT INTO users(username, password, enabled) VALUES ('alvvegrod', 'admin', TRUE);
INSERT INTO authorities(id, username, authority) VALUES (6, 'alvvegrod', 'owner');

INSERT INTO users(username, password, enabled) VALUES ('carlosdelrioperez', 'carlos', TRUE);
INSERT INTO authorities(id, username, authority) VALUES (7, 'carlosdelrioperez', 'owner');

INSERT INTO users(username,password,enabled) VALUES ('gabvidtev','admin',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (8,'gabvidtev','owner');


INSERT INTO vets(id, first_name,last_name) VALUES (1, 'James', 'Carter');
INSERT INTO vets(id, first_name,last_name) VALUES (2, 'Helen', 'Leary');
INSERT INTO vets(id, first_name,last_name) VALUES (3, 'Linda', 'Douglas');
INSERT INTO vets(id, first_name,last_name) VALUES (4, 'Rafael', 'Ortega');
INSERT INTO vets(id, first_name,last_name) VALUES (5, 'Henry', 'Stevens');
INSERT INTO vets(id, first_name,last_name) VALUES (6, 'Sharon', 'Jenkins');

INSERT INTO specialties VALUES (1, 'radiology');
INSERT INTO specialties VALUES (2, 'surgery');
INSERT INTO specialties VALUES (3, 'dentistry');

INSERT INTO vet_specialties VALUES (2, 1);
INSERT INTO vet_specialties VALUES (3, 2);
INSERT INTO vet_specialties VALUES (3, 3);
INSERT INTO vet_specialties VALUES (4, 2);
INSERT INTO vet_specialties VALUES (5, 1);

INSERT INTO types VALUES (1, 'cat');
INSERT INTO types VALUES (2, 'dog');
INSERT INTO types VALUES (3, 'lizard');
INSERT INTO types VALUES (4, 'snake');
INSERT INTO types VALUES (5, 'bird');
INSERT INTO types VALUES (6, 'hamster');
INSERT INTO types VALUES (7, 'turtle');

INSERT INTO owners VALUES (1, 'George', 'Franklin', '110 W. Liberty St.', 'Madison', '6085551023', 'owner1');
INSERT INTO owners VALUES (2, 'Betty', 'Davis', '638 Cardinal Ave.', 'Sun Prairie', '6085551749', 'owner1');
INSERT INTO owners VALUES (3, 'Eduardo', 'Rodriquez', '2693 Commerce St.', 'McFarland', '6085558763', 'owner1');
INSERT INTO owners VALUES (4, 'Harold', 'Davis', '563 Friendly St.', 'Windsor', '6085553198', 'owner1');
INSERT INTO owners VALUES (5, 'Peter', 'McTavish', '2387 S. Fair Way', 'Madison', '6085552765', 'owner1');
INSERT INTO owners VALUES (6, 'Jean', 'Coleman', '105 N. Lake St.', 'Monona', '6085552654', 'owner1');
INSERT INTO owners VALUES (7, 'Jeff', 'Black', '1450 Oak Blvd.', 'Monona', '6085555387', 'owner1');
INSERT INTO owners VALUES (8, 'Maria', 'Escobito', '345 Maple St.', 'Madison', '6085557683', 'owner1');
INSERT INTO owners VALUES (9, 'David', 'Schroeder', '2749 Blackhawk Trail', 'Madison', '6085559435', 'owner1');
INSERT INTO owners VALUES (10, 'Carlos', 'Estaban', '2335 Independence La.', 'Waunakee', '6085555487', 'owner1');
INSERT INTO owners VALUES (12, 'Francisco', 'Fernandez', 'Reina Mercedes', 'Sevilla', '666697775', 'owner1');
INSERT INTO owners VALUES (11, 'Antonio', 'Peláez', 'Reina Luján', 'Sevilla', '666666666', 'owner1');

INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (1, 'Leo', '2010-09-07', 1, 1);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (2, 'Basil', '2012-08-06', 6, 2);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (3, 'Rosy', '2011-04-17', 2, 3);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (4, 'Jewel', '2010-03-07', 2, 3);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (5, 'Iggy', '2010-11-30', 3, 4);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (6, 'George', '2010-01-20', 4, 5);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (7, 'Samantha', '2012-09-04', 1, 6);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (8, 'Max', '2012-09-04', 1, 6);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (9, 'Lucky', '2011-08-06', 5, 7);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (10, 'Mulligan', '2007-02-24', 2, 8);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (11, 'Freddy', '2010-03-09', 5, 9);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (12, 'Lucky', '2010-06-24', 2, 10);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (13, 'Sly', '2012-06-08', 1, 10);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (15, 'Speed', '2020-01-01', 4, 12);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (14, 'Stark', '2019-05-02', 2, 11);

INSERT INTO visits(id,pet_id,visit_date,description) VALUES (1, 7, '2013-01-01', 'rabies shot');
INSERT INTO visits(id,pet_id,visit_date,description) VALUES (2, 8, '2013-01-02', 'rabies shot');
INSERT INTO visits(id,pet_id,visit_date,description) VALUES (3, 8, '2013-01-03', 'neutered');
INSERT INTO visits(id,pet_id,visit_date,description) VALUES (4, 7, '2013-01-04', 'spayed');

INSERT INTO usuarios(nombre_usuario,contrasena,nombre,apellidos,fecha_nacimiento,enabled) VALUES ('Hola','$2a$10$TLi2Ie51KQ.gAH7J/bpgM.3JAcGCZFu7uGz483ty7YF7RfxdoVtz2','hola','hola','2000-01-10',True);
INSERT INTO autoridades(id,nombre_usuario,autoridad) VALUES (10,'Hola','admin');

INSERT INTO usuarios(nombre_usuario,contrasena,nombre,apellidos,fecha_nacimiento,enabled) VALUES ('Pepe','$2a$10$QuFiLQHzfVwK0SUX3ZBGIOuln.wHqOXZv317SdBoIoft2E8YipLBa','pepe','pepe','2001-01-10',True);
INSERT INTO autoridades(id,nombre_usuario,autoridad) VALUES (11,'Pepe','jugador');

INSERT INTO usuarios(nombre_usuario,contrasena,nombre,apellidos,fecha_nacimiento,enabled) VALUES ('Pablo','$2a$10$5SHbAO4WsM499ItJ91TjqORMxFI9ejP1gfZdJP5RvBHIz4Pphg74e','pablo','pablo','2002-01-10',True);
INSERT INTO autoridades(id,nombre_usuario,autoridad) VALUES (12,'Pablo','jugador');

INSERT INTO usuarios(nombre_usuario,contrasena,nombre,apellidos,fecha_nacimiento,enabled) VALUES ('Jaime','$2a$10$hwxxg6YaV0t./XkYcDcTXOLBY3l5GzfOMmASjbVHZVG.iyVmWJTK2','Jaime','Rodriguez Mantero','2003-01-10',True);
INSERT INTO autoridades(id,nombre_usuario,autoridad) VALUES (13,'Jaime','jugador');

INSERT INTO usuarios(nombre_usuario,contrasena,nombre,apellidos,fecha_nacimiento,enabled) VALUES ('Kiko','$2a$10$DMquhgR55QNvtGBUW7hCCesHyGAYvc7a17UXexNTQfVj5VJSKdUPi','kiko','Mota Mota','2002-01-10',True);
INSERT INTO autoridades(id,nombre_usuario,autoridad) VALUES (14,'Kiko','jugador');

INSERT INTO usuarios(nombre_usuario,contrasena,nombre,apellidos,fecha_nacimiento,enabled) VALUES ('Juan','$2a$10$M8iK0OJU3xwDxgwm1GBcwukMtZFNbogfjX5b3KVcWnUIiEeAX37Pu','juan','Recio Matamoros','2002-01-10',True);
INSERT INTO autoridades(id,nombre_usuario,autoridad) VALUES (15,'Juan','jugador');

INSERT INTO jugadores(partidas_jugadas, partidas_ganadas, total_puntos, record_puntos, nombre_usuario) VALUES (1,0,12,12,'Pepe');
INSERT INTO jugadores(partidas_jugadas, partidas_ganadas, total_puntos, record_puntos, nombre_usuario) VALUES (1,1,18,18,'Hola');
INSERT INTO jugadores(partidas_jugadas, partidas_ganadas, total_puntos, record_puntos, nombre_usuario) VALUES (2,1,38,26,'Pablo');
INSERT INTO jugadores(partidas_jugadas, partidas_ganadas, total_puntos, record_puntos, nombre_usuario) VALUES (1,0,15,15,'Jaime');
INSERT INTO jugadores(partidas_jugadas, partidas_ganadas, total_puntos, record_puntos, nombre_usuario) VALUES (0,0,0,0,'Kiko');
INSERT INTO jugadores(partidas_jugadas, partidas_ganadas, total_puntos, record_puntos, nombre_usuario) VALUES (0,0,0,0,'Juan');

INSERT INTO partidas(id,fecha, hora_inicio, hora_fin, estado, codigo,creador_id, jugador_actual_id, dado_tirado, ganador_id) VALUES (1,'2001-01-10','23:00:00','23:20:00',2,'qwerty',1,1, false, null);
INSERT INTO partida_jugador(partida_id, jugador_id) VALUES (1,1);
INSERT INTO partida_jugador(partida_id, jugador_id) VALUES (1,2);

INSERT INTO partidas(id,fecha, hora_inicio, hora_fin, estado,duracion, codigo,creador_id, jugador_actual_id, dado_tirado, ganador_id) VALUES (2,'2001-01-12','15:00:00','15:26:00',0,26,'password',3,3, false, 3);
INSERT INTO partida_jugador(partida_id, jugador_id) VALUES (2,3);
INSERT INTO partida_jugador(partida_id, jugador_id) VALUES (2,4);
INSERT INTO estadisticas(jugador_id,partida_id,posicion,puntos_obtenidos,barcos_usados,cartas_obtenidas) VALUES (3,2,1,26,4,7);
INSERT INTO estadisticas(jugador_id,partida_id,posicion,puntos_obtenidos,barcos_usados,cartas_obtenidas) VALUES (4,2,2,15,3,7);

INSERT INTO partidas(id,fecha, hora_inicio, hora_fin, estado,duracion, codigo,creador_id, jugador_actual_id, dado_tirado, ganador_id) VALUES (3,'2015-12-12','17:00:00','17:38:00',0,38,'password',1,1, false, 2);
INSERT INTO partida_jugador(partida_id, jugador_id) VALUES (3,1);
INSERT INTO partida_jugador(partida_id, jugador_id) VALUES (3,2);
INSERT INTO partida_jugador(partida_id, jugador_id) VALUES (3,3);
INSERT INTO estadisticas(jugador_id,partida_id,posicion,puntos_obtenidos,barcos_usados,cartas_obtenidas) VALUES (1,3,2,12,2,5);
INSERT INTO estadisticas(jugador_id,partida_id,posicion,puntos_obtenidos,barcos_usados,cartas_obtenidas) VALUES (2,3,1,18,3,5);
INSERT INTO estadisticas(jugador_id,partida_id,posicion,puntos_obtenidos,barcos_usados,cartas_obtenidas) VALUES (3,3,3,12,2,5);

INSERT INTO tablero(id,background,height,width) VALUES (1,'resources/images/tablero.png',850,700);

INSERT INTO cartas(id,tipo_carta,estado_carta,posicion,jugador_id, tablero_id) VALUES (1,0,2,0,null, 1);
INSERT INTO cartas(id,tipo_carta,estado_carta,posicion,jugador_id, tablero_id) VALUES (2,0,2,0,null, 1);
INSERT INTO cartas(id,tipo_carta,estado_carta,posicion,jugador_id, tablero_id) VALUES (3,0,2,0,null, 1);
INSERT INTO cartas(id,tipo_carta,estado_carta,posicion,jugador_id, tablero_id) VALUES (4,0,2,0,null, 1);
INSERT INTO cartas(id,tipo_carta,estado_carta,posicion,jugador_id, tablero_id) VALUES (5,0,2,1,null, 1);
INSERT INTO cartas(id,tipo_carta,estado_carta,posicion,jugador_id, tablero_id) VALUES (6,0,2,0,null, 1);
INSERT INTO cartas(id,tipo_carta,estado_carta,posicion,jugador_id, tablero_id) VALUES (7,0,2,0,null, 1);
INSERT INTO cartas(id,tipo_carta,estado_carta,posicion,jugador_id, tablero_id) VALUES (8,0,2,0,null, 1);
INSERT INTO cartas(id,tipo_carta,estado_carta,posicion,jugador_id, tablero_id) VALUES (9,0,2,0,null, 1);
INSERT INTO cartas(id,tipo_carta,estado_carta,posicion,jugador_id, tablero_id) VALUES (10,0,2,0,null, 1);
INSERT INTO cartas(id,tipo_carta,estado_carta,posicion,jugador_id, tablero_id) VALUES (11,0,2,0,null, 1);
INSERT INTO cartas(id,tipo_carta,estado_carta,posicion,jugador_id, tablero_id) VALUES (12,0,2,0,null, 1);
INSERT INTO cartas(id,tipo_carta,estado_carta,posicion,jugador_id, tablero_id) VALUES (13,0,2,0,null, 1);
INSERT INTO cartas(id,tipo_carta,estado_carta,posicion,jugador_id, tablero_id) VALUES (14,0,2,0,null, 1);
INSERT INTO cartas(id,tipo_carta,estado_carta,posicion,jugador_id, tablero_id) VALUES (15,0,2,0,null, 1);
INSERT INTO cartas(id,tipo_carta,estado_carta,posicion,jugador_id, tablero_id) VALUES (16,0,2,0,null, 1);
INSERT INTO cartas(id,tipo_carta,estado_carta,posicion,jugador_id, tablero_id) VALUES (17,0,2,0,null, 1);
INSERT INTO cartas(id,tipo_carta,estado_carta,posicion,jugador_id, tablero_id) VALUES (18,0,2,0,null, 1);
INSERT INTO cartas(id,tipo_carta,estado_carta,posicion,jugador_id, tablero_id) VALUES (19,0,2,0,null, 1);
INSERT INTO cartas(id,tipo_carta,estado_carta,posicion,jugador_id, tablero_id) VALUES (20,0,2,0,null, 1);
INSERT INTO cartas(id,tipo_carta,estado_carta,posicion,jugador_id, tablero_id) VALUES (21,0,2,0,null, 1);
INSERT INTO cartas(id,tipo_carta,estado_carta,posicion,jugador_id, tablero_id) VALUES (22,0,2,0,null, 1);
INSERT INTO cartas(id,tipo_carta,estado_carta,posicion,jugador_id, tablero_id) VALUES (23,0,2,0,null, 1);
INSERT INTO cartas(id,tipo_carta,estado_carta,posicion,jugador_id, tablero_id) VALUES (24,0,2,0,null, 1);
INSERT INTO cartas(id,tipo_carta,estado_carta,posicion,jugador_id, tablero_id) VALUES (25,0,2,0,null, 1);
INSERT INTO cartas(id,tipo_carta,estado_carta,posicion,jugador_id, tablero_id) VALUES (26,0,2,0,null, 1);
INSERT INTO cartas(id,tipo_carta,estado_carta,posicion,jugador_id, tablero_id) VALUES (27,0,2,0,null, 1);
INSERT INTO cartas(id,tipo_carta,estado_carta,posicion,jugador_id, tablero_id) VALUES (28,1,2,0,null, 1);
INSERT INTO cartas(id,tipo_carta,estado_carta,posicion,jugador_id, tablero_id) VALUES (29,1,2,0,null, 1);
INSERT INTO cartas(id,tipo_carta,estado_carta,posicion,jugador_id, tablero_id) VALUES (30,1,2,0,null, 1);
INSERT INTO cartas(id,tipo_carta,estado_carta,posicion,jugador_id, tablero_id) VALUES (31,2,2,0,null, 1);
INSERT INTO cartas(id,tipo_carta,estado_carta,posicion,jugador_id, tablero_id) VALUES (32,2,2,0,null, 1);
INSERT INTO cartas(id,tipo_carta,estado_carta,posicion,jugador_id, tablero_id) VALUES (33,2,2,0,null, 1);
INSERT INTO cartas(id,tipo_carta,estado_carta,posicion,jugador_id, tablero_id) VALUES (34,3,2,0,null, 1);
INSERT INTO cartas(id,tipo_carta,estado_carta,posicion,jugador_id, tablero_id) VALUES (35,3,2,0,null, 1);
INSERT INTO cartas(id,tipo_carta,estado_carta,posicion,jugador_id, tablero_id) VALUES (36,3,2,0,null, 1);
INSERT INTO cartas(id,tipo_carta,estado_carta,posicion,jugador_id, tablero_id) VALUES (37,4,2,0,null, 1);
INSERT INTO cartas(id,tipo_carta,estado_carta,posicion,jugador_id, tablero_id) VALUES (38,4,2,0,null, 1);
INSERT INTO cartas(id,tipo_carta,estado_carta,posicion,jugador_id, tablero_id) VALUES (39,4,2,0,null, 1);
INSERT INTO cartas(id,tipo_carta,estado_carta,posicion,jugador_id, tablero_id) VALUES (40,4,2,0,null, 1);
INSERT INTO cartas(id,tipo_carta,estado_carta,posicion,jugador_id, tablero_id) VALUES (41,5,2,0,null, 1);
INSERT INTO cartas(id,tipo_carta,estado_carta,posicion,jugador_id, tablero_id) VALUES (42,5,2,0,null, 1);
INSERT INTO cartas(id,tipo_carta,estado_carta,posicion,jugador_id, tablero_id) VALUES (43,5,2,0,null, 1);
INSERT INTO cartas(id,tipo_carta,estado_carta,posicion,jugador_id, tablero_id) VALUES (44,5,2,0,null, 1);
INSERT INTO cartas(id,tipo_carta,estado_carta,posicion,jugador_id, tablero_id) VALUES (45,6,2,0,null, 1);
INSERT INTO cartas(id,tipo_carta,estado_carta,posicion,jugador_id, tablero_id) VALUES (46,6,2,0,null, 1);
INSERT INTO cartas(id,tipo_carta,estado_carta,posicion,jugador_id, tablero_id) VALUES (47,6,2,0,null, 1);
INSERT INTO cartas(id,tipo_carta,estado_carta,posicion,jugador_id, tablero_id) VALUES (48,6,2,0,null, 1);
INSERT INTO cartas(id,tipo_carta,estado_carta,posicion,jugador_id, tablero_id) VALUES (49,7,2,0,null, 1);
INSERT INTO cartas(id,tipo_carta,estado_carta,posicion,jugador_id, tablero_id) VALUES (50,7,2,0,null, 1);
INSERT INTO cartas(id,tipo_carta,estado_carta,posicion,jugador_id, tablero_id) VALUES (51,7,2,0,null, 1);
INSERT INTO cartas(id,tipo_carta,estado_carta,posicion,jugador_id, tablero_id) VALUES (52,7,2,0,null, 1);
INSERT INTO cartas(id,tipo_carta,estado_carta,posicion,jugador_id, tablero_id) VALUES (53,7,2,0,null, 1);
INSERT INTO cartas(id,tipo_carta,estado_carta,posicion,jugador_id, tablero_id) VALUES (54,7,2,0,null, 1);
INSERT INTO cartas(id,tipo_carta,estado_carta,posicion,jugador_id, tablero_id) VALUES (55,8,2,0,null, 1);
INSERT INTO cartas(id,tipo_carta,estado_carta,posicion,jugador_id, tablero_id) VALUES (56,8,2,0,null, 1);
INSERT INTO cartas(id,tipo_carta,estado_carta,posicion,jugador_id, tablero_id) VALUES (57,8,2,0,null, 1);
INSERT INTO cartas(id,tipo_carta,estado_carta,posicion,jugador_id, tablero_id) VALUES (58,8,2,0,null, 1);
INSERT INTO cartas(id,tipo_carta,estado_carta,posicion,jugador_id, tablero_id) VALUES (59,8,2,0,null, 1);
INSERT INTO cartas(id,tipo_carta,estado_carta,posicion,jugador_id, tablero_id) VALUES (60,8,2,0,null, 1);
INSERT INTO cartas(id,tipo_carta,estado_carta,posicion,jugador_id, tablero_id) VALUES (61,9,2,0,null, 1);
INSERT INTO cartas(id,tipo_carta,estado_carta,posicion,jugador_id, tablero_id) VALUES (62,9,2,0,null, 1);
INSERT INTO cartas(id,tipo_carta,estado_carta,posicion,jugador_id, tablero_id) VALUES (63,9,2,0,null, 1);
INSERT INTO cartas(id,tipo_carta,estado_carta,posicion,jugador_id, tablero_id) VALUES (64,9,2,0,null, 1);
INSERT INTO cartas(id,tipo_carta,estado_carta,posicion,jugador_id, tablero_id) VALUES (65,9,2,0,null, 1);
INSERT INTO cartas(id,tipo_carta,estado_carta,posicion,jugador_id, tablero_id) VALUES (66,9,2,0,null, 1);

INSERT INTO logros(id,nombre_logro,descripcion,objetivo,tipo_logro) VALUES (1,'Buen comienzo','Gana tu primera partida',1,2);
INSERT INTO logros(id,nombre_logro,descripcion,objetivo,tipo_logro) VALUES (2,'Profesional','Gana 5 partidas',5,2);
INSERT INTO logros(id,nombre_logro,descripcion,objetivo,tipo_logro) VALUES (3,'Campeón','Gana 10 partidas',10,2);
INSERT INTO logros(id,nombre_logro,descripcion,objetivo,tipo_logro) VALUES (4,'Imparable','Gana 20 partidas',20,2);
INSERT INTO logros(id,nombre_logro,descripcion,objetivo,tipo_logro) VALUES (5,'Inmejorable','Gana 50 partidas',50,2);
INSERT INTO logros(id,nombre_logro,descripcion,objetivo,tipo_logro) VALUES (6,'Todo un pirata','Gana 100 partidas',100,2);
INSERT INTO logros(id,nombre_logro,descripcion,objetivo,tipo_logro) VALUES (7,'Novato','Juega 10 partidas',10,1);
INSERT INTO logros(id,nombre_logro,descripcion,objetivo,tipo_logro) VALUES (8,'Principiante','Juega 25 partidas',25,1);
INSERT INTO logros(id,nombre_logro,descripcion,objetivo,tipo_logro) VALUES (9,'Profesional','Juega 50 partidas',50,1);
INSERT INTO logros(id,nombre_logro,descripcion,objetivo,tipo_logro) VALUES (10,'Veterano','Juega 100 partidas',100,1);
INSERT INTO logros(id,nombre_logro,descripcion,objetivo,tipo_logro) VALUES (11,'Un abrir y cerrar de ojos','Termina una partida en 10 minutos o menos',10,0);
INSERT INTO logros(id,nombre_logro,descripcion,objetivo,tipo_logro) VALUES (12,'¡Flash!','Termina una partida en 20 minutos o menos',20,0);
INSERT INTO logros(id,nombre_logro,descripcion,objetivo,tipo_logro) VALUES (13,'Justo a tiempo','Termina una partida en 30 minutos o menos',30,0);
INSERT INTO logros(id,nombre_logro,descripcion,objetivo,tipo_logro) VALUES (14,'Superando límites','Consigue 50 puntos en una sola partida',30,3);
INSERT INTO logros(id,nombre_logro,descripcion,objetivo,tipo_logro) VALUES (15,'Puntos y más puntos...','Consigue 75 puntos en una sola partida',30,3);
INSERT INTO logros(id,nombre_logro,descripcion,objetivo,tipo_logro) VALUES (16,'¡Botín!','Consigue 100 puntos en una sola partida',30,3);
INSERT INTO logros(id,nombre_logro,descripcion,objetivo,tipo_logro) VALUES (17,'¡Guinness!','Consigue 150 puntos en una sola partida',30,3);
INSERT INTO logros(id,nombre_logro,descripcion,objetivo,tipo_logro) VALUES (18,'Grumete','Acumula un total de 500 puntos',500,4);
INSERT INTO logros(id,nombre_logro,descripcion,objetivo,tipo_logro) VALUES (19,'Capitán','Acumula un total de 1000 puntos',1000,4);
INSERT INTO logros(id,nombre_logro,descripcion,objetivo,tipo_logro) VALUES (20,'Saqueador','Acumula un total de 2500 puntos',2500,4);
INSERT INTO logros(id,nombre_logro,descripcion,objetivo,tipo_logro) VALUES (21,'¿Jack Sparrow?','Acumula un total de 5000 puntos',5000,4);

INSERT INTO logro_jugador(logro_id, jugador_id) VALUES (1,1);
INSERT INTO logro_jugador(logro_id, jugador_id) VALUES (2,1);
INSERT INTO logro_jugador(logro_id, jugador_id) VALUES (3,1);
INSERT INTO logro_jugador(logro_id, jugador_id) VALUES (4,1);
INSERT INTO logro_jugador(logro_id, jugador_id) VALUES (5,1);
INSERT INTO logro_jugador(logro_id, jugador_id) VALUES (6,1);
INSERT INTO logro_jugador(logro_id, jugador_id) VALUES (7,1);
INSERT INTO logro_jugador(logro_id, jugador_id) VALUES (8,1);
INSERT INTO logro_jugador(logro_id, jugador_id) VALUES (9,1);
