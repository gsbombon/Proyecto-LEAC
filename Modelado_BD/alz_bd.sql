-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 23-08-2020 a las 04:42:05
-- Versión del servidor: 10.4.13-MariaDB
-- Versión de PHP: 7.2.32

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `alz_bd`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cuidador`
--

CREATE TABLE `cuidador` (
  `USUARIO_ID` int(11) NOT NULL,
  `CUIDADOR_HORARIO` char(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `cuidador`
--

INSERT INTO `cuidador` (`USUARIO_ID`, `CUIDADOR_HORARIO`) VALUES
(3, 'Matutino'),
(13, 'Nocturno');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `juego`
--

CREATE TABLE `juego` (
  `JUEGO_ID` int(11) NOT NULL,
  `JUEGO_NOMBRE` char(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `juego`
--

INSERT INTO `juego` (`JUEGO_ID`, `JUEGO_NOMBRE`) VALUES
(1, 'Memorama'),
(2, 'Adivina'),
(3, 'Rompecabezas');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `medico`
--

CREATE TABLE `medico` (
  `USUARIO_ID` int(11) NOT NULL,
  `MEDICO_ESPECIALIDA` char(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `medico`
--

INSERT INTO `medico` (`USUARIO_ID`, `MEDICO_ESPECIALIDA`) VALUES
(2, 'Neurologo'),
(12, 'Medicina física y rehabilitaci');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `mensaje`
--

CREATE TABLE `mensaje` (
  `MENSAJE_ID` int(11) NOT NULL,
  `MENSAJE_TEXTO` varchar(100) CHARACTER SET utf8 NOT NULL,
  `ID_PACIENTE` int(11) NOT NULL,
  `ID_CUIDADOR` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `mensaje`
--

INSERT INTO `mensaje` (`MENSAJE_ID`, `MENSAJE_TEXTO`, `ID_PACIENTE`, `ID_CUIDADOR`) VALUES
(1, 'Hola Amigo', 1, 3),
(2, 'Hola amigo', 4, 3),
(6, 'Bien y tu', 4, 3),
(7, 'jjjjjjjjjjjj', 1, 3),
(8, 'HOLAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA COMOAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA ESTAS ', 1, 3),
(9, 'HOLAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA COMOAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA ESTAS ', 1, 3),
(10, 'nihola estoy bien', 1, 3),
(11, 'hola Ramiro mena', 4, 3),
(12, 'hola Ramiro mena', 4, 3),
(13, 'hola Ramiro mena', 4, 3),
(14, 'hola', 11, 3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `paciente`
--

CREATE TABLE `paciente` (
  `USUARIO_ID` int(11) NOT NULL,
  `MED_USUARIO_ID` int(11) NOT NULL,
  `CUI_USUARIO_ID` int(11) NOT NULL,
  `PACIENTE_GRADO` char(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `paciente`
--

INSERT INTO `paciente` (`USUARIO_ID`, `MED_USUARIO_ID`, `CUI_USUARIO_ID`, `PACIENTE_GRADO`) VALUES
(1, 2, 3, 'Bajo'),
(4, 2, 3, 'Bajo'),
(10, 2, 3, 'Bajo'),
(11, 2, 3, 'Bajo');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `resultado`
--

CREATE TABLE `resultado` (
  `RESULTADO_ID` int(11) NOT NULL,
  `USUARIO_ID` int(11) NOT NULL,
  `JUEGO_ID` int(11) NOT NULL,
  `RESULTADO_PUNTUACION` decimal(10,0) NOT NULL,
  `RESULTADO_FECHA` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `resultado`
--

INSERT INTO `resultado` (`RESULTADO_ID`, `USUARIO_ID`, `JUEGO_ID`, `RESULTADO_PUNTUACION`, `RESULTADO_FECHA`) VALUES
(9, 1, 1, '12', '2020-07-27 05:04:21'),
(15, 1, 1, '33', '2020-07-27 06:04:50'),
(19, 1, 1, '50', '2020-07-27 18:28:00'),
(20, 1, 1, '40', '2020-07-27 18:44:53'),
(21, 4, 1, '75', '2020-07-28 04:16:58'),
(22, 1, 2, '20', '2020-07-28 21:37:49'),
(23, 1, 1, '40', '2020-07-28 22:42:56'),
(24, 1, 2, '20', '2020-07-28 22:44:43'),
(25, 1, 1, '50', '2020-07-28 23:13:32'),
(26, 4, 1, '33', '2020-08-11 01:19:22');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `USUARIO_ID` int(11) NOT NULL,
  `USUARIO_NOMBRE` char(50) NOT NULL,
  `USUARIO_EMAIL` char(30) NOT NULL,
  `USUARIO_PASSWORD` char(30) NOT NULL,
  `USUARIO_TELEFONO` decimal(11,0) NOT NULL,
  `USUARIO_ROL` char(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`USUARIO_ID`, `USUARIO_NOMBRE`, `USUARIO_EMAIL`, `USUARIO_PASSWORD`, `USUARIO_TELEFONO`, `USUARIO_ROL`) VALUES
(1, 'Carlos Perez', 'carlos@gmail.com', '123', '988112757', 'Paciente'),
(2, 'Dr Albuja', 'albuja@gmail.com', '123', '988112755', 'Medico'),
(3, 'Pedro Sanchez', 'pedro@gmail.com', '123', '998862983', 'Cuidador'),
(4, 'Ramiro Mena', 'ramiro@gmail.com', '123', '988112756', 'Paciente'),
(5, '', '', '', '0', 'Paciente'),
(6, '', '', '', '0', 'Paciente'),
(7, '', '', '', '0', 'Paciente'),
(8, '', '', '', '0', 'Paciente'),
(9, '', '', '', '0', 'Paciente'),
(10, '', '', '', '0', 'Paciente'),
(11, 'Luis Loachamin', 'luis@gmail.com', '123', '988112756', 'Paciente'),
(12, 'Mario lopez', 'mario@gmail.com', '123', '988112756', 'Medico'),
(13, 'Arturo gomez', 'arturo@gmail.com', '123', '988112756', 'Cuidador');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `cuidador`
--
ALTER TABLE `cuidador`
  ADD PRIMARY KEY (`USUARIO_ID`);

--
-- Indices de la tabla `juego`
--
ALTER TABLE `juego`
  ADD PRIMARY KEY (`JUEGO_ID`);

--
-- Indices de la tabla `medico`
--
ALTER TABLE `medico`
  ADD PRIMARY KEY (`USUARIO_ID`);

--
-- Indices de la tabla `mensaje`
--
ALTER TABLE `mensaje`
  ADD PRIMARY KEY (`MENSAJE_ID`),
  ADD KEY `FK_PACIENTE` (`ID_PACIENTE`),
  ADD KEY `FK_CUIDADOR` (`ID_CUIDADOR`);

--
-- Indices de la tabla `paciente`
--
ALTER TABLE `paciente`
  ADD PRIMARY KEY (`USUARIO_ID`),
  ADD KEY `FK_RELATIONSHIP_2` (`MED_USUARIO_ID`),
  ADD KEY `FK_RELATIONSHIP_3` (`CUI_USUARIO_ID`);

--
-- Indices de la tabla `resultado`
--
ALTER TABLE `resultado`
  ADD PRIMARY KEY (`RESULTADO_ID`,`USUARIO_ID`,`JUEGO_ID`),
  ADD KEY `FK_RESULTADO` (`USUARIO_ID`),
  ADD KEY `FK_RESULTADO2` (`JUEGO_ID`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`USUARIO_ID`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `juego`
--
ALTER TABLE `juego`
  MODIFY `JUEGO_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `mensaje`
