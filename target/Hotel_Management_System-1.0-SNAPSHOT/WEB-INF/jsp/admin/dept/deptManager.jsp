<%--
  Created by IntelliJ IDEA.
  User: YH
  Date: 2021/7/8
  Time: 9:43
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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/layui/lib/layui-v2.6.3/css/layui.css" media="all">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/layui/css/public.css" media="all">
</head>
<body>
<div class="layuimini-container">
    <div class="layuimini-main">

        <%--搜索条件区域--%>
        <fieldset class="table-search-fieldset">
            <legend>搜索信息</legend>
            <div style="margin: 10px 10px 10px 10px">
                <form class="layui-form layui-form-pane" action="">

                    <div class="layui-form-item">
                        <div class="layui-inline">
                            <label class="layui-form-label">部门名称</label>
                            <div class="layui-input-inline">
                                <input type="text" name="dept_name" autocomplete="off" class="layui-input">
                            </div>
                        </div>

                        <div class="layui-inline">
                            <button type="submit" class="layui-btn layui-btn-primary"  lay-submit lay-filter="data-search-btn"><i class="layui-icon"></i> 搜 索</button>
                            <button type="reset" class="layui-btn layui-btn-primary" ><i class="layui-icon layui-icon-refresh-1"></i> 重 置</button>
                        </div>
                    </div>
                </form>
            </div>
        </fieldset>

        <%--头部工具栏区域--%>
        <script type="text/html" id="toolbarDemo">
            <div class="layui-btn-container">
                <button class="layui-btn layui-btn-normal layui-btn-sm data-add-btn" lay-event="add"><i class="layui-icon layui-icon-add-1"></i> 添加 </button>
            </div>
        </script>

        <%--表格区域--%>
        <table class="layui-hide" id="currentTableId" lay-filter="currentTableFilter"></table>

        <%--行工具栏区域--%>
        <script type="text/html" id="currentTableBar">
            <a class="layui-btn layui-btn-normal layui-btn-xs data-count-edit" lay-event="edit"><i class="layui-icon layui-icon-edit"></i>编辑</a>
            <a class="layui-btn layui-btn-xs layui-btn-danger data-count-delete" lay-event="delete"><i class="layui-icon layui-icon-delete"></i>删除</a>
        </script>

        <%-- 添加和修改窗口 --%>
        <div style="display: none;padding: 5px" id="addOrUpdateWindow">
            <form class="layui-form" style="width:90%;" id="dataFrm" lay-filter="dataFrm">
                <div class="layui-form-item">
                    <label class="layui-form-label">部门名称</label>
                    <div class="layui-input-block">
                        <input type="hidden" name="id">
                        <input type="text" name="dept_name" lay-verify="required" autocomplete="off"
                               placeholder="请输入部门名称" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">部门地址</label>
                    <div class="layui-input-block">
                        <input type="text" name="address" lay-verify="required" autocomplete="off" placeholder="请输入部门地址"
                               class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">部门备注</label>
                    <div class="layui-input-block">
                        <textarea class="layui-textarea" name="remark" id="content"></textarea>
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

    </div>
</div>
<script src="${pageContext.request.contextPath}/statics/layui/lib/layui-v2.6.3/layui.js" charset="utf-8"></script>
<script>
    layui.use(['form', 'table','layer'], function () {
        var $ = layui.jquery,
            form = layui.form,
            layer = layui.layer,
            table = layui.table;


        var tableIns = table.render({
            elem: '#currentTableId',
            url: '${pageContext.request.contextPath}/admin/dept/list',
            toolbar: '#toolbarDemo',

            cols: [[
                {field: 'id', width: 120, title: '部门编号',align: 'center'},
                {field: 'dept_name', minWidth: 120, title: '部门名称',align: 'center'},
                {field: 'address', minWidth: 180, title: '地址',align: 'center'},
                {field: 'create_time', minWidth: 150, title: '创建时间',align: 'center',templet: '<div>{{layui.util.toDateString(d.create_time, "yyyy-MM-dd") }}</div>'},
                {field: 'remark', title: '备注', minWidth: 90,align: 'center'},
                {title: '操作', minWidth: 150, toolbar: '#currentTableBar', align: "center"}
            ]],
            limits: [10, 15, 20, 25, 50, 100],
            limit: 15,
            page: true,
            skin: 'line',

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

        // 监听搜索操作
        form.on('submit(data-search-btn)', function (data) {
            tableIns.reload({
                where: data.field,
                page: {
                    curr: 1
                }
            });
            return false;
        });

        /**
         * toolbar监听事件
         */
        table.on('toolbar(currentTableFilter)', function (obj) {
            if (obj.event === 'add') {  // 监听添加操作
                //这里写点击添加按钮之后的操作
                openAddWindow();
            }
        });

        //监听表格复选框选择
        table.on('checkbox(currentTableFilter)', function (obj) {
            console.log(obj)
        });

        //监听行工具栏时间
        table.on('tool(currentTableFilter)', function (obj) {
            var data = obj.data;
            if (obj.event === 'edit') {
                openUpdateWindow(obj.data);
            } else if (obj.event === 'delete') {
                openDeleteWindow(obj.data);
            }
        });

        /**
         * 打开添加窗口
         */
        var url;//提交地址
        var mainIndex;//打开窗口的索引

        //添加部门函数
        function openAddWindow() {
            mainIndex = layer.open({
                type: 1,//打开类型
                title: "添加部门",//窗口标题
                area: ["800px", "400px"],//窗口宽高
                content: $("#addOrUpdateWindow"),//引用的内容窗口
                success: function () {
                    //清空表单数据
                    $("#dataFrm")[0].reset();
                    //添加的提交请求
                    url = "/admin/dept/addDept";
                }
            })
        }

        //更改部门函数
        function openUpdateWindow(data){
            //这里的data是取出来的一行的数据
            //{id: 1, dept_name: "部门1", address: "四川省成都市成华区", create_time: 1613361600000, remark: "无"}
            //console.log(data);
            mainIndex = layer.open({
                type: 1,//打开类型
                title: "更改部门信息",//窗口标题
                area: ["800px", "400px"],//窗口宽高
                content: $("#addOrUpdateWindow"),//引用的内容窗口
                success: function () {
                    //表单数据回显
                    form.val("dataFrm",data);
                    //修改请求
                    url = "/admin/dept/updateDept";
                }
            })
        }

        //删除部门函数
        function openDeleteWindow(data){
            //先判断当前部门下是否存在员工
            //如果存在，则无法删除，如果没有，则可以删除
            $.get("/admin/dept/checkDeptHasEmployee",{"id":data.id},function (result) {
                if (result.exist){
                    //提示用户无法删除
                    layer.msg(result.message)
                }else {
                    //提示用户是否删除该部门
                    layer.confirm("确定要删除[<font color='#ff7f50'>"+data.dept_name+"</font>]吗",{icon:3,title:'提示'},function (index) {
                        //发送请求，删除
                        $.post("/admin/dept/deleteDeptById",{"id":data.id},function (result) {

                            if (result.success){
                                tableIns.reload()
                            }

                            layer.msg(result.message)
                        },"json")

                        layer.close(index);
                    });
                }
            },"json")
        }



        //监听表单提交事件
        form.on("submit(doSubmit)",function (data) {
            $.post(url,data.field,function(result){
                if(result.success){
                    //刷新数据表格
                    tableIns.reload();
                    //关闭窗口
                    layer.close(mainIndex);
                }
                //提示信息
                layer.msg(result.message);
            },"json");
            //禁止页面刷新
            return false;
        });

    });
</script>

</body>
</html>