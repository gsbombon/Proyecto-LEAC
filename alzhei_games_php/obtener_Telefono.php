
<?php
include 'conexion.php';
$idPaciente=$_POST['idPaciente'];
//$idPaciente="1";
$con=mysqli_connect("localhost","root","123","alz_bd");
$sql3="SELECT c.USUARIO_ID FROM usuario u, paciente p, cuidador c WHERE u.USUARIO_ID=p.USUARIO_ID AND p.CUI_USUARIO_ID=c.USUARIO_ID AND p.USUARIO_ID='$idPaciente'";
$res3=mysqli_query($con,$sql3);

while($reg=mysqli_fetch_array($res3)){
$idCuidador= $reg[0];
}


$sentencia=$conexion->prepare("SELECT USUARIO_TELEFONO FROM usuario WHERE usuario.USUARIO_ID=?");
$sentencia->bind_param('s',$idCuidador);
$sentencia->execute();
function get_result( $stmt) {
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