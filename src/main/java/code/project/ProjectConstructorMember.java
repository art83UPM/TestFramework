package code.project;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

import code.Margin;

public class ProjectConstructorMember extends ProjectMember implements CodeComponent {

    private String name;

    private int order;

    private List<ProjectParameterMember> parameterTypes;

    public ProjectConstructorMember(Constructor<?> constructor, int order) {
        super(constructor);
        this.name = constructor.getName().substring(constructor.getName().lastIndexOf('.') + 1);
        this.order = order;
        this.parameterTypes = new ArrayList<ProjectParameterMember>();
        System.out.println(Margin.instance().tabs() + "Constructor: " + this.name);
        this.build(constructor);
    }

    public ProjectConstructorMember(String name, ArrayList<ProjectParameterMember> arrayList) {
        this.name = name;
        this.parameterTypes = new ArrayList<ProjectParameterMember>();
    }

    private void build(Constructor<?> constructor) {
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

    @Override
    public boolean equals(Object obj) {
        if ((obj instanceof ProjectConstructorMember)) {
            ProjectConstructorMember projectConstructorMember = (ProjectConstructorMember) obj;
            Margin.instance().inc();
            System.out.print(Margin.instance().tabs() + "compruebo si " + this.toString() + " es igual al método: "
                    + projectConstructorMember.toString() + " --> ");
            if (!this.getName().equals(projectConstructorMember.getName())) {
                System.out.println("El nombre del método no coincide.");
                Margin.instance().dec();
                return false;
            }
            if (!parameterTypes.equals(projectConstructorMember.getParametersType())) {
                System.out.println("Los tipos de los parámetros no coinciden.");
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
        return name + " [" + parameterTypes + "]";
    }
}
