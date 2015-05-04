package spike;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

public class ConstructorMember implements Visitable {

    private String name;

    private List<ParameterMember> parameterTypes;

    public ConstructorMember(Constructor<?> constructor) {
        this.name = constructor.getName();
        this.parameterTypes = new ArrayList<ParameterMember>();
        for (Class<?> parameterType : constructor.getParameterTypes()) {
            this.parameterTypes.add(new ParameterMember(parameterType));
        }
    }

    public String getName() {
        return this.name;
    }

    public List<ParameterMember> getParametersType() {
        return parameterTypes;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
