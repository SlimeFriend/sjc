/*
* lineList.js
*/
document.addEventListener('DOMContentLoaded', function() {

    tui.Grid.applyTheme('striped');
	
    const grid = new tui.Grid({
        el: document.getElementById('grid'),
        scrollX: false,
        scrollY: false,
        columns: [
            {
                header: '라인코드',
                name: 'lineCode',
                align: 'center',
                sortingType: 'desc',
                sortable: true                
            },
            {
                header: '제품코드',
                name: 'prdCode',
                align: 'center',
                sortingType: 'desc',
                sortable: true                  
            },
            {
                header: '사용여부',
                name: 'use',
                align: 'center',
                sortingType: 'desc',
                sortable: true                  
            },
        ],
        rowHeaders: ['rowNum'],
        pageOptions: {
            useClient: true,
            perPage: 10
        }
    });

    document.getElementById('searchBtn').addEventListener('click', function() {
        const search = {
        		lineCode: document.getElementById('inputLineCode').value,
        		prdCode: document.getElementById('inputPrdCode').value,
        		use: document.getElementById('inputUse').value,
        };
        fetchPrds(search);
    });

    function fetchPrds(search = {}) {
        const params = new URLSearchParams(search);
        const url = `/lines?${params.toString()}`;

        fetch(url)
            .then(response => response.json())
            .then(result => {
                grid.resetData(result);
            })
            .catch(error => {
                console.error(error);
            });
    }

    fetchPrds();
});