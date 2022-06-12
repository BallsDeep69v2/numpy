package qr;

import com.google.zxing.WriterException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.testfx.assertions.api.Assertions;
import qr.generating.GenerateQRCode;
import resources.ResourcesPath;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class QRCodeGeneratingTest {


    @AfterEach
    void clearTestResources() throws IOException {
        Files.walk(ResourcesPath.getRessourcePath()).filter(Files::isRegularFile).map(Path::toFile).forEach(File::delete);
    }

    @Nested
    class Generating {
        @Test
        void generateQRCode() throws IOException, WriterException {
            GenerateQRCode.createQRImage("1234567890123", "01", ResourcesPath.getRessourcePath());

            Assertions.assertThat(Files.list(ResourcesPath.getRessourcePath())).containsExactly(Path.of(ResourcesPath.getRessourcePath() + File.separator + "qrCode1234567890123-01.png"));
        }

        @Test
        void failsWhenISBNOrExemplarNumberIsInvalid() throws IOException, WriterException {
            Assertions.assertThatThrownBy(() -> GenerateQRCode.createQRImage("000111", "1", ResourcesPath.getRessourcePath()));
        }
    }

}
