package spike;

public class TestGenerator {

	public TestGenerator(String clazzName) {
        try {
            ClazzReader clazzReader = new ClazzReader(Class.forName(clazzName));
            Clazz clazz = clazzReader.read();
            TestWriter testWriter = new TestWriter("C:\\Users\\nyuron\\Desktop");
            HeaderDataWriter headerDataWriter = new HeaderDataWriter("C:\\Users\\nyuron\\Desktop");
            clazz.accept(testWriter);
            clazz.accept(headerDataWriter);
            testWriter.close();
            headerDataWriter.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(0);
        }		
	}
	
	public static void main(String[] args) {
	        new TestGenerator(args[0]);
	}
}
