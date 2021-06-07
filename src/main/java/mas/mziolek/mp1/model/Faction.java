package mas.mziolek.mp1.model;

import mas.mziolek.mp1.model.exceptions.DataValidationException;

public class Faction {

    private String factionName;
    private Leader leader;

    private Region region;
    private long regionId;

    public Faction(String factionName, Leader leader, long regionId) {
        setFactionName(factionName);
        setLeader(leader);
        setRegionId(regionId);
    }

    public String getFactionName() {
        return factionName;
    }

    public Leader getLeader() {
        return leader;
    }

    public void setFactionName(String factionName) {
        if (factionName == null) {
            throw new DataValidationException("Faction must have a name!");
        }
        this.factionName = factionName;
    }

    public Region getRegion() {
        return region;
    }

    public long getRegionId() {
        return regionId;
    }

    //test
    public void setLeader(Leader newLeader) {
        if (this.leader == newLeader) {
            return;
        }
        if (newLeader == null) {
            throw new DataValidationException("New Faction cannot be null!");
        } else {
            this.leader = newLeader;
        }
    }

    public void setRegion(Region newRegion) {
        if (this.region == newRegion) {
            return;
        }
        if (this.region != null) {

            Region tmpRegion = this.region;
            this.region = null;
            tmpRegion.removeFaction(this);

            if (newRegion != null) {
                this.region = newRegion;
                newRegion.addFaction(this);
            }

        } else {
            this.region = newRegion;
            newRegion.addFaction(this);
        }
        this.region = newRegion;
    }

    public void setRegionId(long regionId) {
        if (regionId <= 0) {
            throw new DataValidationException("ID is required!");
        }
        if (this.region != null) {
            Region tmpRegion = this.region;
            this.region.removeFaction(this);
            this.regionId = regionId;
            tmpRegion.addFaction(this);
        } else {
            this.regionId = regionId;
        }
        this.regionId = regionId;
    }
}
