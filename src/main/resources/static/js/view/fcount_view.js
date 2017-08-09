var sessionKey, table;
$(document).ready(function(){
    $('#view_tabs a').click(function(e) {
            e.preventDefault()
            $(this).tab('show')
        });
    showFileCountTable("11");
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
    			var fcId = data.fileCountList[i].fcId;
    			var tenant = data.fileCountList[i].tenant;
    			var fileNum =  data.fileCountList[i].fileNum;
    			var floderNum = data.fileCountList[i].floderNum;
    			var totalNum = fileNum + floderNum;
    			str+='<tr>'
	                +'<td>'+(i+1)+'</td>'
	                +'<td>'+tenant+'</td>'
	                +'<td>'+fileNum+'</td>'
	                +'<td>'+floderNum+'</td>'
	                +'<td>'+totalNum+'</td>'
	                +'<td>'
	                	+'<button type="button" class="btn btn-danger" fcId ='+fcId+' onclick="removeOneFileCount(this)">删除</button>'
	                	+'&nbsp&nbsp&nbsp'
	                	+'<button type="button" class="btn btn-danger" fcId ='+fcId+' onclick="editModel(this)" >修改</button>'
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
	var params = {
			"fcId" : $(obj).attr("fcId"),
	}
	$('#alertModal .modal-body').html('确定要删除？');
    $('#alertModal').modal('show');
    $('#removeFc').unbind('click');
    $('#removeFc').click(function() {
    	$('#alertModal').modal('hide');
    	$.ajax({
    		type : "POST",
    		url : ctx + "fcount/deleteOne",
    		data : params,
    		success : function (data){
    			data = eval('('+data+')');
    			if(data.message){
    				$(obj).parent().parent().addClass('selected');
    				//$('#view_table').DataTable().row('.selected').remove().draw(false);
    			}
    		}
    	});
    });
}

function addOneFileCount(){
	var fcDate = $('#fc_date').val();
	var fcTime = $('#fc_time').val();
	var sysDate = fcDate + fcTime;
	if(fcDate.length<8){
		sysDate = '201707011630';
	}
	var tenant = $('#addFcForm #fcTenantName').val();
	var fileNum =$('#addFcForm #fcFileNum').val();
	var floderNum = $('#addFcForm #fcFloderNum').val()
	var params = {
		"tenant" : tenant,
		"fileNum" : fileNum,
		"floderNum" : floderNum,
		"sysDate" : sysDate
	}
	$.ajax({
        cache: true,
        type: "POST",
        url: ctx +"fcount/addOne",
        data: params,// 你的formid
        async: false,
        error: function(request) {
            alert("Connection error");
        },
        success: function(data) {
        	fcSearch();
        }
    });
}

function editModel(obj){
	$('#editFcForm #fcTenantNameEdit').val('');
	$('#editFcForm #fcFileNumEdit').val('');
	$('#editFcForm #fcFileFloderEdit').val('');
	$('#editFcForm #fcIdEdit').val('');
	$('#editFcForm #fcSysDateEdit').val('');
	$.ajax({
        type: "GET",
        url: ctx +"fcount/findOneFc",
        data: {"fcId" : $(obj).attr("fcId")},// 你的formid
        error: function(request) {
            alert("Connection error");
        },
        success: function(data) {
        	data = eval('('+data+')');
        	if(data.message == "200"){
        		var fcId = data.fileCountEntity.fcId;
        		var tenant = data.fileCountEntity.tenant;
        		var fileNum = data.fileCountEntity.fileNum;
        		var floderNum = data.fileCountEntity.floderNum;
        		var sysDate = data.fileCountEntity.sysDate;
        		$('#editFcForm #fcTenantNameEdit').val(tenant);
        		$('#editFcForm #fcFileNumEdit').val(fileNum);
        		$('#editFcForm #fcFloderNumEdit').val(floderNum);
        		$('#editFcForm #fcIdEdit').val(fcId);
        		$('#editFcForm #fcSysDateEdit').val(sysDate);
        		$('#editFcModel').modal();
        	}
        }
    });
}

function editOneFileCount(){
	
	var tenant = $('#editFcForm #fcTenantNameEdit').val();
	var fileNum = $('#editFcForm #fcFileNumEdit').val();
	var floderNum = $('#editFcForm #fcFloderNumEdit').val();
	var fcId = $('#editFcForm #fcIdEdit').val();
	var sysDate = $('#editFcForm #fcSysDateEdit').val();
	var params = {
			"fcId" : fcId,
			"tenant" : tenant,
			"fileNum" : fileNum,
			"floderNum" : floderNum,
			"sysDate" : sysDate
		}
		$.ajax({
	        cache: true,
	        type: "POST",
	        url: ctx +"fcount/editOne",
	        data: params,// 你的formid
	        async: false,
	        error: function(request) {
	            alert("Connection error");
	        },
	        success: function(data) {
	        	fcSearch();
	        	$('#editFcModel').modal('hide');
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
