<%--
  Created by IntelliJ IDEA.
  User: YH
  Date: 2021/7/9
  Time: 11:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>layui</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/layui/lib/layui-v2.6.3/css/layui.css"
          media="all">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/layui/css/public.css" media="all">

    <%--    下面是添加dtree的插件--%>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/layui_ext/dtree/dtree.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/layui_ext/dtree/font/dtreefont.css">


</head>
<body>
<div class="layuimini-container">
    <div class="layuimini-main">

        <!-- 搜索条件 -->
        <fieldset class="table-search-fieldset">
            <legend>搜索信息</legend>
            <div style="margin: 10px 10px 10px 10px">
                <form class="layui-form layui-form-pane" action="">
                    <div class="layui-form-item">
                        <div class="layui-inline">
                            <label class="layui-form-label">角色名称</label>
                            <div class="layui-input-inline">
                                <input type="text" name="roleName" autocomplete="off" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-inline">
                            <button type="submit" class="layui-btn" lay-submit lay-filter="data-search-btn"><i
                                    class="layui-icon layui-icon-search"></i>搜索
                            </button>
                            <button type="reset" class="layui-btn layui-btn-warm"><i
                                    class="layui-icon layui-icon-refresh-1"></i>重置
                            </button>
                        </div>
                    </div>
                </form>
            </div>
        </fieldset>

        <!-- 表格工具栏 -->
        <script type="text/html" id="toolbarDemo">
            <div class="layui-btn-container">
                <button class="layui-btn layui-btn-normal layui-btn-sm data-add-btn" lay-event="add"><i
                        class="layui-icon layui-icon-add-1"></i>添加
                </button>
            </div>
        </script>

        <!-- 数据表格 -->
        <table class="layui-hide" id="currentTableId" lay-filter="currentTableFilter"></table>

        <!-- 行工具栏 -->
        <script type="text/html" id="currentTableBar">
            <a class="layui-btn layui-btn-xs data-count-edit" lay-event="edit"><i
                    class="layui-icon layui-icon-edit"></i>编辑</a>
            <a class="layui-btn layui-btn-xs layui-btn-danger data-count-delete" lay-event="delete"><i
                    class="layui-icon layui-icon-close"></i>删除</a>
            <a class="layui-btn layui-btn-xs data-count-edit" lay-event="grantMenu"><i
                    class="layui-icon layui-icon-edit"></i>分配菜单</a>
        </script>

        <!-- 添加和修改窗口 -->
        <div style="display: none;padding: 5px" id="addOrUpdateWindow">
            <form class="layui-form" style="width:90%;" id="dataFrm" lay-filter="dataFrm">
                <div class="layui-form-item">
                    <label class="layui-form-label">角色名称</label>
                    <div class="layui-input-block">
                        <!-- 角色编号 -->
                        <input type="hidden" name="id">
                        <input type="text" name="roleName" lay-verify="required" autocomplete="off"
                               placeholder="请输入角色名称" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">角色描述</label>
                    <div class="layui-input-block">
                        <textarea class="layui-textarea" name="roleDesc" id="roleDesc"></textarea>
                    </div>
                </div>
                <div class="layui-form-item layui-row layui-col-xs12">
                    <div class="layui-input-block" style="text-align: center;">
                        <button type="button" class="layui-btn" lay-submit lay-filter="doSubmit"><span
                                class="layui-icon layui-icon-add-1"></span>提交
                        </button>
                        <button type="reset" class="layui-btn layui-btn-warm"><span
                                class="layui-icon layui-icon-refresh-1"></span>重置
                        </button>
                    </div>
                </div>
            </form>
        </div>

        <!-- 分配菜单的弹出层 开始 -->
        <div style="display: none;" id="selectRoleMenuDiv">
            <ul id="roleTree" class="dtree" data-id="0"></ul>
        </div>
    </div>
