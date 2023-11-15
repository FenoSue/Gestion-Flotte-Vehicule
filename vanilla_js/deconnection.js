const body = document.getElementById("body");

function deconnection() {
    const token  = localStorage.getItem("token");
    console.log(token)

    const url = "http://localhost:8080/Deconnection?token="+token;
    fetch(url, {
        method: "GET",
        headers: {
            "Content-Type": "application/json"
        },
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
            localStorage.removeItem("token");
            console.log("Données du web service:", jsonObject.data[0]);
            window.location.href = "index.html";
        }
        else {;
            const p = document.createElement("p");
            p.textContent = jsonObject.data[0];
            body.appendChild(p);
        }
    })
    .catch(error => {
        console.error("Erreur:", error);
    });
}