@API_US10
  Feature: Bir yönetici olarak API baglantisi üzerinden id'si verilen kullanicinin
           Visitor bilgilerine erisebilmeliyim.

    @API_US10_TC1
    Scenario: /api/visitorsId endpoint'ine gecerli authorization bilgileri ve dogru data (id) iceren
    bir GET body gönderildiginde dönen status code'in 200 oldugu ve response body'deki
    message bilgisinin "Success" oldugu dogrulanmali

      Given Api kullanicisi "api/visitorsId" path parametreleri set eder.
      When Api kullanicisi api visitorsId endpointe gonderilecek request bodyi hazırlar
      When Api kullanicisi api visitorsId endpointden donen response kaydeder
      Then Api kullanicisi donen status codein 200 oldugunu ve response bodydeki message bilgisinin "Success" oldugunu dogrular
