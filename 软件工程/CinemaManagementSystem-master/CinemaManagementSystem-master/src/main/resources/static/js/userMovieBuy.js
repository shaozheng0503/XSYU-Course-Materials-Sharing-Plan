var selectedSeats = []
var scheduleId;
var order = {ticketId: [], couponId: 0};
var coupons = [];
var orderInfo = [];
var isVIP = false;
var useVIP = true;
var movieId;

$(document).ready(function () {
    scheduleId = parseInt(window.location.href.split('?')[1].split('&')[1].split('=')[1]);

    getInfo();

    function getInfo() {
        getRequest(
            '/ticket/get/occupiedSeats?scheduleId=' + scheduleId,
            function (res) {
                if (res.success) {
                    renderSchedule(res.content.scheduleItem, res.content.seats);
                }
            },
            function (error) {
                alert(JSON.stringify(error));
            }
        );
    }

});


// 显示用户已选的座位信息列表
function showSelectedInfo() {
    selectedSeats.sort(function (x, y) {
        var res = x[0] - y[0];
        return res === 0 ? x[1] - y[1] : res;
    });

    let seatDetailStr = "";
    if (selectedSeats.length == 0) {
        seatDetailStr += "还未选择座位"
        $('#order-confirm-btn').attr("disabled", "disabled")
    } else {
        for (let seatLoc of selectedSeats) {
            seatDetailStr += "<span>" + (seatLoc[0] + 1) + "排" + (seatLoc[1] + 1) + "座</span>";
        }
        $('#order-confirm-btn').removeAttr("disabled");
    }
    $('#seat-detail').html(seatDetailStr);
}

function renderSchedule(schedule, seats) {
    $('#schedule-hall-name').text(schedule.hallName);
    $('#order-schedule-hall-name').text(schedule.hallName);
    $('#schedule-fare').text(schedule.fare.toFixed(2));
    $('#order-schedule-fare').text(schedule.fare.toFixed(2));
    $('#schedule-time').text(schedule.startTime.substring(5, 7) + "月" + schedule.startTime.substring(8, 10) + "日 " + schedule.startTime.substring(11, 16) + "场");
    $('#order-schedule-time').text(schedule.startTime.substring(5, 7) + "月" + schedule.startTime.substring(8, 10) + "日 " + schedule.startTime.substring(11, 16) + "场");

    var hallDomStr = "";
    var seat = "";
    for (var i = 0; i < seats.length; i++) {
        var temp = ""
        for (var j = 0; j < seats[i].length; j++) {
            var id = "seat" + i + j

            if (seats[i][j] === 0) {
                // 未选
                temp += "<button class='cinema-hall-seat-choose' id='" + id + "' onclick='seatClick(\"" + id + "\"," + i + "," + j + ")'></button>";
            } else if (seats[i][j] === 1) {
                // 已被其他已支付的电影票选中
                temp += "<button class='cinema-hall-seat-lock'></button>";
            } else if (seats[i][j] === 2) {
                // 用户自己已经锁定座位但尚未支付的座位
                temp += "<button class='cinema-hall-seat' id='" + id + "' onclick='seatClick(\"" + id + "\"," + i + "," + j + ")'></button>";
                selectedSeats.push([i, j]);
            } else {
                // 该位置没有座位 状态为-1
                temp += "<div class='cinema-hall-seat-none' id='" + id + "'></div>";
            }
        }
        seat += "<div>" + temp + "</div>";
    }

    showSelectedInfo();

    var hallDom =
        "<div class='cinema-hall'>" +
        "<div>" +
        "<span class='cinema-hall-name'>" + schedule.hallName + "</span>" +
        "<span class='cinema-hall-size'>" + seats.length + '*' + seats[0].length + "</span>" +
        "</div>" +
        "<div class='cinema-seat'>" + seat +
        "</div>" +
        "</div>";
    hallDomStr += hallDom;

    $('#hall-card').html(hallDomStr);
    movieId=schedule.movieId;
}

function seatClick(id, i, j) {
    let seat = $('#' + id);
    if (seat.hasClass("cinema-hall-seat-choose")) {
        seat.removeClass("cinema-hall-seat-choose");
        seat.addClass("cinema-hall-seat");

        selectedSeats[selectedSeats.length] = [i, j]
    } else {
        seat.removeClass("cinema-hall-seat");
        seat.addClass("cinema-hall-seat-choose");

        selectedSeats = selectedSeats.filter(function (value) {
            return value[0] != i || value[1] != j;
        })
    }

    showSelectedInfo();
}

function orderConfirmClick() {
    $('#seat-state').css("display", "none");
    $('#order-state').css("display", "");

    const seats = []
    selectedSeats.forEach(x => {
        seats.push({'columnIndex': x[1], 'rowIndex': x[0]})
    })
    postRequest(
        '/ticket/lockSeat',
        {
            'userId': sessionStorage.getItem('id'),
            'scheduleId': scheduleId,
            'seats': seats
        },
        function (res) {
            orderInfo = res.content;
            renderOrder(orderInfo);
        },
        function (error) {
            alert(error);
        }
    );

    getRequest(
        '/vip' + '/get/' + sessionStorage.getItem('id'),
        function (res) {
            isVIP = res.success;
            useVIP = res.success;
            if (isVIP) {
                $('#member-balance').html("<div><b>会员卡余额：</b>" + res.content.balance.toFixed(2) + "元</div>");
            } else {
                $("#member-pay").css("display", "none");
                $("#nonmember-pay").addClass("active");

                $("#modal-body-member").css("display", "none");
                $("#modal-body-nonmember").css("display", "");
            }
        },
        function (error) {
            alert(error);
        });
}

