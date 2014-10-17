package com.github.cmput301f14t11.teamlort.test;

import junit.framework.TestCase;

public class UseCase21Test extends TestCase {
	public void TestSaveQuestion() {
		LocalManager lm = new LocalManager();
		Question question = new Question();
		lm.save(question);
		assertEquals(question, lm.loadSavedQuestions(question));
	}
	public void TestPushSavedQuestion() {
		LocalManager lm = new LocalManager();
		Question question = new Question();
		ElasticManager em = new ElasticManager();
		lm.save(question);
		em.sendQuestion(lm.loadSavedQuestions(question));
		assertTrue(em.serverQuery(question) == question);
	}
	public void TestNetworkController() {
		NetworkController nc = new NetworkController();
		//if there's wifi: assertTrue(nc.checkConnection)
		//else: assertFalse(nc.checkConnection)
	}
}
