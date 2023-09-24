@API_US04
Feature: Bir yönetici (admin) olarak API bağlantisi üzeriden sisteme kayitli visitor purpose bilgilerini
  güncelleyebilmek istiyorum.

  @API_US04_TC1
  Scenario: api/visitorsPurposeUpdate endpoint'ine gecerli authorization bilgileri ve dogru datalar
  (id, visitors_purpose, description) iceren bir PATCH body gönderildiginde dönen status code'in 200 oldugu
  ve response body'deki message bilgisinin "Success" oldugu dogrulanmali

    Given Api kullanicisi "api/visitorsPurposeUpdate" path parametreleri set eder.
    When Api kullanicisi api visitorsPurposeUpdate endpointine gondermek icin bir request body hazirlar
    And Api kullanicisi api visitorsPurposeUpdate endpointinden donen response kaydeder
    Then Api kullanicisi donen status codein 200 oldugunu ve response bodydeki message bilgisinin "Success" oldugunu dogrular

  @API_US04_TC2
  Scenario: api/visitorsPurposeUpdate endpoint'ine gecersiz authorization bilgileri veya eksik/yanlış data (id)
  iceren bir PATCH body (visitors_purpose, description) iceren bir PATCH body gönderildiginde dönen
  status code'in 403 oldugu ve response body'deki message bilgisinin "failed" oldugu dogrulanmali

    Given Api kullanicisi "api/visitorsPurposeUpdate" path parametreleri set eder.
    When Api kullanicisi api visitorsPurposeUpdate endpointine gondermek icin eksik id iceren request body hazirlar
    And Api kullanicisi api visitorsPurposeUpdate endpointinden donen response gecersiz authorization bilgileriyle kaydeder ve status codunun '403' oldugunu dogrular

  @API_US04_TC3
  Scenario: Response body icindeki updateId bilgisinin api/visitorsPurposeUpdate endpoint'ine gönderilen
  PATCH request body icindeki id bilgisi ile ayni oldugu dogrulanmali.

    Given Api kullanicisi "api/visitorsPurposeUpdate" path parametreleri set eder.
    When Api kullanicisi api visitorsPurposeUpdate endpointine gondermek icin bir request body hazirlar
    And Api kullanicisi api visitorsPurposeUpdate endpointinden donen response kaydeder
    Then Api kullanicisi response body icindeki updateId bilgisinin api visitorsPurposeUpdate endpointine gonderilen request body icindeki id bilgisi ile ayni oldugunu dogrular

  @API_US04_TC4
  Scenario: API uzerinden güncellenmek istenen visitor purpose kaydinin güncellendigi, API uzerinden dogrulanmali.
  (Response body'de dönen updateId ile api/visitorsPurposeId endpoint'ine POST body gönderilerek kaydın güncellendiği doğrulanabilir.)

    Given Api kullanicisi "api/visitorsPurposeId" path parametreleri set eder.
    When Api kullanicisi api visitorsPurposeId endpointine gondermek icin bir request body hazirlar
    And Api kullanicisi api visitorsPurposeId endpointinden donen response kaydeder
    Then Api kullanicisi response bodyde donen updateId ile apivisitorsPurposeId endpointine POST body gondererek kaydin guncellendigini dogrular

