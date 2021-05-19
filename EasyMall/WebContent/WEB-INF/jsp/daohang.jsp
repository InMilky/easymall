<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
 <!-- 适应移动端页面展示以便使页面更好地在移动平台展示 -->
 <meta name="viewport" content="initial-scale=1.0, user-scalable=no"/> 
 <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
 <!-- 设置地图显示的样式 -->
<style type="text/css">
html{height:100%}
body{height:100%;position:relative;top:0px;padding:0px}
#result{
            padding: 30px 30px;
            position: fixed;
            top: 200px;
            left: 100px;
            width: 300px;
            background: #fff;
            box-shadow: 0 2px 6px 0 rgba(27, 142, 236, 0.5);
            border-radius: 7px;
            z-index: 99;
        }
#container{height:100%}
</style>
<!--引入百度地图API，ak每个人都可以通过创建用户及项目获得-->
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=ShoI14MD3gen6OuOSogqbVk9KFbU1vKl"></script>
<title>百度地图导航</title>
</head>
<body>
<!-- 创建地图容器元素，因为地图需要一个html元素作为容器，这样才能展现到页面上 -->
<div id="container"></div>
<div id='result'>
        City: <input id="cityName" type="text"/>
		<button onclick="theLocation()"/>search</button>
</div>
<script type="text/javascript">
var map = new BMap.Map("container"); //创建地图实例（在container容器中创建一个地图，此处的container为div的id属性，从上面的样式可知，container的height为100%）
var point = new BMap.Point(116.331398,39.897445); // 创建点坐标 北京
map.centerAndZoom(point, 15); // 初始化地图，设置中心点坐标(上面的北京市坐标)和地图级别
var marker = new BMap.Marker(point);  // 创建标注
map.addOverlay(marker);              // 将标注添加到地图中
map.enableScrollWheelZoom();//激活滚轮调整大小功能
map.addControl(new BMap.NavigationControl());//添加缩放地图的控件，默认在左上角
map.addControl(new BMap.ScaleControl());//添加显示地图类显示比例的控件，默认在左下方
map.addControl(new BMap.OverviewMapControl());//添加地图的缩略图的控件，默认在右下方TrafficControl
map.addControl(new BMap.MapTypeControl());//添加显示地图类型的控件，默认在左上角
//通过输入的城市名字将该城市设置为中心点坐标并将该城市标注起来
function theLocation(){
    var city = document.getElementById("cityName").value;
    if(city != ""){
        map.centerAndZoom(city,15);      // 用城市名设置地图中心点
    }
}

</script>
</body>
</html>