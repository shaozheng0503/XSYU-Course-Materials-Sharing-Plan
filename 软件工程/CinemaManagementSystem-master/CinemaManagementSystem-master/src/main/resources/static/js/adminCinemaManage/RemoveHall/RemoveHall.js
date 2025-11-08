class RemoveHall extends Component {
    constructor(props) {
        super(props);
        this.state = {
            deletableHalls: [],
            operationName: '删除'
        };
        this.getDeletableHalls();
    }

    // 获取可删除的影厅列表操作
    getDeletableHalls() {
        fetch(
            '/hall/available',
            {
                method: 'GET'
            })
            .then(response => response.json())
            .then(res => {
                if (res.success)
                    this.setState({
                        deletableHalls: res.content
                    });
                else alert(res.message);
            })
            .catch(err => alert(err.json()))

    }

    // 删除操作响应事件
    onDeleteClick(hallId) {
        fetch(
            '/hall/remove?hallId='+hallId,
            {
                method: 'GET',
            })
            .then(response => response.json())
            // 刷新可删除影厅列表界面
            .then(res => {
                if (res.success) this.getDeletableHalls();
                else alert(res.message);
            })
            .catch(err => alert(err.json()))
    }

    renderDOM() {
        const el_html = `
            <div id="hall-manage-remove-wrapper"></div>
        `;
        this.el = createDOMFromString(el_html);
        this.removeableListWrapper = this.el.querySelector("#hall-manage-remove-wrapper");
        mount(new ManageableHallList({
            availableHalls: this.state.deletableHalls,
            operationName: this.state.operationName,
            onOperationClick: (id) => this.onDeleteClick(id),
        }), this.removeableListWrapper);
        return this.el;
    }

}