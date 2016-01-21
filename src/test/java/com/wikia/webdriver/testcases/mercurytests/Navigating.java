package com.wikia.webdriver.testcases.mercurytests;

import com.wikia.webdriver.common.contentpatterns.MercurySubpages;
import com.wikia.webdriver.common.contentpatterns.MercuryWikis;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.helpers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.mercury.Navigation;
import com.wikia.webdriver.elements.mercury.TopBar;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Execute(onWikia = MercuryWikis.MERCURY_AUTOMATION_TESTING)
@InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
public class Navigating extends NewTestTemplate {

  private TopBar topBar;
  private Navigation navigation;

  private final static String SEARCH_PASS = "Gallery";
  private final static String SEARCH_FAIL = "tee";

  @BeforeMethod()
  public void before() {
    this.topBar = new TopBar(driver);
    this.navigation = new Navigation(driver);

    topBar.openMercuryArticleByName(wikiURL, MercurySubpages.MAIN_PAGE);
  }

  @Test()
  public void navigating_001_openNavigateClose() {
    topBar.openNavigation();
    navigation.openSubMenu(1);
    navigation.closeSubMenu();
    topBar.closeNavigation();
  }

  @Test()
  public void navigating_002_searchValidSuggestionAndOpenIt() {
    topBar.openNavigation();
    navigation.openSubMenu(1);
    navigation.typeInSearch(SEARCH_PASS);
    navigation.selectSearchSuggestion(1);
  }

  @Test()
  public void navigating_003_searchInvalidSuggestionAndCloseSearchView() {
    topBar.openNavigation();
    navigation.openSubMenu(1);
    navigation.typeInSearch(SEARCH_FAIL);
    navigation.seeNoSearchResults();
    navigation.cancelSearch();
  }

  @Test()
  public void navigating_004_navigateToPageUsingLocalNav() {
    topBar.openNavigation();
    navigation.openSubMenu(1);
    navigation.openSubMenu(0);
    navigation.openPageLink(0);
  }
}
