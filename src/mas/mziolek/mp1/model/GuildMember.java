package mas.mziolek.mp1.model;

import mas.mziolek.mp1.model.enums.MemberStatus;
import mas.mziolek.mp1.model.exceptions.DataValidationException;

import java.time.Duration;
import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class GuildMember {

    private long id;                                         //player's member ID
    private int level;                                       //player's character level.
    private float reputationAwarded;                         //player points donated to the guild

    private String nickname;                            //player's nickname.
    private String messageOfTheDay;                     //player's day message

    private Set<String> playerClasses = new HashSet<>();     //player's character classes
    private PlayerLocation playerLocation;                   //player's current location on map
    private LocalDate dateOfAccession;                       //date the player joined the guild
    private MemberStatus status;                             //player online status

    private final static int STARTING_REP_POINTS = 0;        //default value of reputation points for every guild member

    /**
     * Class constructor.
     */
    public GuildMember(long id, int level, String nickname, String playerClass,
                       PlayerLocation playerLocation, LocalDate dateOfAccession, MemberStatus status) {
        this.id = id;
        setLevel(level);
        setNickname(nickname);
        this.reputationAwarded = STARTING_REP_POINTS;         //player always starts with 0 rep. awarded.
        addPlayerClass(playerClass);
        setPlayerLocation(playerLocation);
        setDateOfAccession(dateOfAccession);
        setStatus(status);
    }

    /**
     * Class constructor with optional attributes.
     */
    public GuildMember(long id, int level, String nickname, String messageOfTheDay, String playerClass,
                       PlayerLocation playerLocation, LocalDate dateOfAccession, MemberStatus status) {
        this.id = id;
        setLevel(level);
        setNickname(nickname);
        setMessageOfTheDay(messageOfTheDay);
        this.reputationAwarded = STARTING_REP_POINTS;        //player always starts with 0 rep. awarded.
        addPlayerClass(playerClass);
        setPlayerLocation(playerLocation);
        setDateOfAccession(dateOfAccession);
        setStatus(status);
    }

    /**
     * ID.
     */
    public long getId() {
        return id;
    }

    public void setId(long id) {
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
            throw new DataValidationException("level cannot be lower than 1");
        }
        this.level = level;
    }

    /**
     * Player Classes.
     */
    public Set<String> getPlayerClasses() {
        return Collections.unmodifiableSet(playerClasses);
    }

    public void addPlayerClass(String playerClass) {
        ValidateEnteredString(playerClass, "class cannot be null or empty");
        this.playerClasses.add(playerClass);
    }

    public void removePlayerClass(String playerClass) {

        ValidateEnteredString(playerClass, "class cannot be null or empty");
        if (playerClasses.size() <= 1) {
            throw new DataValidationException("cannot remove last player's class");
        }
        this.playerClasses.remove(playerClass);
    }

    /**
     * MessageOfTheDay.
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

    public void setReputationAwarded(float reputationAwarded) {
        if (reputationAwarded <= 0) {
            throw new DataValidationException("reputation amount cannot be less than 1");
        }
        this.reputationAwarded += reputationAwarded;
    }

    /**
     * Date of Accesion.
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
     * Player Status.
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
     * Days of Service.
     */
    public int getDaysOfService() {
        return Math.abs((int) Duration.between(LocalDate.now().atStartOfDay(), dateOfAccession.atStartOfDay()).toDays());
    }

    /**
     * Player Location.
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
     * Validation utilities.
     */
    //Checks if given string is null or empty/blank. Can throw DataValidationException
    private void ValidateEnteredString(String toValidate, String errorMessage) {
        if (toValidate == null || toValidate.trim().isBlank()) {
            throw new DataValidationException(errorMessage);
        }
    }

    @Override
    public String toString() {
        return "GuildMember{" +
                "id=" + id +
                ", level=" + level +
                ", reputationAwarded=" + reputationAwarded +
                ", nickname='" + nickname + '\'' +
                ", messageOfTheDay='" + messageOfTheDay + '\'' +
                ", playerClasses=" + playerClasses +
                ", playerLocation=" + playerLocation +
                ", dateOfAccession=" + dateOfAccession +
                ", status=" + status +
                '}';
    }
}
