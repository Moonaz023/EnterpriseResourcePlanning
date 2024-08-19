package com.erp.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.erp.dto.RecipeDataDOT;
import com.erp.entity.ProductBatchesStockEntity;
import com.erp.entity.ProductEntity;
import com.erp.entity.ProductionEntity;
import com.erp.entity.UnitEntity;
import com.erp.repository.ProductBatchesStockRepository;
import com.erp.repository.ProductionRepository;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {ProductionServiceImp.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class ProductionServiceImpDiffblueTest {
    @MockBean
    private IngredientStockService ingredientStockService;

    @MockBean
    private ProductBatchesStockRepository productBatchesStockRepository;

    @MockBean
    private ProductionRepository productionRepository;

    @Autowired
    private ProductionServiceImp productionServiceImp;

    @MockBean
    private StockService stockService;

    /**
     * Method under test:
     * {@link ProductionServiceImp#saveProduction(ProductionEntity)}
     */
    @Test
    void testSaveProduction() {
        // Arrange
        when(ingredientStockService.checkAvailablity(Mockito.<List<RecipeDataDOT>>any())).thenReturn(true);

        ProductEntity product = new ProductEntity();
        product.setCategory("Category");
        product.setId(1L);
        product.setName("Name");
        product.setProductCode("Product Code");
        product.setProductions(new ArrayList<>());
        product.setUnitPrice(new ArrayList<>());

        UnitEntity productionUnit = new UnitEntity();
        productionUnit.setCf(10.0d);
        productionUnit.setId(1L);
        productionUnit.setIngredientEntity(new ArrayList<>());
        productionUnit.setName("Name");
        productionUnit.setProductBatchesStockEntity(new ArrayList<>());
        productionUnit.setProductionEntity(new ArrayList<>());
        productionUnit.setStockEntity(new ArrayList<>());

        ProductBatchesStockEntity productBatchesStockEntity = new ProductBatchesStockEntity();
        productBatchesStockEntity.setCostPerUnit(10.0d);
        productBatchesStockEntity.setId(1L);
        productBatchesStockEntity.setProduct(product);
        productBatchesStockEntity.setProductionUnit(productionUnit);
        productBatchesStockEntity.setQuantity(1);
        productBatchesStockEntity.setReferenceKey("Reference Key");
        when(productBatchesStockRepository.save(Mockito.<ProductBatchesStockEntity>any()))
                .thenReturn(productBatchesStockEntity);

        ProductEntity product2 = new ProductEntity();
        product2.setCategory("Category");
        product2.setId(1L);
        product2.setName("Name");
        product2.setProductCode("Product Code");
        product2.setProductions(new ArrayList<>());
        product2.setUnitPrice(new ArrayList<>());

        UnitEntity productionUnit2 = new UnitEntity();
        productionUnit2.setCf(10.0d);
        productionUnit2.setId(1L);
        productionUnit2.setIngredientEntity(new ArrayList<>());
        productionUnit2.setName("Name");
        productionUnit2.setProductBatchesStockEntity(new ArrayList<>());
        productionUnit2.setProductionEntity(new ArrayList<>());
        productionUnit2.setStockEntity(new ArrayList<>());

        ProductionEntity productionEntity = new ProductionEntity();
        productionEntity.setDamagedProduct(new ArrayList<>());
        productionEntity.setDateOfProduction(mock(Date.class));
        productionEntity.setId(1L);
        productionEntity.setProduct(product2);
        productionEntity.setProductionQuantity(1);
        productionEntity.setProductionUnit(productionUnit2);
        productionEntity.setRecipe(new ArrayList<>());
        productionEntity.setUnitCost(10.0d);
        when(productionRepository.save(Mockito.<ProductionEntity>any())).thenReturn(productionEntity);
        doNothing().when(stockService).updateStock(Mockito.<ProductEntity>any(), anyInt(), Mockito.<UnitEntity>any());

        ProductEntity product3 = new ProductEntity();
        product3.setCategory("Category");
        product3.setId(1L);
        product3.setName("Name");
        product3.setProductCode("Product Code");
        product3.setProductions(new ArrayList<>());
        product3.setUnitPrice(new ArrayList<>());

        UnitEntity productionUnit3 = new UnitEntity();
        productionUnit3.setCf(10.0d);
        productionUnit3.setId(1L);
        productionUnit3.setIngredientEntity(new ArrayList<>());
        productionUnit3.setName("Name");
        productionUnit3.setProductBatchesStockEntity(new ArrayList<>());
        productionUnit3.setProductionEntity(new ArrayList<>());
        productionUnit3.setStockEntity(new ArrayList<>());

        ProductionEntity production = new ProductionEntity();
        production.setDamagedProduct(new ArrayList<>());
        production.setDateOfProduction(mock(Date.class));
        production.setId(1L);
        production.setProduct(product3);
        production.setProductionQuantity(1);
        production.setProductionUnit(productionUnit3);
        production.setRecipe(new ArrayList<>());
        production.setUnitCost(10.0d);

        // Act
        String actualSaveProductionResult = productionServiceImp.saveProduction(production);

        // Assert
        verify(ingredientStockService).checkAvailablity(isA(List.class));
        verify(stockService).updateStock(isA(ProductEntity.class), eq(1), isA(UnitEntity.class));
        verify(productBatchesStockRepository).save(isA(ProductBatchesStockEntity.class));
        verify(productionRepository).save(isA(ProductionEntity.class));
        assertEquals("ok", actualSaveProductionResult);
        assertEquals(0.0d, production.getUnitCost());
    }

    /**
     * Method under test:
     * {@link ProductionServiceImp#saveProduction(ProductionEntity)}
     */
    @Test
    void testSaveProduction2() {
        // Arrange
        when(ingredientStockService.checkAvailablity(Mockito.<List<RecipeDataDOT>>any())).thenReturn(false);

        ProductEntity product = new ProductEntity();
        product.setCategory("Category");
        product.setId(1L);
        product.setName("Name");
        product.setProductCode("Product Code");
        product.setProductions(new ArrayList<>());
        product.setUnitPrice(new ArrayList<>());

        UnitEntity productionUnit = new UnitEntity();
        productionUnit.setCf(10.0d);
        productionUnit.setId(1L);
        productionUnit.setIngredientEntity(new ArrayList<>());
        productionUnit.setName("Name");
        productionUnit.setProductBatchesStockEntity(new ArrayList<>());
        productionUnit.setProductionEntity(new ArrayList<>());
        productionUnit.setStockEntity(new ArrayList<>());

        ProductionEntity production = new ProductionEntity();
        production.setDamagedProduct(new ArrayList<>());
        production.setDateOfProduction(mock(Date.class));
        production.setId(1L);
        production.setProduct(product);
        production.setProductionQuantity(1);
        production.setProductionUnit(productionUnit);
        production.setRecipe(new ArrayList<>());
        production.setUnitCost(10.0d);

        // Act
        String actualSaveProductionResult = productionServiceImp.saveProduction(production);

        // Assert
        verify(ingredientStockService).checkAvailablity(isA(List.class));
        assertEquals("no", actualSaveProductionResult);
    }

    /**
     * Method under test: {@link ProductionServiceImp#getAllproduction()}
     */
    @Test
    void testGetAllproduction() {
        // Arrange
        ArrayList<ProductionEntity> productionEntityList = new ArrayList<>();
        when(productionRepository.findAll()).thenReturn(productionEntityList);

        // Act
        List<ProductionEntity> actualAllproduction = productionServiceImp.getAllproduction();

        // Assert
        verify(productionRepository).findAll();
        assertTrue(actualAllproduction.isEmpty());
        assertSame(productionEntityList, actualAllproduction);
    }

    /**
     * Method under test: {@link ProductionServiceImp#getProductionById(long)}
     */
    @Test
    void testGetProductionById() {
        // Arrange
        ProductEntity product = new ProductEntity();
        product.setCategory("Category");
        product.setId(1L);
        product.setName("Name");
        product.setProductCode("Product Code");
        product.setProductions(new ArrayList<>());
        product.setUnitPrice(new ArrayList<>());

        UnitEntity productionUnit = new UnitEntity();
        productionUnit.setCf(10.0d);
        productionUnit.setId(1L);
        productionUnit.setIngredientEntity(new ArrayList<>());
        productionUnit.setName("Name");
        productionUnit.setProductBatchesStockEntity(new ArrayList<>());
        productionUnit.setProductionEntity(new ArrayList<>());
        productionUnit.setStockEntity(new ArrayList<>());

        ProductionEntity productionEntity = new ProductionEntity();
        productionEntity.setDamagedProduct(new ArrayList<>());
        productionEntity.setDateOfProduction(mock(Date.class));
        productionEntity.setId(1L);
        productionEntity.setProduct(product);
        productionEntity.setProductionQuantity(1);
        productionEntity.setProductionUnit(productionUnit);
        productionEntity.setRecipe(new ArrayList<>());
        productionEntity.setUnitCost(10.0d);
        Optional<ProductionEntity> ofResult = Optional.of(productionEntity);
        when(productionRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        // Act
        ProductionEntity actualProductionById = productionServiceImp.getProductionById(1L);

        // Assert
        verify(productionRepository).findById(eq(1L));
        assertSame(productionEntity, actualProductionById);
    }

    /**
     * Method under test:
     * {@link ProductionServiceImp#updateProduction(ProductionEntity)}
     */
    @Test
    void testUpdateProduction() {
        // Arrange
        ProductEntity product = new ProductEntity();
        product.setCategory("Category");
        product.setId(1L);
        product.setName("Name");
        product.setProductCode("Product Code");
        product.setProductions(new ArrayList<>());
        product.setUnitPrice(new ArrayList<>());

        UnitEntity productionUnit = new UnitEntity();
        productionUnit.setCf(10.0d);
        productionUnit.setId(1L);
        productionUnit.setIngredientEntity(new ArrayList<>());
        productionUnit.setName("Name");
        productionUnit.setProductBatchesStockEntity(new ArrayList<>());
        productionUnit.setProductionEntity(new ArrayList<>());
        productionUnit.setStockEntity(new ArrayList<>());

        ProductBatchesStockEntity productBatchesStockEntity = new ProductBatchesStockEntity();
        productBatchesStockEntity.setCostPerUnit(10.0d);
        productBatchesStockEntity.setId(1L);
        productBatchesStockEntity.setProduct(product);
        productBatchesStockEntity.setProductionUnit(productionUnit);
        productBatchesStockEntity.setQuantity(1);
        productBatchesStockEntity.setReferenceKey("Reference Key");

        ProductEntity product2 = new ProductEntity();
        product2.setCategory("Category");
        product2.setId(1L);
        product2.setName("Name");
        product2.setProductCode("Product Code");
        product2.setProductions(new ArrayList<>());
        product2.setUnitPrice(new ArrayList<>());

        UnitEntity productionUnit2 = new UnitEntity();
        productionUnit2.setCf(10.0d);
        productionUnit2.setId(1L);
        productionUnit2.setIngredientEntity(new ArrayList<>());
        productionUnit2.setName("Name");
        productionUnit2.setProductBatchesStockEntity(new ArrayList<>());
        productionUnit2.setProductionEntity(new ArrayList<>());
        productionUnit2.setStockEntity(new ArrayList<>());

        ProductBatchesStockEntity productBatchesStockEntity2 = new ProductBatchesStockEntity();
        productBatchesStockEntity2.setCostPerUnit(10.0d);
        productBatchesStockEntity2.setId(1L);
        productBatchesStockEntity2.setProduct(product2);
        productBatchesStockEntity2.setProductionUnit(productionUnit2);
        productBatchesStockEntity2.setQuantity(1);
        productBatchesStockEntity2.setReferenceKey("Reference Key");
        when(productBatchesStockRepository.save(Mockito.<ProductBatchesStockEntity>any()))
                .thenReturn(productBatchesStockEntity2);
        when(productBatchesStockRepository.findByReferenceKey(Mockito.<String>any())).thenReturn(productBatchesStockEntity);

        ProductEntity product3 = new ProductEntity();
        product3.setCategory("Category");
        product3.setId(1L);
        product3.setName("Name");
        product3.setProductCode("Product Code");
        product3.setProductions(new ArrayList<>());
        product3.setUnitPrice(new ArrayList<>());

        UnitEntity productionUnit3 = new UnitEntity();
        productionUnit3.setCf(10.0d);
        productionUnit3.setId(1L);
        productionUnit3.setIngredientEntity(new ArrayList<>());
        productionUnit3.setName("Name");
        productionUnit3.setProductBatchesStockEntity(new ArrayList<>());
        productionUnit3.setProductionEntity(new ArrayList<>());
        productionUnit3.setStockEntity(new ArrayList<>());

        ProductionEntity productionEntity = new ProductionEntity();
        productionEntity.setDamagedProduct(new ArrayList<>());
        productionEntity.setDateOfProduction(mock(Date.class));
        productionEntity.setId(1L);
        productionEntity.setProduct(product3);
        productionEntity.setProductionQuantity(1);
        productionEntity.setProductionUnit(productionUnit3);
        productionEntity.setRecipe(new ArrayList<>());
        productionEntity.setUnitCost(10.0d);
        Optional<ProductionEntity> ofResult = Optional.of(productionEntity);

        ProductEntity product4 = new ProductEntity();
        product4.setCategory("Category");
        product4.setId(1L);
        product4.setName("Name");
        product4.setProductCode("Product Code");
        product4.setProductions(new ArrayList<>());
        product4.setUnitPrice(new ArrayList<>());

        UnitEntity productionUnit4 = new UnitEntity();
        productionUnit4.setCf(10.0d);
        productionUnit4.setId(1L);
        productionUnit4.setIngredientEntity(new ArrayList<>());
        productionUnit4.setName("Name");
        productionUnit4.setProductBatchesStockEntity(new ArrayList<>());
        productionUnit4.setProductionEntity(new ArrayList<>());
        productionUnit4.setStockEntity(new ArrayList<>());

        ProductionEntity productionEntity2 = new ProductionEntity();
        productionEntity2.setDamagedProduct(new ArrayList<>());
        productionEntity2.setDateOfProduction(mock(Date.class));
        productionEntity2.setId(1L);
        productionEntity2.setProduct(product4);
        productionEntity2.setProductionQuantity(1);
        productionEntity2.setProductionUnit(productionUnit4);
        productionEntity2.setRecipe(new ArrayList<>());
        productionEntity2.setUnitCost(10.0d);
        when(productionRepository.save(Mockito.<ProductionEntity>any())).thenReturn(productionEntity2);
        when(productionRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        doNothing().when(stockService)
                .updateStockWhenProductChanged(Mockito.<ProductEntity>any(), Mockito.<UnitEntity>any(),
                        Mockito.<ProductEntity>any(), Mockito.<UnitEntity>any(), anyInt(), anyInt());

        ProductEntity product5 = new ProductEntity();
        product5.setCategory("Category");
        product5.setId(1L);
        product5.setName("Name");
        product5.setProductCode("Product Code");
        product5.setProductions(new ArrayList<>());
        product5.setUnitPrice(new ArrayList<>());

        UnitEntity productionUnit5 = new UnitEntity();
        productionUnit5.setCf(10.0d);
        productionUnit5.setId(1L);
        productionUnit5.setIngredientEntity(new ArrayList<>());
        productionUnit5.setName("Name");
        productionUnit5.setProductBatchesStockEntity(new ArrayList<>());
        productionUnit5.setProductionEntity(new ArrayList<>());
        productionUnit5.setStockEntity(new ArrayList<>());

        ProductionEntity updatedProduction = new ProductionEntity();
        updatedProduction.setDamagedProduct(new ArrayList<>());
        updatedProduction.setDateOfProduction(mock(Date.class));
        updatedProduction.setId(1L);
        updatedProduction.setProduct(product5);
        updatedProduction.setProductionQuantity(1);
        updatedProduction.setProductionUnit(productionUnit5);
        updatedProduction.setRecipe(new ArrayList<>());
        updatedProduction.setUnitCost(10.0d);

        // Act
        productionServiceImp.updateProduction(updatedProduction);

        // Assert
        verify(productBatchesStockRepository).findByReferenceKey(eq("Production-1"));
        verify(stockService).updateStockWhenProductChanged(isA(ProductEntity.class), isA(UnitEntity.class),
                isA(ProductEntity.class), isA(UnitEntity.class), eq(1), eq(1));
        verify(productionRepository).findById(eq(1L));
        verify(productBatchesStockRepository).save(isA(ProductBatchesStockEntity.class));
        verify(productionRepository).save(isA(ProductionEntity.class));
    }

    /**
     * Method under test: {@link ProductionServiceImp#deleteProduction(long)}
     */
    @Test
    void testDeleteProduction2() {
        // Arrange
        Optional<ProductionEntity> emptyResult = Optional.empty();
        when(productionRepository.findById(Mockito.<Long>any())).thenReturn(emptyResult);
        doNothing().when(productionRepository).deleteById(Mockito.<Long>any());

        // Act
        productionServiceImp.deleteProduction(1L);

        // Assert that nothing has changed
        verify(productionRepository).deleteById(eq(1L));
        verify(productionRepository).findById(eq(1L));
    }
}