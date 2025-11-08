const BUY_TICKET_STR = "购买电影票";
const BUY_VIP_CARD_STR = "购买会员卡";

var historyId;

$(document).ready(function () {

    historyId = parseInt(window.location.href.split('?')[1].split('&')[0].split('=')[1]);

    getConsumeHisDetail();

});

function getConsumeHisDetail() {
    getRequest(
      '/history/consume/get/detail?id='+historyId,
        function (res) {
            if (res.success) {
                renderConsumeHisDetail(res.content);
            } else {
                alert(res.message);
            }
        },
        function (error) {
            alert(JSON.stringify(error));
        }
    );
}

/**
 * 渲染消费详细信息
 * @author hxw
 * @date 2019-5-22
 * @param detail
 */
function renderConsumeHisDetail(detail) {
    var detailContent = '';
    if (detail.type == BUY_TICKET_STR) {
        detailContent +=
            "<div class='detail-type-title'>" + detail.type + "</div>" +
            "<div class='detail-wrapper'>" +
                "<div class='detail-col-one'>" +
                    "<span class='detail-txt'>实付金额:" + detail.money + "</span>" +
                    "<span class='detail-txt'>消费时间:" + formateTime(detail.time) + "</span>" +
                    "<span class='detail-txt'>影厅名称:" + detail.hallName + "</span>" +
                    "<span class='detail-txt'>放映时间:" + formateTime(detail.startTime) + "</span>" +
                "</div>" +
                "<div class='detail-col-two'>" +
                    "<span class='detail-txt'>优惠金额:" + detail.discount + "</span>" +
                    "<span class='detail-txt'>消费方式:" + detail.consumeType + "</span>" +
                    "<span class='detail-txt'>电影名称:" + detail.movieName + "</span>" +
                    "<span class='detail-txt'>座位号:" + detail.rowIndex + "排" + detail.columnIndex + "座" + "</span>" +
                "</div>" +
            "</div>";
    } else if (detail.type == BUY_VIP_CARD_STR) {
        detailContent +=
            "<div class='detail-type-title'>" + detail.type + "</div>" +
            "<div class='detail-wrapper'>" +
                "<div class='detail-col-one'>" +
                    "<span class='detail-txt'>实付金额:" + detail.money + "</span>" +
                    "<span class='detail-txt'>消费时间:" + formateTime(detail.time) + "</span>" +
                    "<span class='detail-txt'>会员卡种类:" + detail.cardType +"</span>" +
                "</div>" +
                "<div class='detail-col-two'>" +
                    "<span class='detail-txt'>优惠金额:" + detail.discount + "</span>" +
                    "<span class='detail-txt'>消费方式:" + detail.consumeType + "</span>" +
                "</div>" +
            "</div>";
    }
    $('#consumehis-detail').html(detailContent);
}

/**
 * 返回消费记录页
 * @author hxw
 * @date 2019-5-22
 */
function back() {
    window.location.href = "/user/history/consume";
}

function formateTime(time) {
    var res = '';
    var date = time.split('T')[0];
    var hms = time.split('T')[1].split('.')[0];
    res = res + date + ' ' + hms;
    return res;
}