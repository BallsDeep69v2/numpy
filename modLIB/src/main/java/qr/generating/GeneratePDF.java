package qr.generating;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfDiv;
import com.itextpdf.text.pdf.PdfWriter;
import resources.ResourcesPath;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class GeneratePDF {

    public static void generatePDFFileWithAllExistingQRCodes() {
        generatePDFFileWithAllExistingQRCodes(ResourcesPath.RESOURCES_PATH);
    }

    public static void generatePDFFileWithAllExistingQRCodes(Path path) {
        var pdfFile = new File(path + File.separator + "qrCodes.pdf");
        Document document = new Document();

        try {
            PdfWriter.getInstance(document, new FileOutputStream(pdfFile));
            document.open();

            Path qrImagesPath = Path.of(path.toString());

            List<Path> allQRCodes = Files.list(qrImagesPath).filter(path1 -> path1.toString().endsWith(".png")).toList();

            for (Path qrImagePath : allQRCodes) {
                var div = new PdfDiv();
                Image img = Image.getInstance(qrImagePath.toString());

                div.setFloatType(PdfDiv.FloatType.LEFT);
                div.setHeight(img.getHeight());
                div.setWidth(img.getWidth());
                div.addElement(img);

                document.add(div);
            }
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }finally {
            document.close();
        }
    }
}
