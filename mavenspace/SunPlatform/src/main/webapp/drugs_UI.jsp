<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta charset="utf-8">
<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport">
<meta content="telephone=no" name="format-detection">
<title>医院物价公示</title>

<link rel="stylesheet" type="text/css" href="./style/bootstrap.css" />
<link rel="stylesheet" type="text/css" href="./style/bootstrap-theme.min.css" />
<link rel="stylesheet" type="text/css" href="./style/fsls.css" /> 
<link rel="stylesheet" type="text/css" href="./style/dragdealer.css" />
<script src="./js/SoftKey/vk_loader.js?vk_layout=CN%20Chinese%20Simpl.%20Pinyin&vk_skin=flat_gray"></script>
<script src="js/SoftKey/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="./js/bootstrap.min.js"></script>
<script src="./js/nanhai.js"></script>
<script src="./js/dragdealer.js"></script>

</head>
<body >
    <div class="container changebg" >
        <div class="header" onselectstart='return false'>
          <div class="toplogo"> </div>
          <div class="toptime">
           <p class="getfullyear">2014年07月12日 星期日</p>
            <p class="gettime">09:52:12</p>
          </div>
        </div>

        <div class="mid midgoodsprice">
			<div class="titt tittgoodsprice" onselectstart='return false'>
    			<p>医院物价公示</p>
  			</div>
            <div class="midgoodspricenav" onselectstart='return false'>
            	<ul >
                	<li onclick="submitCZ()">常规医疗项目</li>
                    <li onclick="submitXY()">药品类</li>
                    <li onclick="submitHY()">化验类</li>
                    <li onclick="submitFS()">放射类</li>
                    <li onclick="submitCS()">超声类</li>
                </ul>
            </div>
            <div class="midgoodspricesearch">
                <form action="drugsNameDelta.do" method="post" id="fromid">
                	<div class="on_off_keyboard" onselectstart='return false'>打开/关闭键盘</div>
                	<input type="text" name="name" class="searchcont" value="请输入名称快捷查找价格" id = 'txt_Search'>
                	<input type="hidden" name="hospitalName" id="hospitalName" value="里水医院">
                    <input type="submit" value="查询" class="searchsubmit">
                    <div class="clearsearch" onselectstart='return false'>清除记录</div>
                    <div id="softkey"></div> 
                </form>
            </div>
            <ul class="midgoodspriceconttittl" onselectstart='return false' id="aa">
            	<li><span>编码</span></li>
                <li><span>项目名称</span></li>
                <li><span>医保类型</span></li>
                <li><span>项目类型</span></li>
                <li><span>规格</span></li>
                <li><span>单价</span></li>
                <li><span>单位</span></li>
                <li class="thlast-child"><span>备注</span></li>
            </ul>
            <div onselectstart='return false'>
            <div class="midgoodspricecont dragdealer" id="demo-simple-slider">
          		<table width="100%" border="0" cellspacing="0" class="handle" id="handle">
          		<c:forEach var="v" items="${ls}" varStatus="status">
                  <tr>
                    <td >${status.index+1}</td>
                    <td >${v.medicineName}</td>
                    <td >${v.ybType}</td>
                    <td >${v.medicineType}</td>
                    <td >${v.specifiCation}</td>
                    <td >${v.price}</td>
                    <td >${v.outpatienTunit}</td>
                    <td >${v.medicineCode}</td>
                  </tr>
                </c:forEach>
                </table>
            </div>
            </div>
        </div>
    </div>
<script>
 $(function(){
	 $('.searchcont').focus(function(){
		 if($(this).val() == '请输入名称快捷查找价格')$(this).val('');
		 $(this).css('color','#000');
	 })
	  $('.searchcont').blur(function(){
		 if($(this).val() == '')$(this).val('请输入名称快捷查找价格');
		  $(this).css('color','#999');
	  })
	  $('.on_off_keyboard').click(function(){
		   VirtualKeyboard.toggle("txt_Search", "softkey"); 
		   //$("#kb_langselector,#kb_mappingselector,#copyrights").css("display", "none");
	  })
	  $('.clearsearch').click(function(){
		  $('.searchcont').val('');
	  })
	  $('.midgoodspricenav ul li').click(function(){
		  $('.midgoodspricenav ul li').removeClass('active');
		  $(this).addClass('active');
	  })
	  document.onselectstart=function(){return true;} ;
	  settittlewidth();
	  
 })
 function settittlewidth(){
	 var temp;
	 if($('.handle').height()> $('#demo-simple-slider').height()){
	 	//$('.handle').css({'position':'absolute','top':0})
		temp = new Dragdealer('demo-simple-slider',{
		horizontal: false,
  		vertical: true,
  		yPrecision:0
	  })}else temp = null;
 }
//$(function(){
 // $("li").click(function(){
 	//$(this).context.innerHTML;
   // alert($(this).index()+1);
 //  alert($(this).context.value);
 // })
//});
//提交西药数据
 function submitXY(){
 	window.location.href="drugs_UI01.jsp";
 }
 //提交常规数据
 function submitCZ(){
 	window.location.href="drugs_UI02.jsp";
 }
 //提交化验数据
 function submitHY(){
 	window.location.href="drugs_UI03.jsp";
 }
 //提交放射数据
 function submitFS(){
 	window.location.href="drugs_UI04.jsp";
 }
 //提交超声波数据
 function submitCS(){
 	window.location.href="drugs_UI05.jsp";
 }
</script>
</body>
    