<?php
$hostname_localhost="localhost";
$database_localhost="recyapp";
$username_localhost="root";
$password_localhost="";

$json=array();

if(isset($_GET["usuario"]) && isset($_GET["contrasenia"])){
    $usuario=$_GET['usuario'];
    $contrasenia=$_GET['contrasenia'];

    $conexion= new mysqli($hostname_localhost,$username_localhost,$password_localhost,$database_localhost);
	
	$consulta="SELECT * FROM usuarios WHERE usuario = '{$usuario}' AND contrasenia = '{$contrasenia}'";
	$resultado=$conexion->query($consulta);
			
    if($registro=mysqli_fetch_array($resultado)){
        $json['usuario'][]=$registro;
    } else {
        $resulta["usuario"]='No Registra';
        $resulta["contrasenia"]='No Registra';
        $json['usuario'][]=$resulta;
        echo json_encode($json);
    }
    mysqli_close($conexion);
    echo json_encode($json);
} else{
    $resulta["usuario"]='WS No retorna';
    $resulta["contrasenia"]='WS No retorna';
    $json['usuario'][]=$resulta;
    echo json_encode($json);
}