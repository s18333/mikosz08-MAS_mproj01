package mas.mziolek.mp1.model;

import mas.mziolek.mp1.model.exceptions.DataValidationException;

import java.time.LocalDate;

public class GuildAchievement {

    private String achievementName;
    private String requirements;
    private LocalDate earnDate;

    private Guild owner;

    public GuildAchievement(String achievementName, String requirements, Guild owner) {
        this.achievementName = achievementName;
        this.requirements = requirements;
        setOwner(owner);
    }

    public void delete(){
        if(this.owner != null){
            Guild tmpOwner = this.owner;
            this.owner = null;
            tmpOwner.removeGuildAchievement(this);
        }
    }

    public Guild getOwner() {
        return owner;
    }

    private void setOwner(Guild owner) {
        if(owner == null){
            throw new DataValidationException("Owner is required!");
        }
        this.owner = owner;
        owner.addGuildAchievement(this);
    }

    public String getAchievementName() {
        return achievementName;
    }

    public void setAchievementName(String achievementName) {
        this.achievementName = achievementName;
    }

    public String getRequirements() {
        return requirements;
    }

    public void setRequirements(String requirements) {
        this.requirements = requirements;
    }

    public LocalDate getEarnDate() {
        return earnDate;
    }

    public void setEarnDate(LocalDate earnDate) {
        this.earnDate = earnDate;
    }
}
