package code.project;

public class ProjectParameterMember {
    private String type;

    public ProjectParameterMember(Class<?> parameterType) {
        this.type = parameterType.toString();
    }

    public String getType() {
        return type.substring(type.lastIndexOf('.') + 1);
    }
}
