class ComingMovies extends Component {
    constructor(props) {
        super(props);
        this.state = {
            comingMovies: []
        };
        this.getComingMovies();
    }

    // 从后端获取即将上映电影列表
    getComingMovies() {
        fetch('/movie/coming/top', {method: 'GET'})
            .then(response => response.json())
            .then(res => this.setState({
                comingMovies: res.content
            }))
            .catch(error => alert(error));
    }

    renderDOM() {
        const el_html = `
            <div id="coming-movies-container">
                <div id="coming-movies-title">
                    即将上映
                </div>
                <div id="coming-movies-grid">
                </div>
            </div>
        `;
        this.el = createDOMFromString(el_html);
        this.comingMovies = this.el.querySelector("#coming-movies-grid");

        this.state.comingMovies.forEach(movie => {
            const child = createDOMFromString("<div></div>");
            mount(new MoviePoster({
                movie: movie,
                type: "详情"
            }), child);
            this.comingMovies.appendChild(child);
        });

        return this.el;
    }
}