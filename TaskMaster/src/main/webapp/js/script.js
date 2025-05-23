let tasks = [];

function addTask() {
    const taskInput = document.getElementById('taskInput');
    const taskText = taskInput.value.trim();
    
    if (taskText === "") {
        alert("Por favor, digite uma tarefa!");
        return;
    }
    
    tasks.push({
        id: Date.now(),
        text: taskText,
        completed: false
    });
    
    taskInput.value = "";
    renderTasks();
}

function renderTasks() {
    const taskList = document.getElementById('taskList');
    taskList.innerHTML = "";
    
    tasks.forEach(task => {
        const taskElement = document.createElement('div');
        taskElement.className = `task-item ${task.completed ? 'completed' : ''}`;
        
        taskElement.innerHTML = `
            <span>${task.text}</span>
            <div>
                <button onclick="toggleTask(${task.id})">✓</button>
                <button onclick="deleteTask(${task.id})">✕</button>
            </div>
        `;
        
        taskList.appendChild(taskElement);
    });
}

function toggleTask(id) {
    tasks = tasks.map(task => 
        task.id === id ? {...task, completed: !task.completed} : task
    );
    renderTasks();
}

function deleteTask(id) {
    tasks = tasks.filter(task => task.id !== id);
    renderTasks();
}

// Inicializa a lista
renderTasks();