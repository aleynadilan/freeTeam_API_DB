@API_US02
Feature: Bir yönetici (admin) olarak API bağlantısı üzerinden id'si verilen kullanicinin
  Visitor Purpose bilgilerine erisebilmek istiyorum.

  @API_US02_TC1
  Scenario: api/visitorsPurposeId endpoint'ine gecerli authorization bilgileri ve dogru data (id) iceren bir POST body
  gönderildiğinde dönen status code'in 200 oldugu ve response body'deki message bilgisinin "Success" olduğu doğrulanmali

    Given Api kullanicisi "api/visitorsPurposeId" path parametreleri set eder.
    When Api kullanicisi api visitorsPurposeId endpointine gondermek icin bir request body hazirlar
    And Api kullanicisi api visitorsPurposeId endpointinden donen response kaydeder
    Then Api kullanicisi donen status codein 200 oldugunu ve response bodydeki message bilgisinin "Success" oldugunu dogrular

  @API_US02_TC2
  Scenario: api/visitorsPurposeId endpoint'ine gecersiz authorization bilgileri veya gecersiz data (id) iceren
  bir POST body gönderildiğinde dönen status code'in 403 oldugu ve response body'deki message bilgisinin "failed"
  olduğu doğrulanmali

    Given Api kullanicisi "api/visitorsPurposeId" path parametreleri set eder.
    When Api kullanicisi api visitorsPurposeId endpointine gondermek icin gecersiz bir id iceren request body hazirlar
    And Api kullanicisi api visitorsPurposeId endpointinden donen response gecersiz authorization bilgileriyle kaydeder
    Then Api kullanicisi donen status codein 403 oldugunu ve response bodydeki message bilgisinin "failed" oldugunu dogrular

  @API_US02_TC3
  Scenario: Response body icindeki lists datalarının (id, visitors_purpose, description, created_at) içerikleri doğrulanmali.

    Given Api kullanicisi "api/visitorsPurposeId" path parametreleri set eder.
    When Api kullanicisi api visitorsPurposeId endpointine gondermek icin bir request body hazirlar
    And Api kullanicisi api visitorsPurposeId endpointinden donen response kaydeder
    Then Api kullanicisi api visitorsPurposeId endpointinden donen response body icindeki lists datalarini dogrular

