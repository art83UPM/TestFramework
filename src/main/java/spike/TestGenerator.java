package spike;

import java.io.File;
import java.io.IOException;

public class TestGenerator {

    private File file;

    public TestGenerator(String fileName) {
        this.file = new File(fileName);
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeHeader() {
        
    }

    public static void main(String[] args) {
        TestGenerator testGenerator = new TestGenerator("C:\\Users\\nyuron\\Desktop\\newfile.java");
    }
}
