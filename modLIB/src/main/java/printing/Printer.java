package printing;


import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfDiv;
import com.itextpdf.text.pdf.PdfWriter;

import javax.print.DocPrintJob;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;


public class Printer {
    static public void main(String args[]) throws Exception {
        PrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();
        pras.add(new Copies(1));
        PrintService ps = PrintServiceLookup.lookupDefaultPrintService();
        if (ps == null) throw new RuntimeException("No printer services available.");
        System.out.println("Printing to " + ps);
        DocPrintJob job = ps.createPrintJob();
        FileInputStream fin = new FileInputStream(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "qrcodes" + File.separator + "qrCode.png");
//        Doc doc = new SimpleDoc(fin, DocFlavor.INPUT_STREAM.PNG, null);

        var file = new File(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "qrcodes" + File.separator + "qrCode.pdf");

        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(file));

        document.open();
        Path path = Path.of(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "qrcodes");

        //druckt nur 4 in der ersten reihe (new line, floating)


        var allQRCodes = Files.list(path).filter(path1 -> path1.toString().endsWith(".png")).toList();
        System.out.println(allQRCodes.size());
        for (Path qrPath : allQRCodes) {
            var div = new PdfDiv();

            Image img = Image.getInstance(qrPath.toString());

            div.setFloatType(PdfDiv.FloatType.LEFT);
            div.setHeight(img.getHeight());
            div.setWidth(img.getWidth());
            div.addElement(img);

            document.add(div);
        }


        document.close();


//        job.print(doc, pras);
        fin.close();
    }
}
