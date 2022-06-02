package qr;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.imageio.ImageIO;
import javax.print.*;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Hashtable;

public class GenerateQRCode {
    static public void main(String args[]) throws Exception {
        PrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();
        pras.add(new Copies(1));
        PrintService ps = PrintServiceLookup.lookupDefaultPrintService();
        if (ps == null)
            throw new RuntimeException("No printer services available.");
        System.out.println("Printing to " + ps);
        DocPrintJob job = ps.createPrintJob();
        FileInputStream fin = new FileInputStream(System.getProperty("user.dir") + File.separator + "modLIB" + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "qrcodes" + File.separator + "qrCode.png");
        Doc doc = new SimpleDoc(fin, DocFlavor.INPUT_STREAM.PNG, null);
        job.print(doc, pras);
        fin.close();
    }
    public static void createQRImage(String qrCodeText, int size) throws WriterException, IOException {

        File qrFile = new File(System.getProperty("user.dir") + File.separator + "modLIB" + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "qrcodes" + File.separator + "qrCode.png");

        // Create the ByteMatrix for the QR-Code that encodes the given String
        Hashtable<EncodeHintType, ErrorCorrectionLevel> hintMap = new Hashtable<>();
        hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix byteMatrix = qrCodeWriter.encode(qrCodeText, BarcodeFormat.QR_CODE, size, size, hintMap);
        // Make the BufferedImage that are to hold the QRCode
        int matrixWidth = byteMatrix.getWidth();
        BufferedImage image = new BufferedImage(matrixWidth, matrixWidth, BufferedImage.TYPE_INT_RGB);
        image.createGraphics();

        Graphics2D graphics = (Graphics2D) image.getGraphics();
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, matrixWidth, matrixWidth);
        // Paint and save the image using the ByteMatrix
        graphics.setColor(Color.BLACK);

        for (int i = 0; i < matrixWidth; i++) {
            for (int j = 0; j < matrixWidth; j++) {
                if (byteMatrix.get(i, j)) {
                    graphics.fillRect(i, j, 1, 1);
                }
            }
        }
        ImageIO.write(image, "png", qrFile);
    }
}
