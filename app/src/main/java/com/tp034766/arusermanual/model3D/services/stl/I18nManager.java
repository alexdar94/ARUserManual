package com.tp034766.arusermanual.model3D.services.stl;

/**
 * Created by andres on 17/04/17.
 */

public class I18nManager {

	private static final I18nManager manager = new I18nManager();

	public static I18nManager getManager() {
		return manager;
	}

	public String getString(String unknownKeywordMsgProp) {
		return unknownKeywordMsgProp;
	}
}
