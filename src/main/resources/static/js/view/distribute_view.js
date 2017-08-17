var tables;
$(document).ready(function() {
    tables= $('#tools_table').DataTable({
    	fixedHeader: {
	        header: true
	    },
	    "dom": '<<t>ilp>',
	    "pagingType": "simple_numbers",		// 设置分页控件的模式
        "processing": true, 				// 打开数据加载时的等待效果
        "serverSide": true,					// 打开后台分页
        "ordering" : false,
        "order": [[1, 'asc']],
        "bPaginate": true,                  // 分页设置
        "bLengthChange": true,
        "bFilter": false,                   // 搜索设置
        "bSort": false,
        "bInfo": true,
        "bAutoWidth": true,
        "bStateSave":true,
        "bDestory":true,
//        scrollY: "300px",
        scrollX:  true,
        scrollCollapse: true,
        paging:true,
        fixedColumns:   {
            leftColumns: 3,
        },
         "ajax":{
         	"url":ctx+"users/findlist",
         	"data":function(d){
         		var serviceType = $("#serviceType").val();
         		var tenantName = $("#tenantName").val();
         		var tenantLevel = $("#tenantLevel").val();
         		var tenantBoss = $("#tenantBoss").val();
         		var tenantTel = $("#tenantTel").val();
         		d.serviceType =serviceType;
         		d.tenantName = tenantName;
         		d.tenantLevel = tenantLevel;
         		d.tenantBoss = tenantBoss;
         		d.tenantTel = tenantTel;
         	}
         },
         "columns":[
                 {"data": null,"targets": 0},
	             {
	            	 data:"serviceType",
	            	 render:function(data, type, row) {
	            		 if(data == "" || data == null) {
	            			 return "-";
	            		 }
	            		 return data;
	            	 }
	             },
	             {
	            	 data:"tenantName",
	            	 render:function(data, type, row) {
	            		 if(data == "" || data == null) {
	            			 return "-";
	            		 }
	            		 return data;
	            	 }
	             },
	             {
	            	 data:"tenantLevel",
	            	 render:function(data, type, row) {
	            		 if(data == 0){
	            			 return "小";
	            		 }
	            		 if(data == 1){
	            			 return "中";
	            		 }
	            		 if(data == 2){
	            			 return "大";
	            		 }
	            		 if(data == "" || data == null) {
	            			 return "-";
	            		 }
	            		 return data;
	            	 }
	             },
	             {
	            	 data:"tenantBoss",
	            	 render:function(data, type, row) {
	            		 if(data == "" || data == null) {
	            			 return "-";
	            		 }
	            		 return data;
	            	 }
	             },
	             {
	            	 data:"tenantTel",
	            	 render:function(data, type, row) {
	            		 if(data == "" || data == null) {
	            			 return "-";
	            		 }
	            		 return data;
	            	 }
	             },
	             {
	            	 data:"resourceType",
	            	 render:function(data, type, row) {
	            		 if(data == 1 ){
	            			 return "Flume";
	            		 }
	            		 if(data == 2 ){
	            			 return "FTP集群";
	            		 }
	            		 if(data == 3 ){
	            			 return "Hbase";
	            		 }
	            		 if(data == 4 ){
	            			 return "hue";
	            		 }
	            		 if(data == 5 ){
	            			 return "Hive";
	            		 }
	            		 if(data == 6 ){
	            			 return "IMPALA";
	            		 }
	            		 if(data == 7 ){
	            			 return "KAFKA";
	            		 }
	            		 if(data == 8 ){
	            			 return "MPP";
	            		 }
	            		 if(data == 9 ){
	            			 return "Mysql";
	            		 }
	            		 if(data == 10 ){
	            			 return "Oracle";
	            		 }
	            		 if(data == 11 ){
	            			 return "Redis";
	            		 }
	            		 if(data == 12 ){
	            			 return "spark";
	            		 }
	            		 if(data == 13 ){
	            			 return "storm";
	            		 }
	            		 if(data == 14 ){
	            			 return "接口机";
	            		 }
	            		 if(data == 15 ){
	            			 return "虚拟机";
	            		 }
	            		 if(data == 16 ){
	            			 return "物理裸机";
	            		 }
	            		 if(data == 17 ){
	            			 return "应用服务器";
	            		 }
	            		 if(data == "" || data == null) {
	            			 return "-";
	            		 }
	            		 return data;
	            	 }
	             },
	             {
	            	 data:"fileCount",
	            	 render:function(data, type, row) {
	            		 if(data == "" || data == null) {
	            			 return "-";
	            		 }
	            		 return data;
	            	 }
	             },
	             {
	            	 data:"storage",
	            	 render:function(data, type, row) {
	            		 if(data == "" || data == null) {
	            			 return "-";
	            		 }
	            		 return data;
	            	 }
	             },
	             {
	            	 data:"storageUsage",
	            	 render:function(data, type, row) {
	            		 if(data == "" || data == null) {
	            			 return "-";
	            		 }
	            		 return data;
	            	 }
	             },
	             {
	            	 data:"storageUsageRate",
	            	 render:function(data, type, row) {
	            		 if(data == "" || data == null) {
	            			 return "-";
	            		 }
	            		 return data;
	            	 }
	             },
	             {
	            	 data:"cpuNum",
	            	 render:function(data, type, row) {
	            		 if(data == "" || data == null) {
	            			 return "-";
	            		 }
	            		 return data;
	            	 }
	             },
	             {
	            	 data:"cpuMax",
	            	 render:function(data, type, row) {
	            		 if(data == "" || data == null) {
	            			 return "-";
	            		 }
	            		 return data;
	            	 }
	             },
	             {
	            	 data:"cpuAvg",
	            	 render:function(data, type, row) {
	            		 if(data == "" || data == null) {
	            			 return "-";
	            		 }
	            		 return data;
	            	 }
	             },
	             {
	            	 data:"memorySize",
	            	 render:function(data, type, row) {
	            		 if(data == "" || data == null) {
	            			 return "-";
	            		 }
	            		 return data;
	            	 }
	             },
	             {
	            	 data:"memoryMax",
	            	 render:function(data, type, row) {
	            		 if(data == "" || data == null) {
	            			 return "-";
	            		 }
	            		 return data;
	            	 }
	             },
	             {
	            	 data:"memoryAvg",
	            	 render:function(data, type, row) {
	            		 if(data == "" || data == null) {
	            			 return "-";
	            		 }
	            		 return data;
	            	 }
	             },
	             {
	            	 data:"askDate",
	            	 render:function(data, type, row) {
	            		 if(data == "" || data == null) {
	            			 return "-";
	            		 }
	            		 return data;
	            	 }
	             },
	             {
	            	 data:"changeDate",
	            	 render:function(data, type, row) {
	            		 if(data == "" || data == null) {
	            			 return "-";
	            		 }
	            		 return data;
	            	 }
	             },
	             {
	            	 data:"openDate",
	            	 render:function(data, type, row) {
	            		 if(data == "" || data == null) {
	            			 return "-";
	            		 }
	            		 return data;
	            	 }
	             },
	             null
	     ],
	     "aoColumnDefs":[
	                     {//倒数第一列
	                     	"targets":-1,
	                     	"bSortable": false,
	                     	 render: function(data, type, row) {
	                         	var html ='<button class="btn btn-xs tdEdit btn-danger" value="'+row.tdId+'">编辑</button><button class="btn btn-xs btn-danger tdDelete" value="'+row.tdId+'">删除</button>';
	                             return html;
	                     	}
	                     }],
         "language": {
 	        "paginate": {
 	                 "previous": "首页",
 	                 "next": "下一页"
 	        },
             "info": "显示_START_到_END_, 共计_TOTAL_条数据",
             "zeroRecords": "无记录",
             "infoEmpty": "共计0",
             "lengthMenu": "每页显示 _MENU_ 记录",
             "infoFiltered": ""
             //"processing": "加载中......"
             // "zeroRecords": "没有找到相关内容",
             // "search": "搜索 : "
         },
        "fnDrawCallback": function(){
        	var api = this.api();
        	var startIndex= api.context[0]._iDisplayStart;//获取到本页开始的条数
        	api.column(0).nodes().each(function(cell, i) {
        		cell.innerHTML = startIndex + i + 1;
        	});
        }
    });

        //新增表单提交
    $('#addToolBtn').click(function() {
        	var serviceType=$("#add-serviceType").val();
        	var tenantName=$("#add-tenantName").val();
        	var tenantLevel=$("#add-tenantLevel").val();
        	var tenantBoss=$("#add-tenantBoss").val();
        	var tenantTel=$("#add-tenantTel").val();
        	var resourceType=$("#add-resourceType").val();
        	var fileCount=$("#add-fileCount").val();
        	var storage=$("#add-storage").val();
        	var storageUsage=$("#add-storageUsage").val();
        	var storageUsageRate=$("#add-storageUsageRate").val();
        	var cpuNum=$("#add-cpuNum").val();
        	var cpuMax=$("#add-cpuMax").val();
        	var cpuAvg=$("#add-cpuAvg").val();
        	var memorySize=$("#add-memorySize").val();
        	var memoryMax=$("#add-memoryMax").val();
        	var memoryAvg=$("#add-memoryAvg").val();
        	var askDate=$("#add-askDate").val();
        	var changeDate=$("#add-changeDate").val();
        	var openDate=$("#add-openDate").val();
        	$.ajax({
          		url :ctx + "users/addP",
          		type : "get",
          		data: {serviceType:serviceType,tenantName:tenantName,tenantLevel:tenantLevel,
          			tenantBoss:tenantBoss,tenantTel:tenantTel,resourceType:resourceType,
          			fileCount:fileCount,storage:storage,storageUsage:storageUsage,
          			storageUsageRate:storageUsageRate,cpuNum:cpuNum,cpuMax:cpuMax,cpuAvg:cpuAvg,
          			memorySize:memorySize,memoryMax:memoryMax,memoryAvg:memoryAvg,
          			askDate:askDate,changeDate:changeDate,openDate:openDate},
          		success : function(data) {
          			refreshTables();
          			addClean();
          			}
          		});
    });
    
  //编辑上传工具
    function ToolOperate(e) {
        e = e || window.event;
        var tar = e.target || e.srcElement;
        var tarName = tar.tagName;
        var tarHTML = tar.innerHTML;
        var tarval = tar.value;
        console.log(tar);
        console.log(tarName);
        console.log(tarHTML);
        console.log(tarval);
        if (tarName == 'BUTTON') {
            console.log(1);
            if (tarHTML == '编辑') {
            	$.ajax({
            		 type:"get",
                     url:ctx + "users/update",
                     data:{"tdId":tarval},
                     success:function(data){
                    	 $("#update-serviceType").val(data.serviceType);
                     	$("#update-tenantName").val(data.tenantName);
                     	$("#update-tenantLevel").val(data.tenantLevel);
                     	$("#update-tenantBoss").val(data.tenantBoss);
                     	$("#update-tenantTel").val(data.tenantTel);
                     	$("#update-resourceType").val(data.resourceType);
                     	$("#update-fileCount").val(data.fileCount);
                     	$("#update-storage").val(data.storage);
                     	$("#update-storageUsage").val(data.storageUsage);
                     	$("#update-storageUsageRate").val(data.storageUsageRate);
                     	$("#update-cpuNum").val(data.cpuNum);
                     	$("#update-cpuMax").val(data.cpuMax);
                     	$("#update-cpuAvg").val(data.cpuAvg);
                     	$("#update-memorySize").val(data.memorySize);
                     	$("#update-memoryMax").val(data.memoryMax);
                     	$("#update-memoryAvg").val(data.memoryAvg);
                     	$("#update-askDate").val(data.askDate);
                     	$("#update-changeDate").val(data.changeDate);
                     	$("#update-openDate").val(data.openDate);	
                       $("#update-tdId").val(data.tdId);
                     }
            	});
                $('#uploadToolModal').modal('show');
            } 
        }
    }
    
    
    //删除
    $('#tools_table tbody').on('click','button.tdDelete',function(){
    	 var tdId=$(this).val();
    	 $('#alertModal .modal-body').html('确定要删除该工具？');
         $('#alertModal').modal('show');
         $('#removeTool').unbind('click');
         $('#removeTool').click(function(){
        	 $.post( ctx+"users/delete",
    	  		  	  { tdId:tdId,},
    	  	  			function(data){
    	  		  	    $('#alertModal').modal('hide');
    	  		  	    refreshTables();
    	  		    })
         })
    });
    $('#tools_table tbody').unbind('click', ToolOperate);
    $('#tools_table tbody').bind('click', ToolOperate);
    
    //编辑表单提交
    $('#updateToolBtn').click(function() {
    	var serviceType=$("#update-serviceType").val();
    	var tenantName=$("#update-tenantName").val();
    	var tenantLevel=$("#update-tenantLevel").val();
    	var tenantBoss=$("#update-tenantBoss").val();
    	var tenantTel=$("#update-tenantTel").val();
    	var resourceType=$("#update-resourceType").val();
    	var fileCount=$("#update-fileCount").val();
    	var storage=$("#update-storage").val();
    	var storageUsage=$("#update-storageUsage").val();
    	var storageUsageRate=$("#update-storageUsageRate").val();
    	var cpuNum=$("#update-cpuNum").val();
    	var cpuMax=$("#update-cpuMax").val();
    	var cpuAvg=$("#update-cpuAvg").val();
    	var memorySize=$("#update-memorySize").val();
    	var memoryMax=$("#update-memoryMax").val();
    	var memoryAvg=$("#update-memoryAvg").val();
    	var askDate=$("#update-askDate").val();
    	var changeDate=$("#update-changeDate").val();
    	var openDate=$("#update-openDate").val();	
        var tdId=$("#update-tdId").val();
        	 $.ajax({
           		url :ctx + "users/updateP",
           		type : "get",
           		data: {serviceType:serviceType,tenantName:tenantName,tenantLevel:tenantLevel,
          			tenantBoss:tenantBoss,tenantTel:tenantTel,resourceType:resourceType,
          			fileCount:fileCount,storage:storage,storageUsage:storageUsage,storageUsageRate:storageUsageRate,
          			cpuNum:cpuNum,cpuMax:cpuMax,cpuAvg:cpuAvg,memorySize:memorySize,memoryMax:memoryMax,
          			memoryAvg:memoryAvg,askDate:askDate,changeDate:changeDate,openDate:openDate,tdId:tdId},
           		success : function(data) {
           		    refreshTables();
           			updateClean();
           			}
           		});
//        }
    });
    
    //编辑点击提交后，重置验证
    function updateClean(){
    	$('#uploadToolModal').modal('hide');
    }
    //新增点击提交后，重置验证
    function addClean(){
    	$('#addToolModal').modal('hide');
    }

    //新增表单关闭
    $('#addReloadHTML').click(function() {
        window.location.reload();
    })
     //编辑表单关闭
    $('#updateReloadHTML').click(function() {
        window.location.reload();
    })
    
    
    
    
});

