<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>图表</title>
	<link href="${ pageContext.request.contextPath }/css/managestyle.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="${ pageContext.request.contextPath }/js/jquery-1.4.2.js"></script>
	<script type="text/javascript" src="${ pageContext.request.contextPath }/js/echarts.js"></script>
	<style type="text/css">
			.chartStyle{
				margin-left:30px;
				margin-top:30px;
				margin-bottom:50px;
			}
		</style>
</head>
<body>
	<%@ include file = "_top.jsp" %>
	<div class="content">
		<div class="right">	
		<div class=chartStyle>
		<!-- 显示Echarts图表 -->
		<div style="height:410px;min-height:100px;margin:0 auto;" id="pie"></div>
		<div style="height:410px;min-height:100px;margin:0 auto;" id="bar"></div>
		<!-- 饼状图 -->
		<script>
	 	  var chart = echarts.init(document.getElementById('pie'));
	 	  chart.setOption({
	 		 aria: {
	             show: true
	         },
	         title : {
	             text: '各类商品所占比例',
	             x:'center'
	         },
	         tooltip : {
	             trigger: 'item',
	             formatter: "{a} <br/>{b} : {c} ({d}%)"
	         },
	         legend: {
	             orient: 'vertical',
	             left: 'left',
	             data: ['手机数码','日用百货','家用电器','图书杂志','服装服饰','床上用品','汽车用品']
	         },
	         series : [
	             {
	                 name: '商品类别',
	                 type: 'pie',
	                 radius : '55%',
	                 center: ['50%', '60%'],
	                 data:[
	                     
	                      
	                 ],
	                 itemStyle: {
	                     emphasis: {
	                         shadowBlur: 10,
	                         shadowOffsetX: 0,
	                         shadowColor: 'rgba(0, 0, 0, 0.5)'
	                     }
	                 }
	             }
	         ]
	     
	 	  });
	 
	      var nums=[];    //销量数组（实际用来盛放Y坐标值）
		$.ajax({
			  url:"getPie",
			  cache: false, 
		      dataType:"JSON",
		      success:function(data){
		    	  if(data){
		    		  data=data.substring(1,data.length-1);  //因为传过来的数据是string类型[13, 9, 2, 1]
		    		  data=data.split(", ");                  //所以要将数据转换成数组类型，方便赋值
		    		  for(var i=0;i<data.length;i++){
		                  nums.push(data[i]) ;  //挨个取出并填入数组
		                  
		              } 
		    		  chart.setOption({
		    			  series : [
		    		             {
		    		                 data:[
		    		                     {value:nums[0], name:'手机数码'}, 
		    		                     {value:nums[1], name:'日用百货'},
		    		                     {value:nums[2], name:'家用电器'},
		    		                     {value:nums[3], name:'图书杂志'},
		    		                     {value:nums[4], name:'服装服饰'}, 
		    		                     {value:nums[5], name:'床上用品'},
		    		                     {value:nums[6], name:'汽车用品'},
		    		                 ]
		    		             }
		    		         ]
			          });
		    	  }
		      },
		      error : function(errorMsg) {
		            //请求失败时执行该函数
		            alert("图表请求数据失败!");
		            chart.hideLoading();
		        }
			})
	 	</script>
	 	<!-- 柱状图 -->
	 	<script type="text/javascript">
	        // 基于准备好的dom，初始化echarts实例
	        var chart2 = echarts.init(document.getElementById('bar'));
	        chart2.setOption({
	        	aria: {
	                show: true
	            },
	            title : {
	                text: '各类商品的数量',
	                x:'center'
	            },
	            legend: {
	                orient: 'vertical',
	                left: 'left',
	                data: ['数量']
	            },
	            xAxis: {
	            	data: ['手机数码','日用百货','家用电器','图书杂志','服装服饰','床上用品','汽车用品']
	            },
	            yAxis: {},
	            series: [{
	                name: '数量',
	                type: 'bar',
	                data: []
	            }]
	        });
	       // var names=[];    //类别数组（实际用来盛放X轴坐标值）
	        var nums=[];    //销量数组（实际用来盛放Y坐标值）
	  $.ajax({
			url:"getPie",
			  cache: false, 
		      dataType:"JSON",
		      success:function(data){
		    	  if(data){
		    		 
		    		  for(var i=0;i<data.length;i++){
		                  nums.push(data[i]) ;  //挨个取出销量并填入销量数组
		              } 
		    		  chart2.setOption({
			              series: [{
			                  name: '数量',
			                  type: 'bar',
			                  data: nums
			              }]
			          });
		    	  }
		      },
		      error : function(errorMsg) {
		            //请求失败时执行该函数
		            alert("图表请求数据失败!");
		            chart2.hideLoading();
		        }
	  		})
	    </script>
	    </div>
		</div>
	</div>
</body>
</html>