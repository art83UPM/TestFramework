package code.project;

public interface ProjectVisitor {
	void visit(ProjectPackage projectPackage);

	void visit(ProjectClazz projectClazz);

	void visit(ProjectConstructorMember projectConstructorMember);

	void visit(ProjectMethodMember projectMethodMember);
}
