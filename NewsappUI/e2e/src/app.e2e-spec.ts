import { AppPage } from './app.po';
import { browser, by, protractor, element } from 'protractor';

describe('workspace-project App', () => {
  let page: AppPage;

  beforeEach(() => {
    page = new AppPage();
  });

  it('should display title', () => {
    page.navigateTo();
    expect(browser.getTitle()).toEqual('Newsapp');
  });

  it('should be redirected to /login route on opening the application', () => {
    expect(browser.getCurrentUrl()).toContain('/login');
  });

  it('should be redirected to /register route', () => {
    browser.element(by.css('.register-button')).click();
    expect(browser.getCurrentUrl()).toContain('/register');
  });

  it('should be able to register user and navigate to login page', () => {
    browser.element(by.id('firstName')).sendKeys('UserFirstName');
    browser.element(by.id('lastName')).sendKeys('UserLastName');
    browser.element(by.id('userId')).sendKeys('user123');
    browser.element(by.id('password')).sendKeys('userpass123');
    browser.element(by.css('.register-user')).click();
    expect(browser.getCurrentUrl()).toContain('/login');
  });

  it('should be able to login user and navigate to Dashboard', () => {
    browser.element(by.id('userId')).sendKeys('user123');
    browser.element(by.id('password')).sendKeys('userpass123');
    browser.element(by.css('.login-user')).click();
    expect(browser.getCurrentUrl()).toContain('/news/dashboard');
  });

  it('should be able to add news to favouriteslist', async() => {
    browser.driver.manage().window().maximize();
    browser.driver.sleep(5000);
    const searchItems = element.all(by.css('.card-thumbnail'));
    expect(searchItems.count()).toBe(20);
    searchItems.get(0).click();
    browser.element(by.css('.addButton')).click();
    browser.driver.sleep(5000);
  });

  it('should be able to navigate to Search page', () => {
    browser.element(by.id('searchPage')).click();
    expect(browser.getCurrentUrl()).toContain('/news/search');
  });

  it('should be able to search news using search field', () => {
    browser.element(by.id('search-button-input')).sendKeys('Super');
    browser.element(by.id('search-button-input')).sendKeys(protractor.Key.ENTER);
    const searchItems = element.all(by.css('.newsTitle'));
    browser.driver.sleep(2000);
    expect(searchItems.isPresent()).toBeTruthy();
  });

  it('should be able to add news to favouriteslist', async() => {
    browser.driver.manage().window().maximize();
    browser.driver.sleep(5000);
    const searchItems = element.all(by.css('.card-thumbnail'));
    expect(searchItems.count()).toBe(20);
    searchItems.get(2).click();
    browser.element(by.css('.addButton')).click();
    browser.driver.sleep(5000);
  });

  it('should be able to navigate to favorite page and able to see fav news', ()=>{
    browser.element(by.id('favoritePage')).click();
    browser.driver.sleep(2000);
    const searchItems = element.all(by.css('.card-thumbnail'));
    expect(searchItems.isPresent()).toBeTruthy();
  });

  it('should be able to logout and go back to login page', ()=>{
    browser.element(by.id('logout')).click();
    browser.driver.sleep(2000);
    expect(browser.getCurrentUrl()).toContain('/');
  });

});
