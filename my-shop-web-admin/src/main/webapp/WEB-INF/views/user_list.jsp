<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sys" tagdir="/WEB-INF/tags/sys" %>
<!DOCTYPE html>
<html>
<head>
    <title>我的商城 | 用户面板</title>
    <jsp:include page="../includes/header.jsp" />
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
    <jsp:include page="../includes/nav.jsp" />
    <jsp:include page="../includes/menu.jsp" />

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>
                用户管理面板
                <small></small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="/main"><i class="fa fa-dashboard"></i> 首页</a></li>
                <li class="active">用户管理面板</li>
            </ol>
        </section>

        <!-- Main content -->
        <section class="content">
            <!-- /.row -->
            <div class="row">
                <div class="col-12">
                    <%--提示语--%>
                    <c:if test="${baseResult != null}">
                        <div class="alert alert-${baseResult.status == 200 ? "success" : "danger"} alert-dismissible">
                            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                                ${baseResult.message}
                        </div>
                    </c:if>

                        <div class="box box-info box-info-search" style="display: none;">
                            <div class="box-header">
                                <span>sousuo</span>
                            </div>
                            <div class="box-body">
                                <div class="row form-horizontal">
                                    <div class="col-xs-12 col-sm-3">
                                        <div class="form-group">
                                            <label for="username" class="col-sm-4 control-label">姓名</label>

                                            <div class="col-sm-8">
                                                <input id="username" class="form-control" placeholder="姓名" />
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-xs-12 col-sm-3">
                                        <div class="form-group">
                                            <label for="email" class="col-sm-4 control-label">邮箱</label>

                                            <div class="col-sm-8">
                                                <input id="email" class="form-control" placeholder="邮箱" />
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-xs-12 col-sm-3">
                                        <div class="form-group">
                                            <label for="phone" class="col-sm-4 control-label">手机</label>

                                            <div class="col-sm-8">
                                                <input id="phone" class="form-control" placeholder="手机" />
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="box-footer">
                                <button type="button" class="btn btn-info pull-right" onclick="search();">搜索</button>
                            </div>
                        </div>


                    <div class="box">
                        <div class="box-header">
                            <h3 class="box-title">用户列表</h3>
                        </div>
                        <div class="box-body">
                            <div class="row" style="padding-left: 10px;padding-top: 10px">
                                <a href="#" type="button" class="btn btn-default btn-xs"><i class="fa fa-fw fa-medkit"></i>新建</a>
                                <button type="button" class="btn btn-default btn-xs" onclick="App.deleteMedium('/user/delete')"><i class="fa fa-fw fa-heartbeat"></i>删除</button>
                                <a href="#" type="button" class="btn btn-default btn-xs"><i class="fa fa-fw fa-heart"></i>导入</a>
                                <a href="#" type="button" class="btn btn-default btn-xs"><i class="fa fa-fw fa-heart-o"></i>导出</a>
                                <button type="submit" class="btn btn-sm btn-primary" onclick="$('.box-info-search').css('display') == 'none' ? $('.box-info-search').show('fast') : $('.box-info-search').hide('fast')"><i class="fa fa-search"></i> 搜索</button>
                            </div>
                        </div>
                        <div class="box-body table-responsive ">

                            <table id="dataTable" class="table table-hover">
                                <thead>
                                <tr>
                                    <th><input type="checkbox" class="minimal icheck_master"/></th>
                                    <th>ID</th>
                                    <th>用户名</th>
                                    <th>手机号</th>
                                    <th>邮箱</th>
                                    <th>更新时间</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${tbUsers}" var="tbUser">
                                    <tr>
<%--                                        <td><input id="${tbUser.id}" type="checkbox" class="minimal " /></td>--%>
                                        <td>${tbUser.id}</td>
                                        <td>${tbUser.username}</td>
                                        <td>${tbUser.phone}</td>
                                        <td>${tbUser.email}</td>
                                        <td><fmt:formatDate value="${tbUser.updated}" pattern="yyyy-MM-dd HH:mm:ss"/> </td>
<%--                                        <td>--%>
<%--                                            <a href="https://www.baidu.com" type="button" class="btn btn-success btn-sm"><icon class="fa fa-fw fa-tree"></icon>查看</a>--%>
<%--                                            <a type="button" class="btn btn-primary btn-sm"><i class="fa fa-fw fa-venus-mars"></i>编辑</a>--%>
<%--                                            <a type="button" class="btn btn-danger btn-sm"><i class="fa fa-fw fa-trash-o"></i>删除</a>--%>
<%--                                        </td>--%>
                                    </tr>
                                </c:forEach>

                                </tbody>
                            </table>
                        </div>
                    </div>
                    <!-- /.box -->
                </div>
            </div>
            <!-- /.row -->
        </section>
    </div>

    <jsp:include page="../includes/copyright.jsp" />
</div>
<jsp:include page="../includes/footer.jsp" />
<sys:modal/>
<script>
    var _dataTable;
    $(function () {
        const _columns =
        [
            {
                "data":function (row,type,val,meta) {
                    return '<input id="'+row.id+'" type="checkbox" class="minimal " />';
                }
            },
            {"data":"id"},
            {"data":"username"},
            {"data":"phone"},
            {"data":"email"},
            {"data":"updated"},
            {
                "data":function (row,type,val,meta) {
                    const detailUrl = "/user/detail?id="+row.id;
                    return '<button type="button" class="btn btn-success btn-sm" onclick="App.showDetail(\'' + detailUrl + '\')"><icon class="fa fa-fw fa-tree"></icon>查看</button>'+
                        '<a href="/user/form?id='+row.id+'" type="button" class="btn btn-primary btn-sm"><i class="fa fa-fw fa-venus-mars"></i>编辑</a>'+
                        '<a type="button" class="btn btn-danger btn-sm"><i class="fa fa-fw fa-trash-o"></i>删除</a>';
                }
            }
        ]
        _dataTable = App.initDataTables("/user/page",_columns);
    });
    function search() {
        var username = $("#username").val();
        var phone = $("#phone").val();
        var email = $("#email").val();

        var param = {
            "username": username,
            "phone": phone,
            "email": email
        };

        _dataTable.settings()[0].ajax.data = param;
        _dataTable.ajax.reload();
    }
</script>
</body>
</html>