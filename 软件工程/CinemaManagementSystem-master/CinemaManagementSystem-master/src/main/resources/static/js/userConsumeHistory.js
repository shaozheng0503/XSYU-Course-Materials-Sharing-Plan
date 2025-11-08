$(document).ready(function () {
    mount(new UserHeader({active: 0}), document.querySelector("#nav-top-container"));

    getBriefConsumeHisList();

});

function getBriefConsumeHisList() {
    getRequest(
      '/history/consume/get?userId='+sessionStorage.getItem("id"),
      function (res) {
          if (res.success) {
              renderConsumeHisList(res.content);
          } else {
              alert(res.message);
          }
      },
      function (error) {
          alert(JSON.stringify(error));
      }
    );
}

function renderConsumeHisList(histories) {
    if (!histories || histories.length == 0) {
        $('#no-his-caption').attr("style","visibility: visible");
        return;
    }
    listContent = '';
    for (let his of histories) {
        listContent +=
            "<div class='his-item' onclick='toDetail(\""+his.id+"\")'>" +
                "<div class='consumehis-wrapper-one'>" +
                    "<div class='consumehis-type'><b>" + his.type + "</b></div>" +
                    "<div class='consumehis-time'><span>消费时间:" + formateTime(his.time) + "</span></div>" +
                "</div>" +
                "<div class='consumehis-money'><b>￥" + his.money + "</b></div>" +
            "</div>";
    }
    $('#consumehis-list').html(listContent);
}

function toDetail(id) {
    window.location.href = "/user/history/consume/detail?id="+id;
}

function formateTime(time) {
    var res = '';
    var date = time.split('T')[0];
    var hms = time.split('T')[1].split('.')[0];
    res = res + date + ' ' + hms;
    return res;
}