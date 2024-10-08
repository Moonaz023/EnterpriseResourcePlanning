package com.erp.service;

import java.util.List;

import com.erp.dto.IngredientStocKResponseDTO;
import com.erp.dto.RecipeDataDOT;
import com.erp.entity.IngredientEntity;
import com.erp.entity.IngredientStockEntity;
import com.erp.entity.PurchaseIngredientEntity;

public interface IngredientStockService {

	void saveIngredientStock(PurchaseIngredientEntity purchasedIngredient,long tenantId);

	double modifystock_purchagedlt(long ingredient, double quantity);

	List<IngredientStockEntity> getAllIngredientsStock(long tenantId);
	public boolean checkAvailablity(List<RecipeDataDOT> recipe);

	IngredientStocKResponseDTO updateStockByProduction(List<RecipeDataDOT> recipe);

}
