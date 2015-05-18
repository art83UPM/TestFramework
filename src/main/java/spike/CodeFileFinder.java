package spike;

import java.util.ArrayList;

import code.TestFrameworkClassLoader;
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
        ProjectMethodMember projectMethodMember = new ProjectMethodMember("read", "ProjectClazz", "ClazzReader",
                new ArrayList<ProjectParameterMember>());
        main.exist(projectMethodMember, new ProjectClazz("ClazzReader"), new ProjectPackage("readers"));
        ProjectConstructorMember projectConstructorMember = new ProjectConstructorMember("ProjectMember",
                new ArrayList<ProjectParameterMember>());
        main.exist(projectConstructorMember, new ProjectClazz("ProjectMember"), new ProjectPackage("code.project"));
    }
}
