package code;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

public class ConstructorMember implements CodeComponent {

    private String name;
    
    private int order;

    private List<ParameterMember> parameterTypes;

    public ConstructorMember(Constructor<?> constructor, int order) {
        this.name = constructor.getName().substring(constructor.getName().lastIndexOf('.') + 1);
        this.order = order;
        this.parameterTypes = new ArrayList<ParameterMember>();
        for (Class<?> parameterType : constructor.getParameterTypes()) {
            this.parameterTypes.add(new ParameterMember(parameterType));
        }
    }

    public String getName() {
        return this.name;
    }

    public int getOrder() {
        return order;
    }

    public List<ParameterMember> getParametersType() {
        return parameterTypes;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
