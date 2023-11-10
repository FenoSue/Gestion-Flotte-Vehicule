function getKilometrage(idVehicule) {
    const url = "http://localhost:8080/ShowKilometrageVehicule?idVehicule="+idVehicule;
    const data = { key: "value" };

    fetch(url, {
        method: "GET",
        headers: {
            "Content-Type": "application/json"
        }
    })
    .then(response => {
        if (!response.ok) {
            throw new Error(`Erreur HTTP! Statut: ${response.status}`);
        }
        return response.json();
    })
    .then(data => {
        const div = document.createElement("div");
        div.className = "panel panel-default row";
        const detail = document.createElement("div");
        detail.className = "col-md-6"
        const kilometre = document.createElement("div");
        kilometre.className = "col-md-6"

        const jsonObject = JSON.parse(JSON.stringify(data));
        const datas = jsonObject.data;

        datas.forEach(kilometrage => {
            alert (
                "IdVehicule : "+kilometrage.idVehicule+
                "Date kilometrage : "+kilometrage.dateKilometrage+
                "Debut kilometrage : "+kilometrage.debutKilometrage+
                "Fin kilometrage : "+kilometrage.finKilometrage
            )
            const paragraphe1 = document.createElement("p");
            paragraphe1.textContent = "IdVehicule : "+kilometrage.idVehicule;

            const paragraphe2 = document.createElement("p");
            paragraphe2.textContent = "Matricule : "+kilometrage.matricule;

            const paragraphe3 = document.createElement("p");
            paragraphe3.textContent = "Marque : "+kilometrage.marque;

            const paragraphe4= document.createElement("p");
            paragraphe4.textContent = "Modele : "+kilometrage.modele;

            detail.appendChild(paragraphe1);
            detail.appendChild(paragraphe2);
            detail.appendChild(paragraphe3);
            detail.appendChild(paragraphe4);

            const paragraphe5 = document.createElement("p");
            paragraphe5.textContent = "Date kilometrage : "+kilometrage.dateKilometrage;

            const paragraphe6 = document.createElement("p");
            paragraphe6.textContent = "Debut kilometrage : "+kilometrage.debut;

            const paragraphe7 = document.createElement("p");
            paragraphe7.textContent = "Fin kilometrage : "+kilometrage.fin;

            kilometre.appendChild(paragraphe5);
            kilometre.appendChild(paragraphe6);
            kilometre.appendChild(paragraphe7);

            console.log("Données du web service:", "Kilometrage "+kilometrage.id);
        });
        const hr = document.createElement("hr");
        const title = document.createElement("h4");
        title.textContent = "Détail du kilometrage du véhicule";
        title.className = "title";
        div.appendChild(detail);
        div.appendChild(kilometre);
        document.body.appendChild(hr);
        document.body.appendChild(title );
        document.body.appendChild(div);

        console.log("Données du web service:", jsonObject.data);
    })
    .catch(error => {
        console.error("Erreur lors de la récupération des données:", error);
    });
}

function listeVehicule() {
    let resultat = "Traitement effectué avec succès!";
    localStorage.setItem('resultat', resultat);
    window.location.reload();
}

document.addEventListener('DOMContentLoaded', function() {
    const resultatStocke = localStorage.getItem('resultat');

    if (resultatStocke) {
        const url = "http://localhost:8080/ShowVehicule";
        const data = { key: "value" };

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
            const ul = document.createElement("ul");
            ul.className = "list list-group";

            const jsonObject = JSON.parse(JSON.stringify(data));
            const datas = jsonObject.data;

            datas.forEach(vehicule => {
                const li = document.createElement("li");
                li.className = "list-group-item";
                const link = document.createElement("a");
                link.href = "#";
                link.textContent = "Vehicule "+vehicule.id;
                link.onclick = function() {
                    getKilometrage(vehicule.id);
                };  
                li.appendChild(link);
                ul.appendChild(li);
                console.log("Données du web service:", "Vehicule "+vehicule.id);
            });
            document.body.appendChild(ul);

            console.log("Données du web service:", jsonObject.data);
        })
        .catch(error => {
            console.error("Erreur lors de la récupération des données:", error);
        });

        localStorage.removeItem('resultat');
    }
});
