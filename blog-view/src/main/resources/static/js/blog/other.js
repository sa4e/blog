/**
 * 
 * other
 */
$(function(){
	
	//返回顶部插件
	$('.to-top').toTop({
		right: 30,
		speed: 180
	});

	//热度tips
//	$('[data-toggle="tooltip"]').tooltip();
	
	layui.use(['layer','form'],function(){
		var layer = layui.layer

		//header搜索监听
		$('#search').click(function(){
			var keyword = $('#keyword').val();
			window.location.href = '/blogs/q/' + keyword;
		});
		
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
	});
	
});
