class MovieItem extends Component {
    constructor(props) {
        super(props);
    }

    // 点击事件
    onDetailClick() {
        this.props.onDetailClick(this.props.movie.id);
    }

    renderDOM() {
        const el_html = `
        <div class='movie-item card' id="movie-item-container">
            <img class='movie-img' src='${this.props.movie.posterUrl || "../images/defaultAvatar.jpg"}'/>
            <div class='movie-info'>
                <div class='movie-title'>
                    <span class='primary-text'>${this.props.movie.name}</span>
                    <span class='label ${!this.props.movie.status ? 'primary-bg' : 'error-bg'}'>
                        ${this.props.movie.status ? '已下架' : (new Date(this.props.movie.startDate) >= new Date() ? '未上映' : '热映中')} 
                    </span>
                    <span class='movie-want'>
                        <i class='icon-heart error-text'></i> ${this.props.movie.likeCount || 0} 人想看
                    </span>
                </div>
                <div class='movie-description dark-text'><span> ${this.props.movie.description} </span></div>
                <div>类型： ${this.props.movie.type} </div>
                <div style='display: flex'>
                    <span>导演： ${this.props.movie.director} </span>
                    <span style='margin-left: 30px;'>主演： ${this.props.movie.starring} </span>
                    <div class='movie-operation'><a href='#'>详情</a></div>
                </div>
            </div>
        </div>
        `;
        this.el = createDOMFromString(el_html);
        this.movieOperation = this.el.querySelector(".movie-operation");
        this.movieOperation.addEventListener('click', () => this.onDetailClick());
        this.el.addEventListener('click', () => this.onDetailClick());

        return this.el;
    }
}