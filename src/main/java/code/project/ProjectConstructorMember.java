package code.project;

import java.lang.reflect.Constructor;
import java.util.ArrayList;

import code.Margin;

public class ProjectConstructorMember extends ProjectMember implements CodeComponent {

    public ProjectConstructorMember(Constructor<?> constructor, ProjectClazz projectClazz) {
        super(constructor, projectClazz);
        this.name = constructor.getName().substring(constructor.getName().lastIndexOf('.') + 1);
        this.parameterTypes = new ArrayList<ProjectParameterMember>();
        System.out.println(Margin.instance().tabs() + "Constructor: " + this.name);
        this.build(constructor);
    }

    private void build(Constructor<?> constructor) {
        for (Class<?> parameterType : constructor.getParameterTypes()) {
            this.parameterTypes.add(new ProjectParameterMember(parameterType));
        }
    }

    public String getName() {
        return this.name;
    }

    public int getParameterNumber() {
        return this.parameterTypes.size();
    }
	
    public void accept(ProjectVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public boolean equals(Object obj) {
        if ((obj instanceof ProjectConstructorMember)) {
            ProjectConstructorMember projectConstructorMember = (ProjectConstructorMember) obj;
            Margin.instance().inc();
            System.out.print(Margin.instance().tabs() + "compruebo si " + this.toString() + " es igual al método: "
                    + projectConstructorMember.toString() + " --> ");
            if (!projectClazz.equals(projectConstructorMember.getProjectClazz())) {
                System.out.println("La clase no coincide.");
                Margin.instance().dec();
                return false;
            }
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
