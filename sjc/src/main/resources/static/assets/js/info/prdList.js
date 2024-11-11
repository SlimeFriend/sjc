/**
 * bomList.js
 */
document.addEventListener('DOMContentLoaded', function() {
    
    
   
    
    
    //tui.Grid.applyTheme('striped');
    tui.Grid.applyTheme('default');
	    
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
                header: '자재명',
                name: 'mtName',
                align: 'center',
                sortingType: 'desc',
                sortable: true,
                ellipsis: true,
				renderer: {
					type: TooltipRenderer
				}                                 
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
    align: 'right',
    sortingType: 'desc',
    sortable: true,
    formatter: (props) => {
        if (!props.value) return '';
        return Number(props.value).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
    }
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
                header: '자재명',
                name: 'mtName',
                align: 'center',
                ellipsis: true,
				renderer: {
					type: TooltipRenderer
				}                
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
        scrollY: true,
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
                sortable: true,
                ellipsis: true,
				renderer: {
					type: TooltipRenderer
				}                
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
                header: '설명',
                name: 'description',
                align: 'center',
                sortingType: 'desc',
                sortable: true,
                ellipsis: true,
				ellipsis: true,
				renderer: {
					type: TooltipRenderer
				}                 
            },            
            /*            
            {
                header: '메모',
                name: 'comm',
                align: 'center',
                sortingType: 'desc',
                sortable: true                
            },
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
	    rowHeaders: [
			{
	        type: 'checkbox',
	        header: ' ',
	        checkAll: false,
	    	},
		    {
		        type: 'rowNum',
			},
	    ],        /*
        pageOptions: {
            useClient: true,
            perPage: 5
        }        
        */
	    bodyHeight: 160,
		pageOptions: {
		    type: 'scroll', 
		    perPage: 10 
		}, 
    });



    document.getElementById('bomBtn').addEventListener('click', function() {
		
		const bomCheckedRows = gridBom.getCheckedRows();
		const prdCheckedRows = gridPrd.getCheckedRows();
		
		//const selectedRowData = bomCheckedRows.length > 0 ? bomCheckedRows : null;
		const bom = bomCheckedRows.length > 0 ? bomCheckedRows[0] : null;
		const prd = prdCheckedRows.length > 0 ? prdCheckedRows[0] : null;
		
		if (bom) {
		    console.log('bom : ', bom);
		} else {
		    alert('선택된 BOM이 없습니다.');
		    return false;
		}
		
		if (prd) {
		    console.log('prd : ', prd);
		} else {
		    alert('선택된 제품이 없습니다.');
		    return false;
		}
		
		if(confirm("BOM 지정 하시겠습니까?")){
			// prd bom 업데이트
			// bom prd 업데이트
			const requestData = {
			    prd: prd,
			    bom: bom
			};			
			fetchBomPrd(requestData);
		}
    });
    function fetchBomPrd(requestData) {
	    fetch('prdBom', {
	        method: 'PUT',
	        headers: {
	            'Content-Type': 'application/json',
	        },
	        body: JSON.stringify(requestData),
	    })
	    .then(response => {
	        if (!response.ok) {
      			throw new Error('response error');
	        }
	        return response.json();
	    })
	    .then(result => {
	        //gridPrd.resetData(result.prd);
	        //gridBom.resetData(result.bom);
	        fetchPrds();
	        fetchBoms();
	        console.log(result.status);
	        console.log(result.message);
	    })
	    .catch(error => {
	        console.error('Error: ', error);
	    })
	    .finally(() => {
	    	
	    });
    }
	    
    const gridBomDetail = new tui.Grid({
        el: document.getElementById('gridBomDetail'),
        scrollX: false,
        scrollY: true,
        columns: [
            {
                header: 'BOM코드',
                name: 'bomCode',
                align: 'center',
                sortingType: 'desc',
                sortable: true,
                rowSpan: true,

            },
            {
                header: 'BOM상세코드',
                name: 'bdetailCode', //bDetailCode
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
	    bodyHeight: 160,
        rowHeaders: ['rowNum'],
        /*
        pageOptions: {
            useClient: true,
            perPage: 4
        } 
        */
        /*
	    bodyHeight: 200,
		pageOptions: {
		    type: 'scroll', 
		    perPage: 10 
		},        
		*/
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
                header: '자재명',
                name: 'mtName',
                align: 'center',
                sortingType: 'desc',
                sortable: true,
                ellipsis: true,
				renderer: {
					type: TooltipRenderer
				}
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
	    //selectionUnit: 'row',  // 행 단위 선택으로 설정
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
                sortable: true,
                ellipsis: true,
				renderer: {
					type: TooltipRenderer
				}
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
                header: '설명',
                name: 'description',
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
            },
            {
                header: 'BOM코드',
                name: 'bomCode',
                align: 'center',
                sortingType: 'desc',
                sortable: true                
            },            
        ],
		    rowHeaders: [
				{
		        type: 'checkbox',
		        header: ' ',
		        checkAll: false,
		    	},
			    {
			        type: 'rowNum',
				},
		    ],
            pageOptions: {
            useClient: true,
            perPage: 13
        }
    });
    
    const gridPrdModal = new tui.Grid({
        el: document.getElementById('gridPrdModal'),
        scrollX: false,
        scrollY: false,
	    //selectionUnit: 'row',  // 행 단위 선택으로 설정
        columns: [
            {
                header: '제품코드',
                name: 'prdCode',
                align: 'center',
                sortingType: 'desc',
                sortable: true,
            },
            {
                header: '제품명',
                name: 'prdName',
                align: 'center',
                sortingType: 'desc',
                sortable: true,
                editor: 'text',                
                ellipsis: true,
				renderer: {
					type: TooltipRenderer
				},
            },
            {
                header: '단가',
                name: 'unitPrice',
                align: 'center',
                sortingType: 'desc',
                sortable: true,
                editor: 'text',

                                                  
            },
            {
                header: '설명',
                name: 'description',
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
            },
            {
                header: 'BOM코드',
                name: 'bomCode',
                align: 'center',
                sortingType: 'desc',
                sortable: true,
            },            
        ],
        /*
	    rowHeaders: [
			{
	        type: 'checkbox',
	        header: ' ',
	        checkAll: false,
	    	},
		    {
		        type: 'rowNum',
			},
	    ],
	    */
		/*	   	
	    pageOptions: {
		    useClient: true,
		    perPage: 13
        }
        */
    });

	/*
	tui.Grid.applyTheme('default', {
	  	cell: {
	        rowHeader: {
	            border: '#fff',
	            showVerticalBorder: true,
	            verticalBorder: '#fff'
	        },
		    focused: {
		      border: 'none'
	    	},
	        selected: {
	            background: 'none',
	            border: 'none'
	        },    	
		    normal: {
		      background: '#eee',
		      border: '#fff',    
		      // text: '#000'
		    },
		    evenRow: {
		      background: '#fff',
		      border: '#000'    
		    },
		    oddRow: {
		      background: '#fff',
		      border: '#000'
		    }
	  	}
	});
	*/
	const customTheme = {
	  	cell: {
	        rowHeader: {
	            //border: '#fff',
	            showVerticalBorder: true,
	            verticalBorder: '#fff'
	        },
		    focused: {
		      border: 'none'
	    	},
	        selected: {
	            background: 'none',
	            border: 'none'
	        },
	            	
		    normal: {
		      background: '#eee',
		      //border: '',    
		      // text: '#000'
		    },
		    head: {
		      background: '#eee',
		      //border: '#fff',
		      text: '#208be4',
		    },
		    rowHead: {
		      //border: ''
		    },
		    selectedHead: {
		      background: '#eee',
		    },		    
		    evenRow: {
		      background: '#fff',
		      //border: ''    
		    },
		    oddRow: {
		      background: '#fff',
		      //border: ''
		    }
	  	}
	};
	
	tui.Grid.applyTheme('custom', customTheme);


	/*
	
	
	gridPrd.on('click', (ev) => {
	    // 체크박스 칼럼을 직접 클릭한 경우는 제외
	    if (ev.columnName !== '_checked') {
	        gridPrd.uncheckAll();
	        
	        gridPrd.check(ev.rowKey);
	        
	        const selectedRowData = gridPrd.getRow(ev.rowKey);
	        console.log('gridPrd : ', selectedRowData);
	        
	        if(selectedRowData == null){
				return false;
			}
	    }
	    
		gridPrd.setSelectionRange({
		    start: [ev.rowKey, 0],
		    end: [ev.rowKey, gridPrd.getColumns().length]
		});	    
	    
	});
	*/
