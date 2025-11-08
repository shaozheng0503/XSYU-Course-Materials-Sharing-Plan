class LotteryCategory extends Component {

    constructor(props) {
        super(props);

    }

    renderDOM() {
        const el_html = `
            <div class="lottery-category" id="category-0">
                <div class="lottery-category-description">
                    <div class="lottery-category-name">R优惠券</div>
                    <div class="lottery-category-price">5元/次</div>
                </div>
            </div>
            <div class="lottery-category" id="category-1">
                <div class="lottery-category-description">
                    <div class="lottery-category-name">SR优惠券</div>
                    <div class="lottery-category-price">20元/次</div>
                </div>
            </div>
            <div class="lottery-category" id="category-2">
                <div class="lottery-category-description">
                    <div class="lottery-category-name">SSR优惠券</div>
                    <div class="lottery-category-price">45元/次</div>
                </div>
            </div>
        `;
        this.el = createDOMFromString(el_html);
        let category0 = this.el.querySelector("#category-0");
        let category1 = this.el.querySelector("#category-1");
        let category2 = this.el.querySelector("#category-2");
        category0.addEventListener('click', () => {
            this.categoryClick(category0.id)
        });
        category1.addEventListener('click', () => {
            this.categoryClick(category1.id)
        });
        category2.addEventListener('click', () => {
            this.categoryClick(category2.id)
        });
        return this.el
    }

    categoryClick(id){
        let category = this.el.querySelector("#"+id);
        if (category.classList.contains("lottery-category")) {
            this.el.querySelectorAll('div[id^=category]').forEach((e)=>{
                e.classList.remove("lottery-category", "lottery-category-chosen");
                e.classList.add("lottery-category");
            });
            category.classList.remove("lottery-category");
            category.classList.add("lottery-category-chosen");
        } else {
            category.classList.remove("lottery-category-chosen");
            category.classList.add("lottery-category");
        }
    }
}