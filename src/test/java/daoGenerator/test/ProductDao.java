package daoGenerator.test;

import model.Product;
import daoGenerator.Create;
import daoGenerator.Delete;
import daoGenerator.DomainEntity;
import daoGenerator.SqlCmd;
import daoGenerator.Update;

@DomainEntity(value = Product.class, key = "id")
public interface ProductDao {
  
  @SqlCmd(cmd = "SELECT ProductID, ProductName, UnitPrice, UnitsInStock FROM Products")
  Iterable<Product> getAll();
  
  @SqlCmd(cmd = "SELECT ProductID, ProductName, UnitPrice, UnitsInStock FROM Products WHERE ProductID = ?")
  Product getById(int id);
  
  @SqlCmd(type = Update.class, cmd = "UPDATE Products SET ProductName = ?, UnitPrice = ?, UnitsInStock = ? WHERE ProductID = ?")
  void update(String name, double price, int stock, int id);
  
  @SqlCmd(type = Delete.class, cmd = "DELETE FROM Products WHERE ProductID = ?")
  void delete(int id);
  
  @SqlCmd(type = Create.class, cmd = "INSERT INTO Products (ProductName, UnitPrice, UnitsInStock) VALUES (?, ?, ?)")
  Product insert(String name, double price, int stock);
  
}

