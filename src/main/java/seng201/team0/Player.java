package seng201.team0;

import seng201.team0.models.Tower;

import java.util.List;
public class Player {
    private String name;
    private List<Tower> towerList;

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String tempName) {
        this.name = tempName;
    }

    public List<Tower> getTowerList() {
        return towerList;
    }

    public void setTowerList(List<Tower> towerList) {
        this.towerList = towerList;
    }
}
