$(document).ready(function () {
    mount(new AdminPanel({active: 0}), document.querySelector(".nav-left-container"));

    getMovieList();

    $("#movie-form-btn").click(function () {
        var formData = getMovieForm();
        if (!validateMovieForm(formData)) {
            return;
        }
        postRequest(
            '/movie/add',
            formData,
            function (res) {
                getMovieList();
                $("#movieModal").modal('hide');
            },
            function (error) {
                alert(error);
            });
    });

    function getMovieForm() {
        return {
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

    function getMovieList() {
        getRequest(
            '/movie/all',
            function (res) {
                renderMovieList(res.content);
            },
            function (error) {
                alert(error);
            }
        );
    }

    function renderMovieList(list) {
        $('.movie-on-list').empty();
        // movieItem的点击事件，跳转到详情
        const onItemClick = movieId => window.location.href = "/admin/movieDetail?id=" + movieId;

        list.forEach(movie => {
            movie.description = movie.description || '';
            const movieItem = createDOMFromString(`<div></div>`);
            mount(new MovieItem({movie: movie, onDetailClick: movieId => onItemClick(movieId)}), movieItem);
            $('.movie-on-list').append(movieItem);
        });
    }

    /**
     * @Date:   2019-5-7
     * @Author: hxw
     * @Intro:  检验表单信息完整性
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