
DROP VIEW IF EXISTS CatalogService_Products;
DROP TABLE IF EXISTS cds_outbox_Messages;
DROP TABLE IF EXISTS demo_Products;

CREATE TABLE demo_Products (
  ID INTEGER NOT NULL,
  Name NVARCHAR(255),
  PRIMARY KEY(ID)
);

CREATE TABLE cds_outbox_Messages (
  ID NVARCHAR(36) NOT NULL,
  timestamp TIMESTAMP(7),
  target NVARCHAR(255),
  msg NCLOB,
  attempts INTEGER DEFAULT 0,
  "PARTITION" INTEGER DEFAULT 0,
  lastError NCLOB,
  lastAttemptTimestamp TIMESTAMP(7),
  status NVARCHAR(23),
  task NVARCHAR(255),
  appid NVARCHAR(255),
  PRIMARY KEY(ID)
);

CREATE VIEW CatalogService_Products AS SELECT
  Products_0.ID,
  Products_0.Name
FROM demo_Products AS Products_0;
