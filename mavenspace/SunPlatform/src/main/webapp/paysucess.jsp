<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<title>评价成功</title>
<link rel="stylesheet" type="text/css" href="../style/bootstrap.css" />
<link rel="stylesheet" type="text/css" href="../style/bootstrap-theme.min.css" />
<link rel="stylesheet" type="text/css" href="../style/fsls.css" />
<link rel="stylesheet" type="text/css" href="../style/dragdealer.css" />
<script src="../js/jquery.js"></script>
<script type="text/javascript" src="../js/bootstrap.min.js"></script>
<script src="../js/nanhai.js"></script> 
</head>
<body>
    <div class="container changebg" >
        <div class="header">
          <div class="toplogo"> </div>
          <!--<div class="toptime">
            <p class="gettime">09:52:12</p>
            <p class="getfullyear">2014年07月12日 星期日</p>
          </div>-->
        </div>
        
        <div class="mid midother midsurvey paysuccessbg">
<!--        	<div class="wrongpswtittle paysuccesstittle"><img src="img/gou.png">缴费成功，请取缴费凭证</div>
            <img src="img/1.png" class="paysuccessimg">  
            <div class="wrongpswexit"><span>5</span>秒后自动退出</div> -->     
        </div>
    </div>
<script>
var settimes = window.setInterval(showt, 1000);
$(function(){
	delay();

})
function delay(){
	var temp = window.setTimeout(function(){
		clearTimeout(temp);
		settimes;
	}, 1000);
}
function showt()   
{   
	var t = $('.wrongpswexit span').text();
	$('.wrongpswexit span').text(--t);
	if(t == 0)clearInterval(settimes);
       
} 

</script>
</body>
    