/*
const events = [
    // 셀 관련 이벤트
    'beforeChange',
    'afterChange',
    'click',
    'dblclick',
    'mousedown',
    //'mouseover',
    //'mouseout',
    'focusChange',
    'focusedCell',
    'key',
    'beforeKeyDown',
    'afterKeyDown',
    
    // 체크박스 관련
    'check',
    'uncheck',
    
    // 선택 관련
    'selection',
    'selectionStart',
    'selectionEnd',
    
    // 편집 관련
    'editingStart',
    'editingFinish',
    'beforeEditStart',
    'afterEditStart',
    'beforeComplete',
    'afterComplete',
    
    // 행 관련
    'expand',
    'collapse',
    'beforeExpandAll',
    'expandAll',
    'beforeCollapseAll',
    'collapseAll',
    'rowMounted',
    'rowUpdated',
    
    // 정렬/필터 관련
    'sort',
    'filter',
    
    // 스크롤 관련
    'scrollEnd',
    'beforeScroll',
    'afterScroll',
    
    // 컬럼 관련
    'columnResize',
    'beforeCreateEditor',
    'afterCreateEditor',
    
    // 기타
    'paste',
    'drag',
    'drop'
];

// 모든 이벤트 리스너 등록
events.forEach(eventName => {
    gridPrd.on(eventName, (ev) => {
        console.log(`${eventName} event:`, ev);
    });
});
*/


	gridPrd.on('check', (ev) => {
		const grid = gridPrd;
			
	    const checkedRows = grid.getCheckedRows();
	    if (checkedRows.length == 0) return false;
	    
	    if (checkedRows.length > 1 && checkedRows != null) {
	        checkedRows.forEach(row => {
	            if (row.rowKey !== ev.rowKey) {
	                grid.uncheck(row.rowKey);
	            }
	        });
	    }
	});

	gridPrd.on('mousedown', (ev) => {
		
		const grid = gridPrd;	
	    // 체크박스 칼럼을 직접 클릭한 경우는 제외
		if (ev.columnName !== '_checked') {
	        const checkedRowKeys = grid.getCheckedRowKeys();
	        const currentRowChecked = checkedRowKeys[0] === ev.rowKey;
	
	        if (currentRowChecked) {
	            grid.uncheck(ev.rowKey);
	        } else {
		    	grid.uncheckAll();
		    	grid.check(ev.rowKey);
		    	const selectedRowData = grid.getRow(ev.rowKey);
		    	console.log('mousedown1 : ', selectedRowData);
	
	    		if(selectedRowData == null){
	            	return false;
	        	}
	        }
	
	       	grid.setSelectionRange({
	        	start: [ev.rowKey, 0],
	        	end: [ev.rowKey, grid.getColumns().length]
	    	});
		}
	});



	gridBom.on('mousedown', (ev) => {
		const grid = gridBom;	
	   // 체크박스 칼럼을 직접 클릭한 경우는 제외
	   if (ev.columnName !== '_checked') {
	       const checkedRowKeys = grid.getCheckedRowKeys();
	       const currentRowChecked = checkedRowKeys[0] === ev.rowKey;
	
	       if (currentRowChecked) {
	           grid.uncheck(ev.rowKey);
	       } else {
	           grid.uncheckAll();
	           grid.check(ev.rowKey);
	           const selectedRowData = grid.getRow(ev.rowKey);
	           console.log('gridPrd : ', selectedRowData);
	
	           if(selectedRowData == null){
	               return false;
	           }
	       }
	
	       grid.setSelectionRange({
	           start: [ev.rowKey, 0],
	           end: [ev.rowKey, grid.getColumns().length]
	       });
	   }
	   
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
	            //gridBomDetailModal.resetData(result);
	            //document.getElementById('gridBomDetailModal').style.opacity = 1;
	            //$('#bomDetailModal').modal('show');
	            //gridBomDetailModal.refreshLayout();
	            gridBomDetail.resetData(result);
	            //gridBomDetail.refreshLayout();            
	        })
	        .catch(error => {
	            console.error(error);
	        });
		}
		   
	});
	



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
	            //gridBomDetailModal.resetData(result);
	            //document.getElementById('gridBomDetailModal').style.opacity = 1;
	            //$('#bomDetailModal').modal('show');
	            //gridBomDetailModal.refreshLayout();
	            gridBomDetail.resetData(result);
	            //gridBomDetail.refreshLayout();            
	        })
	        .catch(error => {
	            console.error(error);
	        });
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

    document.getElementById('mtModalRegisterBtn').addEventListener('click', function() {
        const selectedRows = gridMtModal.getCheckedRows();
        
		const rowKeys = selectedRows.map(row => row.rowKey);
		
		if($('input:checkbox[name="customSwitchBom"]').val() == "on"){
			if (selectedRows.length > 0) {
			    const validation = gridMtModal.validate();
			    
			    const invalidRows = validation.filter(result => 
			        rowKeys.includes(result.rowKey)
			    );
			
			    if (invalidRows.length > 0) {
			        alert('필요수량 값을 확인하세요.');
			        return false;
			    }
	        } else {
	            alert('자재를 선택하세요.');
	            return false;
	        }
			
		}
	    const prdCode = document.querySelector('input[type="text"][name="prdCode"]').value;
		if (!prdCode || prdCode.trim() === '') {
		    alert('제품코드를 입력하세요.');
		    return false;
		}	    
	    const prdName = document.querySelector('input[type="text"][name="prdName"]').value;
		if (!prdName || prdName.trim() === '') {
		    alert('제품명을 입력하세요.');
		    return false;
		}	    
	    const unitPrice = document.querySelector('input[type="text"][name="unitPrice"]').value;
		if (!unitPrice || unitPrice.trim() === '') {
		    alert('단가를 입력하세요.');
		    return false;
		}	    

		const description = document.querySelector('textarea[name="description"]').value;
		if (!description || description.trim() === '') {
		    alert('설명을 입력하세요.');
		    return false;
		}			
		
    	if (confirm("새로운 제픔을 등록하시겠습니까??") == true){
    		//registerBoms(selectedRows.map(row => row.mtCode));
    		//registerBoms(selectedRows);
    		registerPrdBom(selectedRows);
    	}else{
    		return false;
    	}
        	
        
        $('#mtModal').modal('hide');
	    document.querySelector('textarea[name="description"]').value="";
	    //document.querySelector('input[type="date"][name="regDate"]').value="";
	    document.querySelector('input[type="text"][name="manager"]').value="";
	    document.querySelector('input[type="text"][name="prdCode"]').value="";
	    document.querySelector('input[type="text"][name="prdName"]').value="";
	    document.querySelector('input[type="text"][name="unitPrice"]').value="";
	    document.querySelector('input[type="text"][name="comm"]').value="";

        
        
    });

    function registerPrdBom(selectedRows) {
		
	
		let customSwitchBom= false;
		
		if($('#customSwitchBom').prop('checked')){
			customSwitchBom = "true";
		}else{
			customSwitchBom = "false";
			
		}

		let editedRows;		
		
		if($('#customSwitchBom').prop('checked')){
			
			editedRows = selectedRows.map(row => ({
			    ...row,
			    description: document.querySelector('textarea[name="description"]').value,
			    regDate: document.querySelector('input[type="date"][name="regDate"]').value,
			    manager: document.querySelector('input[type="text"][name="manager"]').value,
			    
			    prdCode: document.querySelector('input[type="text"][name="prdCode"]').value,
			    prdName: document.querySelector('input[type="text"][name="prdName"]').value,
			    unitPrice: document.querySelector('input[type="text"][name="unitPrice"]').value,
			    comm: document.querySelector('input[type="text"][name="comm"]').value,
			    customSwitchBom: customSwitchBom,
			}));
		}else{
			editedRows = [{
			    description: document.querySelector('textarea[name="description"]').value,
			    regDate: document.querySelector('input[type="date"][name="regDate"]').value,
			    manager: document.querySelector('input[type="text"][name="manager"]').value,
			    
			    prdCode: document.querySelector('input[type="text"][name="prdCode"]').value,
			    prdName: document.querySelector('input[type="text"][name="prdName"]').value,
			    unitPrice: document.querySelector('input[type="text"][name="unitPrice"]').value,
			    comm: document.querySelector('input[type="text"][name="comm"]').value,
			    customSwitchBom: customSwitchBom,
			}];
		}
		

		
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

		    	/*
		    	gridPrd.addRowClassName(0, 'bg-success');
		    	gridBom.addRowClassName(0, 'bg-success');
			    selectedRows.forEach((row , index) => {
					gridBomDetail.addRowClassName(index, 'bg-success');
				});
				*/
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
                gridBom.refreshLayout();
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
                gridBomDetail.refreshLayout();
                
            })
            .catch(error => {
                console.error(error);
            });
    }
    //fetchBomDetails();

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

    
    document.getElementById('openPrdBtn').addEventListener('click', function() {
		fetchMtModal();
    });
    
    function fetchMtModal(search = {}) {
        const params = new URLSearchParams(search);
        const url = `/mts?${params.toString()}`;

        fetch(url)
        .then(response => response.json())
        .then(result => {
        	$('#customSwitchBom').prop('checked', false); //체크 해제
            gridMtModal.resetData(result);
        	$('#mtModal').modal('show');
        	gridMtModal.refreshLayout();
  			gridMtModal.disable();

        })
        .catch(error => {
            console.error(error);
        });
    }
    
    document.getElementById('updatePrdBtn').addEventListener('click', function() {
    
		const checkedRows = gridPrd.getCheckedRows();
		
		if (checkedRows.length === 0) {
			alert('수정할 제품을 선택해주세요.');
	    	return;
		}
		/*
		// get
		fetchPrdModal({
			prdCode: checkedRows.map(row => row.prdCode)
		});
		*/
		//post 
		fetchPrdModal({
			prdCode : checkedRows[0].prdCode,
		});
		
    });


    function fetchPrdModal(prdCodes){
		/*
		// get
        const params = new URLSearchParams(prdCodes);
        const url = `prds?${params.toString()}`;		
		fetch(url)
		*/
		
		// post
		fetch("prds",{
			method: "POST",
			headers: {
				"Content-Type" : "application/json"
			},
			body: JSON.stringify(prdCodes),
		})
		
		.then(response => response.json())
		.then(result => {
        	$('#prdModal').modal('show');
			gridPrdModal.resetData(result);
        	gridPrdModal.refreshLayout();
        	
			const gridData = gridPrdModal.getData();
			gridData.forEach((row) => {
			  	gridPrdModal.addCellClassName(row.rowKey,'prdName' ,'bg-warning');
			  	gridPrdModal.addCellClassName(row.rowKey,'unitPrice' ,'bg-warning');
			  	gridPrdModal.addCellClassName(row.rowKey,'description' ,'bg-warning');
			  	gridPrdModal.addCellClassName(row.rowKey,'comm' ,'bg-warning');
			});
        				
		})
		.catch(err => {
			console.log(err);
		})
	}


    document.getElementById('customSwitchBom').addEventListener('change', function() {
		
		if($(this).prop('checked')){
			gridMtModal.enable();
			console.log($(this).prop('checked'));
			console.log($('#customSwitchBom').prop('checked'));
		}else{
			gridMtModal.disable();
			console.log($(this).prop('checked'));
			console.log($('#customSwitchBom').prop('checked'));
		}
		
    });
    
    
	document.getElementById('deletePrdBtn').addEventListener('click', function() {
		
		const checkedRows = gridPrd.getCheckedRows();
		if (checkedRows.length === 0) {
			alert('삭제할 제품을 선택해주세요.');
	    	return;
		}
	  
		if (confirm("삭제 하시겠습니까??")){
			deletePrds(checkedRows.map(row => row.prdCode));
		}else{
			return;
		}
	});    
    function deletePrds(prdCodes){
		fetch("prds",{
			method: "DELETE",
			headers: {
				"Content-Type" : "application/json"
			},
			body: JSON.stringify(prdCodes),
		})
		.then(response => response.json())
		.then(result => {
			gridPrd.removeCheckedRows();
			fetchBoms();
			
		})
		.catch(err => {
			console.log(err);
		})
	}
    
    document.getElementById('prdModalUpdateBtn').addEventListener('click', function() {
	    //const modifiedRows = grid.getModifiedRows();
	    const updatedRows = gridPrdModal.getModifiedRows().updatedRows;
	    
	    if (updatedRows.length > 0) {
			if (confirm("수정 하시겠습니까??")){
				updatePrds(updatedRows);
			}else{
				return;
			}	
	    }else if(confirm("수정한 내용이 없습니다. 종료 하시겠습니까?")){
	        $('#prdModal').modal('hide');
			return false;
			
		}
	});
    function updatePrds(updatedRows) {
	    fetch('prds', {
	        method: 'PUT',
	        headers: {
	            'Content-Type': 'application/json',
	        },
	        body: JSON.stringify(updatedRows),
	    })
	    .then(response => response.json())
	    .then(result => {
	        console.log(result);
	        //fetchUsers();
	        $('#prdModal').modal('hide');
	        fetchPrds();
	    })
	    .catch(error => {
	        console.error('Error: ', error);
	    })
	    .finally(() => {
	    	
	    });
    }	
	
	document.addEventListener('click', (e) => {
	    gridMtModal.finishEditing();
	    gridPrdModal.finishEditing();
	});    
	
});		 