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
