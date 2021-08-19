<%--
  Created by IntelliJ IDEA.
  User: YH
  Date: 2021/8/18
  Time: 16:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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

        <%-- 搜索条件 --%>
        <fieldset class="table-search-fieldset">
            <legend>搜索信息</legend>
            <div style="margin: 10px 10px 10px 10px">
                <form class="layui-form layui-form-pane" action="">
                    <div class="layui-form-item">
                        <div class="layui-inline">
                            <label class="layui-form-label">入住人</label>
                            <div class="layui-input-inline">
                                <input type="text" name="reservationname" autocomplete="off" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-inline">
                            <label class="layui-form-label">身份证号</label>
                            <div class="layui-input-inline">
                                <input type="text" name="idcard" autocomplete="off" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-inline">
                            <label class="layui-form-label">手机号码</label>
                            <div class="layui-input-inline">
                                <input type="text" name="phone" autocomplete="off" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-inline">
                            <label class="layui-form-label">房间类型</label>
                            <div class="layui-input-inline">
                                <select name="roomtypeid" autocomplete="off" class="layui-input s_roomTypeId">
                                    <option value="">全部</option>
                                </select>
                            </div>
                        </div>
                        <div class="layui-inline">
                            <label class="layui-form-label">入住状态</label>
                            <div class="layui-input-inline">
                                <select name="status" autocomplete="off" class="layui-input">
                                    <option value="">全部</option>
                                    <option value="1">入住中</option>
                                    <option value="2">已离店</option>
                                </select>
                            </div>
                        </div>
                        <div class="layui-inline">
                            <label class="layui-form-label">入住日期</label>
                            <div class="layui-input-inline">
                                <input type="text" name="startTime" id="startTime" autocomplete="off" class="layui-input" placeholder="请选择入住日期" readonly>
                            </div>
                        </div>
                        <div class="layui-inline">
                            <label class="layui-form-label">离店日期</label>
                            <div class="layui-input-inline">
                                <input type="text" name="endTime" id="endTime" autocomplete="off" class="layui-input" placeholder="请选择离店日期" readonly>
                            </div>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <div class="layui-input-block" style="text-align: center">
                            <button type="submit" class="layui-btn"  lay-submit lay-filter="data-search-btn"><i class="layui-icon layui-icon-search"></i>搜索</button>
                            <button type="reset" class="layui-btn layui-btn-warm"><i class="layui-icon layui-icon-refresh-1"></i>重置</button>
                        </div>
                    </div>
                </form>
            </div>
        </fieldset>

        <%-- 表格工具栏 --%>
        <script type="text/html" id="toolbarDemo">
            <div class="layui-btn-container">
                <button class="layui-btn layui-btn-normal layui-btn-sm data-add-btn" lay-event="checkIn"><i
                        class="layui-icon layui-icon-edit"></i>登记入住
                </button>
            </div>
        </script>

        <%-- 数据表格 --%>
        <table class="layui-hide" id="currentTableId" lay-filter="currentTableFilter"></table>

        <%-- 行工具栏 --%>
        <script type="text/html" id="currentTableBar">
            <a class="layui-btn layui-btn-xs data-count-edit" lay-event="checkout"><i
                    class="layui-icon layui-icon-delete"></i>退房</a>
        </script>

        <%-- 添加和修改窗口 --%>
        <div style="display: none;padding: 5px" id="addOrUpdateWindow">
            <form class="layui-form" style="width:90%;" id="dataFrm" lay-filter="dataFrm">
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">预订编号</label>
                        <div class="layui-input-inline">
                            <input type="text" name="ordersid" lay-verify="required" autocomplete="off" readonly
                                   placeholder="请选择预订订单" class="layui-input">
                        </div>
                        <div class="layui-form-mid" style="position:relative;bottom:5px;">
                            <button type="button" class="layui-btn layui-btn-sm" id="openOrdersWindow"><i class="layui-icon layui-icon-add-circle-fine"></i>选择订单</button>
                        </div>
                    </div>

                </div>
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">所属房型</label>
                        <div class="layui-input-inline">
                            <input type="hidden" name="roomtypeid">
                            <input type="text" name="roomTypeName" placeholder="请选择房型" lay-verify="required" autocomplete="off" readonly class="layui-input">
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">房间编号</label>
                        <div class="layui-input-inline">
                            <input type="hidden" name="roomid">
                            <input type="text" name="roomnum" placeholder="请输入房间号码" lay-verify="required" autocomplete="off" readonly class="layui-input">
                        </div>
                    </div>

                </div>
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">入住人</label>
                        <div class="layui-input-inline">
                            <input type="text" name="customername" placeholder="请输入入住人姓名" lay-verify="required" autocomplete="off" readonly class="layui-input">
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">手机号码</label>
                        <div class="layui-input-inline">
                            <input type="text" name="phone" placeholder="请输入手机号码"  lay-verify="required" autocomplete="off" readonly class="layui-input">
                        </div>
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">入住价格</label>
                        <div class="layui-input-inline">
                            <input type="text" name="payprice" placeholder="请输入入住价格" lay-verify="required" autocomplete="off" readonly class="layui-input">
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">入住状态</label>
                        <div class="layui-input-inline">
                            <input type="text" value="已入住" autocomplete="off" readonly class="layui-input">
                        </div>
                    </div>

                </div>
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">入住日期</label>
                        <div class="layui-input-inline">
                            <input type="text" name="arrivedate" placeholder="请选择入住日期" readonly autocomplete="off" class="layui-input">
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">离店日期</label>
                        <div class="layui-input-inline">
                            <input type="text" name="leavedate" placeholder="请选择离店日期" readonly autocomplete="off" class="layui-input">
                        </div>
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">身份证号</label>
                    <div class="layui-input-block">
                        <input type="text" name="idcard"placeholder="请输入身份证号码" lay-verify="required" autocomplete="off" readonly class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">备注</label>
                    <div class="layui-input-block">
                        <textarea class="layui-textarea" name="remark"></textarea>
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

        <!-- 选择预定订单窗口 -->
        <div style="display: none;" id="selectOrdersWindow">
            <%-- 数据表格 --%>
            <table class="layui-hide" id="ordersTableId" lay-filter="ordersTableFilter"></table>
        </div>

    </div>
