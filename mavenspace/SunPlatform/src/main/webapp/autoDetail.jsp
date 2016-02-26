<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<object id="LODOP_OB" classid="clsid:2105C259-1E0C-4534-8141-A753534CB4CA" width=0 height=0> 
	<embed id="LODOP_EM" type="application/x-print-lodop" width=0 height=0 pluginspage="install_lodop.exe"></embed>
</object> 
<div class="pageContent">
	<form id="form1" method="post" action="" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
		<div class="pageFormContent" layoutH="50">
			<h2><p align="center"><font face="verdana">佛山市南海区第三人民医院<br>门诊自助缴费凭证</font></p></h2>
			<table width="500px">
				<tr><td><nobr><label>系统参考号：<%out.println(request.getParameter("systemPayTranLine"));%></label></nobr></td></tr>
				<tr><td><label>终端号：<%out.println(request.getParameter("operId"));%></label></td></tr>
				<tr><td><nobr><label>打印时间：<%=new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date())%></label></nobr></td></tr>
				<tr><td>&nbsp;</td></tr>
				<!-- <tr><td><label>商户号：221232</label></td></tr>  -->
				<tr><td><label>支付金额：<%out.println(request.getParameter("payMoney"));%></label></td></tr>
				<tr><td><nobr><label>支付卡号：<%out.println(request.getParameter("payCardNo"));%></label></nobr></td></tr>
				<tr><td><label>支付参考号：<%out.println(request.getParameter("payTranLine"));%></label></td></tr>
				<tr><td><label><nobr>支付时间：<%out.println(request.getParameter("paytime"));%></nobr></label></td></tr>
				<tr><td><label>门  诊  号：${ade.orderNo}</label></td></tr>
				<tr><td>&nbsp;</td></tr>
				<tr><td><label>就诊医生：${ade.doctorName}</label></td><td><label><nobr>就诊科室：${ade.deptName}</nobr></label></td></tr>
				<tr><td><label>就诊姓名：${ade.patientName}</label></td><td><nobr><label>就诊日期：${ade.visitDateTime}</label></nobr></td></tr>
				<tr><td><nobr><label>诊疗卡号：${ade.medicalCardNo}</label></nobr></td></tr>
				<tr><td><label>结算方式：${ade.settlementType}</label></td><td><label>总金额：${ade.totalFee}</label></td></tr>
				<tr><td><label>社保支付：${ade.medicalInsurance}</label></td><td><label>个人缴费：${ade.selfFee}</label></td></tr>
			</table>
			<div class="divider"></div>
			<p><%
					if(request.getParameter("chinese").length()==4) out.println(new String(request.getParameter("remark").getBytes("ISO-8859-1"),"GB2312"));
					else out.println(new String(request.getParameter("remark").getBytes("ISO-8859-1"),"UTF-8"));
			   %>
				&nbsp;<br>
				若需发票请凭此票到客服中心打印
				&nbsp;<br>
				<strong>注：本凭证取药、治疗、检查时需出示，切勿遗失。</strong>
			</p>   
		</div>
			</form>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="button" onclick="javascript:prn1_preview()">打印</button></div></div></li>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
				</li>
			</ul>
		</div>
</div>
<script language="javascript" type="text/javascript">   
        var LODOP; //声明为全局变量 
	function prn1_preview() {
		CreateOneFormPage();
		LODOP.PREVIEW();
	};
	function CreateOneFormPage(){
		LODOP=getLodop(document.getElementById('LODOP_OB'),document.getElementById('LODOP_EM'));  
		LODOP.PRINT_INIT("打印控件功能演示_Lodop功能_表单一");
		LODOP.SET_PRINT_STYLE("FontSize",11);
		LODOP.SET_PRINT_STYLE("Bold",0);
		LODOP.ADD_PRINT_HTM(2,80,500,650,document.getElementById("form1").innerHTML);
	};
	function CheckIsInstall() {	
		try{
		    var LODOP=getLodop(document.getElementById('LODOP_OB'),document.getElementById('LODOP_EM'));
			if ((LODOP!=null)&&(typeof(LODOP.VERSION)!="undefined")) alert("本机已成功安装过Lodop控件!\n  版本号:"+LODOP.VERSION);
		 }catch(err){
			//alert("Error:本机未安装或需要升级!");
 		 }
	}
</script> 
