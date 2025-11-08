var presentId = 0;

$(document).ready(function () {
    mount(new AdminPanel({active: 8}), document.querySelector(".nav-left-container"));

    getRoleList();

    renderSelect();

    $('#add-submit-btn').click(function () {
        var form = getAddForm();
        if (!validateEditForm(form)) {
            return;
        }
        postRequest(
            '/role/add',
            form,
            function (res) {
                if (res.success) {
                    alert('新增角色成功!');
                    getRoleList();
                    $('#addRoleModal').modal("hide");
                } else {
                    alert(res.message);
                }
            },
            function (error) {
                alert(JSON.stringify(error));
            }
        );
    });

    $('#edit-submit-btn').click(function () {
        var editForm = getEditForm(presentId);
        if (!validateEditForm(editForm)) {
            return;
        }
            postRequest(
                '/role/edit',
                editForm,
                function (res) {
                    if (res.success) {
                        alert("修改成功!");
                        getRoleList();
                        $('#editRoleModal').modal("hide");
                    } else {
                        alert(res.message);
                    }
                },
                function (error) {
                    alert(JSON.stringify(error));
                }
            );

    });

});

/**
 * 根据身份渲染下拉菜单
 * @author hxw
 * @date 2019-5-21
 */
function renderSelect() {
    if (sessionStorage.getItem('auth') == 2) {
        var managerContent = "<option selected='selected'>影院员工</option>" +
            "<option>管理员</option>";
        $('#edit-auth-input').html(managerContent);
        $('#add-auth-input').html(managerContent);
    }
}

function getRoleList() {
    getRequest(
        "/role/get?level="+sessionStorage.getItem('auth'),
        function (res) {
            if (res.success) {
                renderRole(res.content);
            } else {
                alert(res.message);
            }
        },
        function (error) {
            alert(JSON.stringify(error));
        }
    );
}

/**
 * 渲染影院角色表格
 * @author hxw
 * @date 2019-5-20
 * @param roles
 */
function renderRole(roles) {
    var roleTableContent = '';
    for (let role of roles) {
        roleTableContent +=
            '<tr>' +
            '<td>' + role.username + '</td>' +
            '<td>' + role.password + '</td>' +
            '<td>' + role.auth +'</td>' +
            '<td>' + getEditBtn(role.password,role.id,role.username,role.auth) + '</td>' +
            '<td>' + getDelBtn(role.password,role.id) + '</td>' +
            '</tr>';
    }
    $('#role-table').html(roleTableContent);
}

/**
 * 获取修改按钮的HTML
 * @author hxw
 * @date 2019-5-20
 * @param password
 * @param id
 * @return
 */
function getEditBtn(password,id,username,auth) {
    if (password == "******") {
        return "<button class='btn btn-primary' disabled='disabled' onclick='editRole(\""+id+"\",\""+username+"\",\""+password+"\",\""+auth+"\")'>修改</button>";
    } else {
        return "<button class='btn btn-primary' onclick='editRole(\""+id+"\",\""+username+"\",\""+password+"\",\""+auth+"\")'>修改</button>";
    }
}

/**
 * 获取删除按钮的HTML
 * @author hxw
 * @date 2019-5-20
 * @param password
 * @param id
 * @return
 */
function getDelBtn(password,id) {
    if (password == "******") {
        return "<button class='btn btn-danger' disabled='disabled' onclick='delRole(\""+id+"\")'>删除</button>";
    } else {
        return "<button class='btn btn-danger' onclick='delRole(\""+id+"\")'>删除</button>";
    }
}

/**
 * 删除角色
 * @author hxw
 * @date 2019-5-20
 * @param id
 */
function delRole(id) {
    $('#roleDelModal').modal("toggle");
    $('#commitRoleDel').click(function () {
        getRequest(
            '/role/delete?id='+id,
            function (res) {
                if (res.success) {
                    alert("删除成功!");
                    getRoleList();
                    $('#roleDelModal').modal("hide");
                } else {
                    alert(res.message);
                }
            },
            function (error) {
                alert(JSON.stringify(error));
            }
        );
    });
}

/**
 * 修改角色信息
 * @param id
 * @param usn
 * @param pwd
 */
function editRole(id,usn,pwd,auth) {
    presentId = id;
    //渲染原有信息
    $('#edit-usn-input').val(usn);
    $('#edit-pwd-input').val(pwd);
    if (auth === "影院员工") {
        $('#edit-auth-input option:eq(0)').attr('selected','selected');
    } else if (auth === "管理员") {
        $('#edit-auth-input option:eq(1)').attr('selected','selected');
    }
    $('#editRoleModal').modal("toggle");
}

/**
 * 获取编辑信息表单
 * @author hxw
 * @date 2019-5-20
 * @return
 */
function getEditForm(id) {
    return {
        id: id,
        username: $('#edit-usn-input').val(),
        password: $('#edit-pwd-input').val(),
        auth: $('#edit-auth-input option:selected').text()
    };
}

/**
 * 检验编辑信息表单
 * @author hxw
 * @date 2019-5-20
 * @param form
 */
function validateEditForm(form) {
    if (!form.username) {
        alert("请输入用户名!");
        return false;
    }
    if (!form.password) {
        alert("请输入密码!");
        return false;
    }
    return true;
}

/**
 * 获取添加信息表单
 * @author hxw
 * @date 2019-5-21
 * @return
 */
function getAddForm() {
    return {
        id: 0, //实际上无用
        username: $('#add-usn-input').val(),
        password: $('#add-pwd-input').val(),
        auth: $('#add-auth-input option:selected').text()
    };
}