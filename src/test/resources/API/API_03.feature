@API_US03
Feature: Bir yönetici (admin) olarak API bağlantısı üzerinden yeni bir visitor purpose kaydı olusturabilmek istiyorum.

  @API_US03_TC1
  Scenario: api/visitorsPurposeAdd endpoint'ine gecerli authorization bilgileri ve dogru datalar
  (visitors_purpose, description) iceren bir POST body gönderildiginde dönen status code'in 200 oldugu
  ve response body'deki message bilgisinin "Success" oldugu dogrulanmali

    Given Api kullanicisi "api/visitorsPurposeAdd" path parametreleri set eder.
    When Api kullanicisi api visitorsPurposeAdd endpointine gondermek icin bir request body hazirlar
    And Api kullanicisi api visitorsPurposeAdd endpointinden donen response kaydeder
    Then Api kullanicisi donen status codein 200 oldugunu ve response bodydeki message bilgisinin "Success" oldugunu dogrular

  @API_US03_TC2
  Scenario: api/visitorsPurposeAdd endpoint'ine gecersiz authorization bilgileri veya eksik datalar
  (visitors_purpose, description) iceren bir POST body gönderildiginde dönen status code'in 403 oldugu
  ve response body'deki message bilgisinin "failed" oldugu dogrulanmali

    Given Api kullanicisi "api/visitorsPurposeAdd" path parametreleri set eder.
    When Api kullanicisi api visitorsPurposeAdd endpointine gondermek icin eksik datalar iceren request body hazirlar
    And Api kullanicisi api visitorsPurposeAdd endpointinden donen response gecersiz authorization bilgileriyle kaydeder
    Then Api kullanicisi donen status codein 403 oldugunu ve response bodydeki message bilgisinin "failed" oldugunu dogrular

  @API_US03_TC3
  Scenario: API uzerinden olusturulmak istenen yeni visitor purpose kaydinin olustugu API uzerinden dogrulanmali.
  (Response bodyde dönen addId ile api/visitorsPurposeId endpoint'ine POST body gönderilerek kayıt oluşturulduğu doğrulanabilir.)

    Given Api kullanicisi "api/visitorsPurposeId" path parametreleri set eder.
    When Api kullanicisi api visitorsPurposeId endpointine gondermek icin bir request body hazirlar
    And Api kullanicisi api visitorsPurposeId endpointinden donen response kaydeder
    Then Api kullanicisi donen status codein 200 oldugunu ve response bodydeki message bilgisinin "Success" oldugunu dogrular


