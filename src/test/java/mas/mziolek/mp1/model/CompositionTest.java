package mas.mziolek.mp1.model;

import static org.junit.jupiter.api.Assertions.*;

import mas.mziolek.mp1.model.enums.MemberStatus;
import mas.mziolek.mp1.model.exceptions.DataValidationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.time.LocalDate;

class CompositionTest {

    GuildMember gMember1, gMember2;
    GuildAchievement ga1, ga2;
    Guild guildOne, guildTwo;

    @BeforeEach
    public void init() {

        gMember1 = new GuildMember(1, 16, "Robertx13",
                "Student", new PlayerLocation("Dire Maul", 64, -34),
                LocalDate.of(2021, 7, 2), MemberStatus.ONLINE);

        gMember2 = new GuildMember(2, 26, "xXMaciekXx",
                "Student", new PlayerLocation("Stormwind", 6, -4),
                LocalDate.of(2021, 8, 3), MemberStatus.AFK);

        guildOne = new Guild("WarMakers", gMember1);
        guildTwo = new Guild("PeaceMakers", gMember2);

        ga1 = new GuildAchievement("Guild Founder!!", "Create the brand new Guild.", guildOne);
        ga2 = new GuildAchievement("Slayer!", "Defeat Your first enemy as a guild member.", guildOne);
    }

    @Test
    public void testCreate() {
        assertSame(ga1.getOwner(), guildOne);
        assertTrue(guildOne.getGuildAchievements().contains(ga1));
    }

    @Test
    public void testRemoveAchievement() {
        guildOne.removeGuildAchievement(ga1);
        assertSame(ga1.getOwner(), null);
        assertFalse(guildOne.getGuildAchievements().contains(ga1));
    }

    @Test
    public void testDeleteAchievementFromGuild() {
        ga1.delete();
        assertSame(ga1.getOwner(), null);
        assertFalse(guildOne.getGuildAchievements().contains(ga1));
    }

    @Test
    public void testDeleteGuild() {
        guildOne.delete();
        assertSame(ga1.getOwner(), null);
        assertFalse(guildOne.getGuildAchievements().contains(ga1));
    }

    @Test
    public void testChangeOwner() {
        assertThrows(DataValidationException.class, () -> {
            guildTwo.addGuildAchievement(ga1);
        });
    }

    @Test
    public void testInvalidRemoveAchievement() {
        assertThrows(UnsupportedOperationException.class, () -> {
            guildOne.getGuildAchievements().remove(ga1);
        });
    }

    @Test
    public void testInvalidCreate() {
        assertThrows(DataValidationException.class, () -> {
            new GuildAchievement("test", "test", null);
        });
    }

    @Test
    public void testAddNullAchievement() {
        assertThrows(DataValidationException.class, ()->{
            guildOne.addGuildAchievement(null);
        });
    }



}