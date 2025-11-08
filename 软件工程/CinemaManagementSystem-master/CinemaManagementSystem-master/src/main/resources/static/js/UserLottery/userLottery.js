class UserLottery extends Component {

    constructor(props) {
        super(props);

    }

    renderDOM() {
        const el_html = `
            <div id="user-lottery-name">67的幸运抽奖</div>
            <div id="user-lottery-category"></div>
            <div id="user-lottery-button"></div> 
        `;
        this.el = createDOMFromString(el_html);
        this.lotteryCategory = this.el.querySelector("#user-lottery-category");
        this.lotteryButton = this.el.querySelector("#user-lottery-button");
        mount(new LotteryCategory({}), this.lotteryCategory);
        mount(new LotteryButton({}), this.lotteryButton);

        return this.el
    }
}

$(document).ready(function () {
    mount(new UserHeader({}), document.querySelector("#nav-top-container"));
    mount(new UserLottery({}), document.querySelector("#user-lottery-container"));
})