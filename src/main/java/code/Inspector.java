package code;

import java.io.File;

public class Inspector {
    
    public Inspector() {
    }
    
    public Code inspect(String path){
        return new Code(new File(path));        
    }
    
}
