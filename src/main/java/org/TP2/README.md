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
## Exercice 3 - UserService (Scénarios avancés)

### 1. Scénarios testés

- **Exception** : Simuler un échec de création d'utilisateur avec levée de `ServiceException`.
- **Erreur de validation** : Vérifier que l'API n'est jamais appelée si l'utilisateur est invalide (avec `never()`).
- **ID après création** : Vérifier que l'ID est bien attribué après création.
- **Capture d'arguments** : Utiliser `ArgumentCaptor` pour capturer et vérifier les données passées à l'API.

### 2. Type de test

Ce sont des **tests d'interactions** :
- On vérifie comment `UserService` interagit avec `UtilisateurApi`.
- On capture et vérifie les valeurs transmises.


### 3. Résumé Mockito utilisé

| Action | Mockito utilisé |
|:------:|:---------------:|
| Forcer exception | `doThrow().when(mock).method()` |
| Vérifier aucune interaction | `verify(mock, never())` |
| Capturer arguments | `ArgumentCaptor.forClass()` |
- when(T) in 'org.mockito.Mockito' cannot be applied to '(void)'
---
## Exercice 4 - Jeu du 7
### 1. Objets mockés
Les objets suivants sont forcément mockés :

-  De : pour contrôler les valeurs des lancers (éviter le hasard).
-  Joueur : pour contrôler les mises, simuler une insolvabilité, etc.
-  Banque : pour contrôler le solde, tester la solvabilité, etc.

 Ces objets sont mockés car ils représentent des dépendances externes dont on veut isoler les effets pour tester uniquement le comportement de la méthode jouer().

### 2. Tests écrits

1.  TestJeuFerme  
    - Le jeu est fermé, une exception est levée.  
    - Test d’état.
2. TestJoueurInsolvable  
   - Le joueur ne peut pas payer la mise (DebitImpossibleException).  
   - On vérifie que les dés ne sont pas lancés.  
   - Test d’interaction.

3.  TestPerte  
    - La somme des dés ≠ 7, le joueur perd sa mise.  
    - Test d’interaction (vérification de debit/credit).

4.  TestVictoire
    - La somme des dés = 7 → gain crédité au joueur.  
    - La banque débite et le joueur crédite.  
    - Test d’interaction.

5. TestBanqueInsolvable  
    - Le joueur gagne, mais la banque devient insolvable.  
    - Le jeu se ferme après ce tour.  
    - Test d’état.
###  Test avec vraie implémentation de Banque

Une classe BanqueImpl a été créée avec :

- solde
- fond minimum
- logique de solvabilité

Un test avec BanqueImpl (non mocké) a été ajouté.  
Cela permet de tester le comportement intégré, et non isolé.
### 3. Type de tests

- Tests d'état : vérification de l'état du jeu (ouvert/fermé).
- Tests d'interaction : vérifier les appels aux méthodes (`debiter`, `crediter`, `lancer`, etc.)


### 4. Remarque importante

En cas de gain, si la banque devient insolvable, le gain est donné, puis le jeu se ferme immédiatement après.
