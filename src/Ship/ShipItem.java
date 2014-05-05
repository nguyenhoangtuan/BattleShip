/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Ship;

import Frame.Cell;

/**
 *
 * @author Lyn
 */
public class ShipItem {

    private String name;
    private String links;
    private int sizeShipH;
    private int sizeShipV;
    private String selected;
    private Cell locatedCell;
    private String add = "can";

    public ShipItem(String code) {
        String[] s = code.split("\\|");
        this.name = s[0];
        this.links = s[3];
        this.sizeShipH = Integer.parseInt(s[1]);
        this.sizeShipV = Integer.parseInt(s[2]);
        this.selected = "no";
    }

    public ShipItem(String name, String links, int sizeShipH, int sizeShipV) {
        this.name = name;
        this.links = links;
        this.sizeShipH = sizeShipH;
        this.sizeShipV = sizeShipV;
        this.selected = "no";
    }

    public Cell getLocatedCell() {
        return locatedCell;
    }

    public void setLocatedCell(Cell locatedCell) {
        this.locatedCell = locatedCell;

    }

    public String outPutString() {
        return name + "|" + sizeShipH + "|" + sizeShipV + "|" + links;
    }

    public int getSizeShipH() {
        return sizeShipH;
    }

    public void setSizeShipH(int sizeShipH) {
        this.sizeShipH = sizeShipH;
    }

    public int getSizeShipV() {
        return sizeShipV;
    }

    public void setSizeShipV(int sizeShipV) {
        this.sizeShipV = sizeShipV;
    }

    public String getLinks() {
        return links;
    }

    public void setLinks(String links) {
        this.links = links;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSelected() {
        return selected;
    }

    public void setSelected(String selected) {
        this.selected = selected;
    }

    public String getAdd() {
        return add;
    }

    public void setAdd(String add) {
        this.add = add;
    }

    public void reset() {
        this.add = "can";
        this.locatedCell = null;
        this.selected = "no";

    }
}
