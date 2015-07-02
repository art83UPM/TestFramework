package code;

public class TestFrameworkClassLoaderTest {

	public static void main(String[] args) {
		String[] path = {"C:\\Users\\CarlosDavid\\git\\exampleProject\\target\\classes\\"};
		TestFrameworkClassLoader.setClassLoaderByPath(path);
		TestFrameworkClassLoader classLoader = TestFrameworkClassLoader.getClassLoader();
		try {
			classLoader.loadClass("spike.Example");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
