tableStudent();
tableEvents();
fill_select("event-filter", "list_events");
fill_select("discipline-filter", "list_disciplines");

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

                })
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
                option.value = item.name;
                option.textContent = item.name;
                select.appendChild(option);
            });
        }
    };
    xhr.send(null);
}
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
                    disciplineEvent.innerHTML = item.discipline;
                })
            } else {
                alert("No hay nada de data")
            }
        }
    }
    xhr.send(null);
}


