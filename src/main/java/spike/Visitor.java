package spike;

public interface Visitor {
	void visit(Clazz clazz);
	void visit(ConstructorMember constructorMember);
	void visit(MethodMember methodMember);
}
