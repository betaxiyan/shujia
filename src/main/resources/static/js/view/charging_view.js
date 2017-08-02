$(document).ready(function(){
/*    $('#view_tabs a').click(function(e) {
            e.preventDefault()
            $(this).tab('show')
        });
*/
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
        "bAutoWidth": false
    });
})