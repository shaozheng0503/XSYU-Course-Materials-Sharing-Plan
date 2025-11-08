/**
* @Date: 2019-5-18
* @Author: cz
* @Intro: 退票策略管理
*/

$(document).ready(function(){
    mount(new AdminPanel({active: 5}), document.querySelector(".nav-left-container"));

    var movieList;
    var selectedMovieIds = new Set();
    var selectedMovieNames = new Set();

    getAllMovies();

    getRefundList();

    function getRefundList(){
        getRequest(
            '/refund/all',
            function(res){
                renderRefundList(res.content);
            },
            function(error){
                alert(error);
             }
        );
    }

    function getAllMovies() {
            getRequest(
                '/movie/all/exclude/off',
                function (res) {
                    movieList = res.content;
                    $('#refund-movie-input').append("<option value="+ -1 +">所有电影</option>");
                    movieList.forEach(function (movie) {
                        $('#refund-movie-input').append("<option value="+ movie.id +">"+movie.name+"</option>");
                    });
                },
                function (error) {
                    alert(error);
                }
            );
        }

    function renderRefundList(list){
        $(".refund-on-list").empty();
        var refundDomStr="";

        list.forEach(function(refund){
            refundDomStr +=
                "<li class='refund-item card'>"+
                "   <div class='refund-movie' id="+refund.movie.id+">电影："+refund.movie.name+"</div>"+
                "   <div class='refund-time'>时间：电影开场前"+refund.time+"天</div>"+
                "   <div class='refund-price'>价格折算：按实际支付金额的"+refund.price+"%返还</div>"+
                "   <div class='new-div'>"+
                "       <button class='btn btn-primary change-btn' id='"+refund.id+"change'>修改</button>"+
                "       <button class='btn btn-primary delete-btn' id='"+refund.id+"delete'>删除</button>"+
                "   </div>"+
                "</li>"
        });
        $(".refund-on-list").append(refundDomStr);
    }

     $("#refundModal").on('click','#refund-form-btn',function(){
     console.log("refund-form-btn");
        addRefund();
     });


    $('#refund-movie-input').change(function(){
        var movieId = $('#refund-movie-input').val();
        var movieName = $('#refund-movie-input').children('option:selected').text();

        if(movieId==-1){
            movieList.forEach(function(movie){
                selectedMovieIds.add(movie.id);
                selectedMovieNames.add(movie.name);
            });
        }else{
            selectedMovieIds.add(movieId);
            selectedMovieNames.add(movieName);
        }
        renderSelectedMovies();
    });

    //渲染选择的参加活动的电影
    function renderSelectedMovies() {
        $('#selected-movies').empty();
        var moviesDomStr = "";
        selectedMovieNames.forEach(function (movieName) {
            moviesDomStr += "<span class='label label-primary'>"+movieName+"</span>";
        });
        $('#selected-movies').append(moviesDomStr);
    }

    //修改退票策略
    $(".refund-on-list").on('click','button[id$="change"]',function() {
        refundId=$(this).attr('id').substring(0,$(this).attr('id').indexOf('change'));
        changeRefund(refundId);
        $("#refundModal").modal('show');
    });

    $(".refund-on-list").on('click','button[id$="delete"]',function() {
        console.log($(this).attr('id').substring(0,$(this).attr('id').indexOf('delete')));
        getRequest(
            '/refund/delete?refundId='+$(this).attr('id').substring(0,$(this).attr('id').indexOf('delete')),
            function(res){
                getRefundList();
            },
            function(error){
                alert(error);
            }
        );
        getRefundList();
    });

    function changeRefund(refundId){
    getRequest(
        '/refund/get?refundId='+refundId,
        function(res){
            $("#refundModalLabel").text("修改退票策略");
            console.log(res.content+"2222222")
            $('#selected-movies').empty();
            $('#selected-movies').append("<span class='label label-primary'>"+res.content.movie.name+"</span>");
            selectedMovieIds.add(res.content.movie.id);
            selectedMovieNames.add(res.content.movie.name);
            $("#refund-time-input").attr('value',res.content.time);
            $("#refund-price-input").attr('value',res.content.price);
            $("#refund-form-btn").attr('id',"refund-form-btn-change");
        },
        function(error){
            alert(error);
        }
    );
    }

    $("#refundModal").on('click','#refund-form-btn-change',function(){
        console.log("refund-form-btn-change");
        //新增
        addRefund();

        //删除
        getRequest(
            '/refund/delete?refundId='+refundId,
            function(res){
                getRefundList();
            },
            function(error){
                alert(error);
            }
        );
    });

    function getRefundForm(){
        return {
            time: $('#refund-time-input').val(),
            price: $('#refund-price-input').val(),
            movieList: [...selectedMovieIds]
        };
    }

    /**
     * 检查数据
     * @param formData
     * @returns {boolean}
     */
    function validateRefundForm(formData){
        if(!formData.time) {
            $('#refund-time-input').parent('.form-group').addClass('has-error');
            alert("请填写适用退票时间!");
            return false;
        }
        if(!formData.price) {
            $('#refund-price-input').parent('.form-group').addClass('has-error');
            alert("请填写折算百分比!");
            return false;
        }
        if(formData.time<=0){
            $('#refund-price-input').parent('.form-group').addClass('has-error');
            alert("退票日期需大于0天!");
            return false;
        }
        if(formData.price<0 || formData.price>100){
            $('#refund-price-input').parent('.form-group').addClass('has-error');
            alert("价格折算必须在0-100之间!");
            return false;
        }
        return true;
    }

    function addRefund(){
        var formData = getRefundForm();
        if(!validateRefundForm(formData)){
            return;
        }
        postRequest(
            '/refund/add',
            formData,
            function (res){
                getRefundList();
                $("#refundModal").modal('hide');
                $("#refundModalLabel").text("添加退票策略");
                $('#selected-movies').empty();
                $("#refund-time-input").attr("value","");
                $("#refund-price-input").attr("value","");
                $("#refund-form-btn-change").attr('id',"refund-form-btn");
                selectedMovieNames.clear();
                selectedMovieIds.clear();
            },
            function (error){
                alert(error);
            }
        );
    }
});

