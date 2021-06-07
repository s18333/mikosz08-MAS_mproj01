package mas.mziolek.mp1.model;

import mas.mziolek.mp1.model.exceptions.DataValidationException;

import java.util.*;
import java.util.stream.Collectors;

public class Region {

    private String regionName;

    private Map<Long, Faction> factions = new HashMap<>();

    public Region(String regionName) {
        this.regionName = regionName;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public Map<Long, Faction> getFactions() {
        return Collections.unmodifiableMap(factions);
    }

    public void addFaction(Faction f) {
        if (f == null) {
            throw new DataValidationException("Faction is required!");
        }
        if (this.factions.containsKey(f.getRegionId())) {
            return;
        }
        this.factions.put(f.getRegionId(), f);
        f.setRegion(this);
    }

    public void removeFaction(Faction f) {
        if (f == null) {
            throw new DataValidationException("Faction is required!");
        }
        if (!this.factions.containsKey(f.getRegionId())) {
            return;
        }
        this.factions.remove(f.getRegionId());
        f.setRegion(null);
    }

    public Set<Faction> getFactionList() {
        if (factions != null) {
            return new HashSet<>(factions.values());
        }
        return new HashSet<>();
    }

    public Faction findFactionById(Long id) {
        return this.factions.get(id);
    }

    public Set<Faction> getFactionListByLeader(String leaderName){
        return getFactionList()
                .stream()
                .filter(x -> x.getLeader().getLeaderName().equals(leaderName))
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

}
