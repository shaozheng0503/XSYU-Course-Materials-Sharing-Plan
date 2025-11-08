class ShowLottery extends Component {

    constructor(props) {
        super(props);
        this.state = {
            coupons: []
        };
        this.getLottery(props.min,props.max,props.num);
    }

    renderDOM() {
        let couponList = ``;
        switch (this.props.num) {
            case 1:
                console.log(this.state.coupons);
                let coupon = this.state.coupons[0] || { discountAmount: 1, targetAmount: 1, couponName: '优惠券'};
                couponList = `
                    <div id="show-lottery-coupon-one">
                        <div class="coupon-item coupon-one-item">
                            <div class="coupon-amount-wrapper">
                                <div class="coupon-one-discount-amount">￥${coupon.discountAmount}</div>
                                <div class="coupon-one-target-amount">需满${coupon.targetAmount}元</div>
                            </div>
                            <div class="coupon-one-movie-name">${coupon.couponName}</div>
                        </div>
                    </div>
                `;
                break;
            case 10:
                let couponsHTML = ``;
                this.state.coupons.forEach(coupon => {
                    couponsHTML += `
                        <div class="coupon-item coupon-ten-item">
                            <div class="coupon-amount-wrapper">
                                <div class="coupon-ten-discount-amount">￥${coupon.discountAmount}</div>
                                <div class="coupon-ten-target-amount">需满${coupon.targetAmount}元</div>
                            </div>
                            <div class="coupon-ten-movie-name">${coupon.couponName}</div>
                        </div>
                    `;
                })
                couponList = `
                    <div id="show-lottery-coupon-ten">${couponsHTML}</div>
                `;
                break;
        }
        const el_html = `
            <div id="show-lottery-container">
                <div class="show-lottery-head"><a href="/user/member">点击这里去卡包查看</a></div>
                <div id="show-lottery-coupons">${couponList}</div>
                <div class="show-lottery-foot">
                    <button id="show-lottery-back" class="btn btn-success">返回</button>
                </div>
            </div>
            <div class="full-screen-background">
                <img id="cool-animation" src="../../../images/star.png"/>
            </div>
        `;
        this.el = createDOMFromString(el_html);
        this.el.querySelector("#show-lottery-back").addEventListener('click', () => {
            let container = document.querySelector("#lottery-coupon-container");
            container.removeChild(container.firstChild);
        });
        return this.el;
    }

    /**
     * 抽取符合条件的num张优惠券
     * @param min
     * @param max
     * @param num
     */
    getLottery(min,max,num) {
        getRequest(
            '/lottery/get?min='+min+'&max='+max+'&num='+num,
            (res) => {
                if (res.success) {
                    this.setState({
                        coupons: res.content
                    })
                } else {
                    alert(res.message);
                }
            },
            (error) => {
                alert(JSON.stringify(error));
            }
        );
    }

}