package com.wikia.webdriver.testcases.desktop.discussions;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.elements.communities.desktop.components.navigation.local.CommunityHeaderDesktop;
import com.wikia.webdriver.elements.communities.mobile.pages.discussions.GuidelinesPage;
import com.wikia.webdriver.elements.communities.mobile.pages.discussions.PostsListPage;
import com.wikia.webdriver.testcases.desktop.createawikitests.CreateWikiTestsLang;

import org.testng.annotations.Test;

@Test(groups = "discussions-creation")
public class DiscussionsCreation extends CreateWikiTestsLang {

  @Execute(asUser = User.USER_CNW)
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  public void userCanSeeEmptyDiscussionsMessageAndAddPostOnNewCommunityDiscussionsPage() {
    createNewWikiLangTC001("de");
    PostsListPage page = navigateToDiscussions();
    Assertion.assertTrue(page.getErrorMessages().isEmptyPostsListMessageDisplayed());
    createNewPost(page);
    Assertion.assertFalse(page.getPost().isPostListEmpty());
  }

  @Execute(asUser = User.USER_CNW)
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  public void guidelinesIsNotEmptyOnNewCommunityDiscussionsPage() {
    createNewWikiLangTC001("de");
    PostsListPage page = navigateToDiscussions();
    GuidelinesPage guidelines = page.openGuidelinesPage();
    Assertion.assertFalse(guidelines.getGuidelinesText().isEmpty());
  }

  private void createNewPost(PostsListPage page) {
    page.getPostsCreatorDesktop().startPostCreation().clickSubmitButton();
    page.waitForLoadingSpinner();
  }

  private PostsListPage navigateToDiscussions() {
    new CommunityHeaderDesktop().clickDiscussLink();
    PostsListPage page = new PostsListPage();
    page.waitForEmberLoad();
    return page;
  }
}
