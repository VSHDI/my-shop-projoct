var App = function () {

    //iCheck
    var _masterCheckbox;
    var _checkbox;
    //数组
    var _idArray;
    /**
     * 私有方法，初始化 ICheck
     */
    var handlerInitCheckbox = function () {
        // 激活
        $('input[type="checkbox"].minimal, input[type="radio"].minimal').iCheck({
            checkboxClass: 'icheckbox_minimal-blue',
            radioClass   : 'iradio_minimal-blue'
        });

        // 获取控制端 Checkbox
        _masterCheckbox = $('input[type="checkbox"].minimal.icheck_master');

        // 获取全部 Checkbox 集合
        _checkbox = $('input[type="checkbox"].minimal');
    };

    /**
     * Checkbox 全选功能
     */
    var handlerCheckboxAll = function () {
        _masterCheckbox.on("ifClicked", function (e) {
            // 返回 true 表示未选中
            if (e.target.checked) {
                _checkbox.iCheck("uncheck");
            }

            // 选中状态
            else {
                _checkbox.iCheck("check");
            }
        });
    };

    /**
     * 批量删除
     */
    var handlerDeleteMulti =  function (url) {
        _idArray = new Array();
        // 将选中元素的 ID 放入数组中
        _checkbox.each(function () {
            var _id =$(this).attr("id");
            if (_id != null && _id != "undefine" && $(this).is(":checked")) {
                _idArray.push(_id);
            }
        });
        if (_idArray.length === 0) {
            $("#modal-message").html("至少选泽一项啊");
        }
        else {
            $("#modal-message").html("你确定要删除吗");
        }
        $("#modal-default").modal("show");

        $("#btnModalOk").bind("click",function () {
            del();
        });

        /**
         * 当前私有函数的私有函数
         */
        function del() {
            $("#modal-default").modal("hide");
            //没有选择则关闭模态框
            if (_idArray.length === 0) {
            }
            //删除数据
            else {
                setTimeout(function () {
                    $.ajax({
                        "url":url,
                        "type":"POST",
                        "data":{"ids":_idArray.toString()},
                        "dataType": "JSON",
                        "success": function (data) {
                            if (data.status === 200) {
                                window.location.reload();
                            }
                            else {
                                $("#btnModalOk").unbind("click");
                                $("#btnModalOk").bind("click",function () {
                                    $("#modal-default").modal("hide");
                                })
                                $("#modal-message").html(data.message);
                                $("#modal-default").modal("show");
                            }
                        }
                    });
                },500)
            }
        }
    };

    return {
        init: function () {
            handlerInitCheckbox();
            handlerCheckboxAll();
        },
        getCheckbox: function () {
            return _checkbox;
        },
        deleteMedium: function(url) {
            handlerDeleteMulti(url);
        }
    }
}();

$(document).ready(function () {
    App.init();
})