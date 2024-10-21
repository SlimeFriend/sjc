/**
 * userListGridApi.js
 */

document.addEventListener('DOMContentLoaded', function() {
	const dataSource = {
		api: {
			readData: { 
			url: '/usersApi',
			method: 'GET',
	    	},
		},
	}
	
    const grid = new tui.Grid({
        el: document.getElementById('grid'),
		data: dataSource,
        scrollX: false,
        scrollY: false,
        rowHeaders: ['checkbox', 'rowNum'],
        columns: [
            {
                header: '사용자번호',
                name: 'userId',
                align: 'center',
                sortingType: 'desc',
                sortable: true                
            },
            {
                header: '아이디',
                name: 'loginId',
                align: 'center',
                sortingType: 'desc',
                sortable: true                  
            },
            {
                header: '이름',
                name: 'userName',
                align: 'center',
                sortingType: 'desc',
                sortable: true,
                editor: 'text',
                                  
            },
            {
                header: '권한',
                name: 'roleName',
                align: 'center',
                sortingType: 'desc',
                sortable: true                  
            },
            {
                header: '부서명',
                name: 'deptCode',
                align: 'center',
                formatter: 'listItemText',
                editor: {
                  type: 'select',
                  options: {
                    listItems: [
                      { text: '영업', value: 'SA' },
                      { text: '생산', value: 'PR' },
                      { text: '자재', value: 'MT' },
                      { text: '품질', value: 'QA' },
                      { text: '설비', value: 'EQ' },
                      { text: '전산', value: 'IT' },
                    ]
                  }
                }
              },
              {
			    header: '연락처',
			    name: 'phone',
			    align: 'center',
			    sortingType: 'desc',
			    sortable: true
			  },
        ],
        pageOptions: {
            useClient: false,
            perPage: 4
        }
    });
    
    const gridInsert = new tui.Grid({
        el: document.getElementById('gridInsert'),
		//data: dataSource,
        scrollX: false,
        scrollY: false,
        rowHeaders: ['checkbox', 'rowNum'],
        columns: [
            {
                header: '사용자번호',
                name: 'userId',
                align: 'center',
                sortingType: 'desc',
                sortable: true,                
                editor: 'text',
            },
            {
                header: '아이디',
                name: 'loginId',
                align: 'center',
                sortingType: 'desc',
                sortable: true,
                editor: 'text',
      
            },
            {
                header: '이름',
                name: 'userName',
                align: 'center',
                sortingType: 'desc',
                sortable: true,
                editor: 'text',
                                  
            },
            {
                header: '권한',
                name: 'roleName',
                align: 'center',
                formatter: 'listItemText',
                editor: {
                  type: 'select',
                  options: {
                    listItems: [
                      { text: 'ROLE_ADMIN', value: 'ROLE_ADMIN' },
                      { text: 'ROLE_USER', value: 'ROLE_USER' },
                    ]
                  }
                }                  
            },
            {
                header: '부서명',
                name: 'deptCode',
                align: 'center',
                formatter: 'listItemText',
                editor: {
                  type: 'select',
                  options: {
                    listItems: [
                      { text: '영업', value: 'SA' },
                      { text: '생산', value: 'PR' },
                      { text: '자재', value: 'MT' },
                      { text: '품질', value: 'QA' },
                      { text: '설비', value: 'EQ' },
                      { text: '전산', value: 'IT' },
                    ]
                  }
                }
              },
              {
			    header: '연락처',
			    name: 'phone',
			    align: 'center',
			    sortingType: 'desc',
			    sortable: true,
                editor: 'text',
			    
			  },
        ],
        pageOptions: {
            useClient: false,
            perPage: 3
        }
    });
    
    tui.Grid.applyTheme('striped');

    document.getElementById('searchBtn').addEventListener('click', function() {
		
        const searchParams = {
            userId: document.getElementById('inputUserId').value,
            loginId: document.getElementById('inputLoginId').value,
            roleName: document.getElementById('inputRoleName').value,
            userName: document.getElementById('inputUserName').value,
            deptCode: document.getElementById('inputDeptCode').value,
            deptName: document.getElementById('inputDeptName').value,
        };
        
  		grid.readData(1, searchParams);
    });
    
    grid.on('editingFinish', (event) => {
        const { rowKey, columnName, value } = event;
        grid.setValue(rowKey, columnName, value);
	  	console.log('grid editingFinish:', rowKey);
    });
    
    gridInsert.on('editingFinish', (event) => {
        const { rowKey, columnName, value } = event;
        gridInsert.setValue(rowKey, columnName, value);
	  	console.log('gridInsert editingFinish:', rowKey, columnName, value );
    });
	
    document.getElementById('updateBtn').addEventListener('click', function() {
	    //const modifiedRows = grid.getModifiedRows();
	    const modifiedRows = grid.getModifiedRows().updatedRows;
	    
	    if (modifiedRows.length === 0) {
	        alert('수정된 데이터가 없습니다.');
	        return;
	    }
	
		if (confirm("수정 하시겠습니까??")){
			modifyUsers(modifiedRows);
		}else{
			return;
		}	
	});

    function modifyUsers(modifiedRows) {
	    fetch('users', {
	        method: 'PUT',
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
				    grid.addRowClassName(rowKey, 'bg-warning');
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

    
    document.getElementById('saveBtn').addEventListener('click', function() {
		
	    //const modifiedRows = gridInsert.getModifiedRows().updatedRows;
	    const modifiedRows = gridInsert.getModifiedRows().createdRows;
	    
	    if (modifiedRows.length === 0) {
	        alert('등록된 데이터가 없습니다.');
	        return;
	    }
	
		if (confirm("등록 하시겠습니까??")){
			saveUsers(modifiedRows);
		}else{
			return;
		}
	});
	
	
    function saveUsers(modifiedRows) {
	    fetch('users', {
	        method: 'PUT',
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
				    gridInsert.addRowClassName(rowKey, 'bg-warning');
				    gridInsert.restore();
		    		document.getElementById('gridInsert').style.display="block";
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
	
	
	
    document.getElementById('insertBtn').addEventListener('click', function() {

		document.getElementById('gridInsert').style.display="block";
		gridInsert.refreshLayout();
		gridInsert.appendRow();
    });
    
    function insertUser(user) {
        fetch('/users', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(user),
        })
        .then(response => response.json())
        .then(result => {
            fetchUsers();
        })
        .catch(error => {
            console.error('Error:', error);
        });
    }

	document.getElementById('deleteBtn').addEventListener('click', function() {
		
		const checkedRows = grid.getCheckedRows();
		if (checkedRows.length === 0) {
			alert('삭제할 항목을 선택해주세요.');
	    	return;
		}
	  
		if (confirm("삭제하시겠습니까??")){
			deleteUsers(checkedRows.map(row => row.userId));
		}else{
			return;
		}
	});

    function deleteUsers(userIds) {
		fetch('/users', {
			method: 'DELETE',
			headers: {
			  'Content-Type': 'application/json',
			},
			body: JSON.stringify(userIds)
			})
		.then(response => response.json())
		.then(result => {
			console.log('Delete result:', result);
			grid.removeCheckedRows();
			grid.refreshLayout();
		})
		.catch(error => {
			console.error('Delete error:', error);
			alert('삭제 중 오류가 발생했습니다.');
		});
    }
    
    document.getElementById('copyBtn').addEventListener('click', function() {
        const selectedRows = grid.getCheckedRows();
        
        if (selectedRows.length > 0) {
        	if (confirm("복사하시겠습니까??") == true){
        		copyUsers(selectedRows.map(row => row.userId));
        	}else{
        		return false;
        	}
        } else {
            alert('복사할 사용자를 선택해주세요.');
        }
    });

    function copyUsers(userIds) {
        fetch('/copyUsers', {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(userIds),
        })
        .then(response => response.json())
        .then(result => {
            //fetchUsers();
            fetchCopyLogs();
            fetchCopyDetails();
            console.error('result:', result);
        })
        .catch(error => {
            console.error('Error:', error);
        });
    }      

    const gridCopyLog = new tui.Grid({
        el: document.getElementById('gridCopyLog'),
        scrollX: false,
        scrollY: false,
        rowHeaders: ['rowNum'],
        columns: [
            {
                header: '복사번호',
                name: 'logId',
                align: 'center',
                sortingType: 'desc',
                sortable: true                
            },
            {
                header: '입력일',
                name: 'copyDate',
                align: 'center',
                sortingType: 'desc',
                sortable: true                  
            },
        ],
        pageOptions: {
            useClient: true,
            perPage: 3
        }
    });

    function fetchCopyLogs(search = {}) {
        const params = new URLSearchParams(search);
        const url = `/copyLogs?${params.toString()}`;

        fetch(url)
            .then(response => response.json())
            .then(result => {
                gridCopyLog.resetData(result);
            })
            .catch(error => {
                console.error(error);
            });
    }

    fetchCopyLogs();
    
    const gridCopyDetail = new tui.Grid({
        el: document.getElementById('gridCopyDetail'),
        scrollX: false,
        scrollY: false,
        rowHeaders: ['rowNum'],
        columns: [
            {
                header: '복사상세번호',
                name: 'detailId',
                align: 'center',
                sortingType: 'desc',
                sortable: true                
            },
            {
                header: '복사번호',
                name: 'logId',
                align: 'center',
                sortingType: 'desc',
                sortable: true                
            },
            {
                header: '사용자번호',
                name: 'userId',
                align: 'center',
                sortingType: 'desc',
                sortable: true                  
            },
            {
                header: '아이디',
                name: 'loginId',
                align: 'center',
                sortingType: 'desc',
                sortable: true                  
            },
            {
                header: '이름',
                name: 'userName',
                align: 'center',
                sortingType: 'desc',
                sortable: true                  
            },
            {
                header: '부서코드',
                name: 'DeptCode',
                align: 'center',
                sortingType: 'desc',
                sortable: true                  
            },
            {
                header: '권한',
                name: 'roleName',
                align: 'center',
                sortingType: 'desc',
                sortable: true                  
            },
        ],
        pageOptions: {
            useClient: true,
            perPage: 3
        }
    });

    function fetchCopyDetails(search = {}) {
        const params = new URLSearchParams(search);
        const url = `/copyDetails?${params.toString()}`;

        fetch(url)
            .then(response => response.json())
            .then(result => {
                gridCopyDetail.resetData(result);
            })
            .catch(error => {
                console.error(error);
            });
    }

    fetchCopyDetails();    
    
});