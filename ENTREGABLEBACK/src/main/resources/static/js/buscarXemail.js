window.addEventListener('load', function () {

    const input = document.querySelector("input");
    const button = document.querySelector("button");
    const registroContainer = document.querySelector(".registro-container");

    button.addEventListener("click", (e) =>{
    e.preventDefault();
    const botonEmail = (input.value);
    console.log(botonEmail)

            const url = '/pacientes';
            const settings = {
                method: 'GET',
                }

            fetch(url,settings)
            .then(response => response.json())
            .then(data => {
            console.log(data)
                const regPaciente = data.filter(paciente => paciente.email === botonEmail)
                console.log(regPaciente)

                for(paciente of regPaciente){

                            var table = document.getElementById("pacienteTable");
                            var pacienteRow =table.insertRow();
                            let tr_id = 'tr_' + paciente.id;
                            pacienteRow.id = tr_id;

                            let deleteButton = '<button' +
                                                      ' id=' + '\"' + 'btn_delete_' + paciente.id + '\"' +
                                                      'type="button" onclick="deleteBy('+paciente.id+')" class="btn btn-secondary btn_id" disabled/>'+
                                                      '&times'
                                                      '</button>';

                            let updateButton = '<button' +
                                                      ' id=' + '\"' + 'btn_id_' + paciente.id + '\"' +
                                                      ' type="button" onclick="findBy('+paciente.id+')" class="btn btn-secondary btn_id" disabled/>' +
                                                       paciente.id+
                                                       '</button>';

                            pacienteRow.innerHTML = '<td>' +updateButton+ '</td>'+
                                    '<td class=\"td_first_name\">' + paciente.nombre.toUpperCase() + '</td>' +
                                    '<td class=\"td_last_name\">' + paciente.apellido.toUpperCase() + '</td>'+
                                    '<td class=\"td_last_name\">' + paciente.email + '</td>'+
                                    '<td>' +deleteButton+ '</td>'
                         }
                })
           })
    })