/**
 * mtList.js
 */


document.addEventListener('DOMContentLoaded', function() {
	
    const grid = new tui.Grid({
        el: document.getElementById('grid'),
        scrollX: false,
        scrollY: false,
        columns: [
            {
                header: '자재코드',
                name: 'mtCode',
                align: 'center',
                sortingType: 'desc',
                sortable: true                
            },
            {
                header: '자재이름',
                name: 'mtName',
                align: 'center',
                sortingType: 'desc',
                sortable: true                  
            },
            {
                header: '자재구분',
                name: 'materialType',
                align: 'center',
                sortingType: 'desc',
                sortable: true                  
            },
            {
                header: '규격',
                name: 'specification',
                align: 'center',
                sortingType: 'desc',
                sortable: true                  
            },
            {
                header: '단위',
                name: 'unit',
                align: 'center',
                sortingType: 'desc',
                sortable: true                  
            },
            {
                header: '단가',
                name: 'unitPrice',
                align: 'center',
                sortingType: 'desc',
                sortable: true                  
            },
            {
                header: '안전재고',
                name: 'safetyStock',
                align: 'center',
                sortingType: 'desc',
                sortable: true                  
            },
            {
                header: '재고코드',
                name: 'stcCode',
                align: 'center',
                sortingType: 'desc',
                sortable: true                  
            },
            {
                header: '재고변동일',
                name: 'updateDate',
                align: 'center',
                sortingType: 'desc',
                sortable: true                  
            },
            {
                header: '현재재고',
                name: 'currentStc',
                align: 'center',
                sortingType: 'desc',
                sortable: true                  
            },
            /*
            {
                header: '리드타임',
                name: 'leadtime',
                align: 'center',
                sortingType: 'desc',
                sortable: true                  
            },
            */
            {
                header: '비고',
                name: 'comm',
                align: 'center',
                sortingType: 'desc',
                sortable: true                  
            },            
            /*
            {
                header: '제품코드',
                name: 'prdCode',
                align: 'center',
                sortingType: 'desc',
                sortable: true                  
            },
            */
        ],
        rowHeaders: ['checkbox', 'rowNum'],
        pageOptions: {
            useClient: true,
            perPage: 15
        }
    });

    function fetchMtList(search = {}) {
        const params = new URLSearchParams(search);
        const url = `/mts?${params.toString()}`;

        fetch(url)
            .then(response => response.json())
            .then(result => {
                grid.resetData(result);
            })
            .catch(error => {
                console.error(error);
            });
    }
    
    fetchMtList();

});		 