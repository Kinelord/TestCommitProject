package com.shakirov.database.circularDependency;

import com.shakirov.database.component.TestBeanComponent;
import com.shakirov.database.pool.ConnectionPool;
import com.shakirov.spting_pp.bpp.Auditing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.Resource;

//@Component
@Auditing
public class ClassA {

    @Resource
    private ConnectionPool pool2;

//    @Qualifier(value = "#{systemProperties.}")
    private String name;
    public ClassA() {
    }

    private ClassB classB;
    
    private TestBeanComponent testBeanComponent;

    @Autowired
    public ClassA(@Value("ClasAAAAA") String name, ClassB classB) {
        this.name = name;
        this.classB = classB;
    }

    public ClassB getClassB() {
        return classB;
    }

//    @Autowired
//    public void setClassB(ClassB classB) {
//        this.classB = classB;
//    }
}
