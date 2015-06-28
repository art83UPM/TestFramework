package writers;

import java.io.File;

import code.config.ConfigStatus;
import code.project.ProjectClazz;
import code.project.ProjectConstructorMember;
import code.project.ProjectMethodMember;
import code.project.ProjectPackage;
import code.project.ProjectParameterMember;
import code.project.ProjectVisitor;

public class HeaderDataWriter implements ProjectVisitor {

    private DataWriter dataWriter;

    private String path;

    public HeaderDataWriter(String path) {
        this.path = path;
    }

    public void visit(ProjectClazz clazz) {
        if (clazz.getConfigMember().getStatus() == ConfigStatus.GENERATE) {
            this.dataWriter = new DataWriter(path + clazz.getPackagePath() + File.separator + clazz.getName() + "TestData.xlsx");
        }
    }

    public void visit(ProjectConstructorMember constructorMember) {
        try {
            if (constructorMember.getConfigMember().getStatus() == ConfigStatus.GENERATE) {
                dataWriter.setSheet("Constructors");
                String header = "get" + constructorMember.getNameWithParams();
                if (constructorMember.getParameterNumber() == 0) {
                    dataWriter.write(header);
                } else {
                    for (int i = 0; i < constructorMember.getParameterNumber(); i++) {
                        dataWriter.write(header + "Parameter" + i);
                    }
                }
            }
        } catch (NullPointerException e) {
            System.out.println("Can't generate constructor " + constructorMember.getNameWithParams() + " because class "
                    + constructorMember.getProjectClazz().getName() + " is not marked as GENERATE.");
        }
    }

    public void visit(ProjectMethodMember methodMember) {
        try {
            if (methodMember.getConfigMember().getStatus() == ConfigStatus.GENERATE) {
                String header = "get" + methodMember.getName();
                for (ProjectParameterMember parameterMember : methodMember.getParametersType()) {
                    header += parameterMember.getType();
                }
                dataWriter.setSheet(header);
                for (int i = 0; i < methodMember.getParametersType().size(); i++) {
                    dataWriter.write(header + "Parameter" + i);
                }
                dataWriter.write(header + "Result");
            }
        } catch (NullPointerException e) {
            System.out.println("Can't generate method " + methodMember.getNameWithParams() + " because class "
                    + methodMember.getProjectClazz().getName() + " is not marked as GENERATE.");
        }
    }

    @Override
    public void visit(ProjectPackage package1) {
    }

    public void close() {
        if (dataWriter != null) {
            dataWriter.save();
        }
    }

}
