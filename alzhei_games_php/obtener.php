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
            $sql = "SELECT USUARIO_NOMBRE FROM usuario WHERE USUARIO_ROL='Cuidador'";
            $sentencia = $this->dblink->prepare($sql);
            $sentencia->execute();            
            return $sentencia->fetchAll(PDO::FETCH_OBJ);
        }  
	}
	$objFruta = new Usuario();
    $resultado = $objFruta->listar();
    $respuesta = array(
        "usuario"=>$resultado
    );
    echo json_encode($respuesta);	
?>