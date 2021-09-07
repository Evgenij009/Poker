package com.epam.poker.model.dao.mapper.impl.game;

import com.epam.poker.model.dao.mapper.RowMapper;
import com.epam.poker.model.entity.game.Game;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.epam.poker.model.dao.ColumnName.*;

public class GameRowMapper implements RowMapper<Game> {

    @Override
    public Game map(ResultSet resultSet) throws SQLException {
        return Game.builder()
                .setGameId(resultSet.getLong(GAME_ID))
                .setTitle(resultSet.getString(GAME_TITLE))
                .setDate(resultSet.getTimestamp(GAME_DATE))
                .setBank(resultSet.getBigDecimal(GAME_BANK))
                .setFiveCards(resultSet.getString(GAME_FIVE_CARDS))
                .createGame();
    }
}