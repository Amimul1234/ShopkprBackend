package com.owo.OwoDokan.service.order;

import com.owo.OwoDokan.entity.product.OwoProduct;
import com.owo.OwoDokan.entity.shops.shopsData.Shops;
import com.owo.OwoDokan.entity.order.Shop_keeper_ordered_products;
import com.owo.OwoDokan.entity.order.ShopKeeperOrders;
import com.owo.OwoDokan.repository.admin.ShopRepository;
import com.owo.OwoDokan.repository.cart.CartRepo;
import com.owo.OwoDokan.repository.order.Order_repo;
import com.owo.OwoDokan.repository.product.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ShopKeeperOrderService
{
    private final Order_repo order_repo;
    private final CartRepo cartRepo;
    private final ShopRepository shopRepository;
    private final ProductRepository productRepository;

    public ShopKeeperOrderService( Order_repo order_repo, CartRepo cartRepo, ShopRepository shopRepository,
                                   ProductRepository productRepository )
    {
        this.order_repo = order_repo;
        this.cartRepo = cartRepo;
        this.shopRepository = shopRepository;
        this.productRepository = productRepository;
    }

    @Transactional
    public void addOrder( ShopKeeperOrders shopKeeperOrderParam, String mobileNumber) {

        Optional<Shops> shopsOptional = shopRepository.getByPhone(mobileNumber);

        if(shopsOptional.isPresent())
        {
            Shops shops = shopsOptional.get();

            ShopKeeperOrders shopKeeperOrders = new ShopKeeperOrders();

            shopKeeperOrders.setAdditional_comments(shopKeeperOrderParam.getAdditional_comments());
            shopKeeperOrders.setCoupon_discount(shopKeeperOrderParam.getCoupon_discount());
            shopKeeperOrders.setDate(shopKeeperOrderParam.getDate());
            shopKeeperOrders.setDelivery_address(shopKeeperOrderParam.getDelivery_address());
            shopKeeperOrders.setMethod(shopKeeperOrderParam.getMethod());
            shopKeeperOrders.setReceiver_phone(shopKeeperOrderParam.getReceiver_phone());
            shopKeeperOrders.setShop_phone(shopKeeperOrderParam.getShop_phone());
            shopKeeperOrders.setShipping_state(shopKeeperOrderParam.getShipping_state());
            shopKeeperOrders.setTime_slot(shopKeeperOrderParam.getTime_slot());
            shopKeeperOrders.setOrder_time(shopKeeperOrderParam.getOrder_time());
            shopKeeperOrders.setTotal_amount(shopKeeperOrderParam.getTotal_amount());

            shopKeeperOrders.setShops(shops);
            shops.getShopKeeperOrders().add(shopKeeperOrders);

            for(Shop_keeper_ordered_products shop_keeper_ordered_products : shopKeeperOrderParam.getShop_keeper_ordered_products())
            {
                shop_keeper_ordered_products.setShopKeeperOrders(shopKeeperOrders);
                shopKeeperOrders.getShop_keeper_ordered_products().add(shop_keeper_ordered_products);
            }

            try
            {
                shopRepository.save(shops);
            }catch(Exception e)
            {
                e.printStackTrace();
                throw new RuntimeException("Can not save order");
            }

            try
            {
                cartRepo.deleteById(shopKeeperOrders.getShop_phone());
            }catch (Exception e)
            {
                e.printStackTrace();
                throw new RuntimeException("Can not delete products from cart");
            }
        }
        else
        {
            throw new RuntimeException("Can not find shop");
        }
    }

    public Page<ShopKeeperOrders> getAllProducts( int page, String mobile_number) {
        int pageSize = 10; //products per page
        org.springframework.data.domain.Pageable pageable = PageRequest.of(page, pageSize);
        return order_repo.findByMobileNumber(mobile_number, pageable);
    }

    public ResponseEntity findAllByState(String pending) {
        try
        {
            return new ResponseEntity<>(order_repo.findAllByState(pending), HttpStatus.OK);
        }catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.FAILED_DEPENDENCY);
        }
    }

    @Transactional
    public void setOrderState(long order_id, String order_state)
    {
        ShopKeeperOrders shopKeeperOrders;

        try
        {
            shopKeeperOrders = order_repo.findOrderById(order_id);

            if(order_state.equals("Confirmed"))
            {
                List<Shop_keeper_ordered_products> shop_keeper_ordered_productsList =
                        shopKeeperOrders.getShop_keeper_ordered_products();

                shop_keeper_ordered_productsList.forEach(products -> {

                    Optional<OwoProduct> owoProductOptional = productRepository.findById(products.getProduct_id());

                    if(owoProductOptional.isPresent())
                    {
                        OwoProduct owoProduct = owoProductOptional.get();

                        owoProduct.setProductQuantity(owoProduct.getProductQuantity() - products.getProduct_quantity());

                        productRepository.save(owoProduct);
                    }
                    else {
                        throw new RuntimeException("Can not update product quantity");
                    }
                });
            }

            else if(order_state.equals("Cancelled"))
            {
                List<Shop_keeper_ordered_products> shop_keeper_ordered_productsList =
                        shopKeeperOrders.getShop_keeper_ordered_products();

                shop_keeper_ordered_productsList.forEach(products -> {

                    Optional<OwoProduct> owoProductOptional = productRepository.findById(products.getProduct_id());

                    if(owoProductOptional.isPresent())
                    {
                        OwoProduct owoProduct = owoProductOptional.get();

                        owoProduct.setProductQuantity(owoProduct.getProductQuantity() + products.getProduct_quantity());

                        productRepository.save(owoProduct);
                    }
                    else {
                        throw new RuntimeException("Can not update product quantity");
                    }
                });
            }

            shopKeeperOrders.setShipping_state(order_state);

            order_repo.save(shopKeeperOrders);

        }
        catch (Exception e)
        {
            throw new RuntimeException(e.getMessage());
        }
    }

    public Page<ShopKeeperOrders> findCancelledOrders( int page_num) {
        int page_size = 10;
        org.springframework.data.domain.Pageable pageable = PageRequest.of(page_num, page_size);
        return order_repo.findByCancelledOrders("Cancelled", pageable);
    }

    public Page<ShopKeeperOrders> findDeliveredOrders( int page_num) {
        int page_size = 10;
        org.springframework.data.domain.Pageable pageable = PageRequest.of(page_num, page_size);
        return order_repo.findByCancelledOrders("Delivered", pageable);
    }
}
