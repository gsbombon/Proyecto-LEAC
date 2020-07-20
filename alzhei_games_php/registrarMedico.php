<?php

$con=mysqli_connect("localhost","root","123","alz_bd");

$usuario=$_POST['usuario'];
$email=$_POST['email'];
$password=$_POST['password'];
$telefono=$_POST['telefono'];
$rol=$_POST['rol'];
$especialidad=$_POST['especialidad'];


/*
$usuario="Ramiro Sanchez";
$email="ramiro@gmail.com";
$password="123";
$telefono="0988112756";
$rol="Medico";
$especialidad="Neurologo";
*/

 
$sql="INSERT INTO `usuario` (`USUARIO_ID`, `USUARIO_NOMBRE`, `USUARIO_EMAIL`, `USUARIO_PASSWORD`, `USUARIO_TELEFONO`, `USUARIO_ROL`) VALUES (NULL, '$usuario', '$email', '$password', '$telefono', '$rol');";
 if(mysqli_query($con,$sql)){
 echo("Yes");
 }else{
 echo("No");
 }
 $sql3='SELECT * FROM `usuario` WHERE `USUARIO_NOMBRE`="'.$usuario.'" ';
 $res3=mysqli_query($con,$sql3);

while($reg=mysqli_fetch_array($res3)){
$idMedico= $reg[0];
}
$sql="INSERT INTO `medico` (`USUARIO_ID`, `MEDICO_ESPECIALIDA`) VALUES ('$idMedico', '$especialidad');";
 if(mysqli_query($con,$sql)){
 echo("Yes");
 }else{
 echo("No");
 }


 

 
 

?>