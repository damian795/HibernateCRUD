import org.example.Game;
import org.example.GameCRUD;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class GameCRUDTest {

    private SessionFactory sessionFactory;
    private Session session;
    private GameCRUD gameCRUD;

    @Before
    public void setUp() {
        //Użycie osobnej konfiguracji testowej
        sessionFactory = new Configuration().configure("hibernate-test.cfg.xml").buildSessionFactory();
        session = sessionFactory.openSession();

        gameCRUD = new GameCRUD();
    }

    @After
    public void tearDown() {
        session.close();
        sessionFactory.close();
    }

    @Test
    public void testSaveGame() {
        Game game = new Game("Cyberpunk 2077", LocalDate.of(2020, 12, 10), "PC");
        gameCRUD.saveGame(game);

        List<Game> games = gameCRUD.getAllGames();
        //Sprawdza, czy rozmiar listy gier wynosi 1 co oznacza że gra została poprawnie dodana
        assertEquals(1, games.size());
        //Sprawdza, czy nazwa pierwszej gry w liście to "Cyberpunk 2077"
        //asercja sprawdza czy wyniki sa zgodne z oczekiwaniami
        assertEquals("Cyberpunk 2077", games.get(0).getName());
    }

    @Test
    public void testUpdateGame() {
        //Tworzenie nowej gry i zapisywanie jej
        Game game = new Game("Cyberpunk 2077", LocalDate.of(2020, 12, 10), "PC");
        Integer id = gameCRUD.saveGame(game);  // Zakładam, że saveGame zwraca ID nowej gry

        //Aktualizacja gry
        gameCRUD.updateGame( id, "Cyberpunk 2077 Updated");

        //Pobranie zaktualizowanej gry
        Game updatedGame = gameCRUD.getGame(id);
        assertEquals("Cyberpunk 2077 Updated", updatedGame.getName());
    }

    @Test
    public void testDeleteGame() {
        Game game = new Game("Cyberpunk 2077", LocalDate.of(2020, 12, 10), "PC");
        gameCRUD.saveGame(game);
        List<Game> gamesBefore = gameCRUD.getAllGames();
        gameCRUD.deleteGame(game.getId());

        List<Game> gamesAfter = gameCRUD.getAllGames();
        assertEquals(gamesBefore.size()-1, gamesAfter.size());
    }
}
