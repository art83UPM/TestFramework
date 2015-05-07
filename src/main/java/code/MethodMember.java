package code;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;


public class MethodMember implements CodeComponent  {

    private Method method;
    
    private String name;
    
    private String returnType;
    
    private String clazzName;

    private List<ParameterMember> parameterTypes;
    
    public MethodMember(Method method) {
        this.name = method.getName();
        this.returnType = method.getReturnType().toString();
        this.clazzName = method.getDeclaringClass().getSimpleName();
        this.parameterTypes = new ArrayList<ParameterMember>();
        this.build();
    }
    
    public void build() {
        for (Class<?> parameterType : method.getParameterTypes()) {
            this.parameterTypes.add(new ParameterMember(parameterType));
        }
    }
    
    public String getName() {
        return name.toString().substring(0, 1).toUpperCase() + name.toString().substring(1);
    }

    public String getReturnType() {
        return returnType;
    }

    public String getClazzName() {
        return clazzName;
    }

    public List<ParameterMember> getParametersType() {
        return parameterTypes;
    }
    
	public void accept(Visitor visitor){
		visitor.visit(this);
	}
}
