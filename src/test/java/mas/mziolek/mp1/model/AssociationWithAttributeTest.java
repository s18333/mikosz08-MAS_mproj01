package mas.mziolek.mp1.model;

import mas.mziolek.mp1.model.enums.MemberStatus;
import mas.mziolek.mp1.model.exceptions.DataValidationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class AssociationWithAttributeTest {

    GuildMember gMember1, gMember2;
    Guild guildOne, guildTwo;
    ApplicationForm applicationForm;

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

        applicationForm = new ApplicationForm("please add me", guildOne, gMember1);
    }

    @Test
    public void testCreate() {
        assertTrue(gMember1.getApplicationForms().contains(applicationForm));
        assertSame(applicationForm.getGuildMember(), gMember1);
        assertTrue(guildOne.getApplicationForms().contains(applicationForm));
        assertSame(applicationForm.getGuild(), gMember1.getGuild());
    }

    @Test
    public void removeApplication() {
        applicationForm.delete();
        assertFalse(gMember1.getApplicationForms().contains(applicationForm));
        assertSame(applicationForm.getGuildMember(), null);
        assertFalse(guildOne.getApplicationForms().contains(applicationForm));
        assertSame(applicationForm.getGuild(), null);
    }

    @Test
    public void removeGuildMemberApplication() {
        gMember1.removeApplicationForm(applicationForm);
        assertFalse(gMember1.getApplicationForms().contains(applicationForm));
        assertSame(applicationForm.getGuildMember(), null);
        assertFalse(guildOne.getApplicationForms().contains(applicationForm));
        assertSame(applicationForm.getGuild(), null);
    }

    @Test
    public void removeGuildApplication() {
        guildOne.removeApplicationForm(applicationForm);
        assertFalse(gMember1.getApplicationForms().contains(applicationForm));
        assertSame(applicationForm.getGuildMember(), null);
        assertFalse(guildOne.getApplicationForms().contains(applicationForm));
        assertSame(applicationForm.getGuild(), null);
    }

    @Test
    public void testDuplicate() {
        assertThrows(DataValidationException.class, () ->
                new ApplicationForm(applicationForm.getMessageContent(),
                        applicationForm.getGuild(), applicationForm.getGuildMember()));
    }

    @Test
    public void testAddUnrelatedToGuildMember() {
        assertThrows(DataValidationException.class, () -> {
            gMember2.addApplication(applicationForm);
        });

    }

    @Test
    public void testAddUnrelatedToGuild() {
        assertThrows(DataValidationException.class, () -> {
            guildTwo.addApplicationForm(applicationForm);
        });

    }

}