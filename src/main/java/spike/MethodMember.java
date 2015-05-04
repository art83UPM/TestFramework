package spike;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;


public class MethodMember implements Visitable  {

    private String name;
    
    private String returnType;

    private List<ParameterMember> parameterTypes;
    
    public MethodMember(Method method) {
        this.name = method.getName();
        this.returnType = method.getReturnType().toString();
        this.parameterTypes = new ArrayList<ParameterMember>();
        for (Class<?> parameterType : method.getParameterTypes()) {
            this.parameterTypes.add(new ParameterMember(parameterType));
        }
    }
    
    public String getName() {
        return this.name;
    }

    public String getReturnType() {
        return returnType;
    }

    public List<ParameterMember> getParametersType() {
        return parameterTypes;
    }
    
	public void accept(Visitor visitor){
		visitor.visit(this);
	}
}
