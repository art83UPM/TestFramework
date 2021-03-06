package code.project;


public abstract class ProjectCodeFile implements CodeComponent {

    protected String name;

    public String getName() {
        return this.name;
    }

    public abstract boolean exist(ProjectMember projectMember);

}
