<?php
include 'conexion.php';
$user_usuario=$_POST['usuario'];
$user_password=$_POST['password'];
 
//$usu_usuario="aroncal@gmail.com";
//$usu_password="12345678";

$sentencia=$conexion->prepare("SELECT * FROM usuario WHERE user_usuario=? AND user_password=?");
$sentencia->bind_param('ss',$user_usuario,$user_password);
$sentencia->execute();

function get_result( $stmt ) {
    $arrResult = array();
    $stmt->store_result();
    for ( $i = 0; $i < $stmt->num_rows; $i++ ) {
        $metadata = $stmt->result_metadata();
        $arrParams = array();
        while ( $field = $metadata->fetch_field() ) {
            $arrParams[] = &$arrResult[ $i ][ $field->name ];
        }
        call_user_func_array( array( $stmt, 'bind_result' ), $arrParams );
        $stmt->fetch();
    }
    return $arrResult;
}

$resultado = $sentencia->get_result();
if ($fila = $resultado->fetch_assoc()) {
         echo json_encode($fila,JSON_UNESCAPED_UNICODE);     
}
$sentencia->close();
$conexion->close();
?>