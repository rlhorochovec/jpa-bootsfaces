package br.rafaelhorochovec.jpa.test;

import javax.persistence.Persistence;

public class TestJPA {
	
	public static void main(String[] args) {
		Persistence.createEntityManagerFactory("jpa-bootsfaces");
	}
}