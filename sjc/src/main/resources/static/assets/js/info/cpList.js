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
                header: '전화번호',
                name: 'tel',
                align: 'center',
                sortingType: 'desc',
                sortable: true                  
            },
            {
                header: '비고',
                name: 'comm',
                align: 'center',
                sortingType: 'desc',
                sortable: true,
				ellipsis: true,
				renderer: {
					type: TooltipRenderer
				}                                  
            }
        ],
        rowHeaders: ['rowNum'],
        pageOptions: {
            useClient: true,
            perPage: 10
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
                header: '전화번호',
                name: 'tel',
                align: 'center',
                sortingType: 'desc',
                sortable: true,
                editor: 'text',                                  
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
            perPage: 3
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
	    
	    if(gridCpInsertModal.validate() == 0){
			// do nothing
		}else if (gridCpInsertModal.validate()[0].errors.length > 0) {
	        //alert('형식에 맞게 입력하세요.');
			Swal.fire({
                icon: 
                //'success';	// v
                //'error',		// X
                'warning',		// !	
                //'info',		// i	
                //'question', 	// ?
                text: '업체명은 한글 및 영문만 가능합니다.',
            });		        
	        return false;
	    }


		const isConfirmed = Swal.fire({
	            title: '업체 등록',
	            text: "업체 등록하시겠습니까?",
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
					saveCps(createdRows);
					
                    Swal.fire({
                        icon: 'success',
                        title: '업체 등록완료',
                        text: '업체 등록이 완료 되었습니다.',
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
				console.log(result);
	            //gridCpModal.resetData(result);
	            //$('#CpModal').modal('show');
	            //gridCpModal.refreshLayout();
	            setSidePanel(result[0]);
    			//$('#updateBtn, .custom-switch').toggle(true);            
	        })
	        .catch(error => {
	            console.error(error);
	        });
		}
    });    
    
    function setSidePanel(cpVO){
		$('#cpCode').val(cpVO.cpCode);
		$('#cpName').val(cpVO.cpName);
		$('#cpType').val(cpVO.cpType);
		$('#businessNo').val(cpVO.businessNo);
		$('#address').val(cpVO.address);
		$('#tel').val(cpVO.tel);
		$('#comm').val(cpVO.comm);
		
	    $('#deleteBtn').prop('disabled', false);

	}
	
	function clearSidePanel() {
	    
	    $('#cpCode').val("");
	    $('#cpName').val("");
	    $('#cpType').val("");
	    $('#businessNo').val("");
	    $('#addressNo').val("");
	    $('#tel').val("");
	    $('#comm').val("");
	
	    $('#deleteBtn, #updateBtn').prop('disabled', true);
	}

	$('#editModeSwitch').on('change', function() {
	    if(!$('#cpCode').val()) {
	        $(this).prop('checked', false);
	        //alert('업체를 선택하세요.');
			Swal.fire({
                icon: 
                //'success';	// v
                //'error',		// X
                'warning',		// !	
                //'info',		// i
                //'question', 	// ?
                text: '업체를 선택하세요.',
            });        
	        return false;
	    }
	
	    const isEnabled = $(this).is(':checked');
	    $('#updateBtn, #cpName, #cpType, #businessNo, #address, #tel, #comm').prop('disabled', !isEnabled);
	});

    
	document.addEventListener('click', (e) => {
	    gridCpModal.finishEditing();
	    gridCpInsertModal.finishEditing();
	});
	
	
    document.getElementById('deleteCpBtn').addEventListener('click', function() {
		const isConfirmed = Swal.fire({
	            title: '업체 삭제',
	            text: "업체 삭제하시겠습니까?",
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
					const getData = gridCpModal.getData();
					console.log(getData);
					
					const map = getData.map(row => ({
					    cpCode: row.cpCode,
					}));
					console.log(map);
					
					deleteCps(map);
								
                    Swal.fire({
                        icon: 'success',
                        title: '업체 삭제완료',
                        text: '업체 삭제가 완료 되었습니다.',
                    });
                }else{
    				return false;
				}
        	});		
		/*
		if (confirm("삭제하시겠습니까?")){
			
			const getData = gridCpModal.getData();
			console.log(getData);
			
			const map = getData.map(row => ({
			    cpCode: row.cpCode,
			}));
			console.log(map);
			
			deleteCps(map);
		}else{
			return false;
		}
		*/
    });
	
	$('#deleteBtn').on('click', function(){
	const isConfirmed = Swal.fire({
            title: '업체 삭제',
            text: "업체 삭제하시겠습니까?",
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
				const getData = $('#cpCode').val();
				console.log(getData);
				
				const map = {
				    cpCode: getData,
				};
				const mapAry = [{
				    cpCode: getData,
				}];
				
				console.log(mapAry);
				deleteCps(mapAry);
							
                Swal.fire({
                    icon: 'success',
                    title: '업체 삭제완료',
                    text: '업체 삭제가 완료 되었습니다.',
                });
            }else{
				return false;
			}
    	});
    			
		/*
		if (confirm("삭제 하시겠습니까?")){
			
			const getData = $('#cpCode').val();
			console.log(getData);
			
			const map = {
			    cpCode: getData,
			};
			const mapAry = [{
			    cpCode: getData,
			}];
			
			console.log(mapAry);
			deleteCps(mapAry);
		}else{
			return false;
		}
		*/
	});    
    
    
    function deleteCps(CpVOs) {
		fetch('cps', {
			method: 'DELETE',
			headers: {
			  'Content-Type': 'application/json',
			},
			body: JSON.stringify(CpVOs)
			})
		.then(response => response.json())
		.then(result => {
			console.log('Delete result:', result);
			fetchCps();
			//$('#CpModal').modal('hide');
			clearSidePanel();
		})
		.catch(error => {
			console.error('Delete error:', error);
			//alert('삭제 중 오류가 발생했습니다.');
			Swal.fire({
                icon: 
                'erorr',		// !	
                text: '삭제 중 오류가 발생했습니다.',
            });			
		});
    }
    
    $('#updateBtn').on('click', function(){
		
		const isConfirmed = Swal.fire({
	            title: '업체 수정',
	            text: "업체를 수정하시겠습니까?",
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
			
					/*
					const getData = $('#cpCode').val();
					console.log(getData);
					const map = {
					    cpCode: getData,
					};
					*/
					
					const mapAry = [{
					    cpCode: $('#cpCode').val(),
					    cpName: $('#cpName').val(),
					    cpType: $('#cpType').val(),
					    businessNo: $('#businessNo').val(),
					    address: $('#address').val(),
					    tel: $('#tel').val(),
					    comm: $('#comm').val(),
					}];
					console.log(mapAry);
					updateCps(mapAry);
					
                    Swal.fire({
                        icon: 'success',
                        title: '업체 수정완료',
                        text: '업체 수정이 완료 되었습니다.',
                    });
                }else{
    				return false;
				}
        	});		
		
		/*		
		if (confirm("수정 하시겠습니까?")){
			
			const mapAry = [{
			    cpCode: $('#cpCode').val(),
			    cpName: $('#cpName').val(),
			    cpType: $('#cpType').val(),
			    businessNo: $('#businessNo').val(),
			    address: $('#address').val(),
			    tel: $('#tel').val(),
			    comm: $('#comm').val(),
			}];
			console.log(mapAry);
			updateCps(mapAry);
		}else{
			return false;
		}
		*/		
	});
    function updateCps(CpVOs) {
		/*
		fetch('cps', {
			method: 'PUT',
			headers: {
			  'Content-Type': 'application/json',
			},
			body: JSON.stringify(CpVOs)
			})
		.then(response => response.json())
		.then(result => {
			console.log(result);
			fetchCps();
		})
		.catch(error => {
			console.error(error);
		});
		*/
		
		$.ajax("cps", {
			method : 'POST', // method
			contentType : 'application/json',
			data: JSON.stringify(CpVOs)
		})
		.done(result => {
			console.log(result);
			//clearSidePanel();
			fetchCps();
			
			//$('#editModeSwitch').prop('checked', false).change();
			
			const checkbox = document.getElementById('editModeSwitch');
			checkbox.checked = false;
			checkbox.dispatchEvent(new Event('change'));
		});
    }
    
    $('#inputCpCode').on('input', function(){
		const cpCode = $('#inputCpCode').val();
		const cpName = $('#inputCpName').val();
		const cpType = $('#inputCpType').val();
		
		const cpVO = {
			cpCode : cpCode,
			cpName : cpName,
			cpType : cpType,
		}
		
		$.ajax({
			url : "cps",
			method : "GET",
			data : cpVO,	
		})
		.done(result => {
			grid.resetData(result);
		})
	});
	
    $('#inputCpName').on('input', function(){
		const cpCode = $('#inputCpCode').val();
		const cpName = $('#inputCpName').val();
		const cpType = $('#inputCpType').val();
		
		const cpVO = {
			cpCode : cpCode,
			cpName : cpName,
			cpType : cpType,
		}
		
		$.ajax({
			url : "cps",
			type : "GET",
			data : cpVO,
			success : function(response){
				grid.resetData(response);
			},
			error : function(error){
				console.log(error);
			}
		});
	});
	
    $('#inputCpType').on('change', function(){
		const cpCode = $('#inputCpCode').val();
		const cpName = $('#inputCpName').val();
		const cpType = $('#inputCpType').val();
		
		const cpVO = {
			cpCode : cpCode,
			cpName : cpName,
			cpType : cpType,
		}
		
	    $.get('cps', cpVO, function(response) {
			grid.resetData(response);
	    });
	});
				
        
});