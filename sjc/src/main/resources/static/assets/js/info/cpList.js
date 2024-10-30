/**
 * cpList.js
 */
document.addEventListener('DOMContentLoaded', function() {
    const grid = new tui.Grid({
        el: document.getElementById('grid'),
        scrollX: false,
        scrollY: false,
        columns: [
            {
                header: '업체코드',
                name: 'cpCode',
                align: 'center',
                sortingType: 'desc',
                sortable: true                
            },
            {
                header: '업체명',
                name: 'cpName',
                align: 'center',
                sortingType: 'desc',
                sortable: true                  
            },
            {
                header: '업체구분',
                name: 'cpType',
                align: 'center',
                sortingType: 'desc',
                sortable: true                  
            },
            {
                header: '사업자번호',
                name: 'businessNo',
                align: 'center',
                sortingType: 'desc',
                sortable: true                  
            },
            {
                header: '주소',
                name: 'address',
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
            }
        ],
        rowHeaders: ['rowNum'],
        pageOptions: {
            useClient: true,
            perPage: 10
        }
    });

    document.getElementById('searchBtn').addEventListener('click', function() {
        const search = {
        		cpCode: document.getElementById('inputCpCode').value,
        		cpName: document.getElementById('inputCpName').value,
        		cpType: document.getElementById('inputCpType').value,
        };
        fetchUsers(search);
    });

    function fetchUsers(search = {}) {
        const params = new URLSearchParams(search);
        const url = `/cps?${params.toString()}`;

        fetch(url)
            .then(response => response.json())
            .then(result => {
                grid.resetData(result);
            })
            .catch(error => {
                console.error(error);
            });
    }

    fetchUsers();
});