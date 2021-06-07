package mas.mziolek.mp1.model;

import mas.mziolek.mp1.model.exceptions.DataValidationException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ApplicationForm {

    private String messageContent;
    private LocalDate messagePostDate;
    private boolean isAccepted;

    private Guild guild;
    private GuildMember guildMember;

    //Class extension
    private static List<ApplicationForm> applicationFormExtent = new ArrayList<>();

    //region Constructor
    public ApplicationForm(String messageContent, Guild guild, GuildMember guildMember) {

        setMessageContent(messageContent);
        this.messagePostDate = LocalDate.now();
        setGuild(guild);
        setGuildMember(guildMember);

        if (!isUnique()) {
            throw new DataValidationException("Duplicated ApplicationForm!");
        }

        applicationFormExtent.add(this);
    }
    //endregion

    //region Guild & GuildMember Association
    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        if (messageContent == null) {
            throw new DataValidationException("Message is required!");
        }
        this.messageContent = messageContent;
    }

    public LocalDate getMessagePostDate() {
        return messagePostDate;
    }

    public void setMessagePostDate(LocalDate messagePostDate) {
        this.messagePostDate = messagePostDate;
    }

    public boolean isAccepted() {
        return isAccepted;
    }

    public void setAccepted(boolean accepted) {
        isAccepted = accepted;
    }

    public Guild getGuild() {
        return guild;
    }

    public GuildMember getGuildMember() {
        return guildMember;
    }

    private void setGuild(Guild guild) {
        if (guild == null) {
            throw new DataValidationException("Guild is required!");
        }
        this.guild = guild;
        guild.addApplicationForm(this);
    }

    private void setGuildMember(GuildMember guildMember) {
        if (guildMember == null) {
            throw new DataValidationException("Guild member is required!");
        }
        this.guildMember = guildMember;
        guildMember.addApplication(this);
    }

    public void delete() {

        if (this.guild != null) {
            Guild tmpGuild = this.guild;
            this.guild = null;
            tmpGuild.removeApplicationForm(this);
        }

        if (this.guildMember != null) {
            GuildMember tmpGuildMember = this.guildMember;
            this.guildMember = null;
            tmpGuildMember.removeApplicationForm(this);
        }
        applicationFormExtent.remove(this);
    }

    private boolean isUnique() {
        for (ApplicationForm ap : applicationFormExtent) {
            if (ap.guild == this.guild && ap.guildMember == this.guildMember) {
                return false;
            }
        }
        return true;
    }
    //endregion


    public static List<ApplicationForm> getApplicationFormExtent() {
        return Collections.unmodifiableList(applicationFormExtent);
    }

    public static void setApplicationFormExtent(List<ApplicationForm> applicationFormExtent) {
        if (applicationFormExtent == null) {
            throw new DataValidationException("extent cannot be null");
        }
        ApplicationForm.applicationFormExtent = applicationFormExtent;
    }
}
