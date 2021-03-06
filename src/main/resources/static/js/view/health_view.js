$(document).ready(function() {
	$("#test_health").click(restfulHealth);
	$("#update").click(updateTable);
	$("#export").click(exportTable)
});

function restfulHealth() {

	$.ajax({
		type : "GET",
		url : ctx + "restfulHealth",
		dataType : "json",
		contentType : "application/x-www-form-urlencoded; charset=utf-8",
		success : function(data) {
			var data = data.data;
			console.log(data);
			var length = data.length;
			// 每次测试都要刷新表中数据
			$('#detail_health tbody').html("");
			for (var i = 0; i < length; i++) {
				$('#detail_health tbody').append(
						"<tr>" + "<td>" + data[i].restfulName + "</td>"
								+ "<td>" + data[i].restfulURL + "</td>"
								+ "<td>" + data[i].status + "</td>" + "<td>"
								+ data[i].checkDate + "</td>" + "</tr>");
			}

		}
	});

}
$('#detail_health').dataTable({

	"language" : {
		"paginate" : {
			"sFirst": "首页",
            "sPrevious": "前一页",
            "sNext": "后一页",
            "sLast": "尾页"
		},
		"info" : "显示_START_到_END_, 共计_TOTAL_条数据",
		"emptyTable" : "无记录",
		"infoEmpty" : "共计0",
		"sSearch" : "查询：",
		"sZeroRecords" : "查询不到任何相关数据",
	},
	// 分页设置
	"bPaginate" : true,
	"bLengthChange" : false,
	// 搜索设置
	"bFilter" : true,
	"bSort" : false,
	// 显示总条数
	"bInfo" : true,
	"bAutoWidth" : false,
	"bSearching" : false,
	"ajax" : {
		url : ctx + "restfulHealth",
	},
	"columns" : [
	// mData 表示发请求时候本列的列明，返回的数据中相同下标名字的数据会填充到这一列
	{
		"mData" : 'restfulName'
	}, {
		"mData" : 'restfulURL'
	}, {
		"mData" : 'status'
	}, {
		"mData" : 'checkDate'
	},

	],
});

$('#upTableData').dataTable({

	"language" : {
		"paginate" : {
			"sFirst": "首页",
            "sPrevious": "前一页",
            "sNext": "后一页",
            "sLast": "尾页"
		},
		"info" : "显示_START_到_END_, 共计_TOTAL_条数据",
		"emptyTable" : "无记录",
		"infoEmpty" : "共计0",
		"sSearch" : "查询：",
		"sZeroRecords" : "查询不到任何相关数据",
		"lengthMenu": "每页显示 _MENU_ 记录",
		"processing": "加载中......",
	},
	// 分页设置
	"bPaginate" : true,
	"bLengthChange" : false,
	//"aLengthMenu":[[1,2,3,5],[1,2,3,5]],
	// 搜索设置,无法指定数据源
	"bFilter" : false,
	"bSort" : false,
	// 显示总条数
	"bInfo" : true,
	"bAutoWidth" : false,
	"destroy" : true,

	/*"ajax" : {
		url : ctx +,
	},*/
	"columns" : [
	{
		"mData" : 'tableName'
	}, {
		"mData" : 'updateDate'
	}, {
		"mData" : 'result'
	},
	],
});

function updateTable() {

	$('.updateresult').text("更新中…");
	// 刷新Ttl数据
	$.ajax({
		type : "GET",
		url : ctx + "getMidDateToTtl",
		dataType : "json",
		contentType : "application/x-www-form-urlencoded; charset=utf-8",
		success : function(data) {
			var data = data.data;
			console.log(data);
			var length = data.length;
			// 每次刷新表中数据,注意给更新结果那一列加上class属性
			$('#Ttl').html("");
			for (var i = 0; i < length; i++) {
				$('#Ttl').append(
						"<td>" + data[i].tableName + "</td>" + "<td>"
								+ data[i].updateDate + "</td>"
								+ "<td class=\"updateresult\">"
								+ data[i].result + "</td>");
			}
		}
	});

	// 刷新Ttd数据
	$.ajax({
		type : "GET",
		url : ctx + "getMidDateToTtd",
		dataType : "json",
		contentType : "application/x-www-form-urlencoded; charset=utf-8",
		success : function(data) {
			var data = data.data;
			console.log(data);
			var length = data.length;
			// 每次刷新表中数据,注意给更新结果那一列加上class属性
			$('#Ttd').html("");
			for (var i = 0; i < length; i++) {
				$('#Ttd').append(
						"<td>" + data[i].tableName + "</td>" + "<td>"
								+ data[i].updateDate + "</td>"
								+ "<td class=\"updateresult\">"
								+ data[i].result + "</td>");
			}

		}
	});
	
}

//全量导出表格初始化
$('#totalExport').dataTable({

	"language" : {
		"paginate" : {
			"sFirst": "首页",
            "sPrevious": "前一页",
            "sNext": "后一页",
            "sLast": "尾页"
		},
		"info" : "显示_START_到_END_, 共计_TOTAL_条数据",
		"emptyTable" : "无记录",
		"infoEmpty" : "共计0",
		"sSearch" : "查询：",
		"sZeroRecords" : "查询不到任何相关数据",
		"lengthMenu": "每页显示 _MENU_ 记录",
		"processing": "加载中......",
	},
	// 分页设置
	"bPaginate" : true,
	"bLengthChange" : false,
	//"aLengthMenu":[[1,2,3,5],[1,2,3,5]],
	// 搜索设置,无法指定数据源
	"bFilter" : false,
	"bSort" : false,
	// 显示总条数
	"bInfo" : true,
	"bAutoWidth" : false,
	"destroy" : true,

	/*"ajax" : {
		url : ctx +,
	},*/
	"columns" : [
	{
		"mData" : 'tableName'
	}, {
		"mData" : 'updateDate'
	}, {
		"mData" : 'result'
	},
	],
});

//全量导出
function exportTable() {

	$('.exportresult').text("导出中…");
	location.href=ctx+'getExcel';
	var url = location.href;
	console.log(url);
	var result = new Boolean(true);
	$.ajax({
		type : "GET",
		url : ctx + "isExport",
		dataType : "json",
		data :{"results":result},
		contentType : "application/x-www-form-urlencoded; charset=utf-8",
		success : function(data) {
			var data = data.data;
			console.log(data);
			var length = data.length;
			// 每次刷新表中数据,注意给更新结果那一列加上class属性
			$('#tdOne').html("");
			$('#tdTwo').html("");
			$('#tdThree').html("");
			for (var i = 0; i < length; i++) {
				$('#tdOne').append(
						"<td>" + "已划配租户情况" + "</td>" + "<td>"
								+ data[i].updateDate + "</td>"
								+ "<td class=\"exportresult\">"
								+ data[i].result + "</td>");
				$('#tdTwo').append(
						"<td>" + "租户计费情况" + "</td>" + "<td>"
								+ data[i].updateDate + "</td>"
								+ "<td class=\"exportresult\">"
								+ data[i].result + "</td>");
				$('#tdThree').append(
						"<td>" + "已退租户情况" + "</td>" + "<td>"
								+ data[i].updateDate + "</td>"
								+ "<td class=\"exportresult\">"
								+ data[i].result + "</td>");
			}
		}
	});	
}

