tableDiscipline();
function tableDiscipline(){
    const xhr = new XMLHttpRequest();
    let action = "list_disciplines";
    xhr.open("GET", "http://localhost:8080/Students_web_managment_war_exploded/servlet_main?action=" + action, true);
    xhr.onreadystatechange = () => {
        const table = document.querySelector("#table_discipline")
        const tbody = table.querySelector("tbody");

        tbody.innerHTML = "";

        if (xhr.readyState === 4 && xhr.status === 200) {
            if (xhr.responseText != null) {
                const events = JSON.parse(xhr.responseText);
                events.forEach(item => {
                    const newRow = tbody.insertRow();

                    const idDiscipline = newRow.insertCell();
                    idDiscipline.innerHTML = item.id;

                    const nameDiscipline = newRow.insertCell();
                    nameDiscipline.innerHTML = item.name;

                    const participantsDiscipline = newRow.insertCell();
                    participantsDiscipline.innerHTML = item.participants;
                })
            } else {
                alert("No hay nada de data")
            }
        }
    }
    xhr.send(null);
}
function validateId(id) {
    return id.trim() !== '' && !isNaN(id);
}

function validateName(name) {
    return name.trim() !== '' && /^[a-zA-Z\s]*$/.test(name);
}

document.querySelector("#btn-add-discipline").addEventListener("click", () =>{
    const id = document.querySelector("#inputIdDis").value
    const name = document.querySelector("#inputNameDis").value

    if (!validateId(id)){
        alert("Por favor, introduce un ID válido (número).");
        return;
    }

    if (!validateName(name)){
        alert("El nombre no puede contener números ni caracteres especiales.");
        return;
    }

    const xhr = new XMLHttpRequest();
    let action = "add_discipline";
    xhr.open("POST", "http://localhost:8080/Students_web_managment_war_exploded/servlet_main?action=" + action, true);

    let params = JSON.stringify({ "id": id, "name": name})
    xhr.onreadystatechange = () =>{
        if (xhr.readyState === 4 && xhr.status === 200){
            if(xhr.responseText !== ""){
                addToTable(xhr.responseText, "table_discipline");
            }else alert("Ya existe una disciplina con este id");
        }
    }
    xhr.send(params);
})

document.querySelector("#deleteDiscipline").addEventListener("click", () =>{
    const id = document.querySelector("#inputDisciplineDelete").value

    if(!validateId(id)){
        alert("Por favor, introduce un ID válido (número).");
        return;
    }

    const xhr = new XMLHttpRequest();
    let action = "delete_discipline";
    xhr.open("DELETE", `http://localhost:8080/Students_web_managment_war_exploded/servlet_main?action=${action}&id=${id}`, true);

    xhr.onreadystatechange = () =>{
        if(xhr.readyState === 4 && xhr.status === 200){
            if(xhr.responseText !== ""){
                alert(JSON.parse(xhr.responseText).name + " ha sido eliminado de la base de datos.")
                deleteFromTable("table_discipline", id);
            }else alert("El id no existe o ha ocurrido un problema")
        }
    }
    xhr.send(null);

})