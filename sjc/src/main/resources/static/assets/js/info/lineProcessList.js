/*
* lineList.js
*/
document.addEventListener('DOMContentLoaded', function() {

    tui.Grid.applyTheme('striped');
	
    const gridLine = new tui.Grid({
        el: document.getElementById('gridLine'),
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

    document.getElementById('searchProcessBtn').addEventListener('click', function() {
        const search = {
        		lineCode: document.getElementById('inputLineCode').value,
        };
        fetchlines(search);
    });

    function fetchlines(search = {}) {
        const params = new URLSearchParams(search);
        const url = `/lines?${params.toString()}`;

        fetch(url)
            .then(response => response.json())
            .then(result => {
                gridLine.resetData(result);
            })
            .catch(error => {
                console.error(error);
            });
    }
    fetchlines();
    
    const gridProcess = new tui.Grid({
        el: document.getElementById('gridProcess'),
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

    document.getElementById('searchProcessBtn').addEventListener('click', function() {
        const search = {
        		processCode: document.getElementById('inputProcessCode').value,
        };
        fetchprocesses(search);
    });

    function fetchprocesses(search = {}) {
        const params = new URLSearchParams(search);
        const url = `/processes?${params.toString()}`;

        fetch(url)
            .then(response => response.json())
            .then(result => {
                gridProcess.resetData(result);
            })
            .catch(error => {
                console.error(error);
            });
    }

    fetchprocesses();    
});