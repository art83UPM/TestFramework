package code;

interface CodeComponent {
    
    void accept(Visitor visitor);
    void build();
}
