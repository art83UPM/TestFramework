package code.project;


interface CodeComponent {
    
    void accept(ProjectVisitor visitor);
}
