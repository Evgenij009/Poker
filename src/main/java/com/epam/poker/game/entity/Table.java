package com.epam.poker.game.entity;

import com.epam.poker.game.logic.PokerGameService;
import com.epam.poker.model.entity.Entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Table implements Entity {
    private PokerGameService pokerGameService = PokerGameService.getInstacne();
    private int id;
    private String name;
    private int seatsCount;
    private int gamblersSeatedCount;
    private BigDecimal bigBlind;
    private BigDecimal smallBlind;
    private boolean privateTable;
    private int gamblersSittingInCount;
    private int gamblersInHandCount;
    private String lastGamblerToAct;
    private boolean gameIsOn;
    private boolean headsUp;
    private Gambler[] seats;
    private Deck deck;
//    private eventEmitter;
    private BigDecimal minBuyIn;
    private BigDecimal maxBuyIn;
    private List<Pot> pot;
    private BigDecimal biggestBet;
    private Integer dealerSeat;
    private Integer activeSeat;
    private String[] seatsPlace;
    private String phaseGame;
    private String[] board;
    private Log log;

    public Table(int id, String name, int seatsCount, BigDecimal bigBlind,
                 BigDecimal smallBlind, BigDecimal maxBuyIn, BigDecimal minBuyIn, boolean privateTable) {
        this.id = id;
        this.name = name;
        this.seatsCount = seatsCount;
        this.bigBlind = bigBlind;
        this.smallBlind = smallBlind;
        this.maxBuyIn = maxBuyIn;
        this.minBuyIn = minBuyIn;
        this.biggestBet = new BigDecimal(0);
        this.seatsPlace = new String[seatsCount];
        this.privateTable = privateTable;
        this.seats = new Gambler[seatsCount];
//        initSeatsTable(seatsCount);
        this.deck = new Deck();
        this.board = new String[]{"", "", "", "", ""};
        this.pot = new ArrayList<>(1);
        this.log = new Log();
    }

//    private void initSeatsTable(int seatsCount) {
//        for (Gambler gambler : this.se)
//    }

    public void addGamblerOnTable(Gambler gambler, int numberSeat, BigDecimal bet) {
        this.seats[numberSeat] = gambler;
        gambler.setSittingOnTable(this.id);
        gambler.setMoneyInPlay(bet);
        gambler.setNumberSeatOnTable(numberSeat);
//        gambler.setMoneyInPlay(gambler.getBalance());
        this.gamblersSeatedCount++;
        pokerGameService.playerSatIn(this, gambler);
    }

    public void deleteGamblerOfSeatByEntity(Gambler gambler) {
        for (int i = 0; i < this.seats.length; ++i) {
            if (this.seats[i].equals(gambler)) {
                this.seats[i] = null;
            }
        }
    }

    public void deleteGamblerOfSeatByIndex(int numberOfSeats) {
        this.seats[numberOfSeats] = null;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSeatsCount(int seatsCount) {
        this.seatsCount = seatsCount;
    }

    public void setGamblersSeatedCount(int gamblersSeatedCount) {
        this.gamblersSeatedCount = gamblersSeatedCount;
    }

    public void setBigBlind(BigDecimal bigBlind) {
        this.bigBlind = bigBlind;
    }

    public void setSmallBlind(BigDecimal smallBlind) {
        this.smallBlind = smallBlind;
    }

    public boolean isPrivateTable() {
        return privateTable;
    }

    public void setPrivateTable(boolean privateTable) {
        this.privateTable = privateTable;
    }

    public int getGamblersSittingInCount() {
        return gamblersSittingInCount;
    }

    public void setGamblersSittingInCount(int gamblersSittingInCount) {
        this.gamblersSittingInCount = gamblersSittingInCount;
    }

    public int getGamblersInHandCount() {
        return gamblersInHandCount;
    }

    public void setGamblersInHandCount(int gamblersInHandCount) {
        this.gamblersInHandCount = gamblersInHandCount;
    }

    public String getLastGamblerToAct() {
        return lastGamblerToAct;
    }

    public void setLastGamblerToAct(String lastGamblerToAct) {
        this.lastGamblerToAct = lastGamblerToAct;
    }

    public boolean isGameIsOn() {
        return gameIsOn;
    }

    public void setGameIsOn(boolean gameIsOn) {
        this.gameIsOn = gameIsOn;
    }

    public boolean isHeadsUp() {
        return headsUp;
    }

    public void setHeadsUp(boolean headsUp) {
        this.headsUp = headsUp;
    }

    public Gambler[] getSeats() {
        return seats;
    }

    public void setSeats(Gambler[] seats) {
        this.seats = seats;
    }

//    public void deleteGamblerToSeats(Gambler gambler) {
//        int seatNumber = gambler.getNumberSeatOnTable();
//        if (seatNumber > -1) {
//            this.seatsPlace[seatNumber] = null;
//            gambler.setNumberSeatOnTable(-1);
//        }
//        this.seats.remove(gambler);
//    }

    public Deck getDeck() {
        return deck;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    public BigDecimal getMinBuyIn() {
        return minBuyIn;
    }

    public void setMinBuyIn(BigDecimal minBuyIn) {
        this.minBuyIn = minBuyIn;
    }

    public BigDecimal getMaxBuyIn() {
        return maxBuyIn;
    }

    public void setMaxBuyIn(BigDecimal maxBuyIn) {
        this.maxBuyIn = maxBuyIn;
    }

    public List<Pot> getPots() {
        return pot;
    }

    public Pot getPotByIndex(int index) {
        return this.pot.get(index);
    }

    public void setPot(List<Pot> pot) {
        this.pot = pot;
    }

    public BigDecimal getBiggestBet() {
        return biggestBet;
    }

    public void setBiggestBet(BigDecimal biggestBet) {
        this.biggestBet = biggestBet;
    }

    public Integer getDealerSeat() {
        return dealerSeat;
    }

    public void setDealerSeat(Integer dealerSeat) {
        this.dealerSeat = dealerSeat;
    }

    public Integer getActiveSeat() {
        return activeSeat;
    }

    public void setActiveSeat(Integer activeSeat) {
        this.activeSeat = activeSeat;
    }

    public String[] getSeatsPlace() {
        return seatsPlace;
    }

    public void setSeatsPlace(String[] seatsPlace) {
        this.seatsPlace = seatsPlace;
    }

    public String getPhaseGame() {
        return phaseGame;
    }

    public void setPhaseGame(String phaseGame) {
        this.phaseGame = phaseGame;
    }

    public String[] getBoard() {
        return board;
    }

    public void setBoard(String[] board) {
        this.board = board;
    }

    public Log getLog() {
        return log;
    }

    public void setLog(Log log) {
        this.log = log;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getSeatsCount() {
        return seatsCount;
    }

    public int getGamblersSeatedCount() {
        return gamblersSeatedCount;
    }

    public BigDecimal getBigBlind() {
        return bigBlind;
    }

    public BigDecimal getSmallBlind() {
        return smallBlind;
    }

}