package com.epam.poker.controller.command.impl.game;

import com.epam.poker.controller.command.Command;
import com.epam.poker.controller.command.CommandResult;
import com.epam.poker.util.ParserDataToJson;
import com.epam.poker.util.constant.Attribute;
import com.epam.poker.controller.request.RequestContext;
import com.epam.poker.exception.DaoException;
import com.epam.poker.exception.InvalidParametersException;
import com.epam.poker.exception.ServiceException;
import com.epam.poker.game.entity.Lobby;
import com.epam.poker.game.entity.Table;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class TakeLobbyDataCommand implements Command {
    private static final Lobby lobby = Lobby.getInstance();
    private static final Gson gson = new Gson();
    private static final ParserDataToJson parserDataToJson = ParserDataToJson.getInstance();
    private static final ObjectMapper mapper = new ObjectMapper();

    @Override
    public CommandResult execute(RequestContext requestContext) throws ServiceException, InvalidParametersException, DaoException {
        List<String> lobbyTablesJson = new ArrayList<>();
        List<Table> tables = lobby.findAllTables();
        for (Table table : tables) {
            lobbyTablesJson.add(parserDataToJson.parseDataTableForLobby(table));
        }
        CommandResult commandResult = new CommandResult(true);
        commandResult.setJsonResponse(String.valueOf(lobbyTablesJson));
        return commandResult;
    }
}
