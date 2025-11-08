class HallInfoForm extends Component {
    constructor(props) {
        super(props);
        this.state = {
            hallId: this.props.hallId,
            hallName: this.props.hallName,
            seatRow: this.props.seatRow,
            seatColumn: this.props.seatColumn,
            seatArray: this.props.seats ?
                this.props.seats :
                HallInfoForm.getSeatArray(this.props.seatRow, this.props.seatColumn),
            // 0:录入影厅   1:更新影厅信息
            operationType: this.props.operationType,
        }
    }

    static getSeatArray(r, c) {
        let seatArray = [];
        for (let i = 0; i < r; i++) {
            let row = [];
            for (let j = 0; j < c; j++) {
                row.push(0);
            }
            seatArray.push(row);
        }
        return seatArray;
    }

    // 改变影厅规格的响应事件
    onScaleClick() {
        const row = document.querySelector("#hall-manage-form-scale-row-input").value;
        const column = document.querySelector("#hall-manage-form-scale-column-input").value;
        const hallName = document.querySelector("#hall-manage-form-name-input").value;
        // 将新的规格的影厅布局和原影厅布局合并
        let seatLayoutMerged = HallInfoForm.getSeatArray(row, column);
        for (let i = 0; i < row && i < this.state.seatRow; i++) {
            for (let j = 0; j < column && j < this.state.seatColumn; j++) {
                seatLayoutMerged[i][j] = this.state.seatArray[i][j]
            }
        }
        this.setState({
            hallName: hallName,
            seatRow: row,
            seatColumn: column,
            seatArray: seatLayoutMerged
        })
    }

    // 确认录入影厅信息的响应事件
    onSubmitClick() {
        const hallName = document.querySelector("#hall-manage-form-name-input").value;
        const seats = this.state.seatArray;
        const seatNum = getSeatNum(seats);
        const scale = ((seatNum) => {
            if (seatNum < 30) return 2;
            else if (seatNum < 80) return 1;
            else return 0;
        })(seatNum);

        if (this.state.operationType === 0) {
            this.props.onSubmitClick(hallName, seats, scale);
        } else {
            this.props.onSubmitClick(this.state.hallId, hallName, seats, scale);
        }
    }

    // 排布座位响应事件
    onChooseSeat(rowId, columnId) {
        let seatArray = this.state.seatArray;
        seatArray[rowId][columnId] = seatArray[rowId][columnId] === 0 ? -1 : 0;
        const hallName = document.querySelector("#hall-manage-form-name-input").value;
        this.setState({
            hallName: hallName,
            seatArray: seatArray
        });
    }

    renderDOM() {
        let seatsLayout = ``;
        for (let i = 0; i < this.state.seatRow; i++) {
            let rowSeat = ``;
            for (let j = 0; j < this.state.seatColumn; j++) {
                if (this.state.seatArray[i][j] === 0)
                    rowSeat += `<div class='cinema-hall-seat' id="cinema-hall-seat${i}${j}"></div>`;
                else
                    rowSeat += `<div class='cinema-hall-seat-none' id="cinema-hall-seat${i}${j}"></div>`;
            }
            seatsLayout += `<div>${rowSeat}</div>`;
        }

        const el_html = `
            <div id="hall-manage-form">
                <div id="hall-manage-form-name">
                    <label id="hall-manage-form-name-label"><span class="error-text">*</span>影厅名称</label>
                    <div id="hall-manage-form-name-input-wrapper">
                        <input type="text" class="form-control" id="hall-manage-form-name-input" placeholder="请输入影厅名称" value="${this.state.hallName}">
                    </div>
                </div>
                <div id="hall-manage-form-scale">
                    <label id="hall-manage-form-scale-label"><span class="error-text">*</span>影厅规格</label>
                    <div id="hall-manage-form-scale-input-wrapper">
                        <input type="number" class="form-control" id="hall-manage-form-scale-row-input" value="${this.state.seatRow}">排 × 
                        <input type="number" class="form-control" id="hall-manage-form-scale-column-input" value="${this.state.seatColumn}">列
                        <button type="button" class="btn btn-primary" id="hall-manage-form-scale-confirm">确定</button>
                    </div>
                </div>
                <div id="hall-manage-form-seats">
                    <label id="hall-manage-form-seats-label"><span class="error-text">*</span>座位排布</label>
                    <div id="hall-manage-form-seats-input-wrapper">
                        ${seatsLayout}
                    </div>
                </div>
                <div id="hall-manage-form-submit">
                    <button type="button" class="btn btn-primary" id="hall-manage-form-submit-button">确定录入</button>
                </div>
            </div>
        `;
        this.el = createDOMFromString(el_html);

        this.scaleConfirm = this.el.querySelector("#hall-manage-form-scale-confirm");
        this.submitButton = this.el.querySelector("#hall-manage-form-submit-button");
        this.scaleConfirm.addEventListener('click', () => this.onScaleClick());
        this.submitButton.addEventListener('click', () => this.onSubmitClick());
        for (let i = 0; i < this.state.seatRow; i++) {
            for (let j = 0; j < this.state.seatColumn; j++) {
                this.el.querySelector("#cinema-hall-seat" + i + j)
                    .addEventListener('click', () => this.onChooseSeat(i, j));
            }
        }

        return this.el;
    }
}