import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class main {
    public static void main(String args[]) throws IOException {
        ArrayList<String> files = new ArrayList<>();

        files.add("Meta 1");
        files.add("Meta 2");

        readAllFiles(files);
    }

    private static void readAllFiles(ArrayList<String> files) throws IOException {
        String fileExt = ".txt";

        for (String file: files) {
            read(file + fileExt);
        }
    }

    private static void read(String fileName) throws IOException {
        String prepend = "ADAPTED - ";
        String separator = " -------- ";

        Path pathRead = Paths.get("D:\\Programs\\OneDrive\\" + fileName);
        Path pathWrite = Paths.get("D:\\Pedro\\Shared-LAPTOP\\" + prepend + fileName);

        List<String> lines = Files.readAllLines(pathRead);
        ArrayList<String> output = new ArrayList<>();

        int prevTime = 0;

        for (String line : lines) {
            String[] items = line.split("/////");

            for (String item: items) {
                String[] p = item.split(": ");

                int timenow = stringToSec(p[0]);
                int timeUsed = timenow - prevTime;
                prevTime = timenow;

                output.add(timeUsed + separator + flatten(p));
            }
        }

        Files.deleteIfExists(pathWrite);
        Files.createFile(pathWrite);

        for (String item: output) {
            Files.write(pathWrite, (item + "\n").getBytes(), StandardOpenOption.APPEND);
        }
    }

    public static int stringToSec(String time){
        int result = 0;

        String[] i = time.split(":");

        int min = Integer.parseInt(i[0]);
        int sec = Integer.parseInt(i[1]);

        result = min*60 + sec;

        return result;
    }

    public static String flatten(String[] items){
        StringBuilder output = new StringBuilder();


        for (int i = 1; i<items.length ; i++){
            output.append(items[i]);
        }


        return output.toString();
    }
}
