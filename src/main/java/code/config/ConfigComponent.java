package code.config;

import code.config.ConfigVisitor;

interface ConfigComponent {
    
    void accept(ConfigVisitor visitor);
}
