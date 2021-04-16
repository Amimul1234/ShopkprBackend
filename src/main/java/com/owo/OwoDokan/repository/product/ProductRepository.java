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
            "(:subCategories)) and MATCH(s.product_name) AGAINST(:product_name IN BOOLEAN MODE) ORDER BY product_price",
            nativeQuery = true)
    Optional<List<OwoProduct>> findProductBySubCategories(@Param("product_name") String product_name,
                                                          @Param("subCategories") List<String> subCategories, Pageable pageable );

    @Query(value = "SELECT * FROM owo_product s WHERE s.product_sub_category_id IN " +
            "(SELECT e.sub_category_id FROM sub_category_entity e WHERE e.sub_category_name IN " +
            "(:subCategories)) ORDER BY product_price",
            nativeQuery = true)
    Optional<List<OwoProduct>> findProductBySubCategoriesEmpty(@Param("subCategories") List<String> subCategories, Pageable pageable );


    @Query(value = "SELECT * FROM owo_product s WHERE s.product_sub_category_id IN " +
            "(SELECT e.sub_category_id FROM sub_category_entity e WHERE e.sub_category_name IN " +
            "(:subCategories)) and MATCH(s.product_name) AGAINST(:product_name IN BOOLEAN MODE) ORDER BY product_price DESC",
            nativeQuery = true)
    Optional<List<OwoProduct>> findProductBySubCategoriesDesc(@Param("product_name") String s, @Param("subCategories") List<String> subCategories, Pageable pageable );


    @Query(value = "SELECT * FROM owo_product s WHERE s.product_sub_category_id IN " +
            "(SELECT e.sub_category_id FROM sub_category_entity e WHERE e.sub_category_name IN " +
            "(:subCategories)) ORDER BY product_price DESC ",
            nativeQuery = true)
    Optional<List<OwoProduct>> findProductBySubCategoriesEmptyDesc(@Param("subCategories") List<String> subCategories, Pageable pageable );



    /*
    @Query("SELECT e FROM OwoProduct e WHERE e.product_category IN (:categories) order by e.product_price DESC")
    Page<OwoProduct> findByCategoriesDesc(@Param("categories") List<String> categories, Pageable pageable);

    @Query("SELECT e FROM OwoProduct e WHERE e.product_category = :category order by e.product_price")
    Page<OwoProduct> findByproduct_category(@Param("category") String category, Pageable pageable);

    @Query("SELECT e FROM OwoProduct e WHERE e.product_category = :category order by e.product_price DESC")
    Page<OwoProduct> findByproduct_categoryDesc(@Param("category") String category, Pageable pageable);

    @Query("SELECT e FROM OwoProduct e WHERE e.product_sub_category = :sub_category order by e.product_price")
    Page<OwoProduct> findByproduct_sub_category(@Param("sub_category") String sub_category, Pageable pageable);

    @Query("SELECT e FROM OwoProduct e WHERE e.product_sub_category = :sub_category order by e.product_price DESC")
    Page<OwoProduct> findByproduct_sub_categoryDesc(@Param("sub_category") String sub_category, Pageable pageable);

    @Query("SELECT e FROM OwoProduct e WHERE e.product_id = :id")
    OwoProduct findByProduct_Id(@Param("id") long id);

    @Modifying
    @Query("DELETE FROM OwoProduct e WHERE e.product_id = :product_id")
    void delete(@Param("product_id") int product_id);

    @Query("SELECT e FROM OwoProduct e WHERE e.product_category IN(:categories) and e.product_brand = :product_brand order by e.product_price")
    Page<OwoProduct> findProductByBrand(Pageable pageable, @Param("product_brand") String product_brand, @Param("categories") List<String> categories);

    @Query("SELECT e FROM OwoProduct e WHERE e.product_category IN(:categories) and e.product_brand = (:product_brand) order by e.product_price DESC")
    Page<OwoProduct> findProductByBrandDesc(Pageable pageable, @Param("product_brand") String product_brand, @Param("categories") List<String> categories);
     */

}

