package spike;

public class ClazzReader {
	
    private Class<?> clazz;
    
	public ClazzReader(Class<?> clazz) {
        this.clazz = clazz;
    }

    Clazz read(){		
		Clazz result = new Clazz(this.clazz);
		return result;
	}

}
