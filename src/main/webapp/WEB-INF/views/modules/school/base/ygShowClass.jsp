<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>班级图片滚动</title>
	<meta name="decorator" content="default"/>
	
<style>
	* { padding:0; margin:0;}       /*设置所有对像的内边距为0*/
	body { text-align:center;}      /*设置页面居中对齐*/
	#photo-list {
	/* 6张图片的宽度（包含宽度、padding、border、图片间的留白）
	计算：6*(100+2*2+1*2+9) - 9 
	之所以减去9是第6张图片的右边留白 */
	   width:381px;  
	/* 图片的宽度（包含高度、padding、border）
	   计算：100+2*2+1*2  */ 
	    height:106px;  
	    margin:50px auto; 
	 overflow:hidden;     /*溢出部份将被隐藏*/ 
	    border:1px dashed #ccc;  
	}  
	#photo-list ul { list-style:none;}  
	#photo-list li { float:left; padding-right:9px;}  
	#photo-list img { border:1px solid #ddd; background:#fff; padding:2px;}
</style>




<script language="javascript">

			
$.ajax({
	    url : '${ctx}/api/rest/getTop10',
		data : {
			
		},
		dataType : 'json',
		success : function(data) {
    //这里，对返回数据做处理。
         alert(data);
		}
	});

		</script>

	
<script type="text/javascript">
	   var id = function(el) {
		   return document.getElementById(el);
		   },
       c = id('photo-list');
   if(c) {
       var ul = id('scroll'),
           lis = ul.getElementsByTagName('li'),
           itemCount = lis.length,
           width = lis[0].offsetWidth, //获得每个img容器的宽度
           marquee = function() {
               c.scrollLeft += 2;
               if(c.scrollLeft % width <= 1){  //当 c.scrollLeft 和 width 相等时，把第一个img追加到最后面
                   ul.appendChild(ul.getElementsByTagName('li')[0]);
                   c.scrollLeft = 0;
               };
           },
           speed = 100; //数值越大越慢
       ul.style.width = width*itemCount + 'px'; //加载完后设置容器长度        
       var timer = setInterval(marquee, speed);
       c.onmouseover = function() {
           clearInterval(timer);
       };
       c.onmouseout = function() {
           timer = setInterval(marquee, speed);
       };
   };
</script>
</head>
<body>

<div id="photo-list">  <ul id="scroll">  
<li><a href="#"><img src="images/1.jpg" width="100px" height="100px" alt=""/></a></li>  
<li><a href="#"><img src="images/2.jpg" width="100px" height="100px" alt=""/></a></li>  
<li><a href="#"><img src="images/3.jpg" width="100px" height="100px" alt=""/></a></li>  
<li><a href="#"><img src="images/4.jpg" width="100px" height="100px" alt=""/></a></li>  
<li><a href="#"><img src="images/5.jpg" width="100px" height="100px" alt=""/></a></li>  
<li><a href="#"><img src="images/6.jpg" width="100px" height="100px" alt=""/></a></li>
<li><a href="#"><img src="images/7.jpg" width="100px" height="100px" alt=""/></a></li>  
<li><a href="#"><img src="images/8.jpg" width="100px" height="100px" alt=""/></a></li>
</ul> 

</div>

<div style="display:inline; width:150px; height:26px;">图片滚动起来</div>
</body>
</html>