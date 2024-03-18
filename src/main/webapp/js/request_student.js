tableStudent();

function tableStudent(){
    const xhr = new XMLHttpRequest();
    let action = "list_student";
    xhr.open("GET", "http://localhost:8080/Students_web_managment_war_exploded/servlet_main?action=" + action, true);
    xhr.onreadystatechange = () => {
        const table = document.querySelector("#table_students")
        const tbody = table.querySelector("tbody");

        tbody.innerHTML = "";

        if (xhr.readyState === 4 && xhr.status === 200) {
            if (xhr.responseText != null) {
                const students = JSON.parse(xhr.responseText);
                students.forEach(item => {
                    const newRow = tbody.insertRow();

                    const idStudent = newRow.insertCell();
                    idStudent.innerHTML = item.id;

                    const nameStudent = newRow.insertCell();
                    nameStudent.innerHTML = item.name;

                    const ageStudent = newRow.insertCell();
                    ageStudent.innerHTML = item.age;

                    const disciplineStudent = newRow.insertCell();
                    disciplineStudent.innerHTML = item.discipline;

                    const eventsStudent = newRow.insertCell();
                    eventsStudent.innerHTML = item.events;



                })
                fill_select("discipline-filter", "list_disciplines");
                fill_select("regi_discipline", "list_disciplines");
                fill_select("select-discipline", "list_disciplines");

            } else {
                alert("No hay nada de data")
            }
        }
    }
    xhr.send(null);
}
function fill_select(selectId, action) {
    const xhr = new XMLHttpRequest();
    xhr.open("GET", "http://localhost:8080/Students_web_managment_war_exploded/servlet_main?action=" + action, true);
    const select = document.querySelector("#" + selectId);
    xhr.onreadystatechange = () => {
        if (xhr.readyState === 4 && xhr.status === 200) {
            select.innerHTML = "";
            const data = JSON.parse(xhr.responseText);
            data.forEach(item => {
                const option = document.createElement("option");
                option.value = item.id;
                option.textContent = item.name;
                select.appendChild(option);
            });
        }
    };
    xhr.send(null);
}

function isNumber(string) {
    return !isNaN(string);
}
function validateInput(input) {
    return input && !isNaN(input);
}
function stringVerify(string) {
    return string.trim() !== '' && /^[a-zA-Z\s]+$/.test(string);
}
function stringNotNull(cadena) {
    return cadena.trim() !== '';
}


function addToTable(object, tableId){
    const table = document.querySelector("#"+tableId)
    const item = JSON.parse(object)
    const newRow = table.insertRow()

    const idCell = newRow.insertCell();
    idCell.innerHTML = item.id;

    const nameCell = newRow.insertCell();
    nameCell.innerHTML = item.name;

    const priceCell = newRow.insertCell();
    priceCell.innerHTML = item.price;
}

function deleteFromTable(tableId, itemId) {
    const table = document.querySelector("#" + tableId);
    const rows = table.querySelectorAll("tbody tr");

    for (const row of rows) {
        const idCell = row.querySelector("td:first-child");
        if (idCell && idCell.textContent.trim() === itemId.toString()) {
            row.remove();
            return true;
        }
    }
    return false;
}


document.querySelector("#add-participant-btn").addEventListener("click", ()=>{
    const name = document.querySelector("#input-name").value
    const age = document.querySelector("#input-age").value
    const id = document.querySelector("#input-id").value
    const discipline = document.querySelector("#regi_discipline").value

    if (!name) {
        alert("Por favor, introduce un nombre válido.");
        return;
    }

    if (id && !isNumber(id)) {
        alert("Por favor, introduce un ID válido (número).");
        return;
    }

    if (age && !isNumber(age)) {
        alert("Por favor, introduce una edad válida (número).");
        return;
    }

    const xhr = new XMLHttpRequest();
    let action = "add_student";
    xhr.open("POST", "http://localhost:8080/Students_web_managment_war_exploded/servlet_main?action=" + action, true);

    let params = JSON.stringify({ "id": id, "name": name, "age": age, "discipline": discipline})
    xhr.onreadystatechange = () =>{
        if (xhr.readyState === 4 && xhr.status === 200){
            if(xhr.responseText !== ""){
                addToTable(xhr.responseText, "table_students");
            }else alert("Ya existe un estudiante con este id");
        }
    }
    xhr.send(params);

})
document.querySelector("#delete-participant-btn").addEventListener("click", ()=>{
    const id = document.querySelector("#input-idDelete").value

    if (id && !isNumber(id)) {
        alert("Por favor, introduce un ID válido (número).");
        return;
    }

    if (!confirm("¿Estás seguro de que deseas eliminar este estudiante?")) {
        return;
    }

    const xhr = new XMLHttpRequest();
    let action = "delete_student";
    xhr.open("DELETE", `http://localhost:8080/Students_web_managment_war_exploded/servlet_main?action=${action}&id=${id}`, true);

    xhr.onreadystatechange = () =>{
        if(xhr.readyState === 4 && xhr.status === 200){
            if(xhr.responseText !== ""){
                alert(JSON.parse(xhr.responseText).name + " ha sido eliminado de la base de datos.")
                deleteFromTable("table_students", id);
            }else alert("El id no existe o ha ocurrido un problema")
        }
    }
    xhr.send(null);

})

document.querySelector("#add-eventSt").addEventListener("click", () =>{
    const idStudent = document.querySelector("#input-idSt").value
    const idEvent = document.querySelector("#input-idEv").value

    if (!validateInput(idStudent) || !validateInput(idEvent)) {
        alert("Por favor, introduce un ID válido (número).");
        return;
    }

    const xhr = new XMLHttpRequest();
    let action = "event-to-student";
    xhr.open("GET", `http://localhost:8080/Students_web_managment_war_exploded/servlet_main?action=${action}&idStudent=${idStudent}&idEvent=${idEvent}`, true);

    xhr.onreadystatechange = () =>{
        if (xhr.readyState === 4 && xhr.status === 200){
            if(xhr.responseText != null){
                alert(xhr.responseText)
            }else alert("Asegurate de que los id digitados correspondan")
        }
    }

    xhr.send(null);
})

