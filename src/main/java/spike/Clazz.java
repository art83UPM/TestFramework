package spike;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;


public class Clazz implements Visitable {

	private List<ConstructorMember> constructorMemberList;
	private List<MethodMember> methodMemberList;
	
	public Clazz(Class<?> clazz){ 
		constructorMemberList = new ArrayList<ConstructorMember>();
		methodMemberList = new ArrayList<MethodMember>();
		
		for (Constructor<?> constructor : clazz.getConstructors()) {
		    ConstructorMember constructorMember = new ConstructorMember(constructor);
	        constructorMemberList.add(constructorMember);
        }
		
		for (Method method : clazz.getMethods()) {
		    MethodMember methodMember = new MethodMember(method);
	        methodMemberList.add(methodMember); 
        }		
	}
	
	public void accept(Visitor visitor){
		visitor.visit(this);
		for(ConstructorMember constructorMember : constructorMemberList){
			constructorMember.accept(visitor);
		}
		for(MethodMember methodMember : methodMemberList){
			methodMember.accept(visitor);
		}
	}
}
