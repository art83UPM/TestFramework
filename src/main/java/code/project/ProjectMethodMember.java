package code.project;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import code.Margin;

public class ProjectMethodMember extends ProjectMember implements CodeComponent {

    private String name;

    private String returnType;

    private String clazzName;

    private List<ProjectParameterMember> parameterTypes;

    public ProjectMethodMember(Method method) {
        super(method);
        this.name = method.getName();
        this.returnType = method.getReturnType().getSimpleName();
        this.clazzName = method.getDeclaringClass().getSimpleName();
        this.parameterTypes = new ArrayList<ProjectParameterMember>();
        System.out.println(Margin.instance().tabs() + "Método: " + this.name);
        this.build(method);
    }

    public ProjectMethodMember(String name, String returnType, String clazzName, List<ProjectParameterMember> parameterType) {
        this.name = name;
        this.returnType = returnType;
        this.clazzName = clazzName;
        this.parameterTypes = new ArrayList<ProjectParameterMember>();
    }

    private void build(Method method) {
        for (Class<?> parameterType : method.getParameterTypes()) {
            this.parameterTypes.add(new ProjectParameterMember(parameterType));
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

    public List<ProjectParameterMember> getParametersType() {
        return parameterTypes;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public boolean equals(Object obj) {
        if ((obj instanceof ProjectMethodMember)) {
            ProjectMethodMember projectMethodMember = (ProjectMethodMember) obj;
            Margin.instance().inc();
            System.out.print(Margin.instance().tabs() + "compruebo si " + this.toString() + " es igual al método: "
                    + projectMethodMember.toString() + " --> ");
            if (!clazzName.equals(projectMethodMember.getClazzName())) {
                System.out.println("El nombre de la clase no coincide.");
                Margin.instance().dec();
                return false;
            }
            if (!this.getName().equals(projectMethodMember.getName())) {
                System.out.println("El nombre del método no coincide.");
                Margin.instance().dec();
                return false;
            }
            if (!parameterTypes.equals(projectMethodMember.getParametersType())) {
                System.out.println("Los tipos de los parámetros no coinciden.");
                Margin.instance().dec();
                return false;
            }
            if (!returnType.equals(projectMethodMember.getReturnType())) {
                System.out.println("El tipo devuelto no coincide.");
                Margin.instance().dec();
                return false;
            }
            System.out.println("Sí lo es.");
            Margin.instance().dec();
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return name + " [" + returnType + ", " + clazzName + ", " + parameterTypes + "]";
    }

}
