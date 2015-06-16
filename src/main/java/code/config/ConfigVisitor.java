package code.config;

public interface ConfigVisitor {
	void visit(ConfigPackage configPackage);

	void visitPackageBack();

	void visit(ConfigClazz configClazz);

	void visitClassBack();

	void visit(ConfigMember configMember);
}
