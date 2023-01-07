package com.shakirov.database.setter;

import com.shakirov.database.circularDependency.ClassA;
import com.shakirov.database.circularDependency.ClassB;

//@Component
public class AutowiredSetter {

    private ClassA classA;
    private ClassB classB;

//    @Autowired
    public void setClassA(ClassA classA) {
        this.classA = classA;
    }

//    @Autowired
    public void setClassB(ClassB classB) {
        this.classB = classB;
    }
}
