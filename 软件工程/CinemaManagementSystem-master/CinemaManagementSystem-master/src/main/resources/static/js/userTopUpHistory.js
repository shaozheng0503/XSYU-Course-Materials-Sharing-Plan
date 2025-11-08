$(document).ready(function () {
    mount(new UserHeader({active: 1}), document.querySelector("#nav-top-container"));

    getTopUpHistoryList();

});

function getTopUpHistoryList() {
    getRequest(
      '/history/topup/get?userId='+sessionStorage.getItem('id'),
      function (res) {
          if (res.success) {
              renderTopUpHistory(res.content);
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
 * 渲染充值记录列表
 * @author hxw
 * @date 2019-5-21
 * @param histories
 */
function renderTopUpHistory(histories) {
    if (!histories || histories.length == 0) {
        $('#topup-his-list').attr("style","visibility: hidden");
        $('#no-his-caption').attr("style","visibility: visible");
        return;
    }
    var accordionContent = '';
    for (let his of histories) {
        accordionContent += getPanelHTML(his);
    }
    $('#topup-his-list').html(accordionContent);
}

/**
 * 获取充值记录子项HTML代码
 * @author hxw
 * @date 2019-6-10
 * @param his
 * @return
 */
function getPanelHTML(his) {
    return "<tr>" +
                "<td>" + his.money + "</td>" +
                "<td>" + his.discount + "</td>" +
                "<td>" + formateTime(his.time) + "</td>" +
                "<td>" + his.balance + "</td>" +
           "</tr>";
}

function formateTime(time) {
    var res = '';
    var date = time.split('T')[0];
    var hms = time.split('T')[1].split('.')[0];
    res = res + date + ' ' + hms;
    return res;
}