function switchPay(type) {
    useVIP = (type == 0);
    if (type == 0) {
        $("#member-pay").addClass("active");
        $("#nonmember-pay").removeClass("active");

        $("#modal-body-member").css("display", "");
        $("#modal-body-nonmember").css("display", "none");
    } else {
        $("#member-pay").removeClass("active");
        $("#nonmember-pay").addClass("active");

        $("#modal-body-member").css("display", "none");
        $("#modal-body-nonmember").css("display", "");
    }
}

function renderOrder(orderInfo) {
    var ticketStr = "<div>" + selectedSeats.length + "张</div>";
    for (let ticketInfo of orderInfo.ticketVOList) {
        ticketStr += "<div>" + (ticketInfo.rowIndex + 1) + "排" + (ticketInfo.columnIndex + 1) + "座</div>";
        order.ticketId.push(ticketInfo.id);
    }
    $('#order-tickets').html(ticketStr);

    var total = orderInfo.total.toFixed(2);
    $('#order-total').text(total);
    $('#order-footer-total').text("总金额： ¥" + total);

    var couponTicketStr = "";
    if (orderInfo.coupons.length == 0) {
        $('#order-discount').text("优惠金额：无");
        $('#order-actual-total').text(" ¥" + total);
        $('#pay-amount').html("<div><b>金额：</b>" + total + "元</div>");
        $('#order-coupons').html("<option>无可用优惠券</option>");
    } else {
        coupons = orderInfo.coupons;
        for (let coupon of coupons) {
            couponTicketStr += "<option>满" + coupon.targetAmount + "减" + coupon.discountAmount + "</option>"
        }
        $('#order-coupons').html(couponTicketStr);
        changeCoupon(0);
    }

    getRequest(
        '/refund/all',
        function (res) {
            if (res.success){
                renderRefund(res.content);
            }
        },
        function (error) {
            alert(error);
        }
    );
}

function changeCoupon(couponIndex) {
    order.couponId = coupons[couponIndex].id;
    $('#order-discount').text("优惠金额： ¥" + coupons[couponIndex].discountAmount.toFixed(2));
    var actualTotal = (parseFloat($('#order-total').text()) - parseFloat(coupons[couponIndex].discountAmount)).toFixed(2);
    $('#order-actual-total').text(" ¥" + actualTotal);
    $('#pay-amount').html("<div><b>金额：</b>" + actualTotal + "元</div>");
}

function payConfirmClick() {
    if (useVIP) {
        $.post(
            {
                type: 'POST',
                url: '/ticket/vip/buy',
                async: true,
                data: JSON.stringify(
                    {
                        "ticketId": order.ticketId,
                        "couponId":order.couponId
                    }
                ),
                contentType: 'application/json',
                processData: false,
                success: function (res) {
                    if (res.success) postPayRequest()
                    else alert(JSON.stringify(res))
                },
                error: function (err) {
                    alert(JSON.stringify(err))
                }
            }
        );
    } else {
        if (validateForm()) {
            if ($('#userBuy-cardNum').val() === "123123123" && $('#userBuy-cardPwd').val() === "123123") {
                $.post(
                    {
                        type: 'POST',
                        url: '/ticket/buy',
                        async: true,
                        data: JSON.stringify(
                            {
                                "ticketId": order.ticketId,
                                "couponId":order.couponId
                            }
                        ),
                        contentType: 'application/json',
                        processData: false,
                        success: function (res) {
                            if (res.success) postPayRequest()
                            else alert(JSON.stringify(res))
                        },
                        error: function (err) {
                            alert(JSON.stringify(err))
                        }
                    }
                );
            } else {
                alert("银行卡号或密码错误");
            }
        }
    }
}

// 支付成功后的界面渲染
function postPayRequest() {
    $('#order-state').css("display", "none");
    $('#success-state').css("display", "");
    $('#buyModal').modal('hide')
}

function validateForm() {
    var isValidate = true;
    if (!$('#userBuy-cardNum').val()) {
        isValidate = false;
        $('#userBuy-cardNum').parent('.form-group').addClass('has-error');
        $('#userBuy-cardNum-error').css("visibility", "visible");
    }
    if (!$('#userBuy-cardPwd').val()) {
        isValidate = false;
        $('#userBuy-cardPwd').parent('.form-group').addClass('has-error');
        $('#userBuy-cardPwd-error').css("visibility", "visible");
    }
    return isValidate;
}

function renderRefund(refunds){
    var bodyContent='*退票策略：此电影上映';
    for (var i=0; i<refunds.length; i++){
        if (refunds[i].movie.id===movieId) {
            if (refunds[i].price===100){
                bodyContent=bodyContent+refunds[i].time+'天前退票，全款返还支付金额，上映';
            } else if (refunds[i].price===0){
                bodyContent=bodyContent+refunds[i].time+'天前退票，不返还支付金额，上映';
            } else{
                bodyContent=bodyContent+refunds[i].time+'天前退票，返还实付票价的'+refunds[i].price+'%，上映';
            }
        }
    }
    bodyContent=bodyContent.substring(0,bodyContent.length-3)+'。';
    if (bodyContent==='*退票策略：此电。'){
        bodyContent='*退票策略：此电影全款退票。';
    }
    $('.refund-show').empty().html(bodyContent);
}