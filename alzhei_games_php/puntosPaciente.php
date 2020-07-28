<?php

include "conexion.php";

$nombre = $_GET['nombreAS'];

$primeraConsulta = "SELECT USUARIO_ID FROM usuario WHERE USUARIO_NOMBRE = '$nombre';";

$sql_resultado = $conexion->query($primeraConsulta);
$rows = [];
while($row = mysqli_fetch_array($sql_resultado))
{
    $rows[] = $row;
}
$id=$rows[0][0];


$sql = "SELECT sum(RESULTADO_PUNTUACION) AS SUMA FROM resultado WHERE USUARIO_ID='$id' GROUP BY RESULTADO_PUNTUACION";
$respuesta = $conexion->query($sql);

$juegos = [];
if (!$respuesta) { 
    echo "NO REGISTRA RESULTADOS ";
}else{
while ($fila=$respuesta->fetch_array()) {
    $suma[] = array_map('utf8_encode',$fila);
}
}
echo json_encode($suma);
$respuesta ->close();

?>