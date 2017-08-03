$(document).ready(function(){
/*    $('#view_tabs a').click(function(e) {
            e.preventDefault()
            $(this).tab('show')
        });
*/
	
	
	
   $('#charging_table').dataTable({
        "language": {
            "paginate": {
                "previous": "上一页",
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
        "bAutoWidth": false,
        /*	数据源Ajax*/
        "ajax":{ url:"findAll",},
    	"columns": [{"mData": "tenantName"},		{"mData": "serviceType"},			{"mData": "tenantType"}, 
    	            {"mData": "resourceTime"},		{"mData": "uniplatform4aTime"},		{"mData": "havedateTime"},
    	            {"mData": "resideDuration"},	{"mData": "monthlyFee"},			{"mData": "dataFee"}, 
    	            {"mData": "beginRentDate"},		{"mData": "tasteDuration"},			{"mData": "chargeBeginDate"},
    	            {"mData": "chargeEndDate"},		{"mData": "introduceUnicom"},		{"mData": "introduceTenant"}, 
    	            {"mData": "contact"},			{"mData": "askDate"},				{"mData": "signContract"},
    	            {"mData": "monthlyFeeRemark"},	{"mData": "endRentDate"},			{"mData": "remark"},null
    	            ],
        "aoColumnDefs":[//设置列的属性，此处设置第一列不排序
                        {"bSortable": false, "aTargets": [0]},
                        {
                        	"targets":-1,
                            "data": null,
                            "bSortable": false,
                            "defaultContent": "<p>&nbsp;&nbsp;&nbsp;&nbsp;<a id=\"edit\" href=\"#\">修改</a>&nbsp;&nbsp;&nbsp;&nbsp;<a id=\"del\"  href=\"#\">删除</a></p>"
                        } 
                        
                    ],
        
    });
   /**
    * 查看修改
    */
   $('#charging_table tbody').on( 'click', 'a#edit', function () {
       var data = $('#charging_table').DataTable().row($(this).parents('tr')).data();
       alert("查看修改："+data.tenantName +","+ data.id );
   } );

   /**
    * 删除
    */
   $('#charging_table tbody').on( 'click', 'a#del', function () {
       var data = $('#charging_table').DataTable().row($(this).parents('tr')).data();
       alert("删除："+data );
   }) 
})