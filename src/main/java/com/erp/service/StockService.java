package com.erp.service;

import java.util.List;

import com.erp.entity.ProductEntity;
import com.erp.entity.StockEntity;
import com.erp.entity.UnitEntity;

public interface StockService {

	void updateStock(ProductEntity product, int productionQuantity,UnitEntity unit);

	void updateStockQuantity(ProductEntity product, int productionQuantity, int productionQuantity2);

	void updateStockWhenProductChanged(ProductEntity oldProduct, ProductEntity newProduct, int newQuantity,
			int oldQuantity);

	//void updateStockWhenProductionDeteted(ProductEntity product, int quantity);

	List<StockEntity> getAllProductsStock();

	void updateStockWhenProductionDeteted(ProductEntity product, UnitEntity unit, int quantity);

}
