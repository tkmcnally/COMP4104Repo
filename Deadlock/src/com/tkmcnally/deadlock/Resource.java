package com.tkmcnally.deadlock;
import java.io.File;


public class Resource {

	public File resource;

	public Resource(String path) {
		this.resource = new File(path);
	}

	public void dolt() {
		System.out.println(resource.getName());
	}

}
