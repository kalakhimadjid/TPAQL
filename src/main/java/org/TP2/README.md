# TP2 - Tests Unitaires avec Mockups

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

---
### 3. Erreur de la methode getstate

- **Parce que** dans ta classe Calculatrice, il n’existe pas de méthode getState().

- Or dans l’énoncé de l’Exercice 1, on te demande d'écrire un test qui utilise :
    **verify(objet).getState();**

- **Donc**  on l'ajouter toi-même une méthode getState() dans la classe Calculatrice:
    - additionne deux nombres,
    - garde le résultat en mémoire (result),
    - permet de récupérer result avec getState()