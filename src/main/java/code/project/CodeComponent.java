package code.project;

interface CodeComponent {
    
    void accept(Visitor visitor);
    void build();
}
