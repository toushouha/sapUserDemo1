using demo from '../db/schema';

service CatalogService {
  entity Products as projection on demo.Products;
}
