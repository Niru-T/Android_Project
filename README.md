# Projet android

## Présentation du projet

Mon application affiche une liste de tous les pays du monde, que je recupère grace à l'API(rest) "https://restcountries.eu/".
En cliquant sur  le nom d'un pays, on est redirigés vers une nouvelle fenêtre affichant la description de ce pays.

### Prérequis

* Android studio
* API Rest : https://restcountries.eu/

### Consignes respectées

* Ecran avec une liste d'éléments
* Ecran avec le détail d'un élément
* Appel Webservice à une API Rest
* Stockage de données en cache
* Gitflow

## Fonctionnalités

### Premier écran

Affichage de la liste des pays précédés par le drapeau du pays:


![Capture1](https://user-images.githubusercontent.com/62753141/83981275-b63f4680-a91c-11ea-8f20-a7fc3ae0d0a2.PNG)



### Deuxième écran 

Description du pays sur lequel on a cliqué (ici l'Arménie):


![Capture2](https://user-images.githubusercontent.com/62753141/83981281-c0f9db80-a91c-11ea-9859-816267054ee4.PNG)



### Informations complémentaires
Pour récupérer les drapeaux des pays, j'ai suivi le tuto suivant: https://muetsch.io/how-to-load-svg-into-imageview-by-url-in-android.html car la récupération des images web au format svg est un peu spéciale.
Il y a un petit bug qui reste à corriger, l'application plante lorsqu'on descend plus bas que la Biélorussie dans la liste.


