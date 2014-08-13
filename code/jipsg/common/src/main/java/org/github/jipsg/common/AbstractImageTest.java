package org.github.jipsg.common;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Abstract test class for image testing.
 */
public abstract class AbstractImageTest {

    private String moduleName;
    private File imageDirectory;

    public abstract BufferedImage createBufferedImage(File file) throws Exception;
    public abstract void writeBufferedImage(final BufferedImage bufferedImage, final String formatName, final File file) throws Exception;

    public void setup() {
        this.imageDirectory = new File("../../images");
    }

    private File getImageDirectory() {
        return imageDirectory;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    /**
     * Get the given file.
     * @param folderName Name of the folder under "images"
     * @param fileName File name
     * @return the file
     */
    public File getImageFile(String folderName, String fileName) throws IOException {
        File folderFile = new File(getImageDirectory(), folderName);
        File result = new File(folderFile, fileName);
        if (!result.exists() || !result.canRead()) {
            throw new IOException("Can't open/read the following file : " + result.getAbsolutePath());
        }
        return result;
    }

    /**
     * Some dumb sanity check that we have a valid buffered image.
     */
    public void assertValidBufferedImage(BufferedImage bufferedImage) {
        assertNotNull("bufferedImage is null", bufferedImage);
        assertTrue(bufferedImage.getHeight() > 0);
        assertTrue(bufferedImage.getWidth() > 0);
    }

    public File createOutputFileName(File file, String format) {
        return createOutputFileName(file.getName(), format);
    }

    public File createOutputFileName(String name, String format) {

        File outputDir = new File(new File(new File("./target"), "out"), this.moduleName);

        if(!outputDir.exists()) {
            outputDir.mkdirs();
        }

        return new File(outputDir, name + "." + format);
    }
}