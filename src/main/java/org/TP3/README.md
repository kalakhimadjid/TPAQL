# Tests d’intégration – partie 1

---
## Exercice 1 – Interaction Simple entre Modules
### 1. Objectif
Implémenter un service (UserService) qui récupère les données d'un utilisateur via un repository (UserRepository) en utilisant des tests d'intégration avec des mocks (Mockito).

### 2. Classe Utilisées
- `User.java` : Modèle représentant un utilisateur (id, nom).
- `UserRepository.java` : Interface d'accès aux données utilisateur.
- `UserService.java` : Service métier qui utilise UserRepository.
- `UserServiceTest.java` : Test unitaire utilisant un mock de UserRepository.

### 3. Résumé
- Utilisation de Mockito pour simuler UserRepository.
- Vérification que UserService appelle correctement UserRepository.
- Contrôle du comportement en fonction des données mockées.
---
## 📄 Exercice 2 – Interaction avec une Base de Données avec des Mocks

### 1. Objectif
Implémenter les classes `OrderController`, `OrderService`, et `OrderDao`, puis écrire un test d'intégration utilisant des mocks pour vérifier l'interaction entre ces composants.

---

### 2. Classe Utilisées

- `Order.java` : Représente une commande simple avec un id et un nom de produit.
- `OrderDao.java` : Interface d'accès aux données.
- `OrderService.java` : Service qui gère la logique métier pour créer une commande.
- `OrderController.java` : Contrôleur qui reçoit la commande et délègue au service.
- `OrderControllerTest.java` : Classe de test JUnit avec mocks.

## 3.Composants testés :

- OrderController : reçoit une commande et appelle OrderService.
- OrderService : reçoit la commande et appelle OrderDao pour la sauvegarder.
- OrderDao : interface simulée (mockée).

## 4.Vérifications effectuées dans le test :
- OrderController.createOrder(order) appelle bien : → OrderService.createOrder(order)
- OrderService.createOrder(order) appelle bien : → OrderDao.saveOrder(order)
- Les deux appels sont faits avec le bon objet Order (vérifié avec verify(mock, times(1))).

---
##  Exercice 3 – Intégration d'une API avec Mocking

### 1. Objectif
Mettre en place une classe `ProductService` qui appelle une API externe via une interface `ProductApiClient`, puis tester les interactions avec des objets mock.

### 2. Structure du projet

- `Product.java` : Classe modèle représentant un produit.
- `ProductApiClient.java` : Interface simulant une API externe.
- `ProductService.java` : Service qui utilise l'API client.
- `ProductServiceTest.java` : Test JUnit avec mocking de l'appel API.

### 3. Ce que le test vérifie

- Que `ProductApiClient.getProduct` est appelé avec le bon identifiant.
- Succès : le produit est bien retourné.
- Erreur : simulation d'une exception levée par l'API.
- Données absentes : retour null simulé.