/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import Frame.Cell;
import java.util.Observable;

/**
 *
 * @author Lyn
 */
public class SeaComModel extends Observable {

    public void change() {
        setChanged();
        notifyObservers();
    }

    public void nuclearSkill(Cell[][] cellsAI, int i, int j) {
        if (0 <= i && i <= 9 && 0 <= j + 1 && j + 1 <= 9) {
            cellsAI[i][j + 1].setTokenSpecial('X');
        }
        if (0 <= i + 1 && i + 1 <= 9 && 0 <= j && j <= 9) {
            cellsAI[i + 1][j].setTokenSpecial('X');
        }
        if (0 <= i - 1 && i - 1 <= 9 && 0 <= j && j <= 9) {
            cellsAI[i - 1][j].setTokenSpecial('X');
        }
        if (0 <= i && i <= 9 && 0 <= j - 1 && j - 1 <= 9) {
            cellsAI[i][j - 1].setTokenSpecial('X');
        }
    }

    public void rainSkill(Cell[][] cellsAI, int i, int j) {
        if (0 <= i + 2 && i + 2 <= 9 && 0 <= j + 2 && j + 2 <= 9) {
            cellsAI[i + 2][j + 2].setTokenSpecial('X');
        }
        if (0 <= i - 2 && i - 2 <= 9 && 0 <= j - 2 && j - 2 <= 9) {
            cellsAI[i - 2][j - 2].setTokenSpecial('X');
        }
        if (0 <= i - 2 && i - 2 <= 9 && 0 <= j + 2 && j + 2 <= 9) {
            cellsAI[i - 2][j + 2].setTokenSpecial('X');
        }
        if (0 <= i + 2 && i + 2 <= 9 && 0 <= j - 2 && j - 2 <= 9) {
            cellsAI[i + 2][j - 2].setTokenSpecial('X');
        }
    }

    public void nuclearTarget(Cell[][] cellsAI, int i, int j) {
        if (0 <= i && i <= 9 && 0 <= j + 1 && j + 1 <= 9) {
            cellsAI[i][j + 1].setTarget('A');
        }
        if (0 <= i + 1 && i + 1 <= 9 && 0 <= j && j <= 9) {
            cellsAI[i + 1][j].setTarget('A');
        }
        if (0 <= i - 1 && i - 1 <= 9 && 0 <= j && j <= 9) {
            cellsAI[i - 1][j].setTarget('A');
        }
        if (0 <= i && i <= 9 && 0 <= j - 1 && j - 1 <= 9) {
            cellsAI[i][j - 1].setTarget('A');
        }
    }

    public void rainTarget(Cell[][] cellsAI, int i, int j) {
        if (0 <= i + 2 && i + 2 <= 9 && 0 <= j + 2 && j + 2 <= 9) {
            cellsAI[i + 2][j + 2].setTarget('A');
        }
        if (0 <= i - 2 && i - 2 <= 9 && 0 <= j - 2 && j - 2 <= 9) {
            cellsAI[i - 2][j - 2].setTarget('A');
        }
        if (0 <= i - 2 && i - 2 <= 9 && 0 <= j + 2 && j + 2 <= 9) {
            cellsAI[i - 2][j + 2].setTarget('A');
        }
        if (0 <= i + 2 && i + 2 <= 9 && 0 <= j - 2 && j - 2 <= 9) {
            cellsAI[i + 2][j - 2].setTarget('A');
        }
    }

    public void clearTarget(Cell[][] cellsAI, int i, int j) {
        cellsAI[i][j].setTarget(' ');
        if (0 <= i && i <= 9 && 0 <= j + 1 && j + 1 <= 9) {
            cellsAI[i][j + 1].setTarget(' ');
        }
        if (0 <= i + 1 && i + 1 <= 9 && 0 <= j && j <= 9) {
            cellsAI[i + 1][j].setTarget(' ');
        }
        if (0 <= i - 1 && i - 1 <= 9 && 0 <= j && j <= 9) {
            cellsAI[i - 1][j].setTarget(' ');
        }
        if (0 <= i && i <= 9 && 0 <= j - 1 && j - 1 <= 9) {
            cellsAI[i][j - 1].setTarget(' ');
        }
        if (0 <= i + 2 && i + 2 <= 9 && 0 <= j + 2 && j + 2 <= 9) {
            cellsAI[i + 2][j + 2].setTarget(' ');
        }
        if (0 <= i - 2 && i - 2 <= 9 && 0 <= j - 2 && j - 2 <= 9) {
            cellsAI[i - 2][j - 2].setTarget(' ');
        }
        if (0 <= i - 2 && i - 2 <= 9 && 0 <= j + 2 && j + 2 <= 9) {
            cellsAI[i - 2][j + 2].setTarget(' ');
        }
        if (0 <= i + 2 && i + 2 <= 9 && 0 <= j - 2 && j - 2 <= 9) {
            cellsAI[i + 2][j - 2].setTarget(' ');
        }
    }
}
