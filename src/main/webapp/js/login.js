$(document).ready(function (){
    $('#btn-login').click(function (e){
        e.preventDefault()
        const account = $('#enter-account').val()
        const password = $('#enter-password').val()

        $.ajax({
            method: 'POST',
            url: `http://localhost:8080/login`,
            data:{
                'email': account,
                'password': password
            }
        }).done(function (data){
            if (data.statusCode == 200) {
                alert('Email ' + data.data + ' không tồn tại hoặc mật khẩu không đúng. Vui lòng nhập lại!')
                //document.getElementById("enter-account").value = ''
                document.getElementById("enter-password").value = ''
            } else if (data.statusCode == 500) {
                window.location.href = data.data
            }
        })
    })
})