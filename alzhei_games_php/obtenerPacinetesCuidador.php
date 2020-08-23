<?php
    
     //require "conexion.php";
	$mysqli = new mysqli("localhost","root","123","alz_bd");
        $sql_consult = "SELECT u.USUARIO_ID,u.USUARIO_NOMBRE FROM paciente p,usuario u WHERE CUI_USUARIO_ID='3' AND p.USUARIO_ID=u.USUARIO_ID";
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
    
    
     ?>