</div>
<script src="${pageContext.request.contextPath}/statics/layui/lib/layui-v2.6.3/layui.js" charset="utf-8"></script>
<script>
    layui.extend({
        dtree: "${pageContext.request.contextPath}/statics/layui_ext/dtree/dtree"//dtree脚本文件的路径
    }).use(['form', 'table', 'laydate', 'jquery', 'layer', 'dtree'], function () {
        var $ = layui.jquery,
            form = layui.form,
            laydate = layui.laydate,
            layer = layui.layer,
            dtree = layui.dtree,
            table = layui.table;

        //渲染表格组件
        var tableIns = table.render({
            elem: '#currentTableId',
            url: '${pageContext.request.contextPath}/admin/role/list',
            toolbar: '#toolbarDemo',
            cols: [[
                {type: "checkbox", width: 50},
                {field: 'id', width: 100, title: '角色编号', align: "center"},
                {field: 'roleName', minWidth: 150, title: '角色名称', align: "center"},
                {field: 'roleDesc', minWidth: 200, title: '角色描述', align: "center"},
                {title: '操作', minWidth: 120, toolbar: '#currentTableBar', align: "center"}
            ]],
            page: true,
            done: function (res, curr, count) {
                //判断当前页码是否是大于1并且当前页的数据量为0
                if (curr > 1 && res.data.length == 0) {
                    var pageValue = curr - 1;
                    //刷新数据表格的数据
                    tableIns.reload({
                        page: {curr: pageValue}
                    });
                }
            }
        });

        //监听模糊查询的提交
        form.on('submit(data-search-btn)', function (data) {
            tableIns.reload({
                where: data.field,
                page: {
                    curr: 1
                }
            });
            return false;
        });

        //添加按钮
        table.on('toolbar(currentTableFilter)', function (obj) {
            if (obj.event === 'add') {  // 监听添加操作
                //这里写点击添加按钮之后的操作
                openAddWindow();
            }
        });

        //编辑按钮
        //删除按钮
        //分配菜单按钮
        table.on('tool(currentTableFilter)', function (obj) {
            var data = obj.data;
            if (obj.event === 'edit') {
                openUpdateWindow(obj.data);
            } else if (obj.event === 'delete') {
                openDeleteWindow(obj.data);
            } else if (obj.event === 'grantMenu') {
                openGrantMenuWindow(obj.data)
            }
        });


        var url;//提交地址
        var mainIndex;//打开窗口的索引

        //添加角色的函数
        function openAddWindow() {
            mainIndex = layer.open({
                type: 1,//打开类型
                title: "添加角色",//窗口标题
                area: ["800px", "400px"],//窗口宽高
                content: $("#addOrUpdateWindow"),//引用的内容窗口
                success: function () {
                    //清空表单数据
                    $("#dataFrm")[0].reset();
                    //添加的提交请求
                    url = "/admin/role/addRole";
                }
            })
        }

        //更改角色的函数
        function openUpdateWindow(data) {
            mainIndex = layer.open({
                type: 1,//打开类型
                title: "更改角色信息",//窗口标题
                area: ["800px", "400px"],//窗口宽高
                content: $("#addOrUpdateWindow"),//引用的内容窗口
                success: function () {
                    //表单数据回显
                    form.val("dataFrm", data);
                    //修改请求
                    url = "/admin/role/updateRole";
                }
            })
        }

        //删除角色的函数
        function openDeleteWindow(data) {
            //先判断当前角色下是否存在员工
            //如果存在，则无法删除，如果没有，则可以删除
            $.get("/admin/role/checkRoleHasEmployee", {"id": data.id}, function (result) {
                if (result.exist) {
                    //提示用户无法删除
                    layer.msg(result.message)
                } else {
                    //提示用户是否删除该角色
                    layer.confirm("确定要删除[<font color='#ff7f50'>" + data.roleName + "</font>]吗", {
                        icon: 3,
                        title: '提示'
                    }, function (index) {
                        //发送请求，删除
                        $.post("/admin/role/deleteRoleById", {"id": data.id}, function (result) {
                            if (result.success) {
                                tableIns.reload()
                            }
                            layer.msg(result.message)
                        }, "json")
                        layer.close(index);
                    });
                }
            }, "json")
        }

        //分配菜单的函数
        function openGrantMenuWindow(data) {
            mainIndex = layer.open({
                type: 1,//打开类型
                title: "分配[<font color='#ff7f50'>" + data.roleName + "</font>]菜单",//窗口标题
                area: ["400px", "550px"],//窗口宽高
                content: $("#selectRoleMenuDiv"),//引用的内容窗口
                btn: ['确定','取消'],
                yes: function (index,layero) {
                    //发送ajax请求，保存选中的菜单ID

                    //获取选中的节点
                    //var menuData = dtree.getCheckbarNodeParam("roleTree");
                    var menuData = dtree.getCheckbarNodesParam("roleTree");
                    //判断当前节点是否被选中
                    if (menuData.length>0){
                        var idArr = [];
                        //获取选中的节点
                        for (var i = 0;i<menuData.length;i++){
                            idArr.push(menuData[i].nodeId)
                        }
                        //将数组转化成字符串
                        var ids = idArr.join(",");

                        //rid:当前角色id，ids:选中的菜单菜单id
                        $.post("/admin/role/saveRoleMenu",{"roleId":data.id,"ids":ids},function (result) {
                            console.log(result)
                            layer.msg(result.message)
                        },"json")
                        layer.close(index)
                    }else {
                        layer.msg("请选择要分配的菜单")
                    }

                },
                btn2: function (index,layero) {

                },
                success: function () {
                    //渲染dtree组件
                    dtree.render({
                        elem: "#roleTree", //绑定ul标签的id属性值
                        url: "/admin/role/initMenuTree?roleId="+data.id,//请求地址
                        dataStyle: "layuiStyle",
                        dataFormat: "list",
                        response: {message: "msg", statusCode: 0},
                        checkbar: true,//开启复选框
                        checkbarType: "all",
                    });
                }
            })
        }

        //监听表单提交事件
        form.on("submit(doSubmit)", function (data) {
            $.post(url, data.field, function (result) {
                if (result.success) {
                    //刷新数据表格
                    tableIns.reload();
                    //关闭窗口
                    layer.close(mainIndex);
                }
                //提示信息
                layer.msg(result.message);
            }, "json");
            //禁止页面刷新
            return false;
        });

    });
</script>

</body>
</html>
