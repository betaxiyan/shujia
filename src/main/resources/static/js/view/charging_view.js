$(function(){
	loaddata()
})

function loaddata() {
	
	$('#charging_table').dataTable({
        "language": {
            "paginate": {
                "previous": "上一页",
                "next": "下一页"
            },
            "info": "显示_START_到_END_, 共计_TOTAL_条数据",
            "emptyTable": "无记录",
            "infoEmpty": "共计0",
            "search":"搜索：",
            "lengthMenu": "每页显示 _MENU_ 记录",
        },
        "scrollX": true,
        "destroy":true, //Cannot reinitialise DataTable,解决重新加载表格内容问题
        "bAutoWidth": true,        
        /* 分页设置 */
        "bPaginate": true,
        "bLengthChange": true,
        /* 搜索设置 */
        "bFilter": true,
        "bSort": false,
        /* 显示总条数 */
        "bInfo": true,
        "bAutoWidth": false,
        /*	数据源Ajax*/
        "ajax":{ url:"findAll"},
    	"columns": [				
    	            {"mData": "tenantName"},		{"mData": "serviceType"},			{"mData": "tenantType"}, 
    	            {"mData": "resourceTime"},		{"mData": "uniplatform4aTime"},		{"mData": "havedateTime"},
    	            {"mData": "resideDuration"},	{"mData": "monthlyFee"},			{"mData": "dataFee"}, 
    	            {"mData": "beginRentDate"},		{"mData": "tasteDuration"},			{"mData": "chargeBeginDate"},
    	            {"mData": "chargeEndDate"},		{"mData": "introduceUnicom"},		{"mData": "introduceTenant"}, 
    	            {"mData": "contact"},			{"mData": "askDate"},				{"mData": "signContract"},
    	            {"mData": "monthlyFeeRemark"},	{"mData": "endRentDate"},			{"mData": "remark"},
    	            null
    	            ],
        "aoColumnDefs":[
                        {//倒数第一列
                        	"targets":-1,
                        	"bSortable": false,
                        	render: function(data, type, row) {
                            	var html ='<button class="btn btn-xs jfedit btn-danger" value="'+row.tcId+'">编辑</button>&nbsp;&nbsp;&nbsp;&nbsp;<button class="btn btn-xs btn-danger jfdelete" value="'+row.tcId+'">删除</button>';
                                return html;
                        	}
                        },
                        {//第二列 服务类型
                        	"targets":1,
                        	render: function(data, type, row) {
                        		if (data == 10) {
                        			return "内部"  //10：内部
								}else
									return "外部"; //20：外部
                        	}
                        },
                        {//第三列 租户分类
                        	"targets":2,
                        	render: function(data, type, row) {
                        		if (data == 10) {
                        			return "近期租户" //10：近期租户
								}else
									return "历史租户";//20：历史租户
                        	}
                        },
                        {//倒数第五列是否签署合同
                        	"targets":-5,
                        	render: function(data, type, row) {
                        		if (data == 10) {
                        			return "已签署" //10：已签署
								}else
									return "未签署";//20：未签署
                        	}
                        },
                        {//第13列 到期日期 
                        	"targets":12,
                        	createdCell: function(td, cellData, rowData) {
                        		var data = cellData
                        		if ((data+"").length>=8) {
                        			var data1 = data.substr(0,4)+"/"+data.substr(4,2)+"/"+data.substr(6,2)
                        			var da_1 = new Date(Date.parse(data1));  
                        			var da_2 = new Date();
                        			if ((da_1-da_2)/1000/3600/24 < 4 && (da_1-da_2)/1000/3600/24 > 0) {//三天内到期
										/*该行变色*/
                        				$(td).parents('tr').css("background-color","yellow");
                        				$(td).css("color","red");
									}
                        			return data
                        		}else
                        			return data
                        	},
                        	 
                        }
                        
                    ],
    });
}


