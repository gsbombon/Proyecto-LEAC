<?php
    
     //require "conexion.php";
	$mysqli = new mysqli("localhost","root","123","alz_bd");
    
     $accion = $_POST['accion'];
     $mensaje = $_POST['mensaje'];
$idPaciente = $_POST['idPaciente'];
$idCuidador = $_POST['idCuidador'];
    //$accion = "";
    //$mensaje = "Bien y tu";
//$idPaciente = "1";
    
    if($accion == "nuevo") {

        $sql_insert = "INSERT INTO `mensaje` (`MENSAJE_ID`, `MENSAJE_TEXTO`, `ID_PACIENTE`, `ID_CUIDADOR`, `MENSAJE_FECHA`, `MENSAJE_EMISOR`) VALUES (NULL, '$mensaje', '$idPaciente', '$idCuidador', current_timestamp(), 'Cuidador');";
        $query = $mysqli->query($sql_insert);

        $sql_consult = "SELECT * FROM mensajes";
        $query = $mysqli->query($sql_consult);
        
        $data = array();
        
        $num = $query->num_rows;
        
        if($num > 0) {
            while($resultado = $query->fetch_assoc()) {
                $data[] = $resultado;
            }
            echo json_encode(array("mensajes" => $data));
        }
        
        $mysqli->close();

        
    } else {
        $sql_consult = "SELECT MENSAJE_TEXTO,MENSAJE_EMISOR,MENSAJE_FECHA FROM mensaje WHERE ID_PACIENTE='$idPaciente' ORDER BY MENSAJE_FECHA DESC";
        $query = $mysqli->query($sql_consult);
        
        $data = array();
        
        $num = $query->num_rows;
        
        if($num > 0) {
            while($resultado = $query->fetch_assoc()) {
                $data[] = $resultado;
            }
            echo json_encode(array("mensajes" => $data));
        }
        
        $mysqli->close();
    }
    
     ?>
