class PayModal extends Component {

    constructor(props) {
        super(props);

    }

    renderDOM() {
        const el_html = `
            <div class="modal show" id="payModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                            </button>
                            <h4 class="modal-title" id="myModalLabel">抽奖</h4>
                        </div>
                        <div class="modal-body" id="userLottery-form">
                            <div class="form-group">
                                <label for="userLottery-cardNum"><span class="error-text">*</span>银行卡号</label>
                                <input class="form-control" id="userLottery-cardNum" placeholder="请输入银行卡号">
                                <p id="userLottery-cardNum-error" class="notice">银行卡号不能为空</p>
                            </div>
                            <div class="form-group">
                                <label for="userLottery-cardPwd"><span class="error-text">*</span>密码</label>
                                <input type="password" class="form-control" id="userLottery-cardPwd" placeholder="请输入密码">
                                <p id="userLottery-cardPwd-error" class="notice">密码不能为空</p>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button id="cancel-button" type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                            <button id="commit-button" class="btn btn-primary">确定</button>
                        </div>
                    </div>
                </div>
            </div>
        `;
        this.el = createDOMFromString(el_html);
        this.el.querySelector("#payModal")
        this.el.querySelector("#cancel-button").addEventListener('click', () => {
            document.querySelector('#pay-modal-container').innerHTML='';
        });
        this.el.querySelector("#commit-button").addEventListener('click', () => {
            if(this.confirmCommit()) {
                document.querySelector('#pay-modal-container').innerHTML='';
            }
        });
        return this.el
    }

    confirmCommit() {
        let isValidate = true;
        if (!$('#userLottery-cardNum').val()) {
            isValidate = false;
            $('#userLottery-cardNum').parent('.form-group').addClass('has-error');
            $('#userLottery-cardNum-error').css("visibility", "visible");
        }
        if (!$('#userLottery-cardPwd').val()) {
            isValidate = false;
            $('#userLottery-cardPwd').parent('.form-group').addClass('has-error');
            $('#userLottery-cardPwd-error').css("visibility", "visible");
        }

        if (isValidate) {
            if ($('#userLottery-cardNum').val() === "123123123" && $('#userLottery-cardPwd').val() === "123123") {
                console.log("抽奖成功,min:"+this.props.min+",max:"+this.props.max+",num:"+this.props.num);
                mount(new ShowLottery(this.props), document.querySelector("#lottery-coupon-container"));
                return true;
            } else {
                alert("银行卡号或密码错误");
            }
        }

        return false;
    }

}