package com.epam.poker.model.dao;

import com.epam.poker.exception.DaoException;
import com.epam.poker.model.entity.game.Game;

import java.util.List;

public interface GameDao extends Dao<Game> {
    long add(Game game) throws DaoException;
    /**
     * Gets games amount in database.
     *
     * @return  an amount of all games.
     *
     * @throws DaoException  if database errors occurs.
     */
    int findGameAmount() throws DaoException;
    /**
     *   Gets list games in range described as offset and amount of games.
     *
     *   @param offset an amount of games to get.
     *
     *   @return a received list of games.
     *
     *   @throws DaoException if database errors occurs.
     */
    List<Game> findGamesRange(int offset, int amount) throws DaoException;
}