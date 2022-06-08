package resources;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ResourcesPath {
    public static final Path RESOURCES_PATH = Path.of(getUsersProjectRootDirectory() + File.separator + "modLIB" + File.separator + "qrResources");

    public static final Path TEST_RESOURCES_PATH = Path.of(getUsersProjectRootDirectory()+ File.separator + "modLIB" + File.separator + "testResources");

    public static Path getUsersProjectRootDirectory() {
        String envRootDir = System.getProperty("user.dir");
        Path rootDir = Paths.get(".").normalize().toAbsolutePath();
        if (rootDir.startsWith(envRootDir)) {
            return rootDir;
        } else {
            throw new RuntimeException("Root dir not found in user directory.");
        }
    }
}
