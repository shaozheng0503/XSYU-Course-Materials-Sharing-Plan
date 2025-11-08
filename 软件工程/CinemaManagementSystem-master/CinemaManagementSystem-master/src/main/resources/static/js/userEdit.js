
var rawPwd = '';
var newPwd = '';
var rawPwdReady = false;
var newPwdReady = false;
var checkReady = false;

$(document).ready(function () {
    mount(new UserHeader({active: 2}), document.querySelector("#nav-top-container"));

    $('#raw-pwd-input').blur(function () {
        var rawPwdInput = $('#raw-pwd-input').val();
        if (!rawPwdInput) {
            $('#raw-pwd-error-text').html("请输入原密码");
            $('#raw-pwd-error-text').css("visibility", "visible");
            rawPwdReady = false;
        } else {
            rawPwd = rawPwdInput;
            getRequest(
                '/user/checkPassword?rawPwd='+rawPwd,
                function (res) {
                    if (res.success) {
                        $('#raw-pwd-error-text').css("visibility", "hidden");
                        rawPwdReady = true;
                    } else {
                        $('#raw-pwd-error-text').html("原密码不正确");
                        $('#raw-pwd-error-text').css("visibility", "visible");
                        rawPwdReady = false;
                    }
                },
                function (error) {
                    alert(JSON.stringify(error));
                    rawPwdReady = false;
                }
            );
        }
    });

    $('#new-pwd-input').blur(function () {
        var newPwdInput = $('#new-pwd-input').val();
        if (!newPwdInput) {
            $('#new-pwd-error-text').html("请输入新密码");
            $('#new-pwd-error-text').css("visibility", "visible");
            newPwdReady = false;
        } else if (newPwdInput == rawPwd) {
            $('#new-pwd-error-text').html("新密码不能与原密码一致");
            $('#new-pwd-error-text').css("visibility", "visible");
            newPwdReady = false;
        } else {
            $('#new-pwd-error-text').css("visibility", "hidden");
            newPwd = newPwdInput;
            newPwdReady = true;
        }
    });

    $('#check-new-pwd').blur(function () {
        var checkPwdInput = $('#check-new-pwd').val();
        if (!checkPwdInput) {
            $('#check-pwd-error-text').html("请再次输入新密码");
            $('#check-pwd-error-text').css("visibility", "visible");
            checkReady = false;
        } else {
            if (checkPwdInput == newPwd) {
                $('#check-pwd-error-text').css("visibility", "hidden");
                checkReady = true;
            } else {
                $('#check-pwd-error-text').html("输入与新密码不一致");
                $('#check-pwd-error-text').css("visibility", "visible");
                checkReady = false;
            }
        }
    });

    $('#submit-new-pwd').click(function () {
        if (rawPwdReady && newPwdReady && checkReady) {
            postRequest(
                '/user/edit/password',
                {
                    username: sessionStorage.getItem("username"),
                    password: newPwd
                },
                function (res) {
                    if (res.success) {
                        alert("修改成功!");
                        window.location.href="/user/home";
                    } else {
                        alert(error.message);
                    }
                },
                function (error) {
                    alert(JSON.stringify(error));
                }
            );
        }
        return;
    })

});