window.addEventListener('load', function () {



    const input = document.querySelector("input");
    const button = document.querySelector("button");
    const registroContainer = document.querySelector(".registro-container");

    button.addEventListener("click", (e) =>{
    e.preventDefault();
    const botonMatricula = (input.value);
    console.log(botonMatricula)

            const url = '/odontologos';
            const settings = {
                method: 'GET',
                }
            fetch(url,settings)
            .then(response => response.json())
            .then(data => {
                const regOdontologo = data.filter(odontologo => odontologo.matricula === botonMatricula)
                console.log(regOdontologo)

                for(odontologo of regOdontologo){

                            var table = document.getElementById("odontologoTable");
                            var odontologoRow =table.insertRow();
                            let tr_id = 'tr_' + odontologo.id;
                            odontologoRow.id = tr_id;

                            let deleteButton = '<button' +
                                                      ' id=' + '\"' + 'btn_delete_' + odontologo.id + '\"' +
                                                      'type="button" onclick="deleteBy('+odontologo.id+')" class="btn btn-secondary btn_id" disabled/>'+
                                                      '&times'
                                                      '</button>';

                            let updateButton = '<button' +
                                                      ' id=' + '\"' + 'btn_id_' + odontologo.id + '\"' +
                                                      ' type="button" onclick="findBy('+odontologo.id+')" class="btn btn-secondary btn_id" disabled/>' +
                                                       odontologo.id+
                                                       '</button>';

                            odontologoRow.innerHTML = '<td>' +updateButton+ '</td>'+
                                    '<td class=\"td_first_name\">' + odontologo.apellido.toUpperCase() + '</td>' +
                                    '<td class=\"td_last_name\">' + odontologo.nombre.toUpperCase() + '</td>'+
                                    '<td class=\"td_last_name\">' + odontologo.matricula.toUpperCase() + '</td>'+
                                    '<td>' +deleteButton+ '</td>'
                         }
            })
    })
})









