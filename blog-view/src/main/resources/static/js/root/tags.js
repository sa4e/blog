$(function(){
		$.ajax({
			type: 'get',
			url: '/tagall',
			success: function(data){
				var tags = data.data;
				$('#tags').importTags(tags);	//'foo,dada,dad'
			},
			error: function(){
				alert("服务器出错了!");
			}
		});
	
		$('#tags').tagsInput({
			width: "100%",
			height: "300px",
			interactive: false,
			onRemoveTag: function(tag){		//tag：标签名
				$.ajax({
					type: 'post',
					url: '/root/dt',
					data: {_method:'DELETE','name':tag},
					success: function(data){
						alert(data.message);
					},
					error: function(){
						alert("请求出错了!");
					}
				});
			}
		});
	});