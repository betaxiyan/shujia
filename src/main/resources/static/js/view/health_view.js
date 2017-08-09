
$(document).ready(function() {
    $("#test_health").click(restfulHealth);
    $("#update").click(updateTable);
});

function restfulHealth(){

		   $.ajax({
		       type : "GET",
		       url : "./restfulHealth",
		       dataType:"json",
		       contentType: "application/x-www-form-urlencoded; charset=utf-8",
		       success : function(data) {
		    	   	var data = data.data;
		    	   	console.log(data);
		            var length = data.length;
		            //每次测试都要刷新表中数据
		            $('#detail_health tbody').html("");
		            for(var i=0;i<length;i++)
		                {
		                  $('#detail_health tbody').append("<tr>"+
		                        "<td>"+data[i].restfulName+"</td>" +
		                        "<td>"+data[i].restfulURL+"</td>" +
		                        "<td>"+data[i].status+"</td>" +
		                        "<td>"+data[i].checkDate+"</td>" +
		                                "</tr>");
		                }

		       }
		   }); 
		   
   }
$('#detail_health').dataTable({
	
    "language": {
        "paginate": {
            "previous": "首页",
            "next": "下一页"
        },
        "info": "显示_START_到_END_, 共计_TOTAL_条数据",
        "emptyTable": "无记录",
        "infoEmpty": "共计0",
    },
   //  分页设置 
    "bPaginate": true,
    "bLengthChange": false,
   //  搜索设置 
    "bFilter": true,
    "bSort": false,
   //  显示总条数 
    "bInfo": true,
    "bAutoWidth": false,
    
    "ajax":{ url:"./restfulHealth",},
    		
    "columns": [ 
                   //mData 表示发请求时候本列的列明，返回的数据中相同下标名字的数据会填充到这一列
                   {"mData": 'restfulName'},
                   {"mData": 'restfulURL'},
                   {"mData": 'status'},
                   {"mData": 'checkDate'},

               ],
});

$('#upTableData').dataTable({
	
    "language": {
        "paginate": {
            "previous": "首页",
            "next": "下一页"
        },
        "info": "显示_START_到_END_, 共计_TOTAL_条数据",
        "emptyTable": "无记录",
        "infoEmpty": "共计0",
    },
   //  分页设置 
    "bPaginate": true,
    "bLengthChange": false,
   //  搜索设置 
    "bFilter": true,
    "bSort": false,
   //  显示总条数 
    "bInfo": true,
    "bAutoWidth": false,
});

function updateTable(){

	$('.updateresult').text("更新中…");
	//刷新Ttl数据
	   $.ajax({
	       type : "GET",
	       url : "./getMidDateToTtl",
	       dataType:"json",
	       contentType: "application/x-www-form-urlencoded; charset=utf-8",
	       success : function(data) {
	    	   	var data = data.data;
	    	   	console.log(data);
	            var length = data.length;
	            //每次刷新表中数据,注意给更新结果那一列加上class属性
	            $('#Ttl').html("");
	            for(var i=0;i<length;i++)
	                {
	                  $('#Ttl').append(
	                        "<td>"+data[i].tableName+"</td>" +
	                        "<td>"+data[i].updateDate+"</td>" +
	                        "<td class=\"updateresult\">"+data[i].result+"</td>");
	                }
	       }
	   }); 
	   
	   //刷新Ttd数据
	   $.ajax({
	       type : "GET",
	       url : "./getMidDateToTtd",
	       dataType:"json",
	       contentType: "application/x-www-form-urlencoded; charset=utf-8",
	       success : function(data) {
	    	   	var data = data.data;
	    	   	console.log(data);
	            var length = data.length;
	            //每次刷新表中数据,注意给更新结果那一列加上class属性
	            $('#Ttd').html("");
	            for(var i=0;i<length;i++)
	                {
	                  $('#Ttd').append(
	                        "<td>"+data[i].tableName+"</td>" +
	                        "<td>"+data[i].updateDate+"</td>" +
	                        "<td class=\"updateresult\">"+data[i].result+"</td>");
	                }

	       }
	   }); 
	   
}

