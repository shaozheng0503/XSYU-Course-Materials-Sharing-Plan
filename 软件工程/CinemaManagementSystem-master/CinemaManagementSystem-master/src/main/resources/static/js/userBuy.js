const ticketState = {'0': '未支付', '1': '已完成', '2': '已失效', '3':'已出票', '4':'已退票'};

$(document).ready(function () {
    mount(new UserHeader({active: 3}), document.querySelector("#nav-top-container"));

    getMovieList();

    function getMovieList() {
        getRequest(
            '/ticket/get/' + sessionStorage.getItem('id'),
            function (res) {
                renderTicketList(res.content);
            },
            function (error) {
                alert(error);
            });
    }

    /**
     * @Date:   2019-5-5
     * @Author: hxw
     * @Info:   渲染购票记录
     */
    function renderTicketList(list) {
        var ticketListContent = '';
        for (let ticket of list) {
            ticketListContent +=
                '<tr>' +
                '<td>' + ticket.movieName + '</td>' +
                '<td>' + ticket.hallName + '</td>' +
                '<td>' + (ticket.rowIndex + 1) + '排' + (ticket.columnIndex + 1) + '座' + '</td>' +
                '<td>' + formateTime(ticket.startTime) + '</td>' +
                '<td>' + formateTime(ticket.endTime) + '</td>' +
                '<td>' + formateTime(ticket.time) + '</td>' +
                '<td>' + ticketState[ticket.state] + '</td>' ;
            switch(ticket.state){
                case 0:
                    url="/user/movieDetail/buy?id="+ticket.movieId+"&scheduleId="+ticket.scheduleId
                    ticketListContent +=
                                    '<td><a class="pay-ticket" id=p'+ticket.id+' href='+url+'>支付</a><tb>'+
                                    '</tr>';
                    break;
                case 1:
                    ticketListContent +=
                                    '<td><a class="issue-ticket" id=i'+ticket.id+'>出票</a>/<a class="refund-ticket" id=r'+ticket.id+'>退票</a><tb>'+
                                    '</tr>';
                    break;
                case 2:
                    ticketListContent +=
                                    '<td><a class="delete-ticket" id=d'+ticket.id+'>删除</a><tb>'+
                                    '</tr>';
                    break;
                case 3:
                    ticketListContent +=
                                    '<td><a class="delete-ticket" id=d'+ticket.id+'>删除</a><tb>'+
                                    '</tr>';
                    break;
                case 4:
                    ticketListContent +=
                                    '<td><a class="delete-ticket" id=d'+ticket.id+'>删除</a><tb>'+
                                    '</tr>';
                    break;
                default:
                    ticketListContent +='<td>error<tb>'+'</tr>';
            }

        }
        $('#ticket-list').html(ticketListContent);
    }

    /**
     * @Date:   2019-5-5
     * @Author: hxw
     * @Info:   格式化时间
     */
    function formateTime(time) {
        var res = '';
        var date = time.split('T')[0];
        var hms = time.split('T')[1].split('.')[0];
        res = res + date + ' ' + hms;
        return res;
    }

    $("#ticket-list").on('click','.refund-ticket',function(){
        var bodyContent='';
        syncGetRequest(
            '/ticket/getTicketRefund?ticketId='+$(this).attr('id').substring(1),
            function (res) {
                if (res.success){
                    var discount=parseInt(res.content);
                    if (discount===100) {
                        bodyContent='现在退票将全款返还支付金额，请确认是否退票。';
                    }else if (discount===0){
                        bodyContent='现在退票不返还支付金额，请确认是否退票。';
                    }else{
                        bodyContent='现在退款将返还支付金额的'+discount+'%，请确认是否退票。';
                    }
                }
            }
        );


        var r=confirm(bodyContent);
        if (r){
            console.log($(this).attr('id').substring(1));
            getRequest(
                '/ticket/delete?ticketId='+$(this).attr('id').substring(1),
                function(res){
                    getMovieList();
                },
                function(error){
                    alert(error);
                }
            );
        }

    });

    $("#ticket-list").on('click','.issue-ticket',function(){
        var r=confirm("出票后将不可退票，请确认是否出票。");
        if (r){
            console.log($(this).attr('id').substring(1)),
                getRequest(
                    '/ticket/issue?ticketId='+$(this).attr('id').substring(1),
                    function(res){
                        getMovieList();
                    },
                    function(error){
                        alert(error);
                    }
                );
        }
    })

    $("#ticket-list").on('click','.delete-ticket',function(){
        console.log($(this).attr('id').substring(1)),
                getRequest(
                    '/ticket/delete?ticketId='+$(this).attr('id').substring(1),
                    function(res){
                        getMovieList();
                    },
                    function(error){
                        alert(error);
                    }
                );
    })

});