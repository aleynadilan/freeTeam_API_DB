@API_US01
  Feature: Bir yönetici (admin) olarak API bağlantısı üzerinden Purpose List'e erişebilmek istiyorum.

    @API_US01_TC1
    Scenario: api/visitorsPurposeList endpoint'ine gecerli authorization bilgileri ile bir GET request gönderildiginde
    dönen status code'un 200 oldugu ve response message bilgisinin "Success" oldugu dogrulanmali

      Given Api kullanicisi "api/visitorsPurposeList" path parametreleri set eder.
      When Api kullanicisi api visitorsPurposeList endpointden donen response kaydeder
      Then Api kullanicisi donen status codein 200 oldugunu ve response bodydeki message bilgisinin "Success" oldugunu dogrular

    @API_US01_TC2
    Scenario: api/visitorsPurposeList endpoint'ine gecersiz authorization bilgileri ile bir GET Request gönderildiginde
    dönen status code'un 403 oldugu ve response message bilgisinin "failed" oldugu dogrulanmali

      Given Api kullanicisi "api/visitorsPurposeList" path parametreleri set eder.
      Then Api kullanicisi api visitorsPurposeList endpointinden donen response gecersiz authorization bilgileriyle kaydeder ve status codunun '403' oldugunu dogrular

    @API_US01_TC3
    Scenario: Response body icindeki lists icerigi
    (id'si = "1", olan veri içeriğindeki visitors_purpose: "Marketing " ve created_at: "2023-01-18 01:07:12")
    olduğu doğrulanmalı.

      Given Api kullanicisi "api/visitorsPurposeList" path parametreleri set eder.
      When Api kullanicisi api visitorsPurposeList endpointden donen response kaydeder
      Then Api kullanicisi api visitorsPurposeList endpointinden donen response body icindeki lists icerigi idsi 1 olanin datalarini dogrular
