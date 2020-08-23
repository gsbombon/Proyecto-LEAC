<?php
$mysqli = new mysqli("localhost","root","123","alz_bd");

$idPaciente = "4";
    
$sql3="SELECT CUI_USUARIO_ID FROM `paciente` WHERE `USUARIO_ID`='$idPaciente'";
 $res3=$mysqli->query($sql3);

while($reg=$res3->fetch_array()){
$idCuidador= $reg[0];
}
echo $idCuidador;


 
 

?>