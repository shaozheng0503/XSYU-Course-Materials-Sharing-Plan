class UserHeader extends Component {
    constructor(props) {
        super(props);
        this.state = {
            user: {
                username: sessionStorage.getItem('username'),
                id: sessionStorage.getItem('id'),
                auth: sessionStorage.getItem('auth')
            },
            active: this.props.active,
        }
    }

    // 退出登录响应事件
    onLogoutListener() {
        confirm('确认要退出登录吗？') && postRequest('/logout', null, res => window.location.href = '/index');
    }

    renderDOM() {
        const el_html = `
            <div id="user-header">
                <div class="nav-logo title">NJU-Se电影购票系统</div>
                <ul class="nav nav-pills">
                    <li role="presentation"><a href="/user/home">首页</a></li>
                    <li role="presentation"><a href="/user/movie">电影</a></li>
                    <li role="presentation"><a href="/user/lottery">抽奖</a></li>
                </ul>
                <div class="btn-group">
                    <button class="nav-user-container btn dropdown-toggle" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        <img class="avatar-sm" src="/images/defaultAvatar.jpg" />
                        <span class="gray-text" style="vertical-align: middle">${this.state.user.username}<span class="caret"></span></span>
                    </button>
                    <ul class="dropdown-menu">
                        <li><a href="/user/history/consume">消费记录</a></li>
                        <li><a href="/user/history/topup">充值记录</a></li>
                        <li><a href="/user/edit">修改密码</a></li>
                        <li><a href="/user/buy">电影票</a></li>
                        <li><a href="/user/member">卡包</a></li>
                        <li id="logout"><a href="#">登出</a></li>
                    </ul>
                </div>
            </div>
        `;
        this.el = createDOMFromString(el_html);
        this.logoutButton = this.el.querySelector("#logout");
        this.menuItems = this.el.querySelector("ul.dropdown-menu").getElementsByTagName("li");

        this.logoutButton.addEventListener('click', () => this.onLogoutListener());
        // 权限展示
        if (this.state.active >= 0)
            this.menuItems[this.state.active].querySelector("a").href = "#";

        return this.el;
    }

}