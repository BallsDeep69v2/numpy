package printing;

import javax.print.*;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import java.io.File;
import java.io.FileInputStream;

public class Printer {
    static public void main(String args[]) throws Exception {
        PrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();
        pras.add(new Copies(1));
        PrintService ps = PrintServiceLookup.lookupDefaultPrintService();
        if (ps == null) throw new RuntimeException("No printer services available.");
        System.out.println("Printing to " + ps);
        DocPrintJob job = ps.createPrintJob();
        FileInputStream fin = new FileInputStream(System.getProperty("user.dir") + File.separator + "modLIB" + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "qrcodes" + File.separator + "qrCode.png");
        Doc doc = new SimpleDoc(fin, DocFlavor.INPUT_STREAM.PNG, null);
        job.print(doc, pras);
        fin.close();
    }
}
