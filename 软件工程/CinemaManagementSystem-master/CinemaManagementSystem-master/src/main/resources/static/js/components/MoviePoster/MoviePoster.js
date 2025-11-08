class MoviePoster extends Component {
    constructor(props) {
        super(props);

    }

    // 电影海报点击事件
    onPosterClick() {
        window.location.href = "/user/movieDetail?id=" + this.props.movie.movieId;
    }

    renderDOM() {
        const el_html = `
            <div id="movie-poster-container">
                <div id="movie-poster-content">
                    <img class='movie-img' src='${this.props.movie.posterUrl || "../images/defaultAvatar.jpg"}'/>
                    <div id="movie-poster-summary">
                        <div id="movie-name">${this.props.movie.name}</div>
                        <div id="movie-like-count">${this.props.movie.likeCount || 0}人想看</div>
                    </div>
                </div>
                <div id="movie-sales">
                    ${this.props.type}
                </div>
            </div>
        `;
        this.el = createDOMFromString(el_html);
        this.el.addEventListener('click', () => this.onPosterClick());
        return this.el;
    }
}