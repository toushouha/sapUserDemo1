using demo from '../db/schema';

type WhoAmIResult : {
  isUser    : Boolean;
  user_name : String;
  client_id : String;
}

service CatalogService {
  entity Products as projection on demo.Products;
}
