package code.project;

public abstract class CodeFile implements CodeComponent {

    protected String name;

    public String getName() {
        return this.name;
    }

    public abstract boolean exist(ProjectMember projectMember, ProjectClazz projectClazz, ProjectPackage projectPackage);

    public abstract boolean exist(ProjectMember projectMember, ProjectClazz projectClazz);

}
