class MovieRankItem extends Component {
    constructor(props) {
        super(props);

    }

    onItemClick() {
        window.location.href = "/user/movieDetail?id=" + this.props.movie.movieId;
    }

    renderDOM() {
        const el_html = `
            <div id="movie-rank-item-container">
                <div id="movie-rank-item-left">
                    <div id="movie-rank-item-index">${this.props.index}</div>
                    <div id="movie-rank-item-name">${this.props.movie.name}</div>
                </div>
                <div id="movie-rank-item-value">${this.props.movie.boxOffice}</div>
            </div>
        `;
        this.el = createDOMFromString(el_html);
        this.el.addEventListener('click', () => this.onItemClick());
        this.itemIndex = this.el.querySelector("#movie-rank-item-index");
        this.itemName = this.el.querySelector("#movie-rank-item-name");
        if (this.props.index <= 3) {
            this.itemIndex.classList.add("movie-rank-item-top", "movie-rank-item-top" + this.props.index);
            this.itemName.classList.add("movie-rank-item-top" + this.props.index);
        } else {
            this.itemIndex.classList.add("movie-rank-item-index-others");
            this.itemName.classList.add("movie-rank-item-name-others")
        }
        return this.el;
    }
}