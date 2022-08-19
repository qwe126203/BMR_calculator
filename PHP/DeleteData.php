<?php
	$link = mysqli_connect("localhost", "root", "1111", "bmr");
	$link -> set_charset("UTF8");

	$pname = $_POST['pname'];
	$psex = $_POST['psex'];
	$pweight = $_POST['pweight'];
	$pheight= $_POST['pheight'];
	$page = $_POST['page'];

	$result = $link -> query("DELETE FROM bmr WHERE name='$pname' AND sex='$psex' AND weight='$pweight' AND height='$pheight' AND age='$page'");

	$link -> close();
?>