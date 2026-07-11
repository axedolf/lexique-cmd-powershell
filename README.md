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
- Scenarios terrain avec playbooks d'intervention.
- Scripts PowerShell complets copiables en un clic.
- Assistant local base sur les donnees du lexique, sans appel externe.
- Mode sombre / clair avec detection de la preference systeme.
- Raccourcis clavier : `/`, `Ctrl+K`, `Esc`, fleches de navigation, `Ctrl+D`.
- Export JSON des commandes filtrees.
- Stockage local des favoris via `localStorage`.
- PWA installable sur ordinateur et telephone.
- Projet Android WebView fourni dans `android/`.

## Structure

```text
.
├── index.html
├── commandes.json
├── manifest.webmanifest
├── service-worker.js
├── icon.svg
├── android/
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

## Installer sur telephone

### Option 1 : PWA

Depuis Chrome ou Edge mobile :

1. Ouvrir `https://axedolf.github.io/lexique-cmd-powershell/`.
2. Ouvrir le menu du navigateur.
3. Choisir `Ajouter a l'ecran d'accueil` ou `Installer l'application`.

### Option 2 : Application Android native

Le dossier `android/` contient une application WebView prete a ouvrir dans Android Studio.

Voir :

```text
android/README_ANDROID.md
```

APK debug directement installable :

```text
lexique-cmd-powershell.apk
```

Sur Android, il faudra peut-etre autoriser l'installation depuis le navigateur ou le gestionnaire de fichiers. Cet APK est signe avec la cle debug Android, donc il est fait pour ton usage personnel et les tests, pas pour une publication Play Store.

## Deploiement GitHub Pages

1. Copier `index.html`, `commandes.json`, `manifest.webmanifest`, `service-worker.js`, `icon.svg`, `README.md`, `CONTRIBUTING.md` et `android/` a la racine du depot GitHub.
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
- Le service worker met en cache les fichiers principaux apres le premier chargement.
- L'application Android charge la version GitHub Pages officielle.
