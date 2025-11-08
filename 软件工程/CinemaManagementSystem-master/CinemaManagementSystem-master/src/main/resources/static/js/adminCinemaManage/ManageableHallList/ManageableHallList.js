class ManageableHallList extends Component {
    constructor(props) {
        super(props);
        this.state = {
            availableHalls: this.props.availableHalls,
            operationName: this.props.operationName
        }
    }

    // 获取影厅规格类型
    getHallScale(scale){
        let s = '';
        switch (scale) {
            case 0:
                s = '大型影厅';
                break;
            case 1:
                s = '常规影厅';
                break;
            case 2:
                s = '小型影厅';
                break;
        }
        return s;
    }

    renderDOM() {
        let hallListLayout = ``;
        if (this.state.availableHalls.length > 0)
            this.state.availableHalls.forEach(availableHall => {
                const seatNum = getSeatNum(availableHall.seats);
                hallListLayout += `
                <tr>
                    <td>${availableHall.name}</td>
                    <td>${this.getHallScale(availableHall.scale)}</td>
                    <td>${seatNum}</td>
                    <td>
                        <a class="manageable-hall-list-item" id="manageable-hall-list-item${availableHall.id}">
                            ${this.state.operationName}
                        </a>
                    </td>
                </tr>
            `;
            });

        const el_html = `
            <div id="hall-manageable-hall-list">
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th>影厅名称</th>
                        <th>影厅规格</th>
                        <th>影厅座位数</th>
                        <th>影厅管理</th>
                    </tr>
                    </thead>
                    <tbody id="hall-list">
                        ${hallListLayout}
                    </tbody>
                </table>
            </div>
        `;
        this.el = createDOMFromString(el_html);

        if (this.state.availableHalls.length > 0)
            this.state.availableHalls.forEach(availableHall => {
                const hallId = availableHall.id;
                (this.el.querySelector("#manageable-hall-list-item" + hallId))
                    .addEventListener('click', () => this.props.onOperationClick(hallId));
            });

        return this.el;
    }
}