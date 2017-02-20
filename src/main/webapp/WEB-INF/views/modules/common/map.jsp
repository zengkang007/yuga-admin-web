
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
	<style type="text/css">
		body, html{width: 100%;height: 100%;margin:0;font-family:"微软雅黑";font-size:14px;}
		#l-map{height:372px;width:100%;}
		#r-result{width:100%; text-align: left; padding-top: 7px;}
	</style>
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=B3f7707c25da5b29a6ff69618788a296"></script>
	<title>关键字输入提示词条</title>
</head>
<body>
	<div id="l-map"></div>
	<div id="r-result">请输入:&nbsp;<input type="text" id="suggestId" size="20" value="百度" style="width:350px;" />&nbsp;<input type="button" onclick="onClose()" value="确定"/></div>
	<div id="searchResultPanel" style="border:1px solid #C0C0C0;width:150px;height:auto; display:none;"></div>
</body>
</html>
<script type="text/javascript">
	//回写父级页面的longtitude,latitude,address等字段
	//parent.$('#longtitude').val(pp.lng);
	//parent.$('#latitude').val(pp.lat);
	//parent.$('#addresss').val(myValue);
	
	function onClose(){
		parent.$.fancybox.close();
	}
	
	// 百度地图API功能
	function G(id) {
		return document.getElementById(id);
	}

	var map = new BMap.Map("l-map");
	map.centerAndZoom("武汉",12);                   // 初始化地图,设置城市和地图级别。
	map.addEventListener("click", function(e){
		alert(e.point.lng + ", " + e.point.lat);
	});
	var ac = new BMap.Autocomplete(    //建立一个自动完成的对象
		{"input" : "suggestId"
		,"location" : map});
	
	var myValue;
	var city;
	var district;
	var street;
	
	ac.addEventListener("onhighlight", function(e) {  //鼠标放在下拉列表上的事件
	var str = "";
		var _value = e.fromitem.value;
		var value = "";
		if (e.fromitem.index > -1) {
			value = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
			city = _value.city;
			district = _value.district;
			street = _value.street;
		}    
		str = "FromItem<br />index = " + e.fromitem.index + "<br />value = " + value;
		
		value = "";
		if (e.toitem.index > -1) {
			_value = e.toitem.value;
			value = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
			city = _value.city;
			district = _value.district;
			street = _value.street;
		}    
		str += "<br />ToItem<br />index = " + e.toitem.index + "<br />value = " + value;
		G("searchResultPanel").innerHTML = str;
	});

	
	ac.addEventListener("onconfirm", function(e) {    //鼠标点击下拉列表后的事件
	var _value = e.item.value;
		myValue = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
		G("searchResultPanel").innerHTML ="onconfirm<br />index = " + e.item.index + "<br />myValue = " + myValue;
		
		setPlace();
	});

	function setPlace(){
		map.clearOverlays();    //清除地图上所有覆盖物
		function myFun(){
			var pp = local.getResults().getPoi(0).point;    //获取第一个智能搜索的结果
			//获取经纬度
			parent.$('#longtitude').val(pp.lng);
			parent.$('#latitude').val(pp.lat);
			parent.$('#addresss').val(myValue);
			parent.$('#city').val(city);
			parent.$('#district').val(district);
			parent.$('#street').val(street);
			map.centerAndZoom(pp, 18);
			map.addOverlay(new BMap.Marker(pp));    //添加标注
		}
		var local = new BMap.LocalSearch(map, { //智能搜索
		  onSearchComplete: myFun
		});
		local.search(myValue);
	}
</script>
