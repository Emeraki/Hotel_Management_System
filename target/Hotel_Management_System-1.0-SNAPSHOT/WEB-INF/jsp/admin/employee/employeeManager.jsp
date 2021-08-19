<%--
  Created by IntelliJ IDEA.
  User: YH
  Date: 2021/7/13
  Time: 9:21
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
</head>
<body>
<div class="layuimini-container">
    <div class="layuimini-main">

        <!-- 搜索条件 -->
        <fieldset class="table-search-fieldset">
            <legend>搜索信息</legend>
            <div style="margin: 10px 10px 10px 10px">
                <form class="layui-form layui-form-pane">
                    <div class="layui-form-item">
                        <div class="layui-inline">
                            <label class="layui-form-label">员工姓名</label>
                            <div class="layui-input-inline">
                                <input type="text" name="login_name" autocomplete="off" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-inline">
                            <label class="layui-form-label">真实姓名</label>
                            <div class="layui-input-inline">
                                <input type="text" name="name" autocomplete="off" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-inline">
                            <label class="layui-form-label">员工性别</label>
                            <div class="layui-input-inline">
                                <select name="sex" autocomplete="off" class="layui-input">
                                    <option value="">请选择性别</option>
                                    <option value="1">男</option>
                                    <option value="2">女</option>
                                </select>
                            </div>
                        </div>
                        <div class="layui-inline">
                            <label class="layui-form-label">所属部门</label>
                            <div class="layui-input-inline">
                                <select name="dept_id" autocomplete="off" id="s_deptId" class="layui-input">
                                    <option value="">请选择部门</option>
                                </select>
                            </div>
                        </div>
                        <div class="layui-inline">
                            <label class="layui-form-label">开始日期</label>
                            <div class="layui-input-inline">
                                <input type="text" name="startDate" id="startTime" autocomplete="off"
                                       class="layui-input" readonly>
                            </div>
                        </div>
                        <div class="layui-inline">
                            <label class="layui-form-label">结束日期</label>
                            <div class="layui-input-inline">
                                <input type="text" name="endDate" id="endTime" autocomplete="off" class="layui-input"
                                       readonly>
                            </div>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <div class="layui-input-block" style="text-align: center">
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
            <button class="layui-btn layui-btn-xs layui-btn-warm" lay-event="resetPwd"><i
                    class="layui-icon layui-icon-refresh"></i>重置密码
            </button>
            <button class="layui-btn layui-btn-xs" lay-event="grantRole"><i class="layui-icon layui-icon-edit"></i>分配角色
            </button>
        </script>

        <!-- 添加和修改窗口 -->
        <div style="display: none;padding: 5px" id="addOrUpdateWindow">
            <form class="layui-form" style="width:90%;" id="dataFrm" lay-filter="dataFrm">
                <!-- 隐藏域，保存员工id -->
                <input type="hidden" name="id" id="id">

                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">登陆名称</label>
                        <div class="layui-input-block">
                            <input type="text" name="login_name" id="loginname" lay-verify="required" autocomplete="off"
                                   placeholder="请输入登陆名称" class="layui-input">
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">员工姓名</label>
                        <div class="layui-input-block">
                            <input type="text" name="name" id="name" lay-verify="required" autocomplete="off"
                                   placeholder="请输入员工姓名" class="layui-input">
                        </div>
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">入职日期</label>
                        <div class="layui-input-block">
                            <input type="text" name="hire_date" id="hiredate" readonly lay-verify="required"
                                   autocomplete="off" placeholder="请选择入职日期" class="layui-input">
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">所属部门</label>
                        <div class="layui-input-block">
                            <select name="dept_id" id="deptid" class="layui-input">
                                <option value="">请选择部门</option>
                            </select>
                        </div>
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">员工性别</label>
                        <div class="layui-input-block">
                            <input type="radio" name="sex" value="1" title="男" checked>
                            <input type="radio" name="sex" value="2" title="女">
                        </div>
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">员工备注</label>
                    <div class="layui-input-block">
                        <textarea class="layui-textarea" name="remark" id="remark"></textarea>
                    </div>
                </div>


                <div class="layui-form-item layui-row layui-col-xs12">
                    <div class="layui-input-block" style="text-align: center;">
                        <button type="button" class="layui-btn" lay-submit lay-filter="doSubmit"><span
                                class="layui-icon layui-icon-add-1"></span>提交
                        </button>
                        <button type="button" class="layui-btn layui-btn-warm"><span
                                class="layui-icon layui-icon-refresh-1"></span>重置
                        </button>
                    </div>
                </div>
            </form>
        </div>

        <!-- 更改密码窗口 -->
        <div style="display: none;padding: 5px" id="resetPwdWindow">
            <form class="layui-form" style="width:90%;" id="pwd_dataFrm" lay-filter="dataFrm">
                <!-- 隐藏域，保存员工id -->
                <input type="hidden" name="id" id="pwd_id">

                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">新密码</label>
                        <div class="layui-input-block">
                            <input type="text" name="login_pwd" id="pwd_loginname" lay-verify="required" autocomplete="off"
                                   placeholder="新密码" class="layui-input">
                        </div>
                    </div>
                </div>

                <div class="layui-form-item layui-row layui-col-xs12">
                    <div class="layui-input-block" style="text-align: center;">
                        <button type="button" class="layui-btn" lay-submit lay-filter="doSubmit"><span
                                class="layui-icon layui-icon-add-1"></span>提交
                        </button>
                        <button type="button" class="layui-btn layui-btn-warm"><span
                                class="layui-icon layui-icon-refresh-1"></span>重置
                        </button>
                    </div>
                </div>
            </form>
        </div>

        <!-- 员工分配角色弹出层 -->
        <div style="display: none;padding: 5px" id="selectUserRoleDiv">
            <table class="layui-hide" id="roleTable" lay-filter="roleTable"></table>
        </div>

    </div>
