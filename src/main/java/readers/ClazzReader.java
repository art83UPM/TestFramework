package readers;

import code.Clazz;

public class ClazzReader {
	
    private Class<?> clazz;
    
	public ClazzReader(Class<?> clazz) {
        this.clazz = clazz;
    }

    public Clazz read(){		
		Clazz result = new Clazz(this.clazz);
		return result;
	}

}
