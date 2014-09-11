package com.wikia.webdriver.TestCases.AdsTests;

import com.wikia.webdriver.Common.Core.URLBuilder.UrlBuilder;
import com.wikia.webdriver.Common.DataProvider.Ads.AdsDataProvider;
import com.wikia.webdriver.Common.Templates.NewTestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.AdsBase.AdsBaseObject;
import java.io.IOException;
import org.openqa.selenium.Dimension;
import org.testng.annotations.Test;

/**
 * @author Bogna 'bognix' Knychala
 * @ownership AdEngineering
 */
public class TestAdSkinPresence extends NewTestTemplate {

	public TestAdSkinPresence() {
		super();
		urlBuilder = new UrlBuilder(config.getEnv());
	}

	/**
	 * Test ad skin presence on given resolution.
	 * @param wikiName - name of the wiki
	 * @param article - name of the article
	 * @param screenImageUrl - DFP link with ad skin image
	 * @param windowResolution - window resolution
	 * @param skinLeftSide - path to file with decoded using Base64 ad skin
	 * @param skinRightSide - path to file with decoded using Base64 ad skin
	 * @throws IOException
	 */
	@Test(
		dataProviderClass=AdsDataProvider.class,
		dataProvider="skin",
		groups={"TestSkinPresence_GeoEdgeFree"},
		invocationCount=3
	)
	public void TestSkinPresence_GeoEdgeFree(
		String wikiName, String article, String screenImageUrl,
		Dimension windowResolution, String skinLeftSide, String skinRightSide
	) throws IOException {
		String testedPage = urlBuilder.getUrlForPath(wikiName, article);
		AdsBaseObject wikiPage = new AdsBaseObject(driver, testedPage, windowResolution);
		wikiPage.verifyAdSkinPresence(screenImageUrl, skinLeftSide, skinRightSide);
	}
}
