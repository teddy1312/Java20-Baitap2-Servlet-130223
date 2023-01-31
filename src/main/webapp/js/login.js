$(document).ready(function (){
    $('#btn-login').click(function (e){
        e.preventDefault()
        const email = $('#enter-email').val()
        const password = $('#enter-password').val()

        $.ajax({
            method: 'POST',
            url: `http://localhost:8080/api/login`,
            data:{
                'email': email,
                'password': password
            }
        }).done(function (data){
            if (data.statusCode == 500) {
                window.location.href = data.data
            } else if (data.statusCode == 200) {
                alert('Email ' + data.data + ' không tồn tại hoặc mật khẩu không đúng. Vui lòng nhập lại!')
                document.getElementById("enter-password").value = ''
            }
        })
    })
})