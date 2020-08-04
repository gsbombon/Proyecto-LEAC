<?php

$con=mysqli_connect("localhost","root","123","alz_bd");

$idPaciente=$_POST['idUser'];
$idJuego=$_POST['idJuego'];
$puntuacion=$_POST['puntuacion'];

$sql="INSERT INTO `resultado` (`RESULTADO_ID`, `USUARIO_ID`, `JUEGO_ID`, `RESULTADO_PUNTUACION`, `RESULTADO_FECHA`) VALUES (NULL, '$idPaciente', '$idJuego', '$puntuacion', current_timestamp());";
 if(mysqli_query($con,$sql)){
 echo("Yes");
 }else{
 echo("No");
 }

?>