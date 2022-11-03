package com.ipn.swems.code.executive;

import com.google.common.collect.ImmutableSet;
import org.apache.commons.cli.*;
import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.commons.io.filefilter.NotFileFilter;
import org.apache.commons.lang3.StringUtils;
import com.ipn.swems.code.input.FileScanner;
import com.ipn.swems.code.input.Project;
import com.ipn.swems.code.output.JSONOutputter;
import com.ipn.swems.code.output.XMLOutputter;
import org.w3c.dom.Document;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonWriter;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Set;
import javax.xml.transform.stream.StreamResult;

public class CommandLineExecutive {

    public static void main(String[] args) throws IOException, ParseException {

        Options options = new Options();

        {

            // TODO: still need a way to do excludes, regex or something. joda has an
            // example package I want to ignore
            Option help = new Option("h", "help", false, "print this message");
            Option version = new Option("v", "version", false, "print the version information and exit");
            Option excludetests = new Option("xt", "excludetests", false, "exclude test files from scanning");
            Option output = new Option("o", "output", true, "where to save output (default is print to STDOUT");

            options.addOption(help);
            options.addOption(version);
            options.addOption(excludetests);
            options.addOption(output);
        }

        System.out.println(args[0]);

        CommandLineParser parser = new DefaultParser();
        CommandLine line = parser.parse(options, args);

        if (line.getArgs().length != 1) {
            System.out.println("No source directory provided.");
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp("jasome <java file or directory>", options);
            System.exit(0);
        } else {
            String fileParam = line.getArgs()[0];
            File scanDir = new File(fileParam).getAbsoluteFile();
            FileScanner scanner = new FileScanner(scanDir);

            IOFileFilter fileFilter = line.hasOption("excludetests") ? new ExcludeTestsFilter(scanDir)
                    : FileFilterUtils.trueFileFilter();

            scanner.setFilter(fileFilter);

            Project scannerOutput = scanner.scan();

            ProcessorFactory.getProcessor().process(scannerOutput);

            // try {
            // Document outputDocument = new XMLOutputter().output(scannerOutput);

            JsonObject output = new JSONOutputter().output(scannerOutput);

            // write to file
            OutputStream os = new FileOutputStream("a.json");
            JsonWriter jsonWriter = Json.createWriter(os);
            /**
             * We can get JsonWriter from JsonWriterFactory also
             * JsonWriterFactory factory = Json.createWriterFactory(null);
             * jsonWriter = factory.createWriter(os);
             */
            jsonWriter.writeObject(output);
            jsonWriter.close();

            // Transformer transformer = TransformerFactory.newInstance().newTransformer();
            // transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            // transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount",
            // "4");

            // DOMSource source = new DOMSource(outputDocument);

            // StreamResult result;
            // result = new StreamResult(System.out);
            // transformer.transform(source, result);
            // } catch (TransformerConfigurationException e) {
            // e.printStackTrace();
            // } catch (TransformerException e) {
            // e.printStackTrace();
            // }

        }
    }

    private static class ExcludeTestsFilter implements IOFileFilter {

        private static Set<String> testSuffixes = ImmutableSet.of(
                "Test",
                "Spec",
                "Tests",
                "Specs",
                "Suite",
                "TestCase");

        private static Set<String> testDirectories = ImmutableSet.of(
                "test",
                "tests",
                "examples",
                "example",
                "samples",
                "sample");

        private IOFileFilter underlyingFilter;

        public ExcludeTestsFilter(File baseDir) {
            String baseDirPath = baseDir.getPath();
            IOFileFilter doesNotHaveTestSuffix = new NotFileFilter(FileFilterUtils.asFileFilter(path -> {
                for (String testSuffix : testSuffixes) {
                    if (path.getName().endsWith(testSuffix + ".java")) {
                        return true;
                    }
                }
                return false;
            }));

            IOFileFilter isNotInTestSubDirectory = new NotFileFilter(FileFilterUtils.asFileFilter(path -> {
                String pathName = path.getPath();
                String relativePath = StringUtils.removeStart(pathName, baseDirPath);
                for (String testDirectory : testDirectories) {
                    if (relativePath.contains(File.separator + testDirectory + File.separator)) {
                        return true;
                    }
                }
                return false;
            }));

            this.underlyingFilter = FileFilterUtils.and(doesNotHaveTestSuffix, isNotInTestSubDirectory);
        }

        @Override
        public boolean accept(File file) {
            return underlyingFilter.accept(file);
        }

        @Override
        public boolean accept(File dir, String name) {
            return underlyingFilter.accept(dir, name);
        }
    }
}
