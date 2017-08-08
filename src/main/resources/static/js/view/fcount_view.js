var sessionKey, table;
$(document).ready(function(){
    $('#view_tabs a').click(function(e) {
            e.preventDefault()
            $(this).tab('show')
        });
    showFileCountTable("11");
//   $('#view_table').dataTable({
//        "language": {
//            "paginate": {
//                "previous": "首页",
//                "next": "下一页"
//            },
//            "info": "显示_START_到_END_, 共计_TOTAL_条数据",
//            "emptyTable": "无记录",
//            "infoEmpty": "共计0",
//        },
//        /* 分页设置 */
//        "bPaginate": true,
//        "bLengthChange": false,
//        /* 搜索设置 */
//        "bFilter": true,
//        "bSort": false,
//        /* 显示总条数 */
//        "bInfo": true,
//        "bAutoWidth": false
//    });
})

/**
 * 获取数据表
 * @param sessionKey
 */
function showFileCountTable(){
	searchFc();
}

function fcSearch(){
	table.fnClearTable();//清空数据.fnClearTable();//清空数据 
	table.fnDestroy();
	searchFc();
}

function searchFc(){
	var fcDate = $('#fc_date').val();
	var fcTime = $('#fc_time').val();
	var sysDate = fcDate+fcTime;
	if(fcDate.length<8){
		sysDate = '201707011630';
	}
    var params = {
    		'sysDate' : sysDate
    }
    $.ajax({
    	type : 'GET',
    	url : ctx + 'fcount/findAllFileCount',
    	data : params,
    	success : function(data){
    		data = eval('('+data+')');
    		var str = '';
    		for(var i = 0; i < data.fileCountList.length; i++){
    			var tenant = data.fileCountList[i].tenant;
    			var fileNum =  data.fileCountList[i].fileNum;
    			var folderNum = data.fileCountList[i].folderNum;
    			var totalNum = fileNum + folderNum;
    			str+='<tr>'
	                +'<td>'+(i+1)+'</td>'
	                +'<td>'+tenant+'</td>'
	                +'<td>'+fileNum+'</td>'
	                +'<td>'+folderNum+'</td>'
	                +'<td>'+totalNum+'</td>'
	                +'<td>'
	                	+'<button type="button" class="btn btn-danger" onclick="removeOneFileCount(this)">删除</button>'
	                	+'<button type="button" class="btn btn-danger" th:onclick="" disabled>修改</button>'
	                +'</td>'
                +'</tr>'
    		}
    		$('#user_table_tbody').empty().append(str);
    		table = $('#fcView_table').dataTable({
    			
    	        "language": {
    	            "paginate": {
    	                "previous": "首页",
    	                "next": "下一页"
    	            },
    	            "info": "显示_START_到_END_, 共计_TOTAL_条数据",
    	            "emptyTable": "无记录",
    	            "infoEmpty": "共计0",
    	        },
    	        /* 分页设置 */
    	        "bPaginate": true,
    	        "bLengthChange": true,
    	        /* 搜索设置 */
    	        "bFilter": true,
    	        "bSort": true,
    	        /* 显示总条数 */
    	        "bInfo": true,
    	        "bAutoWidth": false
    	    });
    	}
    });
}

function removeOneFileCount(obj){
	$(obj).parent().parent().addClass('selected');
	var params = {
			"serialNum" : $(obj).attr("serialNum"),
			"sessionKey" : sessionKey
	}
	$.ajax({
		type : "POST",
		url : ctx + "v2/filecount/removeOneFileCount",
		data : params,
		success : function (data){
			data = eval('('+data+')');
			if(data.success){
				$('#view_table').DataTable().row('.selected').remove().draw(false);
			}
		}
	});
}

function exportExcel(){
	var fcDate = $('#fc_date').val();
	var fcTime = $('#fc_time').val();
	var sysDate = fcDate+fcTime;
	if(fcDate.length<8){
		sysDate = '201707011630';
	}
	window.location.href = ctx + "fcount/exportExcel?sysDate=" + sysDate;
}
