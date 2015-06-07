package code;

public class TestFrameworkClassLoaderTest {

	public static void main(String[] args) {
		TestFrameworkClassLoader.setClassLoaderByPath("C:\\Users\\CarlosDavid\\git\\exampleProject\\target\\classes\\");
		TestFrameworkClassLoader classLoader = TestFrameworkClassLoader.getClassLoader();
		try {
			classLoader.loadClass("spike.Example");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
