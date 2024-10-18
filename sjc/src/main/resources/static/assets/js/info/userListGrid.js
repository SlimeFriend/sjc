/**
 * userListGrid.js
 */

 document.addEventListener('DOMContentLoaded', function() {
    const grid = new tui.Grid({
        el: document.getElementById('grid'),
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
            useClient: true,
            perPage: 4
        }
    });

    document.getElementById('searchBtn').addEventListener('click', function() {
        const search = {
            userId: document.getElementById('inputUserId').value,
            loginId: document.getElementById('inputLoginId').value,
            roleName: document.getElementById('inputRoleName').value,
            userName: document.getElementById('inputUserName').value,
            deptCode: document.getElementById('inputDeptCode').value,
            deptName: document.getElementById('inputDeptName').value,
        };
        fetchUsers(search);
    });

    function fetchUsers(search = {}) {
        const params = new URLSearchParams(search);
        const url = `/users?${params.toString()}`;

        fetch(url)
            .then(response => response.json())
            .then(result => {
                grid.resetData(result);
            })
            .catch(error => {
                console.error(error);
            });
    }

    grid.on('editingFinish', (event) => {
        const { rowKey, columnName, value } = event;
        grid.setValue(rowKey, columnName, value);
    });

    document.getElementById('updateBtn').addEventListener('click', saveModifiedData);
    
	function saveModifiedData() {
	    const modifiedData = grid.getModifiedRows();
	    
	    if (modifiedData.updatedRows.length === 0) {
	        alert('수정된 데이터가 없습니다.');
	        return;
	    }
	
	    fetch('users', {
	        method: 'PUT',
	        headers: {
	            'Content-Type': 'application/json',
	        },
	        body: JSON.stringify(modifiedData.updatedRows),
	    })
	    .then(response => {
	        if (!response.ok) {
	            throw new Error(`HTTP error! status: ${response.status}`);
	        }
	        return response.json();
	    })
	    .then(result => {
	        fetchUsers();
	    })
	    .catch(error => {
	        console.error('Error:', error);
	        alert(`저장 오류: ${error.message || '알 수 없는 오류가 발생했습니다.'}`, 'error');
	    })
	    .finally(() => {
	    	
	    });
	}
    
    /*
    document.getElementById('insertBtn').addEventListener('click', function() {
        const newUser = {
            loginId: document.getElementById('inputLoginId').value,
            userName: document.getElementById('inputUserName').value,
            roleName: document.getElementById('inputRoleName').value,
            deptCode: document.getElementById('inputDeptCode').value,
            // Add other necessary fields
        };
        insertUser(newUser);
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
    */

    document.getElementById('deleteBtn').addEventListener('click', function() {
        const selectedRows = grid.getCheckedRows();
        if (selectedRows.length > 0) {
        	
        	if (confirm("삭제하시겠습니까??") == true){
        		deleteUsers(selectedRows.map(row => row.userId));
        	}else{
        		return false;
        	}
        	
        } else {
            alert('삭제할 사용자를 선택해주세요.');
        }
    });


    function deleteUsers(userIds) {
        fetch('/users', {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(userIds),
        })
        .then(response => response.json())
        .then(result => {
            fetchUsers();
        })
        .catch(error => {
            console.error('Error:', error);
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
            fetchUsers();
            fetchCopyLogs();
            fetchCopyDetails();
        })
        .catch(error => {
            console.error('Error:', error);
        });
    }      


    fetchUsers();
    
    
    
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