package rxwriter.drug;

import org.junit.jupiter.api.*;
import rxwriter.drug.database.DrugRecord;
import rxwriter.drug.database.DrugSource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("DrugService should")
class DrugServiceTest implements DrugSource {

    private DrugService drugService;

    @BeforeEach
    public void setup() {
        drugService = new DrugService(this);
    }

    @Test
    @Tag("database")
    @DisplayName("return drugs from the database sorted by drug name")
    public void drugsAreReturnedSorted() {
        List<DispensableDrug> foundDrugs = drugService.findDrugsStartingWith("as");

        assertNotNull(foundDrugs);
        assertEquals(2, foundDrugs.size(), "two drugs starting with 'as' should be returned from test data");
        assertEquals("asmanex", foundDrugs.get(0).drugName());
        assertEquals("aspirin", foundDrugs.get(1).drugName());
    }

    @Nested
    @DisplayName("throw an illegal argument exception")
    class ThrowExceptionTests {

        @Test
        @DisplayName("when passed a single character string for startingWith")
        public void throwsExceptionOnSingleCharacterStartsWith() {
            Exception thrown = assertThrows(IllegalArgumentException.class, () -> drugService.findDrugsStartingWith("a"));
            assertEquals("Starts with string must be non-null, non-blank, " +
                    "and at least two characters.  String provided: [a]", thrown.getMessage());
        }

        @Disabled("Waiting for feature A to be implemented")
        @Test
        @DisplayName("when passed an empty string for startingWith")
        public void throwsExceptionOnEmptyStartsWith() {
            Exception thrown = assertThrows(IllegalArgumentException.class, () -> drugService.findDrugsStartingWith(""));
            assertEquals("Starts with string must be non-null, non-blank, " +
                    "and at least two characters.  String provided: []", thrown.getMessage());
        }

        @Test
        @DisplayName("when passed an blank string for startingWith")
        public void throwsExceptionOnBlankStartsWith() {
            Exception thrown = assertThrows(IllegalArgumentException.class, () -> drugService.findDrugsStartingWith(" "));
            assertEquals("Starts with string must be non-null, non-blank, " +
                    "and at least two characters.  String provided: [ ]", thrown.getMessage());
        }
    }

    @Test
    @Tag("database")
    public void setsDrugPropertiesCorrectly() {
        List<DispensableDrug> foundDrugs = drugService.findDrugsStartingWith("aspirin");
        DrugClassification[] expectedClassification = new DrugClassification[]{
                DrugClassification.ANALGESIC, DrugClassification.PLATELET_AGGREGATION_INHIBITORS
        };

        DispensableDrug drug = foundDrugs.get(0);

        assertAll("DispensableDrug propeties",
                () -> assertEquals("aspirin", drug.drugName()),
                () -> assertFalse(drug.isControlled()),
                () -> assertEquals(2, drug.drugClassifications().length),
                () -> assertArrayEquals(expectedClassification, drug.drugClassifications())
        );
    }

    @Override
    public List<DrugRecord> findDrugsStartingWith(String startingString) {
        List<DrugRecord> records = new ArrayList<>();
        if (startingString.equals("as")) {
            records.add(new DrugRecord("asmanex", new int[]{301}, 0));
            records.add(new DrugRecord("aspirin", new int[]{3645, 3530}, 0));
        } else if (startingString.equals("aspirin")) {
            records.add(new DrugRecord("aspirin", new int[]{3645, 3530}, 0));
        }
        return records;
    }
}