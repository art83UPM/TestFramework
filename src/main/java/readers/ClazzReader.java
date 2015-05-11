package readers;

import code.project.ProjectClazz;

public class ClazzReader {
	
    private Class<?> clazz;
    
	public ClazzReader(Class<?> clazz) {
        this.clazz = clazz;
    }

    public ProjectClazz read(){		
		ProjectClazz result = new ProjectClazz(this.clazz);
		return result;
	}

}