/*	显示修改页面		*/
   $('#charging_table tbody').on( 'click', 'button.jfedit', function () {
       var jstcId = $(this).val();//获得tcId 主码
       var data_1
       /*AJAX获取被修改的一行数据*/
       $.ajax({
    	   		url:"findByTcId",
    	   		type: "GET",
    	   		async:false,			//同步执行
    	   		data:{jstcId:jstcId},
    	   		success:function(data){
    	   			data_1=data
   				},
   				dataType:"json"
   			})
   	  /*分别设置显示的数据*/
   	  tcId = data_1.tcId	//获得 tcId
   	  var jfedittenantName = data_1.tenantName; //租户
      $('#jfedittenantName').val(jfedittenantName);  
      
      var jfeditserviceType = data_1.serviceType; //服务类型
      $('#jfeditserviceType').val(jfeditserviceType);
     
      var jfedittenantType = data_1.tenantType; //租户分类
      $('#jfedittenantType').val(jfedittenantType);  
      
      var jfeditresourceTime = data_1.resourceTime; //资源
      $('#jfeditresourceTime').val(jfeditresourceTime);  
      
      var jfedituniplatform4aTime = data_1.uniplatform4aTime; //统一及4A
      $('#jfedituniplatform4aTime').val(jfedituniplatform4aTime);  
      
      var jfedithavedateTime = data_1.havedateTime; //数据实际具备
      $('#jfedithavedateTime').val(jfedithavedateTime); 
      
      var jfeditresideDuration = data_1.resideDuration; //入住时长
      $('#jfeditresideDuration').val(jfeditresideDuration);  
      
      var jfeditmonthlyFee = data_1.monthlyFee; //月租费用（元/月）
      $('#jfeditmonthlyFee').val(jfeditmonthlyFee); 
      
      var jfeditdataFee = data_1.dataFee; //数据报价/万元
      $('#jfeditdataFee').val(jfeditdataFee);  
      
      var jfeditbeginRentDate = data_1.beginRentDate; //起租日期
      $('#jfeditbeginRentDate').val(jfeditbeginRentDate);  
      
      var jfedittasteDuration = data_1.tasteDuration; //试用期
      $('#jfedittasteDuration').val(jfedittasteDuration);  
      
      var jfeditchargeBeginDate = data_1.chargeBeginDate; //计费时期
      $('#jfeditchargeBeginDate').val(jfeditchargeBeginDate);
      
      var jfeditchargeEndDate = data_1.chargeEndDate; //到期日期
      $('#jfeditchargeEndDate').val(jfeditchargeEndDate);
      
      var jfeditintroduceUnicom = data_1.introduceUnicom; //联通引入方
      $('#jfeditintroduceUnicom').val(jfeditintroduceUnicom);  
      
      var jfeditintroduceTenant = data_1.introduceTenant; //引入方联系人（租户管理员）
      $('#jfeditintroduceTenant').val(jfeditintroduceTenant);  
      
      var jfeditcontact = data_1.contact; //联系方式
      $('#jfeditcontact').val(jfeditcontact);  
      
      var jfeditaskDate = data_1.askDate; //申请日期
      $('#jfeditaskDate').val(jfeditaskDate); 
      
      var jfeditsignContract = data_1.signContract; //是否签署
      $('#jfeditsignContract').val(jfeditsignContract);  
      
      var jfeditmonthlyFeeRemark = data_1.monthlyFeeRemark; //月租备注
      $('#jfeditmonthlyFeeRemark').val(jfeditmonthlyFeeRemark); 

      var jfeditendRentDate = data_1.endRentDate; //退租时间
      $('#jfeditendRentDate').val(jfeditendRentDate);  
      
      var jfeditremark = data_1.remark; //备注
      $('#jfeditremark').val(jfeditremark);  
   
     
   	  $('#jfeditmodel').modal('show');
   });
   
