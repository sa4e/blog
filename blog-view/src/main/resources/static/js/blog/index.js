/*
 * blog index 
 */
//点击打开二维码函数
function openewm(_tiitle,_content){
	layer.open({
		type: 1,
		title: _tiitle,
		content: _content,
		shadeClose: true,
		maxWidth: 400
	});
}

var $wximg = $('#weixinewm');
$('#weixin').click(function(){
	return openewm('Wechat QR Code',$wximg);
});

var $qqimg = $('#qqewm');
$('#qq').click(function(){
	return openewm('QQ QR Code',$qqimg);
});

layui.use(['layer', 'laypage'], function() {
	var layer = layui.layer,
		laypage = layui.laypage;
	
	var url = '/first';
	var tempData = [10, 11, 12, 13, 14, 15, 16, 17, 18];
	$.get(url, {
		'first': 5
	}, function(data) {
		tempData = data;
	}, 'json');
	
	//执行一个laypage实例
	laypage.render({
		elem: 'page' //注意，这里的 page 是 ID，不用加 # 号
			,
		count: tempData.length //数据总数 //从服务端得到
			,
		limit: 3,
		groups: 3,
		prev: 'prev',
		next: 'next',
		theme: '#FFC107',
		jump: function(obj, first) { //obj包含了当前分页的所有参数，比如：obj.curr(当前页数),obj.limit(每页显示的条数)
			if(!first) {
				var url = '/data';
				$.ajax({
					url: url,
					type: 'get',
					dataType: 'json',
					data: {
						'async': 'true',
						'pageIndex': obj.curr,
						'pageSize': obj.limit,
						'keyword': $('#indexKeyWord').val()
					},
					success: function(data) {
						//do something
					},
					error: function() {
						layer.msg('服务器繁忙,请稍后重试!', {
							icon: 2,
							time: 2000
						});
					}
				});
			}
		}
	});
});