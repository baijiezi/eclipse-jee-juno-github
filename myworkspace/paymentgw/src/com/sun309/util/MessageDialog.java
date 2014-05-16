package com.sun309.util;

import org.apache.commons.lang.StringUtils;

public class MessageDialog
{
    /**
    * 通过HTTP嵌入页面
    * 
    * @param mixed $width
    * @param mixed $height
    * @param mixed $contentUrl
    * @param mixed $title
    */
    public static String sm(int width, int height, String contentUrl, String title)
    {
        StringBuffer html = new StringBuffer();
        html.append("<script language='javascript'>var path='images/dialogclose.gif';</script>");
        html.append("<script language='javascript' src='scripts/popup.js'></script>");
        html.append("<script language='javascript'>");
        html.append("function ShowIframe()");
        html.append("{");
        html.append("var pop=new Popup({ contentType:1,scrollType:'yes',isReloadOnClose:false,width:" + width + ",height:" + height + "});");
        html.append("pop.setContent('contentUrl','" + contentUrl + "');");
        html.append("pop.setContent(\"title\",\"" + title + "\");");
        html.append("pop.build();");
        html.append("pop.show();");
        html.append("}");
        html.append("ShowIframe();");
        html.append("</script>");
        return html.toString();
    }
    /**
    * 直接嵌入内容（可以HTML）
    * 
    * @param mixed $width
    * @param mixed $height
    * @param mixed $contentHtml
    * @param mixed $title
    */
    public static String sm1(int width, int height, String contentHtml, String title)
    {
        StringBuffer html = new StringBuffer();
        html.append("<script language='javascript'>var path='images/dialogclose.gif';</script>");
        html.append("<script language='javascript' src='scripts/popup.js'></script>");
        html.append("<script language='javascript'>");
        html.append("function ShowHtmlString()");
        html.append("{");
        html.append("var pop=new Popup({ contentType:2,isReloadOnClose:false,width:" + width + ",height:" + height + "});");
        html.append("pop.setContent(\"contentHtml\",'" + contentHtml + "');");
        html.append("pop.setContent(\"title\",\"" + title + "\");");
        html.append("pop.build();");
        html.append("pop.show();");
        html.append("}");
        html.append("ShowHtmlString();");
        html.append("</script>");
        return html.toString();
    }
    /**
    * 弹出确认提示框 
    * 
    * @param mixed $width
    * @param mixed $height
    * @param mixed $message
    * @param mixed $title
    * @param mixed $callbackDiv
    * @param mixed $callBackMessage
    */
    public static String sm(String width, String height, String message, String title, String callbackDiv, String callBackMessage)
    {
    	StringBuffer html = new StringBuffer();
    	if(!StringUtils.isEmpty(callbackDiv))
    	{
    		html.append("<script language='javascript'>o = document.createElement('div');  o.id='{$callbackDiv}'; document.body.appendChild(o);</script>");
    	}
    	else
    	{
    		return "";
    	}
        html.append("<script language='javascript'>var path='images/dialogclose.gif';</script>");
        html.append("<script language='javascript' src='scripts/popup.js'></script>");
        html.append("<script language='javascript'>");
        html.append("function ShowHtmlString()");
        html.append("{");
        html.append("var pop=new Popup({ contentType:3,isReloadOnClose:true,width:" + width + ",height:" + height + "});");
        html.append("pop.setContent(\"confirmCon\",\"" + message + "\");");
        html.append("pop.setContent(\"title\",\"" + title + "\");");
        html.append("pop.setContent(\"parameter\",{id:\"" + callbackDiv + "\",str:\"" + callBackMessage + "\",obj:pop});");
        html.append("pop.setContent(\"callBack\",ShowCallBack);");
        html.append("pop.build();");
        html.append("pop.show();");
        html.append("}");
        html.append("function ShowCallBack(para)");
        html.append("{");
        html.append("var o_pop = para[\"obj\"];");
        html.append("var obj = document.getElementById(para[\"id\"]);");
        html.append("o_pop.close();");
        html.append("if(obj != null)  obj.innerHTML = para[\"str\"];");
        html.append("}");
        
        html.append("ShowConfirm();");
        html.append("</script>");
        return html.toString();
    }
    /**
    * 弹出提示信息
    * 
    * @param mixed $width
    * @param mixed $height
    * @param mixed $alertContent
    * @param mixed $title
    */
    public static String sm2(int width, int height, String alertContent, String title)
    {
        StringBuffer html = new StringBuffer();
        html.append("<script language='javascript'>var path='images/dialogclose.gif';</script>");
        html.append("<script language='javascript' src='scripts/popup.js'></script>");
        html.append("<script language='javascript'>");
        html.append("function ShowAlert()");
        html.append("{");
        html.append("var pop=new Popup({ contentType:4,isReloadOnClose:false,width:" + width + ",height:" + height + "});");
        html.append("pop.setContent(\"alertCon\",'" + alertContent + "');");
        html.append("pop.setContent(\"title\",\"" + title + "\");");
        html.append("pop.build();");
        html.append("pop.show();");
        html.append("}");
        html.append("ShowAlert();");
        html.append("</script>");
        return html.toString();
    }
}
