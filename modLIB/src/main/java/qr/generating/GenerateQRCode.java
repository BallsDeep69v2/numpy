package qr.generating;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import errorhandling.InvalidDataException;
import resources.ResourcesPath;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;

public class GenerateQRCode {

    public static void createQRImage(String isbn, String exemplarNumber) throws WriterException, IOException, InvalidDataException {
        createQRImage(isbn, exemplarNumber, ResourcesPath.getRessourcePath());
    }

    public static void createQRImage(String isbn, String exemplarNumber, Path resourcesPath) throws WriterException, IOException, InvalidDataException {
        if (isbn.length() != 13 || exemplarNumber.length() != 2) {
            throw new InvalidDataException("isbn must have an size of 13 and exemplar-number must have an size of 2");
        }

        File qrFile = new File(resourcesPath + File.separator + "qrCode" + isbn + "-" + exemplarNumber + ".png");

        // Create the ByteMatrix for the QR-Code that encodes the given String
        HashMap<EncodeHintType, ErrorCorrectionLevel> hintMap = new HashMap<>();
        hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix byteMatrix = qrCodeWriter.encode(isbn + "-" + exemplarNumber, BarcodeFormat.QR_CODE, 128, 128, hintMap);

        // Make the BufferedImage that are to hold the QRCode
        int matrixWidth = byteMatrix.getWidth();
        int matrixHeight = byteMatrix.getHeight();
        BufferedImage image = new BufferedImage(matrixWidth, matrixHeight, BufferedImage.TYPE_INT_RGB);
        image.createGraphics();

        Graphics2D graphics = (Graphics2D) image.getGraphics();
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, matrixWidth, matrixHeight);
        graphics.setColor(Color.BLACK);

        //draw ISBN and exemplar-number on the QR Code
        graphics.drawString(isbn, 18, 18);
        graphics.drawString(exemplarNumber, 55, 120);

        // Paint and save the image using the ByteMatrix
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
