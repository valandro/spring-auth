DROP TABLE IF EXISTS usuarios;


CREATE TABLE IF NOT EXISTS `usuarios` (
  `id` int(11) NOT NULL,
  `id_client` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `access_level` int(11) NOT NULL,
  `blocked` int(11) NOT NULL,
  PRIMARY KEY (`id`)
);

INSERT INTO USUARIOS (ID, ID_CLIENT, ACCESS_LEVEL, NAME, PASSWORD, BLOCKED) VALUES (1, 1, 1, 'Lucas', '52d7d8604bf71e968bf07d468c7d394bff7cb0bb142fd4da7a85dd6f8056a940', 1);