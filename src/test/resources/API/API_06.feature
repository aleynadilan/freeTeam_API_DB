@API_US06
Feature: Bir yönetici (admin) olarak API bağlantısı üzerinden Session List'e erişebilmek istiyorum.

  @API_US06_TC1
  Scenario: api/sessionList endpoint'ine gecerli authorization bilgileri ile bir GET request gönderildiginde
  dönen status code'un 200 oldugu ve response message bilgisinin "Success" oldugu dogrulanmali

    Given Api kullanicisi "api/sessionList" path parametreleri set eder.
    And Api kullanicisi api sessionList endpointinden donen response kaydeder
    Then Api kullanicisi donen status codein 200 oldugunu ve response bodydeki message bilgisinin "Success" oldugunu dogrular

  @API_US06_TC2
  Scenario: api/sessionList endpoint'ine gecersiz authorization bilgileri ile bir GET Request gönderildiginde
  dönen status code'un 403 oldugu ve response message bilgisinin "failed" oldugu dogrulanmali

    Given Api kullanicisi "api/sessionList" path parametreleri set eder.
    Then Api kullanicisi api sessionList endpointinden donen response gecersiz authorization bilgileriyle kaydeder ve status codunun '403' oldugunu dogrular

  @API_US06_TC3
  Scenario: Response body icindeki lists icerigi
  (id'si = "11", olan veri içeriğindeki session: "2017-18", is_active: "no", created_at: "2017-04-20 02:41:37"
  ve updated_at: "0000-00-00" ) olduğu doğrulanmalı.

    Given Api kullanicisi "api/sessionList" path parametreleri set eder.
    And Api kullanicisi api sessionList endpointinden donen response kaydeder
    Then Api kullanicisi api sessionList endpointinden donen response body icindeki lists icerigiidsi 11 olanin datalarini dogrular

