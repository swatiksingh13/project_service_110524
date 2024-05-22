package in.swatiksingh.product_service_110524;

import in.swatiksingh.product_service_110524.models.Product;
import in.swatiksingh.product_service_110524.repositories.CategoryRepository;
import in.swatiksingh.product_service_110524.repositories.ProductRepository;
import in.swatiksingh.product_service_110524.repositories.projections.ProductProjection;
import in.swatiksingh.product_service_110524.repositories.projections.ProductWithIdAndTitle;
import in.swatiksingh.product_service_110524.services.SelfProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class ProductService110524ApplicationTests {

// 3 ways to do DI -Constructor,Setter,Autowired
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    void contextLoads() {
    }

    @Test
    void testJpaDeclaredJoin() {
        List<Product> products = productRepository.findAllByCategory_title("new electronics");
        for( Product product : products ) {
            System.out.println( product.getTitle());
        }
    }

    @Test
    void testHQL() {
        List<Product> products = productRepository.getProductWithCategoryName("new electronics");
        for( Product product : products ) {
            System.out.println( product.getTitle());
            System.out.println( product.getCategory().getTitle());
            System.out.println( product.getPrice());
        }
    }

    @Test
    void testSpecificFields() {
        List<String> productTitles = productRepository.someTitleMethod("new electronics");
        for( String productTitle : productTitles ) {
            System.out.println(productTitle);
        }
    }

    @Test
    void testProjections() {

        List<ProductWithIdAndTitle> products= productRepository.someMethod1("new electronics");
        for( ProductWithIdAndTitle product : products) {
            System.out.println(product.getTitle() + " " + product.getId());
        }

        List<ProductProjection> productProjections = productRepository.someMethod2("new electronics");
        for( ProductProjection p : productProjections) {
            System.out.println(p.getTitle() + " " + p.getId()+ " " + p.getPrice());
        }
    }

    @Test
    void testNativeSql() {
        Product product = productRepository.someNativeSql(2L);
        System.out.println( product.getTitle());

        ProductProjection product1= productRepository.someNativeSql1(8L);
        System.out.println( product1.getTitle() + " " + product1.getId() + " " + product1.getPrice());


        ProductProjection product2 = productRepository.someNativeSql2(7L);
        System.out.println( product2.getTitle());
        System.out.println(product2.getId());
    }
}
