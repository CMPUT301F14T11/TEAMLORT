package com.github.cmput301f14t11.teamlort.test;

import junit.framework.TestCase;

public class UseCase21Test extends TestCase {
	public void TestSaveQuestion() {
		LocalManager lm = LocalManager.getInstance();
		Question question = new Question();
		lm.saveFile(question);
		assertEquals(question, lm.loadFile(question));
	}
	public void TestPushSavedQuestion() {
		LocalManager lm = LocalManager.getInstance();
		Question question = new Question();
		ElasticManager em = ElasticManager.getInstance();
		lm.save(question);
		em.sendQuestion(lm.loadSavedQuestions(question));
		assertTrue(em.serverQuery(question) == question);
	}
	public void TestNetworkController() {
		NetworkController nc = new NetworkController();
		//if there's wifi: assertTrue(nc.checkConnection)
		//else: assertFalse(nc.checkConnection)
	}
	public void TestUseCase() {
		Elasticmanager em = ElasticManager.getInstance();
		LocalManager lm = LocalManager.getInstance();
		Question question = new Question();
		lm.saveFile(question);
		em.sendQuestion(lm.loadSavedQuestions(question));
		assertTrue(em.serverQuery(question) == question);
	}
}
