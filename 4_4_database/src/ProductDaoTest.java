import java.util.List;

public class ProductDaoTest {
    public static void main(String[] args) {
        Product product = new Product();
        product.setName("電卓");
        product.setPrice(1500);

        ProductDao dao = new ProductDao();
        dao.register(product);
        
        Product p = new Product();
        p.setId(1);  // ← 実際に存在するID
        p.setName("修正済みペン");
        p.setPrice(300);

        
        dao.update(p);
        
        ProductDao b = new ProductDao();
        dao.delete(1);
        
        Product cond = new Product();
        cond.setName("シャープペンシル");  // nameだけ条件にする例

        List<Product> list = dao.find(cond);

        for (Product a : list) {
            System.out.println(p.getId() + " / " + p.getName() + " / " + p.getPrice());
        }
    }
    
    
        
    
}