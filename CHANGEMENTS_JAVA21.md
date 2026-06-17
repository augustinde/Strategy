# Migration Java 21 et corrections de bugs

## 1) Migration vers Java 21

Le projet Eclipse a été migré de Java 11 vers Java 21 :

- `/.classpath`
  - Passage du conteneur JRE de `JavaSE-11` à `JavaSE-21`.
- `/.settings/org.eclipse.jdt.core.prefs`
  - `org.eclipse.jdt.core.compiler.codegen.targetPlatform=21`
  - `org.eclipse.jdt.core.compiler.compliance=21`
  - `org.eclipse.jdt.core.compiler.source=21`

Cette mise à jour aligne la configuration du projet avec Java 21 pour la compilation et l’exécution dans Eclipse.

## 2) Bugs corrigés

### 2.1 Initialisation incorrecte des identifiants de capitales

- `src/com/serkox/entity/Player.java`
- `src/com/serkox/entity/PNJ.java`

Les constructeurs faisaient `id = id`, ce qui n’affectait jamais l’identifiant réel.
Correction appliquée :
- `Player` : `this.id = pId`
- `PNJ` : `this.id = id`

Impact : les identifiants des capitales sont maintenant correctement initialisés.

### 2.2 Setter de grille inopérant

- `src/com/serkox/entity/Grid.java`

`setHexagons(List<Hexagon> hexagons)` faisait une auto-affectation locale (`hexagons = hexagons`).
Correction : affectation sur le champ statique `Grid.hexagons`.

Impact : le setter met désormais correctement à jour la collection de la grille.

### 2.3 Risque de NullPointerException lors des attaques

- `src/com/serkox/entity/Hexagon.java`

Un clic sur une unité adverse ou la capitale adverse pouvait tenter une attaque sans unité sélectionnée côté joueur.
Des vérifications `Grid.getCapitalJoueur().getUnitToDeplace() != null` ont été ajoutées avant les appels à `attack(...)`.

Impact : suppression des plantages par `NullPointerException` dans ces scénarios.

### 2.4 Calcul du radius de capitale instable

- `src/com/serkox/entity/Capital.java`

Corrections apportées :
- Nettoyage de `hexagonsInRadius` avant recalcul pour éviter l’accumulation de doublons.
- Utilisation de `Grid.resetViewRadiusHexagons()` au lieu du reset de vue prioritaire.
- Ajout d’un garde-fou si la liste est vide avant `get(0)`.

Impact : calcul de priorité plus fiable et suppression d’un risque de `IndexOutOfBoundsException`.

### 2.5 Setter de coût d’unité inopérant

- `src/com/serkox/entity/Unit.java`

`setGoldCost(int goldCost)` faisait `goldCost = goldCost` (aucun effet).
Correction : affectation à `Unit.goldCost`.

Impact : le coût unitaire peut désormais être modifié correctement.

### 2.6 Déplacements exécutés en double

- `src/com/serkox/entity/Unit.java`

Dans les méthodes de déplacement asynchrones, chaque étape faisait deux appels de mouvement sur le même élément (un `get(i)` puis un `remove(i)`).
Correction : un seul appel avec l’élément retiré de la liste (`remove(i)`).

Impact : suppression de comportements de déplacement incohérents et duplication d’actions.

### 2.7 Scope IA non réinitialisé

- `src/com/serkox/entity/Unit.java`

`calculDistanceUnitIa()` ajoutait des cases au scope sans le vider avant recalcul.
Correction : `this.scope.clear()` en début de calcul.

Impact : évite l’accumulation d’anciens résultats et améliore la cohérence des décisions IA.

## 3) Vérification

Le projet compile après modifications avec `javac` sur l’ensemble des sources (`src/**/*.java`) dans l’environnement fourni.
