var table;
$(document).ready(function() {
	
	table = $('#leave_table').DataTable({
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
        "bStateSave":true,
//        scrollY: "300px",
        scrollX:  true,
        scrollCollapse: true,
        paging:true,
        fixedColumns:   {
            leftColumns: 3,
        },
        "ajax":{
     		"url":ctx+"tenant/findTenretiredList",
        	"data":function(d){
        		d.serviceType=$("#tenantServiceType").val();
        		d.tenantName = $("#tenantLeaveName").val();
        		d.tenantLevel=$("#tenantLeaveLevel").val();
        		d.tenantBoss=$("#tenantLeaveBoss").val();
        		d.tenantTel=$("#tenantLeaveTel").val();
        		d.tenantInterface= $("#tenantLeaveInterface").val();
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
                 		 if(data == "" || data == null) {
                 			 return "-";
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
                 		 return (new Date(data)).Format("yyyyMMdd");
                 	 }
                },
                {
                 	 data:"openDate",
                 	 render:function(data, type, row) {
                 		 if(data == "" || data == null) {
                 			 return "-";
                 		 }
                 		 return (new Date(data)).Format("yyyyMMdd");
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
                 	 data:"endRentDate",
                 	 render:function(data, type, row) {
                 		 if(data == "" || data == null) {
                 			 return "-";
                 		 }
                 		 return (new Date(data)).Format("yyyyMMdd");
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
                },null
             ],
	     "aoColumnDefs":[
	                     {//倒数第一列
	                     	"targets":-1,
	                     	"bSortable": false,
	                     	 render: function(data, type, row) {
	                         	var html ='<button class="btn btn-xs Tledit btn-danger" value="'+row.tlId+'">编辑</button><button class="btn btn-xs btn-danger Tldelete" value="'+row.tlId+'">删除</button>';
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
    //格式化页面展示的时间
    Date.prototype.Format = function(fmt) { //author: meizz 
        var o = { 
            "M+": this.getMonth() + 1, 
            //月份 
            "d+": this.getDate(), 
            //日 
            "h+": this.getHours(), 
            //小时 
            "m+": this.getMinutes(), 
            //分 
            "s+": this.getSeconds(), 
            //秒 
            "q+": Math.floor((this.getMonth() + 3) / 3), 
            //季度 
            "S": this.getMilliseconds() //毫秒 
        }; 
        if (/(y+)/.test(fmt)) { 
            fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length)); 
        } 
        for (var k in o) { 
            if (new RegExp("(" + k + ")").test(fmt)) { 
                fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length))); 
            } 
        } 
        return fmt; 
    } 


	  
     
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
           		data: {tlId:tlId,serviceType:serviceType,tenantName:tenantName,tenantLevel:tenantLevel,tenantBoss:tenantBoss,tenantTel:tenantTel,resourceType:resourceType,askIp:askIp,hostNum:hostNum,storage:storage,computingResourceRate:computingResourceRate,computeRoom:computeRoom,uniplatformNum:uniplatformNum,numOf4a:numOf4a,demand:demand,serviceName:serviceName,sequenceName:sequenceName,askDate:askDate,openDate:openDate,changeDate:changeDate,endRentDate:endRentDate,tenantInterface:tenantInterface,remark:remark},
           		success : function(data) {
           			updateClean();
           			}
           		});
//        	 clickLeaveTable();
        	 refresh();
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
    
    //编辑
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
                    	    $("#updateleave-uniplatformNum").val(data.uniplatformNum);
                    	    $("#updateleave-numOf4a").val(data.numOf4a);
                    	    $("#updateleave-demand").val(data.demand);
                    	    $("#updateleave-serviceName").val(data.serviceName);
                    	    $("#updateleave-sequenceName").val(data.sequenceName);
                    	    var askDate=data.askDate;
                    	    if(askDate==null){
                    	    	 $("#updateleave-askDate").val("");
                    	    }else{
                    	    	$("#updateleave-askDate").val((new Date(askDate)).Format("yyyyMMdd"));
                    	    }
                    	    var openDate=data.openDate;
                    	    if(opneDate==null){
                    	    	$("#updateleave-openDate").val("");
                    	    }else{
                    	    	$("#updateleave-openDate").val((new Date(openDate)).Format("yyyyMMdd"));
                    	    }
                    	    $("#updateleave-changeDate").val(data.changeDate);
                    	    var endRentDate=data.endRentDate;
                    	    if(endRentDate==null){
                    	    	 $("#updateleave-endRentDate").val("");
                    	    }else{
                    	    	 $("#updateleave-endRentDate").val((new Date(endRentDate)).Format("yyyyMMdd"));
                    	    }
                    	    $("#updateleave-tenantInterface").val(data.tenantInterface);
                    	    $("#updateleave-remark").val(data.remark);
                    }
           	});
              $('#updateTenModal').modal('show');
          }
     }
   }
    
  //删除
   $('#leave_table tbody').on( 'click', 'button.Tldelete', function () {
	   var tlId = $(this).val();//获得tlId 主码
	   $('#alertModal2 .modal-body').html('确定要删除该工具？');
       $('#alertModal2').modal('show');
       $('#removeTen').unbind('click');
       $('#removeTen').click(function() {
    	   $.post( ctx+"tenant/tenretired/delete",
 	  		  	  { tlId:tlId,},
 	  	  			function(data){
 	  		  	    $('#alertModal2').modal('hide');
// 	  		        clickLeaveTable();
 	  		  	    refresh();
 	  		    })
       })
	});
  
   
    $('#leave_table tbody').unbind('click',TenretiredOperate);
    $('#leave_table tbody').bind('click',TenretiredOperate);
   
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

function refresh(){
	table.draw(false);
}
//查询操作按钮
function clickLeaveTable(){
	table.ajax.reload();
}

//清除查询数据
function cleanLeaveSearch() {
	$("#tenantServiceType").val("");
	$("#tenantLeaveName").val("");
	$("#tenantLeaveLevel").val("");
	$("#tenantLeaveBoss").val("");
	$("#tenantLeaveTel").val("");
	$("#tenantLeaveInterface").val("");
	clickLeaveTable();
}

//文件导出
function exportFile() {
    var serviceType=$("#tenantServiceType").val();
	var tenantName = $("#tenantLeaveName").val();
	var tenantLevel=$("#tenantLeaveLevel").val();
	var tenantBoss=$("#tenantLeaveBoss").val();
	var tenantTel=$("#tenantLeaveTel").val();
	var tenantInterface= $("#tenantLeaveInterface").val();
	location.href=ctx+'tenant/exportTenretired?serviceType='+serviceType+'&tenantName='+tenantName+'&tenantLevel='+tenantLevel+'&tenantBoss='+tenantBoss+'&tenantTel='+tenantTel+ '&tenantInterface='+tenantInterface;
}
