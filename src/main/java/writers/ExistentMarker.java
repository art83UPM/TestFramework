package writers;

import code.config.ConfigStatus;
import code.project.ProjectClazz;
import code.project.ProjectConstructorMember;
import code.project.ProjectMethodMember;
import code.project.ProjectPackage;

public class ExistentMarker implements ProjectVisitor {

    @Override
    public void visit(ProjectPackage projectPackage) {

    }

    @Override
    public void visit(ProjectClazz projectClazz) {
        if (projectClazz.getConfigMember().getStatus() == ConfigStatus.GENERATE) {
            projectClazz.getConfigMember().setStatus(ConfigStatus.EXISTENT);
        }
    }

    @Override
    public void visit(ProjectConstructorMember projectConstructorMember) {
        if (projectConstructorMember.getConfigMember().getStatus() == ConfigStatus.GENERATE) {
            projectConstructorMember.getConfigMember().setStatus(ConfigStatus.EXISTENT);
        }
    }

    @Override
    public void visit(ProjectMethodMember projectMethodMember) {
        if (projectMethodMember.getConfigMember().getStatus() == ConfigStatus.GENERATE) {
            projectMethodMember.getConfigMember().setStatus(ConfigStatus.EXISTENT);
        }
    }

    @Override
    public void close() {

    }

}
