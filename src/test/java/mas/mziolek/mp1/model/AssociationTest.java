package mas.mziolek.mp1.model;

import mas.mziolek.mp1.model.enums.MemberStatus;
import mas.mziolek.mp1.model.exceptions.DataValidationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class AssociationTest {

    GuildMember gMember1, gMember2, gMember3;
    Guild guildOne, guildTwo;

    @BeforeEach
    public void initDta() {

        gMember1 = new GuildMember(1, 16, "Robertx13",
                "Student", new PlayerLocation("Dire Maul", 64, -34),
                LocalDate.of(2021, 7, 2), MemberStatus.ONLINE);

        gMember2 = new GuildMember(2, 26, "xXMaciekXx",
                "Student", new PlayerLocation("Stormwind", 6, -4),
                LocalDate.of(2021, 8, 3), MemberStatus.AFK);

        gMember3 = new GuildMember(3, 36, "Bobromir",
                "Student", new PlayerLocation("Hellfire Peninsula", 6, -4),
                LocalDate.of(2021, 10, 1), MemberStatus.ONLINE);

        guildOne = new Guild("WarMakers", gMember1);
        guildTwo = new Guild("PeaceMakers", gMember3);
    }

    @Test
    public void testConstructors() {
        assertSame(gMember1.getGuild(), guildOne);
        assertTrue(guildOne.getGuildMembers().contains(gMember1));
    }

    @Test
    public void testMissingGuildMemberForGuild() {
        gMember1 = null;
        assertThrows(DataValidationException.class, () -> {
            new Guild("new guild", gMember1);
        });
    }

    @Test
    public void testAddGuildMember() {
        guildOne.addGuildMember(gMember2);
        assertSame(gMember2.getGuild(), guildOne);
        assertTrue(guildOne.getGuildMembers().contains(gMember2));
    }

    @Test
    public void testSetMemberGuild() {
        gMember2.setGuild(guildOne);
        assertSame(gMember2.getGuild(), guildOne);
        assertTrue(guildOne.getGuildMembers().contains(gMember2));
    }

    @Test
    public void testRemoveGuild() {
        guildOne.addGuildMember(gMember2);
        assertNotNull(gMember2.getGuild());
        gMember2.setGuild(null);
        assertNull(gMember2.getGuild());
        assertFalse(guildOne.getGuildMembers().contains(gMember2));
    }

    @Test
    public void testRemoveGuildMember() {
        guildOne.addGuildMember(gMember2);
        guildOne.removeGuildMember(gMember2);
        assertNull(gMember2.getGuild());
        assertFalse(guildOne.getGuildMembers().contains(gMember2));
    }

    @Test //FILMIK
    public void testSwitchGuild() {
        gMember2.setGuild(guildOne);
        gMember2.setGuild(guildTwo);
        assertFalse(guildOne.getGuildMembers().contains(gMember2));
        assertSame(gMember2.getGuild(), guildTwo);
        assertTrue(guildTwo.getGuildMembers().contains(gMember2));
    }

    @Test
    public void testAddGuildMemberWithGuild() {
        gMember2.setGuild(guildOne);
        guildTwo.addGuildMember(gMember2);
        assertFalse(guildOne.getGuildMembers().contains(gMember2));
        assertSame(gMember2.getGuild(), guildTwo);
        assertTrue(guildTwo.getGuildMembers().contains(gMember2));
    }

    @Test
    public void testRemoveLastGuildMember() {
        assertThrows(DataValidationException.class, () -> {
            guildOne.removeGuildMember(gMember1);
        });
    }

    @Test
    public void testRemoveGuildWithOneGuildMember() {
        assertThrows(DataValidationException.class, () -> {
            gMember1.setGuild(null);
        });
    }

    @Test
    public void testInvalidAddGuildMember() {
        assertThrows(UnsupportedOperationException.class, () -> {
            guildOne.getGuildMembers().add(gMember3);
        });
    }

    @Test
    public void testNullMemberGuild() {
        guildOne.addGuildMember(gMember2);
        gMember2.setGuild(null);
        assertTrue(gMember2.getGuild() == null);
        assertFalse(guildOne.getGuildMembers().contains(gMember2));
    }

    @Test
    public void testAddNullMember() {
        assertThrows(DataValidationException.class, () -> {
            guildOne.addGuildMember(null);
        });

    }

}