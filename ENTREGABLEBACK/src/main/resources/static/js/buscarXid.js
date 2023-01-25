window.addEventListener('load', function () {

    const input = document.querySelector("input");
    const button = document.querySelector("button");
    const registroContainer = document.querySelector(".registro-container");

    button.addEventListener("click", (e) =>{
    e.preventDefault();
    const botonMatricula = (input.value);
    console.log(botonMatricula)

            const url = '/turnos';
            const settings = {
                method: 'GET',
                }
            fetch(url,settings)
            .then(response => response.json())
            .then(data => {
                const regTurno = data.filter(turno => turno.fecha === botonMatriculaFecha)
                console.log(regTurno)

                for(turno of regTurno){

                            var table = document.getElementById("turnoTable");
                            var turnoRow =table.insertRow();
                            let tr_id = 'tr_' + turno.id;
                            turnoRow.id = tr_id;

                            let deleteButton = '<button' +
                                                      ' id=' + '\"' + 'btn_delete_' + turno.id + '\"' +
                                                      'type="button" onclick="deleteBy('+turno.id+')" class="btn btn-secondary btn_id" disabled/>'+
                                                      '&times'
                                                      '</button>';

                            let updateButton = '<button' +
                                                      ' id=' + '\"' + 'btn_id_' + turno.id + '\"' +
                                                      ' type="button" onclick="findBy('+turno.id+')" class="btn btn-secondary btn_id" disabled/>' +
                                                       turno.id+
                                                       '</button>';

                            turnoRow.innerHTML = '<td>' +updateButton+ '</td>'+
                                    '<td class=\"td_first_name\">' + turno.paciente.toUpperCase() + '</td>' +
                                    '<td class=\"td_last_name\">' + turno.odontologo.toUpperCase() + '</td>'+
                                    '<td class=\"td_last_name\">' + turno.fecha + '</td>'+
                                    '<td>' +deleteButton+ '</td>'
                         }
            })
    })
})









