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
public class SeaPlayerModel extends Observable {

    private Cell[][] cells = new Cell[10][10];

    public void increaseMana(int mana) {
        if (mana >= 10) {
            mana = 10;
        } else {
            mana = mana + 1;
        }
        setChanged();
        notifyObservers(mana);
    }

    public void decreaseMana(int mana) {
        if (mana <= 0) {
            mana = 0;
        } else {
            mana = mana - 1;
        }
        setChanged();
        notifyObservers(mana);
    }

    public void useMana(int mana, int use) {
        if (mana >= use) {
            mana = mana - use;
        }
        setChanged();
        notifyObservers(mana);
    }

    public void setCell(int x, int y) {
        cells[x][y].setStatus("shipLocation");
        setChanged();
        notifyObservers();
    }

    public void change() {
        setChanged();
        notifyObservers();
    }
}
