<?php
	$server		= "localhost"; 
	$user		= "root"; 
	$password	= "";
	$namadb	        = "TIUMY";
	$port		= 3307;

	$conn = mysqli_connect($server, $user, $password, $namadb, $port) or die ("Koneksi gagal!");
?>