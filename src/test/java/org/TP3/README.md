# Tests d’intégration – partie 1

---
## Exercice 1 – Interaction Simple entre Modules
### 1. Objectif
Implémenter un service (UserService) qui récupère les données d'un utilisateur via un repository (UserRepository) en utilisant des tests d'intégration avec des mocks (Mockito).

### 2. Structure du projet
- User.java : Modèle représentant un utilisateur (id, nom).
- UserRepository.java : Interface d'accès aux données utilisateur.
- UserService.java : Service métier qui utilise UserRepository.
- UserServiceTest.java : Test unitaire utilisant un mock de UserRepository.

### 3. Résumé
- Utilisation de Mockito pour simuler UserRepository.
- Vérification que UserService appelle correctement UserRepository.
- Contrôle du comportement en fonction des données mockées.
---
