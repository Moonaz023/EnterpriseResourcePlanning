package com.erp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erp.entity.IngredientEntity;
import com.erp.repository.IngredientRepository;

@Service
public class IngredientServiceImp implements IngredientService{

	@Autowired
	private IngredientRepository ingredientRepository;
	@Override
	public void saveIngredient(IngredientEntity ingredient,long tenantId) {
		ingredient.setTenantId(tenantId);
		ingredientRepository.save(ingredient);
		
	}
	@Override
	public List<IngredientEntity> getAllIngredients(long tenantId) {
		
		return ingredientRepository.findByTenantId(tenantId);
	}
	@Override
	public void deleteIngredient(Long id) {
		ingredientRepository.deleteById(id);
		
	}
	@Override
	public void updateIngredient(IngredientEntity ingredient,long tenantId) {
		ingredient.setTenantId(tenantId);
		ingredientRepository.save(ingredient);
		
	}

}
