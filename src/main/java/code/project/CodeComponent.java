package code.project;

import writers.ProjectVisitor;


interface CodeComponent {
    
    void accept(ProjectVisitor visitor);
}
