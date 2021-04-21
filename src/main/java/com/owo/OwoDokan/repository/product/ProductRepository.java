package com.owo.OwoDokan.repository.product;

import com.owo.OwoDokan.entity.product.OwoProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<OwoProduct, Long> {

    @Query("SELECT e FROM OwoProduct e WHERE e.productCategoryId IN (:categories) order by e.productPrice")
    Page<OwoProduct> findByCategories(@Param("categories") List<Long> categories, Pageable pageable);

    @Query("select e from OwoProduct e where e.brands.brandId = :brandId")
    Optional<List<OwoProduct>> findByBrandId(@Param("brandId") Long brandsId, Pageable pageable);

    @Query("SELECT e FROM OwoProduct e WHERE e.productCategoryId = :productCategory")
    Optional<List<OwoProduct>> findBySpecificCategory(@Param("productCategory") Long productCategory, Pageable pageable);

    @Query(value = "SELECT * FROM owo_product s WHERE s.product_sub_category_id IN " +
            "(SELECT e.sub_category_id FROM sub_category_entity e WHERE e.sub_category_name IN " +
            "(:subCategories)) and MATCH(s.product_name) AGAINST(:product_name IN BOOLEAN MODE) ORDER BY (product_price - product_discount)",
            nativeQuery = true)
    Optional<List<OwoProduct>> findProductBySubCategories(@Param("product_name") String product_name,
                                                          @Param("subCategories") List<String> subCategories, Pageable pageable );

    @Query(value = "SELECT * FROM owo_product s WHERE s.product_sub_category_id IN " +
            "(SELECT e.sub_category_id FROM sub_category_entity e WHERE e.sub_category_name IN " +
            "(:subCategories)) ORDER BY (product_price - product_discount)",
            nativeQuery = true)
    Optional<List<OwoProduct>> findProductBySubCategoriesEmpty(@Param("subCategories") List<String> subCategories, Pageable pageable );


    @Query(value = "SELECT * FROM owo_product s WHERE s.product_sub_category_id IN " +
            "(SELECT e.sub_category_id FROM sub_category_entity e WHERE e.sub_category_name IN " +
            "(:subCategories)) and MATCH(s.product_name) AGAINST(:product_name IN BOOLEAN MODE) ORDER BY (product_price - product_discount) DESC",
            nativeQuery = true)
    Optional<List<OwoProduct>> findProductBySubCategoriesDesc(@Param("product_name") String s, @Param("subCategories") List<String> subCategories, Pageable pageable );


    @Query(value = "SELECT * FROM owo_product s WHERE s.product_sub_category_id IN " +
            "(SELECT e.sub_category_id FROM sub_category_entity e WHERE e.sub_category_name IN " +
            "(:subCategories)) ORDER BY (product_price - product_discount) DESC ",
            nativeQuery = true)
    Optional<List<OwoProduct>> findProductBySubCategoriesEmptyDesc(@Param("subCategories") List<String> subCategories, Pageable pageable );

    @Query(value = "SELECT * FROM owo_product s WHERE s.product_sub_category_id IN(" +
            "(SELECT e.sub_category_id FROM sub_category_entity e WHERE e.sub_category_name IN(:subCategories)))" +
            " and s.product_name LIKE (:alphabet)", nativeQuery = true)
    Optional<List<OwoProduct>> sortProductAlphabetically( Pageable pageable, @Param("subCategories") List<String> subCategories,
                                                          @Param("alphabet") String alphabet );

    @Query(value = "SELECT * FROM owo_product s where s.product_sub_category_id = (:product_sub_category_id) ORDER BY brands_brand_id LIMIT 20", nativeQuery = true)
    Optional<List<OwoProduct>> findProductsViaSubCategory(@Param("product_sub_category_id") Long product_sub_category_id );

    @Query(value = "SELECT * FROM owo_product s WHERE s.product_sub_category_id = (SELECT e.sub_category_id FROM sub_category_entity e WHERE e.sub_category_name = :subCategoryName) " +
            "and s.product_category_id IN(:categoryIds) ORDER BY s.product_id DESC LIMIT 20 ", nativeQuery = true)
    List<OwoProduct> findByProductsBySubCategory(@Param("categoryIds") List<Long> categoryIds, @Param("subCategoryName") String subCategoryName );
}

