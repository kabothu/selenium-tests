package com.wikia.webdriver.elements.communities.desktop.pages;

import com.wikia.webdriver.common.logging.Log;
import com.wikia.webdriver.elements.common.Navigate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchPage extends WikiBasePageObject {

  private static final String SPECIAL_SEARCH_PAGE = "/wiki/Special:Search";
  private static final String EXISTING_COMMUNITY_NAME = "pokemon";
  @FindBy(css = "#search-v2-input")
  private WebElement searchInput;
  @FindBy(css = "#search-v2-button")
  private WebElement searchButton;
  @FindBy(css = ".exact-wiki-match__result")
  private WebElement relatedCommunityModule;

  public SearchPage() {
    super();
  }

  public SearchPage navigateToSearchPage() {
    new Navigate().toPageByPath(SPECIAL_SEARCH_PAGE);

    return this;
  }

  public SearchPage typeCommunityNameInSearchInput() {
    wait.forElementClickable(searchInput);
    searchInput.sendKeys(EXISTING_COMMUNITY_NAME);

    return this;
  }

  public SearchPage clickSearchButton() {
    wait.forElementClickable(searchButton);
    searchButton.click();

    return this;
  }

  public void relatedCommunityModuleIsVisible() {
    Log.info("Is related community module visible?");
    wait.forElementVisible(relatedCommunityModule);
    Log.info("Related community module is visible");
  }
}
