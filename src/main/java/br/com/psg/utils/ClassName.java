package br.com.psg.utils;

public class ClassName<E> {

	// Class that has the function of returning the name of the class without the
	// package names.
	public String getClassName(Class<E> entity) {

		String[] realClassName = entity.getCanonicalName().split("\\.");

		return realClassName[realClassName.length - 1];
	}

}
