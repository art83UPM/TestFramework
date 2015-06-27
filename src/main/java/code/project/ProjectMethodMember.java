package code.project;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import code.Margin;
import code.config.ConfigStatus;

public class ProjectMethodMember extends ProjectMember implements CodeComponent {

    private String returnType;

    public ProjectMethodMember(Method method, ProjectClazz projectClazz) {
        super(method, projectClazz);
        this.name = method.getName();
        this.returnType = method.getReturnType().getSimpleName();
        this.parameterTypes = new ArrayList<ProjectParameterMember>();
        System.out.println(Margin.instance().tabs() + "Método: " + this.name + " de la clase: " + this.projectClazz.getName());
        this.build(method);
    }

    public ProjectMethodMember(String name, ProjectClazz projectClazz, String returnType, List<ProjectParameterMember> parameterType) {
        this.name = name;
        this.projectClazz = projectClazz;
        this.returnType = returnType;
        this.parameterTypes = new ArrayList<ProjectParameterMember>();
    }

    private void build(Method method) {
        for (Class<?> parameterType : method.getParameterTypes()) {
            this.parameterTypes.add(new ProjectParameterMember(parameterType));
        }
    }

    public String getName() {
        return name;
    }

    public String getProjectClazzName() {
    	return this.projectClazz.getName();
    }
    
    public String getReturnType() {
        return returnType;
    }

    public void accept(ProjectVisitor visitor) {
        if(this.getConfigMember().getStatus() == ConfigStatus.GENERATE) {
            visitor.visit(this);
            this.getConfigMember().setStatus(ConfigStatus.EXISTENT);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if ((obj instanceof ProjectMethodMember)) {
            ProjectMethodMember projectMethodMember = (ProjectMethodMember) obj;
            Margin.instance().inc();
            System.out.print(Margin.instance().tabs() + "compruebo si " + this.toString() + " es igual al método: "
                    + projectMethodMember.toString() + " --> ");
            if (!projectClazz.equals(projectMethodMember.getProjectClazz())) {
                System.out.println("La clase no coincide.");
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
        return name + " [" + returnType + ", " + this.projectClazz.getName() + ", " + parameterTypes + "]";
    }

}
