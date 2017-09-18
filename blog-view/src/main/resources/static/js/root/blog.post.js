//JavaScript代码区域
layui.use(['form','layedit','laydate','layer','element'], function(){
  var form = layui.form
  ,layedit = layui.layedit
  ,laydate = layui.laydate
  ,layer = layui.layer
  ,element = layui.element;
  
//日期
laydate.render({
	elem: '#date'
	,type: 'datetime'
});
  
//创建一个编辑器
var editIndex = layedit.build('editor');

//自定义验证规则
form.verify({
  	title: function(value) {
  		if(value.length < 5) {
  			return '标题至少得5个字符啊';
  		}
  	},
  	content: function(value) {
  		layedit.sync(editIndex);
  	}
 });
  
//监听提交
form.on('submit(post)', function(data){
    /*layer.alert(JSON.stringify(data.field), {
      title: '最终的提交信息'
    })
   return false;*/

	
});

$('#tags').tagsInput({
	removeWithBackspace: false,
	onAddTag: function(tag){
		$.ajax({
			type: 'post',
			data: {'name':tag},
			url: '/root/ct',
			success: function(data){
				if(!data.result) {
					layer.msg(data.message,{time:2000});
					return;
				}
				if(data.message == 'existed') {	//已存在标签,直接将标签名显示
					$(this).addTag(tag);
				}
			},
			error: function(){
				layer.msg('添加失败,请稍后重试!',{time:2000});
			}
		});
	}
});


//动态获取分类列表
$(function(){
	var url = "/catall";
	var html;
	$.get(url,function(data){
		$.each(data.data,function(idx,obj){
			html += "<option value="+ obj.id +">"+ obj.name +"</option>";
		});
		
		$('#category').append(html);
		//刷新select框
		form.render('select');
	},'json');
});

});