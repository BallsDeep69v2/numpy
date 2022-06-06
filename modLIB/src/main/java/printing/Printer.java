package printing;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;


import javax.print.*;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import java.io.*;
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
        FileInputStream fin = new FileInputStream(System.getProperty("user.dir") + File.separator + "modLIB" + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "qrcodes" + File.separator + "qrCode.png");
//        Doc doc = new SimpleDoc(fin, DocFlavor.INPUT_STREAM.PNG, null);

        var file = new File(System.getProperty("user.dir") + File.separator + "modLIB" + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "qrcodes" + File.separator + "qrCode.pdf");

        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(file));

        document.open();
        Image img = Image.getInstance((System.getProperty("user.dir") + File.separator + "modLIB" + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "qrcodes" + File.separator + "qrCode.png").toString());
        document.add(img);


        //druckt nur 4 in der ersten reihe (new line, floating)

        document.add(new Chunk(img, 0, 0));
        document.add(new Chunk(img, 0, 0));
        document.add(new Chunk(img, 0, 0));
        document.add(new Chunk(img, 0, 0));
        document.add(Chunk.NEWLINE);
        document.add(new Chunk(img, 0, 0));
        document.add(new Chunk(img, 0, 0));
        document.add(new Chunk(img, 0, 0));
        document.add(new Chunk(img, 0, 0));


        document.close();

//        job.print(doc, pras);
        fin.close();
    }
}
