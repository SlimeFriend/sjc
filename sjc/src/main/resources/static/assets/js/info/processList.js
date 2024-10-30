/*
* processList.js
*/

document.addEventListener('DOMContentLoaded', function() {

    tui.Grid.applyTheme('striped');
	
    const grid = new tui.Grid({
        el: document.getElementById('grid'),
        scrollX: false,
        scrollY: false,
        columns: [
            {
                header: '공정코드',
                name: 'processCode',
                align: 'center',
                sortingType: 'desc',
                sortable: true                
            },
            {
                header: '공정이름',
                name: 'processName',
                align: 'center',
                sortingType: 'desc',
                sortable: true                  
            },
            {
                header: '공겅구분',
                name: 'prccessType',
                align: 'center',
                sortingType: 'desc',
                sortable: true                  
            },
            {
                header: '설명',
                name: 'description',
                align: 'center',
                sortingType: 'desc',
                sortable: true                  
            },
            {
                header: '비고',
                name: 'comm',
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
        		processCode: document.getElementById('inputProcessCode').value,
        		processName: document.getElementById('inputProcessName').value,
        		prccessType: document.getElementById('inputPrccessType').value,
        };
        fetchPrds(search);
    });

    function fetchPrds(search = {}) {
        const params = new URLSearchParams(search);
        const url = `/processes?${params.toString()}`;

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