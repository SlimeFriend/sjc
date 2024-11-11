/**
 * mtList.js
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
                sortable: true,
                /*
                editor: {
                  type: 'select',
                  options: {
                    listItems: [
                      { text: '당류', value: '당류' },
                      { text: '유가공품', value: '유가공품' },
                      { text: '과자류', value: '과자류' },
                      { text: '초콜릿류', value: '초콜릿류' },
                      { text: '부속자재', value: '부속자재' },
                    ]
                  }
                },
                */                                  
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
                sortable: true,
				formatter: function(e){
				   if (!e.value) return '';
				   return e.value.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
				},                                   
            },
            {
                header: '안전재고',
                name: 'safetyStock',
                align: 'center',
                sortingType: 'desc',
                sortable: true,
				formatter: function(e){
				   if (!e.value) return '';
				   return e.value.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
				},                                   
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
                sortable: true,
				formatter: function(e){
				    if (!e.value) return '';
				    return moment(e.value).format('YYYY-MM-DD');
				},                                 
            },
            {
                header: '현재재고',
                name: 'currentStc',
                align: 'center',
                sortingType: 'desc',
                sortable: true,
				formatter: function(e){
				   if (!e.value) return '';
				   return e.value.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
				},                                   
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
    
    const gridMtUpdateModal = new tui.Grid({
        el: document.getElementById('gridMtUpdateModal'),
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
                sortable: true,
                editor: 'text',                  
            },
            {
                header: '자재구분',
                name: 'materialType',
                align: 'center',
                sortingType: 'desc',
                sortable: true,
                editor: {
                  type: 'select',
                  options: {
                    listItems: [
                      { text: '당류', value: '당류' },
                      { text: '유가공품', value: '유가공품' },
                      { text: '과자류', value: '과자류' },
                      { text: '초콜릿류', value: '초콜릿류' },
                      { text: '부속자재', value: '부속자재' },
                    ]
                  }
                },                                  
            },
            {
                header: '규격',
                name: 'specification',
                align: 'center',
                sortingType: 'desc',
                sortable: true,
                editor: 'text',                                  
            },
            {
                header: '단위',
                name: 'unit',
                align: 'center',
                sortingType: 'desc',
                sortable: true,
                editor: 'text',                                  
            },
            {
                header: '단가',
                name: 'unitPrice',
                align: 'center',
                sortingType: 'desc',
                sortable: true,
                editor: 'text',                
				formatter: function(e){
				   if (!e.value) return '';
				   return e.value.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
				},                                   
            },
            {
                header: '안전재고',
                name: 'safetyStock',
                align: 'center',
                sortingType: 'desc',
                sortable: true,
                editor: 'text',                
				formatter: function(e){
				   if (!e.value) return '';
				   return e.value.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
				},                                   
            },
            {
                header: '재고코드',
                name: 'stcCode',
                align: 'center',
                sortingType: 'desc',
                sortable: true,
                editor: 'text',                
                                  
            },
            {
                header: '재고변동일',
                name: 'updateDate',
                align: 'center',
                sortingType: 'desc',
                sortable: true,
				formatter: function(e){
				    if (!e.value) return '';
				    return moment(e.value).format('YYYY-MM-DD');
				},
          
			},
            {
                header: '현재재고',
                name: 'currentStc',
                align: 'center',
                sortingType: 'desc',
                sortable: true,
                editor: 'text',                
				formatter: function(e){
				   if (!e.value) return '';
				   return e.value.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
				},                                   
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
                sortable: true,
                editor: 'text',                                  
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
        rowHeaders: ['rowNum'],
        /*
        pageOptions: {
            useClient: true,
            perPage: 15
        }
        */
    });
    
    const gridMtInsertModal = new tui.Grid({
        el: document.getElementById('gridMtInsertModal'),
        scrollX: false,
        scrollY: false,
        columns: [
			/*
            {
                header: '자재코드',
                name: 'mtCode',
                align: 'center',
                sortingType: 'desc',
                sortable: true                
            },
            */
            {
                header: '자재이름',
                name: 'mtName',
                align: 'center',
                sortingType: 'desc',
                sortable: true,
                editor: 'text',                  
            },
            {
                header: '자재구분',
                name: 'materialType',
                align: 'center',
                sortingType: 'desc',
                sortable: true,
                editor: {
                  type: 'select',
                  options: {
                    listItems: [
                      { text: '당류', value: '당류' },
                      { text: '유가공품', value: '유가공품' },
                      { text: '과자류', value: '과자류' },
                      { text: '초콜릿류', value: '초콜릿류' },
                      { text: '부속자재', value: '부속자재' },
                    ]
                  }
                },                                  
            },
            {
                header: '규격',
                name: 'specification',
                align: 'center',
                sortingType: 'desc',
                sortable: true,
                editor: 'text',                                  
            },
            {
                header: '단위',
                name: 'unit',
                align: 'center',
                sortingType: 'desc',
                sortable: true,
                editor: 'text',                                  
            },
            {
                header: '단가',
                name: 'unitPrice',
                align: 'center',
                sortingType: 'desc',
                sortable: true,
                editor: 'text',                
				formatter: function(e){
				   if (!e.value) return '';
				   return e.value.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
				},                                   
            },
            {
                header: '안전재고',
                name: 'safetyStock',
                align: 'center',
                sortingType: 'desc',
                sortable: true,
                editor: 'text',                
				formatter: function(e){
				   if (!e.value) return '';
				   return e.value.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
				},                                   
            },
            {
                header: '재고코드',
                name: 'stcCode',
                align: 'center',
                sortingType: 'desc',
                sortable: true,
                editor: 'text',                                  
            },
            /*
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
                sortable: true,
                editor: 'text',                
				formatter: function(e){
				   if (!e.value) return '';
				   return e.value.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
				},                                   
            },
            */
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
                sortable: true,
                editor: 'text',                                  
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
            perPage: 4
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



    document.getElementById('showMtModalBtn').addEventListener('click', function() {
		gridMtInsertModal.clear();
        $('#mtInsertModal').modal('show');
		gridMtInsertModal.refreshLayout();
		gridMtInsertModal.appendRow();
    });
    
    document.getElementById('appendMtBtn').addEventListener('click', function() {
		gridMtInsertModal.refreshLayout();
		gridMtInsertModal.appendRow();
    });
    
    document.getElementById('insertMtBtn').addEventListener('click', function() {
	    // 빈 값 입력 시 오류 있음.
	    let createdRows = gridMtInsertModal.getModifiedRows().createdRows;
	    let updatedRows = gridMtInsertModal.getModifiedRows().updatedRows;

		//createdRows = updatedRows;
	    
	    console.log('createdRows');
	    console.log(createdRows);
	    console.log('updatedRows');
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
	                text: '데이터를 입력하세요.',
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
	    
	    if(gridMtInsertModal.validate() == 0){
			// do nothing
		}else if (gridMtInsertModal.validate()[0].errors.length > 0) {
	        //alert('형식에 맞게 입력하세요.');
			Swal.fire({
                icon: 
                //'success';	// v
                //'error',		// X
                'warning',		// !	
                //'info',		// i
                //'question', 	// ?
                text: '형식에 맞게 입력하세요.',
            });		        
	        return false;
	    }

		const isConfirmed = Swal.fire({
	            title: '자재 등록',
	            text: "자재를 등록하시겠습니까?",
                icon: 
                //'success';	// v
                //'error',		// X
                //'warning',		// !	
                'info',		// i
                //'question', 	// ?
	            showCancelButton: true,
	            confirmButtonColor: '#3085d6',
	            cancelButtonColor: '#d33',
	            confirmButtonText: '등록', // 수정.
	            cancelButtonText: '취소'
            }).then((result) => {
                if (result.isConfirmed) {
					// do something.
					saveMts(createdRows);
					
                    Swal.fire({
                        icon: 'success',
                        title: '자재 등록완료',
                        text: '자재 등록이 완료 되었습니다.',
                    });
                }else{
    				return false;
				}
        	});

		/*
		if (confirm("등록 하시겠습니까??")){
			saveMts(createdRows);
		}else{
			return false;
		}
		*/
		
	});
	
	
    function saveMts(createdRows) {
	    fetch('mts', {
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
        			$('#mtInsertModal').modal('hide');
				    fetchMtList();

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

		
	//$('deleteMtBtn').on('click', function(){
	document.getElementById('deleteMtBtn').addEventListener('click', function() {
		
		const checkedRows = grid.getCheckedRows();
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
                text: '삭제할 자재를 선택해주세요.',
            });			
	    	return;
		}


		const isConfirmed = Swal.fire({
	            title: '자재 삭제',
	            text: "자재를 삭제하시겠습니까?",
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
					deleteMts(checkedRows); // List<MtVO>
					
                    Swal.fire({
                        icon: 'success',
                        title: '자재 삭제완료',
                        text: '자재 삭제가 완료 되었습니다.',
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

    function deleteMts(mtCodes) {
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
		
		fetch('/mts', {
			method: 'DELETE',
			headers: {
			  'Content-Type': 'application/json',
			},
			body: JSON.stringify(mtCodes)
			})
		.then(response => response.json())
		.then(result => {
			console.log('Delete result:', result);
			grid.removeCheckedRows();
			grid.refreshLayout();
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


	grid.on('click', (ev) => {
	    
	    // 체크 버튼 클릭 시 종료.
		if (ev.columnName == '_checked') {
			return false;	
		}
			    
	    const rowKey = ev.rowKey;
		const mtCode = grid.getValue(rowKey, 'mtCode');
		
		if(mtCode){
	        const params = new URLSearchParams({
			    mtCode: mtCode,
			}); 
	        
	        const url = `/mts?${params.toString()}`;
		
	        fetch(url)
	        .then(response => response.json())
	        .then(result => {
				console.log(result);
	            gridMtUpdateModal.resetData(result);
	            $('#mtUpdateModal').modal('show');
	            gridMtUpdateModal.refreshLayout();
	        })
	        .catch(error => {
	            console.error(error);
	        });
		}
    });



    document.getElementById('updateMtBtn').addEventListener('click', function() {
	    const modifiedRows = gridMtUpdateModal.getModifiedRows();
	    const updatedRows = modifiedRows.updatedRows;
	    
	    if (updatedRows.length > 0) {
			
			const isConfirmed = Swal.fire({
		            title: '자재 수정',
		            text: "자재를 수정하시겠습니까?",
	                icon: 
	                //'success';	// v
	                //'error',		// X
	                //'warning',		// !	
	                'info',		// i
	                //'question', 	// ?
		            showCancelButton: true,
		            confirmButtonColor: '#3085d6',
		            cancelButtonColor: '#d33',
		            confirmButtonText: '등록', // 수정.
		            cancelButtonText: '취소'
	            }).then((result) => {
	                if (result.isConfirmed) {
						// do something.
						updateMts(updatedRows);
						
	                    Swal.fire({
	                        icon: 'success',
	                        title: 'BOM 등록완료',
	                        text: 'BOM 등록이 완료 되었습니다.',
	                    });
	                }else{
	    				return false;
					}
	        	});			
			/*
			if (confirm("수정 하시겠습니까??")){
				updateMts(updatedRows);
			}else{
				return false;
			}
			*/	

	    }else {

			const isConfirmed = Swal.fire({
		            title: '수정 사항 없음',
		            text: "수정한 내용이 없습니다. 종료 하시겠습니까?",
	                icon: 
	                //'success';	// v
	                //'error',		// X
	                'warning',		// !	
	                //'info',		// i
	                //'question', 	// ?
		            showCancelButton: true,
		            confirmButtonColor: '#3085d6',
		            cancelButtonColor: '#d33',
		            confirmButtonText: '확인', // 수정.
		            cancelButtonText: '취소'
	            }).then((result) => {
	                if (result.isConfirmed) {
						// do something.
				        $('#mtUpdateModal').modal('hide');
						return false;

	                }else{
	    				return false;
					}
	        	});
		}
		/*			
	    }else if(confirm("수정한 내용이 없습니다. 종료 하시겠습니까?")){
	        $('#mtUpdateModal').modal('hide');
			return false;
		}
		*/
	});
    function updateMts(updatedRows) {
	    fetch('mts', {
	        method: 'POST',
	        headers: {
	            'Content-Type': 'application/json',
	        },
	        body: JSON.stringify(updatedRows),
	    })
	    .then(response => response.json())
	    .then(result => {
	        console.log(result);
	        //fetchUsers();
	        $('#mtUpdateModal').modal('hide');
	        fetchMtList();
	    })
	    .catch(error => {
	        console.error('Error: ', error);
	    })
	    .finally(() => {
	    	
	    });
    }

    $('#inputMtCode').on('input', function(){
		const mtCode = $('#inputMtCode').val();
		const mtName = $('#inputMtName').val();
		const materialType = $('#materialType').val();
		
		const mtVO = {
			mtCode : mtCode,
			mtName : mtName,
			materialType : materialType,
		}
		
		$.ajax({
			url : "mts",
			method : "GET",
			data : mtVO,	
		})
		.done(result => {
			grid.resetData(result);
		})
	});
	
    $('#inputMtName').on('input', function(){
		const mtCode = $('#inputMtCode').val();
		const mtName = $('#inputMtName').val();
		const materialType = $('#materialType').val();
		
		const mtVO = {
			mtCode : mtCode,
			mtName : mtName,
			materialType : materialType,
		}
		
		$.ajax({
			url : "mts",
			method : "GET",
			data : mtVO,	
		})
		.done(result => {
			grid.resetData(result);
		})
	});

    $('#materialType').on('input', function(){
		const mtCode = $('#inputMtCode').val();
		const mtName = $('#inputMtName').val();
		const materialType = $('#materialType').val();
		
		const mtVO = {
			mtCode : mtCode,
			mtName : mtName,
			materialType : materialType,
		}
		
		$.ajax({
			url : "mts",
			method : "GET",
			data : mtVO,	
		})
		.done(result => {
			grid.resetData(result);
		})
	});


	document.addEventListener('click', (e) => {
	    gridMtInsertModal.finishEditing();
	    gridMtUpdateModal.finishEditing();
	});


});		 