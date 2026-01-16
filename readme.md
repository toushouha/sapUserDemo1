mbt build
cf deploy mta_archives/cap-odata-demo_1.0.0-SNAPSHOT.mtar






https://akkodis-consulting-ltd--wg09-z8x0k7po-dev-cap-odata-demo.cfapps.us10-001.hana.ondemand.com/odata/v4/CatalogService/Products


https://emea.cockpit.btp.cloud.sap/cockpit#/globalaccount/d2b92c2d-cca9-4809-a637-55cdf354b84a/subaccount/72664bb3-0dbc-4c65-926e-0f6d917d4fce/org/129ae210-03a5-4c53-a20d-757771ee0a0c/space/e9b98289-3972-4076-8b4f-ac4092462273/app/baf47a77-0328-4246-ba59-34afb8b43130/logs



cf create-service-key cap-odata-demo-auth batch-client-key -c '{
  "xsappname": "cap-odata-demo",
  "clientid": "batch-client",
  "clientsecret": "temp-secret",
  "grant_type": "client_credentials",
  "authorities": [
    "cap-odata-demo.batch.read"
  ]
}'


cap-odata-demo-auth

cf service-key cap-odata-demo-auth
cap-odata-demo-auth


Tasklet is running




user: spring-batch-job $ cf service-key cap-odata-demo-auth batch-client-key
Getting key batch-client-key for service instance cap-odata-demo-auth as takumi.nakagawa@akkodis.co.jp...

mvn clean package
mvn spring-boot:run


user: spring-batch-job $ 