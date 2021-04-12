<?php
$hostname_localhost="localhost";
$database_localhost="recyapp";
$username_localhost="root";
$password_localhost="";

$json=array();
    
if(isset($_GET["idUsuario"])){
    $idUsuario=$_GET['idUsuario'];

    $conexion= new mysqli($hostname_localhost,$username_localhost,$password_localhost,$database_localhost);
	
	$consulta="SELECT usr.idUsuario, usr.nombres AS nombreUsuario, resi.nombre AS residuo, resi.puntos, pts.fecha 
    FROM usuarios AS usr INNER JOIN puntosobt AS pts ON usr.idUsuario = pts.idUsuario INNER JOIN residuos AS resi ON 
    pts.Residuos_idResiduo = resi.idResiduo WHERE usr.idUsuario = '{$idUsuario}' ORDER BY pts.fecha DESC";
	$resultado=$conexion->query($consulta);
			
        while($lista=mysqli_fetch_array($resultado)){
            $json['listPuntosObt'][]=$lista;
        }
        mysqli_close($conexion);
        echo json_encode($json);
} else{

    $lista["idUsuario"]=0;
    $json['listPuntosObt'][]=$lista;
    echo json_encode($json);
}