</div>
<script src="${pageContext.request.contextPath}/statics/layui/lib/layui-v2.6.3/layui.js" charset="utf-8"></script>
<script>


    layui.use(['form', 'table', 'laydate', 'jquery', 'layer'], function () {
        var $ = layui.jquery,
            form = layui.form,
            laydate = layui.laydate,
            layer = layui.layer,
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

        //渲染查询房型的数据
        $.get("/admin/roomType/findAll",{},function (result) {
            var html = "";
            for (let i = 0; i < result.length; i++) {
                html+="<option value='"+result[i].id+"'>"+result[i].typename+"</option>"
            }
            //将网页代码追加到下拉列表
            $("[name='roomtypeid']").append(html)
            //渲染下拉列表
            form.render("select")
        },"json")

        //渲染表格组件
        var tableIns = table.render({
            elem: '#currentTableId',
            url: '${pageContext.request.contextPath}/admin/checkin/list',
            toolbar: '#toolbarDemo',
            cols: [[
                {field: 'id', width: 100, title: '入住编号', align: "center"},
                {field: 'roomType', width: 100, title: '入住房型', align: "center",templet:function (d) {
                        return d.roomType.typename;
                    }},
                {field: 'room', width: 80, title: '房间号', align: "center",templet:function (d) {
                        return d.room.roomnum;
                    }},
                {field: 'customername', width: 100, title: '入住人', align: "center"},
                {field: 'idcard', minWidth: 120, title: '身份证号', align: "center"},
                {field: 'phone', width: 150, title: '手机号', align: "center"},
                {field: 'statusStr', width: 100, title: '状态', align: "center",templet:function (d) {
                        if(d.status==1){
                            return "<font color='#ff7f50'>已入住</font>";
                        }else if(d.status==2){
                            return "已离店";
                        }
                    }},
                {field: 'payprice', width: 120, title: '支付金额', align: "center"},
                {field: 'arrivedate', width: 170, title: '入住日期', align: "center",templet: '<div>{{layui.util.toDateString(d.arrivedate, "yyyy-MM-dd HH:mm:ss") }}</div>'},
                {field: 'leavedate', width: 170, title: '离店日期', align: "center",templet: '<div>{{layui.util.toDateString(d.leavedate, "yyyy-MM-dd HH:mm:ss") }}</div>'},
                {title: '操作', width: 150, toolbar: '#currentTableBar', align: "center"}
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


        //监听头部工具栏
        table.on('toolbar(currentTableFilter)', function (obj) {
            if (obj.event === 'checkIn') {  // 监听添加操作
                //这里写点击添加按钮之后的操作
                openAddWindow();
            }
        });

        table.on('tool(currentTableFilter)', function (obj) {
            var data = obj.data;
            if (obj.event === 'checkout') {
                checkoutWindow(obj.data);
            }
        });

        /**
         * 打开添加窗口
         */
        var url;//提交地址
        var mainIndex;//打开窗口的索引
        var index;//小窗口索引
        var data;

        //添加部门函数
        function openAddWindow() {
            mainIndex = layer.open({
                type: 1,//打开类型
                title: "登记入住",//窗口标题
                area: ["800px", "600px"],//窗口宽高
                content: $("#addOrUpdateWindow"),//引用的内容窗口
                success: function () {
                    //清空表单数据
                    $("#dataFrm")[0].reset();

                }
            })
        }

        //添加部门函数
        function checkoutWindow(data) {
            if (data.status == 1){
                layer.confirm("确定要办理退房吗?",{icon:3,title:"提示"},function (index) {

                    $.post("/admin/checkout/addCheckout",{"checkInId":data.id,"consumePrice":data.payprice,"remark":data.remark},function (result) {
                        if (result.success){
                            layer.alert(result.message,{icon: 6})
                            tableIns.reload()
                        }else {
                            layer.alert(result.message,{icon: 5})
                        }
                    },"json")




                    //关闭确认窗口
                    layer.close(index)
                })
            }else {
                layer.msg("该用户已离店! 不能再退房")
            }
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

        //“选择订单”按钮点击事件
        $("#openOrdersWindow").click(function () {
            index = layer.open({
                type: 1,//打开类型
                title: "选择订单",//窗口标题
                area: ["1300px", "500px"],//窗口宽高
                maxmin:true,//开启最大化最小化
                content: $("#selectOrdersWindow"),//引用的内容窗口
                success: function () {
                    //清空表单数据
                    $("#dataFrm")[0].reset();

                    //添加的提交请求
                    url = "/admin/checkin/addCheckin";

                    //打开成功，初始化表格数据
                    initOrderTable();

                },
                btn:["确定","取消"],
                yes: function (index,layero) {
                    //判断是否有选中行
                    var checkStatus = table.checkStatus('ordersTableId');
                    if (checkStatus.data.length >0){
                        data = checkStatus.data[0]
                        console.log(data)

                        //将到达时间和离开时间格式化一下
                        var a = timestampToTime(data.arrivedate)
                        var b = timestampToTime(data.leavedate)


                        //数据传走进行回显
                        form.val("dataFrm",{
                            //key是form表单的name属性值
                            "ordersid":data.id//订单id
                            ,"roomtypeid": data.roomtypeid //房型ID
                            ,"roomTypeName": data.roomType.typename //房型名称
                            ,"roomnum": data.room.roomnum   //房间号码
                            ,"roomid": data.roomid          //房间ID
                            ,"customername": data.reservationname   //预订人姓名
                            ,"phone": data.phone    //预订人手机
                            ,"payprice": data.reserveprice  //预订价格
                            ,"arrivedate": a  //入住日期
                            ,"leavedate": b   //离店日期
                            ,"idcard": data.idcard  //身份证号码
                            ,"remark":data.remark //备注
                        })

                        layer.close(index)

                    }else {
                        layer.msg("请选择一个订单信息")
                    }
                }
            })
            //默认最大化
            layer.full(index)
        })

        function initOrderTable() {
            table.render({
                elem: '#ordersTableId',
                url: '${pageContext.request.contextPath}/admin/orders/list?status=2',//只查询已确认的订单列表
                cols: [[
                    {type: "radio", width: 50},
                    {field: 'id', width: 100, title: '预订编号', align: "center"},
                    {field: 'roomType', width: 100, title: '预订房型', align: "center",templet:function (d) {
                            return d.roomType.typename;
                        }},
                    {field: 'room', width: 80, title: '房间号', align: "center",templet:function (d) {
                            return d.room.roomnum;
                        }},
                    {field: 'reservationname', width: 100, title: '预订人', align: "center"},
                    {field: 'idcard', width: 170, title: '身份证号', align: "center"},
                    {field: 'phone', width: 150, title: '手机号', align: "center"},
                    {field: 'status', width: 80, title: '状态', align: "center",templet:function (d) {
                            if(d.status==1){
                                return "待确认";
                            }else if(d.status==2){
                                return "已确认";
                            }else if(d.status==3){
                                return "已入住";
                            }
                        }},
                    {field: 'reserveprice', width: 80, title: '预订价', align: "center"},
                    {field: 'reservedate', width: 160, title: '预订时间', align: "center",templet: '<div>{{layui.util.toDateString(d.reservedate, "yyyy-MM-dd HH:mm:ss") }}</div>'},
                    {field: 'arrivedate', width: 110, title: '入住日期', align: "center",templet: '<div>{{layui.util.toDateString(d.arrivedate, "yyyy-MM-dd HH:mm:ss") }}</div>'},
                    {field: 'leavedate', width: 110, title: '离店日期', align: "center",templet: '<div>{{layui.util.toDateString(d.leavedate, "yyyy-MM-dd HH:mm:ss") }}</div>'},
                ]],
                page: true
            });
        }

        function timestampToTime(timestamp) {
            var date = new Date(timestamp);//时间戳为10位需*1000，时间戳为13位的话不需乘1000
            var Y = date.getFullYear() + '-';
            var M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-';
            var D = (date.getDate() < 10 ? '0'+date.getDate() : date.getDate()) + ' ';
            var h = (date.getHours() < 10 ? '0'+date.getHours() : date.getHours()) + ':';
            var m = (date.getMinutes() < 10 ? '0'+date.getMinutes() : date.getMinutes()) + ':';
            var s = (date.getSeconds() < 10 ? '0'+date.getSeconds() : date.getSeconds());

            strDate = Y+M+D+h+m+s;
            return strDate;

        }



    });
</script>

</body>
</html>

