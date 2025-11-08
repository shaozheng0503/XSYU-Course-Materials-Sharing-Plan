$(document).ready(function() {
    mount(new AdminPanel({active: 6}), document.querySelector(".nav-left-container"));

    var cards = [];

    getCards();

    /**
     * 向数据库请求所有会员卡类型
     */
    function getCards() {
        getRequest(
            '/card/get',
            function (res) {
                cards = res.content;
                renderCards(cards);
            },
            function (error) {
                alert(JSON.stringify(error));
            }
        );
    }

    /**
     * 显示所有会员卡类型
     * @param cards
     */
    function renderCards(cards) {
        $(".content-cards").empty();
        var cardsDomStr = "";

        for (var i=0;i<cards.length;i++){
            cardsDomStr+=
                "<div class='card-container'>" +
                "    <div class='card-card card'>" +
                "       <div class='card-line title-line'>" +
                "           <span class='title'>"+cards[i].name+"</span>" +
                "           <span class='gray-text'>"+cards[i].description+"</span>" +
                "       </div>" +
                "       <div class='card-line'>" +
                "           <span>会员卡价格："+cards[i].price+"</span>" +
                "       </div>" +
                "       <div class='card-line'>" +
                "           <span>购卡需满金额："+cards[i].topUpTarget+"&nbsp;&nbsp;&nbsp;&nbsp;</span>" +
                "           <span>购卡优惠金额："+cards[i].topUpDiscount+"</span>" +
                "       </div>" +
                "       <div class='card-line'>" +
                "           <span>购票需满金额："+cards[i].ticketTarget+"&nbsp;&nbsp;&nbsp;&nbsp;</span>" +
                "           <span>购票优惠金额："+cards[i].ticketDiscount+"</span>" +
                "       </div>" +
                "       <div class='card-item'>"+
                "           <button class='btn btn-primary card-change-btn' id='change"+i+"'>修改</button>"+
                "           <button class='btn btn-primary card-delete-btn' id='delete"+i+"'>删除</button>"+
                "       </div>"+
                "    </div>" +
                "</div>";
        }
        $(".content-cards").append(cardsDomStr);
    }

    /**
     * 确认发布新会员卡
     */
    $("#card-form-btn").click(function () {
        var cardForm = {
            name: $("#card-name-input").val(),
            description: $("#card-description-input").val(),
            price: $("#card-price-input").val(),
            topUpTarget: $("#card-topUp-target-input").val(),
            topUpDiscount: $("#card-topUp-discount-input").val(),
            ticketTarget: $("#card-ticket-target-input").val(),
            ticketDiscount: $("#card-ticket-discount-input").val()
        };

        if(!validateCardForm(cardForm)){
            return;
        }

        postRequest(
            '/card/publish',
            cardForm,
            function (res) {
                if(res.success){
                    getCards();
                    $("#cardModal").modal('hide');
                } else {
                    alert(res.message);
                }
            },
            function (error) {
                alert(JSON.stringify(error));
            }
        );
    });

    /**
     * 验证会员卡表单
     * @param cardForm
     * @returns {boolean}
     */
    function validateCardForm(cardForm) {
        var isValidate = true;
        var messages = [];
        if (!cardForm.name) {
            isValidate = false;
            messages.push("会员卡名称")
        }
        if (!cardForm.price) {
            isValidate = false;
            messages.push("会员卡价格")
        }
        if (!cardForm.topUpTarget) {
            isValidate = false;
            messages.push("购卡需满金额")
        }
        if (!cardForm.topUpDiscount) {
            isValidate = false;
            messages.push("购卡优惠金额")
        }
        if (!cardForm.ticketTarget) {
            isValidate = false;
            messages.push("购票需满金额")
        }
        if (!cardForm.ticketDiscount) {
            isValidate = false;
            messages.push("购票优惠金额")
        }
        if (!isValidate){
            alert("请输入"+messages.join("、")+"!");
            return isValidate;
        }

        for (var i=0;i<cards.length;i++){
            if (cardForm.name===cards[i].name){
                isValidate = false;
                alert("相同名称的会员卡已存在");
                break;
            } else if (parseFloat(cardForm.topUpTarget)===cards[i].topUpTarget
                && parseFloat(cardForm.topUpDiscount)===cards[i].topUpDiscount
                && parseFloat(cardForm.ticketTarget)===cards[i].ticketTarget
                && parseFloat(cardForm.ticketDiscount)===cards[i].ticketDiscount){
                isValidate = false;
                alert("相同类型的会员卡已存在");
                break;
            }
        }

        return isValidate;
    }


    /**
     * 删除会员卡
     */
    $(".content-cards").on('click','button[id^="delete"]',function() {
        var r=confirm("确定要删除该会员卡吗");
        if (r){
            getRequest(
                '/card/delete/'+cards[parseInt($(this).attr('id').substring(6))].id,
                function(res){
                    getCards();
                },
                function(error){
                    alert(error);
                }
            );
        }
    });


    /**
     * 修改会员卡
     */
    $(".content-cards").on('click','button[id^="change"]',function() {
        var card = cards[parseInt($(this).attr('id').substring(6))];
        $("#card-edit-name-input").val(card.name);
        $("#card-edit-description-input").val(card.description);
        $("#card-edit-price-input").val(card.price);
        $("#card-edit-topUp-target-input").val(card.topUpTarget);
        $("#card-edit-topUp-discount-input").val(card.topUpDiscount);
        $("#card-edit-ticket-target-input").val(card.ticketTarget);
        $("#card-edit-ticket-discount-input").val(card.ticketDiscount);
        $('#cardEditModal').modal('show');
        $('#cardEditModal')[0].dataset.cardId = cards[parseInt($(this).attr('id').substring(6))].id;
    });

    /**
     * 确认修改会员卡
     */
    $("#card-edit-btn").click(function () {
        var cardForm = {
            name: $("#card-edit-name-input").val(),
            description: $("#card-edit-description-input").val(),
            price: $("#card-edit-price-input").val(),
            topUpTarget: $("#card-edit-topUp-target-input").val(),
            topUpDiscount: $("#card-edit-topUp-discount-input").val(),
            ticketTarget: $("#card-edit-ticket-target-input").val(),
            ticketDiscount: $("#card-edit-ticket-discount-input").val()
        };

        if(!validateCardEditForm(cardForm)){
            return;
        }

        postRequest(
            '/card/update/'+ parseInt($('#cardEditModal')[0].dataset.cardId),
            cardForm,
            function(res){
                getCards();
                $("#cardEditModal").modal('hide');
            },
            function(error){
                alert(error);
            }
        );
    });

    /**
     * 验证会员卡修改表单
     * @param cardForm
     * @returns {boolean}
     */
    function validateCardEditForm(cardForm) {
        var isValidate = true;
        var messages = [];
        if (!cardForm.price) {
            isValidate = false;
            messages.push("会员卡价格")
        }
        if (!cardForm.topUpTarget) {
            isValidate = false;
            messages.push("购卡需满金额")
        }
        if (!cardForm.topUpDiscount) {
            isValidate = false;
            messages.push("购卡优惠金额")
        }
        if (!cardForm.ticketTarget) {
            isValidate = false;
            messages.push("购票需满金额")
        }
        if (!cardForm.ticketDiscount) {
            isValidate = false;
            messages.push("购票优惠金额")
        }
        if (!isValidate){
            alert("请输入"+messages.join("、")+"!");
            return isValidate;
        }

        for (var i=0;i<cards.length;i++){
            if (parseFloat(cardForm.topUpTarget)===cards[i].topUpTarget
                && parseFloat(cardForm.topUpDiscount)===cards[i].topUpDiscount
                && parseFloat(cardForm.ticketTarget)===cards[i].ticketTarget
                && parseFloat(cardForm.ticketDiscount)===cards[i].ticketDiscount){
                isValidate = false;
                alert("相同类型的会员卡已存在");
                break;
            }
        }

        return isValidate;
    }

});