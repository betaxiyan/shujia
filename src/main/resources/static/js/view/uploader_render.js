var renderUser = (function() {
    var connectFlag = false;

    function selectChange() {
        $('select[name="savePath"]').change(function() {
            $('.resetOper').hide();
            $('.connectBtn').hide();
            $('input[name="username"]').val('');
            $('input[name="password"]').val('');
            $('.operateBtn').show();
            connectFlag = false;
            chooseFileBtn();

        })
        $('input[name="password"]').keyup(function() {
            $('.connectBtn').show();
        })
        connectOper();
    }

    function connectOper() {
        $('.connectBtn').unbind('click', ajaxOper)
        $('.connectBtn').bind('click', ajaxOper)
    }

    function ajaxOper() {
        if (!$('input[name="username"]').val()) {
            console.log($('input[name="username"]').val())
            $('#alertModal .modal-body').html('请输入正确信息');
            $('#alertModal').modal('show');
        } else {
            var username = $('input[name="username"]').val();
            var password = $('input[name="password"]').val();
            var userInfo = {
                username: username,
                password: password
            }
            $.ajax({
                url: '/getBoolean',
                type: 'POST',
                data: userInfo,
                success: function(data) {
                    if (data.result == 1) {
                        $('.operateBtn').hide();
                        $('.resetOper').show();
                        $('.connectSuccess').show();
                        $('.resetInfo').show();
                        connectFlag = true;
                        chooseFileBtn();
                        resetOper();
                    } else if (data.result == 0) {
                        $('.operateBtn').hide();
                        $('.resetOper').show();
                        $('.connectFail').show();
                        $('.resetInfo').show();
                        connectFlag = false;
                        chooseFileBtn();
                        resetOper();
                    }
                },
                error: function() {
                    $('.connectBtn').hide();
                    $('#alertModal .modal-body').html('请求错误');
                    $('#alertModal').modal('show');
                }
            })
        }
        $('input[name="username"]').val('');
        $('input[name="password"]').val('');
    }

    function resetOper() {
        $('.resetInfo').click(function() {
            $('.connectSuccess').hide();
            $('.connectFail').hide();
            $('.resetInfo').hide();
            $('.connectBtn').hide();
            $('.operateBtn').show();
            connectFlag = false;
            chooseFileBtn();
            connectOper();
        })
    }

    function chooseFileBtn() {
        if (connectFlag) {
            if ($('#chooseButton')) {
                $('#chooseButton').removeProp('disabled', true);
                if ('#ssi-uploadBtn') {
                    $('#ssi-uploadBtn').removeProp('disabled', true);
                }
            }
        } else {
            if ($('#chooseButton')) {
                $('#chooseButton').prop('disabled', true);
                if ('#ssi-uploadBtn') {
                    $('#ssi-uploadBtn').prop('disabled', true);
                }

            }
        }
    }
    return {
        init: function() {
            $('#ssi-upload').ssi_uploader({
                url: '#',
                data: {},
                allowed: ['jpg', 'gif', 'txt', 'png', 'pdf', 'js', 'css', 'docx', 'bat', 'rar'],
                onEachUpload: function(fileInfo) {
                    // console.log(fileInfo);
                    var indexNum = fileInfo.indexNum;
                    if (fileInfo.uploadStatus == 'success') {
                        $('.ssi-imgToUploadTable').eq(indexNum).append('<tr class="scanBg"><td><div id="bg-cover"><div class="bg_icon"></div><span>安全扫描...</span></div></td></tr>')
                        $('.ssi-imgToUploadTable')
                        clearInterval(timer);
                        var timer = setInterval(function() {
                            $.ajax({
                                url: '/safeInfo',
                                type: 'GET',
                                success: function(data) {

                                    if (data.result == 'success') {
                                        clearInterval(timer);
                                        $('.ssi-imgToUploadTable').eq(indexNum).find('.scanBg').remove();
                                        $('.ssi-imgToUploadTable').eq(indexNum).append('<tr><td><div id="safe-cover"><div class="safe_icon"></div><span>文件安全</span></div></td></tr>')
                                    } else if (data.result == 'fail') {
                                        clearInterval(timer);
                                        $('.ssi-imgToUploadTable').eq(indexNum).find('.scanBg').remove();
                                        $('.ssi-imgToUploadTable').eq(indexNum).append('<tr><td><div id="unsafe-cover"><div class="unsafe_icon"></div><span>文件不安全</span></div></td></tr>')
                                    }
                                }
                            })
                        }, 5000);

                    }

                }
            });
            selectChange();
            chooseFileBtn();
            $('#closeMyModal').click(function() {
                window.location.reload();
                $('#myModal').modal('hide');
            })

        }
    }
})()
renderUser.init();