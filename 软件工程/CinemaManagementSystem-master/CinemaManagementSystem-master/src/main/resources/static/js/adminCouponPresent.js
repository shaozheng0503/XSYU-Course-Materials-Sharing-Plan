$(document).ready(function() {
    mount(new AdminPanel({active: 7}), document.querySelector(".nav-left-container"));

    initUserTable();
    initCouponTable();

    $("#query_btn").click(function () {
        var opt = {
            url: '/coupon/get/users',
            query:{
                totalConsume: $("#total_consume_input").val()
            }
        };
        $("#user-table").bootstrapTable('refresh',opt);
    });

    $("#present_btn").click(function () {
        var selectedUsers = $("#user-table").bootstrapTable('getSelections');
        if (selectedUsers.length===0){
            return alert("请选择用户")
        }
        var selectedCoupons = $("#coupon-table").bootstrapTable('getSelections');
        if (selectedCoupons.length===0){
            return alert("请选择优惠券")
        }
        var userIds = [];
        selectedUsers.forEach(function (value) {
            userIds.push(value.userId)
        });
        var couponIds = [];
        selectedCoupons.forEach(function (value) {
            couponIds.push(value.id)
        });
        var presentForm = {
            userIds: userIds,
            couponIds: couponIds
        };

        postRequest(
            '/coupon/present',
            presentForm,
            function (res) {
                if(res.success){
                    alert("赠送成功");
                } else {
                    alert(res.message);
                }
            },
            function (error) {
                alert(JSON.stringify(error));
            }
        );
    });
});


function initUserTable() {
    $('#user-table').bootstrapTable({
        url: '/coupon/get/users',         //请求后台的URL（*）
        method: 'get',                      //请求方式（*）
        striped: true,                      //是否显示行间隔色
        cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        pagination: true,                   //是否显示分页（*）
        sortable: false,                     //是否启用排序
        queryParams: getQueryParams(),//传递参数（*）
        sidePagination: "client",           //分页方式：client客户端分页，server服务端分页（*）
        pageNumber:1,                       //初始化加载第一页，默认第一页
        pageSize: 5,                       //每页的记录行数（*）
        pageList: [5, 10, 15],        //可供选择的每页的行数（*）
        search: true,                       //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
        showColumns: true,                  //是否显示所有的列
        showRefresh: true,                  //是否显示刷新按钮
        minimumCountColumns: 2,             //最少允许的列数
        clickToSelect: true,                //是否启用点击选中行
        uniqueId: "userId",                     //每一行的唯一标识，一般为主键列
        responseHandler: function(res){
            return res.content;
        },
        columns: [{
            checkbox: true
        }, {
            title: 'id',
            field: 'userId'
        }, {
            title: '用户名',
            field: 'username'
        }, {
            title: '消费总额',
            field: 'total'
        } ]
    });
}

function initCouponTable() {
    $('#coupon-table').bootstrapTable({
        url: '/coupon/get/coupons',
        method: 'get',
        striped: true,
        cache: false,
        pagination: true,
        sortable: false,
        sidePagination: "client",
        pageNumber:1,
        pageSize: 5,
        pageList: [5, 10, 15],
        search: true,
        showColumns: true,
        showRefresh: true,
        minimumCountColumns: 5,
        clickToSelect: true,
        uniqueId: "id",
        responseHandler: function(res){
            return res.content;
        },
        columns: [{
            checkbox: true
        }, {
            title: 'id',
            field: 'id'
        }, {
            title: '名称',
            field: 'name'
        }, {
            title: '描述',
            field: 'description'
        }, {
            title: '使用门槛',
            field: 'targetAmount'
        }, {
            title: '优惠金额',
            field: 'discountAmount'
        }, {
            title: '可用时间',
            field: 'startTime',
            formatter: function (value) {
                return value.substring(0,10)
            }
        }, {
            title: '失效时间',
            field: 'endTime',
            formatter: function (value) {
                return value.substring(0,10)
            }
        } ]
    });
}

function getQueryParams() {
    return {
        totalConsume: $("#total_consume_input").val()
    };
}