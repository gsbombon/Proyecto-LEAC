<?php
$hostname='localhost';
$database='alzhei_games';
$username='root';
$password='123';

$conexion=new mysqli($hostname,$username,$password,$database);
if($conexion->connect_errno){
    echo "El sitio web está experimentado problemas";
}
?>