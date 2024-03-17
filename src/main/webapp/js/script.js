function openTab(tabName) {
    const tabs = document.querySelectorAll('.tabcontent');
    tabs.forEach(tab => {
        tab.style.display = 'none';
    });
    const activeTab = document.getElementById(tabName + '-content');
    activeTab.style.display = 'block';
}

document.addEventListener('DOMContentLoaded', function() {
    const eventFilter = document.getElementById('event-filter');
    const disciplineFilter = document.getElementById('discipline-filter');
    const participantList = document.getElementById('participant-list');
    const addParticipantBtn = document.getElementById('add-participant-btn');

    // Dummy data for demonstration
    const participants = [
        { name: 'Juan', event: 'Fútbol', discipline: 'Fútbol 11' },
        { name: 'María', event: 'Baloncesto', discipline: 'Baloncesto' },
        { name: 'Pedro', event: 'Natación', discipline: 'Estilo Libre' }
    ];

    // Function to render participants
    function renderParticipants(participants) {
        participantList.innerHTML = '';
        participants.forEach(participant => {
            const li = document.createElement('li');
            li.textContent = `${participant.name} - ${participant.event} (${participant.discipline})`;
            participantList.appendChild(li);
        });
    }

    // Populate event and discipline filters (dummy data)
    const events = ['Fútbol', 'Baloncesto', 'Natación'];
    events.forEach(event => {
        const option = document.createElement('option');
        option.value = event;
        option.textContent = event;
        eventFilter.appendChild(option);
    });

    const disciplines = ['Fútbol 11', 'Baloncesto', 'Estilo Libre'];
    disciplines.forEach(discipline => {
        const option = document.createElement('option');
        option.value = discipline;
        option.textContent = discipline;
        disciplineFilter.appendChild(option);
    });

    // Initial render
    renderParticipants(participants);

    // Event listener for filters
    eventFilter.addEventListener('change', filterParticipants);
    disciplineFilter.addEventListener('change', filterParticipants);

    // Function to filter participants based on selected filters
    function filterParticipants() {
        const selectedEvent = eventFilter.value;
        const selectedDiscipline = disciplineFilter.value;
        const filteredParticipants = participants.filter(participant => {
            return (selectedEvent === 'todos' || participant.event === selectedEvent) &&
                (selectedDiscipline === 'todos' || participant.discipline === selectedDiscipline);
        });
        renderParticipants(filteredParticipants);
    }

    // Dummy function to handle adding participants
    addParticipantBtn.addEventListener('click', function() {
        // Implement logic to add participant
        alert('Funcionalidad de añadir participante aún no implementada.');
    });
});


