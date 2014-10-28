package com.github.cmput301f14t11.teamlort.test;

import com.github.cmput301f14t11.teamlort.Question;
import com.github.cmput301f14t11.teamlort.UserController;
import com.github.cmput301f14t11.teamlort.Model.PersistentDataManager;

import junit.framework.TestCase;

public class UserControllerTest extends TestCase {
	
	private static PersistentDataManager pdm = PersistentDataManager.getInstance();
	
	public void testCreate(){
		UserController uc = new UserController();
		assertTrue("uc should not be null",uc != null);
	}
	
	public void testIsLogin(){
		UserController uc = new UserController();
		assertTrue("no user log in", !uc.isLogin());
		uc.login("Ann");
		assertTrue("Ann is logging in", !uc.isLogin());
		assertTrue("User should be Ann", pdm.getUsername().equals("Ann"));
		
	}
	
	public void testLogin(){
		UserController uc = new UserController();
		uc.login("Ann");
		assertTrue("Should set Username to Ann",pdm.getUsername().equals("Ann"));
		uc.login("Bob");
		assertTrue("Should set Username to Bob",pdm.getUsername().equals("Bob"));
	}
	
	public void testLogout(){
		UserController uc = new UserController();
		assertTrue("Should return a null string",pdm.getUsername().isEmpty());
		uc.logout();
		assertTrue("Should return a null string",pdm.getUsername().isEmpty());
		uc.login("Ann");
		assertTrue("Should set Username to Ann",pdm.getUsername().equals("Ann"));
		uc.logout();
		assertTrue("Should return a null string",pdm.getUsername().isEmpty());
	}
	/*
	public void testSaveCacheData(){
		UserController uc = new UserController();
		DataController dc = new DataController();
		uc.login("Ann");
		assertTrue("should have empty question list",pdm.getSavedQuestions().isEmpty());
		Question q1 = new Question();
		dc.addQuestion(q1);
		uc.saveCacheData();
		assertTrue("should contain q1 now", pdm.getSavedQuestions().contains(q1));	
		
	}
	
	public void testSaveFavoriteData(){
		UserController uc = new UserController();
		DataController dc = new DataController();
		uc.login("Ann");
		assertTrue("should have empty question list",pdm.getFavedQuestions().isEmpty());
		Question q1 = new Question();
		dc.addQuestion(q1);
		uc.saveFavoriteData();
		assertTrue("should contain q1 now", pdm.getFavedQuestions().contains(q1));	
		
	}*/
	
	public void testAddFavorite(){
		UserController uc = new UserController();
		uc.login("Ann");
		assertTrue("should have empty question list",pdm.getFavedQuestions().isEmpty());
		Question q1 = new Question();
		uc.addFavorite(q1);
		assertTrue("q1 should be in the favorite question list",pdm.getFavedQuestions().contains(q1));
		
	}
	
	public void testRemoveFavorite(){
		UserController uc = new UserController();
		uc.login("Ann");
		assertTrue("should have empty question list",pdm.getFavedQuestions().isEmpty());
		Question q1 = new Question();
		uc.addFavorite(q1);
		uc.removeFavorite(q1);
		assertTrue("should have empty question list",pdm.getFavedQuestions().isEmpty());
		
	}
	
	public void testAddCache(){
		UserController uc = new UserController();
		uc.login("Ann");
		assertTrue("should have empty question list",pdm.getSavedQuestions().isEmpty());
		Question q1 = new Question();
		uc.addCache(q1);
		assertTrue("should have empty question list",pdm.getSavedQuestions().contains(q1));
	}
	
	public void testRemoveCache(){
		UserController uc = new UserController();
		uc.login("Ann");
		assertTrue("should have empty question list",pdm.getSavedQuestions().isEmpty());
		Question q1 = new Question();
		uc.addCache(q1);
		uc.removeCache(q1);
		assertTrue("should have empty question list",pdm.getSavedQuestions().isEmpty());
		
	}

}
