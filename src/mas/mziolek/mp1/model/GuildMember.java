package mas.mziolek.mp1.model;

import mas.mziolek.mp1.model.enums.MemberStatus;
import mas.mziolek.mp1.model.exceptions.DataValidationException;

import java.io.*;
import java.time.Duration;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import static mas.mziolek.mp1.model.enums.MemberStatus.ONLINE;

public class GuildMember implements Serializable {

    private long id;                                                                                                    //player's member ID
    private int level;                                                                                                  //player's character level.
    private float reputationAwarded;                                                                                    //player's points donated to the guild

    private String nickname;                                                                                            //player's nickname.
    private String messageOfTheDay;                                                                                     //player's day message

    private Set<String> playerClasses = new HashSet<>();                                                                //player's character classes
    private PlayerLocation playerLocation;                                                                              //player's current location on map
    private LocalDate dateOfAccession;                                                                                  //date the player joined the guild
    private MemberStatus status;                                                                                        //player online status

    private final static int STARTING_REP_POINTS = 0;                                                                   //default value of reputation points for every guild member - class attribute

    private static List<GuildMember> guildMembersExtent = new ArrayList<>();                                            //Class extension

    /**
     * Class constructor.
     */
    public GuildMember(long id, int level, String nickname, String playerClass,
                       PlayerLocation playerLocation, LocalDate dateOfAccession, MemberStatus status) {
        this.id = id;
        this.reputationAwarded = STARTING_REP_POINTS;

        setLevel(level);
        setNickname(nickname);
        setPlayerLocation(playerLocation);
        setDateOfAccession(dateOfAccession);
        setStatus(status);

        addPlayerClass(playerClass);

        guildMembersExtent.add(this);
    }

    /**
     * Class constructor with optional attributes.
     */
    public GuildMember(long id, int level, String nickname, String messageOfTheDay, String playerClass,
                       PlayerLocation playerLocation, LocalDate dateOfAccession, MemberStatus status) {
        this.id = id;
        this.reputationAwarded = STARTING_REP_POINTS;
        setLevel(level);
        setNickname(nickname);
        setMessageOfTheDay(messageOfTheDay);
        setPlayerLocation(playerLocation);
        setDateOfAccession(dateOfAccession);
        setStatus(status);

        addPlayerClass(playerClass);

        guildMembersExtent.add(this);
    }

    /**
     * ID.
     */
    public long getId() {
        return id;
    }

    public void setId(long id) {
        if (id < 1) {
            throw new DataValidationException("id cannot be less than 1");
        }
        this.id = id;
    }

