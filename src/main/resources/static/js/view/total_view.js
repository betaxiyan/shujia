var sessionKey, table;
$(document).ready(function(){
    $('#view_tabs a').click(function(e) {
            e.preventDefault()
            $(this).tab('show')
        });

   $('#view_table').dataTable({
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
        "bLengthChange": false,
        /* 搜索设置 */
        "bFilter": true,
        "bSort": false,
        /* 显示总条数 */
        "bInfo": true,
        "bAutoWidth": false
    });
})

/**
 * 提交excel
 */
function submitExcel(){
	var formData = new FormData();
	formData.append("file",$("#file")[0].files[0]);
	$.ajax({
		type:'POST',
		processData : false,
		contentType: false,
		data: formData,
		url: ctx + "v2/filecount/submitExcel",
		 success: function(data){
			data = eval('('+data+')');
			sessionKey = data.sessionKey;
			console.log("sessionKey = "+ sessionKey);
			showFileCountTable(sessionKey);
		}
	});
}

/**
 * 根据sessionekey获取数据表
 * @param sessionKey
 */
function showFileCountTable(sessionKey){
    var params = {
    		'sessionKey' : sessionKey
    }
    
    $.ajax({
    	type : 'GET',
    	url : ctx + 'v2/filecount/getFileDataTable',
    	data : params,
    	success : function(data){
    		data = eval('('+data+')');
    		var str = '';
    		for(var i = 0; i < data.dataTableEntityList.length; i++){
    			var tenant = data.dataTableEntityList[i].tenant;
    			var fileNum =  data.dataTableEntityList[i].fileNum;
    			var folderNum = data.dataTableEntityList[i].folderNum;
    			var serialNum = data.dataTableEntityList[i].serialNum;
    			var totalNum = fileNum + folderNum;
    			str+='<tr>'
	                +'<td>'+(i+1)+'</td>'
	                +'<td>'+tenant+'</td>'
	                +'<td>'+fileNum+'</td>'
	                +'<td>'+folderNum+'</td>'
	                +'<td>'+totalNum+'</td>'
	                +'<td>'
	                	+'<button type="button" class="btn btn-danger" serialNum = "'+serialNum+'" onclick="removeOneFileCount(this)">删除</button>'
	                	+'<button type="button" class="btn btn-danger" th:onclick="" disabled>修改</button>'
	                +'</td>'
                +'</tr>'
    		}
    		$('#user_table_tbody').empty().append(str);
    		
    		table = $('#view_table').dataTable({
    			
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
	window.location.href = ctx + "v2/filecount/exportExcel?sessionKey="+sessionKey;
}
