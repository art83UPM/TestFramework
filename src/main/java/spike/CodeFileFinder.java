package spike;

import java.util.ArrayList;

import code.TestFrameworkClassLoader;
import code.config.ConfigClazz;
import code.config.ConfigCode;
import code.config.ConfigMethodMember;
import code.config.ConfigPackage;
import code.project.ProjectClazz;
import code.project.ProjectCode;
import code.project.ProjectConstructorMember;
import code.project.ProjectMethodMember;
import code.project.ProjectPackage;
import code.project.ProjectParameterMember;

public class CodeFileFinder {

    public static void main(String[] args) {
        TestFrameworkClassLoader.setClassLoaderByPath(args[0] + "\\target\\classes\\");
        ProjectCode main = new ProjectCode(args[0] + "\\target\\classes");
        ProjectMethodMember projectMethodMember = new ProjectMethodMember("read", new ProjectClazz("ClazzReader", new ProjectPackage(
                "readers", null)), "ProjectClazz", new ArrayList<ProjectParameterMember>());
        main.exist(projectMethodMember);        
        ProjectConstructorMember projectConstructorMember = new ProjectConstructorMember("ProjectMember", new ProjectClazz("ProjectMember",
                new ProjectPackage("project", new ProjectPackage("code", null))), new ArrayList<ProjectParameterMember>());
        main.exist(projectConstructorMember);
        System.out.println("-------------------------------------------------------------------------------------------------------------------");
        ConfigCode configCode = new ConfigCode("C:\\Users\\nyuron\\Desktop\\config1.json");
        configCode.exist(new ConfigMethodMember("getGetName", new ConfigClazz("Clazz", new ConfigPackage("code", null))));
    }
}
