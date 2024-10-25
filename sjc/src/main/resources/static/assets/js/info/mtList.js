/**
 * userListGridApi.js
 */

	

document.addEventListener('DOMContentLoaded', function() {
	
	const gridData = /*[[${prds}]]*/[];

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
                header: '비고',
                name: 'comm',
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
                header: '현재수량',
                name: 'quantityRequired',
                align: 'center',
                sortingType: 'desc',
                sortable: true                  
            },
            {
                header: '제품명',
                name: 'prdName',
                align: 'center',
                sortingType: 'desc',
                sortable: true                  
            },
        ],
        rowHeaders: ['checkbox', 'rowNum'],
        pageOptions: {
            useClient: true,
            perPage: 4
        }
    });


    const gridBom = new tui.Grid({
        el: document.getElementById('grid'),
        scrollX: false,
        scrollY: false,
        width: 'auto',

        columns: [
            {
                header: '자재코드',
                name: 'mtCode',
                align: 'center',
                sortingType: 'desc',
                sortable: true                
            },
        ]
    });
    
    const gridBomDetail = new tui.Grid({
        el: document.getElementById('grid'),
        scrollX: false,
        scrollY: false,
        width: 'auto',
        
        columns: [
            {
                header: '자재코드',
                name: 'mtCode',
                align: 'center',
                sortingType: 'desc',
                sortable: true                
            },
        ]
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

    document.getElementById('registerBtn').addEventListener('click', function() {
        const selectedRows = grid.getCheckedRows();
        
        if (selectedRows.length > 0) {
        	if (confirm("새로운 BOM을 등록하시겠습니까??") == true){
        		//registerBoms(selectedRows.map(row => row.userId));
        		registerBoms(selectedRows.map(row => row.mtCode));
        	}else{
        		return false;
        	}
        } else {
            alert('등록할 자재를 선택해주세요.');
        }
    });

    function registerBoms(mtCode) {
        fetch('/registerBoms', {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(userIds),
        })
        .then(response => response.json())
        .then(result => {
            fetchBoms();
            fetchBomDetails();
            console.error('result:', result);
        })
        .catch(error => {
            console.error('Error:', error);
        });
    }

    function fetchBoms(search = {}) {
        const params = new URLSearchParams(search);
        const url = `/boms?${params.toString()}`;

        fetch(url)
            .then(response => response.json())
            .then(result => {
                gridBom.resetData(result);
            })
            .catch(error => {
                console.error(error);
            });
    }
    fetchBoms();
    
    function fetchBomDetails(search = {}) {
        const params = new URLSearchParams(search);
        const url = `/bomDetails?${params.toString()}`;

        fetch(url)
            .then(response => response.json())
            .then(result => {
                gridBomDetail.resetData(result);
            })
            .catch(error => {
                console.error(error);
            });
    }
    fetchBomDetails();


		 
});		 