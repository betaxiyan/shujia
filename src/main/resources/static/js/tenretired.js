var table;
$(document).ready(function() {
	
	table = $('#leave_table').dataTable({
		fixedHeader: {
	        header: true
	    },
	    "dom": '<<t>ilp>',   		
 	    "pagingType": "simple_numbers",		// 设置分页控件的模式
 	    "processing": false, 				// 打开数据加载时的等待效果
        "serverSide": true,				// 打开后台分页
        "ordering" : false,
        "order": [[1, 'asc']],
        "bPaginate": true,                  // 分页设置
        "bLengthChange": true,
        "bFilter": false,                   // 搜索设置
        "bSort": false,
        "bInfo": true,
        "bAutoWidth": true,
        "ajax":{
     		"url":ctx+"tenant/findTenretiredList",
        	"data":function(d){
        		d.tenantName = $("#tenantName").val();
        		d.tenantInterface= $("#tenantInterface").val();
             }
        },
        "columns":[
              	 	{	"data": null,"targets": 0},//序号
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
       	                 if(data == "" || data == null) {
            			    return "-";
            		     }else if(data==2){
       	           			 return "大";
       	           		 }else if(data==1){
       	           			 return "中";
       	           		 }else if(dada==0){
       	           			 return "小";
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
                 		 if(data == "" || data == null) {
                 			 return "-";
                 		 }else if(data==1){
                 			 return "Flume";
                 		 }else if(data==2){
                 			 return "FTP集群";
                 		 }else if(data==3){
                 			 return "Hbase";
                 		 }else if(data==4){
                 			 return "hue";
                 		 }else if(data==5){
                 			 return "Hive";
                 		 }else if(data==6){
                 			 return "IMPALA";
                 		 }else if(data==7){
                 			 return "KAFKA";
                 		 }else if(data==8){
                 			 return "MPP";
                 		 }else if(data==9){
                 			 return "Mysql";
                 		 }else if(data==10){
                 			 return "Oracle";
                 		 }else if(data==11){
                 			 return "Redis";
                 		 }else if(data==12){
                 			 return "spark";
                 		 }else if(data==13){
                 			 return "storm";
                 		 }else if(data==14){
                 			 return "接口机";
                 		 }else if(data==15){
                 			 return "虚拟机";
                 		 }else if(data==16){
                 			 return "物理机";
                 		 }else if(data==17){
                 			 return "应用服务器";
                 		 }
                 		 return data;
                 	 }
                },
                {
                 	 data:"askIp",
                 	 render:function(data, type, row) {
                 		 if(data == "" || data == null) {
                 			 return "-";
                 		 }
                 		 return data;
                 	 }
                },
                {
                 	 data:"hostNum",
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
                 	 data:"storageUnit",
                 	 render:function(data, type, row) {
                 		 if(data == "" || data == null) {
                 			 return "-";
                 		 }
                 		 return data;
                 	 }
                },
                {
                 	 data:"computingResourceRate",
                 	 render:function(data, type, row) {
                 		 if(data == "" || data == null) {
                 			 return "-";
                 		 }
                 		 return data;
                 	 }
                },
                {
                 	 data:"computeRoom",
                 	 render:function(data, type, row) {
                 		 if(data == "" || data == null) {
                 			 return "-";
                 		 }
                 		 return data;
                 	 }
                },
                {
                 	 data:"uniplatformNum",
                 	 render:function(data, type, row) {
                 		 if(data == "" || data == null) {
                 			 return "-";
                 		 }
                 		 return data;
                 	 }
                },
                {
                 	 data:"numOf4a",
                 	 render:function(data, type, row) {
                 		 if(data == "" || data == null) {
                 			 return "-";
                 		 }
                 		 return data;
                 	 }
                },
                {
                 	 data:"demand",
                 	 render:function(data, type, row) {
                 		 if(data == "" || data == null) {
                 			 return "-";
                 		 }else if(data.length > 9){//如果data长度大于7
	            			 var html = '<a class="ten-toggle" data-toggle="tooltip" data-placement="bottom" herf="#" title="'+data+'">'+data.substring(0,9)+'...</a>';
	            			 return html;
	            		 }
                 		 return data;
                 	 }
                },
                {
                 	 data:"serviceName",
                 	 render:function(data, type, row) {
                 		 if(data == "" || data == null) {
                 			 return "-";
                 		 }
                 		 return data;
                 	 }
                },
                {
                 	 data:"sequenceName",
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
                 	 data:"openDate",
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
                 	 data:"end_rent_date",
                 	 render:function(data, type, row) {
                 		 if(data == "" || data == null) {
                 			 return "-";
                 		 }
                 		 return data;
                 	 }
                },
                {
                 	 data:"tenantInterface",
                 	 render:function(data, type, row) {
                 		 if(data == "" || data == null) {
                 			 return "-";
                 		 }
                 		 return data;
                 	 }
                },
                {
                 	 data:"remark",
                 	 render:function(data, type, row) {
                 		 if(data == "" || data == null) {
                 			 return "-";
                 		 }
                 		 return data;
                 	 }
                },
                {
               	 data:"tlId",
               	 render:function(data, type, row) {
               		 return '<td><button class="btn btn-xs btn-danger" value="'+data+'">编辑</button>&nbsp;&nbsp;&nbsp;&nbsp;<button class="btn btn-xs btn-danger" value="'+data+'">删除</button></td>';
               	 }
                }
             ],
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
    $('#addTenBtn').click(function() {
//        var bootstrapValidator = $('#addUserForm').data('bootstrapValidator');
//        bootstrapValidator.validate();
//        if (bootstrapValidator.isValid()) {
    	 var serviceType= $("#add-serviceType").val();
    	 var tenantName=$("#add-tenantName").val();
    	 var tenantLevel=$("#add-tenantLevel").val();
    	 var tenantBoss=$("#add-tenantBoss").val();
    	 var tenantTel=$("#add-tenantTel").val();
    	 var resourceType=$("#add-resourceType").val();
    	 var askIp=$("#add-askIp").val();
    	 var hostNum=$("#add-hostNum").val();
    	 var storage=$("#add-storage").val();
    	 var storageUnit=$("#add-storageUnit").val();
    	 var computingResourceRate=$("#add-computingResourceRate").val();
    	 var computeRoom=$("#add-computeRoom").val();
    	 var uniplatformNum=$("#add-uniplatformNum").val();
    	 var numOf4a=$("#add-numOf4a").val();
    	 var demand=$("#add-demand").val();
    	 var serviceName=$("#add-serviceName").val();
    	 var sequenceName=$("#add-sequenceName").val();
    	 var askDate=$("#add-askDate").val();
    	 var openDate=$("#add-openDate").val();
    	 var changeDate=$("#add-changeDate").val();
    	 var endRentDate=$("#add-endRentDate").val();
    	 var tenantInterface=$("#add-tenantInterface").val();
    	 var remark=$("#add-remark").val();
    	$.ajax({
      		url :ctx + "tenant/saveTenretired",
      		type : "get",
      		data: {serviceType:serviceType,tenantName:tenantName,tenantLevel:tenantLevel,tenantBoss:tenantBoss,tenantTel:tenantTel,resourceType:resourceType,askIp:askIp,hostNum:hostNum,storage:storage,storageUnit:storageUnit,computingResourceRate:computingResourceRate,computeRoom:computeRoom,uniplatformNum:uniplatformNum,numOf4a:numOf4a,demand:demand,serviceName:serviceName,sequenceName:sequenceName,askDate:askDate,openDate:openDate,changeDate:changeDate,endRentDate:endRentDate,tenantInterface:tenantInterface,remark:remark},
      		success : function(data) {
      			addClean();
      			data = eval("(" + data + ")");
	      			if (data.status == "200") {
	      				clickTable();
	      				alert("添加成功");
	      			} else {
	      				alert("添加失败");
	      			}
      			}
      		});
//    }
});
    
    $('#addReloadHTML').click(function(){
    	window.location.reload();
    });
     
    //编辑表单提交
    $('#updateTenBtn').click(function() {
//       var bootstrapValidator = $('#updateUserForm').data('bootstrapValidator');
//        bootstrapValidator.validate();
//        if (bootstrapValidator.isValid()) {
    	 var tlId=$("#update-tlId").val();
    	 var serviceType= $("#update-serviceType").val();
    	 var tenantName=$("#update-tenantName").val();
    	 var tenantLevel=$("#update-tenantLevel").val();
    	 var tenantBoss=$("#update-tenantBoss").val();
    	 var tenantTel=$("#update-tenantTel").val();
    	 var resourceType=$("#update-resourceType").val();
    	 var askIp=$("#update-askIp").val();
    	 var hostNum=$("#update-hostNum").val();
    	 var storage=$("#update-storage").val();
    	 var storageUnit=$("#update-storageUnit").val();
    	 var computingResourceRate=$("#update-computingResourceRate").val();
    	 var computeRoom=$("#update-computeRoom").val();
    	 var uniplatformNum=$("#update-uniplatformNum").val();
    	 var numOf4a=$("#update-numOf4a").val();
    	 var demand=$("#update-demand").val();
    	 var serviceName=$("#add-serviceName").val();
    	 var sequenceName=$("#update-sequenceName").val();
    	 var askDate=$("#update-askDate").val();
    	 var openDate=$("#update-openDate").val();
    	 var changeDate=$("#update-changeDate").val();
    	 var endRentDate=$("#update-endRentDate").val();
    	 var tenantInterface=$("#update-tenantInterface").val();
    	 var remark=$("#update-remark").val();
        	 $.ajax({
           		url :ctx+"tenant/tenretired/update",
           		type : "get",
           		data: {tlId:tlId,serviceType:serviceType,tenantName:tenantName,tenantLevel:tenantLevel,tenantBoss:tenantBoss,tenantTel:tenantTel,resourceType:resourceType,askIp:askIp,hostNum:hostNum,storage:storage,storageUnit:storageUnit,computingResourceRate:computingResourceRate,computeRoom:computeRoom,uniplatformNum:uniplatformNum,numOf4a:numOf4a,demand:demand,serviceName:serviceName,sequenceName:sequenceName,askDate:askDate,openDate:openDate,changeDate:changeDate,endRentDate:endRentDate,tenantInterface:tenantInterface,remark:remark},
           		success : function(data) {
           			updateClean();
           			data = eval("(" + data + ")");
           			}
           		});
//        }
    });
    
    //编辑点击提交后，重置验证
    function updateClean(){
    	$('#updateTenModal').modal('hide');
//    	$('#updateTenForm').bootstrapValidator('resetForm', true);
    }
    
    //编辑表单关闭
    $('#updateReloadHTML').click(function(){
    	window.location.reload();
    });
    
    //编辑&删除
    function TenretiredOperate(e) {
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
           if (tarHTML == '编辑') {
           	$.ajax({
           		 type:"get",
                    url:ctx+"tenant/tenretired/edit",
                    data:{"tlId":tarval},
                    success:function(data){
                    	$("#update-tlId").val(data.tlId);
                   	    $("#update-serviceType").val(data.serviceType);
                   	    $("#update-tenantName").val(data.tenantName);
                   	    $("#update-tenantLevel").val(data.tenantLevel);
                   	    $("#update-tenantBoss").val(data.tenantBoss);
                   	    $("#update-tenantTel").val(data.tenantTel);
                   	    $("#update-resourceType").val(data.resourceType);
                   	    $("#update-askIp").val(data.askIp);
                   	    $("#update-hostNum").val(data.hostNum);
                   	    $("#update-storage").val(data.storage);
                   	    $("#update-storageUnit").val(data.storage);
                   	    $("#update-computingResourceRate").val(data.computingResourceRate);
                   	    $("#update-computeRoom").val(data.computeRoom);
                   	    $("#update-uniplatformNum").val(data.uniplatformNum);
                   	    $("#update-numOf4a").val(data.numOf4a);
                   	    $("#update-demand").val(data.demand);
                   	    $("#update-serviceName").val(data.serviceName);
                   	    $("#update-sequenceName").val(data.sequenceName);
                   	    $("#update-askDate").val(data.askDate);
                   	    $("#update-openDate").val(data.openDate);
                   	    $("#update-changeDate").val(data.changeDate);
                   	    $("#update-endRentDate").val(data.endRentDate);
                   	    $("#update-tenantInterface").val(data.tenantInterface);
                   	    $("#update-remark").val(data.remark);
                    }
           	});
              $('#updateTenModal').modal('show');
          }else if (tarHTML == '删除') {
              $('#alertModal .modal-body').html('确定要删除？');
              $('#alertModal').modal('show');
              $('#removeTool').unbind('click');
              $('#removeTool').click(function() {
           	$.ajax({
          		 type:"get",
                   url:ctx + "tenant/retired/validateByTlId",
                   data:{"tlId":tarval},
                   success:function(data){
                   	if(data){
                   		$("#delete-tlId").val(tarval);
                       	$("#deleteTenForm").submit();
                   	}else{ 
                   		alert("无法删除！");
                   	}
                   }
           	});
           	$('#alertModal').modal('hide');
           	
           })
       }
     }
   }
    
    $('#leave_table tbody').unbind('click',TenretiredOperate);
    $('#leave_table tbody').bind('click',TenretiredOperate);
    function addClean(){
    	$('#addTenModal').modal('hide');
    	$("#add-tenantName").val("");
    	$("#add-tenantLevel").val("");
    	$("#add-tenantBoss").val("");
    	$("#add-tenantTel").val("");
    	$("#add-resourceType").val("");
    	$("#add-askIp").val("");
    	$("#add-hostNum").val("");
    	$("#add-storage").val("");
    	$("#add-storageUnit").val("");
    	$("#add-computingResourceRate").val("");
    	$("#add-computeRoom").val("");
    	$("#add-uniplatformNum").val("");
    	$("#add-numOf4a").val("");
    	$("#add-demand").val("");
    	$("#add-serviceName").val("");
    	$("#add-sequenceName").val("");
    	$("#add-askDate").val("");
    	$("#add-openDate").val("");
    	$("#add-changeDate").val("");
    	$("#add-endRentDate").val("");
    	$("#add-tenantInterface").val("");
    	$("#add-remark").val("");

    }
      

    function updateClean(){
    	$('#updateTenModal').modal('hide');
    	$("#update-tenantName").val("");
    	$("#update-tenantLevel").val("");
    	$("#update-tenantBoss").val("");
    	$("#update-tenantTel").val("");
    	$("#update-resourceType").val("");
    	$("#update-askIp").val("");
    	$("#update-hostNum").val("");
    	$("#update-storage").val("");
    	$("#update-storageUnit").val("");
    	$("#update-computingResourceRate").val("");
    	$("#update-computeRoom").val("");
    	$("#update-uniplatformNum").val("");
    	$("#update-numOf4a").val("");
    	$("#update-demand").val("");
    	$("#update-serviceName").val("");
    	$("#update-sequenceName").val("");
    	$("#update-askDate").val("");
    	$("#update-openDate").val("");
    	$("#update-changeDate").val("");
    	$("#update-endRentDate").val("");
    	$("#update-tenantInterface").val("");
    	$("#update-remark").val("");

    }
   /*************************/     
});


  

//查询操作按钮
function clickTable() {
	table.api().ajax.reload();
}

//清除查询数据
function cleanSearch() {
	$("#tenantName").val("");
	$("#tenantInterface").val("");
}

//文件导出
function exportFile() {
	var length = table.api().data().length;
	if (length < 1) {
		layui.use('layer', function(){
			  var layer = layui.layer;
			  layer.alert('导出数据为空，请您重新选择导出数据', {icon: 5});
		});   
		return;
	}
	var tenantName=$("#tenantName").val();
	var tenantInterface=$("#tenantInterface").val();
	location.href=ctx+'tenant/exportTenretired?tenantName='+tenantName+ '&tenantInterface='+tenantInterface;
}


