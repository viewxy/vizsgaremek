import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;

public class Util {

    private String fileName = null;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String read() {
        StringBuilder writtenText = new StringBuilder();
        try {
            File myObj = new File(fileName);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String line = myReader.nextLine();
                writtenText.append(line).append(System.lineSeparator());
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
            e.printStackTrace();
        }
        return writtenText.toString().trim();
    }

    public void write(String text, String splitAtSymbol) {

        Path path = Paths.get(fileName);
        String message = text + splitAtSymbol;

        try {
            Files.write(path, message.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (Exception e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void imageSave(String imageURL) {
        BufferedImage bImage;
        try {
            URL url = new URL(imageURL);
            bImage = ImageIO.read(url);

            ImageIO.write(bImage, "png", new File(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void fileDeleter() {
        try {
            File deleteFile = new File(getFileName());
            deleteFile.deleteOnExit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
