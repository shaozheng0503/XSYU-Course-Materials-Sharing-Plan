
const AUTH_AUDIENCE = 0;
const AUTH_ADMIN = 1;
const AUTH_MANAGER = 2;

$(document).ready(function () {

    $("#login-btn").click(function () {
        var formData = getLoginForm();
        if (!validateLoginForm(formData)) {
            return;
        }

        postRequest(
            '/login',
            formData,
            function (res) {
                if (res.success) {
                    var user = res.content;
                    sessionStorage.setItem('username', user.username);
                    sessionStorage.setItem('id', user.id);
                    sessionStorage.setItem('auth', user.auth);
                    //根据用户身份的不同跳转至不同页面
                    if (user.auth == AUTH_AUDIENCE) {
                        window.location.href = "/user/home";
                    } else if (user.auth == AUTH_ADMIN) {
                        window.location.href = "/admin/movie/manage";
                    } else if (user.auth == AUTH_MANAGER) {
                        window.location.href = "/admin/movie/manage";
                    }
                } else {
                    alert(res.message);
                }
            },
            function (error) {
                alert(error);
            });
    });

    function getLoginForm() {
        return {
            username: $('#index-name').val(),
            password: $('#index-password').val()
        };
    }

    function validateLoginForm(data) {
        var isValidate = true;
        if (!data.username) {
            isValidate = false;
            $('#index-name').parent('.input-group').addClass('has-error');
            $('#index-name-error').css("visibility", "visible");
        }
        if (!data.password) {
            isValidate = false;
            $('#index-password').parent('.input-group').addClass('has-error');
            $('#index-password-error').css("visibility", "visible");
        }
        return isValidate;
    }
});