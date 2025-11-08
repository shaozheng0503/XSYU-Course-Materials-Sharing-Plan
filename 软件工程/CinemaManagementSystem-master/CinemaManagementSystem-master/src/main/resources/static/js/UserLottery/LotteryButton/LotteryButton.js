class LotteryButton extends Component {

    constructor(props) {
        super(props);
        this.props= {
            categories: [[1,10],[10,30],[30,60]]
        }
    }

    renderDOM() {
        const el_html = `
            <div class="lottery-button">
                <button type="button" class="btn btn-primary" id="lottery-button-one">一发入魂</button>
            </div>
            <div class="lottery-button">
                <button type="button" class="btn btn-primary" id="lottery-button-ten">十连保底</button>       
            </div> 
        `;
        this.el = createDOMFromString(el_html);
        let one = this.el.querySelector("#lottery-button-one");
        let ten = this.el.querySelector("#lottery-button-ten");
        // let payModal = new PayModal();
        one.addEventListener('click', () => {
            this.renderPayModal(1)
        });
        ten.addEventListener('click', () => {
            this.renderPayModal(10)
        });
        return this.el
    }

    renderPayModal(num) {
        let category = -1;
        let categoryChosen = document.querySelector(".lottery-category-chosen");
        if (categoryChosen!==null) category = parseInt(categoryChosen.id.replace("category-",""));
        if (category!==-1){
            let min = this.props.categories[category][0];
            let max = this.props.categories[category][1];
            mount(new PayModal({min,max,num}), document.querySelector("#pay-modal-container"))
        } else {
            alert("请选择一种优惠券");
        }
    }

}