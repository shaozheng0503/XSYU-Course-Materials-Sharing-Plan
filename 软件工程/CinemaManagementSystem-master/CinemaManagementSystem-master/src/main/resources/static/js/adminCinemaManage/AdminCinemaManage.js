$(document).ready(function () {
    mount(new AdminCinemaManage({}), document.querySelector("#body"))
});

class AdminCinemaManage extends Component {
    constructor(props) {
        super(props);
        // viewTab =>  -1:常规设置  0:查看    1:添加    2:修改    3:删除
        this.state = {
            viewTab: 0,
        };
    }

    onViewChange(viewType) {
        this.setState(this.setState({
            viewTab: viewType
        }));
    }

    renderDOM() {
        const el_html = `
            <div class="main-container">
                <div class="nav-left-container"></div>
                <div class="content-container" id="hall-manage-wrapper">
                    <div id="hall-manage-navi-wrapper"></div>
                    <div class="content-card card" id="hall-card"></div>
                </div>
            </div>
        `;
        this.el = createDOMFromString(el_html);

        this.panel = this.el.querySelector(".nav-left-container");
        this.mainContainer = this.el.querySelector(".main-container");
        this.hallManageNaviWrapper = this.el.querySelector("#hall-manage-navi-wrapper");
        this.hallWrapper = this.el.querySelector("#hall-card");

        this.mainContainer.style.height = document.documentElement.clientHeight+"px";

        mount(new AdminPanel({active: 3}), this.panel);
        mount(new HallManageNavi({
            activeView: this.state.viewTab,
            onViewChange: (viewType) => this.onViewChange(viewType)
        }), this.hallManageNaviWrapper);

        switch (this.state.viewTab) {
            case -1:
                mount(new CanSeeDay({}), this.hallWrapper);
                break;
            case 0:
                mount(new ViewHall({}), this.hallWrapper);
                break;
            case 1:
                mount(new AddHall({seatRow: 10, seatColumn: 22}), this.hallWrapper);
                break;
            case 2:
                mount(new ModifyHall({}), this.hallWrapper);
                break;
            case 3:
                mount(new RemoveHall({}), this.hallWrapper);
                break;
        }

        return this.el;
    }
}