drop database if exists client_db;
create database if not exists client_db default character set utf8mb4;

CREATE TABLE client_db.clients(
    client_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    client_name VARCHAR(100),
    client_type VARCHAR(20),
    datereg TIMESTAMP
);

CREATE TABLE client_db.address (
	ip VARCHAR(25),
	mac VARCHAR(20) PRIMARY KEY,
	model VARCHAR(200),
	address VARCHAR(200),
    cl_id BIGINT,
    FOREIGN KEY (cl_id) REFERENCES client_db.clients (client_id) ON DELETE CASCADE
);

INSERT INTO client_db.clients(client_name, client_type, datereg) VALUES ('Матвеев Андрей', 'Физическое лицо', NOW());
INSERT INTO client_db.clients(client_name, client_type, datereg) VALUES ('ООО Кондор', 'Юридическое лицо', NOW());
INSERT INTO client_db.clients(client_name, client_type, datereg) VALUES ('Макаров Егор', 'Физическое лицо', NOW());
INSERT INTO client_db.address(ip, mac, model, address, cl_id) VALUES('192.168.52.20','2B-3G-5G-34-57-Y6','XYL-001','Russia, Moscow, ul. Lesnaya, d. 5, kv. 176',1);
INSERT INTO client_db.address(ip, mac, model, address, cl_id) VALUES('192.168.0.143','2C-5G-9H-39-66-XO','ZTK-076','Russia, Moscow, ul. Lenina, d. 20, kv. 786',1);
INSERT INTO client_db.address(ip, mac, model, address, cl_id) VALUES('192.168.112.5','67-45-JK-14-76-IO','XYL-001','Russia, Saint Petersburg, ul. Sosnovaia, d. 33, kv. 11',2);
INSERT INTO client_db.address(ip, mac, model, address, cl_id) VALUES('192.168.34.102','HG-YU-77-H0-47-83','ZTK-002','Russia, Saint Petersburg, ul. Shishkina, d. 23, kv. 54',2);
INSERT INTO client_db.address(ip, mac, model, address, cl_id) VALUES('192.168.145.52','43-67-JK-15-87-IO','XYL-008','Russia, Saint Petersburg, ul. Polikova, d. 11, kv. 1232',3);
INSERT INTO client_db.address(ip, mac, model, address, cl_id) VALUES('192.168.42.23','YU-YU-88-9H-47-34','ZTK-007','Russia, Saint Petersburg, ul. Pavlova, d. 64, kv. 123',3);