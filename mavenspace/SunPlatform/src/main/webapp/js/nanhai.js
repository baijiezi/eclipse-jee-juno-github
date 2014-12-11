//调整样式
$(function(){
		$('.docInfo').attr('style','');
		$('.tit').attr('style','');
		$('.tit span').attr('style','');
		$('.line').css('display','none');
		$('.con').attr('style','');
		$('.docInfo .con span').css({
			fontSize:'17px',
			color:'#152B7B',
			fontFamily:'微软雅黑, Arial, Helvetica, sans-serif'
		})
		$('.docInfo .con div').css({
			fontSize:'17px',
			color:'#152B7B',
			fontFamily:'微软雅黑, Arial, Helvetica, sans-serif'
		})
		$('.docInfo .con p').css({
			fontSize:'17px',
			color:'#152B7B',
			fontFamily:'微软雅黑, Arial, Helvetica, sans-serif'
		})
		$('.docInfo .con strong').css({
			fontSize:'17px',
			color:'#152B7B',
			fontFamily:'微软雅黑, Arial, Helvetica, sans-serif'
		})
		$('.docInfo p').css({
			fontSize:'17px',
			color:'#152B7B',
			fontFamily:'微软雅黑, Arial, Helvetica, sans-serif'
		})
		$('.docInfo span').css({
			fontSize:'17px',
			color:'#0B4FA1',
			fontFamily:'微软雅黑, Arial, Helvetica, sans-serif'
		})
		
		
		$('.cont span').css({
			fontSize:'17px',
			color:'#152B7B',
			fontFamily:'微软雅黑, Arial, Helvetica, sans-serif'
		})
		$('.cont div').css({
			fontSize:'17px',
			color:'#152B7B',
			fontFamily:'微软雅黑, Arial, Helvetica, sans-serif'
		})
		$('.cont p').css({
			fontSize:'17px',
			color:'#152B7B',
			fontFamily:'微软雅黑, Arial, Helvetica, sans-serif'
		})
		$('.cont strong').css({
			fontSize:'17px',
			color:'#152B7B',
			fontFamily:'微软雅黑, Arial, Helvetica, sans-serif'
		})
		
})



$(function(){
	//动态时间
	function getyear(objD){
		var str;   
        var yy = objD.getYear();   
            if(yy<1900) yy = yy+1900;   
        var MM = objD.getMonth()+1;   
            if(MM<10) MM = '0' + MM;   
        var dd = objD.getDate();   
            if(dd<10) dd = '0' + dd; 
		var ww = objD.getDay();   
        if (ww==0) ww="星期日";   
        if (ww==1) ww="星期一";   
        if (ww==2) ww="星期二";   
        if (ww==3) ww="星期三";   
        if (ww==4) ww="星期四";   
        if (ww==5) ww="星期五";   
        if (ww==6) ww="星期六";    
        str =  yy + "年" + MM + "月" + dd + "日" + " " + ww;   
        return(str);  
	}
	function gettime(objD){
		var hh = objD.getHours();   
            if(hh<10) hh = '0' + hh;   
        var mm = objD.getMinutes();   
            if(mm<10) mm = '0' + mm;   
        var ss = objD.getSeconds();   
            if(ss<10) ss = '0' + ss;   
        str =  hh + ":" + mm + ":" + ss ;   
        return(str);
	}
	function showt()   
    {   
        var today;   
        today = new Date();   
        $('.gettime').html(gettime(today));
		$('.getfullyear').html(getyear(today));
		today = null;
        window.setTimeout(showt, 1000);   
    }   
	showt();
	//动态时间
	//禁止选中 ie
	document.onselectstart=function(){return false;} ;
	//导航少于10个，方框下方取消
	if($('.navlist ul li').length < 11){
		$('.leftnavdiv').css('display','none');
		$('.navlist').css({height:$('.navlist ul li').length * $('.navlist ul li').height()});
	}
	
	
	//拖动
	if($('.cont').length){
		$('.cont').css('height',$('.cont')[0].scrollHeight);
			new Dragdealer('demo-simple-slider',{
			 horizontal: false,
  			 vertical: true,
  			yPrecision:0
		});
	
	}
})
var uparr = [0,-600,-1200],num = 0,downarr = [0,-600,-1200];


	//nav上移或下移
	function navup(){
		
			num++;
			if(num==3)num =2;
			$('.navlist ul').animate({'top':uparr[num]},'fast');

	}
	function navdown(){
		if($('.navlist ul').position().top < 0 && $('.navlist ul').position().top > -600){
			mun = 0;
			$('.navlist ul').animate({'top':uparr[num]},'fast');
		}else{
			num--;
			if(num<0)num =0;
			$('.navlist ul').animate({'top':downarr[num]},'fast');
		}
	}
	//nav上移或下移
	
	