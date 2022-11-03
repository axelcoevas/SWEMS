package com.ipn.swems.code.executive;

import com.ipn.swems.code.input.Processor;
import com.ipn.swems.code.metrics.calculators.*;

class ProcessorFactory {

    static Processor getProcessor() {
        Processor processor = new Processor();

        // RTLOC
        processor.registerTypeCalculator(new RawTotalLinesOfCodeCalculator());

        // TLOC
        processor.registerProjectCalculator(new TotalLinesOfCodeCalculator.ProjectCalculator());
        processor.registerPackageCalculator(new TotalLinesOfCodeCalculator.PackageCalculator());
        processor.registerTypeCalculator(new TotalLinesOfCodeCalculator.TypeCalculator());
        processor.registerMethodCalculator(new TotalLinesOfCodeCalculator.MethodCalculator());

        // Complejidad ciclomática
        processor.registerMethodCalculator(new CyclomaticComplexityCalculator());

        // Métodos ponderados por clase
        processor.registerTypeCalculator(new WeightedMethodsCalculator());

        // CF
        processor.registerTypeCalculator(new CouplingFactorCalculator());

        // LCOM
        processor.registerTypeCalculator(new LackOfCohesionMethodsCalculator());

        // SIX
        processor.registerTypeCalculator(new SpecializationIndexCalculator());

        // MIF
        // AIF
        // MHF
        // AHF
        processor.registerTypeCalculator(new MethodAndAttributeInheritanceCalculator());

        // NOCh
        processor.registerTypeCalculator(new ClassInheritanceCalculator());

        return processor;
        // processor.registerTypeCalculator(new NumberOfFieldsCalculator());
        // processor.registerMethodCalculator(new NumberOfParametersCalculator());
        // processor.registerPackageCalculator(new NumberOfClassesCalculator());
        // processor.registerPackageCalculator(new RobertMartinCouplingCalculator());
        // ***
        // processor.registerMethodCalculator(new NestedBlockDepthCalculator());
        // processor.registerMethodCalculator(new FanCalculator());
        // processor.registerTypeCalculator(new LinkCalculator());
        // processor.registerMethodCalculator(new McclureCalculator()); ***
        // processor.registerPackageCalculator(new PackageAggregatorCalculator());
        // processor.registerTypeCalculator(new TypeAggregatorCalculator()); ***
    }
}
