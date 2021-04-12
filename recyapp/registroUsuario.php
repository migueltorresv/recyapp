<?php
$hostname_localhost="localhost";
$database_localhost="recyapp";
$username_localhost="root";
$password_localhost="";

$json=array();

	if(isset($_GET["nombres"]) && isset($_GET["usuario"]) && isset($_GET["contrasenia"])){
		$nombres=$_GET['nombres'];
		$usuario=$_GET['usuario'];
		$contrasenia=$_GET['contrasenia'];
		
		$conexion= new mysqli($hostname_localhost,$username_localhost,$password_localhost,$database_localhost);
		
		$insert="INSERT INTO usuarios(idUsuario, nombres, usuario, contrasenia, fechaRegistro) 
        VALUES (null,'{$nombres}','{$usuario}','{$contrasenia}',NOW())";
		
		if($conexion->query($insert)){
			$consulta="SELECT * FROM usuarios WHERE nombres = '{$nombres}'";
			$resultado=$conexion->query($consulta);
			
			if($registro=mysqli_fetch_array($resultado)){
				$json['usuario'][]=$registro;
			}
			mysqli_close($conexion);
			echo json_encode($json);
		}
		else{
			$resulta["nombres"]=0;
			$resulta["usuario"]='No Registra';
			$resulta["contrasenia"]='No Registra';
			$json['usuario'][]=$resulta;
			echo json_encode($json);
		}
		
	}
	else{
			$resulta["nombres"]=0;
			$resulta["usuario"]='WS No retorna';
			$resulta["contrasenia"]='WS No retorna';
			$json['usuario'][]=$resulta;
			echo json_encode($json);
		}

