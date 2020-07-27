-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 27-07-2020 a las 21:29:19
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
(3, 'Matutino');

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
(1, 'Memorama');

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
(2, 'Neurologo');

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
(1, 2, 3, 'Bajo');

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
(10, 1, 1, '0', '2020-07-27 05:21:16'),
(11, 1, 1, '0', '2020-07-27 05:25:13'),
(12, 1, 1, '0', '2020-07-27 05:31:45'),
(13, 1, 1, '6', '2020-07-27 05:33:30'),
(14, 1, 1, '33', '2020-07-27 05:37:42'),
(15, 1, 1, '33', '2020-07-27 06:04:50'),
(16, 1, 1, '0', '2020-07-27 18:15:50'),
(17, 1, 1, '0', '2020-07-27 18:21:44'),
(18, 1, 1, '0', '2020-07-27 18:24:24'),
(19, 1, 1, '50', '2020-07-27 18:28:00'),
(20, 1, 1, '40', '2020-07-27 18:44:53');

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
(3, 'Pedro Sanchez', 'pedro@gmail.com', '123', '988112757', 'Cuidador');

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
  MODIFY `JUEGO_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `resultado`
--
ALTER TABLE `resultado`
  MODIFY `RESULTADO_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `USUARIO_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

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
