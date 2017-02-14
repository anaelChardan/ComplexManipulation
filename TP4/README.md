# TP4 : Tweeter n'est pas Twitter avec les acteurs

## Rendus

J'ai utilisé SBT pour builder le projet, normalement tout est donc inclus dedans, il vous suffira de faire un "sbt compile" pour compiler.

## Fonctionnalités attendues et réalisées:
- Afficher des tweets dans une vue et avoir la possibilité de retweeter

## Fonctionnalités non attendues et réalisées:
- Réaliser des Tweets depuis l'interface graphique à ses followers

## Réponses aux questions

-  Que se passe t'il si un Tweeter reçoit la demande de suivre une personne qui n'est pas enregistrée ?

Il ne se passe rien, comme c'est un optionnel que reçoit "LookupAnswer", si l'option contient quelque chose on ajoute le follower
Sinon, on affiche une erreur dans la console.


-  Et que se passe-t-il si Bob indique par erreur deux fois qu’il suit les envois d’Alice ?

Il recevra deux fois les envois d'Alice ce qui n'est pas ce que nous souhaitons, j'ai donc remplacé la liste de followers par
un Set de follower afin de ne pouvoir suivre qu'une seule fois un tweeter.