    /**
     * Nickname.
     */
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        ValidateEnteredString(nickname, "nickname cannot be null or empty");
        this.nickname = nickname;
    }

    /**
     * Level.
     */
    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        if (level < 1) {
            throw new DataValidationException("level cannot be less than 1");
        }
        this.level = level;
    }

    /**
     * Player Classes. - repeatable attribute
     */
    public Set<String> getPlayerClasses() {
        return Collections.unmodifiableSet(playerClasses);
    }

    public void addPlayerClass(String playerClass) {
        ValidateEnteredString(playerClass, "player class cannot be null or empty");
        this.playerClasses.add(playerClass);
    }

    public void removePlayerClass(String playerClass) {

        ValidateEnteredString(playerClass, "player class cannot be null or empty");
        if (playerClasses.size() <= 1) {
            throw new DataValidationException("cannot remove last player's class");
        }
        this.playerClasses.remove(playerClass);
    }

    /**
     * MessageOfTheDay. - optional attribute
     */
    public Optional<String> getMessageOfTheDay() {
        return Optional.ofNullable(messageOfTheDay);
    }

    public void setMessageOfTheDay(String messageOfTheDay) {
        if (messageOfTheDay != null && messageOfTheDay.trim().isBlank()) {
            throw new DataValidationException("message cannot be empty");
        }
        this.messageOfTheDay = messageOfTheDay;
    }

    /**
     * Reputation Awarded.
     */
    public float getReputationAwarded() {
        return reputationAwarded;
    }

    public void addReputationPoints(float reputationAwarded) {
        if (reputationAwarded <= 0) {
            throw new DataValidationException("reputation amount cannot be less than 1");
        }
        this.reputationAwarded += reputationAwarded;
    }

    /**
     * Date of Accession.
     */
    public LocalDate getDateOfAccession() {
        return dateOfAccession;
    }

    public void setDateOfAccession(LocalDate dateOfAccession) {
        if (dateOfAccession == null) {
            throw new DataValidationException("date of accession cannot be null");
        }
        this.dateOfAccession = dateOfAccession;
    }

    /**
     * Player Status. - enum
     */
    public MemberStatus getStatus() {
        return status;
    }

    public void setStatus(MemberStatus status) {
        if (status == null) {
            throw new DataValidationException("status cannot be null");
        }
        this.status = status;
    }

    /**
     * Days of Service. - derived attribute
     */
    public int getDaysOfService() {
        return (int) Duration.between(dateOfAccession.atStartOfDay(), LocalDate.now().atStartOfDay()).toDays();
    }

    /**
     * Player Location. - composite attribute
     */
    public PlayerLocation getPlayerLocation() {
        return playerLocation;
    }

    public void setPlayerLocation(PlayerLocation playerLocation) {
        if (playerLocation == null) {
            throw new DataValidationException("location cannot be null");
        }
        this.playerLocation = playerLocation;
    }

    /**
     * Class Extension.
     */
    public static List<GuildMember> getGuildMemberExtent() {
        return Collections.unmodifiableList(guildMembersExtent);
    }

    public static void setGuildMembersExtent(List<GuildMember> extent) {
        if (extent == null) {
            throw new DataValidationException("extent cannot be null");
        }
        GuildMember.guildMembersExtent = extent;
    }

    /**
     * Extension Methods. - class methods
     */
    public static List<GuildMember> getRankingByLevel() {
        if (guildMembersExtent.size() <= 1) {
            return getGuildMemberExtent();
        }
        return guildMembersExtent
                .stream()
                .sorted(Comparator.comparing(GuildMember::getLevel).reversed())
                .collect(Collectors.toList());
    }

    public static List<GuildMember> getRankingByRepPoints() {
        if (guildMembersExtent.size() <= 1) {
            return getGuildMemberExtent();
        }
        return guildMembersExtent
                .stream()
                .sorted(Comparator.comparing(GuildMember::getReputationAwarded).reversed())
                .collect(Collectors.toList());
    }

    public static List<GuildMember> getOnlineMembers() {
        if (guildMembersExtent.isEmpty()) {
            return getGuildMemberExtent();
        }
        if (guildMembersExtent.size() == 1 && guildMembersExtent.get(0).getStatus() == ONLINE) {
            return getGuildMemberExtent();
        }
        return guildMembersExtent
                .stream()
                .filter(guildMember -> guildMember.getStatus() == ONLINE)
                .collect(Collectors.toList());
    }

    //for testing
    public static void clearExtension() {
        guildMembersExtent.clear();
    }

    /**
     * Validation utilities.
     */
    private void ValidateEnteredString(String toValidate, String errorMessage) {
        if (toValidate == null || toValidate.trim().isBlank()) {
            throw new DataValidationException(errorMessage);
        }
    }

    /**
     * toString.
     */
    @Override
    public String toString() {
        return String.format("%d.   Nick:  %s   %s  Level:%d    rep: %d    ~~%s~~   Location: %s    joined(%s) (%s)"
                , getId(), getNickname(), getPlayerClasses(), getLevel(), (int) getReputationAwarded(),
                getMessageOfTheDay().orElse("no message for today!"),
                getPlayerLocation(), getDateOfAccession(), getStatus());

    }

    /**
     * Equals and HashCode.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GuildMember otherMember = (GuildMember) o;
        return id == otherMember.id &&
                level == otherMember.level &&
                Float.compare(otherMember.reputationAwarded, reputationAwarded) == 0 &&
                nickname.equals(otherMember.nickname) &&
                Objects.equals(messageOfTheDay, otherMember.messageOfTheDay) &&
                playerClasses.equals(otherMember.playerClasses) &&
                playerLocation.equals(otherMember.playerLocation) &&
                dateOfAccession.equals(otherMember.dateOfAccession) &&
                status == otherMember.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, level, reputationAwarded, nickname,
                messageOfTheDay, playerClasses, playerLocation, dateOfAccession, status);
    }

}
