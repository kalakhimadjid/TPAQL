# TP3 - Tests d'intégration avec Docker, Testcontainers et Spring Boot

## Description du projet

Ce projet est réalisé dans le cadre du **TP3 - Tests d'intégration (Partie 2)**, qui vise à maîtriser l'utilisation de **Docker**, **Testcontainers**, et **Spring Boot** pour effectuer des tests d'intégration robustes et fiables. L'application est une implémentation simple d'un système de gestion d'utilisateurs, utilisant une base de données MySQL. Les tests d'intégration ont été réécrits en utilisant la bibliothèque Testcontainers pour exécuter une base de données MySQL dans un conteneur Docker, remplaçant ainsi les approches précédentes (par exemple, mocks ou bases de données en mémoire comme H2).

Le projet inclut :
- Une entité `User` avec des attributs `id`, `name`, et `email`.
- Un service `UserService` pour les opérations CRUD (création, récupération, suppression d'utilisateurs).
- Un repository `UserRepository` basé sur Spring Data JPA.
- Des tests d'intégration dans `UserServiceTest.java` et un test de contexte dans `MyIntegrationTest.java`.

1.**Configurer Docker** :
- Assurez-vous que Docker est en cours d'exécution sur votre machine.
- Testcontainers téléchargera automatiquement l'image MySQL (`mysql:8.0.30`) lors de l'exécution des tests.

## Exécution des tests

2.**Exécuter les tests d'intégration** :
- Depuis la racine du projet, exécutez :
  ```bash
  ./mvnw test
  ```
- Les tests dans `MyIntegrationTest.java` et `UserServiceTest.java` démarreront automatiquement un conteneur MySQL via Testcontainers et exécuteront les scénarios de test.

2. **Tests implémentés** :
    - `MyIntegrationTest.java` : Vérifie que le contexte Spring Boot se charge correctement et que le conteneur MySQL est en cours d'exécution.
    - `UserServiceTest.java` :
        - `testCreateAndGetUser` : Crée un utilisateur et vérifie qu'il peut être récupéré par son ID.
        - `testGetAllUsers` : Crée plusieurs utilisateurs et vérifie que la liste complète est récupérée.
        - `testDeleteUser` : Crée un utilisateur, le supprime, et vérifie qu'il n'existe plus.

## Exercice 1 : Maîtriser Testcontainers

### Description
Pour l'exercice 1, les tests d'intégration ont été réécrits pour utiliser Testcontainers au lieu de mocks ou d'une base de données en mémoire (comme H2). Une image Docker pour l'application a été créée et publiée sur Docker Hub.

### Étapes réalisées
1. **Remplacement des mocks** : Les tests précédents (hypothétiques, basés sur une implémentation antérieure) utilisaient probablement des mocks ou une base de données en mémoire. Ces tests ont été remplacés par des tests utilisant un conteneur MySQL réel via Testcontainers.
2. **Création de l'image Docker** :
    - Un fichier `Dockerfile` a été créé pour construire l'application Spring Boot :
      ```dockerfile
      FROM openjdk:17-jdk-slim
      WORKDIR /app
      COPY target/tp3-integration-test-0.0.1-SNAPSHOT.jar app.jar
      ENTRYPOINT ["java", "-jar", "app.jar"]
      ```
    - L'image a été construite avec :
      ```bash
      docker build -t <votre-utilisateur>/tp3-integration-test .
      ```
    - L'image a été poussée sur Docker Hub :
      ```bash
      docker push <votre-utilisateur>/tp3-integration-test
      ```

## Exercice 2 : Tests d'intégration pour une application de gestion

### Description
L'exercice 2 consistait à cloner un projet de gestion (similaire à `rengreen/task-manager`) et à réécrire ses tests d'intégration avec Testcontainers. Dans ce projet, nous avons implémenté une application de gestion d'utilisateurs.

### Analyse des tests existants
- **Approche précédente** : Les tests originaux (hypothétiques, basés sur un projet comme `rengreen/task-manager`) utilisaient probablement une base de données en mémoire (H2) ou des mocks pour simuler les interactions avec la base de données.
- **Limitations** :
    - **Couverture** : Les tests en mémoire ne reflétaient pas fidèlement le comportement d'une base de données réelle (par exemple, différences dans la gestion des transactions ou des contraintes SQL).
    - **Fiabilité** : Les mocks pouvaient masquer des erreurs réelles liées à la configuration ou aux requêtes SQL.
    - **Maintenabilité** : Les mocks nécessitaient une maintenance supplémentaire pour simuler correctement les comportements complexes.

### Tests réécrits avec Testcontainers
- **Changements** :
    - Les tests utilisent désormais un conteneur MySQL (`mysql:8.0.30`) géré par Testcontainers.
    - Les tests sont exécutés dans un environnement isolé, garantissant une reproductibilité et une fidélité accrues.
    - Les scénarios de test couvrent les opérations CRUD de base :
        - Création d'un utilisateur et vérification de son enregistrement.
        - Récupération de tous les utilisateurs.
        - Suppression d'un utilisateur et vérification de sa disparition.
- **Nouveaux scénarios** :
    - Aucun scénario supplémentaire n'a été ajouté dans cette implémentation, mais les tests existants ont été optimisés pour utiliser AssertJ pour des assertions plus lisibles.
- **Avantages de Testcontainers** :
    - **Isolation** : Chaque test s'exécute dans un conteneur propre, évitant les interférences entre tests.
    - **Fiabilité** : Utilisation d'une base de données MySQL réelle, reflétant l'environnement de production.
    - **Lisibilité** : Les tests sont clairs et utilisent des annotations modernes (JUnit 5, Spring Boot, Testcontainers).
    - **Maintenabilité** : La configuration du conteneur est centralisée (bien que des améliorations soient possibles, voir ci-dessous).
- **Inconvénients** :
    - Les tests avec Testcontainers peuvent être plus lents en raison du démarrage des conteneurs.
    - Nécessite Docker sur la machine de développement, ce qui peut compliquer la configuration dans certains environnements.

### Comparaison des tests
| Critère            | Tests originaux (hypothétiques) | Tests avec Testcontainers |
|---------------------|----------------------------------|---------------------------|
| **Couverture**      | Limitée aux scénarios simulés par mocks ou H2 | Identique, mais plus fidèle grâce à MySQL réel |
| **Lisibilité**      | Moyenne (dépend des mocks)      | Élevée (AssertJ, annotations claires) |
| **Maintenabilité**  | Faible (mocks complexes à maintenir) | Élevée (configuration centralisée, moins de mocks) |
| **Fiabilité**       | Faible (comportement différent en production) | Élevée (environnement proche de la production) |
