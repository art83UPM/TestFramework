package spike;

public class ParameterMember {
    private String name;

    public ParameterMember(Class<?> parameterType) {
        this.name = parameterType.toString();
    }

    public String getName() {
        return name;
    }
}
