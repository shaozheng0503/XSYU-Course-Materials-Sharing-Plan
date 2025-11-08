class CanSeeDay extends Component {
    constructor(props) {
        super(props);
        this.state = {
            canSeeDate: 0
        };
        this.getCanSeeDayNum();
    }

    getCanSeeDayNum() {
        getRequest(
            '/schedule/view',
            (res) => {
                this.setState({canSeeDate: res.content});
            },
            error => {
                alert(JSON.stringify(error));
            }
        );
    }

    onModifyCanViewClick() {
        $("#canview-modify-btn").hide();
        $("#canview-set-input").val(this.state.canSeeDate);
        $("#canview-set-input").show();
        $("#canview-confirm-btn").show();
    }

    onCanViewConfirmClick() {
        const dayNum = $("#canview-set-input").val();
        // 验证一下是否为数字
        postRequest(
            '/schedule/view/set',
            {day: dayNum},
            (res) => {
                if (res.success) {
                    this.getCanSeeDayNum();
                    $("#canview-modify-btn").show();
                    $("#canview-set-input").hide();
                    $("#canview-confirm-btn").hide();
                } else {
                    alert(res.message);
                }
            },
            error => {
                alert(JSON.stringify(error));
            }
        );
    }

    renderDOM() {
        const el_html = `<span class="">用户可见排片日期：  ${this.state.canSeeDate} </span> 
            <button style="margin-left: 20px;" class="btn btn-success" id="canview-modify-btn">修改</button> 
            <input type="number" class="form-control" id="canview-set-input" style="display: none;width: 200px;">
            <button style="display: none" class="primary-text" id="canview-confirm-btn">确认</button>
            <hr/>
            <span>首页轮播栏信息：</span>
            <button style="margin-left: 10px;" class="btn btn-success" id="banner-edit-btn" data-backdrop="static" data-toggle="modal" data-target="#bannerModal">修改</button>
<!--轮播栏信息表单-->
<div class="modal fade" id="bannerModal" tabindex="-1" role="dialog" aria-labelledby="bannerModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="bannerModalLabel">修改轮播</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" role="form">
                    <div class="form-group">
                        <label class="col-sm-2 control-label" for="banner1-img-input"><span class="error-text">*</span>图片1路径</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="banner1-img-input" placeholder="请输入图片1路径">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label" for="banner1-navi-input"><span class="error-text">*</span>图片1跳转</label>
                        <div class="col-sm-10">
                        <input type="text" class="form-control" id="banner1-navi-input" placeholder="请输入图片1跳转">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label" for="banner2-img-input"><span class="error-text">*</span>图片2路径</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="banner2-img-input" placeholder="请输入图片2路径">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label" for="banner2-navi-input"><span class="error-text">*</span>图片2跳转</label>
                        <div class="col-sm-10">
                        <input type="text" class="form-control" id="banner2-navi-input" placeholder="请输入图片2跳转">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label" for="banner3-img-input"><span class="error-text">*</span>图片3路径</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="banner3-img-input" placeholder="请输入图片3路径">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label" for="banner3-navi-input"><span class="error-text">*</span>图片3跳转</label>
                        <div class="col-sm-10">
                        <input type="text" class="form-control" id="banner3-navi-input" placeholder="请输入图片3跳转">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" id="banner-edit-submit">确认</button>
            </div>
        </div>
    </div>
</div>
        `;
        this.el = createDOMFromString(el_html);
        this.modifyButton = this.el.querySelector("#canview-modify-btn");
        this.confirmButton = this.el.querySelector("#canview-confirm-btn");
        this.modifyButton.addEventListener('click', () => this.onModifyCanViewClick(), false);
        this.confirmButton.addEventListener('click', () => this.onCanViewConfirmClick(), false);

        this.editBannerBtn = this.el.querySelector("#banner-edit-btn");
        this.editBannerBtn.addEventListener('click', () => this.getBannerInfo());
        this.submitBannerBtn = this.el.querySelector("#banner-edit-submit");
        this.submitBannerBtn.addEventListener('click', () => this.submitBannerEdit());
        return this.el;
    }

    getBannerInfo() {
        getRequest(
            '/banner/get',
            (res) => {
                if (res.success) {
                    let bannerinfo = res.content;
                    this.el.querySelector("#banner1-img-input").value = bannerinfo.img1;
                    this.el.querySelector("#banner1-navi-input").value = bannerinfo.navi1;
                    this.el.querySelector("#banner2-img-input").value = bannerinfo.img2;
                    this.el.querySelector("#banner2-navi-input").value = bannerinfo.navi2;
                    this.el.querySelector("#banner3-img-input").value = bannerinfo.img3;
                    this.el.querySelector("#banner3-navi-input").value = bannerinfo.navi3;
                } else {
                    alert(res.message);
                }
            },
            (err) => {
                alert(JSON.stringify(err));
            }
        )
    }

    submitBannerEdit() {
        let img1 = this.el.querySelector("#banner1-img-input").value;
        let navi1 = this.el.querySelector("#banner1-navi-input").value;
        let img2 = this.el.querySelector("#banner2-img-input").value;
        let navi2 = this.el.querySelector("#banner2-navi-input").value;
        let img3 = this.el.querySelector("#banner3-img-input").value;
        let navi3 = this.el.querySelector("#banner3-navi-input").value;
        postRequest(
            '/banner/update',
            {
                img1: img1,
                navi1: navi1,
                img2: img2,
                navi2: navi2,
                img3: img3,
                navi3: navi3
            },
            (res) => {
                if (res.success) {
                    alert('修改成功!');
                } else {
                    alert(res.message);
                }
            },
            (err) => {
                alert(JSON.stringify(err));
            }
        )
    }

}