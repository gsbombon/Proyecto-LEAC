<?php

include "conexion.php";

$nombre = $_GET['nombreAS'];
$nomJuego = $_GET['nomJuego'];

$primeraConsulta = "SELECT USUARIO_ID FROM usuario WHERE USUARIO_NOMBRE = '$nombre';";

$sql_resultado = $conexion->query($primeraConsulta);
$rows = [];
while($row = mysqli_fetch_array($sql_resultado))
{
    $rows[] = $row;
}
$id=$rows[0][0];


$sql = "SELECT SUM(RESULTADO_PUNTUACION) as SUMA FROM resultado r ,juego j WHERE USUARIO_ID='$id' AND r.JUEGO_ID=j.JUEGO_ID AND JUEGO_NOMBRE='$nomJuego';";
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