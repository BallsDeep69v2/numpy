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

        GenerateQRCode.createQRImage("1234567890123", "01", ResourcesPath.TEST_RESOURCES_PATH);
        GenerateQRCode.createQRImage("2345678901234", "02", ResourcesPath.TEST_RESOURCES_PATH);
        GenerateQRCode.createQRImage("1112225556986", "11", ResourcesPath.TEST_RESOURCES_PATH);

        GeneratePDF.generatePDFFileWithAllExistingQRCodes(ResourcesPath.TEST_RESOURCES_PATH);

        if(PrintPDF.printGeneratedPDFFileByDefaultPrinter(ResourcesPath.TEST_RESOURCES_PATH)){
            Files.walk(ResourcesPath.TEST_RESOURCES_PATH).filter(Files::exists).map(Path::toFile).forEach(File::delete);
        }
        Files.walk(ResourcesPath.TEST_RESOURCES_PATH).filter(path -> path.toString().endsWith(".pdf")).map(Path::toFile).forEach(File::delete);
    }
}
