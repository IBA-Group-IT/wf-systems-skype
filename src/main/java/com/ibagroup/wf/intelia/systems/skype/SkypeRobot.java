package com.ibagroup.wf.intelia.systems.skype;

import com.ibagroup.wf.intelia.core.config.ConfigurationManager;
import com.ibagroup.wf.intelia.core.robots.RobotProtocol;
import com.ibagroup.wf.intelia.core.security.SecureEntryDtoWrapper;
import com.ibagroup.wf.intelia.systems.skype.clients.SkypeClient;
import com.ibagroup.wf.intelia.systems.skype.pages.LoginPage;
import com.ibagroup.wf.intelia.systems.skype.pages.MainPage;

public interface SkypeRobot extends RobotProtocol {
	
    default void initRobot(SecureEntryDtoWrapper loginCreds) {
    	SkypeClient client = new SkypeClient(getCfg());

        LoginPage loginPage = client.getLoginPage();
        storeCurrentMetadata();

        MainPage mainPage = loginPage.login(loginCreds);
        setMainPage(mainPage);
        storeCurrentMetadata();
    }

    default void finiliseRobot() {
        MainPage mainPage = getMainPage();
        if (mainPage != null) {
            mainPage.logout();

            setMainPage(null);
        }
    }

    ConfigurationManager getCfg();

    MainPage getMainPage();
    void setMainPage(MainPage mainPage);

}