/*
* lineProcessList.js
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
                header: '라인명',
                name: 'lineName',
                align: 'center',
                sortingType: 'desc',
                sortable: true,
                editor: 'text'                
            },
            {
                header: '제품코드',
                name: 'prdCode',
                align: 'center',
                sortingType: 'desc',
                sortable: true,
                editor: 'text',                  
            },
            /*
            {
                header: '제품명',
                name: 'prdName',
                align: 'center',
                sortingType: 'desc',
                sortable: true,
            },
            */
            {
                header: '최근수정일',
                name: 'lineDate',
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
        rowHeaders: ['checkbox', 'rowNum'],
        pageOptions: {
            useClient: true,
            perPage: 10
        }
    });
    
    const gridLineInsertModal = new tui.Grid({
        el: document.getElementById('gridLineInsertModal'),
        scrollX: false,
        scrollY: false,
        columns: [
			/*
            {
                header: '라인코드',
                name: 'lineCode',
                align: 'center',
                sortingType: 'desc',
                sortable: true                
            },
            */
            {
                header: '라인명',
                name: 'lineName',
                align: 'center',
                sortingType: 'desc',
                sortable: true,
                editor: 'text',
				validation: {
				    required: true,
				    regExp: /^[가-힣a-zA-Z0-9]+$/
				},                                
            },
            {
                header: '제품코드',
                name: 'prdCode',
                align: 'center',
                sortingType: 'desc',
                sortable: true,
                editor: 'text',                  
            },
            /*
            {
                header: '제품명',
                name: 'prdName',
                align: 'center',
                sortingType: 'desc',
                sortable: true,
            },
            */
            {
                header: '사용여부',
                name: 'use',
                align: 'center',
                sortingType: 'desc',
                sortable: true                  
            },
        ],
        rowHeaders: ['rowNum'],
        /*
        pageOptions: {
            useClient: true,
            perPage: 10
        }
        */
    });

	/*
    document.getElementById('searchProcessBtn').addEventListener('click', function() {
        const search = {
        		lineCode: document.getElementById('inputLineCode').value,
        };
        fetchlines(search);
    });
	*/
    function fetchLines(search = {}) {
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
    fetchLines();
    
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
                header: '공정명',
                name: 'processName',
                align: 'center',
                sortingType: 'desc',
                sortable: true                  
            },
            /*
            {
                header: '공정구분',
                name: 'prccessType',
                align: 'center',
                sortingType: 'desc',
                sortable: true                  
            },
            */
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

	/*
    document.getElementById('searchProcessBtn').addEventListener('click', function() {
        const search = {
        		processCode: document.getElementById('inputProcessCode').value,
        };
        fetchprocesses(search);
    });
	*/
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
    
    
    document.getElementById('updateLineBtn').addEventListener('click', function() {
	    //const modifiedRows = grid.getModifiedRows();
	    const modifiedRows = gridLine.getModifiedRows().updatedRows;
	    
	    if (modifiedRows.length === 0) {
	        //alert('수정된 데이터가 없습니다.');
			Swal.fire({
                icon: 
                //'success';	// v
                //'error',		// X
                'warning',		// !	
                //'info',		// i
                //'question', 	// ?
                text: '수정된 데이터가 없습니다.',
            });	        
	        return;
	    }
	

		const isConfirmed = Swal.fire({
	            title: '라인 수정',
	            text: "라인 수정하시겠습니까?",
                icon: 
                //'success';	// v
                //'error',		// X
                //'warning',		// !	
                'info',		// i
                //'question', 	// ?
	            showCancelButton: true,
	            confirmButtonColor: '#3085d6',
	            cancelButtonColor: '#d33',
	            confirmButtonText: '수정', // 수정.
	            cancelButtonText: '취소'
            }).then((result) => {
                if (result.isConfirmed) {
					// do something.
					modifyLines(modifiedRows);
					
                    Swal.fire({
                        icon: 'success',
                        title: '라인 수정완료',
                        text: '라인 수정이 완료 되었습니다.',
                    });
                }else{
    				return false;
				}
        	});

	
		/*
		if (confirm("수정 하시겠습니까??")){
			modifyLines(modifiedRows);
		}else{
			return;
		}
		*/	
	});    
    
    function modifyLines(modifiedRows) {
	    fetch('lines', {
	        method: 'POST',
	        headers: {
	            'Content-Type': 'application/json',
	        },
	        body: JSON.stringify(modifiedRows),
	    })
	    .then(response => {
	        if (response.ok) {
				const modifiedRowKeys = [
				  ...modifiedRows.map(row => row.rowKey),
				];
				
				modifiedRowKeys.forEach(rowKey => {
					
				  if (rowKey !== undefined) {
				    gridLine.addRowClassName(rowKey, 'bg-warning');
				  }
		  		});
	        }
	        
	        return response.json();
	    })
	    .then(result => {
			fetchlines();
			//gridLine.reloadData();
	        console.log(result);
	    })
	    .catch(error => {
	        console.error('Error: ', error);
	    })
	    .finally(() => {
	    	
	    });
    }
    
    
    
	function searchlines() {
	    const lineVO = {
	        lineCode: $('#inputLineCode').val(),
	        prdCode: $('#inputPrdCode').val(),
	    };
	
	    $.ajax({
	        url: "lines",
	        method: "GET",
	        data: lineVO,
	        success: function(response) {
	            gridLine.resetData(response);
	        },
	        error: function(error) {
	            console.log(error);
	        }
	    });
	}
	$('#inputLineCode, #inputPrdCode').on('input', searchlines);
	
	    
	function searchProcesses() {
	    const processVO = {
	        ProcessCode: $('#inputProcessCode').val(),
	        ProcessName: $('#inputProcessName').val(),
	    };
	
	    $.ajax({
	        url: "processes",
	        method: "GET",
	        data: processVO,
	        success: function(response) {
	            gridProcess.resetData(response);
	        },
	        error: function(error) {
	            console.log(error);
	        }
	    });
	}
	$('#inputProcessCode, #inputProcessName').on('input', searchProcesses);    
        

	document.addEventListener('click', (e) => {
	    gridLine.finishEditing();
	    gridProcess.finishEditing();
	});
	
	
	function fetchSetColums(){
        const url = `prds`;

        fetch(url)
        .then(response => response.json())
        .then(result => {
			
			const listPrdCode = result.map(productVO => ({
				value: productVO.prdCode,
				text: productVO.prdCode + " " + productVO.prdName
			}));
			
			const listPrdName = result.map(productVO => ({
				value: productVO.prdName,
				text: productVO.prdName
			}));
			
            gridLineSetColums(listPrdCode);
            
        })
        .catch(error => {
            console.error(error);
        });
	}
	fetchSetColums();

	function gridLineSetColums(listPrdCode){
		
	    const columns = [
	            {
	                header: '라인코드',
	                name: 'lineCode',
	                align: 'center',
	                sortingType: 'desc',
	                sortable: true,
	            },
	            {
	                header: '라인명',
	                name: 'lineName',
	                align: 'center',
	                sortingType: 'desc',
	                sortable: true,
	                editor: 'text',
	                
	            },
	            {
	                header: '제품코드',
	                name: 'prdCode',
	                align: 'center',
	                sortingType: 'desc',
	                sortable: true,
	                editor: 'text',
	                editor: {
	                  type: 'select',
	                  options: {
	                    listItems : listPrdCode
	                  }
	                },	                                  
	            },
	            {
	                header: '최근수정일',
	                name: 'lineDate',
	                align: 'center',
	                sortingType: 'desc',
	                sortable: true                
	            },	            
	            /*
	            {
	                header: '제품명',
	                name: 'prdName',
	                align: 'center',
	                sortingType: 'desc',
	                sortable: true,
	            },
	            */
	            {
	                header: '사용여부',
	                name: 'use',
	                align: 'center',
	                sortingType: 'desc',
	                sortable: true                  
	            },
	        ];
		
		
		gridLine.setColumns(columns);
	}
	
	
	function fetchSetColumsInsert(){
        const url = `prds`;

        fetch(url)
        .then(response => response.json())
        .then(result => {
			
			const listPrdCode = result.map(productVO => ({
				value: productVO.prdCode,
				text: productVO.prdCode + " " + productVO.prdName
			}));
			
			const listPrdName = result.map(productVO => ({
				value: productVO.prdName,
				text: productVO.prdName
			}));
			
            gridLineSetColumsInsert(listPrdCode);
            
        })
        .catch(error => {
            console.error(error);
        });
	}
	

	function gridLineSetColumsInsert(listPrdCode){
		
	    const columns = [
			
            {
                header: '라인명',
                name: 'lineName',
                align: 'center',
                sortingType: 'desc',
                sortable: true,
                editor: 'text',
				validation: {
				    required: true,
				    regExp: /^[가-힣a-zA-Z0-9]+$/
				},                                
            },
            {
                header: '제품코드',
                name: 'prdCode',
                align: 'center',
                sortingType: 'desc',
                sortable: true,
                editor: 'text',
                editor: {
                  type: 'select',
                  options: {
                    listItems : listPrdCode
                  }
                },	                                  
            },
            /*
            {
                header: '제품명',
                name: 'prdName',
                align: 'center',
                sortingType: 'desc',
                sortable: true,
            },
            */
            {
                header: '사용여부',
                name: 'use',
                align: 'center',
                sortingType: 'desc',
                sortable: true                  
            },	            

	        ];
		
		
		gridLineInsertModal.setColumns(columns);
	}


    document.getElementById('showLineInsertModalBtn').addEventListener('click', function() {
		gridLineInsertModal.clear();
        $('#lineInsertModal').modal('show');
		gridLineInsertModal.refreshLayout();
		fetchSetColumsInsert();		
		gridLineInsertModal.appendRow({'use': 'N'});
    });
    
    document.getElementById('appendLineBtn').addEventListener('click', function() {
		gridLineInsertModal.refreshLayout();
		gridLineInsertModal.appendRow({'use': 'N'});
    });
    
    document.getElementById('insertLineBtn').addEventListener('click', function() {
	    
	    const createdRows = gridLineInsertModal.getModifiedRows().createdRows;
	    const updatedRows = gridLineInsertModal.getModifiedRows().updatedRows;
	    //const modifiedRows = gridInsert.getData();
	    
	    console.log(createdRows);
	    console.log(updatedRows);
	    
	    
	    createdRows.forEach(object => {
			if	(object == null || object == ""){
		        //alert('데이터 입력하세요.');
				Swal.fire({
	                icon: 
	                //'success';	// v
	                //'error',		// X
	                'warning',		// !	
	                //'info',		// i	
	                //'question', 	// ?
	                text: '데이터 입력하세요.',
	            });				
		        return false;
			}
			
		});
	    
	    if (createdRows.length == 0) {
	        //alert('등록된 데이터가 없습니다.');
			Swal.fire({
                icon: 
                //'success';	// v
                //'error',		// X
                'warning',		// !	
                //'info',		// i	
                //'question', 	// ?
                text: '등록된 데이터가 없습니다.',
            });	        
	        return false;
	    }
	    
	    if(gridLineInsertModal.validate() == 0){
			// do nothing
		}else if (gridLineInsertModal.validate()[0].errors.length > 0) {
	        //alert('형식에 맞게 입력하세요.');
			Swal.fire({
                icon: 
                //'success';	// v
                //'error',		// X
                'warning',		// !	
                //'info',		// i	
                //'question', 	// ?
                text: '라인명은 한글, 영문, 숫자만 가능합니다.',
            });		        
	        return false;
	    }


		const isConfirmed = Swal.fire({
	            title: '라인 등록',
	            text: "라인 등록하시겠습니까?",
	            icon: 
	            //'success';	// v
	            //'error',		// X
	            //'warning',		// !	
	            'info',		// i
	            //'question', 	// ?
	            showCancelButton: true,
	            confirmButtonColor: '#3085d6',
	            cancelButtonColor: '#d33',
	            confirmButtonText: '등록',
	            cancelButtonText: '취소'
            }).then((result) => {	
                if (result.isConfirmed) {
					// do something.
					saveLines(createdRows);
					
                    Swal.fire({
                        icon: 'success',
                        title: '라인 등록완료',
                        text: '라인 등록이 완료 되었습니다.',
                    });
                }else{
    				return false;
				}
        	});

		/*
		if (confirm("등록 하시겠습니까??")){
			saveCps(createdRows);
		}else{
			return false;
		}
		*/
		
	});
	
	
    function saveLines(createdRows) {
	    fetch('lines', {
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
        			$('#lineInsertModal').modal('hide');
				    fetchLines();

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

	document.getElementById('deleteLineBtn').addEventListener('click', function() {
		
		const checkedRows = gridLine.getCheckedRows();
		console.log(checkedRows);
		if (checkedRows.length === 0) {
			//alert('삭제할 자재를 선택해주세요.');
			Swal.fire({
                icon: 
                //'success';	// v
                //'error',		// X
                'warning',		// !	
                //'info',		// i
                //'question', 	// ?
                text: '삭제할 라인를 선택해주세요.',
            });			
	    	return;
		}


		const isConfirmed = Swal.fire({
	            title: '라인 삭제',
	            text: "라인를 삭제하시겠습니까?",
                icon: 
                //'success';	// v
                //'error',		// X
                //'warning',		// !	
                'info',		// i
                //'question', 	// ?
	            showCancelButton: true,
	            confirmButtonColor: '#3085d6',
	            cancelButtonColor: '#d33',
	            confirmButtonText: '삭제', // 수정.
	            cancelButtonText: '취소'
            }).then((result) => {
                if (result.isConfirmed) {
					// do something.
					//deleteMts(checkedRows.map(row => row.MtCode)); // List<String>
					deleteLines(checkedRows); // List<MtVO>
					
                    Swal.fire({
                        icon: 'success',
                        title: '라인 삭제완료',
                        text: '라인 삭제가 완료 되었습니다.',
                    });
                }else{
    				return false;
				}
        	});
	  	/*
		if (confirm("삭제하시겠습니까??")){
			//deleteMts(checkedRows.map(row => row.MtCode)); // List<String>
			deleteMts(checkedRows); // List<MtVO>
		}else{
			return;
		}
		*/
	});

    function deleteLines(lineVOs) {
		/*
		$.ajax({
			url : 'mts',
			method : 'DELETE',
			data : mtCodes,
			success : function(response){
				console.log(response);
			},
			error : function(error){
				console.log(error);
			}
		});
		*/
		
		fetch('/lines', {
			method: 'DELETE',
			headers: {
			  'Content-Type': 'application/json',
			},
			body: JSON.stringify(lineVOs)
			})
		.then(response => response.json())
		.then(result => {
			console.log('Delete result:', result);
			gridLine.removeCheckedRows();
			gridLine.refreshLayout();
		})
		.catch(error => {
			console.error('Delete error:', error);
			//alert('삭제 중 오류가 발생했습니다.');
			Swal.fire({
                icon: 
                //'success';	// v
                'error',		// X
                //'warning',		// !	
                //'info',		// i
                //'question', 	// ?
                text: '삭제 중 오류가 발생했습니다.',
            });			
		});
    }



	document.addEventListener('click', (e) => {
	    gridLineInsertModal.finishEditing();
	});
        
});