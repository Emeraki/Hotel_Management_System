<%--
  Created by IntelliJ IDEA.
  User: YH
  Date: 2021/7/15
  Time: 17:05
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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/layui/lib/layui-v2.6.3/css/layui.css"   media="all">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/layui/css/layuimini.css?v=2.0.4.2" media="all">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/layui/css/themes/default.css" media="all">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/layui/lib/font-awesome-4.7.0/css/font-awesome.min.css" media="all">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/layui/css/public.css" media="all">
    <%--    下面是添加dtree的插件--%>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/layui_ext/dtree/dtree.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/layui_ext/dtree/font/dtreefont.css">
</head>
<body>
<div class="layuimini-container">
    <div class="layuimini-main">
        <div class="layui-row">
            <%-- 左侧菜单树 --%>
            <div class="layui-col-md2">
                <!-- 树节点容器开始 -->
                <ul id="menuTree" class="dtree" data-id="0" style="width: 240px;"></ul>
                <!-- 树节点容器结束 -->
            </div>

            <%-- 右侧数据表格 --%>
            <div class="layui-col-md10">

                <%-- 表格工具栏 --%>
                <script type="text/html" id="toolbarDemo">
                    <div class="layui-btn-container">
                        <button class="layui-btn layui-btn-normal layui-btn-sm data-add-btn" lay-event="add"><i class="layui-icon layui-icon-add-1"></i>添加 </button>
                    </div>
                </script>

                <%-- 数据表格 --%>
                <table class="layui-hide" id="currentTableId" lay-filter="currentTableFilter"></table>

                <%-- 行工具栏 --%>
                <script type="text/html" id="currentTableBar">
                    <a class="layui-btn layui-btn-xs data-count-edit" lay-event="edit"><i class="layui-icon layui-icon-edit"></i>编辑</a>
                    <a class="layui-btn layui-btn-xs layui-btn-danger data-count-delete" lay-event="delete"><i class="layui-icon layui-icon-close"></i>删除</a>
                </script>

                <%-- 添加和修改窗口 --%>
                <div style="display: none;padding: 5px" id="addOrUpdateWindow">
                    <form class="layui-form" style="width:90%;" id="dataFrm" lay-filter="dataFrm">
                        <%-- 菜单编号 --%>
                        <input type="hidden" name="id">
                        <div class="layui-form-item">
                            <label class="layui-form-label">父级菜单</label>
                            <div class="layui-input-block">
                                <input type="hidden" name="pid" id="pid">
                                <ul id="menuSelectTree" class="dtree" data-id="0"></ul>
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">菜单名称</label>
                            <div class="layui-input-block">
                                <input type="text" name="title" lay-verify="required" autocomplete="off" placeholder="请输入菜单名称" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">菜单地址</label>
                            <div class="layui-input-block">
                                <input type="text" name="href"  autocomplete="off" placeholder="请输入菜单地址" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">菜单图标</label>
                            <div class="layui-input-block">
                                <%--添加隐藏域，保存选中的图标id--%>
                                <input type="hidden" name="icon" id="icon">
                                <input type="text" name="iconFa" id="iconPicker" lay-filter="iconPicker" autocomplete="off" placeholder="请输入菜单图标" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <div class="layui-inline">
                                <label class="layui-form-label">是否展开</label>
                                <div class="layui-input-block">
                                    <input type="radio" name="spread" value="1" title="是">
                                    <input type="radio" name="spread" value="0" title="否" checked>
                                </div>
                            </div>
                        </div>

                        <div class="layui-form-item layui-row layui-col-xs12">
                            <div class="layui-input-block" style="text-align: center;">
                                <button type="button" class="layui-btn" lay-submit lay-filter="doSubmit"><span class="layui-icon layui-icon-add-1"></span>提交</button>
                                <button type="reset" class="layui-btn layui-btn-warm"><span class="layui-icon layui-icon-refresh-1"></span>清空</button>
                                <button type="button" class="layui-btn layui-btn-danger" id="resetMenu"><span class="layui-icon layui-icon-return"></span>重置菜单</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

</body>

<%-- 导入layui的js文件--%>
<script src="${pageContext.request.contextPath}/statics/layui/lib/layui-v2.6.3/layui.js" charset="utf-8"></script>

