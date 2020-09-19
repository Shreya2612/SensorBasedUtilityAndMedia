package com.example.utilitymedia;

import java.io.File;
import java.io.FilenameFilter;

public class Filef implements FilenameFilter {
	String s;
	public Filef(String s){
		this.s="."+s;
	}
	@Override
	public boolean accept(File dir, String filename) {
		// TODO Auto-generated method stub
		return filename.endsWith(s);
	}

}