//搜索按钮
function clickDisTable(){
	tables.ajax.reload();
};

function clickAllTable(){
	tables.ajax.reload();
}

//当页刷新
function refreshTables(){
	tables.draw(false);
}
//清空按钮
function cleanDisSearch(){
	$("#serviceType").val("");
	$("#tenantName").val("");
	$("#tenantLevel").val("");
    $("#tenantBoss").val("");
    $("#tenantTel").val("");
    clickDisTable();
}
//文件导出
function exportDisFile() {
	var length = table.data().length;
	if (length < 1) {
		layui.use('layer', function(){
			  var layer = layui.layer;
			  layer.alert('导出数据为空，请您重新选择导出数据', {icon: 5});
		});   
		return;
	}
	location.href=ctx+'users/disExport' ;
}

function uploadFileNum(){
	$('#uploadFileNumModal').modal('show');
}

function uploadOraFtp(){
	 var formData = new FormData($( "#uploadOraFtpForm" )[0]);  
	 $.ajax({  
         url: ctx + "analyse/orcftp" ,  
         type: 'POST',  
         data: formData,  
           
         cache: false,  
         contentType: false,  
         processData: false,  
         success: function (returndata) {  
             alert(returndata);  
         }
          
    });  
}

function uploadHdfsTxt(){
	var formData = new FormData($('#uploadHdfsTxtForm')[0]);
	$.ajax({  
        url: ctx + "analyse/hbase" ,  
        type: 'POST',  
        data: formData,  
          
        cache: false,  
        contentType: false,  
        processData: false,  
        success: function (returndata) {  
            alert(returndata);  
        }
         
   });  
}

function uploadWeb(){
	var formData = new FormData($('#uploadWebForm')[0]);
	$.ajax({
		url:  ctx + "analyse/websever",
		type:  'POST',
		data: formData,
		
		cache: false,
		contentType: false,
		processData: false,
		success: function(returndata){
			alert(returndata);
		}
	})
}

function uploadCpuMem(){
	var formData = new FormData($('#uploadCpuMemForm')[0]);
	$.ajax({
		url: ctx + "analyse/yarn",
		type: 'POST',
		data: formData,
		
		cache: false,
		contentType:false,
		processData: false,
		success: function(returndata){
			alert(returndata);
		}
	})
}