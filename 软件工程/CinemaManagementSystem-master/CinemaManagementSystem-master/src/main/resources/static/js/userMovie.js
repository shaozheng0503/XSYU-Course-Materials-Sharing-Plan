$(document).ready(function () {
    mount(new UserHeader({active: -1}), document.querySelector("#nav-top-container"));

    getMovieList('');

    function getMovieList(keyword) {
        getRequest(
            '/movie/search?keyword=' + keyword,
            function (res) {
                renderMovieList(res.content);
            },
            function (error) {
                alert(error);
            });
    }

    function renderMovieList(list) {
        $('.movie-on-list').empty();
        // movieItem的点击事件，跳转到详情
        const onItemClick = movieId => window.location.href = "/user/movieDetail?id=" + movieId;

        list.forEach(movie => {
            movie.description = movie.description || '';
            const movieItem = createDOMFromString("<div></div>");
            mount(new MovieItem({movie: movie, onDetailClick: movieId => onItemClick(movieId)}), movieItem);
            $('.movie-on-list').append(movieItem);
        });
    }

    $('#search-btn').click(function () {
        getMovieList($('#search-input').val());
    })

});