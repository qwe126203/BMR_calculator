<?php
	$link = mysqli_connect("localhost", "root", "1111", "bmr");
	$link -> set_charset("UTF8");

	$pname = $_POST['pname'];
	$psex = $_POST['psex'];
	$pweight = $_POST['pweight'];
	$pheight= $_POST['pheight'];
	$page = $_POST['page'];

	$oname = $_POST['oname'];
	$osex = $_POST['osex'];
	$oweight = $_POST['oweight'];
	$oheight= $_POST['oheight'];
	$oage = $_POST['oage'];

	$result = $link -> query("UPDATE bmr SET name='$pname',sex='$psex',weight='$pweight',height='$pheight',age='$page' WHERE name='$oname'");

	$link -> close();
?>