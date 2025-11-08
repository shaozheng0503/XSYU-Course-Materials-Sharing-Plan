$(document).ready(function() {
    mount(new AdminPanel({active: 4}), document.querySelector(".nav-left-container"));

    getScheduleRate();
    
    getBoxOffice();

    getAudiencePrice();

    getPlacingRate(getFormatToday());

    getPopularMovie(7,3);
    
    $('#place-rate-container').click(function () {
        $('#placingModal').modal("toggle");
    })

    $('#popular-movie-container').click(function () {
        $('#recentPopModal').modal("toggle");
    })

    $('#placing-btn').click(function () {
        var date = $('#placing-date-input').val();
        if (!date) {
            alert("请输入查看日期");
            return;
        }
        getPlacingRate(formatTime(date));
        $('#placingModal').modal("hide");
    })

    $('#recent-pop-btn').click(function () {
        var form = getRecentPopForm();
        if (!validateRecentPopForm(form)) {
            return;
        }
        getPopularMovie(form.days,form.movieNum);
        $('#recentPopModal').modal("hide");
    });

    function getScheduleRate() {

        getRequest(
            '/statistics/scheduleRate',
            function (res) {
                var data = res.content||[];
                var tableData = data.map(function (item) {
                   return {
                       value: item.time,
                       name: item.name
                   };
                });
                var nameList = data.map(function (item) {
                    return item.name;
                });
                var option = {
                    title : {
                        text: '今日排片率',
                        subtext: new Date().toLocaleDateString(),
                        x:'center'
                    },
                    tooltip : {
                        trigger: 'item',
                        formatter: "{a} <br/>{b} : {c} ({d}%)"
                    },
                    legend: {
                        x : 'center',
                        y : 'bottom',
                        data:nameList
                    },
                    calculable : true,
                    series : [
                        {
                            name:'各电影排片率',
                            type:'pie',
                            radius : [30, 110],
                            data:tableData
                        }
                    ]
                };
                var scheduleRateChart = echarts.init($("#schedule-rate-container")[0]);
                scheduleRateChart.setOption(option);
            },
            function (error) {
                alert(JSON.stringify(error));
            }
        );
    }

    function getBoxOffice() {

        getRequest(
            '/statistics/boxOffice/total',
            function (res) {
                var data = res.content || [];
                var tableData = data.map(function (item) {
                    return item.boxOffice;
                });
                var nameList = data.map(function (item) {
                    return item.name;
                });
                var option = {
                    title : {
                        text: '所有电影票房',
                        subtext: '截止至'+new Date().toLocaleDateString(),
                        x:'center'
                    },
                    xAxis: {
                        type: 'category',
                        axisLabel: {
                            interval: 0
                        },
                        data: nameList
                    },
                    yAxis: {
                        type: 'value'
                    },
                    series: [{
                        data: tableData,
                        type: 'bar'
                    }]
                };
                var scheduleRateChart = echarts.init($("#box-office-container")[0]);
                scheduleRateChart.setOption(option);
            },
            function (error) {
                alert(JSON.stringify(error));
            });
    }

    function getAudiencePrice() {
        getRequest(
            '/statistics/audience/price',
            function (res) {
                var data = res.content || [];
                var tableData = data.map(function (item) {
                    return item.price;
                });
                var nameList = data.map(function (item) {
                    return formatDate(new Date(item.date));
                });
                var option = {
                    title : {
                        text: '每日客单价',
                        x:'center'
                    },
                    xAxis: {
                        type: 'category',
                        data: nameList
                    },
                    yAxis: {
                        type: 'value'
                    },
                    series: [{
                        data: tableData,
                        type: 'line'
                    }]
                };
                var scheduleRateChart = echarts.init($("#audience-price-container")[0]);
                scheduleRateChart.setOption(option);
            },
            function (error) {
                alert(JSON.stringify(error));
            });
    }

    /**
     * @Date:   2019-5-14
     * @Author: hxw
     * @Info:   展示指定日期上座率
     */
    function getPlacingRate(date) {
        getRequest(
            "/statistics/PlacingRate?date="+date,
            function (res) {
                var data = res.content || [];
                var tableData = data.map(function (item) {
                    return item.placingRate*100;
                });
                var nameList = data.map(function (item) {
                    return item.name;
                });
                var option = {
                    title : {
                        text: date + '上座率',
                        x:'center'
                    },
                    xAxis: {
                        type: 'category',
                        axisLabel: {
                            interval: 0
                        },
                        data: nameList
                    },
                    yAxis: {
                        type: 'value',
                        axisLabel: {
                            formatter: '{value} %'
                        }
                    },
                    series: [{
                        data: tableData,
                        type: 'bar',
                        barMaxWidth: '100'
                    }]
                };
                var scheduleRateChart = echarts.init($("#place-rate-container")[0]);
                scheduleRateChart.setOption(option);
            },
            function (error) {
                alert(JSON.stringify(error));
            }
        );
    }

    /**
     * @Date:   2019-4-30
     * @Des:    显示最受欢迎的某几部电影
     * @Author: hxw
     */
    function getPopularMovie(days,movieNum) {
        getRequest(
            '/statistics/popular/movie?days='+days+'&movieNum='+movieNum,
            function (res) {
                if (res.success) {
                    var data = res.content;
                    if (data.length == 0) {
                        $("#popular-movie-container").html("<p>近"+days+"天没有票房TAT</p>");
                        return;
                    }
                    var tableData = data.map(function (item) {
                        return {
                            value: item.boxOffice,
                            name: item.name
                        };
                    });
                    var nameList = data.map(function (item) {
                        return item.name;
                    });
                    var option = {
                        title : {
                            text: '近'+days+'天最受欢迎的'+movieNum+'部电影',
                            x:'center'
                        },
                        tooltip : {
                            trigger: 'item',
                            formatter: "{a} <br/>{b} : {c} ({d}%)"
                        },
                        legend: {
                            x: 'center',
                            y: 'bottom',
                            data: nameList
                        },
                        calculable : true,
                        series: [{
                            name:'最受欢迎电影票房',
                            type:'pie',
                            radius: [30, 110],
                            data: tableData
                        }]
                    };
                    var scheduleRateChart = echarts.init($("#popular-movie-container")[0]);
                    scheduleRateChart.setOption(option);
                } else {
                    alert(res.message);
                }
            },
            function (error) {
                alert(JSON.stringify(error));
            });
    }

    /**
     * @Date:   2019-5-14
     * @Author: hxw
     * @Info:   获取修改最受欢迎电影参数的表单
     */
    function getRecentPopForm() {
        return {
            days: $('#days-input').val(),
            movieNum: $('#movie-num-input').val()
        }
    }

    /**
     * @Date:   2019-5-14
     * @Author: hxw
     * @Info:   检验最受欢迎电影表单信息
     */
    function validateRecentPopForm(form) {
        if (!form.days) {
            alert("请输入查看天数！");
            return false;
        }
        if (!form.movieNum) {
            alert("请输入电影数目!");
            return false;
        }
        return true;
    }

    /**
     * @Date:   2019-5-14
     * @Author: hxw
     * @Info:   获取yyyy/MM/dd格式的当前日期
     */
    function getFormatToday() {
        var date = new Date();
        var sep = "/";
        var year = date.getFullYear();
        var month = date.getMonth() + 1;
        var strDate = date.getDate();
        if (month >= 1 && month <= 9) {
            month = "0" + month;
        }
        if (strDate >= 0 && strDate <= 9) {
            strDate = "0" + strDate;
        }
        var res = year + sep + month + sep + strDate;
        return res;
    }

    function formatTime(time) {
        return time.split("-")[0]+"/"+time.split("-")[1]+"/"+time.split("-")[2];
    }
});