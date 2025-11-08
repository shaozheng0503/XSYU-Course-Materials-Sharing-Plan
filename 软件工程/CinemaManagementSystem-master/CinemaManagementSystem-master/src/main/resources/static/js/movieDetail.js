const AUTH_AUDIENCE = 0;
const AUTH_ADMIN = 1;
const AUTH_MANAGER = 2;

$(document).ready(function () {
    if (document.querySelector(".nav-left-container"))
        mount(new AdminPanel({active: 0}), document.querySelector(".nav-left-container"));

    var movieId = parseInt(window.location.href.split('?')[1].split('&')[0].split('=')[1]);
    var userId = sessionStorage.getItem('id');
    var isLike = false;
    var movieData;

    getMovie();
    //更新权限格式
    if (sessionStorage.getItem('auth') >= AUTH_ADMIN)
        getMovieLikeChart();

    function getMovieLikeChart() {
        getRequest(
            '/movie/' + movieId + '/like/date',
            function (res) {
                var data = res.content,
                    dateArray = [],
                    numberArray = [];
                data.forEach(function (item) {
                    dateArray.push(item.likeTime);
                    numberArray.push(item.likeNum);
                });

                var myChart = echarts.init($("#like-date-chart")[0]);

                // 指定图表的配置项和数据
                var option = {
                    title: {
                        text: '想看人数变化表'
                    },
                    xAxis: {
                        type: 'category',
                        data: dateArray
                    },
                    yAxis: {
                        type: 'value'
                    },
                    series: [{
                        data: numberArray,
                        type: 'line'
                    }]
                };

                // 使用刚指定的配置项和数据显示图表。
                myChart.setOption(option);
            },
            function (error) {
                alert(error);
            }
        );
    }

    function getMovie() {
        getRequest(
            '/movie/' + movieId + '/' + userId,
            function (res) {
                var data = res.content;
                isLike = data.islike;
                repaintMovieDetail(data);
                movieData=data;
            },
            function (error) {
                alert(error);
            }
        );
    }

    function repaintMovieDetail(movie) {
        !isLike ? $('.icon-heart').removeClass('error-text') : $('.icon-heart').addClass('error-text');
        $('#like-btn span').text(isLike ? ' 已想看' : ' 想 看');
        $('#movie-img').attr('src', movie.posterUrl);
        $('#movie-name').text(movie.name);
        $('#order-movie-name').text(movie.name);
        $('#movie-description').text(movie.description);
        $('#movie-startDate').text(new Date(movie.startDate).toLocaleDateString());
        $('#movie-type').text(movie.type);
        $('#movie-country').text(movie.country);
        $('#movie-language').text(movie.language);
        $('#movie-director').text(movie.director);
        $('#movie-starring').text(movie.starring);
        $('#movie-writer').text(movie.screenWriter);
        $('#movie-length').text(movie.length);
    }

    // user界面才有
    $('#like-btn').click(function () {
        var url = isLike ? '/movie/' + movieId + '/unlike?userId=' + userId : '/movie/' + movieId + '/like?userId=' + userId;
        postRequest(
            url,
            null,
            function (res) {
                isLike = !isLike;
                getMovie();
            },
            function (error) {
                alert(error);
            });
    });

    /**
     * @Date:   2019-5-7
     * @Author: hxw
     * @Info:   获取电影信息表单
     */
    function getMovieForm() {
        return {
            id: window.location.href.split("=")[1],
            name: $('#movie-name-input').val(),
            startDate: $('#movie-date-input').val(),
            posterUrl: $('#movie-img-input').val(),
            description: $('#movie-description-input').val(),
            type: $('#movie-type-input').val(),
            length: $('#movie-length-input').val(),
            country: $('#movie-country-input').val(),
            starring: $('#movie-star-input').val(),
            director: $('#movie-director-input').val(),
            screenWriter: $('#movie-writer-input').val(),
            language: $('#movie-language-input').val()
        };
    }

    /**
     * @Date: 2019-6-11
     * @Author:  cz
     * @Info:   设置表单
     */
    $('#modify-btn').on('click',function () {
        //展示现有的电影信息
        $('#movie-name-input').attr('value',movieData.name);
        $('#movie-date-input').attr('value',movieData.startDate);
        $('#movie-img-input').attr('value',movieData.posterUrl);
        $('#movie-description-input').html(movieData.description);
        $('#movie-type-input').attr('value',movieData.type);
        $('#movie-length-input').attr('value',movieData.length);
        $('#movie-country-input').attr('value',movieData.country);
        $('#movie-director-input').attr('value',movieData.director);
        $('#movie-star-input').attr('value',movieData.starring);
        $('#movie-writer-input').attr('value',movieData.screenWriter);
        $('#movie-language-input').attr('value',movieData.language);
    });

    /**
     * @Date:   2019-5-7
     * @Author: hxw
     * @Info:   修改电影信息
     */
    $("#movie-form-btn").click(function () {
        //获取更新后的电影信息
        var formData = getMovieForm();
        if (!validateMovieForm(formData)) {
            return;
        }
        postRequest(
            '/movie/update',
            formData,
            function (res) {
                if (res.success) {
                    alert("修改成功!");
                    $("#movieModal").modal('hide');
                } else {
                    alert(res.message);
                }
            },
            function (error) {
                alert(error);
            });
    });

    /**
     * @Date:   2019-5-7
     * @Author: hxw
     * @Info:   下架电影
     */
    $("#commitMovieDel").click(function () {
        var list = [];
        list.push(window.location.href.split("=")[1]);
        postRequest(
            '/movie/off/batch',
            {movieIdList: list},
            function (res) {
                if (res.success) {
                    alert("下架成功!");
                    $("#movieDelModal").modal('hide');
                } else {
                    alert(res.message);
                }
            },
            function (error) {
                alert(error);
            }
        )
    });

    /**
     * @Date:   2019-5-7
     * @Author: hxw
     * @Info:   检验表单信息完整性
     */
    function validateMovieForm(data) {
        if (!data.name) {
            $('#movie-name-input').parent('.form-group').addClass('has-error');
            alert("请填写电影名称!");
            return false;
        }
        if (!data.posterUrl) {
            $('#movie-img-input').parent('.form-group').addClass('has-error');
            alert("请填写电影海报!");
            return false;
        }
        if (!data.startDate) {
            $('#movie-date-input').parent('.form-group').addClass('has-error');
            alert("请填写上映时间!");
            return false;
        }
        if (!data.length) {
            $('#movie-length-input').parent('.form-group').addClass('has-error');
            alert("请填写片长!");
            return false;
        }
        return true;
    }

});