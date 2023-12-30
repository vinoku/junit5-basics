package rxwriter.drug;

import java.util.Arrays;

public class DrugConcept {

    public final static DrugConcept OPIATES = new DrugConcept(new DrugClassification[]{
            DrugClassification.ANTIANXIETY,
            DrugClassification.ANALGESICS_NARCOTIC,
            DrugClassification.NARCOTIC_ANTIHISTAMINE});

    private final DrugClassification[] drugClassesInConcept;

    public DrugConcept(DrugClassification[] drugClassesInConcept) {
        this.drugClassesInConcept = drugClassesInConcept;
    }

    public boolean isDrugInConcept(DispensableDrug drug) {
        return Arrays.stream(drugClassesInConcept).toList().stream().anyMatch(
                drugClass -> Arrays.stream(drug.drugClassifications()).toList().contains(drugClass)
        );
    }
}


