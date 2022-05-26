package com.jkolacz.rentalapplication.architecture;

import com.tngtech.archunit.library.dependencies.SlicesRuleDefinition;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("ArchitectureTest")
class DependenciesTest {

    @Test
    void shoudlHaveNoCycles() {
        SlicesRuleDefinition.slices()
                .matching("com.jkolacz.rentalapplication(*)..")
                .should().beFreeOfCycles();
    }
}
