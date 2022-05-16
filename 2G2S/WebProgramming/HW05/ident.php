<?php
    $server = "pnuailab.synology.me:3310";
    $user = "201724539";
    $pass = "201724539";
    $conn = new mysqli($server, $user, $pass,"201724539");

    $studName = $_POST["name"];
    $studID = $_POST["ID"];
    $studEmail = $_POST["e-mail"];
    $studPhoto = $_POST["photo"];

    $sql = "INSERT INTO info(fullname,id,email,photo) VALUES('$studName','$studID','$studEmail','$studPhoto');";

    $result = mysqli_query($conn,$sql);
    if($result === false){
        echo mysqli_error($conn);
    }
?>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8"> 
        <title>Your ID</title>
    </head>

    <body>
        <div style="height:200px; width:400px;border:7px solid;">
        <h3 style="text-align: center;">신분증</h3>
        <?php
            echo "<img src=$studPhoto alt='student_photo' style='float:right; width:120px; height:120px;'>";
            echo "<h4>이름: $studName</h4>";
            echo "<h4>학번: $studID</h4>";
            echo "<h4>이메일: $studEmail</h4>";
        ?>
        </div>
        
        <a href = "database.php"> Show database </a>
</body>
</html>