<script>

    layui.extend({
        dtree: "${pageContext.request.contextPath}/statics/layui_ext/dtree/dtree", //dtree脚本文件的路径
        iconPickerFa: "${pageContext.request.contextPath}/statics/layui/js/lay-module/iconPicker/iconPickerFa"
    }).use(['form', 'table', 'layer', 'jquery','dtree','iconPickerFa'],function () {
        var form = layui.form;
        var table = layui.table;
        var layer = layui.layer;
        var $ = layui.jquery;
        var dtree = layui.dtree;
        var iconPickerFa = layui.iconPickerFa;

        //渲染左侧菜单树
        var menuTree = dtree.render({
            elem: "#menuTree", //绑定ul标签的id属性值
            url: "/admin/menu/loadMenuTree",//请求地址
            dataStyle: "layuiStyle",
            dataFormat: "list",
            response: {message: "msg", statusCode: 0},
        });

        //左侧菜单树点击事件
        dtree.on("node('menuTree')",function (obj) {
            //nodeId是当前节点id
            tableIns.reload({
                where:{"id":obj.param.nodeId},
                page:{curr: 1}
            })
        })


        //初始化渲染数据表格
        var tableIns = table.render({
            elem: '#currentTableId',
            url: '${pageContext.request.contextPath}/admin/menu/findMenuListByPage',
            toolbar: '#toolbarDemo',

            cols: [[
                {field: 'id', width: 100, title: '菜单编号',align: 'center'},
                {field: 'title', width: 180, title: '菜单名称',align: 'center'},
                {field: 'href', minWidth: 200, title: '菜单地址',align: 'center'},
                {field: 'spread', width: 100, title: '是否展开',align: 'center',templet:function (d) {
                    return d.spread == 1 ? "<font color='#ff7f50'>是</font>" : "<font color='aqua'>否</font>"
                }},
                {field: 'icon', title: '菜单图标', width: 100,align: 'center',templet:function (d) {
                    return "<i class='"+d.icon+"'></i>"
                }},
                {title: '操作', width: 200, toolbar: '#currentTableBar', align: "center"}
            ]],
            page: true,

            //这段代码是用来，在删除部门的时候，如果这一页删完这个部门后，这一页没有数据了
            //那么表单会自动跳转到前一页。
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

        //监听添加按钮
        table.on('toolbar(currentTableFilter)', function (obj) {
            if (obj.event === 'add') {  // 监听添加操作
                //这里写点击添加按钮之后的操作
                openAddWindow();
            }
        });

        //监听编辑按钮和删除按钮
        table.on('tool(currentTableFilter)', function (obj) {
            var data = obj.data;
            if (obj.event === 'edit') {
                openUpdateWindow(obj.data);
            } else if (obj.event === 'delete') {
                openDeleteWindow(obj.data);
            }
        });

        //渲染添加菜单里面的下拉菜单树组件
        var menuSelectTree = dtree.renderSelect({
            elem: "#menuSelectTree", //绑定ul标签的id属性值
            url: "/admin/menu/loadMenuTree",//请求地址
            dataStyle: "layuiStyle",
            dataFormat: "list",
            response: {message: "msg", statusCode: 0},
        });

        //添加菜单里面的下拉菜单树点击事件
        dtree.on("node('menuSelectTree')",function (obj) {
            //一旦选中，给隐藏域赋值
            $("#pid").val(obj.param.nodeId)
        })

        //初始化添加菜单里面的图标选择器
        iconPickerFa.render({
            //选择器，推荐使用input
            elem: '#iconPicker',
            //fa图标接口
            url: "/statics/layui/lib/font-awesome-4.7.0/less/variables.less",
            //是否开启搜索
            search: true,
            //是否开启分页
            page:true,
            //每页显示数量，默认12
            limit:12,
            //点击回调
            click:function (data){
                //给隐藏域赋值
                $('#icon').val("fa "+data.icon)
            },
            //渲染成功后的回调
            success: function (d) {
                //页面加载的时候就渲染成功了
            }
        })

        var url;//提交地址
        var mainIndex;//打开窗口的索引

        //添加菜单的函数
        function openAddWindow() {
            mainIndex = layer.open({
                type: 1,//打开类型
                title: "添加菜单",//窗口标题
                area: ["800px", "600px"],//窗口宽高
                content: $("#addOrUpdateWindow"),//引用的内容窗口
                success: function () {
                    //清空表单数据
                    $("#dataFrm")[0].reset();
                    //添加的提交请求
                    url = "/admin/menu/addMenu";
                    //将图标设成默认值
                    iconPickerFa.checkIcon('iconPicker','fa fa-star')
                }
            })
        }

        //更改菜单的函数
        function openUpdateWindow(data) {
            console.log(data)
            var icon = data.icon

            mainIndex = layer.open({
                type: 1,//打开类型
                title: "修改菜单",//窗口标题
                area: ["800px", "600px"],//窗口宽高
                content: $("#addOrUpdateWindow"),//引用的内容窗口
                success: function () {
                    //表单数据回显
                    form.val("dataFrm", data);

                    //将图标设定成已经有的样式
                    iconPickerFa.checkIcon('iconPicker',icon)

                    //父级菜单回显
                    dtree.dataInit("menuSelectTree",data.pid)
                    dtree.selectVal("menuSelectTree")

                    //判断节点是不是父节点
                    if (data.pid == 0){
                        menuSelectTree.reload()
                    }

                    //修改的提交请求
                    url = "/admin/menu/updateMenu";

                }
            });
        }

        //删除菜单的函数
        function openDeleteWindow(data) {
            //首先判断是否存在子菜单
            $.get("/admin/menu/menuHaveChild",{"id":data.id},function (result) {
                if (result.exist){
                    //存在子菜单，无法删除
                    layer.msg(result.message)
                }else {

                    //可以删除
                    layer.confirm("确定要删除[<font color='#ff7f50'>" + data.title + "</font>]吗", {
                        icon: 3,
                        title: '提示'
                    }, function (index) {
                        $.post("/admin/menu/deleteMenu",{"id":data.id},function (result) {
                            if (result.success){
                                tableIns.reload()
                                //刷新菜单树
                                menuTree.reload()
                                menuSelectTree.reload()
                            }
                            layer.msg(result.message)
                        },'json')
                        layer.close(index);
                    });


                }
            },'json')
        }


        //监听表单提交事件
        form.on("submit(doSubmit)", function (data) {
            console.log(data.field)
            $.post(url, data.field, function (result) {
                if (result.success) {
                    //刷新数据表格
                    tableIns.reload();

                    //刷新菜单树
                    menuTree.reload()
                    menuSelectTree.reload()

                    //关闭窗口
                    layer.close(mainIndex);
                }
                //提示信息
                layer.msg(result.message);
            }, "json");
            //禁止页面刷新
            return false;
        });

        //点击重置按钮重置 “父级菜单”
        $('#resetMenu').click(function () {
            menuSelectTree.selectResetVal()
        })


    })
</script>

</html>
