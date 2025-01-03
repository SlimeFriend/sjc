/**
 * bomList.js
 */
document.addEventListener('DOMContentLoaded', function() {
    
    tui.Grid.applyTheme('striped');
    
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
                header: '필요수량',
                name: 'quantityRequired',
                align: 'center',
                sortingType: 'desc',
                sortable: true,
                editor: 'text',
				validation: {
				    required: true,
				    regExp: /^\d{1,3}$/,
				    min: 1,
				    max: 1000
				}
            },
        ],
        rowHeaders: ['checkbox', 'rowNum'],
        pageOptions: {
            useClient: true,
            perPage: 5
        }
    });
    
    const gridMtModal = new tui.Grid({
        el: document.getElementById('gridMtModal'),
        scrollX: false,
        scrollY: false,
        columns: [
            {
                header: '자재코드',
                name: 'mtCode',
                align: 'center',
            },
            {
                header: '자재이름',
                name: 'mtName',
                align: 'center',
            },
            {
                header: '자재구분',
                name: 'materialType',
                align: 'center',
            },
            {
                header: '규격',
                name: 'specification',
                align: 'center',
            },
            {
                header: '단위',
                name: 'unit',
                align: 'center',
            },
            {
                header: '단가',
                name: 'unitPrice',
                align: 'center',
            },
            {
                header: '필요수량',
                name: 'quantityRequired',
                align: 'center',
                editor: 'text',
				validation: {
				    required: true,
				    regExp: /^\d{1,3}$/,
				    min: 1,
				    max: 1000
				}
            },
        ],
        rowHeaders: ['checkbox', 'rowNum'],
        pageOptions: {
            useClient: true,
            perPage: 10
        }
    });


    const gridBom = new tui.Grid({
        el: document.getElementById('gridBom'),
        scrollX: false,
        scrollY: false,
        columns: [
            {
                header: 'BOM코드',
                name: 'bomCode',
                align: 'center',
                sortingType: 'asc',
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
                header: '제품명',
                name: 'prdName',
                align: 'center',
                sortingType: 'desc',
                sortable: true                
            },
            {
                header: '등록일',
                name: 'regDate',
                align: 'center',
                sortingType: 'desc',
                sortable: true                
            },
            {
                header: '담당자',
                name: 'manager',
                align: 'center',
                sortingType: 'desc',
                sortable: true                
            },
            /*
            {
                header: '설명',
                name: 'description',
                align: 'center',
                sortingType: 'desc',
                sortable: true                
            },            
			*/
            {
                header: '메모',
                name: 'comm',
                align: 'center',
                sortingType: 'desc',
                sortable: true                
            },
            /*            
            {
                header: '소요수량',
                name: 'quantityRequired',
                align: 'center',
                sortingType: 'desc',
                sortable: true                
            },
            {
                header: '자재코드',
                name: 'mtCode',
                align: 'center',
                sortingType: 'desc',
                sortable: true                
            },
            {
                header: '순서',
                name: 'no',
                align: 'center',
                sortingType: 'desc',
                sortable: true                
            },
            */

        ],
        rowHeaders: ['checkbox', 'rowNum'],
        pageOptions: {
            useClient: true,
            perPage: 5
        }        
    });
    
    const gridBomDetail = new tui.Grid({
        el: document.getElementById('gridBomDetail'),
        scrollX: false,
        scrollY: false,
        columns: [
            {
                header: 'BOM상세코드',
                name: 'bdetailCode', //bDetailCode
                align: 'center',
                sortingType: 'desc',
                sortable: true                
            },			
            {
                header: 'BOM코드',
                name: 'bomCode',
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
            {
                header: '자재코드',
                name: 'mtCode',
                align: 'center',
                sortingType: 'asc',
                sortable: true                 
             
            },
            /*
            {
                header: '설명',
                name: 'description',
                align: 'center',
                sortingType: 'desc',
                sortable: true                
            },
            {
                header: '등록일',
                name: 'regDate',
                align: 'center',
                sortingType: 'desc',
                sortable: true                
            },
            {
                header: '담당자',
                name: 'manager',
                align: 'center',
                sortingType: 'desc',
                sortable: true                
            },
            {
                header: '메모',
                name: 'comm',
                align: 'center',
                sortingType: 'desc',
                sortable: true                
            },
            */
		   
            {
                header: '필요수량',
                name: 'quantityRequired',
                align: 'center',
                sortingType: 'desc',
                sortable: true                
            },
            /*
            {
                header: '순서',
                name: 'no',
                align: 'center',
                sortingType: 'desc',
                sortable: true                
            },
            */
        ],
        rowHeaders: ['checkbox', 'rowNum'],
        pageOptions: {
            useClient: true,
            perPage: 5
        }        
    });
    
    const gridBomDetailModal = new tui.Grid({
        el: document.getElementById('gridBomDetailModal'),
        scrollX: false,
        scrollY: false,
        columns: [
            {
                header: 'BOM상세코드',
                name: 'bdetailCode', //bDetailCode
                align: 'center',
                sortingType: 'desc',
                sortable: true                
            },			
            {
                header: 'BOM코드',
                name: 'bomCode',
                align: 'center',
                sortingType: 'desc',
                sortable: true                
            },
            {
                header: '자재코드',
                name: 'mtCode',
                align: 'center',
                sortingType: 'asc',
                sortable: true                
             
            },
            {
                header: '필요수량',
                name: 'quantityRequired',
                align: 'center',
                sortingType: 'desc',
                sortable: true                
            },
        ],
        rowHeaders: ['checkbox', 'rowNum'],
        pageOptions: {
            useClient: true,
            perPage: 5
        }        
    });


    const gridPrd = new tui.Grid({
        el: document.getElementById('gridPrd'),
        scrollX: false,
        scrollY: false,
        columns: [
            {
                header: '제품코드',
                name: 'prdCode',
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
            {
                header: 'BOM코드',
                name: 'bomCode',
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
        rowHeaders: ['checkbox', 'rowNum'],
        pageOptions: {
            useClient: true,
            perPage: 5
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

    document.getElementById('registerBtn').addEventListener('click', function() {
        const selectedRows = grid.getCheckedRows();
        
		const rowKeys = selectedRows.map(row => row.rowKey);
		
		if (selectedRows.length > 0) {
		    const validation = grid.validate();
		    
		    const invalidRows = validation.filter(result => 
		        rowKeys.includes(result.rowKey)
		    );
		
		    if (invalidRows.length > 0) {
		        alert('입력값을 확인하세요.');
		        return false;
		    }
			/*						
			const description = document.querySelector('textarea[name="description"]').value;
			if (!description || description.trim() === '') {
			    alert('설명을 입력하세요.');
			    return false;
			}
			*/			
			
        	if (confirm("새로운 BOM을 등록하시겠습니까??") == true){
        		//registerBoms(selectedRows.map(row => row.mtCode));
        		registerBoms(selectedRows);
        	}else{
        		return false;
        	}
        	
        } else {
            alert('자재를 선택하세요.');
        }
    });
    
    document.getElementById('mtModalRegisterBtn').addEventListener('click', function() {
        const selectedRows = gridMtModal.getCheckedRows();
        
		const rowKeys = selectedRows.map(row => row.rowKey);
		
		if (selectedRows.length > 0) {
		    const validation = gridMtModal.validate();
		    
		    const invalidRows = validation.filter(result => 
		        rowKeys.includes(result.rowKey)
		    );
		
		    if (invalidRows.length > 0) {
		        alert('입력값을 확인하세요.');
		        return false;
		    }
						
			const description = document.querySelector('textarea[name="description"]').value;
			if (!description || description.trim() === '') {
			    alert('설명을 입력하세요.');
			    return false;
			}			
			
        	if (confirm("새로운 BOM을 등록하시겠습니까??") == true){
        		//registerBoms(selectedRows.map(row => row.mtCode));
        		//registerBoms(selectedRows);
        		registerPrdBom(selectedRows);
        	}else{
        		return false;
        	}
        	
        } else {
            alert('자재를 선택하세요.');
            return false;
        }
        
        $('#mtModal').modal('hide');

        
        
    });

    function registerPrdBom(selectedRows) {
		
		document.querySelector('textarea[name="description"]').value
		
		const editedRows = selectedRows.map(row => ({
		    ...row,
		    description: document.querySelector('textarea[name="description"]').value,
		    regDate: document.querySelector('input[type="date"][name="regDate"]').value,
		    manager: document.querySelector('input[type="text"][name="manager"]').value,
		    
		    prdCode: document.querySelector('input[type="text"][name="prdCode"]').value,
		    prdName: document.querySelector('input[type="text"][name="prdName"]').value,
		    unitPrice: document.querySelector('input[type="text"][name="unitPrice"]').value,
		    prdCode: document.querySelector('input[type="text"][name="prdCode"]').value,
		    prdCode: document.querySelector('input[type="text"][name="prdCode"]').value,
		}));

		
        fetch('/registerPrdBoms', {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(editedRows),
        })
	    .then(response => {
	        if (response.ok) {
				
				document.querySelector('textarea[name="description"]').value = '';
			    fetchPrds();
			    fetchBoms();
			    fetchBomDetails();
			    
			    //grid.uncheckAll();

		    	gridPrd.addRowClassName(0, 'bg-success');
		    	gridBom.addRowClassName(0, 'bg-success');
			    selectedRows.forEach((row , index) => {
					gridBomDetail.addRowClassName(index, 'bg-success');
				});
	        }
	        
	        return response.json();
	    })
        .then(result => {

            console.log('result:', result);
        })
        .catch(error => {
            console.error('Error:', error);
        });
    }
    
    function registerBoms(selectedRows) {
		
		document.querySelector('textarea[name="description"]').value
		
		const editedRows = selectedRows.map(row => ({
		    ...row,
		    description: document.querySelector('textarea[name="description"]').value,
		    regDate: document.querySelector('input[type="date"][name="regDate"]').value,
		    manager: document.querySelector('input[type="text"][name="manager"]').value,
		}));

		
        fetch('/registerBoms', {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(editedRows),
        })
	    .then(response => {
	        if (response.ok) {
				
				document.querySelector('textarea[name="description"]').value = '';
			    fetchMtList();
			    fetchBoms();
			    fetchBomDetails();
			    
			    //grid.uncheckAll();

		    	gridBom.addRowClassName(0, 'bg-success');
			    selectedRows.forEach((row , index) => {
					gridBomDetail.addRowClassName(index, 'bg-success');
				});
	        }
	        
	        return response.json();
	    })
        .then(result => {

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
                //gridBom.refreshLayout();
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
                //gridBomDetail.refreshLayout();
                
            })
            .catch(error => {
                console.error(error);
            });
    }
    fetchBomDetails();

    function fetchPrds(search = {}) {
        const params = new URLSearchParams(search);
        const url = `/prds?${params.toString()}`;

        fetch(url)
            .then(response => response.json())
            .then(result => {
                gridPrd.resetData(result);
            })
            .catch(error => {
                console.error(error);
            });
    }
    fetchPrds();

	/*
	gridBom.on('onGridUpdated', (ev) => {
	    gridBom.getData().map(row => {
	        //gridBom.addRowClassName(row.rowKey,'bg-warning');
	        gridBom.addCellClassName(row.rowKey, 'manager', 'bg-warning');
	        
	    });
	});
	*/
	/*
	grid.on('check', (ev) => {
	    grid.addRowClassName(ev.rowKey, 'bg-light');
	});
	
	grid.on('uncheck', (ev) => {
	    grid.removeRowClassName(ev.rowKey, 'bg-light');
	});
	*/
	
	gridBom.on('click', (ev) => {
	    const rowKey = ev.rowKey;
	    
		const bomCode = gridBom.getValue(rowKey, 'bomCode');
		
		if(bomCode){
	        const params = new URLSearchParams({
			    bomCode: bomCode,
			}); 
	        
	        const url = `/bomDetails?${params.toString()}`;
		
	        fetch(url)
	        .then(response => response.json())
	        .then(result => {
	            gridBomDetailModal.resetData(result);
	            //document.getElementById('gridBomDetailModal').style.opacity = 1;
	            $('#bomDetailModal').modal('show');
	            gridBomDetailModal.refreshLayout();            
	        })
	        .catch(error => {
	            console.error(error);
	        });
		}
    });
    
    //수정필요.
    
    document.getElementById('openPrdBtn').addEventListener('click', function() {
		fetchMtModal();
    });
    
    function fetchMtModal(search = {}) {
        const params = new URLSearchParams(search);
        const url = `/mts?${params.toString()}`;

        fetch(url)
            .then(response => response.json())
            .then(result => {
                gridMtModal.resetData(result);
            	$('#mtModal').modal('show');
            	gridMtModal.refreshLayout();                  
            })
            .catch(error => {
                console.error(error);
            });
    }
        


});		 