/*	  点击保存按钮	  */
   $('#jfeditsubmit').on("click",function(){
	   
	   /* 获取数据*/
	   	  var tenantName = $('#jfedittenantName').val();  //租户
	      
	      var serviceType = $('#jfeditserviceType').val();//服务类型
	     
	      var tenantType = $('#jfedittenantType').val();   //租户分类
	      
	      var resourceTime = $('#jfeditresourceTime').val(); //资源 
	     
	      var uniplatform4aTime = $('#jfedituniplatform4aTime').val();  //统一及4A
	      
	      var havedateTime = $('#jfedithavedateTime').val(); //数据实际具备
	      
	      var resideDuration = $('#jfeditresideDuration').val();  //入住时长
	      
	      var monthlyFee = $('#jfeditmonthlyFee').val(); //月租费用（元/月）
	      
	      var dataFee = $('#jfeditdataFee').val();  //数据报价/万元
	      
	      var beginRentDate = $('#jfeditbeginRentDate').val();   //起租日期
	      
	      var tasteDuration = $('#jfedittasteDuration').val();  //试用期
	      
	      var chargeBeginDate = $('#jfeditchargeBeginDate').val();//计费日期
	      
	      var chargeEndDate = $('#jfeditchargeEndDate').val();//到期日期
	      
	      
	      var introduceUnicom = $('#jfeditintroduceUnicom').val();  //联通引入方
	      
	      var introduceTenant = $('#jfeditintroduceTenant').val();  //引入方联系人（租户管理员）
	      
	      var contact = $('#jfeditcontact').val();  //联系方式
	      
	      var askDate = $('#jfeditaskDate').val(); //申请日期
	      
	      var signContract = $('#jfeditsignContract').val(); //是否签署合同 
	      
	      var monthlyFeeRemark = $('#jfeditmonthlyFeeRemark').val(); //月租备注

	      var endRentDate = $('#jfeditendRentDate').val();  //退租时间
	      
	      var remark = $('#jfeditremark').val(); //备注
	      
	      var msg = "真的要修改吗？\n\n请确认！";
		   
		  if (confirm(msg)==true){
			  $.post("saveModifyData",
					 {tcId:tcId,
	    	  	  		tenantName:tenantName, 		serviceType:serviceType, 
	    	  	  		tenantType:tenantType, 		resourceTime:resourceTime, 
	    	  	  		uniplatform4aTime: uniplatform4aTime, 		havedateTime:havedateTime, 
	    	  	  		resideDuration:resideDuration, 		monthlyFee:monthlyFee,
	    	  	  		dataFee:dataFee , 			beginRentDate:beginRentDate,
	    	  	  		tasteDuration:tasteDuration ,		chargeBeginDate:chargeBeginDate ,chargeEndDate:chargeEndDate,
	    	  	  		introduceUnicom:introduceUnicom ,		introduceTenant:introduceTenant ,
	    	  	  		contact:contact ,			askDate:askDate ,
	    	  	  		signContract:signContract ,		monthlyFeeRemark:monthlyFeeRemark,
	    	  	  		endRentDate: endRentDate,		remark: remark ,
					 },
					 function(data){
						 alert(data)
						 $('#jfeditmodel').modal('hide');
						 loaddata();
					 })
		  }
   })
   
   
   /*	显示新增页面		*/
   $('#addjfbutton').on( 'click', function () {
	   $('#jfaddmodel').modal('show');
   });
   
   /*     新增		*/

   $('#jfeditsubmit1').on("click",function(){
	   
	   /* 获取数据*/
	   	  var tenantName = $('#jfedittenantName1').val();  //租户
	      
	      var serviceType = $('#jfeditserviceType1').val();//服务类型
	     
	      var tenantType = $('#jfedittenantType1').val();   //租户分类
	      
	      var resourceTime = $('#jfeditresourceTime1').val(); //资源 
	     
	      var uniplatform4aTime = $('#jfedituniplatform4aTime1').val();  //统一及4A
	      
	      var havedateTime = $('#jfedithavedateTime1').val(); //数据实际具备
	      
	      var resideDuration = $('#jfeditresideDuration1').val();  //入住时长
	      
	      var monthlyFee = $('#jfeditmonthlyFee1').val(); //月租费用（元/月）
	      
	      var dataFee = $('#jfeditdataFee1').val();  //数据报价/万元
	      
	      var beginRentDate = $('#jfeditbeginRentDate1').val();   //起租日期
	      
	      var tasteDuration = $('#jfedittasteDuration1').val();  //试用期
	      
	      var chargeBeginDate = $('#jfeditchargeBeginDate1').val();//计费日期
	      
	      var chargeEndDate = $('#jfeditchargeEndDate1').val();//到期日期
	      
	      
	      var introduceUnicom = $('#jfeditintroduceUnicom1').val();  //联通引入方
	      
	      var introduceTenant = $('#jfeditintroduceTenant1').val();  //引入方联系人（租户管理员）
	      
	      var contact = $('#jfeditcontact1').val();  //联系方式
	      
	      var askDate = $('#jfeditaskDate1').val(); //申请日期
	      
	      var signContract = $('#jfeditsignContract1').val(); //是否签署合同 
	      
	      var monthlyFeeRemark = $('#jfeditmonthlyFeeRemark1').val(); //月租备注

	      var endRentDate = $('#jfeditendRentDate1').val();  //退租时间
	      
	      var remark = $('#jfeditremark1').val(); //备注
	      
	      var msg = "确认信息添加？\n\n请确认！";
		   
		  if (confirm(msg)==true){
			  $.post("saveNewData",
					 {
	    	  	  		tenantName:tenantName, 		serviceType:serviceType, 
	    	  	  		tenantType:tenantType, 		resourceTime:resourceTime, 
	    	  	  		uniplatform4aTime: uniplatform4aTime, 		havedateTime:havedateTime, 
	    	  	  		resideDuration:resideDuration, 		monthlyFee:monthlyFee,
	    	  	  		dataFee:dataFee , 			beginRentDate:beginRentDate,
	    	  	  		tasteDuration:tasteDuration ,		chargeBeginDate:chargeBeginDate ,chargeEndDate:chargeEndDate,
	    	  	  		introduceUnicom:introduceUnicom ,		introduceTenant:introduceTenant ,
	    	  	  		contact:contact ,			askDate:askDate ,
	    	  	  		signContract:signContract ,		monthlyFeeRemark:monthlyFeeRemark,
	    	  	  		endRentDate: endRentDate,		remark: remark ,
					 },
					 function(data){
						 alert(data)
						 $('#jfaddmodel').modal('hide');
						 loaddata();
					 })
		  }
   })
   
   
   /*   删除   */
   $('#charging_table tbody').on( 'click', 'button.jfdelete', function () {
	   var jstcId = $(this).val();//获得tcId 主码
	   /* 删除前的提示 */
	   var msg = "真的要删除吗？\n\n请确认！";
	   
	    if (confirm(msg)==true){
	    	$.post( "deleteById",
	  		  	  { tcId:jstcId,},
	  	  			function(data){
	  		  		  alert(data)
	  		  		  loaddata();
	  	  		    }
	  		  	  )
	    
	    }else{
	        return false;
	    }
	   
	});
   /*	显示上传页面		*/
   $('#uploadjfbutton').on( 'click', function () {
	   $('#jfuploadmodel').modal('show');
   });
   
   /*  上传			*/
   
   function jfdoUpload() {  
	     var formData = new FormData($( "#jfuploadForm" )[0]);  
	     $.ajax({  
	          url: "/save" ,  
	          type: 'POST',  
	          data: formData,  
	            
	          cache: false,  
	          contentType: false,  
	          processData: false,  
	          success: function (returndata) {  
	              alert(returndata);  
	              $('#jfuploadmodel').modal('hide');
	              loaddata();
	          }
	           
	     });  
	}  
   
