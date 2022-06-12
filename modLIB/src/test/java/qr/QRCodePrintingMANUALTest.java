package qr;

import com.google.zxing.WriterException;
import qr.generating.GeneratePDF;
import qr.generating.GenerateQRCode;
import qr.printing.PrintPDF;
import resources.ResourcesPath;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class QRCodePrintingMANUALTest {
    public static void main(String[] args) throws IOException, WriterException {

        GenerateQRCode.createQRImage("1234567890123", "01", ResourcesPath.getTestResourcePath());
        GenerateQRCode.createQRImage("2345678901234", "02", ResourcesPath.getTestResourcePath());
        GenerateQRCode.createQRImage("1112225556986", "11", ResourcesPath.getTestResourcePath());

        GeneratePDF.generatePDFFileWithAllExistingQRCodes(ResourcesPath.getTestResourcePath());

        if(PrintPDF.printGeneratedPDFFileByDefaultPrinter(ResourcesPath.getTestResourcePath())){
            Files.walk(ResourcesPath.getTestResourcePath()).filter(Files::exists).map(Path::toFile).forEach(File::delete);
        }
        Files.walk(ResourcesPath.getTestResourcePath()).filter(path -> path.toString().endsWith(".pdf")).map(Path::toFile).forEach(File::delete);
    }
}
