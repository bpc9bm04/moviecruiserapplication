import { AppPage } from './app.po';
import { browser, by, element, protractor } from 'protractor';

describe('frontend App', () => {
  let page: AppPage;

  beforeEach(() => {
    page = new AppPage();
  });

  it('should display title', () => {
    page.navigateTo();
    expect(browser.getTitle()).toEqual('Moviecruiserclientapplication');
  });

  it('should be redirected to /login route', () => {
   
    expect(browser.getCurrentUrl()).toContain('/login');
  });

  it('should be redirected to /register route', () => {
    browser.element(by.css('.register-user')).click();
    expect(browser.getCurrentUrl()).toContain('/register');
  });

  it('should be redirected to /register route', () => {
    browser.element(by.id('firstName')).sendKeys('super');
    browser.element(by.id('lastName')).sendKeys('super');
    browser.element(by.id('username')).sendKeys('super');
    browser.element(by.id('password')).sendKeys('super');
    browser.element(by.css('.register-button')).click();
    expect(browser.getCurrentUrl()).toContain('/register');
  });

  it('should able to login and navigate to popular movies', () => {
    browser.element(by.css('.cancel-button')).click();
    browser.element(by.id('username')).sendKeys('super');
    browser.element(by.id('password')).sendKeys('super');
    
    browser.element(by.css('.login-user')).click();
    expect(browser.getCurrentUrl()).toContain('/movies/popular');
  });

  it('should able to search movies', () => {
      
    browser.element(by.css('.search-button')).click();
    expect(browser.getCurrentUrl()).toContain('/movies/search');
    browser.element(by.css('.search-button-input')).sendKeys('Super');
    browser.element(by.css('.search-button-input')).sendKeys(protractor.Key.ENTER);

    const searchItems = element.all(by.css('.movie-thumbnail'));
    expect(searchItems.count()).toBe(20);

    for(let i=0; i<1; i += 1){
      expect(searchItems.get(i).getText()).toContain('Super');
    }

  });

  it('should able to add movies to watchlist', () => {
    browser.driver.manage().window().maximize();
    browser.sleep(1000);
    

    const searchItems = element.all(by.css('.movie-thumbnail'));
    expect(searchItems.count()).toBe(20);
    searchItems.get(0).click();
    browser.element(by.css('.addButton')).click();

  });

});
