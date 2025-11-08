class Component {
    constructor(props = {}) {
        this.props = props
    }

    setState(state) {
        const oldEl = this.el;
        this.state = {...this.state, ...state};
        this.el = this.renderDOM();
        if (this.onStateChange) this.onStateChange(oldEl, this.el)
    }

    renderDOM() {
        this.el = createDOMFromString(`<div></div>`);
        return this.el;
    }
}

const createDOMFromString = (domString) => {
    const div = document.createElement('div');
    div.innerHTML = domString;
    return div
};

const mount = (component, wrapper) => {
    wrapper.appendChild(component.renderDOM());
    component.onStateChange = (oldEl, newEl) => {
        wrapper.insertBefore(newEl, oldEl);
        wrapper.removeChild(oldEl)
    }
};
