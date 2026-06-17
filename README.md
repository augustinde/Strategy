# StrateGy — Jeu de stratégie en Java

Projet universitaire (L3) d’un jeu de stratégie en 2D réalisé en Java avec interface Swing.

## Présentation

Le jeu lance une fenêtre graphique (`1280x720`) qui affiche :

- une grille de jeu hexagonale ;
- une interface utilisateur ;
- des unités et des capitales (joueur et IA).

Le point d’entrée est `src/com/serkox/main/Main.java`.

## Prérequis

- Java JDK installé (compilation/exécution en ligne de commande avec `javac` et `java`).

## Compilation

Depuis la racine du dépôt :

```bash
javac -d /tmp/strategy-build $(find src -name '*.java')
```

## Exécution

Après compilation :

```bash
java -cp /tmp/strategy-build com.serkox.main.Main
```

## Structure du projet

- `src/com/serkox/main` : point d’entrée de l’application.
- `src/com/serkox/entity` : logique de jeu et rendu Swing.
- `src/com/serkox/state` : états/comportements de l’IA.
- `src/com/serkox/textures` : ressources graphiques.

## Notes

- La documentation de migration et des corrections est disponible dans `MIGRATION_INTELLIJ.md`.
