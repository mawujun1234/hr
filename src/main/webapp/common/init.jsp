<%@ page language="java"  pageEncoding="UTF-8"%>

<%
String extjscontextPath=request.getContextPath();
String ip=request.getRemoteAddr();
String extjs="ext-all.js";
if("localhost".equals(ip) ||"127.0.0.1".equals(ip) || "0:0:0:0:0:0:0:1".equals(ip)){
	extjs="ext-all-debug.js";
} 


String contextPath = request.getContextPath();
String requestURI=request.getRequestURI();
String aa=requestURI.replaceAll(contextPath+"/", "/");
%>
<!--
<script src="<%=request.getContextPath()%>/menu/queryElementPermission.do?url=<%=aa %>&timestamp="+new Date().getTime()></script>
   --> 
    <!-- <link rel="stylesheet" type="text/css" href="./ext6/build/classic/theme-neptune/resources/theme-neptune-all.css">
    <script type="text/javascript" src="./ext6/build/classic/theme-neptune/theme-neptune.js"></script>
    <link rel="stylesheet" type="text/css" href="./ext6/build/classic/theme-triton/resources/theme-triton-all.css">
    <script type="text/javascript" src="./ext6/build/classic/theme-neptune/theme-triton.js"></script>-->
    
    
    
    
     <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/ext6/build/classic/theme-crisp/resources/theme-crisp-all.css">
   
        <script type="text/javascript" src="<%=request.getContextPath()%>/ext6/build/<%=extjs%>"></script>
         <script type="text/javascript" src="<%=request.getContextPath()%>/ext6/build/classic/theme-crisp/theme-crisp.js"></script>
         <script type="text/javascript" src="<%=request.getContextPath()%>/ext6/build/classic/locale/locale-zh_CN.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/common/common.js"></script>
	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/font_css/font-awesome.css">
	
	<!--
<script type="text/javascript" src="<%=request.getContextPath()%>/common/DatetimePicker.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/common/DatetimeField.js"></script>
-->
    
<style>

</style>
<%


%>


<script type="text/javascript">
//alert('<%=ip%>');
Ext.Loader.setConfig({
	enabled: true,
	paths:{
		'Ems':'..',
		//'Ems':'.',
		'Ext.ux':'../ext6/packages/ux/classic/src'
		//'MyDesktop':'.'
	}
});
Ext.setGlyphFontFamily('FontAwesome');
Ext.required='<span style="color:red;font-weight:bold" data-qtip="Required">*</span>';
Ext.ContextPath="<%=request.getContextPath()%>";
Ext.ariaWarn = Ext.emptyFn;

Ext.ecode_length=20;
Ext.QuickTips.init(); 
</script>


