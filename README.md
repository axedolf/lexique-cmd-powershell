# Lexique CMD / PowerShell

Application web statique pour comparer rapidement les commandes CMD Windows et leurs equivalents PowerShell.

## Fonctionnalites

- Recherche instantanee avec tolerance aux fautes, alias et mots lies.
- Autocompletion sous la barre de recherche.
- Filtres par categorie, niveau, usage et favoris.
- Cartes CMD / PowerShell avec coloration syntaxique.
- Copie rapide localisee sur chaque commande.
- Generateurs de commandes pour les syntaxes avec parametres.
- QR code par commande pour partager une fiche rapidement.
- Assistant local base sur les donnees du lexique, sans appel externe.
- Mode sombre / clair avec detection de la preference systeme.
- Raccourcis clavier : `/`, `Ctrl+K`, `Esc`, fleches de navigation, `Ctrl+D`.
- Export JSON des commandes filtrees.
- Stockage local des favoris via `localStorage`.

## Structure

```text
.
├── index.html
├── commandes.json
├── README.md
└── CONTRIBUTING.md
```

`index.html` contient l'interface, le CSS Tailwind CDN et le JavaScript.

`commandes.json` contient toutes les commandes. C'est le fichier principal a modifier pour enrichir le lexique.

## Lancer en local

Depuis le dossier du projet :

```powershell
python -m http.server 8000
```

Puis ouvrir :

```text
http://localhost:8000
```

L'ouverture directe en `file://` peut bloquer le chargement de `commandes.json` dans certains navigateurs. Un petit serveur local est recommande.

## Deploiement GitHub Pages

1. Copier `index.html`, `commandes.json`, `README.md` et `CONTRIBUTING.md` a la racine du depot GitHub.
2. Dans GitHub, ouvrir `Settings`.
3. Aller dans `Pages`.
4. Choisir la branche `main` et le dossier `/root`.
5. Sauvegarder.

L'application sera disponible a l'adresse GitHub Pages du depot, par exemple :

```text
https://axedolf.github.io/lexique-cmd-powershell/
```

## Ajouter une commande

Ajouter un objet dans `commandes.json` avec cette structure :

```json
{
  "id": "get-aduser",
  "category": "Active Directory",
  "level": "Intermediaire",
  "usage": "Utilisateurs",
  "equivalence": "Partielle",
  "action": "Rechercher un utilisateur Active Directory",
  "cmd": "net user <username> /domain",
  "powershell": "Get-ADUser -Identity <username> -Properties *",
  "alias": [],
  "tags": ["AD", "utilisateur", "recherche"],
  "related": ["ad-new-user", "ad-set-user"],
  "description": "Affiche les informations d'un utilisateur du domaine.",
  "note": "PowerShell retourne un objet ADUser manipulable avec Select-Object, Where-Object et Export-Csv.",
  "example": "Get-ADUser -Identity hugo -Properties EmailAddress,Title",
  "fields": [
    {
      "name": "username",
      "label": "Utilisateur",
      "placeholder": "hugo",
      "default": "hugo"
    }
  ],
  "cmdTemplate": "net user {{username}} /domain",
  "psTemplate": "Get-ADUser -Identity {{username}} -Properties *"
}
```

## Notes techniques

- Le projet est 100% statique et compatible GitHub Pages.
- Les icones viennent de Lucide via CDN.
- Tailwind CSS est charge via CDN.
- Les QR codes sont generes dans le navigateur via `qrcodejs`.
- Aucune donnee n'est envoyee a un serveur par l'assistant local.
