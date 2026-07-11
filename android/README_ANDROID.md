# Application Android - Lexique CMD / PowerShell

Ce dossier contient une application Android native minimaliste qui affiche la version GitHub Pages du lexique dans une WebView.

## Ouvrir dans Android Studio

1. Ouvrir Android Studio.
2. Choisir `Open`.
3. Selectionner le dossier `android`.
4. Laisser Gradle synchroniser le projet.
5. Brancher un telephone Android ou lancer un emulateur.
6. Cliquer sur `Run`.

## Generer un APK

Dans Android Studio :

```text
Build > Build Bundle(s) / APK(s) > Build APK(s)
```

L'APK sera genere dans :

```text
android/app/build/outputs/apk/debug/
```

## Notes

- L'application charge `https://axedolf.github.io/lexique-cmd-powershell/`.
- JavaScript et `localStorage` sont actives pour conserver les favoris et le mode sombre.
- Un ecran hors connexion simple est affiche si le telephone n'a pas de reseau au lancement.
