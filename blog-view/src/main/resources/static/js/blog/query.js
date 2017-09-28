/*
 * blog query 
 */
$(function(){
	
	//获取href中的keyword
	var kwHref = window.location.href;
	var kwIndex = kwHref.lastIndexOf('/');
	var keyword = kwHref.substr(kwIndex + 1);
	
	var url = '/blogs/'+ keyword +'/totalElements';
	var totalElements = 0;
	$.ajax({
		async: false,					//默认true,所有请求均为异步请求,导致数据还没返回,代码就往下执行了,totalElements值为0,所以显示不出分页
		url: url,						//设置为false,同步请求,浏览器设置断点的话默认也是同步请求
		type: 'get',
		dataType: 'json',
		data: {
			'pageIndex': 0,
			'pageSize': 9
		},
		success: function(data){
			totalElements = data.data;
		}
	});
	
	function paging_(obj){
		var url = '/blogs/q/' + keyword;
		$.ajax({
			url: url,
			type: 'get',
			data: {
				'async': true,
				'pageIndex': obj.curr - 1,
				'pageSize': obj.limit
			},
			success: function(data){
				$('#mainContainer').html(data);
			},
			error: function() {
				layer.msg('服务器繁忙,请稍后重试!', {
					icon: 2,
					time: 2000
				});
			}
		});
	}
	
	layui.use(['layer', 'laypage','form'], function() {
		var layer = layui.layer,
		laypage = layui.laypage
		form = layui.form;
		
		//执行一个laypage实例
		laypage.render({
			elem: 'pageable', 			//注意，这里的 pageable 是 ID，不用加 # 
			count: totalElements, 		//数据总数 ,从服务端得到               
			limit: 9,                   //每页显示的条数(pageSize)          
			groups: 3,                  //连续出现的页码个数                  
			prev: 'prev',
			next: 'next',
			theme: '#FFC107',
			jump: function(obj, first) { //obj包含了当前分页的所有参数，比如：obj.curr(当前页数),obj.limit(每页显示的条数)
				if(!first) {
					paging_(obj);
					location.href = "#tags";	//分页请求完成后,跳转到#tags
				}
			}
		});
	});
	
});



