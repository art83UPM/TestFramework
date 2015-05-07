package code;


public interface Visitor {
	void visit(Clazz clazz);
	void visit(ConstructorMember constructorMember);
	void visit(MethodMember methodMember);
    void visit(Package package1);
}
