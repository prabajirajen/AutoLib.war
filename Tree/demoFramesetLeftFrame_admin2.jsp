<!--
     (Please keep all copyright notices.)
     This frameset document includes the Treeview script.
     Script found at: http://www.treeview.net
     Author: Marcelino Alves Martins

     Instructions:
	 - Through the style tag you can change the colors and types
	   of fonts to the particular needs of your site. 
	 - A predefined block has been made for stylish people with
	   black backgrounds.
-->


<html>

<head>

<!-- if you want black backgound, remove this style block -->
<style>
   BODY {background-color: #006699}
   TD {font-size: 10pt; 
       font-family: verdana,helvetica; 
	   text-decoration: none;
	   white-space:nowrap;}
   A  {text-decoration: none;
       color: black}
   
    .specialClass {font-family:garamond; font-size:12pt;color:green;font-weight:bold;text-decoration:underline}
</style>

<!-- if you want black backgound, remove this line and the one marked XXXX and keep the style block below

<style>
   BODY {background-color: black}
   TD {font-size: 10pt; 
       font-family: verdana,helvetica 
	   text-decoration: none;
	   white-space:nowrap;}
   A  {text-decoration: none;
       color: white}
</style>

XXXX -->


<!-- NO CHANGES PAST THIS LINE -->


<!-- Code for browser detection -->

<!--  <script type="text/javascript" src="milonic_src.js"></script>	
<script type="text/javascript" src="mmenudom.js"></script>
<script type="text/javascript" src="menu_data.js"></script>	-->


<script  src="<%= request.getContextPath()%>/script/milonic_src.js"></script>
<script  src="<%= request.getContextPath()%>/script/mmenudom.js"></script>
<script  src="<%= request.getContextPath()%>/script/menu_data_admin3.js"></script> 
</head>

<body topmargin=16 marginheight=16>

<a href=http://www.milonic.com/><font color="#006699"> http://www.milonic.com/beginner.php</font></a><br><br>
 <table border="1" >
  
  </table>
<!-- By making any changes to this code you are violating your user agreement.
     Corporate users or any others that want to remove the link should check 
	 the online FAQ for instructions on how to obtain a version without the link -->
<!-- Removing this link will make the script stop from working -->
<div style="position:absolute; top:0; left:0; "><table border=0><tr><td><font size=-2><a style="font-size:7pt;text-decoration:none;color:silver" href="http://www.milonic.com/" target=_blank></a><font color="#006699"> http://www.milonic.com/beginner.php</font></font></td></tr></table></div>

<!-- Build the browser's objects and display default view of the
     tree. -->
<script>initializeDocument()</script>
<noscript>
A tree for site navigation will open here if you enable JavaScript in your browser.
</noscript>

</html>
