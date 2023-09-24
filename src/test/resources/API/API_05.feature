@API_US05
Feature: Bir yönetici olarak API baglantisi üzerinden sistemdeki visitor puspose kaydini silebilmeliyim.

  @API_US05_TC1
  Scenario: api/visitorsPurposeDelete endpoint'ine gecerli authorization bilgileri ve dogru data (id) iceren
  bir DELETE body gönderildiginde dönen status code'in 200 oldugu ve response body'deki message bilgisinin
  "Success" oldugu dogrulanmali

    Given Api kullanicisi "api/visitorsPurposeDelete" path parametreleri set eder.
    When Api kullanicisi api visitorsPurposeDelete endpointine gondermek icin bir request body hazirlar
    And Api kullanicisi api visitorsPurposeDelete endpointinden donen response kaydeder
    Then Api kullanicisi donen status codein 200 oldugunu ve response bodydeki message bilgisinin "Success" oldugunu dogrular

  @API_US05_TC2
  Scenario: api/visitorsPurposeDelete endpoint'ine gecersiz authorization bilgileri veya yanlis data (id) iceren
  bir DELETE body gönderildiginde dönen status code'in 403 oldugu ve response body'deki message bilgisinin
  "failed" oldugu dogrulanmali

    Given Api kullanicisi "api/visitorsPurposeDelete" path parametreleri set eder.
    When Api kullanicisi api visitorsPurposeDelete endpointine gondermek icin yanlis id iceren request body hazirlar
    And Api kullanicisi api visitorsPurposeDelete endpointinden donen response gecersiz authorization bilgileriyle kaydeder ve status codunun '403' oldugunu dogrular

  @API_US05_TC3
  Scenario: Response body icindeki DeletedId bilgisinin api/visitorsPurposeDelete endpoint'ine gönderilen
  DELETE request body icindeki id bilgisi ile ayni oldugu dogrulanmali.

    Given Api kullanicisi "api/visitorsPurposeDelete" path parametreleri set eder.
    When Api kullanicisi api visitorsPurposeDelete endpointine gondermek icin bir request body hazirlar
    And Api kullanicisi api visitorsPurposeDelete endpointinden donen response kaydeder
    Then Api kullanicisi response body icindeki DeletedId bilgisinin api visitorsPurposeDelete endpointine gonderilen request body icindeki id bilgisi ile ayni oldugunu dogrular

  @API_US05_TC4
  Scenario: API uzerinden silinmek istenen visitor purpose kaydinin silindigi, API uzerinden dogrulanmali.
  (Response body'de dönen DeletedId ile  api/visitorsPurposeId endpoint'ine POST body gönderilerek kaydın silindiği doğrulanabilir.)

    Given Api kullanicisi "api/visitorsPurposeId" path parametreleri set eder.
    When Api kullanicisi api visitorsPurposeId endpointine gondermek icin bir request body hazirlar
    And Api kullanicisi api visitorsPurposeId endpointinden donen response kaydeder
    Then Api kullanicisi donen status codein 403 oldugunu ve response bodydeki message bilgisinin "failed" oldugunu dogrular
