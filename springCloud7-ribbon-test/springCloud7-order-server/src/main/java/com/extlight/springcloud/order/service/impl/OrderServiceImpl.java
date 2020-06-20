package com.extlight.springcloud.order.service.impl;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.extlight.springcloud.common.model.Order;
import com.extlight.springcloud.common.vo.Result;
import com.extlight.springcloud.order.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService{
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private DiscoveryClient client;
	
    @Autowired
    private LoadBalancerClient loadBalancer;

	@Override
	public void placeOrder(Order order) throws Exception{
		
		ServiceInstance serviceInstance = loadBalancer.choose("goods-service");
        System.out.println("服务地址：" + serviceInstance.getUri());
        System.out.println("服务名称：" + serviceInstance.getServiceId());

        String callServiceResult = new RestTemplate().getForObject(serviceInstance.getUri().toString() + "/goods/goodsInfo/" + order.getGoodsId(), String.class);
        System.out.println(callServiceResult);
		
		// 获取商品服务地址列表	
		List<ServiceInstance> list = this.client.getInstances("goods-service");
		String uri = "";
	    for (ServiceInstance instance : list) {
	        if (instance.getUri() != null && !"".equals(instance.getUri())) {
	        	uri = instance.getUri().toString();
	        	break;
	        }
	    }
	    
	    System.out.println("=====访问地址===="+ uri + "/goods/goodsInfo/" + order.getGoodsId());
		// Result result = restTemplate.getForObject(new URI(uri + "/goods/goodsInfo/" + order.getGoodsId()), Result.class);
		
		Result result = this.restTemplate.getForObject("http://goods-service/goods/goodsInfo/" + order.getGoodsId(), Result.class);
		
		if (result != null && result.getCode() == 200) {
			System.out.println("=====下订单====");
			System.out.println(result.getData());
		}
	}
	

}
