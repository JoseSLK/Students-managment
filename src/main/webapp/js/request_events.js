tableEvents();
function tableEvents(){
    const xhr = new XMLHttpRequest();
    let action = "list_events";
    xhr.open("GET", "http://localhost:8080/Students_web_managment_war_exploded/servlet_main?action=" + action, true);
    xhr.onreadystatechange = () => {
        const table = document.querySelector("#table_events")
        const tbody = table.querySelector("tbody");

        tbody.innerHTML = "";

        if (xhr.readyState === 4 && xhr.status === 200) {
            if (xhr.responseText != null) {
                const events = JSON.parse(xhr.responseText);
                events.forEach(item => {
                    const newRow = tbody.insertRow();

                    const idEvent = newRow.insertCell();
                    idEvent.innerHTML = item.id;

                    const nameEvent = newRow.insertCell();
                    nameEvent.innerHTML = item.name;

                    const dateEvent = newRow.insertCell();
                    dateEvent.innerHTML = item.date;

                    const locationEvent = newRow.insertCell();
                    locationEvent.innerHTML = item.location;

                    const disciplineEvent = newRow.insertCell();
                    disciplineEvent.innerHTML = item.disciplineId;

                    const positionEvent = newRow.insertCell();
                    positionEvent.innerHTML = item.positions;
                })
                fill_select("event-filter", "list_events");
            } else {
                alert("No hay nada de data")
            }
        }
    }
    xhr.send(null);
}

function validateInput(input) {
    return input && !isNaN(input);
}
function verifyString(string) {
    if (!string || string.trim().length === 0) {
        return false;
    }
    return /^[a-zA-Z\s]*$/.test(string);
}
function stringNotNull(cadena) {
    return cadena.trim() !== '';
}
function isNumber(string) {
    return !isNaN(string);
}

document.querySelector("#add-event").addEventListener("click", () =>{
    const inputNEA = document.querySelector("#inputNEA").value
    const  inputIE = document.querySelector("#inputIE").value
    const inputUBE = document.querySelector("#inputUBE").value
    const selectDiscipline = document.querySelector("#select-discipline").value
    const date = document.querySelector("#date").value

    if (!validateInput(inputIE)) {
        alert("Por favor, introduce un ID válido (número).");
        return;
    }
    if (!verifyString(inputNEA)){
        alert("El nombre no puede contener numeros ni caracteres especiales")
        return;
    }
    if (!stringNotNull(inputUBE)){
        alert("La descripcion no puede estar vacia")
        return;
    }

    const xhr = new XMLHttpRequest();
    let action = "add_event";
    xhr.open("POST", "http://localhost:8080/Students_web_managment_war_exploded/servlet_main?action=" + action, true);

    let params = JSON.stringify({ "id": inputIE, "name": inputNEA, "disciplineId": selectDiscipline, "date":date, "location":inputUBE})

    xhr.onreadystatechange = () => {
        if (xhr.readyState === 4) {
            if (xhr.status === 200) {
                if (xhr.responseText !== "") {
                    alert("El evento se ha agregado correctamente.");
                } else {
                    alert("Ya existe un evento con este ID.");
                }
            } else if (xhr.status === 409) {
                alert("Ya existe un evento con este ID.");
            } else {
                alert("Ha ocurrido un error al agregar el evento.");
            }
        }
    }
    xhr.send(params)
})

document.querySelector("#add-position-to-event").addEventListener("click", () =>{
    const inputEvId = document.querySelector("#input-id-eventr").value
    const inputStId = document.querySelector("#input-id-student").value

    console.log(inputStId+ " id estudiante")
    console.log(inputEvId + " id evento")

    if (!validateInput(inputEvId)) {
        alert("Por favor, introduce un ID de evento válido (número).");
        return;
    }
    if (!validateInput(inputStId)) {
        alert("Por favor, introduce un ID de estudiante válido (número).");
        return;
    }

    const requestBody = JSON.stringify({ "eventId": inputEvId, "studentId": inputStId });
    const xhr = new XMLHttpRequest();
    let action = "add_position_student";
    xhr.open("POST", "http://localhost:8080/Students_web_managment_war_exploded/servlet_main?action=" + action, true);
    xhr.setRequestHeader("Content-Type", "application/json");

    xhr.onreadystatechange = () => {
        if (xhr.readyState === 4) {
            if (xhr.status === 200) {
                if (xhr.responseText !== "") {
                    alert(xhr.responseText);
                } else {
                    alert("No existe un estudiante o evento con este ID(Valido).");
                }
            } else if (xhr.status === 409) {
                alert("Error pedriloso.");
            } else {
                alert("Ha ocurrido un error al agregar el evento.");
            }
        }
    }
    xhr.send(requestBody)
})
document.querySelector("#deleteEventp").addEventListener("click", () =>{
    const id = document.querySelector("#inputEventDelete").value

    if (!stringNotNull(id)){
        alert("No dejes casillas vacias")
        return;
    }

    if (id && !isNumber(id)) {
        alert("Por favor, introduce un ID válido (número).");
        return;
    }

    if (!confirm("¿Estás seguro de que deseas eliminar este evento?")) {
        return;
    }

    const xhr = new XMLHttpRequest();
    let action = "delete_event";
    xhr.open("DELETE", `http://localhost:8080/Students_web_managment_war_exploded/servlet_main?action=${action}&id=${id}`, true);

    xhr.onreadystatechange = () =>{
        if(xhr.readyState === 4 && xhr.status === 200){
            if(xhr.responseText !== ""){
                alert(JSON.parse(xhr.responseText).name + " ha sido eliminado de la base de datos.")
                deleteFromTable("table_events", id);
            }else alert("El id no existe o ha ocurrido un problema")
        }
    }
    xhr.send(null);
})