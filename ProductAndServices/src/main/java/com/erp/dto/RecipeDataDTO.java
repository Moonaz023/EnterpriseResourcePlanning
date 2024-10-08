package com.erp.dto;

import java.sql.Date;
import java.util.List;

import com.erp.entity.IngredientEntityResponse;
import com.erp.entity.ProductEntity;
import com.erp.entity.ProductionEntity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class RecipeDataDTO {
	
	
	private long ingredient_id;
	private double ingredientQuantity;

}
