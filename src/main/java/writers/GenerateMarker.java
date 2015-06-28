package writers;

import code.config.ConfigStatus;
import code.project.ProjectClazz;
import code.project.ProjectConstructorMember;
import code.project.ProjectMethodMember;
import code.project.ProjectPackage;
import code.project.ProjectVisitor;

public class GenerateMarker implements ProjectVisitor {

    @Override
    public void visit(ProjectPackage projectPackage) {

    }

    @Override
    public void visit(ProjectClazz projectClazz) {

    }

    @Override
    public void visit(ProjectConstructorMember projectConstructorMember) {
        if (projectConstructorMember.getConfigMember().getStatus() == ConfigStatus.NONE) {
            projectConstructorMember.getConfigMember().setStatus(ConfigStatus.GENERATE);
        }
    }

    @Override
    public void visit(ProjectMethodMember projectMethodMember) {
        if (projectMethodMember.getConfigMember().getStatus() == ConfigStatus.NONE) {
            projectMethodMember.getConfigMember().setStatus(ConfigStatus.GENERATE);
        }
    }

    @Override
    public void close() {

    }

}
