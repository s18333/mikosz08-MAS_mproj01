package mas.mziolek.mp1.model;

import mas.mziolek.mp1.model.exceptions.DataValidationException;

public class Leader {
//klasa testowa
    private long id;
    private String leaderName;

    public Leader(long id, String leaderName) {
        setId(id);
        setLeaderName(leaderName);
    }

    public long getId() {
        if (id <= 0) {
            throw new DataValidationException("Invalid id!");
        }
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLeaderName() {
        return leaderName;
    }

    public void setLeaderName(String leaderName) {
        if (leaderName == null) {
            throw new DataValidationException("Leader must have a name!");
        }
        this.leaderName = leaderName;
    }

}
