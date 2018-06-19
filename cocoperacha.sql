-- phpMyAdmin SQL Dump
-- version 4.6.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 19, 2018 at 02:22 PM
-- Server version: 5.7.14-log
-- PHP Version: 5.6.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `cocoperacha`
--

-- --------------------------------------------------------

--
-- Table structure for table `categoria`
--

CREATE TABLE `categoria` (
  `pkcategoria` int(11) NOT NULL,
  `nombre` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `envio`
--

CREATE TABLE `envio` (
  `pkenvio` int(11) NOT NULL,
  `nombre` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `producto`
--

CREATE TABLE `producto` (
  `pkproducto` int(11) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `imagen` longblob,
  `descripcionbreve` text NOT NULL,
  `descripcion` text NOT NULL,
  `fkcategoria` int(11) NOT NULL,
  `fkusuario` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `transaccion`
--

CREATE TABLE `transaccion` (
  `pktransaccion` int(11) NOT NULL,
  `fkusuariosolicitante` varchar(50) NOT NULL,
  `fkproducto` int(11) NOT NULL,
  `fkenvio` int(11) NOT NULL,
  `Elegido` tinyint(1) DEFAULT NULL,
  `megusta` tinyint(1) DEFAULT NULL,
  `comentario` text
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `usuario`
--

CREATE TABLE `usuario` (
  `correo` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `administrador` tinyint(1) NOT NULL DEFAULT '0',
  `nickname` varchar(50) NOT NULL,
  `direccion` text NOT NULL,
  `fechabloqueo` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `categoria`
--
ALTER TABLE `categoria`
  ADD PRIMARY KEY (`pkcategoria`);

--
-- Indexes for table `envio`
--
ALTER TABLE `envio`
  ADD PRIMARY KEY (`pkenvio`);

--
-- Indexes for table `producto`
--
ALTER TABLE `producto`
  ADD PRIMARY KEY (`pkproducto`),
  ADD KEY `fkusuario` (`fkusuario`),
  ADD KEY `fkcategoria` (`fkcategoria`);

--
-- Indexes for table `transaccion`
--
ALTER TABLE `transaccion`
  ADD PRIMARY KEY (`pktransaccion`),
  ADD KEY `fkusuariosolicitante` (`fkusuariosolicitante`),
  ADD KEY `fkproducto` (`fkproducto`),
  ADD KEY `fkenvio` (`fkenvio`);

--
-- Indexes for table `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`correo`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `categoria`
--
ALTER TABLE `categoria`
  MODIFY `pkcategoria` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `envio`
--
ALTER TABLE `envio`
  MODIFY `pkenvio` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `producto`
--
ALTER TABLE `producto`
  MODIFY `pkproducto` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `transaccion`
--
ALTER TABLE `transaccion`
  MODIFY `pktransaccion` int(11) NOT NULL AUTO_INCREMENT;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `producto`
--
ALTER TABLE `producto`
  ADD CONSTRAINT `producto_ibfk_1` FOREIGN KEY (`fkusuario`) REFERENCES `usuario` (`correo`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `producto_ibfk_2` FOREIGN KEY (`fkcategoria`) REFERENCES `categoria` (`pkcategoria`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `transaccion`
--
ALTER TABLE `transaccion`
  ADD CONSTRAINT `transaccion_ibfk_1` FOREIGN KEY (`fkenvio`) REFERENCES `envio` (`pkenvio`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `transaccion_ibfk_2` FOREIGN KEY (`fkusuariosolicitante`) REFERENCES `usuario` (`correo`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `transaccion_ibfk_3` FOREIGN KEY (`fkproducto`) REFERENCES `producto` (`pkproducto`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
