package mas.mziolek.mp1.model;

import static org.junit.jupiter.api.Assertions.*;

import mas.mziolek.mp1.model.exceptions.DataValidationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

class QualifiedAssociationTest {

    Faction f1, f2;
    Region r1;

    @BeforeEach
    public void init() {

        f1 = new Faction("TestFaction", new Leader(1, "Leader"), 2340543);
        f2 = new Faction("TestFaction2", new Leader(2, "Leader2"), 68756);
        r1 = new Region("Taanan Jungle");

    }

    @Test
    public void testSetFaction() {
        f1.setRegion(r1);
        assertSame(f1.getRegion(), r1);
        assertSame(r1.getFactions().get(f1.getRegionId()), f1);
    }

    @Test
    public void testAddRegion() {
        r1.addFaction(f1);
        assertSame(f1.getRegion(), r1);
        assertSame(r1.getFactions().get(f1.getRegionId()), f1);
    }

    @Test
    public void testRemoveRegion() {
        f1.setRegion(r1);
        f1.setRegion(null);
        assertSame(f1.getRegion(), null);
        assertSame(r1.getFactions().get(f1.getRegionId()), null);
    }

    @Test
    public void testRemoveFaction() {
        f1.setRegion(r1);
        r1.removeFaction(f1);
        assertSame(f1.getRegion(), null);
        assertSame(r1.getFactions().get(f1.getRegionId()), null);
    }

    @Test
    public void testFindFactionById() {
        f1.setRegion(r1);
        assertSame(r1.findFactionById(f1.getRegionId()), f1);
    }

    @Test
    public void testChangeQualifier() {
        f1.setRegion(r1);
        long oldId = f1.getRegionId();
        long newId = oldId + 1;
        f1.setRegionId(newId);
        assertSame(r1.getFactions().get(oldId), null);
        assertSame(r1.getFactions().get(newId), f1);
    }

    @Test
    public void testInvalidAddFaction() {
        assertThrows(UnsupportedOperationException.class, () -> r1.getFactions().put(f1.getRegionId(), f1));
    }

    @Test
    public void testSetNullFactionName() {
        assertThrows(DataValidationException.class, () -> {
            f1.setFactionName(null);
        });
    }

    @Test
    public void testSetRegion() {
        f1.setRegion(r1);
        assertSame(f1.getRegion(), r1);
        assertSame(f1.getRegion(), r1);
    }

    @Test
    public void testSetRegionIdBelowZero() {
        assertThrows(DataValidationException.class, () -> {
            f1.setRegionId(0);
        });
    }

    @Test
    public void testAddNullRegion() {
        assertThrows(DataValidationException.class, () -> {
            r1.addFaction(null);
        });
    }

    @Test
    public void testRemoveNullRegion() {
        assertThrows(DataValidationException.class, () -> {
            r1.removeFaction(null);
        });
    }

    @Test
    public void testGetFactionListByLeader() {
        r1.addFaction(f1);
        r1.addFaction(f2);
        assertTrue(r1.getFactionListByLeader("Leader").contains(f1));
    }

}