package resources;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ResourcesPath {

    private static final Path resourcePath = Path.of("C:" + File.separator + "Users" + File.separator + System.getenv("USERNAME") + File.separator + "modLIB" + File.separator + "qrCodes");
    private static final Path testResourcePath = Path.of(getUsersProjectRootDirectory() + File.separator + "testResources");

    public static void main(String[] args) {
        System.out.println(Path.of("C:" + File.separator + "Users" + File.separator + System.getenv("USERNAME") + File.separator + "modLIB" + File.separator + "qrCodes"));
    }

    public static Path getRessourcePath() {
        if (!resourcePath.toFile().exists()) resourcePath.toFile().mkdirs();
        return resourcePath;
    }

    public static Path getTestResourcePath() {
        if (!testResourcePath.toFile().exists()) testResourcePath.toFile().mkdirs();
        return testResourcePath;
    }


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
