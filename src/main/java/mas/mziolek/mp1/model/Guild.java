package mas.mziolek.mp1.model;

import mas.mziolek.mp1.model.exceptions.DataValidationException;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

public class Guild implements Serializable {

    private String guildName;
    private LocalDate dateOfCreation;

    // Collection of Guild_Members.
    private Set<GuildMember> guildMembers = new HashSet<>();

    // Collection of Guild applicants.
    private Set<ApplicationForm> applicationForms = new HashSet<>();

    //Collection of Guild achievements.
    private Set<GuildAchievement> guildAchievements = new HashSet<>();

    //Class extension
    private static List<Guild> guildExtent = new ArrayList<>();

    //region Constructor

    /**
     * Class constructor.
     */
    public Guild(String guildName, GuildMember newMember) {
        setGuildName(guildName);
        this.dateOfCreation = LocalDate.now();
        addGuildMember(newMember);
        guildExtent.add(this);
    }
    //endregion

    //region GuildMember Association
    public Set<GuildMember> getGuildMembers() {
        return Collections.unmodifiableSet(guildMembers);
    }

    public void addGuildMember(GuildMember newMember) {
        if (newMember == null) {
            throw new DataValidationException("Member is set to null!");
        }
        if (this.guildMembers.contains(newMember)) {
            return;
        }
        this.guildMembers.add(newMember);
        newMember.setGuild(this);
    }

    public void removeGuildMember(GuildMember guildMember) {
        if (!this.guildMembers.contains(guildMember)) {
            return;
        }
        if (this.guildMembers.size() == 1) {
            throw new DataValidationException("Cannot remove last guild member!");
        }
        this.guildMembers.remove(guildMember);
        guildMember.setGuild(null);
    }

    public String getGuildName() {
        return guildName;
    }

    public void setGuildName(String guildName) {
        if (guildName == null) {
            throw new DataValidationException("Guild needs a name!");
        }
        this.guildName = guildName;
    }

    public LocalDate getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(LocalDate dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    //endregion

    //region ApplicationForm Association
    public Set<ApplicationForm> getApplicationForms() {
        return Collections.unmodifiableSet(applicationForms);
    }

    public void addApplicationForm(ApplicationForm applicationForm) {
        if (applicationForm == null) {
            throw new DataValidationException("Application from is required!");
        }

        if (this.applicationForms.contains(applicationForm)) {
            return;
        }

        if (applicationForm.getGuild() != this) {
            throw new DataValidationException("Guild is not related!");
        }

        this.applicationForms.add(applicationForm);

    }

    public void removeApplicationForm(ApplicationForm applicationForm) {

        if (!this.applicationForms.contains(applicationForm)) {
            return;
        }

        this.applicationForms.remove(applicationForm);
        applicationForm.delete();

    }
    //endregion

    //region GuildAchievement Association
    public Set<GuildAchievement> getGuildAchievements() {
        return Collections.unmodifiableSet(guildAchievements);
    }

    public void addGuildAchievement(GuildAchievement newGuildAchievement) {
        if (newGuildAchievement == null) {
            throw new DataValidationException("Achievement is required!");
        }
        if (newGuildAchievement.getOwner() != this) {
            throw new DataValidationException("Achievement is not related to this Guild!");
        }
        this.guildAchievements.add(newGuildAchievement);
    }

    public void removeGuildAchievement(GuildAchievement guildAchievement) {
        if (!this.guildAchievements.contains(guildAchievement)) {
            return;
        }
        this.guildAchievements.remove(guildAchievement);
        guildAchievement.delete();
    }

    public void delete() {
        List<GuildAchievement> copiedAchievementList = new ArrayList<>(this.guildAchievements);
        for (GuildAchievement ga : copiedAchievementList) {
            ga.delete();
        }
    }
    //endregion


    public static List<Guild> getGuildExtent() {
        return Collections.unmodifiableList(guildExtent);
    }

    public static void setGuildExtent(List<Guild> guildExtent) {
        if (guildExtent == null) {
            throw new DataValidationException("extent cannot be null");
        }
        Guild.guildExtent = guildExtent;
    }
}
