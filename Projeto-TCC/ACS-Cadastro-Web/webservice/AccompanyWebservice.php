<?php

$method = $_SERVER['REQUEST_METHOD'];

if ($method === AcsDataBase::POST) {

    $list = isset($_GET['o']) ? $_GET['o'] : false;
    $json = $_POST['JSON'];
    if($list){
        insertAll($json);
    }else {
        insert($json);
    }
}


function insert($json)
{
    
}

function insertAll($array)
{

}