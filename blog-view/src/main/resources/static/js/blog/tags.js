//标签style
$('#tags a').each(function(){
	var id = $(this).attr('id');
	if(id == 0){$(this).addClass('badge-light badge-pill');return true;}	
	if(id == 1){$(this).addClass('badge-info badge-pill');return true;}	//true: 相当于continue ，false：相当于break
	if(id == 2){$(this).addClass('badge-danger badge-pill');return true;}
	if(id == 3){$(this).addClass('badge-success badge-pill');return true;}
	if(id == 4){$(this).addClass('badge-warning badge-pill');return true;}	
	if(id == 5){$(this).addClass('badge-dark badge-pill');return true;}	
	if(id == 6){$(this).addClass('badge-secondary badge-pill');return true;}	
	if(id == 7){$(this).addClass('badge-primary badge-pill');return true;}
});