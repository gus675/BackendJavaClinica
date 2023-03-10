window.addEventListener('load', function () {
    const formulario = document.querySelector('#add_new_turno');
    formulario.addEventListener('submit', function (event) {

            const formData = {

                    paciente:{id: document.querySelector('#id-paciente').value},
                    odontologo:{id: document.querySelector('#id-odontologo').value},
                    fecha: document.querySelector('#turno-fecha-hora').value,
//                    +":00"

                };

        const url = '/turnos';
        const settings = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(formData)
        }

        fetch(url, settings)
            .then(response => response.json())
            .then(data => {
                let successAlert = '<div class= "alert alert-success alert-dismissible">' +
                    '<button type= "button" class="close" data-dismiss="alert">&times;</button>' +
                    '<strong></strong> Turno Agregado </div>'

                document.querySelector('#response').innerHTML = successAlert;
                document.querySelector('#response').innerHTML = "block";
                resetUploadForm();
            })
            .catch(error => {
                let errorAlert = '<div class= "alert alert-danger alert-dismissible">' +
                '<button type="button" class="close" data-dismiss="alert">&times;</button>'
                '<strong> Error intente nuevamente</strong> </div>'

                document.querySelector('#response').innerHTML = errorAlert;
                document.querySelector('#response').style.display = "block";
                resetUploadForm();})
    });


    function resetUploadForm(){
        document.querySelector('#id-paciente').value = "";
        document.querySelector('#id-odontologo').value = "";
        document.querySelector('#turno-fecha-hora').value = "";

    }

    (function (){
        let pathname = window.location.pathname;
        if(pathname === "/"){
            document.querySelector(".nav .nav-item a:first").addClass("active");
        } else if(pathname == "/turnosList.html"){
            document.querySelector(".nav .nav-item a:last").addClass("active")
        }

    })
});