</div>
<script src="${pageContext.request.contextPath}/statics/layui/lib/layui-v2.6.3/layui.js" charset="utf-8"></script>
<script>
    layui.use(['form', 'table', 'laydate', 'jquery'], function () {
        var $ = layui.jquery,
            form = layui.form,
            laydate = layui.laydate,
            table = layui.table;

        //渲染日期组件
        laydate.render({
            elem: '#startTime', //指定元素
            type: 'datetime'
        });
        laydate.render({
            elem: '#endTime', //指定元素
            type: 'datetime'
        });
        laydate.render({
            elem: '#hiredate' //指定元素

        });

        //渲染表格组件
        var tableIns = table.render({
            elem: '#currentTableId',
            url: '${pageContext.request.contextPath}/admin/employee/list',
            toolbar: '#toolbarDemo',
            cols: [[
                {type: "checkbox", width: 50},
                {field: 'id', width: 100, title: '员工编号', align: "center"},
                {field: 'login_name', width: 120, title: '登录名', align: "center"},
                {field: 'name', width: 120, title: '真实姓名', align: "center"},
                {field: 'sex', width: 80, title: '性别', align: "center",templet: function (d){
                        return d.sex == 1 ? "男" : "女"
                    }},
                {field: 'dept_name', width: 120, title: '所属部门', align: "center"},
                {
                    field: 'hire_date',
                    width: 180,
                    title: '入职日期',
                    align: "center",
                    templet: '<div>{{layui.util.toDateString(d.hire_date, "yyyy-MM-dd") }}</div>'
                },
                {
                    field: 'create_date',
                    width: 180,
                    title: '创建时间',
                    align: "center",
                    templet: '<div>{{layui.util.toDateString(d.create_date, "yyyy-MM-dd") }}</div>'
                },
                {title: '操作', minWidth: 120, toolbar: '#currentTableBar', align: "center"}
            ]],
            page: true,
            done: function (res, curr, count) {

                //console.log(this.url)

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

            //field: {login_name: "2", name: "", sex: "", dept_id: "", startDate: "", …}
            //console.log(data.field)

            tableIns.reload({
                //这里data.field里面的参数会返回，变成如下格式
                //http://localhost:8080/admin/employee/list?page=1&limit=10&login_name=2&name=2&sex=&dept_id=&startDate=&endDate=
                //这里接受了前端传回来的参数，之后自动封装到EmployeeVo类里，进行查询，
                //所以这就是为什么findEmployeeList要写成模糊查询，就是这个道理
                where: data.field,
                page: {
                    curr: 1
                }
            });
            return false;
        });

        //搜索栏的部门列表，获得数据
        $.get("/admin/dept/deptList", {}, function (result) {
            var html = ""
            //循环遍历
            for (let i = 0; i < result.length; i++) {
                html += "<option value='" + result[i].id + "'>" + result[i].dept_name + "</option>"
            }
            //将html追加到下拉列表中
            $("[name = 'dept_id']").append(html)
            form.render("select")
        }, "json")

        //监听头部工具栏事件
        table.on('toolbar(currentTableFilter)', function (obj) {
            if (obj.event === 'add') {  // 监听添加操作
                //这里写点击添加按钮之后的操作
                openAddWindow();
            }
        });

        //编辑按钮
        //删除按钮
        //重置密码
        //分配角色
        table.on('tool(currentTableFilter)', function (obj) {
            var data = obj.data;
            if (obj.event === 'edit') {
                openUpdateWindow(obj.data);
            } else if (obj.event === 'delete') {
                openDeleteWindow(obj.data);
            } else if (obj.event === 'resetPwd') {
                openResetPwdWindow(obj.data)
            } else if (obj.event === 'grantRole') {
                openGrantRoleWindow(obj.data)
            }
        });


        var url;//提交地址
        var mainIndex;//打开窗口的索引

        //添加员工的函数
        function openAddWindow() {
            mainIndex = layer.open({
                type: 1,//打开类型
                title: "添加员工",//窗口标题
                area: ["800px", "400px"],//窗口宽高
                content: $("#addOrUpdateWindow"),//引用的内容窗口
                success: function () {
                    //清空表单数据
                    $("#dataFrm")[0].reset();
                    //添加的提交请求
                    url = "/admin/employee/addEmployee";
                }
            })
        }

        //更改员工的函数
        function openUpdateWindow(data) {

            //这里日期出了点问题，所以单独把hire_date取出来重新处理
            var r = data.hire_date
            var date = new Date(r);
            var y = date.getFullYear()
            var m = date.getMonth() + 1
            var d = date.getDate()
            var hire_date = y + "-" + m + "-" + d

            mainIndex = layer.open({
                type: 1,//打开类型
                title: "修改员工",//窗口标题
                area: ["800px", "400px"],//窗口宽高
                content: $("#addOrUpdateWindow"),//引用的内容窗口
                success: function () {
                    //表单数据回显
                    form.val("dataFrm", data);

                    //单独再对hire_date这一栏进行数据回显
                    $('#hiredate').val(hire_date)

                    //修改的提交请求
                    url = "/admin/employee/updateEmployee";
                }
            });
        }

        //删除员工的函数
        function openDeleteWindow(data) {

            //提示用户是否删除该角色
            layer.confirm("确定要删除[<font color='#ff7f50'>" + data.login_name + "</font>]吗", {
                icon: 3,
                title: '提示'
            }, function (index) {
                //发送请求，删除
                //直接ajax请求申请删除
                $.post("/admin/employee/deleteById",{"id":data.id},function (result) {
                    if (result.success){
                        tableIns.reload()
                    }
                    layer.msg(result.message)
                },"json")
                layer.close(index);
            });


        }

        // //重置密码的函数
        // function openResetPwdWindow(data) {
        //
        //     //提示用户是否重置密码
        //     layer.confirm("确定要重置[<font color='#ff7f50'>" + data.login_name + "</font>]的密码吗", {
        //         icon: 3,
        //         title: '提示'
        //     }, function (index) {
        //         //发送请求，删除
        //         //直接ajax请求申请删除
        //         $.post("/admin/employee/resetPwd",{"id":data.id},function (result) {
        //             if (result.success){
        //                 tableIns.reload()
        //             }
        //             layer.msg(result.message)
        //         },"json")
        //         layer.close(index);
        //     });
        //
        //
        // }

        //重置密码的函数
        function openResetPwdWindow(data) {

            mainIndex = layer.open({
                type: 1,//打开类型
                title: "重置密码",//窗口标题
                area: ["400px", "200px"],//窗口宽高
                content: $("#resetPwdWindow"),//引用的内容窗口
                success: function () {
                    //清空表单数据
                    $("#dataFrm")[0].reset();

                    //回显一个id
                    $('#pwd_id').val(data.id)

                    //添加的提交请求
                    url = "/admin/employee/resetPwd";
                }
            })
        }

        function openGrantRoleWindow(data){
            var eid = data.id
            mainIndex = layer.open({
                type: 1,//打开类型
                title: "分配[<font color='#ff7f50'>" + data.name + "</font>]的角色",//窗口标题
                area: ["800px", "400px"],//窗口宽高
                content: $("#selectUserRoleDiv"),//引用的内容窗口
                btn: ['<i class="layui-icon layui-icon-ok"></i>确定','<i class="layui-icon layui-icon-close"></i>取消'],
                yes: function (index,layero) {

                    //获取选中的角色id
                    var checkStatus = table.checkStatus('roleTable');
                    if (checkStatus.data.length > 0){

                        var rids = new Array(checkStatus.data.length);
                        //循环将数据写入数据库中
                        for (let i = 0; i < checkStatus.data.length; i++) {
                            rids[i] = checkStatus.data[i].id
                        }

                        //通过post提交参数，执行sql，存入数据库
                        $.post("/admin/role/addRoleEmployee",{"eid":eid,"rids":JSON.stringify(rids)},function (result){
                            layer.msg(result.message)
                            layer.close(index);
                        },"json")

                    }else {
                        layer.msg("请选择要分配的角色")
                    }
                },
                btn2: function (index,layero) {

                },
                success: function () {
                    initTableData(data)
                }
            })
        }

        //初始化上面的这个表格数据
        function initTableData(data){
            table.render({
                elem: '#roleTable',
                url: '${pageContext.request.contextPath}/admin/role/initEmployee_RoleTable?id='+data.id,
                cols: [[
                    {type:"checkbox",width: 50},
                    {field: 'id', minWidth: 120, title: '员工编号',align: 'center'},
                    {field: 'rolename', minWidth: 120, title: '角色名称',align: 'center'},
                    {field: 'roledesc', minWidth: 180, title: '角色描述',align: 'center'},
                ]],

            });
        }


        //监听表单提交事件
        form.on("submit(doSubmit)", function (data) {
            $.post(url,{"id":data.field.id,"newPwd":data.field.login_pwd}, function (result) {
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
