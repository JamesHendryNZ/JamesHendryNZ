package task1;

public class ClassLoaderInvokableClassDefine extends ClassLoader 
{

	public ClassLoaderInvokableClassDefine()
	{
		ClassLoader.getSystemClassLoader();
	}
	public Class<?> classDefine(String name, byte[] b, int off, int len )
	{
		Class<?> daClass = defineClass(name , b , off , len);
		return daClass;
	}
}
