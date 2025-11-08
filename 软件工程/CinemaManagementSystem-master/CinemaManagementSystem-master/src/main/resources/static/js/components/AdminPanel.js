class AdminPanel extends Component {
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
        confirm('确认要退出登录吗？') && postRequest('/logout', null, () => window.location.href = '/index');
    }

    renderDOM() {
        const el_html = `
            <div>
                <div class="nav-user-container" style="margin-bottom: 50px;">
                    <img class="avatar-lg" src="/images/defaultAvatar.jpg" />
                    <p class="title">${this.state.user.username}</p>
                </div>
                <ul class="nav nav-pills nav-stacked">
                    <li role="presentation"><a href="/admin/movie/manage"><i class="icon-film"></i> 电影管理</a></li>
                    <li role="presentation"><a href="/admin/session/manage"><i class="icon-calendar"></i> 排片管理</a></li>
                    <li role="presentation"><a href="/admin/promotion/manage"><i class="icon-gift"></i> 活动管理</a></li>
                    <li role="presentation"><a href="/admin/cinema/manage"><i class="icon-cogs"></i> 影院管理</a></li>
                    <li role="presentation"><a href="/admin/cinema/statistic"><i class="icon-bar-chart"></i> 影院统计</a></li>
                    <li role="presentation"><a href="/admin/refund/manage"><i class="icon-ticket"></i> 退票管理</a></li>
                    <li role="presentation"><a href="/admin/card/manage"><i class="icon-credit-card"></i> 会员卡管理</a></li>
                    <li role="presentation"><a href="/admin/coupon/present"><i class="icon-money"></i> 赠送优惠券</a></li>
                    <li role="presentation"><a href="/admin/role/manage"><i class="icon-user"></i> 影院角色</a></li>
                </ul>
            </div>  
        `;
        this.el = createDOMFromString(el_html);
        this.logoutButton = this.el.querySelector(".avatar-lg");
        this.panelItems = this.el.getElementsByTagName("li");
        this.panelItems[this.state.active].className = "active";

        this.logoutButton.addEventListener('click', () => this.onLogoutListener());

        // 权限展示
        if (this.state.user.auth != 2) {
            for (let i = this.panelItems.length - 1; i >= 0; i--) {
                if (i >= 2)
                    this.panelItems[i].remove();
                if (i === this.state.active)
                    this.panelItems[i].querySelector("a").href = "#";
            }
        }

        return this.el;
    }
}