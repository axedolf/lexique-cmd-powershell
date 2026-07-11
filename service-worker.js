const CACHE_NAME = "lexique-cmd-powershell-v6-ai-assistant";
const CACHE_PREFIX = "lexique-cmd-powershell-";

const OFFLINE_ASSETS = [
  "./",
  "./index.html",
  "./commandes.json",
  "./manifest.webmanifest",
  "./icon.svg",
  "./README.md",
  "./CONTRIBUTING.md",
  "./lexique-cmd-powershell.apk"
];

self.addEventListener("install", (event) => {
  event.waitUntil(
    caches.open(CACHE_NAME)
      .then((cache) => cache.addAll(OFFLINE_ASSETS))
      .then(() => self.skipWaiting())
  );
});

self.addEventListener("activate", (event) => {
  event.waitUntil(
    caches.keys()
      .then((keys) => Promise.all(
        keys
          .filter((key) => key.startsWith(CACHE_PREFIX) && key !== CACHE_NAME)
          .map((key) => caches.delete(key))
      ))
      .then(() => self.clients.claim())
  );
});

async function networkFirst(request) {
  const cache = await caches.open(CACHE_NAME);
  try {
    const response = await fetch(request);
    if (response.ok) {
      cache.put(request, response.clone());
    }
    return response;
  } catch {
    const cached = await cache.match(request);
    if (cached) return cached;
    const url = new URL(request.url);
    if (url.pathname.endsWith("/commandes.json")) return cache.match("./commandes.json");
    return cache.match("./index.html");
  }
}

async function cacheFirst(request) {
  const cache = await caches.open(CACHE_NAME);
  const cached = await cache.match(request);
  if (cached) return cached;
  const response = await fetch(request);
  if (response.ok) cache.put(request, response.clone());
  return response;
}

self.addEventListener("fetch", (event) => {
  const request = event.request;
  if (request.method !== "GET") return;

  const accept = request.headers.get("accept") || "";
  const url = new URL(request.url);
  const isAppFile = url.origin === self.location.origin;
  const isNavigation = request.mode === "navigate" || accept.includes("text/html");
  const isData = isAppFile && url.pathname.endsWith("/commandes.json");

  if (isNavigation || isData) {
    event.respondWith(networkFirst(request));
    return;
  }

  if (isAppFile) {
    event.respondWith(cacheFirst(request));
  }
});
