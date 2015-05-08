package code.project;


public interface Visitor {
	void visit(ProjectClazz clazz);
	void visit(ProjectConstructorMember constructorMember);
	void visit(ProjectMethodMember methodMember);
    void visit(ProjectPackage package1);
}
