/**
 * userListGrid.js
 */

 document.addEventListener('DOMContentLoaded', function() {
    const grid = new tui.Grid({
        el: document.getElementById('grid'),
        scrollX: false,
        scrollY: false,
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
                sortable: true                  
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
              
              /*
				{
				    header: '부서코드',
				    name: 'deptCode',
				    align: 'center',
				    sortingType: 'desc',
				    sortable: true                  
				},
				
				
				{
				    header: '부서명',
				    name: 'deptName',
				    align: 'center',
				    sortingType: 'desc',
				    sortable: true,
				}
	            */
        ],
        rowHeaders: ['checkbox', 'rowNum'],
        pageOptions: {
            useClient: true,
            perPage: 10
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

    // 데이터 수정을 위한 이벤트 리스너 추가
    grid.on('editingFinish', (event) => {
        const { rowKey, columnName, value } = event;
        grid.setValue(rowKey, columnName, value);
    });

    // 수정된 데이터를 다른 DB에 저장하는 함수
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
        })
        .catch(error => {
            console.error('Error:', error);
        });
    }      

    // 저장 버튼에 이벤트 리스너 설정
    document.getElementById('updateBtn').addEventListener('click', saveModifiedData);

    // 초기 데이터 로드
    fetchUsers();
});