--
ALTER TABLE `mensaje`
  MODIFY `MENSAJE_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT de la tabla `resultado`
--
ALTER TABLE `resultado`
  MODIFY `RESULTADO_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;

--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `USUARIO_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `cuidador`
--
ALTER TABLE `cuidador`
  ADD CONSTRAINT `FK_ES2` FOREIGN KEY (`USUARIO_ID`) REFERENCES `usuario` (`USUARIO_ID`);

--
-- Filtros para la tabla `medico`
--
ALTER TABLE `medico`
  ADD CONSTRAINT `FK_ES3` FOREIGN KEY (`USUARIO_ID`) REFERENCES `usuario` (`USUARIO_ID`);

--
-- Filtros para la tabla `mensaje`
--
ALTER TABLE `mensaje`
  ADD CONSTRAINT `mensaje_ibfk_1` FOREIGN KEY (`ID_PACIENTE`) REFERENCES `paciente` (`USUARIO_ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `mensaje_ibfk_2` FOREIGN KEY (`ID_CUIDADOR`) REFERENCES `cuidador` (`USUARIO_ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `paciente`
--
ALTER TABLE `paciente`
  ADD CONSTRAINT `FK_ES` FOREIGN KEY (`USUARIO_ID`) REFERENCES `usuario` (`USUARIO_ID`),
  ADD CONSTRAINT `FK_RELATIONSHIP_2` FOREIGN KEY (`MED_USUARIO_ID`) REFERENCES `medico` (`USUARIO_ID`),
  ADD CONSTRAINT `FK_RELATIONSHIP_3` FOREIGN KEY (`CUI_USUARIO_ID`) REFERENCES `cuidador` (`USUARIO_ID`);

--
-- Filtros para la tabla `resultado`
--
ALTER TABLE `resultado`
  ADD CONSTRAINT `FK_RESULTADO` FOREIGN KEY (`USUARIO_ID`) REFERENCES `paciente` (`USUARIO_ID`),
  ADD CONSTRAINT `FK_RESULTADO2` FOREIGN KEY (`JUEGO_ID`) REFERENCES `juego` (`JUEGO_ID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
