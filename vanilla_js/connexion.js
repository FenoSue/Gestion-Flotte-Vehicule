const login = document.getElementById("nom");
const pwd = document.getElementById("mdp");

function connection() {
    const url = "http://localhost:8080/Connection";
    const data = { 
        "login": login.value,
        "pwd": pwd.value 
    };
    console.log("Données du web service:", data);
    fetch(url, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },  
        body: JSON.stringify(data)
    })
    .then(response => {
        if (!response.ok) {
            throw new Error(`Erreur HTTP! Statut: ${response.status}`);
        }
        return response.json();
    })
    .then(data => {
        const jsonObject = JSON.parse(JSON.stringify(data))
        console.log("Données du web service:", jsonObject.status);
        if(jsonObject.status==200) {
            localStorage.setItem('token', jsonObject.data[0]);
            console.log("Données du web service:", jsonObject.data[0]);
            window.location.href = "menu.html";
        }
        else if(jsonObject.status==400) {
            console.log("Données du web service:", jsonObject.data[0]);
            console.log("Données du web service:", jsonObject.data[1]);
            const erreurLogin = document.getElementById("errorLogin");
            const erreurPwd = document.getElementById("errorPwd");
            const log = document.createElement("p");
            log.textContent = jsonObject.data[0];
            log.className = "error";
            const p = document.createElement("p");
            p.textContent = jsonObject.data[1];
            p.className = "error";
            erreurLogin.appendChild(log);
            erreurPwd.appendChild(p);
        }
    })
    .catch(error => {
        console.error("Erreur lors de la récupération des données:", error);
    });
}