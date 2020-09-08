<?php
include('functions.php'); 

$nombreAS = $_GET['nombreAS'];
$nombreJuego = $_GET['nomJuego'];


	$rows = [];
	if ($resultset = getSQLResultSet("SELECT USUARIO_ID FROM usuario WHERE USUARIO_NOMBRE='$nombreAS'")) {
		while($row = $resultset->fetch_array(MYSQLI_NUM)){
			$rows[] = $row;
		}
	}
	$id=$rows[0][0];

	if ($resultset1 = getSQLResultSet("SELECT JUEGO_NOMBRE,RESULTADO_PUNTUACION,RESULTADO_FECHA FROM resultado r ,juego j WHERE USUARIO_ID='$id' AND r.JUEGO_ID=j.JUEGO_ID AND JUEGO_NOMBRE='$nombreJuego';")) {
    	while ($fila = $resultset1->fetch_array(MYSQLI_NUM)) {
    		echo json_encode($fila);	
    	}
   }
?>
