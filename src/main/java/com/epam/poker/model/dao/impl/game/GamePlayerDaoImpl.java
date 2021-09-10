package com.epam.poker.model.dao.impl.game;

import com.epam.poker.exception.DaoException;
import com.epam.poker.model.dao.AbstractDao;
import com.epam.poker.model.dao.mapper.impl.game.GamePlayerRowMapper;
import com.epam.poker.model.entity.game.GamePlayer;

import java.util.List;
import java.util.Optional;

import static com.epam.poker.model.dao.ColumnName.GAME_PlAYERS;

public class GamePlayerDaoImpl extends AbstractDao<com.epam.poker.model.entity.game.GamePlayer> implements GamePlayerDao {
    private static GamePlayerDao instance;
    public static final String SQL_FIND_ALL_GAME_PLAYERS = """
            SELECT game_player_id, last_action, two_cards, combinations_cards,
            game_player_user_id, game_id
            FROM profile_players
            """;
    public static final String SQL_ADD_GAME_PLAYER = """
            INSERT INTO profile_players
            (game_player_id, last_action, two_cards, combinations_cards,
            game_player_user_id, game_id)
            VALUES (?,?,?,?,?,?)
            """;
    public static final String SQL_FIND_GAME_PLAYER_BY_USER_ID = """
            SELECT user_id, login, first_name, last_name, email,
            balance, role, status, phone_number, create_time
            FROM profile_players
            WHERE user_id=?
            """;
    public static final String SQL_FIND_GAME_PLAYER_BY_GAME_ID = """
            SELECT user_id, login, first_name, last_name, email,
            balance, role, status, phone_number, create_time
            FROM profile_players
            WHERE game_id=?
            """;
    public static final String SQL_FIND_GAME_PLAYER_BY_GAME_PLAYER_ID = """
            SELECT user_id, login, first_name, last_name, email,
            balance, role, status, phone_number, create_time
            FROM profile_players
            WHERE game_player_id=?
            """;

    private GamePlayerDaoImpl() {
        super(GamePlayerRowMapper.getInstance(), GAME_PlAYERS);
    }

    public static GamePlayerDao getInstance() {
        if (instance == null) {
            instance = new GamePlayerDaoImpl();
        }
        return instance;
    }

    @Override
    public Optional<GamePlayer> findById(long id) throws DaoException {
        return executeForSingleResult(SQL_FIND_GAME_PLAYER_BY_GAME_PLAYER_ID, id);
    }

    @Override
    public List<GamePlayer> findAll() throws DaoException {
        return executeQuery(SQL_FIND_ALL_GAME_PLAYERS);
    }

    @Override
    public int findGamePLayerAmount() throws DaoException {
        Optional<String> additionalCondition = Optional.empty();
        return findRowsAmount(additionalCondition);
    }

    @Override
    public long add(GamePlayer gamePlayer) throws DaoException {
        return executeInsertQuery(SQL_ADD_GAME_PLAYER,
                gamePlayer.getGamePlayerId(),
                gamePlayer.getLastAction(),
                gamePlayer.getTwoCards(),
                gamePlayer.getCombinationsCards(),
                gamePlayer.getUserId(),
                gamePlayer.getGameId());
    }

    @Override
    public List<GamePlayer> findGamePlayerByGameId(long gameId) throws DaoException {
        return executeQuery(SQL_FIND_GAME_PLAYER_BY_GAME_ID);
    }

    @Override
    public List<GamePlayer> findGamePlayerByUserId(long userId) throws DaoException {
        return executeQuery(SQL_FIND_GAME_PLAYER_BY_USER_ID);
    }
}