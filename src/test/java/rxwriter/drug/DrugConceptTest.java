package rxwriter.drug;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DrugConceptTest {

    public final static DrugConcept TEST_CONCEPT = new DrugConcept(new DrugClassification[]{
            DrugClassification.ANTIANXIETY,
            DrugClassification.ANALGESICS_NARCOTIC,
            DrugClassification.NARCOTIC_ANTIHISTAMINE});

    @Test
    public void drugBelongsInConceptIfOneClassMatches() {
        DispensableDrug drug = new DispensableDrug("adrug", new DrugClassification[]{
                DrugClassification.ANALGESIC, DrugClassification.ANTIANXIETY
        }, false);
        assertTrue(TEST_CONCEPT.isDrugInConcept(drug));
    }

    @Test
    public void drugNotInConceptIfNoClassesMatch() {
        DispensableDrug drug = new DispensableDrug("adrug", new DrugClassification[]{
                DrugClassification.ANALGESIC, DrugClassification.ANTIBACTERIAL
        }, false);
        assertFalse(TEST_CONCEPT.isDrugInConcept(drug));
    }

}