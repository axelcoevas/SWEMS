package com.ipn.swems.code.executive;

import com.google.common.collect.ImmutableSet;
import java.io.File;
import java.util.Set;
import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.commons.io.filefilter.NotFileFilter;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author axel_
 */
public class ExcludeTestFilter implements IOFileFilter {

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

    public ExcludeTestFilter(File baseDir) {
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
