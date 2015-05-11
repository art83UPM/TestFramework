package code.project;

public class ProjectParameterMember {
    private String type;

    public ProjectParameterMember(Class<?> parameterType) {
        this.type = parameterType.toString().substring(0, 1).toUpperCase() + parameterType.toString().substring(1);
    }

    public String getType() {
        return type.substring(type.lastIndexOf('.') + 1);
    }
}
