package task1;

import java.lang.reflect.*;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.io.*;


//get the CompacDisc.class and load it as a file 
//have the input stream as a byte array
//then use ClassLoader defineClass method to convert the
//bite array to class
//then use reflect api to recrate the file to the assigment
//requirements

public class ReverseEngeneer 
{	

	//get the .class name
	static Path currentPath = Paths.get("");
	private static final String location = currentPath.toAbsolutePath().toString() + "bin\\task1\\CompactDisc.class";
	private static final String nyuFilelocation = currentPath.toAbsolutePath().toString() + "\\src\\task1\\CompactDisc.java";

	//static ClassLoader classLoader = ClassLoader.getSystemClassLoader();
	//static ClassLoaderInvokableClassDefine otherClassLoader = new ClassLoaderInvokableClassDefine();
	static File CDDotClassFile = new File(location);

	static Class<?> compactDiskClass;

	static FileInputStream reading = null;
	//static Writer fileWriter = new FileWriter(nyuFilelocation);

	public static void main(String[] args) 
	{
		//getClassFromClassFile("task1.CompactDisc");
		//compact disk has problems as it is trying to 
		//load the IDataStorage Interface which it can't
		//find
		getDotClassFile("task1.CompactDisc");

		//System.out.println(location  +  "|  ##  |" + nyuFilelocation);
	}

	//get the class file from 
	public static void getDotClassFile(String name)
	{
		//CDDotClassFile
		try
		{
			URLClassLoader UrlClassLoader = new URLClassLoader(new URL[] {CDDotClassFile.toURI().toURL()}, Thread.currentThread().getContextClassLoader() );
			compactDiskClass = Class.forName(name , true , UrlClassLoader);
			//reading.close();
			exportClassAsDotJavaFile( compactDiskClass );
		}
		catch( Exception e)
		{
			e.printStackTrace();
		} 

	}

	//will get all the info needed and write the class as a .java file to
	//the this classes folder value stored in nyuFileLocattion
	//will make a string then write the string
	public static void exportClassAsDotJavaFile( Class<?> class2Export )
	{
		Package classPakage = class2Export.getPackage();
		Class<?> superClass = class2Export.getSuperclass();
		Class<?>[] interfaces = class2Export.getInterfaces();
		String className = class2Export.getSimpleName();
		Field[] compactDFields = class2Export.getDeclaredFields();
		Constructor[] compactDKonstructorz = class2Export.getDeclaredConstructors(); 
		Method[] compactDMethods = class2Export.getDeclaredMethods();


		String daKontents;
		daKontents = classPakage.toString() + ";\n\n"
				+ "public class " + className;

		if(superClass != null)
			daKontents = daKontents + " extends " + superClass.getSimpleName();
		if( interfaces.length != 0)
		{
			daKontents = daKontents + " implements " +interfaces[0].getSimpleName(); 
			if( interfaces.length > 1)
				for (int i = 1; i < compactDMethods.length; i++)
					daKontents = daKontents + " , " + interfaces[i].getSimpleName();
		}	

		daKontents = daKontents + " \n{\n\n";

		for (int i = 0; i < compactDFields.length; i++) 
		{
			daKontents = daKontents + compactDFields[i].getType().getSimpleName() + " " + compactDFields[i].getName() + ";\n";
		}

		daKontents = daKontents + " \n\n"; 

		for (int i = 0; i < compactDKonstructorz.length; i++) 
		{	
			daKontents = daKontents + "\t" + className + "( ";
			Parameter[] parameters = compactDKonstructorz[i].getParameters();
			daKontents = iterateThroughParameters(parameters , daKontents);
			/*if(parameters.length > 0)
			{
				daKontents = daKontents + parameters[0].getType().getSimpleName() + " ";
				if(parameters.length > 1)
					for (int j = 1; j < parameters.length; j++) 
						daKontents = daKontents + ", "+ parameters[j].getType().getSimpleName() + " " ;	
			}*/
			daKontents = daKontents + ")\n\t{\n\n\t}\n";	
		}

		daKontents = daKontents + " \n\n"; 

		for (int i = 0; i < compactDMethods.length; i++) 
		{
			daKontents = daKontents + "\t" + Modifier.toString(compactDMethods[i].getModifiers()) + " " + compactDMethods[i].getReturnType().getSimpleName() + " " +compactDMethods[i].getName() + "( ";
			Parameter[] parameters = compactDMethods[i].getParameters();
			daKontents = iterateThroughParameters(parameters , daKontents);
			daKontents = daKontents + ")\n\t{\n\n\t}\n";
		}

		daKontents = daKontents + "\n}";

		System.out.println(daKontents);
		//
		//Writer fileWriter;
		try
		{
			Writer fileWriter = new FileWriter(nyuFilelocation);
			fileWriter.write(daKontents);
			fileWriter.close();
		}
		catch( IOException e )
		{
			e.printStackTrace();

		}
	}

	public static String iterateThroughParameters(Parameter[] parameters , String kontents)
	{
		if(parameters.length > 0)
		{
			kontents = kontents + parameters[0].getType().getSimpleName() + " ";
			if(parameters.length > 1)
				for (int j = 1; j < parameters.length; j++) 
					kontents = kontents + ", "+ parameters[j].getType().getSimpleName() + " " ;	
		}
		return kontents;
	}

}
