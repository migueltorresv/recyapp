<?php
$hostname_localhost="localhost";
$database_localhost="recyapp";
$username_localhost="root";
$password_localhost="";

$json=array();

	if(isset($_GET["idUsuario"]) && isset($_GET["Residuos_idResiduo"])){
		$idUsuario=$_GET['idUsuario'];
		$Residuos_idResiduo=$_GET['Residuos_idResiduo'];
		
		$conexion= new mysqli($hostname_localhost,$username_localhost,$password_localhost,$database_localhost);
		
		$insert="INSERT INTO puntosobt (idPunto,fecha,idUsuario,Residuos_idResiduo) 
        VALUES (null,NOW(),'{$idUsuario}','{$Residuos_idResiduo}')";
		
		if($conexion->query($insert)){
			$consulta="SELECT * FROM puntosobt WHERE idUsuario = '{$idUsuario}'";
			$resultado=$conexion->query($consulta);
			
			if($registro=mysqli_fetch_array($resultado)){
				$json['puntosObt'][]=$registro;
			}
			mysqli_close($conexion);
			echo json_encode($json);
		}
		else{
			$resulta["idUsuario"]=0;
			$resulta["Residuos_idResiduo"]='No Registra';
			$json['puntosObt'][]=$resulta;
			echo json_encode($json);
		}
		
	}
	else{
			$resulta["idUsuario"]=0;
			$resulta["Residuos_idResiduo"]='WS No retorna';
			$json['puntosObt'][]=$resulta;
			echo json_encode($json);
		}

