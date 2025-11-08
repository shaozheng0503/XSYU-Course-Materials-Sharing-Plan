class ModifyHall extends Component {
    constructor(props) {
        super(props);
        this.state = {
            modifiableHalls: [],
            operationName: '修改',
            // viewTab == 0时是可修改列表展示；viewTab == 1时是修改表单展示
            viewTab: 0,
            // 当前正则修改的影厅ID
            modifyingHallId: 0
        };
        this.getModifiableHalls();
    }

    // 获取可修改的影厅列表操作
    getModifiableHalls() {
        getRequest(
            '/hall/available',
            (res) => this.setState({modifiableHalls: res.content}),
            error => alert(JSON.stringify(error))
        );
    }

    // 提交更新影厅信息点击事件
    onSubmitClick(hallId, hallName, seats, scale) {
        fetch(
            '/hall/update',
            {
                method: 'POST',
                headers: {
                    'content-type': 'application/json'
                },
                body: JSON.stringify({
                    id: hallId,
                    name: hallName,
                    seats: seats,
                    scale: scale
                })
            })
            .then(response => response.json())
            .then(res=>{
                if (res.success) {
                    alert("更新影厅信息成功！");
                    // 重置输入框
                    this.setState({
                        viewTab: 0
                    });
                    // 刷新可修改影厅列表
                    this.getModifiableHalls();
                } else {
                    alert(res.message);
                }
            })
            .catch(error => {
                alert(error)
            });
    }

    // 修改操作响应事件
    onModifyClick(hallId) {
        this.setState({
            viewTab: 1,
            modifyingHallId: hallId,
        });
    }

    renderDOM() {
        const el_html = `
            <div id="hall-manage-modify-wrapper"></div>
        `;
        this.el = createDOMFromString(el_html);

        this.modifyWrapper = this.el.querySelector("#hall-manage-modify-wrapper");
        switch (this.state.viewTab) {
            case 0:
                mount(new ManageableHallList({
                    onOperationClick: (id) => this.onModifyClick(id),
                    availableHalls: this.state.modifiableHalls,
                    operationName: this.state.operationName,
                }), this.modifyWrapper);
                break;
            case 1:
                const modifyingHall = this.state.modifiableHalls.find(hall => hall.id === this.state.modifyingHallId);
                mount(new HallInfoForm({
                    hallId: modifyingHall.id,
                    hallName: modifyingHall.name,
                    seats: modifyingHall.seats,
                    seatRow: modifyingHall.seats.length,
                    seatColumn: modifyingHall.seats[0].length,
                    operationType: 1,
                    onSubmitClick: (id, name, Seats, Scale) => this.onSubmitClick(id, name, Seats, Scale)
                }), this.modifyWrapper);
                break;
        }

        return this.el;
    }

}