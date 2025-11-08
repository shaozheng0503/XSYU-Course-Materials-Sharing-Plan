class HallManageNavi extends Component {
    constructor(props) {
        super(props);
        this.state = {
            viewType: this.props.activeView
        };
    }

    // navi-custom的点击事件
    onCustomClick() {
        this.setState({
            viewType: -1
        });
        this.props.onViewChange(-1);
    }

    // navi-view的点击事件
    onViewClick() {
        this.setState({
            viewType: 0
        });
        this.props.onViewChange(0);
    }

    // navi-add的点击事件
    onAddClick() {
        this.setState({
            viewType: 1
        });
        this.props.onViewChange(1);
    }

    // navi-modify的点击事件
    onModifyClick() {
        this.setState({
            viewType: 2
        });
        this.props.onViewChange(2);
    }

    // navi-delete的点击事件
    onDeleteClick() {
        this.setState({
            viewType: 3
        });
        this.props.onViewChange(3);
    }


    renderDOM() {
        const el_html = `
            <div class="hall-manage-container">
                <div class="hall-manage-title">
                    <span class="title gray-text" id="hall-manage-title">影厅管理</span>
                </div>
                <div class="hall-manage-navi">
                    <div class="hall-manage-navi-item" id="hall-manage-navi-custom">
                        <button class="btn btn-primary">常规</button>
                    </div>
                    <div class="hall-manage-navi-item" id="hall-manage-navi-view">
                        <button class="btn btn-primary">查看</button>
                    </div>
                    <div class="hall-manage-navi-item" id="hall-manage-navi-add">
                        <button class="btn btn-primary">添加</button>
                    </div>
                    <div class="hall-manage-navi-item" id="hall-manage-navi-modify">
                        <button class="btn btn-primary">修改</button>
                    </div>
                    <div class="hall-manage-navi-item" id="hall-manage-navi-delete">
                        <button class="btn btn-primary">删除</button> 
                    </div>
                </div>
                
            </div>
        `;
        this.el = createDOMFromString(el_html);
        this.naviCustom = this.el.querySelector('#hall-manage-navi-custom');
        this.naviView = this.el.querySelector('#hall-manage-navi-view');
        this.naviAdd = this.el.querySelector('#hall-manage-navi-add');
        this.naviModify = this.el.querySelector('#hall-manage-navi-modify');
        this.naviDelete = this.el.querySelector('#hall-manage-navi-delete');
        this.naviCustom.addEventListener('click', ()=>this.onCustomClick());
        this.naviView.addEventListener('click', () => this.onViewClick());
        this.naviAdd.addEventListener('click', () => this.onAddClick());
        this.naviModify.addEventListener('click', () => this.onModifyClick());
        this.naviDelete.addEventListener('click', () => this.onDeleteClick());

        // 当前被点中的tab
        switch (this.state.viewType) {
            case -1:
                this.naviCustom.querySelector("button").setAttribute("class", "btn btn-block");
                break;
            case 0:
                this.naviView.querySelector("button").setAttribute("class", "btn btn-block");
                break;
            case 1:
                this.naviAdd.querySelector("button").setAttribute("class", "btn btn-block");
                break;
            case 2:
                this.naviModify.querySelector("button").setAttribute("class", "btn btn-block");
                break;
            case 3:
                this.naviDelete.querySelector("button").setAttribute("class", "btn btn-block");
                break;
        }

        return this.el;
    }

}