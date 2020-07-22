package com.wang.gmall.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wang.gmall.common.constant.RedisConst;
import com.wang.gmall.mapper.*;
import com.wang.gmall.model.product.BaseCategoryView;
import com.wang.gmall.model.product.SkuImage;
import com.wang.gmall.model.product.SkuInfo;
import com.wang.gmall.model.product.SpuSaleAttr;
import com.wang.gmall.service.ItemApiService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author 微笑
 * @date 2020/7/19 9:40
 */
@Service
public class ItemApiServiceImpl implements ItemApiService {

    @Autowired
    private SkuInfoMapper skuInfoMapper;

    @Autowired
    private BaseCategoryViewMapper categoryViewMapper;

    @Autowired
    private SkuImageMapper skuImageMapper;

    @Autowired
    private SpuSaleAttrMapper spuSaleAttrMapper;

    @Autowired
    private SkuSaleAttrValueMapper skuSaleAttrValueMapper;

    @Autowired
    private RedisTemplate redisTemplate;


    @Override
    public SkuInfo getSkuInfo(Long skuId) {

//        SkuInfo skuInfo = null;
////
////        String skuKey = RedisConst.SKUKEY_PREFIX + skuId + RedisConst.SKUKEY_SUFFIX;
////        //首先从redis获取数据
////        String skuInfoString = (String) redisTemplate.opsForValue().get(skuKey);
////
////        if(StringUtils.isNotEmpty(skuInfoString)){
////            //缓存有数据就不查询数据库，直接返回
////             skuInfo = JSON.parseObject(skuInfoString, SkuInfo.class);
////        }else{
////            //redis里面没有数据从db获取数据
////            skuInfo = getSkuInfoToDB(skuId);
////            //将从db获取的数据保存到redis
////            redisTemplate.opsForValue().set(skuKey,JSON.toJSONString(skuInfo));
////    }
////
////        return skuInfo;

        SkuInfo skuInfoNx = getSkuInfoNx(skuId);
        return skuInfoNx;
    }

    private SkuInfo getSkuInfoNx(Long skuId){
        SkuInfo skuInfo = null;

        String skuKey = RedisConst.SKUKEY_PREFIX + skuId + RedisConst.SKUKEY_SUFFIX;
        //首先从redis获取数据
        String skuInfoString = (String) redisTemplate.opsForValue().get(skuKey);

        if(StringUtils.isNotEmpty(skuInfoString)){
            //缓存有数据就不查询数据库，直接返回
            skuInfo = JSON.parseObject(skuInfoString, SkuInfo.class);
        }else{
            /**
             * 在高并发的情况下，会对数据库有多次连接访问，缓存击穿
             *      解决方案，设置一个锁，拿到锁的线程可以访问数据库，其他的线程自旋
             */
            String uuid = UUID.randomUUID().toString();
            String skuLock = "sku:" + skuId + ":lock";
            //value设置为uuid,到后面删除的时候，可以判断删除的锁是不是当前线程的
            Boolean OK = redisTemplate.opsForValue().setIfAbsent(skuLock, uuid, 5, TimeUnit.SECONDS);

            if(OK){
                //redis里面没有数据从db获取数据
                skuInfo = getSkuInfoToDB(skuId);
                //将从db获取的数据保存到redis
                if(null != skuInfo) {
                    redisTemplate.opsForValue().set(skuKey, JSON.toJSONString(skuInfo));
                }else{
                    //从数据查的数据没有，添加null或空字符串到缓存，不过设置过期时间5分钟，防止缓存穿透
                    redisTemplate.opsForValue().set(skuKey, JSON.toJSONString(new SkuInfo()),5,TimeUnit.MINUTES);
                }

                /**
                 * 当前线程结束，要释放锁(删除锁),
                 *  释放锁的时候当要删除的时候，当前线程锁过期了，就会删掉其他线程的锁
                 *  所以加一个uuid来判断是否是当前线程的锁
                 *  加了判断，在很高并发下，也会出问题，后面就使用lua脚本
                 */
                String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
                // 设置lua脚本返回的数据类型
                DefaultRedisScript<Long> redisScript = new DefaultRedisScript<>();
                // 设置lua脚本返回类型为Long
                redisScript.setResultType(Long.class);
                redisScript.setScriptText(script);
                redisTemplate.execute(redisScript, Arrays.asList(skuLock),uuid);


            }else{
                //没抢到分布式锁资源，自旋，
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                return getSkuInfoNx(skuId);
            }

        }

        return skuInfo;
    }

    private SkuInfo getSkuInfoToDB(Long skuId) {
        SkuInfo skuInfo = skuInfoMapper.selectById(skuId);

        //获取这个skuId的图片信息
        if(null != skuInfo) {
            QueryWrapper<SkuImage> skuImageQueryWrapper = new QueryWrapper<>();
            skuImageQueryWrapper.eq("sku_id", skuId);

            List<SkuImage> skuImageList = skuImageMapper.selectList(skuImageQueryWrapper);

            skuInfo.setSkuImageList(skuImageList);
        }
        return skuInfo;

    }

    @Override
    public BaseCategoryView getCategoryView(Long category3Id) {

        //通过视图获取分类信息
        return categoryViewMapper.selectById(category3Id);
    }

    @Override
    public BigDecimal getPrice(Long skuId) {

        //获取商品sku信息，返回价格
        SkuInfo skuInfo = skuInfoMapper.selectById(skuId);

        return skuInfo.getPrice();
    }

    @Override
    public List<SpuSaleAttr> getSpuSaleAttrListCheckBySku(Long skuId, Long spuId) {

        //获取该商品下spu销售属性
        return spuSaleAttrMapper.selectSpuSaleAttrListCheckBySku(skuId,spuId);
    }

    @Override
    public List<Map<String, Object>> getSkuValueIdsMap(Long spuId) {
        //获取销售属性对应的id为key,value为skuId
        return skuSaleAttrValueMapper.selectSkuValueIdsMap(spuId);
    }
}
