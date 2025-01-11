package org.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class GameCRUD {
    private SessionFactory sessionFactory;

    //Konstruktor ktory konfiguruje fabrykę sesji Hibernate
    public GameCRUD() {
        sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    //Metoda do zapisywania nowej gry w bazie danych
    public Integer saveGame(Game game) {
        Session session = null;
        Integer id = null;

        try {
            session = this.sessionFactory.openSession(); //otwieramy sesje
            Transaction transaction = session.beginTransaction(); //rozpoczynamy transakcje
            id = (Integer) session.save(game); // zapisujmy obiekt i uzyskuejmy jego id
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();  // wyjatek
        } finally {
            if (session != null) {
                session.close();  // zamykamy sesje aby zwolnić zasoby
            }
        }

        return id; //zwracamy id nowo zapisanej gry
    }

    //pobieramy gre na podstawie ID
    public Game getGame(int id) {
        Session session = null;
        Game game = null;

        try {
            session = this.sessionFactory.openSession();
            game = session.get(Game.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return game; //zwracamy gre albo null jezeli nie znajdzie
    }

    public List<Game> getAllGames() {
        Session session = null;
        List<Game> games = null;

        try {
            session = this.sessionFactory.openSession();
            //Game.class to inaczej tabela games z naszej bazy dancyh, gdyz: @Table(name = "games")
            //Game.class to informacja o typie zwracanych wynikow, pozwala to na prawidłowe
            // skonwertowanie wyników z bazy danych na obiekt
            // .list() to zwraca wynik jako liste
            games = session.createQuery("FROM Game", Game.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return games;
    }

    public void updateGame(int id, String newName) {
        Session session = null;

        try {
            session = this.sessionFactory.openSession(); //otweiramy sesje
            Transaction transaction = session.beginTransaction(); //rozpoczynamy transakcje
            Game game = session.get(Game.class, id); //pobieramy gre na podstawie id
            if (game != null) { //sprawdzamy czy istnieje
                game.setName(newName); //aktualizujemy nawze
                session.update(game); //zapisujemy nasze zmiany do bazy
                transaction.commit(); //zatweirdzamy transakcje
            } else {
                System.out.println("Game not found with ID: " + id);
            }
        } catch (Exception e) {
            e.printStackTrace();  // Log exception
        } finally {
            if (session != null) {
                session.close();  // Ensure session is closed
            }
        }
    }

    public void deleteGame(int id) {
        Session session = null;

        try {
            session = this.sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            Game game = session.get(Game.class, id);
            if (game != null) {
                session.delete(game);
                transaction.commit();
            } else {
                System.out.println("Game not found with ID: " + id);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

}
