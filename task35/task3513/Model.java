package com.javarush.task.task35.task3513;

import java.util.*;

public class Model {
    private static final int FIELD_WIDTH = 4;
    public int score = 0;
    public int maxTile = 0;
    private Stack<Integer> previousScores = new Stack<>();
    private Stack<Tile[][]> previousStates = new Stack<>();
    private boolean isSaveNeeded = true;
    private Tile[][] gameTiles;

    public Model() {
        resetGameTiles();
    }

    public void rollback() {
        if (!previousStates.isEmpty() && !previousScores.isEmpty()) {
            gameTiles = previousStates.pop();
            score = previousScores.pop();
        }
    }

    private void saveState(Tile[][] tiles) {
        Tile[][] currentStateGameTiles = new Tile[tiles.length][tiles.length];
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles.length; j++) {
                currentStateGameTiles[i][j] = new Tile(tiles[i][j].value);
            }
        }
        previousScores.push(score);
        previousStates.push(currentStateGameTiles);
        isSaveNeeded = false;
    }

    private void addTile() {
        List<Tile> tiles = getEmptyTiles();
        if (!tiles.isEmpty()) {
            tiles.get((int) (tiles.size() * Math.random())).value = (Math.random() < 0.9 ? 2 : 4);
        }
    }

    public Tile[][] getGameTiles() {
        return gameTiles;
    }

    private List<Tile> getEmptyTiles() {
        List<Tile> tiles = new ArrayList<>();
        for (int i = 0; i < FIELD_WIDTH; i++) {
            for (int j = 0; j < FIELD_WIDTH; j++) {
                if (gameTiles[i][j].isEmpty()) {
                    tiles.add(gameTiles[i][j]);
                }
            }
        }
        return tiles;
    }

    public void resetGameTiles() {

        score = 0;
        maxTile = 0;
        gameTiles = new Tile[FIELD_WIDTH][FIELD_WIDTH];
        for (int i = 0; i < FIELD_WIDTH; i++) {
            for (int j = 0; j < FIELD_WIDTH; j++) {
                gameTiles[i][j] = new Tile();
            }
        }
        addTile();
        addTile();

    }

    private boolean compressTiles(Tile[] tiles) {
        boolean isCompressed = false;
        int index = 0;
        for (int i = 0; i < tiles.length; i++) {
            if (!tiles[i].isEmpty()) {
                if (i != index) {
                    tiles[index] = tiles[i];
                    tiles[i] = new Tile();
                    isCompressed = true;
                }
                index++;
            }
        }
        return isCompressed;
    }

    private boolean mergeTiles(Tile[] tiles) {
        boolean isMerged = false;
        for (int i = 0; i < tiles.length - 1; i++) {
            if (tiles[i].value == tiles[i + 1].value) {
                tiles[i].value += tiles[i + 1].value;
                tiles[i + 1] = new Tile();
                if (tiles[i].value > maxTile) {
                    maxTile = tiles[i].value;
                }
                score += tiles[i].value;
                if (tiles[i].value != 0) {
                    isMerged = true;
                }
            }
        }
        compressTiles(tiles);
        return isMerged;
    }

    public void left() {
        if (isSaveNeeded) {
            saveState(gameTiles);
        }
        boolean isChange = false;
        for (Tile[] gameTile : gameTiles) {
            if (compressTiles(gameTile) | mergeTiles(gameTile)) {
                isChange = true;
            }
        }
        if (isChange) {
            addTile();
        }
        isSaveNeeded = true;
    }

    public void up() {
        saveState(gameTiles);
        rotateArray();
        rotateArray();
        rotateArray();
        left();
        rotateArray();
    }

    public void down() {
        saveState(gameTiles);
        rotateArray();
        left();
        rotateArray();
        rotateArray();
        rotateArray();
    }

    public void right() {
        saveState(gameTiles);
        rotateArray();
        rotateArray();
        left();
        rotateArray();
        rotateArray();

    }

    private void rotateArray() {
        Tile[][] tempGameTiles = new Tile[gameTiles.length][gameTiles.length];
        for (int i = 0; i < gameTiles.length; i++) {
            for (int j = 0; j < gameTiles.length; j++) {
                tempGameTiles[j][gameTiles.length - 1 - i] = gameTiles[i][j];
            }
        }
        gameTiles = tempGameTiles;
    }

    boolean canMove() {
        if (getEmptyTiles().size() != 0) {
            return true;
        }
        for (int x = 0; x < FIELD_WIDTH; x++) {
            for (int y = 0; y < FIELD_WIDTH; y++) {
                Tile t = gameTiles[x][y];
                if ((x < FIELD_WIDTH - 1 && t.value == gameTiles[x + 1][y].value)
                        || ((y < FIELD_WIDTH - 1) && t.value == gameTiles[x][y + 1].value)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void randomMove() {
        int n = ((int) (Math.random() * 100)) % 4;
        if (n != 0) {
            switch (n) {
                case 0:
                    up();
                    break;
                case 1:
                    left();
                    break;
                case 2:
                    down();
                    break;
                case 3:
                    right();
                    break;
            }
        }
    }

    private boolean hasBoardChanged() {
        Tile[][] lastBoard = previousStates.peek();
        for (int i = 0; i < FIELD_WIDTH; i++) {
            for (int j = 0; j < FIELD_WIDTH; j++) {
                if (lastBoard[i][j].value != gameTiles[i][j].value) {
                    return true;
                }
            }
        }

        return false;
    }

    private MoveEfficiency getMoveEfficiency(Move move) {
        move.move();
        if (!hasBoardChanged()) {
            rollback();
            return new MoveEfficiency(-1, 0, move);
        }
        MoveEfficiency moveEfficiency = new MoveEfficiency(getEmptyTiles().size(), score, move);
        rollback();
        return moveEfficiency;
    }

    public void autoMove() {
        PriorityQueue<MoveEfficiency> queue = new PriorityQueue<>(4,Collections.reverseOrder());
        queue.offer(getMoveEfficiency(this::down));
        queue.offer(getMoveEfficiency(this::up));
        queue.offer(getMoveEfficiency(this::right));
        queue.offer(getMoveEfficiency(this::left));

        queue.peek().getMove().move();

    }
}
