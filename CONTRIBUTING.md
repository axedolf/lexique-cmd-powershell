# Contribuer au Lexique CMD / PowerShell

Merci de contribuer a ce lexique. L'objectif est de garder une reference fiable, lisible et utile pour les administrateurs Windows, techniciens helpdesk et developpeurs.

## Regles de contribution

- Ajouter les nouvelles commandes dans `commandes.json`.
- Garder un `id` unique, stable et en minuscules.
- Privilegier les cmdlets PowerShell officiels aux alias.
- Renseigner les alias uniquement dans le champ `alias`.
- Ajouter des `tags` utiles pour la recherche fuzzy.
- Ajouter `adminRequired: true` si la commande necessite une elevation.
- Eviter les exemples dangereux ou destructifs sans note d'avertissement.
- Utiliser des exemples realistes et reutilisables.

## Champs recommandes

```json
{
  "id": "categorie-action",
  "category": "Reseau",
  "level": "Basique",
  "usage": "Diagnostic",
  "equivalence": "Partielle",
  "action": "Action lisible",
  "cmd": "commande CMD",
  "powershell": "Cmdlet-PowerShell -Parametre valeur",
  "alias": ["alias1", "alias2"],
  "tags": ["mot-cle", "diagnostic"],
  "related": ["autre-id"],
  "description": "Description courte.",
  "note": "Difference importante, limite ou astuce.",
  "example": "Exemple concret",
  "fields": [],
  "cmdTemplate": "commande {{parametre}}",
  "psTemplate": "Cmdlet -Parametre {{parametre}}"
}
```

## Niveaux

- `Basique` : commande quotidienne, facile a utiliser.
- `Intermediaire` : commande utile en administration, avec parametres ou contexte.
- `Avance` : commande plus technique, script, audit, domaine, securite ou infrastructure.

## Qualite d'equivalence

- `Parfaite` : la commande CMD et le cmdlet PowerShell couvrent quasiment le meme besoin.
- `Partielle` : les deux commandes se ressemblent mais different sur les sorties, les objets, les parametres ou le comportement.

## Tester avant publication

Verifier le JSON :

```powershell
Get-Content .\commandes.json -Raw | ConvertFrom-Json | Out-Null
```

Lancer un serveur local :

```powershell
python -m http.server 8000
```

Puis tester :

```text
http://localhost:8000
```

## Checklist

- La recherche trouve la commande par CMD, PowerShell, alias, tag et description.
- Les boutons de copie copient la bonne syntaxe.
- Les parametres generent une commande prete a coller.
- La note signale les differences importantes.
- Le mode sombre et mobile restent lisibles.
- Le QR code s'affiche si la fiche est ouverte.
