/*
* tooltip.js
간단하게 사용하려면 아래와 같이.
ellipsis: true,
renderer: {
	attributes: {
		title: (props) => `${props.formattedValue}`
	}
}

칼럼에 추가.
ellipsis: true,
renderer: {
	type: TooltipRenderer
} 
*/

	class TooltipRenderer {
	    constructor(props) {
	        const el = document.createElement('div');
	        el.className = 'tooltip-cell';
	        this.el = el;
	        this.tooltip = null;  // 툴팁 요소를 저장할 속성
	        this.render(props);
	    }
	
	    getElement() {
	        return this.el;
	    }
	
	    render(props) {
	        const { value } = props;
	        this.el.innerText = value;
	        
	        // 셀 스타일 설정: ellipsis가 적용되도록 CSS 스타일 추가
	        this.el.style.whiteSpace = 'nowrap';
	        this.el.style.overflow = 'hidden';
	        this.el.style.textOverflow = 'ellipsis';
	
	        // 마우스 오버 이벤트 추가
	        this.el.addEventListener('mouseover', (event) => {
	            // 텍스트가 셀 너비보다 긴 경우에만 툴팁을 보여줌
	            if (this.el.offsetWidth < this.el.scrollWidth) {
	                this.showTooltip(event, value);
	            }
	        });
	        this.el.addEventListener('mouseleave', () => this.hideTooltip());
	    }
	
	    // 툴팁 표시 함수
	    showTooltip(event, text) {
	        if (!this.tooltip) {
	            this.tooltip = document.createElement('div');
	            this.tooltip.className = 'custom-tooltip';
	            document.body.appendChild(this.tooltip);
	        }
	        
	        // 툴팁 내용 및 스타일 설정
	        this.tooltip.innerText = text;
	        this.tooltip.style.display = 'block';
	        this.tooltip.style.position = 'absolute';
	        this.tooltip.style.zIndex = '10000';
	        this.tooltip.style.fontSize = '18px'; // 툴팁 텍스트 크기를 크게 설정
	        this.tooltip.style.padding = '8px';
	        this.tooltip.style.backgroundColor = 'rgba(0, 0, 0, 0.75)';
	        this.tooltip.style.color = '#fff';
	        this.tooltip.style.borderRadius = '4px';
	        this.tooltip.style.whiteSpace = 'normal';
	        this.tooltip.style.maxWidth = '400px';
	
	        // 마우스 위치 기준으로 툴팁 위치 설정
	        const { clientX: mouseX, clientY: mouseY } = event;
	        this.tooltip.style.left = `${mouseX + 10}px`;  // 살짝 오른쪽에 위치
	        this.tooltip.style.top = `${mouseY - 30}px`;   // 살짝 아래쪽에 위치
	    }
	
	    // 툴팁 숨김 함수
	    hideTooltip() {
	        if (this.tooltip) {
	            this.tooltip.style.display = 'none';
	        }
	    }
	}
