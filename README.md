# Le projet
* **Nom :** RentManager
* **Auteur :** Pauline Maître
* **Date :** 03/04/220

## 		Description du projet
L'objectif de ce projet est de construire un outil web de locations de véhicules. Il faut donc pouvoir ajouter, modifier et supprimer des clients, des véhicules et des réservations. L'utilisateur peut consulter les fiches détaillées des clients et des véhicules. Il peut aussi depuis la fiche d'un véhicule ajouter une réservation pour ce dernier.

## 		Choix de conception
* Création d'une **Javadoc**, acccesible dans RentManager/doc/index.html
* Optimisation de l'**UX** : utilisation de classes Bootstrap, responsivité du site, affichage adapatif
* Pour faciliter la navigation entre les clients et les véhicules, des liens envoient sur les pages de détail des clients et véhicules depuis différentes pages
* Pas de page de détails des Reservation car tous les détails sont déjà dans la page de liste des réservations
* Création de **liens** depuis la page d'accueil vers les catégories utilisaeurs, véhicules et réservations

## 		Améliorations possibles
* Création de comptes pour utiliser l'outil avec différents niveaux de droits d'ajout, modification et suppresion
* Ajout de caractéristiques sur les voitures (photo, année de sortie, usure, caractéristiques techniques, ...)
* Envoi de la réservation par email au client

# Programmes nécessaires
* Eclipse IDE for Java EE Developers [téléchargeable ici](https://www.eclipse.org/downloads/packages/release/helios/sr1/eclipse-ide-java-ee-developers)
* Tomcat 9.0 Core version [téléchargeable ici](https://tomcat.apache.org/download-90.cgi)

# Configuration & Settings
* **Importer le projet :** Eclipse > File > Import > Selectionner le dossier à importer et vérifier que le Workspace pointe dessus
* Eclipse > clic droit à la racine du projet > Properties > Project Facets > Vérifier que Java est sur la version 1.8 > Apply and close
* Eclipse > Window > Preference > Server > Runtime Environments > Add > sélectionner Apache tomcat v9.0, cocher Create a local server > Next > Finish
* **Ajouter le projet aux ressources configurées sur le serveur Tomcat :** Eclipse > Onglet Servers > Clic droit sur Tomcat v9.0 Server at localhost > Add and Remove > Sélectionner le projet puis cliquer sur Add > Finish
* **Lancer le projet :** Eclipse > Clic droit à la racine du projet > Run As > 1 Run on Server

# Technologies utilisées
* Langages Java (JEE), HTML, CSS et SQL
* Framework Bootstrap
