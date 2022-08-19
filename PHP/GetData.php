<?php
	$link = mysqli_connect("localhost", "root", "1111", "bmr") or die("Error with MySql connection!");
	$link -> set_charset("UTF8");

	$pname = $_POST['pname'];
	$psex = $_POST['psex'];
	$pweight = $_POST['pweight'];
	$pheight= $_POST['pheight'];
	$page = $_POST['page'];

	$result = $link -> query("INSERT INTO bmr VALUES('$pname','$psex','$pheight','$pweight','$page')");

	$link -> close();
?>