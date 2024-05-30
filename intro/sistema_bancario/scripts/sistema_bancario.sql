CREATE DATABASE  IF NOT EXISTS `bancorrw` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `bancorrw`;
DROP TABLE IF EXISTS `contas_investimento`;
DROP TABLE IF EXISTS `contas_corrente`;
DROP TABLE IF EXISTS `contas`;
DROP TABLE IF EXISTS `clientes`;
CREATE TABLE `clientes` (
  `id_cliente` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(300) DEFAULT NULL,
  `cpf` varchar(45) DEFAULT NULL,
  `data_nascimento` date DEFAULT NULL,
  `cartao_credito` varchar(45) DEFAULT NULL,
  `id_conta_corrente` int DEFAULT NULL,
  PRIMARY KEY (`id_cliente`),
  KEY `cliente_conta_corrente_idx` (`id_conta_corrente`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `contas` (
  `id_conta` int NOT NULL AUTO_INCREMENT,
  `id_cliente` int DEFAULT NULL,
  `saldo` double DEFAULT NULL,
  PRIMARY KEY (`id_conta`),
  KEY `conta_cliente_idx` (`id_cliente`),
  CONSTRAINT `conta_cliente` FOREIGN KEY (`id_cliente`) REFERENCES `clientes` (`id_cliente`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
CREATE TABLE `contas_corrente` (
  `id_conta` int NOT NULL,
  `limite` double DEFAULT NULL,
  `taxa_juros_limite` double DEFAULT NULL,
  PRIMARY KEY (`id_conta`),
  CONSTRAINT `conta_conta_corrente` FOREIGN KEY (`id_conta`) REFERENCES `contas` (`id_conta`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `contas_investimento` (
  `id_conta` int NOT NULL AUTO_INCREMENT,
  `taxa_remuneracao_investimento` double DEFAULT NULL,
  `montante_minimo` double DEFAULT NULL,
  `deposito_minimo` double DEFAULT NULL,
  PRIMARY KEY (`id_conta`),
  CONSTRAINT `conta_conta_investimento` FOREIGN KEY (`id_conta`) REFERENCES `contas` (`id_conta`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;