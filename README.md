# Klient do publicznego Web API Spotify
## Krótki opis działania aplikacji: 
Użytkownik podaje nazwę artysty. Wyświetlane jest podsumowanie dla artysty z informacjami opisanymi w podpunktach. Użytkownik ma potem możliwość wyświetlenia (w nowym okienku) listy rekomendowanych utworów dla słuchaczy danego artysty. Może wtedy też podać dodatkowych artystów – w takim wypadku wyświetlana jest lista wspólna dla wszystkich podanych artystów (w dokumentacji działanie tej funkcji opisane jest w sekcji Browse -> recommendations).
## Funkcjonalności:
1. Stworzenie GUI i wyświetlanie w nim otrzymywanych wyników
2. Stworzenie zapytania na podstawie danych od użytkownika – użytkownik podaje artystę.
3. Odczytanie i parsowanie odpowiedzi do utworzonych klas
4. Utworzenie listy top 10 najpopularniejszych albumów artysty
5. Utworzenie listy top 10 najpopularniejszych utworów artysty
6. Utworzenie listy gatunków artysty
7. Utworzenie zbiorczych statystyk o charakterystyce utworów artysty – wyświetlanie średnich wartości atrybutów danceability, energy, loudness, valence i tempo dla wszystkich utworów artysty.
8. Pobranie i wyświetlenie zdjęcia artysty
9. Utworzenie listy rekomendowanych utworów dla słuchaczy danego artysty bądź artystów.
10. Eksport wyników do pliku - zapisanie aktualnego widoku gui do jpg
## Dokumentacja:
https://developer.spotify.com/documentation/web-api/reference/
