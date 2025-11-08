class HotMovies extends Component {
    constructor(props) {
        super(props);
        this.state = {
            hotMovies: []
        };
        this.getHotMovies();
    }

    // 获取后端想看人数数据列表
    getHotMovies() {
        fetch('/statistics/movieLike/top', {method: 'GET'})
            .then(response => response.json())
            .then(res => this.setState({
                hotMovies: res.content
            }))
            .catch(error => alert(error));
    }

    renderDOM() {
        const el_html = `
            <div id="hot-movies-container">
                <div id="hot-movies-title">
                    近期最热
                </div>
                <div id="hot-movies-grid">
                </div>
            </div>
        `;
        this.el = createDOMFromString(el_html);
        this.hotMovies = this.el.querySelector("#hot-movies-grid");

        this.state.hotMovies.forEach(movie => {
            const child = createDOMFromString("<div></div>");
            mount(new MoviePoster({
                movie: movie,
                type: "购票"
            }), child);
            this.hotMovies.appendChild(child);
        });

        return this.el;
    }
}