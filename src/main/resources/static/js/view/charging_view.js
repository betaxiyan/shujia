$(document).ready(function(){
/*    $('#view_tabs a').click(function(e) {
            e.preventDefault()
            $(this).tab('show')
        });
*/
	var dataSet = [
	               ['a','b','c','d','e','f','Internet Explorer 4.0','Win 95+','4'],
	               ['a1','b1','c','d','e','f','Internet Explorer 4.0','Win 95+','4'],
	               ['a2','b2','c','d','e','f','Internet Explorer 4.0','Win 95+','4'],
	               ['a3','b3','c','d','e','f','Internet Explorer 4.0','Win 95+','4'],
	               ['a4','b4','c','d','e','f','Internet Explorer 4.0','Win 95+','4'],
	               ['a5','b5','c','d','e','f','Internet Explorer 4.0','Win 95+','4'],
	               ['a','b','c','d','e','f','Internet Explorer 4.0','Win 95+','4'],
	               ['a','b','c','d','e','f','Internet Explorer 4.0','Win 95+','4'],
	               ['a','b','c','d','e','f','Internet Explorer 4.0','Win 95+','4'],
	               ['a','b','c','d','e','f','Internet Explorer 4.0','Win 95+','4'],
	               ['a','b','c','d','e','f','Internet Explorer 4.0','Win 95+','4'],
	               ['a','b','c','d','e','f','Internet Explorer 4.0','Win 95+','4'],
	               ['a','b','c','d','e','f','Internet Explorer 4.0','Win 95+','4'],
	               ['a','b','c','d','e','f','Internet Explorer 4.0','Win 95+','4'],

	           ];
	
	
   $('#charging_table').dataTable({
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
        "bAutoWidth": false,
        
        "data": dataSet,//数据源
        "aoColumnDefs":[//设置列的属性，此处设置第一列不排序
                        {"bSortable": true, "aTargets": [0]},
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
   $('#detail_table2 tbody').on( 'click', 'a#edit', function () {
       var data = $('#detail_table2').DataTable().row($(this).parents('tr')).data();
       alert("查看修改："+data[0] +","+ data[1] );
   } );

   /**
    * 删除
    */
   $('#detail_table2 tbody').on( 'click', 'a#del', function () {
       var data = $('#detail_table2').DataTable().row($(this).parents('tr')).data();
       alert("删除："+data );
   }) 
})