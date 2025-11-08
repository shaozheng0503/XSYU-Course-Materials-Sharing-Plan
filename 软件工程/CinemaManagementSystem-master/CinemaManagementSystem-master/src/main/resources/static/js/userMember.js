var cardTypes = [];
var isBuyState = true;
var vipCardId = 0;


$(document).ready(function () {
    mount(new UserHeader({active: 4}), document.querySelector("#nav-top-container"));

    getVIP();
    getCoupon();

    $("#card-list").on('click','button[id^="card-buy"]',function () {
        if (vipCardId !== 0 && !confirm("确定重新购买会员卡吗？余额将按8折计入新会员卡")){
            return;
        }
        clearForm();
        $('#buyModal')[0].dataset.cardId = $(this).attr('id').replace('card-buy','');
        $('#buyModal').modal('show');
        $("#userMember-amount-group").css("display", "none");
        $("#myModalLabel").text("购买会员卡");
        isBuyState = true;
    })
});


function getVIP() {
    getRequest(
        '/vip/get/' + sessionStorage.getItem('id'),
        function (res) {
            if (res.success) {
                // 是会员
                $("#member-card").css("visibility", "visible");
                $("#member-card").css("display", "");
                $("#nonmember-card .state").css("display", "none");
                $("#nonmember-card .line > div").css("display", "none");

                vipCardId = res.content.id;
                $("#member-balance").text("¥" + res.content.balance.toFixed(2));
                $("#member-joinDate").text(res.content.joinDate.substring(0, 10));
                $("#member-expireDate").text("永久有效");
                $("#member-topUp").text("满" + res.content.cardType.topUpTarget + "送" + res.content.cardType.topUpDiscount);
                $("#member-ticketBuy").text("满" + res.content.cardType.ticketTarget + "减" + res.content.cardType.ticketDiscount);
            } else {
                // 非会员
                $("#member-card").css("display", "none");
                $("#nonmember-card .state").css("display", "");
                $("#nonmember-card .line").css("display", "");
            }
        },
        function (error) {
            alert(error);
        });

    getRequest(
        '/card/get',
        function (res) {
            if (res.success) {
                $("#card-list").empty();

                cardTypes = res.content;
                var cardsDomStr = "";
                for (var i=0;i<cardTypes.length;i++){
                    cardsDomStr+=
                        "<div class='info'>" +
                        "    <div class='price'><b id='member-buy-price'>" + cardTypes[i].price + "</b>元/张</div>" +
                        "    <div id='member-buy-description' class='description'><b>" + cardTypes[i].name + "：</b>" + cardTypes[i].description +"</div>" +
                        "    <div id='member-buy-top-up' class='description'><b>" + "充值优惠：</b>满" + cardTypes[i].topUpTarget+ "送" + cardTypes[i].topUpDiscount + "</div>" +
                        "    <div id='member-buy-ticket' class='description'><b>" + "购票优惠：</b>满" + cardTypes[i].ticketTarget+ "减" + cardTypes[i].ticketDiscount + "</div>" +
                        "    <button id='card-buy"+ i +"'>立即购买</button>" +
                        "</div>";
                }

                $("#card-list").append(cardsDomStr);
            } else {
                alert(res.content);
            }

        },
        function (error) {
            alert(error);
        });
}


function chargeClick() {
    clearForm();
    $('#buyModal').modal('show')
    $("#userMember-amount-group").css("display", "");
    $("#myModalLabel").text("充值会员卡");
    isBuyState = false;
}

function clearForm() {
    $('#userMember-form input').val("");
    $('#userMember-form .form-group').removeClass("has-error");
    $('#userMember-form p').css("visibility", "hidden");
}

function confirmCommit() {
    if (validateForm()) {
        if ($('#userMember-cardNum').val() === "123123123" && $('#userMember-cardPwd').val() === "123123") {
            if (isBuyState) {
                if (vipCardId === 0){
                    postRequest(
                        '/vip/add/' + sessionStorage.getItem('id') + "/" + cardTypes[parseInt($('#buyModal')[0].dataset.cardId)].id,
                        null,
                        function (res) {
                            $('#buyModal').modal('hide');
                            alert("购买会员卡成功");
                            getVIP();
                        },
                        function (error) {
                            alert(error);
                        });
                } else {
                    postRequest(
                        '/vip/change/' + vipCardId + "/" + cardTypes[parseInt($('#buyModal')[0].dataset.cardId)].id,
                        null,
                        function (res) {
                            $('#buyModal').modal('hide');
                            alert("切换会员卡成功");
                            getVIP();
                        },
                        function (error) {
                            alert(error);
                        });
                }
            } else {
                postRequest(
                    '/vip/charge',
                    {vipId: vipCardId, amount: parseInt($('#userMember-amount').val())},
                    function (res) {
                        $('#buyModal').modal('hide');
                        alert("充值成功");
                        getVIP();
                    },
                    function (error) {
                        alert(error);
                    });
            }
        } else {
            alert("银行卡号或密码错误");
        }
    }
}

function validateForm() {
    var isValidate = true;
    if (!$('#userMember-cardNum').val()) {
        isValidate = false;
        $('#userMember-cardNum').parent('.form-group').addClass('has-error');
        $('#userMember-cardNum-error').css("visibility", "visible");
    }
    if (!$('#userMember-cardPwd').val()) {
        isValidate = false;
        $('#userMember-cardPwd').parent('.form-group').addClass('has-error');
        $('#userMember-cardPwd-error').css("visibility", "visible");
    }
    if (!isBuyState && (!$('#userMember-amount').val() || parseInt($('#userMember-amount').val()) <= 0)) {
        isValidate = false;
        $('#userMember-amount').parent('.form-group').addClass('has-error');
        $('#userMember-amount-error').css("visibility", "visible");
    }
    return isValidate;
}

function getCoupon() {
    getRequest(
        '/coupon/' + sessionStorage.getItem('id') + '/get',
        function (res) {
            if (res.success) {
                var couponList = res.content;
                var couponListContent = '';
                for (let coupon of couponList) {
                    couponListContent += '<div class="col-md-6 coupon-wrapper"><div class="coupon"><div class="content">' +
                        '<div class="col-md-8 left">' +
                        '<div class="name">' +
                        coupon.name +
                        '</div>' +
                        '<div class="description">' +
                        coupon.description +
                        '</div>' +
                        '<div class="price">' +
                        '满' + coupon.targetAmount + '减' + coupon.discountAmount +
                        '</div>' +
                        '</div>' +
                        '<div class="col-md-4 right">' +
                        '<div>有效日期：</div>' +
                        '<div>' + formatDate(coupon.startTime) + ' ~ ' + formatDate(coupon.endTime) + '</div>' +
                        '</div></div></div></div>'
                }
                $('#coupon-list').html(couponListContent);

            }
        },
        function (error) {
            alert(error);
        });
}

function formatDate(date) {
    return date.substring(5, 10).replace("-", ".");
}