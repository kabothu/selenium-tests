package com.wikia.webdriver.elements.oasis.pages;

import com.wikia.webdriver.elements.common.Navigate;
import com.wikia.webdriver.elements.oasis.components.articlepreview.MobilePreviewModal;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ArticlePreviewPage extends WikiBasePageObject {

  private static final String ARTICLE_PREVIEW_PAGE = "/wiki/ArticlePreview";
  private static final String[] EDIT_QUERY_PARAM = {"action=edit"};
  @FindBy(css = "#wpPreviewMobile")
  private WebElement mobilePreviewButton;
  @FindBy(css = ".mobileNav-preview")
  private WebElement mobilePreviewModal;
  @FindBy(css = ".mobileNav-preview iframe")
  private WebElement mobilePreviewIframe;
  private Navigate navigate;

  public ArticlePreviewPage() {
    super();

    navigate = new Navigate();
  }

  public ArticlePreviewPage navigateToArticlePreviewPageInEditMode() {
    navigate.toPageByPath(ARTICLE_PREVIEW_PAGE, EDIT_QUERY_PARAM);

    return this;
  }

  public MobilePreviewModal clickOnMobilePreviewButton() {
    wait.forElementClickable(mobilePreviewButton);
    mobilePreviewButton.click();

    wait.forElementVisible(mobilePreviewModal);
    driver.switchTo().frame(mobilePreviewIframe);

    return new MobilePreviewModal();
  }
}
