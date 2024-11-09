/**
 * cpList.js
 */
document.addEventListener('DOMContentLoaded', function() {

    tui.Grid.applyTheme('striped');
	
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
                sortable: true,
				ellipsis: true,
				renderer: {
					type: TooltipRenderer
				}                                 
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
            perPage: 15
        }
    });
    
    const gridCpModal = new tui.Grid({
        el: document.getElementById('gridCpModal'),
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
                sortable: true,
                editor: 'text',
                /*
                validation: {
					required: true,
			        regExp: /^[가-힣a-zA-Z]+$/
                },
                */                
            },
            {
                header: '업체구분',
                name: 'cpType',
                align: 'center',
                sortingType: 'desc',
                sortable: true,
                /*
                editor: {
                  type: 'select',
                  options: {
                    listItems: [
                      { text: '자재', value: '자재' },
                      { text: '완제품', value: '완제품' },
                    ]
                  }
                },                
                */                  
            },
            {
                header: '사업자번호',
                name: 'businessNo',
                align: 'center',
                sortingType: 'desc',
                sortable: true,
                //editor: 'text',                                  
                                  
            },
            {
                header: '주소',
                name: 'address',
                align: 'center',
                sortingType: 'desc',
                sortable: true,
                //editor: 'text',
				ellipsis: true,
				renderer: {
					type: TooltipRenderer
				}                
                                                  
                                  
            },
            {
                header: '비고',
                name: 'comm',
                align: 'center',
                sortingType: 'desc',
                sortable: true,
                //editor: 'text',                                  
                                  
            }
        ],
        //rowHeaders: ['checkbox', 'rowNum'],
        pageOptions: {
            useClient: true,
            perPage: 15
        }
    });
    
    const gridCpInsertModal = new tui.Grid({
        el: document.getElementById('gridCpInsertModal'),
        scrollX: false,
        scrollY: false,
        columns: [
			/*
            {
                header: '업체코드(CP)',
                name: 'cpCode',
                align: 'center',
                sortingType: 'desc',
                sortable: true,
                editor: 'text',
                validation: {
					required: true,
        			unique: true,
			        regExp: /^[a-zA-Z0-9]+$/
                },                

            },
            */
            {
                header: '업체명',
                name: 'cpName',
                align: 'center',
                sortingType: 'desc',
                sortable: true,
                editor: 'text',
                validation: {
					required: true,
			        regExp: /^[가-힣a-zA-Z]+$/
                },                
            },
            {
                header: '업체구분',
                name: 'cpType',
                align: 'center',
                sortingType: 'desc',
                sortable: true,
                editor: {
                  type: 'select',
                  options: {
                    listItems: [
                      { text: '자재', value: '자재' },
                      { text: '완제품', value: '완제품' },
                    ]
                  }
                },                
                                  
            },
            {
                header: '사업자번호',
                name: 'businessNo',
                align: 'center',
                sortingType: 'desc',
                sortable: true,
                editor: 'text',                                  
            },
            {
                header: '주소',
                name: 'address',
                align: 'center',
                sortingType: 'desc',
                sortable: true,
                editor: 'text',
				ellipsis: true,
				renderer: {
					type: TooltipRenderer
				}                                                   
            },
            {
                header: '비고',
                name: 'comm',
                align: 'center',
                sortingType: 'desc',
                sortable: true,
                editor: 'text',                                  
            }
        ],
        rowHeaders: ['rowNum'],
        pageOptions: {
            useClient: true,
            perPage: 15
        }
    });

    document.getElementById('searchBtn').addEventListener('click', function() {
        const search = {
        		cpCode: document.getElementById('inputCpCode').value,
        		cpName: document.getElementById('inputCpName').value,
        		//cpType: document.getElementById('inputCpType').value,
        };
        fetchCps(search);
    });

    function fetchCps(search = {}) {
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
    fetchCps();
    
    document.getElementById('insertBtn').addEventListener('click', function() {
		gridCpInsertModal.clear();
        $('#CpInsertModal').modal('show');
		gridCpInsertModal.refreshLayout();
		gridCpInsertModal.appendRow();
    });
    
    document.getElementById('appendCpBtn').addEventListener('click', function() {
		gridCpInsertModal.refreshLayout();
		gridCpInsertModal.appendRow();
    });
    
    document.getElementById('insertCpBtn').addEventListener('click', function() {
	    
	    const createdRows = gridCpInsertModal.getModifiedRows().createdRows;
	    const updatedRows = gridCpInsertModal.getModifiedRows().updatedRows;
	    //const modifiedRows = gridInsert.getData();
	    
	    console.log(createdRows);
	    console.log(updatedRows);
	    
	    
	    createdRows.forEach(object => {
			if	(object == null || object == ""){
		        alert('데이터 입력하세요.');
		        return false;
			}
			
		});
	    
	    if (createdRows.length == 0) {
	        alert('등록된 데이터가 없습니다.');
	        return false;
	    }
	    
	    if(gridCpInsertModal.validate() == 0){
			// do nothing
		}else if (gridCpInsertModal.validate()[0].errors.length > 0) {
	        alert('형식에 맞게 입력하세요.');
	        return false;
	    }
	
		if (confirm("등록 하시겠습니까??")){
			saveCps(createdRows);
		}else{
			return false;
		}
		
	});
	
	
    function saveCps(createdRows) {
	    fetch('cps', {
	        method: 'PUT',
	        headers: {
	            'Content-Type': 'application/json',
	        },
	        body: JSON.stringify(createdRows),
	    })
	    .then(response => {
	        if (response.ok) {
				const createdRowKeys = [
				  ...createdRows.map(row => row.rowKey),
				];
				
				createdRowKeys.forEach(rowKey => {
				  if (rowKey !== undefined) {
					/*
				    gridCpInsertModal.addRowClassName(rowKey, 'bg-warning');
				    gridCpInsertModal.restore();
					*/
        			$('#CpInsertModal').modal('hide');
				    fetchCps();

				  }
		  		});
	        }
	        return response.json();
	    })
	    .then(result => {
	        //fetchUsers();
	        console.log(result);
	    })
	    .catch(error => {
	        console.error('Error: ', error);
	    })
	    .finally(() => {
	    	
	    });
    }
    
	grid.on('click', (ev) => {
	    const rowKey = ev.rowKey;
	    
		const cpCode = grid.getValue(rowKey, 'cpCode');
		
		if(cpCode){
	        const params = new URLSearchParams({
			    cpCode: cpCode,
			}); 
	        
	        const url = `/cps?${params.toString()}`;
		
	        fetch(url)
	        .then(response => response.json())
	        .then(result => {
	            gridCpModal.resetData(result);
	            //document.getElementById('gridBomDetailModal').style.opacity = 1;
	            $('#CpModal').modal('show');
	            gridCpModal.refreshLayout();            
	        })
	        .catch(error => {
	            console.error(error);
	        });
		}
    });    
    
	document.addEventListener('click', (e) => {
	    gridCpModal.finishEditing();
	    gridCpInsertModal.finishEditing();
	});    
    
        
});