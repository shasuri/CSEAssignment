<?php
    $server = "pnuailab.synology.me:3310";
    $user = "201724539";
    $pass = "201724539";
    $conn = new mysqli($server, $user, $pass,"201724539");
?>

<!DOCTYPE html>

<html>
    <head>
        <meta charset = "utf-8">
        <title>Database Accessed</title>
        <style>
            th,td {padding:15px;}
            table{border-spacing:10px;}
        </style>
    </head>

    <body>
        <?php
            $query = "SELECT * FROM info";
            $result = mysqli_query($conn,$query);

            if($result === false){
                echo mysqli_error($conn);
            }
        ?>

        <h1>Submitted Information</h1>
        <table>
            <tr>
                <th>Name</th>
                <th>Student ID</th>
                <th>E-mail</th>
                <th>Photo</th>
            </tr>

            <?php
                for($index=0;$row=mysqli_fetch_row($result); ++$index)
                {
                    echo "<tr>";
                    
                    foreach($row as $key => $value){
                        
                        if($key == 3)
                            echo "<td><img src = $value style='width:120px; height:120px;'></td>";
                        else
                            echo "<td>$value</td>";
                    }
                    echo "</tr>";
                }

                mysqli_close($conn);
            ?>
        </table>
    </body>
</html>