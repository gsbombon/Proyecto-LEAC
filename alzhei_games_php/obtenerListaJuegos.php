<?php

    
    class Conexion {
        protected $dblink;
                
        function __construct() {
            $servidor = "mysql:host=localhost;dbname=alz_bd";
            $usuario = "root";
            $clave = "123";
            
            $this->dblink = new PDO($servidor, $usuario, $clave);
            $this->dblink->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
            $this->dblink->exec("SET NAMES utf8");
		}
    }
    
	class Usuario extends Conexion{
        public function listar(){
            include "conexion.php";

            $nombre = $_GET['nomPaciente'];
            $primeraConsulta = "SELECT USUARIO_ID FROM usuario WHERE USUARIO_NOMBRE = '$nombre';";
            $sql_resultado = $conexion->query($primeraConsulta);
            $rows = [];
            while($row = mysqli_fetch_array($sql_resultado)){
                $rows[] = $row;
            }
            $id=$rows[0][0];

            $sql = "SELECT JUEGO_NOMBRE FROM resultado r ,juego j  WHERE USUARIO_ID='$id' AND r.JUEGO_ID=j.JUEGO_ID GROUP BY JUEGO_NOMBRE;";
            $sentencia = $this->dblink->prepare($sql);
            $sentencia->execute();            
            return $sentencia->fetchAll(PDO::FETCH_OBJ);
        }  
	}
	$objFruta = new Usuario();
    $resultado = $objFruta->listar();
    $respuesta = array("usuario"=>$resultado);
    echo json_encode($respuesta);
	
	
?>