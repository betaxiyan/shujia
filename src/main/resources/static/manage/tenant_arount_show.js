var table;
$(document).ready(function() {
	console.log("进入js");
	table = $('#tenant_arount_show_table').dataTable({
		fixedHeader: {
	        header: true
	    },
	    "dom": '<<t>ilp>',   		
 	    "pagingType": "simple_numbers",		// 设置分页控件的模式
 	    "processing": false, 				// 打开数据加载时的等待效果
        //"serverSide": true,				// 打开后台分页
        "ordering" : false,
        "order": [[1, 'asc']],
        "bPaginate": true,                  // 分页设置
        "bLengthChange": true,
        "bFilter": false,                   // 搜索设置
        "bSort": false,
        "bInfo": true,
        "bAutoWidth": true,
        "ajax":{
     		"url":ctx+"manage/findAllTenantAroundMgr",
        	"data":function(d){
        		d.tenantName = $("#tenantLeaveName").val();
        		d.tenantInterface= $("#tenantInterface").val();
             }
        },
        "columns":[{data:"ttaId",},               {data:"tenantId",},  {data:"tenantName",},
                   {data:"tenantLevel",},         {data:"tenantBoss",},{data:"tenantTel", },
                   {data:"numOfUnifiedPlatform",},{data:"numOf4a",},   {data:"tenantReqirement",},
                   {data:"tenantInterface",},      null,                null
                  ],
         //修改列
         "aoColumnDefs":[
                             {
                             	"targets":10,
                                 "data": null,
                                 "bSortable": false,
                                 "defaultContent": "<p>&nbsp;&nbsp;&nbsp;&nbsp;<a id=\"edit\" href=\"#\">修改</a></p>"
                             },
                             {
                              	"targets":11,
                                  "data": null,
                                  "bSortable": false,
                                  "defaultContent": "<p>&nbsp;&nbsp;&nbsp;&nbsp;<a id=\"del\"  href=\"#\">删除</a></p>"
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
	
	/**
	    * 查看修改
	    */
	   $('#tenant_arount_show_table tbody').on( 'click', 'a#edit', function () {
	       var data = $('#tenant_arount_show_table').DataTable().row($(this).parents('tr')).data();
	       $('#uploadTenModal').modal();
	       alert("查看修改："+data.tenantName +","+ data.ttaId );
	   } );

	   /**
	    * 删除
	    */
	   $('#tenant_arount_show_table tbody').on( 'click', 'a#del', function () {
	       var data = $('#tenant_arount_show_table').DataTable().row($(this).parents('tr')).data();
	       alert("删除："+data );
	   })
	
	
  //新增表单提交
    $('#addTeantAroundMgrBtn').click(function() {
//        var bootstrapValidator = $('#addUserForm').data('bootstrapValidator');
//        bootstrapValidator.validate();
//        if (bootstrapValidator.isValid()) {
    	 var tenantId= $("#addaround-tenantId").val();
    	 var tenantName=$("#addaround-tenantName").val();
    	 var tenantLevel=$("#addaround-tenantLevel").val();
    	 var tenantBoss=$("#addaround-tenantBoss").val();
    	 var tenantTel=$("#addaround-tenantTel").val();
    	 var numOfUnifiedPlatform=$("#addaround-numOfUnifiedPlatform").val();
    	 var numOf4a=$("#addaround-numOf4a").val();
    	 var tenantReqirement=$("#addaround-tenantReqirement").val();
    	 var serviceName=$("#addaround-serviceName").val();
    	 var sequenceName=$("#addaround-sequenceName").val();
    	 var askDate=$("#addaround-askDate").val();
    	 var openDate=$("#addaround-openDate").val();
    	 var changeDate=$("#addaround-changeDate").val();
    	 var endRentDate=$("#addaround-endRentDate").val();
    	 var tenantInterface=$("#addaround-tenantInterface").val();
    	 var remark=$("#addleave-remark").val();
    	$.ajax({
      		url :ctx + "tenant/saveTenretired",
      		type : "get",
      		data: {serviceType:serviceType,tenantName:tenantName,tenantLevel:tenantLevel,tenantBoss:tenantBoss,tenantTel:tenantTel,resourceType:resourceType,askIp:askIp,hostNum:hostNum,storage:storage,storageUnit:storageUnit,computingResourceRate:computingResourceRate,computeRoom:computeRoom,uniplatformNum:uniplatformNum,numOf4a:numOf4a,demand:demand,serviceName:serviceName,sequenceName:sequenceName,askDate:askDate,openDate:openDate,changeDate:changeDate,endRentDate:endRentDate,tenantInterface:tenantInterface,remark:remark},
      		success : function(data) {
      			//清空表单信息
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
    	 var tlId=$("#updateleave-tlId").val();
    	 var serviceType= $("#updateleave-serviceType").val();
    	 var tenantName=$("#updateleave-tenantName").val();
    	 var tenantLevel=$("#updateleave-tenantLevel").val();
    	 var tenantBoss=$("#updateleave-tenantBoss").val();
    	 var tenantTel=$("#updateleave-tenantTel").val();
    	 var resourceType=$("#updateleave-resourceType").val();
    	 var askIp=$("#updateleave-askIp").val();
    	 var hostNum=$("#updateleave-hostNum").val();
    	 var storage=$("#updateleave-storage").val();
    	 var storageUnit=$("#updateleave-storageUnit").val();
    	 var computingResourceRate=$("#updateleave-computingResourceRate").val();
    	 var computeRoom=$("#updateleave-computeRoom").val();
    	 var uniplatformNum=$("#updateleave-uniplatformNum").val();
    	 var numOf4a=$("#updateleave-numOf4a").val();
    	 var demand=$("#updateleave-demand").val();
    	 var serviceName=$("#updateleave-serviceName").val();
    	 var sequenceName=$("#updateleave-sequenceName").val();
    	 var askDate=$("#updateleave-askDate").val();
    	 var openDate=$("#updateleave-openDate").val();
    	 var changeDate=$("#updateleave-changeDate").val();
    	 var endRentDate=$("#updateleave-endRentDate").val();
    	 var tenantInterface=$("#updateleave-tenantInterface").val();
    	 var remark=$("#updateleave-remark").val();
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
                    	    $("#updateleave-tlId").val(data.tlId);
                    	    $("#updateleave-serviceType").val(data.serviceType);
                    	    $("#updateleave-tenantName").val(data.tenantName);
                    	    $("#updateleave-tenantLevel").val(data.tenantLevel);
                    	    $("#updateleave-tenantBoss").val(data.tenantBoss);
                    	    $("#updateleave-tenantTel").val(data.tenantTel);
                    	    $("#updateleave-resourceType").val(data.resourceType);
                    	    $("#updateleave-askIp").val(data.askIp);
                    	    $("#updateleave-hostNum").val(data.hostNum);
                    	    $("#updateleave-storage").val(data.storage);
                    	    $("#updateleave-storageUnit").val(data.storageUnit);
                    	    $("#updateleave-computingResourceRate").val(data.computingResourceRate);
                    	    $("#updateleave-computeRoom").val(data.computeRoom);
                    	    $("#updateleave-uniplatform").val(data.uniplatform);
                    	    $("#updateleave-numOf4a").val(data.numOf4a);
                    	    $("#updateleave-demand").val(data.demand);
                    	    $("#updateleave-serviceName").val(data.serviceName);
                    	    $("#updateleave-sequenceName").val(data.sequenceName);
                    	    $("#updateleave-askDate").val(data.askDate);
                    	    $("#updateleave-openDate").val(data.openDate);
                    	    $("#updateleave-changeDate").val(data.changeDate);
                    	    $("#updateleave-endRentDate").val(data.endRentDate);
                    	    $("#updateleave-tenantInterface").val(data.tenantInterface);
                    	    $("#updateleave-remark").val(data.remark);
                    }
           	});
              $('#updateTenModal').modal('show');
          }else if (tarHTML == '删除') {
              $('#alertModal2 .modal-body').html('确定要删除？');
              $('#alertModal2').modal('show');
              $('#removeTen').unbind('click');
              $('#removeTen').click(function() {
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
function clickLeaveTable(){
	table.api().ajax.reload();
}

//清除查询数据
function cleanLeaveSearch() {
	$("#tenantLeaveName").val("");
	$("#tenantInterface").val("");
	clickLeaveTable();
}

//文件导出
function exportFile() {
	var length = table.api().data().length;
	alert(length);
	if (length < 1) {
		layui.use('layer', function(){
			  var layer = layui.layer;
			  layer.alert('导出数据为空，请您重新选择导出数据', {icon: 5});
		});   
		return;
	}
	location.href=ctx+'manange/exportTenantAroundMgr';
}

function uploadFile(){
 	$('#uploadFileModal').modal('show');
}

function uploadFileSubmit(){
	$('#uploadFileForm').submit();
	$('#uploadFileModal').modal('hide');
	
}
