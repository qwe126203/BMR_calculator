<?php
	$link = mysqli_connect("localhost", "root", "1111", "bmr");
	$link -> set_charset("UTF8");

	$result = $link -> query("SELECT * FROM bmr");

	while($row = $result->fetch_assoc()){
		$output[] = $row;
	}

	$link -> close();
	echo json_encode($output);
?>