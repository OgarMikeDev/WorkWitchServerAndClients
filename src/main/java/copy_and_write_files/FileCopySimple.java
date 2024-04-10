package copy_and_write_files;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class FileCopySimple {
    public static void main(String[] args) {
        Path inputPath = Path.of(
                "C:\\Users\\Polina\\Downloads\\WorkWitchServerAndClients\\data\\input.txt");

        Path outputPath = Path.of(
                "C:\\Users\\Polina\\Downloads\\WorkWitchServerAndClients\\data\\output.txt");

        try(FileChannel inputChannel = FileChannel.open(inputPath, StandardOpenOption.READ);
            FileChannel outputChannel = FileChannel.open(
                    outputPath, StandardOpenOption.CREATE, StandardOpenOption.WRITE
                    )) {
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            while (inputChannel.read(byteBuffer) != -1) {
                byteBuffer.flip();
                outputChannel.write(byteBuffer);
                byteBuffer.clear();
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
