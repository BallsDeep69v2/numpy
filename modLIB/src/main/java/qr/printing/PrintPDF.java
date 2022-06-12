package qr.printing;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.printing.PDFPageable;
import resources.ResourcesPath;

import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;


public class PrintPDF {

    public static boolean printGeneratedPDFFileByDefaultPrinter() {
        return printGeneratedPDFFileByDefaultPrinter(ResourcesPath.getRessourcePath());
    }

    public static boolean printGeneratedPDFFileByDefaultPrinter(Path path) {
        try {
            PrintService ps = PrintServiceLookup.lookupDefaultPrintService();
            PDDocument documentToPrint = PDDocument.load(new File(path + File.separator + "qrCodes.pdf"));
            PrinterJob job = PrinterJob.getPrinterJob();
            job.setPageable(new PDFPageable(documentToPrint));
            job.setPrintService(ps);
            job.setCopies(1);
            job.print();
            documentToPrint.close();
        } catch (PrinterException | IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
