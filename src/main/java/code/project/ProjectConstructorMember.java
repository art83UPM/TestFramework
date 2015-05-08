package code.project;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

public class ProjectConstructorMember implements CodeComponent {

    private Constructor<?> constructor;
    
    private String name;
    
    private int order;

    private List<ProjectParameterMember> parameterTypes;

    public ProjectConstructorMember(Constructor<?> constructor, int order) {
        this.constructor = constructor;
        this.name = constructor.getName().substring(constructor.getName().lastIndexOf('.') + 1);
        this.order = order;
        this.parameterTypes = new ArrayList<ProjectParameterMember>();
        this.build();
    }
    
    public void build() {
        for (Class<?> parameterType : constructor.getParameterTypes()) {
            this.parameterTypes.add(new ProjectParameterMember(parameterType));
        }
    }

    public String getName() {
        return this.name;
    }

    public int getOrder() {
        return order;
    }

    public List<ProjectParameterMember> getParametersType() {
        return parameterTypes;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
