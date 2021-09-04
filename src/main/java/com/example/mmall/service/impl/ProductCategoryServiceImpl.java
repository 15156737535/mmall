package com.example.mmall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.Mapper;
import com.example.mmall.entity.Product;
import com.example.mmall.entity.ProductCategory;
import com.example.mmall.mapper.ProductCategoryMapper;
import com.example.mmall.mapper.ProductMapper;
import com.example.mmall.service.ProductCategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mmall.service.ProductService;
import com.example.mmall.vo.ProductCategoryVo;
import com.example.mmall.vo.ProductVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 坚强
 * @since 2021-05-21
 */
@Service
public class ProductCategoryServiceImpl extends ServiceImpl<ProductCategoryMapper, ProductCategory> implements ProductCategoryService {
    @Autowired
    private ProductCategoryMapper productCategoryMapper;
    @Autowired
    private ProductMapper productMapper;


    @Override
    public List<ProductCategoryVo> getAllProductCategoryVo() {
        //一级分类
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("type", 1);
        List<ProductCategory> levelOne = productCategoryMapper.selectList(wrapper);
        List<ProductCategoryVo> levelOneVO = levelOne.stream().map(e -> new ProductCategoryVo(e.getId(), e.getName())).collect(Collectors.toList());

        for (int i = 0; i < levelOneVO.size(); i++) {
            levelOneVO.get(i).setBannerImg("/images/banner"+i+".png");
            levelOneVO.get(i).setTopImg("/images/top"+i+".png");
            wrapper =new QueryWrapper();
            wrapper.eq("categorylevelone_id",levelOneVO.get(i).getId());
            List<Product> products=productMapper.selectList(wrapper);
          List<ProductVo> productVos=  products.stream().map(
                    e->new ProductVo(
                            e.getId(),
                            e.getName(),
                            e.getPrice(),
                            e.getFileName()
                    )
            ).collect(Collectors.toList());
        levelOneVO.get(i).setProductVo(productVos);
        }
        for (ProductCategoryVo productCategoryVo : levelOneVO) {
            wrapper = new QueryWrapper();
            wrapper.eq("type", 2);
            wrapper.eq("parent_id", productCategoryVo.getId());
            List<ProductCategory> levelTwo = productCategoryMapper.selectList(wrapper);
            List<ProductCategoryVo> leveTwoVo = levelTwo.stream().map(e -> new ProductCategoryVo(e.getId(), e.getName())).collect(Collectors.toList());
            productCategoryVo.setChildren(leveTwoVo);

            for (ProductCategoryVo productCategoryVo2 : leveTwoVo) {
                wrapper = new QueryWrapper();
                wrapper.eq("type", 3);
                wrapper.eq("parent_id", productCategoryVo2.getId());
                List<ProductCategory> levelThree = productCategoryMapper.selectList(wrapper);
                List<ProductCategoryVo> leveThreeVo = levelThree.stream().map(e -> new ProductCategoryVo(e.getId(), e.getName())).collect(Collectors.toList());
                productCategoryVo2.setChildren(leveThreeVo);
            }


        }
        return levelOneVO;
    }
}


