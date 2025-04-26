# TP2 - Tests Unitaires avec Mockups

---
## Exercice 1 - Calculatrice

### 1. Test effectué

Nous avons mocké la classe `Calculatrice`, et défini le comportement de la méthode `additionner(2,3)` pour retourner `5`.

Nous avons :
- Vérifié que le résultat est bien `5`.
- Vérifié que `additionner(2,3)` a été appelée.
- Vérifié qu'aucune autre méthode n'a été appelée.
- Vérifié l'appel à la méthode `getState()` (supposée exister).

### 2. Type de test

C'est à la fois :
- **Test d'interaction** ➔ on vérifie que certaines méthodes sont appelées.
- **Test d'état** ➔ on vérifie que le résultat est correct (`5`) et que l'état de l'objet est interrogé (`getState()`).

### 3. Erreur de la methode getstate

- **Parce que** dans ta classe Calculatrice, il n’existe pas de méthode getState().

- Or dans l’énoncé de l’Exercice 1, on te demande d'écrire un test qui utilise :
    **verify(objet).getState();**
- **Donc**  on l'ajouter toi-même une méthode getState() dans la classe Calculatrice:
    - additionne deux nombres,
    - garde le résultat en mémoire (result),
    - permet de récupérer result avec getState()
### Erreur de dependences
- JUnit 5 n'utilise plus @RunWith, il utilise @ExtendWith.
- Donc tu remplace :
  - import org.junit.runner.RunWith;
  - import org.mockito.junit.MockitoJUnitRunner;
  - @RunWith(MockitoJUnitRunner.class)
- par : 
  - import org.junit.jupiter.api.extension.ExtendWith;
  - import org.mockito.junit.jupiter.MockitoExtension;
  - @ExtendWith(MockitoExtension.class)
 ---
## Exercice 2 - UserService

### 1. Objectif

Tester que la méthode `creerUtilisateur()` de la classe `UserService` appelle bien la méthode `creerUtilisateur()` du `UtilisateurApi`.

### 2. Démarche

- Mock de `UtilisateurApi`.
- Configuration pour qu'aucune exception ne soit levée (`doNothing()`).
- Création d'un `UserService` avec le mock.
- Appel de la méthode `creerUtilisateur(utilisateur)`.
- Vérification que `creerUtilisateur(utilisateur)` a bien été appelée sur le mock.

### 3. Type de test

C'est un **test d'interaction** :
- On vérifie que `creerUtilisateur` est bien appelée.
- On ne teste pas le résultat de `creerUtilisateur`, seulement l'appel au service.

### 4. Classe Utilisateur

On doit cree la classe Utilisateur :
 - private String prenom;
 - private String nom;
 - private String email;
### Erreur de dependences (meme que exo 1)
- JUnit 5 n'utilise plus @RunWith, il utilise @ExtendWith.
- Donc tu remplace :
  - import org.junit.runner.RunWith;
  - import org.mockito.junit.MockitoJUnitRunner;
  - @RunWith(MockitoJUnitRunner.class)
- par :
  - import org.junit.jupiter.api.extension.ExtendWith;
  - import org.mockito.junit.jupiter.MockitoExtension;
  - @ExtendWith(MockitoExtension.class)
---
