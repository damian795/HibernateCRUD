package org.example;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        GameCRUD gameCRUD = new GameCRUD();

        //Dodawanie nowej gry
        Game game = new Game("The Witcher 3", LocalDate.of(2015,1,1), "PC");
        Integer gameId = gameCRUD.saveGame(game);
        System.out.println("Zapisano grę o ID: " + gameId);

        //Pobieranie gry
        Game savedGame = gameCRUD.getGame(gameId);
        System.out.println("Pobrana gra: " + savedGame);

        //Aktualizacja nazwy gry
        gameCRUD.updateGame(gameId, "The Witcher 3: Wild Hunt");
        Game updatedGame = gameCRUD.getGame(gameId);
        System.out.println("Zaktualizowana gra: " + updatedGame);

        //Pobieranie wszystkich gier
        List<Game> allGames = gameCRUD.getAllGames();
        System.out.println("Wszystkie gry:");
        allGames.forEach(System.out::println);

        //Usuwanie gry
        gameCRUD.deleteGame(4);
        System.out.println("Gra usunięta.");

        //czerwone logi informuja np ze uzywam wbudowanej puli połączen Hibernate ktora nie jest dobra do uzytku produkcyjnego
        //logi pokazuja informacje np jakiego sterownika MySQL uzywamy do poalczenia z baza daych
        //informacje o właściwościach połączenia: INFO: HHH10001001: Connection properties: {password=****, user=root}
        //jaki dialekt uzywa hibernate
    }
}
