package qr;

import com.google.zxing.WriterException;
import qr.generating.GeneratePDF;
import qr.generating.GenerateQRCode;
import resources.ResourcesPath;

import java.io.IOException;

public class GenerateQRCodes {
    public static void main(String[] args) throws IOException, WriterException {
        GenerateQRCode.createQRImage("1234567890123", "01", ResourcesPath.getRessourcePath());
        GenerateQRCode.createQRImage("2345678901234", "02", ResourcesPath.getRessourcePath());
        GenerateQRCode.createQRImage("1112225556986", "11", ResourcesPath.getRessourcePath());
        GenerateQRCode.createQRImage("1234567890123", "11", ResourcesPath.getRessourcePath());
        GenerateQRCode.createQRImage("2345678901234", "22", ResourcesPath.getRessourcePath());
        GenerateQRCode.createQRImage("1112225556986", "33", ResourcesPath.getRessourcePath());

        GeneratePDF.generatePDFFileWithAllExistingQRCodes(ResourcesPath.getRessourcePath());
    }
}
