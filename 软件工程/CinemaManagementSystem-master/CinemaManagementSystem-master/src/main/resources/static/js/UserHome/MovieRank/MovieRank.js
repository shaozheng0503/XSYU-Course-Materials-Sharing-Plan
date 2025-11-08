class MovieRank extends Component {

    constructor(props) {
        super(props);
        this.state = {
            hotMovies: []
        };
        this.getRanking();
    }

    // 从后端获取排行榜数据
    getRanking() {
        fetch(
            '/statistics/boxOffice/top10',
            {
                method: 'GET'
            })
            .then(response => response.json())
            .then(res => {
                this.setState({hotMovies: res.content})
            }).catch(error => alert(error))
    }

    renderDOM() {
        const el_html = `
            <div class="movie-rank-layout">
                <div id="movie-rank-title">票房排行</div>
                <div id="movie-rank-list"></div>
            </div>
        `;
        this.el = createDOMFromString(el_html);
        this.list = this.el.querySelector("#movie-rank-list");
        const hotMovies = this.state.hotMovies;
        if (hotMovies.length > 0) {
            for (let i = 0; i < hotMovies.length; i++) {
                const itemWrapper = createDOMFromString(`<div></div>`);
                mount(new MovieRankItem({index: i + 1, movie: hotMovies[i]}), itemWrapper);
                this.list.appendChild(itemWrapper);
            }
        }
        return this.el;
    }
}