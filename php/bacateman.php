<?php
	include_once "koneksi.php";


	$result	= mysqli_query($conn,"SELECT * FROM teman order by nama");
	$json = array();
	
	while($row = mysqli_fetch_assoc($result)){
		$json[] = $row;
	}
	
	echo json_encode($json);
	
	mysqli_close($conn);
	
?>