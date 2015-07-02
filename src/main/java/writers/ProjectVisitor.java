package writers;

import code.project.ProjectClazz;
import code.project.ProjectConstructorMember;
import code.project.ProjectMethodMember;
import code.project.ProjectPackage;

public interface ProjectVisitor {
	void visit(ProjectPackage projectPackage);

	void visit(ProjectClazz projectClazz);

	void visit(ProjectConstructorMember projectConstructorMember);

	void visit(ProjectMethodMember projectMethodMember);
	
	void close();
}
