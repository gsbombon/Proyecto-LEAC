<?php

$con=mysqli_connect("localhost","root","123","alz_bd");

$usuario=$_POST['usuario'];
$email=$_POST['email'];
$password=$_POST['password'];
$telefono=$_POST['telefono'];
$rol=$_POST['rol'];
$medico=$_POST['medico'];
$cuidador=$_POST['cuidador'];
$grado=$_POST['grado'];

/*
$usuario="Luis Sanchez";
$email="luis@gmail.com";
$password="123";
$telefono="0988112756";
$rol="Paciente";
$medico="Dr Albuja";
$cuidador="Pedro Sanchez";
$grado="bajo";
*/

$sql='SELECT * FROM `usuario` WHERE `USUARIO_NOMBRE`="'.$medico.'" ';
$sql2='SELECT * FROM `usuario` WHERE `USUARIO_NOMBRE`="'.$cuidador.'" ';
$res=mysqli_query($con,$sql);
$res2=mysqli_query($con,$sql2);

while($reg=mysqli_fetch_array($res)){
$idMedico= $reg[0];
}
while($reg=mysqli_fetch_array($res2)){
    $idCuidador= $reg[0];
    }
echo $idMedico;
echo $idCuidador;


 
 
$sql="INSERT INTO `usuario` (`USUARIO_ID`, `USUARIO_NOMBRE`, `USUARIO_EMAIL`, `USUARIO_PASSWORD`, `USUARIO_TELEFONO`, `USUARIO_ROL`) VALUES (NULL, '$usuario', '$email', '$password', '$telefono', '$rol');";
 if(mysqli_query($con,$sql)){
 echo("Yes");
 }else{
 echo("No");
 }
 $sql3='SELECT * FROM `usuario` WHERE `USUARIO_NOMBRE`="'.$usuario.'" ';
 $res3=mysqli_query($con,$sql3);

while($reg=mysqli_fetch_array($res3)){
$idPaciente= $reg[0];
}
$sql="INSERT INTO `paciente` (`USUARIO_ID`, `MED_USUARIO_ID`, `CUI_USUARIO_ID`, `PACIENTE_GRADO`) VALUES ('$idPaciente', '$idMedico', '$idCuidador', '$grado');";
 if(mysqli_query($con,$sql)){
 echo("Yes");
 }else{
 echo("No");
 }


 

 
 

?>