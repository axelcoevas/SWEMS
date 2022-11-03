package com.ipn.swems.code.executive;

import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.commons.io.filefilter.IOFileFilter;
import com.ipn.swems.code.input.FileScanner;
import com.ipn.swems.code.input.Project;
import com.ipn.swems.code.output.JSONOutputter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import org.json.simple.JSONObject;

public class MetricsService {

    public MetricsService() {

    }

    public JSONObject measureMetrics(String path) throws FileNotFoundException {
        System.out.println(path);

        File scanDir = new File(path).getAbsoluteFile();
        FileScanner scanner = new FileScanner(scanDir);

        IOFileFilter fileFilter = true ? new ExcludeTestFilter(scanDir)
                : FileFilterUtils.trueFileFilter();

        scanner.setFilter(fileFilter);

        Project scannerOutput = scanner.scan();

        ProcessorFactory.getProcessor().process(scannerOutput);

        JSONObject output = new JSONOutputter().output(scannerOutput);

        // Escribe la respuesta en un archivo
//        OutputStream os = new FileOutputStream("a.json");
//        jsonWriter = Json.createWriter(os);
//        jsonWriter.writeObject(output);
//        jsonWriter.close();
        return output;
    }
}
