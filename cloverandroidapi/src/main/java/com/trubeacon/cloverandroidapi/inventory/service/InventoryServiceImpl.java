package com.trubeacon.cloverandroidapi.inventory.service;

import java.util.List;

import com.trubeacon.cloverandroidapi.client.RESTException;
import com.trubeacon.cloverandroidapi.client.error.Error;
import com.trubeacon.cloverandroidapi.common.WrappedList;
import com.trubeacon.cloverandroidapi.inventory.Attribute;
import com.trubeacon.cloverandroidapi.inventory.Category;
import com.trubeacon.cloverandroidapi.inventory.CategoryItemAssociation;
import com.trubeacon.cloverandroidapi.inventory.Item;
import com.trubeacon.cloverandroidapi.inventory.ItemGroup;
import com.trubeacon.cloverandroidapi.inventory.ItemSummary;
import com.trubeacon.cloverandroidapi.inventory.ItemTags;
import com.trubeacon.cloverandroidapi.inventory.ModifierGroupItemAssociation;
import com.trubeacon.cloverandroidapi.inventory.ItemStock;
import com.trubeacon.cloverandroidapi.inventory.Modifier;
import com.trubeacon.cloverandroidapi.inventory.ModifierGroup;
import com.trubeacon.cloverandroidapi.inventory.Option;
import com.trubeacon.cloverandroidapi.inventory.OptionItemAssociation;
import com.trubeacon.cloverandroidapi.inventory.Tag;
import com.trubeacon.cloverandroidapi.inventory.TagItemAssociation;
import com.trubeacon.cloverandroidapi.inventory.TaxRateItemAssociation;
import com.trubeacon.cloverandroidapi.inventory.service.CreateAttribute.CreateAttributeCallback;
import com.trubeacon.cloverandroidapi.inventory.service.CreateAttributeOption.CreateAttributeOptionCallback;
import com.trubeacon.cloverandroidapi.inventory.service.CreateCategory.CreateCategoryCallback;
import com.trubeacon.cloverandroidapi.inventory.service.CreateCategoryItemAssociations.CreateCategoryItemAssociationsCallback;
import com.trubeacon.cloverandroidapi.inventory.service.CreateItem.CreateItemCallback;
import com.trubeacon.cloverandroidapi.inventory.service.CreateItemGroup.CreateItemGroupCallback;
import com.trubeacon.cloverandroidapi.inventory.service.CreateModifierGroup.CreateModifierGroupCallback;
import com.trubeacon.cloverandroidapi.inventory.service.CreateModifierGroupItemAssociations.CreateModifierGroupItemAssociationsCallback;
import com.trubeacon.cloverandroidapi.inventory.service.CreateModifierGroupModifier.CreateModifierGroupModifierCallback;
import com.trubeacon.cloverandroidapi.inventory.service.CreateOptionItemAssociations.CreateOptionItemAssociationsCallback;
import com.trubeacon.cloverandroidapi.inventory.service.CreateTag.CreateTagCallback;
import com.trubeacon.cloverandroidapi.inventory.service.CreateTagItemAssociations.CreateTagItemAssociationsCallback;
import com.trubeacon.cloverandroidapi.inventory.service.CreateTaxRateItemAssociations.CreateTaxRateItemAssociationsCallback;
import com.trubeacon.cloverandroidapi.inventory.service.DeleteAttribute.DeleteAttributeCallback;
import com.trubeacon.cloverandroidapi.inventory.service.DeleteAttributeOption.DeleteAttributeOptionCallback;
import com.trubeacon.cloverandroidapi.inventory.service.DeleteCategory.DeleteCategoryCallback;
import com.trubeacon.cloverandroidapi.inventory.service.DeleteCategoryItemAssociations.DeleteCategoryItemAssociationsCallback;
import com.trubeacon.cloverandroidapi.inventory.service.DeleteItem.DeleteItemCallback;
import com.trubeacon.cloverandroidapi.inventory.service.DeleteItemGroup.DeleteItemGroupCallback;
import com.trubeacon.cloverandroidapi.inventory.service.DeleteItemStock.DeleteItemStockCallback;
import com.trubeacon.cloverandroidapi.inventory.service.DeleteModifierGroup.DeleteModifierGroupCallback;
import com.trubeacon.cloverandroidapi.inventory.service.DeleteModifierGroupItemAssociations.DeleteModifierGroupItemAssociationsCallback;
import com.trubeacon.cloverandroidapi.inventory.service.DeleteModifierGroupModifier.DeleteModifierGroupModifierCallback;
import com.trubeacon.cloverandroidapi.inventory.service.DeleteOptionItemAssociations.DeleteOptionItemAssociationsCallback;
import com.trubeacon.cloverandroidapi.inventory.service.DeleteTag.DeleteTagCallback;
import com.trubeacon.cloverandroidapi.inventory.service.DeleteTagItemAssociations.DeleteTagItemAssociationsCallback;
import com.trubeacon.cloverandroidapi.inventory.service.DeleteTaxRateItemAssociations.DeleteTaxRateItemAssociationsCallback;
import com.trubeacon.cloverandroidapi.inventory.service.GetAttribute.GetAttributeCallback;
import com.trubeacon.cloverandroidapi.inventory.service.GetAttributeOption.GetAttributeOptionCallback;
import com.trubeacon.cloverandroidapi.inventory.service.GetAttributeOptions.GetAttributeOptionsCallback;
import com.trubeacon.cloverandroidapi.inventory.service.GetAttributes.GetAttributesCallback;
import com.trubeacon.cloverandroidapi.inventory.service.GetCategories.GetCategoriesCallback;
import com.trubeacon.cloverandroidapi.inventory.service.GetCategory.GetCategoryCallback;
import com.trubeacon.cloverandroidapi.inventory.service.GetCategoryItems.GetCategoryItemsCallback;
import com.trubeacon.cloverandroidapi.inventory.service.GetItem.GetItemCallback;
import com.trubeacon.cloverandroidapi.inventory.service.GetItemCategories.GetItemCategoriesCallback;
import com.trubeacon.cloverandroidapi.inventory.service.GetItemGroup.GetItemGroupCallback;
import com.trubeacon.cloverandroidapi.inventory.service.GetItemGroups.GetItemGroupsCallback;
import com.trubeacon.cloverandroidapi.inventory.service.GetItemStock.GetItemStockCallback;
import com.trubeacon.cloverandroidapi.inventory.service.GetItemStocks.GetItemStocksCallback;
import com.trubeacon.cloverandroidapi.inventory.service.GetItemSummary.GetItemSummaryCallback;
import com.trubeacon.cloverandroidapi.inventory.service.GetItemTags.GetItemTagsCallback;
import com.trubeacon.cloverandroidapi.inventory.service.GetItems.GetItemsCallback;
import com.trubeacon.cloverandroidapi.inventory.service.GetModifierGroup.GetModifierGroupCallback;
import com.trubeacon.cloverandroidapi.inventory.service.GetModifierGroupModifier.GetModifierGroupModifierCallback;
import com.trubeacon.cloverandroidapi.inventory.service.GetModifierGroupModifiers.GetModifierGroupModifiersCallback;
import com.trubeacon.cloverandroidapi.inventory.service.GetModifierGroups.GetModifierGroupsCallback;
import com.trubeacon.cloverandroidapi.inventory.service.GetModifiers.GetModifiersCallback;
import com.trubeacon.cloverandroidapi.inventory.service.GetOptions.GetOptionsCallback;
import com.trubeacon.cloverandroidapi.inventory.service.GetTag.GetTagCallback;
import com.trubeacon.cloverandroidapi.inventory.service.GetTagItems.GetTagItemsCallback;
import com.trubeacon.cloverandroidapi.inventory.service.GetTags.GetTagsCallback;
import com.trubeacon.cloverandroidapi.inventory.service.GetTaxRateItems.GetTaxRateItemsCallback;
import com.trubeacon.cloverandroidapi.inventory.service.UpdateAttribute.UpdateAttributeCallback;
import com.trubeacon.cloverandroidapi.inventory.service.UpdateAttributeOption.UpdateAttributeOptionCallback;
import com.trubeacon.cloverandroidapi.inventory.service.UpdateCategory.UpdateCategoryCallback;
import com.trubeacon.cloverandroidapi.inventory.service.UpdateItem.UpdateItemCallback;
import com.trubeacon.cloverandroidapi.inventory.service.UpdateItemGroup.UpdateItemGroupCallback;
import com.trubeacon.cloverandroidapi.inventory.service.UpdateItemStock.UpdateItemStockCallback;
import com.trubeacon.cloverandroidapi.inventory.service.UpdateModifierGroup.UpdateModifierGroupCallback;
import com.trubeacon.cloverandroidapi.inventory.service.UpdateModifierGroupModifier.UpdateModifierGroupModifierCallback;
import com.trubeacon.cloverandroidapi.inventory.service.UpdateTag.UpdateTagCallback;
import com.trubeacon.cloverandroidapi.merchant.TaxRate;
import com.trubeacon.cloverandroidapi.merchant.service.CreateTaxRate;
import com.trubeacon.cloverandroidapi.merchant.service.DeleteTaxRate;
import com.trubeacon.cloverandroidapi.merchant.service.GetTaxRate;
import com.trubeacon.cloverandroidapi.merchant.service.GetTaxRates;
import com.trubeacon.cloverandroidapi.merchant.service.UpdateTaxRate;

public class InventoryServiceImpl implements InventoryService,
											GetItemsCallback,
											CreateItemCallback,
											GetItemCallback, 
											UpdateItemCallback,
											DeleteItemCallback,
											GetItemStocksCallback,
											GetItemStockCallback,
											UpdateItemStockCallback,
											DeleteItemStockCallback,
											GetItemGroupsCallback,
											CreateItemGroupCallback,
											GetItemGroupCallback,
											UpdateItemGroupCallback,
											DeleteItemGroupCallback,
											GetTagsCallback,
											CreateTagCallback,
											GetTagCallback,
											UpdateTagCallback,
											DeleteTagCallback,
											GetItemTagsCallback,
											GetTagItemsCallback, 
											CreateTagItemAssociationsCallback,
											DeleteTagItemAssociationsCallback,
											GetCategoriesCallback,
											CreateCategoryCallback,
											GetCategoryCallback,
											UpdateCategoryCallback,
											DeleteCategoryCallback,
											GetCategoryItemsCallback,
											GetItemCategoriesCallback,
											CreateCategoryItemAssociationsCallback,
											DeleteCategoryItemAssociationsCallback,
											CreateTaxRateItemAssociationsCallback,
											DeleteTaxRateItemAssociationsCallback,
											GetTaxRateItemsCallback,
											GetModifierGroupsCallback,
											GetModifierGroupCallback,
											CreateModifierGroupCallback,
											UpdateModifierGroupCallback,
											DeleteModifierGroupCallback,
											CreateModifierGroupItemAssociationsCallback,
											DeleteModifierGroupItemAssociationsCallback,
											GetModifiersCallback,
											GetModifierGroupModifiersCallback,
											CreateModifierGroupModifierCallback,
											GetModifierGroupModifierCallback,
											UpdateModifierGroupModifierCallback,
											DeleteModifierGroupModifierCallback,
											GetAttributesCallback,
											CreateAttributeCallback,
											GetAttributeCallback,
											UpdateAttributeCallback,
											DeleteAttributeCallback,
											GetOptionsCallback,
											GetAttributeOptionsCallback,
											CreateAttributeOptionCallback,
											GetAttributeOptionCallback,
											UpdateAttributeOptionCallback,
											DeleteAttributeOptionCallback,
											CreateOptionItemAssociationsCallback,
											DeleteOptionItemAssociationsCallback,
											GetItemSummaryCallback {

	private GetItemsCallback getItemsCallback;
	private CreateItemCallback createItemCallback;
	private GetItemCallback getItemCallback;
	private UpdateItemCallback updateItemCallback;
	private DeleteItemCallback deleteItemCallback;
	private GetItemStocksCallback getItemStocksCallback;
	private GetItemStockCallback getItemStockCallback;
	private UpdateItemStockCallback updateItemStockCallback;
	private DeleteItemStockCallback deleteItemStockCallback;
	private GetItemGroupsCallback getItemGroupsCallback;
	private CreateItemGroupCallback createItemGroupCallback;
	private GetItemGroupCallback getItemGroupCallback;
	private UpdateItemGroupCallback updateItemGroupCallback;
	private DeleteItemGroupCallback deleteItemGroupCallback;
	private GetTagsCallback getTagsCallback;
	private CreateTagCallback createTagCallback;
	private GetTagCallback getTagCallback;
	private UpdateTagCallback updateTagCallback;
	private DeleteTagCallback deleteTagCallback;
	private GetItemTagsCallback getItemTagsCallback;
	private GetTagItemsCallback getTagItemsCallback;
	private CreateTagItemAssociationsCallback createTagItemAssociationsCallback;
	private DeleteTagItemAssociationsCallback deleteTagItemAssociationsCallback;
	private GetCategoriesCallback getCategoriesCallback;
	private CreateCategoryCallback createCategoryCallback;
	private GetCategoryCallback getCategoryCallback;
	private UpdateCategoryCallback updateCategoryCallback;
	private DeleteCategoryCallback deleteCategoryCallback;
	private GetCategoryItemsCallback getCategoryItemsCallback;
	private GetItemCategoriesCallback getItemCategoriesCallback;
	private CreateCategoryItemAssociationsCallback createCategoryItemAssociationsCallback;
	private DeleteCategoryItemAssociationsCallback deleteCategoryItemAssociationsCallback;
	private CreateTaxRateItemAssociationsCallback createTaxRateItemAssociationsCallback;
	private DeleteTaxRateItemAssociationsCallback deleteTaxRateItemAssociationsCallback;
	private GetTaxRateItemsCallback getTaxRateItemsCallback;
	private GetModifierGroupsCallback getModifierGroupsCallback;
	private GetModifierGroupCallback getModifierGroupCallback;
	private CreateModifierGroupCallback createModifierGroupCallback;
	private UpdateModifierGroupCallback updateModifierGroupCallback;
	private DeleteModifierGroupCallback deleteModifierGroupCallback;
	private CreateModifierGroupItemAssociationsCallback createModifierGroupItemAssociationsCallback;
	private DeleteModifierGroupItemAssociationsCallback deleteModifierGroupItemAssociationsCallback;
	private GetModifiersCallback getModifiersCallback;
	private GetModifierGroupModifiersCallback getModifierGroupModifiersCallback;
	private CreateModifierGroupModifierCallback createModifierGroupModifierCallback;
	private GetModifierGroupModifierCallback getModifierGroupModifierCallback;
	private UpdateModifierGroupModifierCallback updateModifierGroupModifierCallback;
	private DeleteModifierGroupModifierCallback deleteModifierGroupModifierCallback;
	private GetAttributesCallback getAttributesCallback;
	private CreateAttributeCallback createAttributeCallback;
	private GetAttributeCallback getAttributeCallback;
	private UpdateAttributeCallback updateAttributeCallback;
	private DeleteAttributeCallback deleteAttributeCallback;
	private GetOptionsCallback getOptionsCallback;
	private GetAttributeOptionsCallback getAttributeOptionsCallback;
	private CreateAttributeOptionCallback createAttributeOptionCallback;
	private GetAttributeOptionCallback getAttributeOptionCallback;
	private UpdateAttributeOptionCallback updateAttributeOptionCallback;
	private DeleteAttributeOptionCallback deleteAttributeOptionCallback;
	private CreateOptionItemAssociationsCallback createOptionItemAssociationsCallback;
	private DeleteOptionItemAssociationsCallback deleteOptionItemAssociationsCallback;
	private GetItemSummaryCallback getItemSummaryCallback;
	
	@Override
    public List<Item> getItems(String mId, String token, Object... params) throws RESTException {
	    return new GetItems(mId, token, params).execute();
    }

	@Override
    public Item createItem(String mId, String token, Item item) throws RESTException {
	    return new CreateItem(mId, token, item).execute();
    }

	@Override
    public Item getItem(String mId, String token, String itemId) throws RESTException {
	    return new GetItem(mId, token, itemId).execute();
    }

	@Override
    public Item updateItem(String mId, String token, String itemId, Item item) throws RESTException {
	    return new UpdateItem(mId, token, itemId, item).execute();
    }

	@Override
    public Item deleteItem(String mId, String token, String itemId) throws RESTException {
	    return new DeleteItem(mId, token, itemId).execute();
    }

	@Override
    public List<ItemStock> getItemStocks(String mId, String token, Object... params) throws RESTException {
	    return new GetItemStocks(mId, token, params).execute();
    }

	@Override
    public ItemStock getItemStock(String mId, String token, String itemId) throws RESTException {
	    return new GetItemStock(mId, token, itemId).execute();
    }

	@Override
    public ItemStock updateItemStock(String mId, String token, String itemId, ItemStock itemStock) throws RESTException {
	    return new UpdateItemStock(mId, token, itemId, itemStock).execute();
    }

	@Override
    public ItemStock deleteItemStock(String mId, String token, String itemId) throws RESTException {
	    return new DeleteItemStock(mId, token, itemId).execute();
    }

	@Override
    public List<ItemGroup> getItemGroups(String mId, String token, Object... params) throws RESTException {
	    return new GetItemGroups(mId, token, params).execute();
    }

	@Override
    public ItemGroup createItemGroup(String mId, String token, ItemGroup itemGroup) throws RESTException {
	    return new CreateItemGroup(mId, token, itemGroup).execute();
    }

	@Override
    public ItemGroup getItemGroup(String mId, String token, String itemGroupId) throws RESTException {
	    return new GetItemGroup(mId, token, itemGroupId).execute();
    }

	@Override
    public ItemGroup updateItemGroup(String mId, String token, String itemGroupId, ItemGroup itemGroup) throws RESTException {
	    return new UpdateItemGroup(mId, token, itemGroupId, itemGroup).execute();
    }

	@Override
    public ItemGroup deleteItemGroup(String mId, String token, String itemGroupId) throws RESTException {
	    return new DeleteItemGroup(mId, token, itemGroupId).execute();
    }

	@Override
    public List<Tag> getTags(String mId, String token, Object... params) throws RESTException {
	    return new GetTags(mId, token, params).execute();
    }

	@Override
    public Tag createTag(String mId, String token, Tag tag) throws RESTException {
	    return new CreateTag(mId, token, tag).execute();
    }

	@Override
    public Tag getTag(String mId, String token, String tagId) throws RESTException {
	    return new GetTag(mId, token, tagId).execute();
    }

	@Override
    public Tag updateTag(String mId, String token, String tagId, Tag tag) throws RESTException {
	    return new UpdateTag(mId, token, tagId, tag).execute();
    }

	@Override
    public Tag deleteTag(String mId, String token, String tagId) throws RESTException {
	    return new DeleteTag(mId, token, tagId).execute();
    }

	@Override
    public List<Tag> getItemTags(String mId, String token, String itemId, Object... params) throws RESTException {
	    return new GetItemTags(mId, token, itemId, params).execute();
    }

	@Override
    public List<Item> getTagItems(String mId, String token, String tagId, Object... params) throws RESTException {
	    return new GetTagItems(mId, token, tagId, params).execute();
    }

	@Override
    public List<TagItemAssociation> createTagItemAssociations(String mId, String token, TagItemAssociation... associations) throws RESTException {
	    return new CreateTagItemAssociations(mId, token, associations).execute();
    }
	
	@Override
    public List<TagItemAssociation> deleteTagItemAssociations(String mId, String token, TagItemAssociation... associations) throws RESTException {
	    return new DeleteTagItemAssociations(mId, token, associations).execute();
    }

	@Override
    public List<Category> getCategories(String mId, String token, Object... params) throws RESTException {
	    return new GetCategories(mId, token, params).execute();
    }

	@Override
    public Category createCategory(String mId, String token, Category category) throws RESTException {
	    return new CreateCategory(mId, token, category).execute();
    }

	@Override
    public Category getCategory(String mId, String token, String catId) throws RESTException {
	    return new GetCategory(mId, token, catId).execute();
    }

	@Override
    public Category updateCategory(String mId, String token, String catId, Category update) throws RESTException {
	    return new UpdateCategory(mId, token, catId, update).execute();
    }

	@Override
    public Category deleteCategory(String mId, String token, String catId) throws RESTException {
	    return new DeleteCategory(mId, token, catId).execute();
    }

	@Override
    public List<Item> getCategoryItems(String mId, String token, String catId, Object... params) throws RESTException {
	    return new GetCategoryItems(mId, token, catId, params).execute();
    }

	@Override
    public List<Category> getItemCategories(String mId, String token, String itemId, Object... params) throws RESTException {
	    return new GetItemCategories(mId, token, itemId, params).execute();
    }

	@Override
    public List<CategoryItemAssociation> createCategoryItemAssociations(String mId, String token, CategoryItemAssociation... associations) throws RESTException {
	    return new CreateCategoryItemAssociations(mId, token, associations).execute();
    }
	
	@Override
    public List<CategoryItemAssociation> deleteCategoryItemAssociations(String mId, String token, CategoryItemAssociation... associations) throws RESTException {
	    return new DeleteCategoryItemAssociations(mId, token, associations).execute();
    }

	@Override
    public List<TaxRateItemAssociation> createTaxRateItemAssociations(String mId, String token, TaxRateItemAssociation... associations) throws RESTException {
	    return new CreateTaxRateItemAssociations(mId, token, associations).execute();
    }
	
	@Override
    public List<TaxRateItemAssociation> deleteTaxRateItemAssociations(String mId, String token, TaxRateItemAssociation... associations) throws RESTException {
	    return new DeleteTaxRateItemAssociations(mId, token, associations).execute();
    }	

	@Override
    public List<Item> getTaxRateItems(String mId, String token, String taxId, Object... params) throws RESTException {
	    return new GetTaxRateItems(mId, token, taxId, params).execute();
    }

	@Override
    public List<ModifierGroup> getModifierGroups(String mId, String token, Object... params) throws RESTException {
	    return new GetModifierGroups(mId, token, params).execute();
    }

	@Override
    public ModifierGroup getModifierGroup(String mId, String token, String modGroupId) throws RESTException {
	    return new GetModifierGroup(mId, token, modGroupId).execute();
    }

	@Override
    public ModifierGroup createModifierGroup(String mId, String token, ModifierGroup modifierGroup) throws RESTException {
	    return new CreateModifierGroup(mId, token, modifierGroup).execute();
    }

	@Override
    public ModifierGroup updateModifierGroup(String mId, String token, String modGroupId, ModifierGroup update) throws RESTException {
	    return new UpdateModifierGroup(mId, token, modGroupId, update).execute();
    }

	@Override
    public ModifierGroup deleteModifierGroup(String mId, String token, String modGroupId) throws RESTException {
	    return new DeleteModifierGroup(mId, token, modGroupId).execute();
    }

	@Override
    public List<ModifierGroupItemAssociation> createModifierGroupItemAssociations(String mId, String token, ModifierGroupItemAssociation... associations) throws RESTException {
	    return new CreateModifierGroupItemAssociations(mId, token, associations).execute();
    }
	
	@Override
    public List<ModifierGroupItemAssociation> deleteModifierGroupItemAssociations(String mId, String token, ModifierGroupItemAssociation... associations) throws RESTException {
	    return new DeleteModifierGroupItemAssociations(mId, token, associations).execute();
    }

	@Override
    public List<Modifier> getModifiers(String mId, String token, Object... params) throws RESTException {
	    return new GetModifiers(mId, token, params).execute();
    }

	@Override
    public List<Modifier> getModifierGroupModifiers(String mId, String token, String modGroupId, Object... params) throws RESTException {
	    return new GetModifierGroupModifiers(mId, token, modGroupId, params).execute();
    }

	@Override
    public Modifier createModifierGroupModifier(String mId, String token, String modGroupId, Modifier modifier) throws RESTException {
	    return new CreateModifierGroupModifier(mId, token, modGroupId, modifier).execute();
    }

	@Override
    public Modifier getModifierGroupModifier(String mId, String token, String modGroupId, String modId) throws RESTException {
	    return new GetModifierGroupModifier(mId, token, modGroupId, modId).execute();
    }

	@Override
    public Modifier updateModifierGroupModifier(String mId, String token, String modGroupId, String modId, Modifier update) throws RESTException {
	    return new UpdateModifierGroupModifier(mId, token, modGroupId, modId, update).execute();
    }

	@Override
    public Modifier deleteModifierGroupModifier(String mId, String token, String modGroupId, String modId) throws RESTException {
	    return new DeleteModifierGroupModifier(mId, token, modGroupId, modId).execute();
    }

	@Override
    public List<Attribute> getAttributes(String mId, String token, Object... params) throws RESTException {
	    return new GetAttributes(mId, token, params).execute();
    }

	@Override
    public Attribute createAttribute(String mId, String token, Attribute attribute) throws RESTException {
	    return new CreateAttribute(mId, token, attribute).execute();
    }

	@Override
    public Attribute getAttribute(String mId, String token, String attributeId) throws RESTException {
	    return new GetAttribute(mId, token, attributeId).execute();
    }

	@Override
    public Attribute updateAttribute(String mId, String token, String attributeId, Attribute attribute) throws RESTException {
	    return new UpdateAttribute(mId, token, attributeId, attribute).execute();
    }

	@Override
    public Attribute deleteAttribute(String mId, String token, String attributeId) throws RESTException {
	    return new DeleteAttribute(mId, token, attributeId).execute();
    }

	@Override
    public List<Option> getOptions(String mId, String token, Object... params) throws RESTException {
	    return new GetOptions(mId, token, params).execute();
    }

	@Override
    public List<Option> getAttributeOptions(String mId, String token, String attributeId, Object... params) throws RESTException {
	    return new GetAttributeOptions(mId, token, attributeId, params).execute();
    }

	@Override
    public Option createAttributeOption(String mId, String token, String attributeId, Option option) throws RESTException {
	    return new CreateAttributeOption(mId, token, attributeId, option).execute();
    }

	@Override
    public Option getAttributeOption(String mId, String token, String attributeId, String optionId) throws RESTException {
	    return new GetAttributeOption(mId, token, attributeId, optionId).execute();
    }

	@Override
    public Option updateAttributeOption(String mId, String token, String attributeId, String optionId, Option update) throws RESTException {
	    return new UpdateAttributeOption(mId, token, attributeId, optionId, update).execute();
    }

	@Override
    public Option deleteAttributeOption(String mId, String token, String attributeId, String optionId) throws RESTException {
	    return new DeleteAttributeOption(mId, token, attributeId, optionId).execute();
    }

	@Override
    public List<OptionItemAssociation> createOptionItemAssociations(String mId, String token, OptionItemAssociation... associations) throws RESTException {
	    return new CreateOptionItemAssociations(mId, token, associations).execute();
    }
	
	@Override
    public List<OptionItemAssociation> deleteOptionItemAssociations(String mId, String token, OptionItemAssociation... associations) throws RESTException {
	    return new DeleteOptionItemAssociations(mId, token, associations).execute();
    }

	@Override
    public ItemSummary getItemSummary(String mId, String token,
            Object... params) throws RESTException {
	    return new GetItemSummary(mId, token, params).execute();
    }

	@Override
    public void getItems(String mId, String token, GetItemsCallback callback,
            Object... params) {
	    this.getItemsCallback = callback;
	    new GetItems(mId, token, params).execute(this);
    }

	@Override
    public void createItem(String mId, String token, Item item,
            CreateItemCallback callback) {
	    this.createItemCallback = callback;
	    new CreateItem(mId, token, item).execute(this);
    }

	@Override
    public void getItem(String mId, String token, String itemId,
            GetItemCallback callback) {
	    this.getItemCallback = callback;
	    new GetItem(mId, token, itemId).execute(this);
    }

	@Override
    public void updateItem(String mId, String token, String itemId, Item item,
            UpdateItemCallback callback) {
	    this.updateItemCallback = callback;
	    new UpdateItem(mId, token, itemId, item).execute(this);
    }

	@Override
    public void deleteItem(String mId, String token, String itemId,
            DeleteItemCallback callback) {
	    this.deleteItemCallback = callback;
	    new DeleteItem(mId, token, itemId).execute(this);
    }

	@Override
    public void getItemStocks(String mId, String token,
            GetItemStocksCallback callback, Object... params) {
	    this.getItemStocksCallback = callback;
	    new GetItemStocks(mId, token, params).execute(this);
    }

	@Override
    public void getItemStock(String mId, String token, String itemId,
            GetItemStockCallback callback) {
	    this.getItemStockCallback = callback;
	    new GetItemStock(mId, token, itemId).execute(this);
    }

	@Override
    public void updateItemStock(String mId, String token, String itemId,
            ItemStock itemStock, UpdateItemStockCallback callback) {
	    this.updateItemStockCallback = callback;
	    new UpdateItemStock(mId, token, itemId, itemStock).execute(this);
    }

	@Override
    public void deleteItemStock(String mId, String token, String itemId,
            DeleteItemStockCallback callback) {
	    this.deleteItemStockCallback = callback;
	    new DeleteItemStock(mId, token, itemId).execute(this);
    }

	@Override
    public void getItemGroups(String mId, String token,
            GetItemGroupsCallback callback, Object... params) {
	    this.getItemGroupsCallback = callback;
	    new GetItemGroups(mId, token, params).execute(this);
    }

	@Override
    public void createItemGroup(String mId, String token, ItemGroup itemGroup,
            CreateItemGroupCallback callback) {
	    this.createItemGroupCallback = callback;
	    new CreateItemGroup(mId, token, itemGroup).execute(this);
    }

	@Override
    public void getItemGroup(String mId, String token, String itemGroupId,
            GetItemGroupCallback callback) {
	    this.getItemGroupCallback = callback;
	    new GetItemGroup(mId, token, itemGroupId).execute(this);
    }

	@Override
    public void updateItemGroup(String mId, String token, String itemGroupId,
            ItemGroup itemGroup, UpdateItemGroupCallback callback) {
	    this.updateItemGroupCallback = callback;
	    new UpdateItemGroup(mId, token, itemGroupId, itemGroup).execute(this);
    }

	@Override
    public void deleteItemGroup(String mId, String token, String itemGroupId,
            DeleteItemGroupCallback callback) {
	    this.deleteItemGroupCallback = callback;
	    new DeleteItemGroup(mId, token, itemGroupId).execute(this);
    }

	@Override
    public void getTags(String mId, String token, GetTagsCallback callback,
            Object... params) {
	    this.getTagsCallback = callback;
	    new GetTags(mId, token, params).execute(this);
    }

	@Override
    public void createTag(String mId, String token, Tag tag,
            CreateTagCallback callback) {
	    this.createTagCallback = callback;
	    new CreateTag(mId, token, tag).execute(this);
    }

	@Override
    public void getTag(String mId, String token, String tagId,
            GetTagCallback callback) {
	    this.getTagCallback = callback;
	    new GetTag(mId, token, tagId).execute(this);
    }

	@Override
    public void updateTag(String mId, String token, String tagId, Tag tag,
            UpdateTagCallback callback) {
	    this.updateTagCallback = callback;
	    new UpdateTag(mId, token, tagId, tag).execute(this);
    }

	@Override
    public void deleteTag(String mId, String token, String tagId,
            DeleteTagCallback callback) {
	    this.deleteTagCallback = callback;
	    new DeleteTag(mId, token, tagId).execute(this);
    }

	@Override
    public void getItemTags(String mId, String token, String itemId,
            GetItemTagsCallback callback, Object... params) {
	    this.getItemTagsCallback = callback;
	    new GetItemTags(mId, token, itemId, params).execute(this);
    }

	@Override
    public void getTagItems(String mId, String token, String tagId,
            GetTagItemsCallback callback, Object... params) {
	    this.getTagItemsCallback = callback;
	    new GetTagItems(mId, token, tagId, params).execute(this);
    }

	@Override
    public void createTagItemAssociations(String mId, String token,
            CreateTagItemAssociationsCallback callback,
            TagItemAssociation... associations) {
	    this.createTagItemAssociationsCallback = callback;
	    new CreateTagItemAssociations(mId, token, associations).execute(this);
    }

	@Override
    public void deleteTagItemAssociations(String mId, String token,
            DeleteTagItemAssociationsCallback callback,
            TagItemAssociation... associations) {
	    this.deleteTagItemAssociationsCallback = callback;
	    new DeleteTagItemAssociations(mId, token, associations).execute(this);
    }

	@Override
    public void getCategories(String mId, String token,
            GetCategoriesCallback callback, Object... params) {
	    this.getCategoriesCallback = callback;
	    new GetCategories(mId, token, params).execute(this);
    }

	@Override
    public void createCategory(String mId, String token, Category category,
            CreateCategoryCallback callback) {
	    this.createCategoryCallback = callback;
	    new CreateCategory(mId, token, category).execute(this);
    }

	@Override
    public void getCategory(String mId, String token, String catId,
            GetCategoryCallback callback) {
	    this.getCategoryCallback = callback;
	    new GetCategory(mId, token, catId).execute(this);
    }

	@Override
    public void updateCategory(String mId, String token, String catId,
            Category update, UpdateCategoryCallback callback) {
	    this.updateCategoryCallback = callback;
	    new UpdateCategory(mId, token, catId, update).execute(this);
    }

	@Override
    public void deleteCategory(String mId, String token, String catId,
            DeleteCategoryCallback callback) {
	    this.deleteCategoryCallback = callback;
	    new DeleteCategory(mId, token, catId).execute(this);
    }

	@Override
    public void getCategoryItems(String mId, String token, String catId,
            GetCategoryItemsCallback callback, Object... params) {
	    this.getCategoryItemsCallback = callback;
	    new GetCategoryItems(mId, token, catId, params).execute(this);
    }

	@Override
    public void getItemCategories(String mId, String token, String itemId,
            GetItemCategoriesCallback callback, Object... params) {
	    this.getItemCategoriesCallback = callback;
	    new GetItemCategories(mId, token, itemId, params).execute(this);
    }

	@Override
    public void createCategoryItemAssociations(String mId, String token,
            CreateCategoryItemAssociationsCallback callback,
            CategoryItemAssociation... associations) {
	    this.createCategoryItemAssociationsCallback = callback;
	    new CreateCategoryItemAssociations(mId, token, associations).execute(this);
    }

	@Override
    public void deleteCategoryItemAssociations(String mId, String token,
            DeleteCategoryItemAssociationsCallback callback,
            CategoryItemAssociation... associations) {
	    this.deleteCategoryItemAssociationsCallback = callback;
	    new DeleteCategoryItemAssociations(mId, token, associations).execute(this);
    }

	@Override
    public void createTaxRateItemAssociations(String mId, String token,
            CreateTaxRateItemAssociationsCallback callback,
            TaxRateItemAssociation... associations) {
	    this.createTaxRateItemAssociationsCallback = callback;
	    new CreateTaxRateItemAssociations(mId, token, associations).execute(this);
    }

	@Override
    public void deleteTaxRateItemAssociations(String mId, String token,
            DeleteTaxRateItemAssociationsCallback callback,
            TaxRateItemAssociation... associations) {
	    this.deleteTaxRateItemAssociationsCallback = callback;
	    new DeleteTaxRateItemAssociations(mId, token, associations).execute(this);
    }

	@Override
    public void getTaxRateItems(String mId, String token, String taxId,
            GetTaxRateItemsCallback callback, Object... params) {
	    this.getTaxRateItemsCallback = callback;
	    new GetTaxRateItems(mId, token, taxId, params).execute(this);
    }

	@Override
    public void getModifierGroups(String mId, String token,
            GetModifierGroupsCallback callback, Object... params) {
	    this.getModifierGroupsCallback = callback;
	    new GetModifierGroups(mId, token, params).execute(this);
    }

	@Override
    public void getModifierGroup(String mId, String token, String modGroupId,
            GetModifierGroupCallback callback) {
	    this.getModifierGroupCallback = callback;
	    new GetModifierGroup(mId, token, modGroupId).execute(this);
    }

	@Override
    public void createModifierGroup(String mId, String token,
            ModifierGroup modifierGroup, CreateModifierGroupCallback callback) {
	    this.createModifierGroupCallback = callback;
	    new CreateModifierGroup(mId, token, modifierGroup).execute(this);
    }

	@Override
    public void updateModifierGroup(String mId, String token,
            String modGroupId, ModifierGroup update,
            UpdateModifierGroupCallback callback) {
	    this.updateModifierGroupCallback = callback;
	    new UpdateModifierGroup(mId, token, modGroupId, update).execute(this);
    }

	@Override
    public void deleteModifierGroup(String mId, String token,
            String modGroupId, DeleteModifierGroupCallback callback) {
	    this.deleteModifierGroupCallback = callback;
	    new DeleteModifierGroup(mId, token, modGroupId).execute(this);
    }

	@Override
    public void createModifierGroupItemAssociations(String mId, String token,
            CreateModifierGroupItemAssociationsCallback callback,
            ModifierGroupItemAssociation... associations) {
	    this.createModifierGroupItemAssociationsCallback = callback;
	    new CreateModifierGroupItemAssociations(mId, token, associations).execute(this);
    }

	@Override
    public void deleteModifierGroupItemAssociations(String mId, String token,
            DeleteModifierGroupItemAssociationsCallback callback,
            ModifierGroupItemAssociation... associations) {
	    this.deleteModifierGroupItemAssociationsCallback = callback;
	    new DeleteModifierGroupItemAssociations(mId, token, associations).execute(this);
    }

	@Override
    public void getModifiers(String mId, String token,
            GetModifiersCallback callback, Object... params) {
	    this.getModifiersCallback = callback;
	    new GetModifiers(mId, token, params).execute(this);
    }

	@Override
    public void getModifierGroupModifiers(String mId, String token,
            String modGroupId, GetModifierGroupModifiersCallback callback,
            Object... params) {
	    this.getModifierGroupModifiersCallback = callback;
	    new GetModifierGroupModifiers(mId, token, modGroupId, params).execute(this);
    }

	@Override
    public void createModifierGroupModifier(String mId, String token,
            String modGroupId, Modifier modifier,
            CreateModifierGroupModifierCallback callback) {
	    this.createModifierGroupModifierCallback = callback;
	    new CreateModifierGroupModifier(mId, token, modGroupId, modifier).execute(this);
    }

	@Override
    public void getModifierGroupModifier(String mId, String token,
            String modGroupId, String modId,
            GetModifierGroupModifierCallback callback) {
	    this.getModifierGroupModifierCallback = callback;
	    new GetModifierGroupModifier(mId, token, modGroupId, modId).execute(this);
    }

	@Override
    public void updateModifierGroupModifier(String mId, String token,
            String modGroupId, String modId, Modifier update,
            UpdateModifierGroupModifierCallback callback) {
	    this.updateModifierGroupModifierCallback = callback;
	    new UpdateModifierGroupModifier(mId, token, modGroupId, modId, update).execute(this);
    }

	@Override
    public void deleteModifierGroupModifier(String mId, String token,
            String modGroupId, String modId,
            DeleteModifierGroupModifierCallback callback) {
	    this.deleteModifierGroupModifierCallback = callback;
	    new DeleteModifierGroupModifier(mId, token, modGroupId, modId).execute(this);
    }

	@Override
    public void getAttributes(String mId, String token,
            GetAttributesCallback callback, Object... params) {
	    this.getAttributesCallback = callback;
	    new GetAttributes(mId, token, params).execute(this);
    }

	@Override
    public void createAttribute(String mId, String token, Attribute attribute,
            CreateAttributeCallback callback) {
	    this.createAttributeCallback = callback;
	    new CreateAttribute(mId, token, attribute).execute(this);
    }

	@Override
    public void getAttribute(String mId, String token, String attributeId,
            GetAttributeCallback callback) {
	    this.getAttributeCallback = callback;
	    new GetAttribute(mId, token, attributeId).execute(this);
    }

	@Override
    public void updateAttribute(String mId, String token, String attributeId,
            Attribute attribute, UpdateAttributeCallback callback) {
	    this.updateAttributeCallback = callback;
	    new UpdateAttribute(mId, token, attributeId, attribute).execute(this);
    }

	@Override
    public void deleteAttribute(String mId, String token, String attributeId,
            DeleteAttributeCallback callback) {
	    this.deleteAttributeCallback = callback;
	    new DeleteAttribute(mId, token, attributeId).execute(this);
    }

	@Override
    public void getOptions(String mId, String token,
            GetOptionsCallback callback, Object... params) {
	    this.getOptionsCallback = callback;
	    new GetOptions(mId, token, params).execute(this);
    }

	@Override
    public void getAttributeOptions(String mId, String token,
            String attributeId, GetAttributeOptionsCallback callback,
            Object... params) {
	    this.getAttributeOptionsCallback = callback;
	    new GetAttributeOptions(mId, token, attributeId, params).execute(this);
    }

	@Override
    public void createAttributeOption(String mId, String token,
            String attributeId, Option option,
            CreateAttributeOptionCallback callback) {
		this.createAttributeOptionCallback = callback;
		new CreateAttributeOption(mId, token, attributeId, option).execute(this);
    }

	@Override
    public void getAttributeOption(String mId, String token,
            String attributeId, String optionId,
            GetAttributeOptionCallback callback) {
	    this.getAttributeOptionCallback = callback;
	    new GetAttributeOption(mId, token, attributeId, optionId).execute(this);
    }

	@Override
    public void updateAttributeOption(String mId, String token,
            String attributeId, String optionId, Option update,
            UpdateAttributeOptionCallback callback) {
	    this.updateAttributeOptionCallback = callback;
	    new UpdateAttributeOption(mId, token, attributeId, optionId, update).execute(this);
    }

	@Override
    public void deleteAttributeOption(String mId, String token,
            String attributeId, String optionId,
            DeleteAttributeOptionCallback callback) {
	    this.deleteAttributeOptionCallback = callback;
	    new DeleteAttributeOption(mId, token, attributeId, optionId).execute(this);
    }

	@Override
    public void createOptionItemAssociations(String mId, String token,
            CreateOptionItemAssociationsCallback callback,
            OptionItemAssociation... associations) {
	    this.createOptionItemAssociationsCallback = callback;
	    new CreateOptionItemAssociations(mId, token, associations).execute(this);
    }

	@Override
    public void deleteOptionItemAssociations(String mId, String token,
            DeleteOptionItemAssociationsCallback callback,
            OptionItemAssociation... associations) {
	    this.deleteOptionItemAssociationsCallback = callback;
	    new DeleteOptionItemAssociations(mId, token, associations).execute(this);
    }

	@Override
    public void getItemSummary(String mId, String token,
            GetItemSummaryCallback callback, Object... params) {
	    this.getItemSummaryCallback = callback;
	    new GetItemSummary(mId, token, params).execute(this);
    }

	@Override
	public void onGetItems(WrappedList<Item> result) {
		if (getItemsCallback != null) {
			getItemsCallback.onGetItems(result);
		}
    }

	@Override
	public void onFailGetItems(Error error) {
		if (getItemsCallback != null) {
			getItemsCallback.onFailGetItems(error);
		}
    }

	@Override
	public void onCreateItem(Item item) {
		if (createItemCallback != null) {
			createItemCallback.onCreateItem(item);	
		}
    }

	@Override
	public void onFailCreateItem(Error error) {
		if (createItemCallback != null) {
			createItemCallback.onFailCreateItem(error);
		}
    }

	@Override
	public void onGetItem(Item result) {
		if (getItemCallback != null) {
			getItemCallback.onGetItem(result);
		}
    }

	@Override
	public void onFailGetItem(Error error) {
		if (getItemCallback != null) {
			getItemCallback.onFailGetItem(error);
		}
    }

	@Override
	public void onUpdateItem(Item result) {
		if (updateItemCallback != null) {
			updateItemCallback.onUpdateItem(result);
		}
    }

	@Override
	public void onFailUpdateItem(Error error) {
		if (updateItemCallback != null) {
			updateItemCallback.onFailUpdateItem(error);
		}
    }

	@Override
	public void onDeleteItem(Item result) {
		if (deleteItemCallback != null) {
			deleteItemCallback.onDeleteItem(result);
		}
    }

	@Override
	public void onFailDeleteItem(Error error) {
		if (deleteItemCallback != null) {
			deleteItemCallback.onFailDeleteItem(error);
		}
    }

	@Override
	public void onGetItemStocks(WrappedList<ItemStock> result) {
		if (getItemStocksCallback != null) {
			getItemStocksCallback.onGetItemStocks(result);
		}
    }

	@Override
	public void onFailGetItemStocks(Error error) {
		if (getItemStocksCallback != null) {
			getItemStocksCallback.onFailGetItemStocks(error);
		}
    }

	@Override
	public void onGetItemStock(ItemStock result) {
		if (getItemStockCallback != null) {
			getItemStockCallback.onGetItemStock(result);
		}
    }

	@Override
	public void onFailGetItemStock(Error error) {
		if (getItemStockCallback != null) {
			getItemStockCallback.onFailGetItemStock(error);
		}
    }

	@Override
	public void onUpdateItemStock(ItemStock result) {
		if (updateItemStockCallback != null) {
			updateItemStockCallback.onUpdateItemStock(result);
		}
    }

	@Override
	public void onFailUpdateItemStock(Error error) {
		if (updateItemStockCallback != null) {
			updateItemStockCallback.onFailUpdateItemStock(error);
		}
    }

	@Override
	public void onDeleteItemStock(ItemStock result) {
		if (deleteItemStockCallback != null) {
			deleteItemStockCallback.onDeleteItemStock(result);
		}
    }

	@Override
	public void onFailDeleteItemStock(Error error) {
		if (deleteItemStockCallback != null) {
			deleteItemStockCallback.onFailDeleteItemStock(error);
		}
    }

	@Override
	public void onGetItemGroups(WrappedList<ItemGroup> result) {
		if (getItemGroupsCallback != null) {
			getItemGroupsCallback.onGetItemGroups(result);
		}
    }

	@Override
	public void onFailGetItemGroups(Error error) {
		if (getItemGroupsCallback != null) {
			getItemGroupsCallback.onFailGetItemGroups(error);
		}
    }

	@Override
	public void onCreateItemGroup(ItemGroup result) {
		if (createItemGroupCallback != null) {
			createItemGroupCallback.onCreateItemGroup(result);
		}
    }

	@Override
	public void onFailCreateItemGroup(Error error) {
		if (createItemGroupCallback != null) {
			createItemGroupCallback.onFailCreateItemGroup(error);
		}
    }

	@Override
	public void onGetItemGroup(ItemGroup result) {
		if (getItemGroupCallback != null) {
			getItemGroupCallback.onGetItemGroup(result);
		}
    }

	@Override
	public void onFailGetItemGroup(Error error) {
		if (getItemGroupCallback != null) {
			getItemGroupCallback.onFailGetItemGroup(error);
		}
    }

	@Override
	public void onUpdateItemGroup(ItemGroup result) {
		if (updateItemGroupCallback != null) {
			updateItemGroupCallback.onUpdateItemGroup(result);
		}
    }

	@Override
	public void onFailUpdateItemGroup(Error error) {
		if (updateItemGroupCallback != null) {
			updateItemGroupCallback.onFailUpdateItemGroup(error);
		}
    }

	@Override
	public void onDeleteItemGroup(ItemGroup result) {
		if (deleteItemGroupCallback != null) {
			deleteItemGroupCallback.onDeleteItemGroup(result);
		}
    }

	@Override
	public void onFailDeleteItemGroup(Error error) {
		if (deleteItemGroupCallback != null) {
			deleteItemGroupCallback.onFailDeleteItemGroup(error);
		}
    }

	@Override
	public void onGetTags(WrappedList<Tag> result) {
		if (getTagsCallback != null) {
			getTagsCallback.onGetTags(result);
		}
    }

	@Override
	public void onFailGetTags(Error error) {
		if (getTagsCallback != null) {
			getTagsCallback.onFailGetTags(error);
		}
    }

	@Override
	public void onCreateTag(Tag result) {
		if (createTagCallback != null) {
			createTagCallback.onCreateTag(result);
		}
    }

	@Override
	public void onFailCreateTag(Error error) {
		if (createTagCallback != null) {
			createTagCallback.onFailCreateTag(error);
		}
    }

	@Override
	public void onGetTag(Tag result) {
		if (getTagCallback != null) {
			getTagCallback.onGetTag(result);
		}
    }

	@Override
	public void onFailGetTag(Error error) {
		if (getTagCallback != null) {
			getTagCallback.onFailGetTag(error);
		}
    }

	@Override
	public void onUpdateTag(Tag result) {
		if (updateTagCallback != null) {
			updateTagCallback.onUpdateTag(result);
		}
    }

	@Override
	public void onFailUpdateTag(Error error) {
		if (updateTagCallback != null) {
			updateTagCallback.onFailUpdateTag(error);
		}
    }

	@Override
	public void onDeleteTag(Tag result) {
		if (deleteTagCallback != null) {
			deleteTagCallback.onDeleteTag(result);
		}
    }

	@Override
	public void onFailDeleteTag(Error error) {
		if (deleteTagCallback != null) {
			deleteTagCallback.onFailDeleteTag(error);
		}
    }

	@Override
	public void onGetItemTags(WrappedList<Tag> result) {
		if (getItemTagsCallback != null) {
			getItemTagsCallback.onGetItemTags(result);
		}
    }

	@Override
	public void onFailGetItemTags(Error error) {
		if (getItemTagsCallback != null) {
			getItemTagsCallback.onFailGetItemTags(error);
		}
    }

	@Override
	public void onGetTagItems(WrappedList<Item> result) {
		if (getTagItemsCallback != null) {
			getTagItemsCallback.onGetTagItems(result);
		}
    }

	@Override
	public void onFailGetTagItems(Error error) {
		if (getTagItemsCallback != null) {
			getTagItemsCallback.onFailGetTagItems(error);
		}
    }

	@Override
	public void onCreateTagItemAssociations(
            WrappedList<TagItemAssociation> result) {
		if (createTagItemAssociationsCallback != null) {
			createTagItemAssociationsCallback.onCreateTagItemAssociations(result);
		}
    }

	@Override
	public void onFailCreateTagItemAssociations(Error error) {
		if (createTagItemAssociationsCallback != null) {
		    createTagItemAssociationsCallback
		            .onFailCreateTagItemAssociations(error);
		}
    }
	
	@Override
	public void onDeleteTagItemAssociations(
            WrappedList<TagItemAssociation> result) {
		if (deleteTagItemAssociationsCallback != null) {
			deleteTagItemAssociationsCallback.onDeleteTagItemAssociations(result);
		}
    }

	@Override
	public void onFailDeleteTagItemAssociations(Error error) {
		if (deleteTagItemAssociationsCallback != null) {
		    deleteTagItemAssociationsCallback
		            .onFailDeleteTagItemAssociations(error);
		}
    }

	@Override
	public void onGetCategories(WrappedList<Category> result) {
		if (getCategoriesCallback != null) {
			getCategoriesCallback.onGetCategories(result);
		}
    }

	@Override
	public void onFailGetCategories(Error error) {
		if (getCategoriesCallback != null) {
			getCategoriesCallback.onFailGetCategories(error);
		}
    }

	@Override
	public void onCreateCategory(Category result) {
		if (createCategoryCallback != null) {
			createCategoryCallback.onCreateCategory(result);
		}
    }

	@Override
	public void onFailCreateCategory(Error error) {
		if (createCategoryCallback != null) {
			createCategoryCallback.onFailCreateCategory(error);
		}
    }

	@Override
	public void onGetCategory(Category result) {
		if (getCategoryCallback != null) {
			getCategoryCallback.onGetCategory(result);
		}
    }

	@Override
	public void onFailGetCategory(Error error) {
		if (getCategoryCallback != null) {
			getCategoryCallback.onFailGetCategory(error);
		}
    }

	@Override
	public void onUpdateCategory(Category result) {
		if (updateCategoryCallback != null) {
			updateCategoryCallback.onUpdateCategory(result);
		}
    }

	@Override
	public void onFailUpdateCategory(Error error) {
		if (updateCategoryCallback != null) {
			updateCategoryCallback.onFailUpdateCategory(error);
		}
    }

	@Override
	public void onDeleteCategory(Category result) {
		if (deleteCategoryCallback != null) {
			deleteCategoryCallback.onDeleteCategory(result);
		}
    }
	
	@Override
	public void onFailDeleteCategory(Error error) {
		if (deleteCategoryCallback != null) {
			deleteCategoryCallback.onFailDeleteCategory(error);
		}
    }

	@Override
	public void onGetCategoryItems(WrappedList<Item> result) {
		if (getCategoryItemsCallback != null) {
			getCategoryItemsCallback.onGetCategoryItems(result);
		}
    }

	@Override
	public void onFailGetCategoryItems(Error error) {
		if (getCategoryItemsCallback != null) {
			getCategoryItemsCallback.onFailGetCategoryItems(error);
		}
    }

	@Override
	public void onGetItemCategories(WrappedList<Category> result) {
		if (getItemCategoriesCallback != null) {
			getItemCategoriesCallback.onGetItemCategories(result);
		}
    }

	@Override
	public void onFailGetItemCategories(Error error) {
		if (getItemCategoriesCallback != null) {
			getItemCategoriesCallback.onFailGetItemCategories(error);
		}
    }

	@Override
	public void onCreateCategoryItemAssociations(
            WrappedList<CategoryItemAssociation> result) {
		if (createCategoryItemAssociationsCallback != null) {
		    createCategoryItemAssociationsCallback
		            .onCreateCategoryItemAssociations(result);
		}
    }

	@Override
	public void onFailCreateCategoryItemAssociations(Error error) {
		if (createCategoryItemAssociationsCallback != null) {
		    createCategoryItemAssociationsCallback
		            .onFailCreateCategoryItemAssociations(error);
		}
    }

	@Override
	public void onDeleteCategoryItemAssociations(
            WrappedList<CategoryItemAssociation> result) {
		if (deleteCategoryItemAssociationsCallback != null) {
		    deleteCategoryItemAssociationsCallback
		            .onDeleteCategoryItemAssociations(result);
		}
    }

	@Override
	public void onFailDeleteCategoryItemAssociations(Error error) {
		if (deleteCategoryItemAssociationsCallback != null) {
		    deleteCategoryItemAssociationsCallback
		            .onFailDeleteCategoryItemAssociations(error);
		}
    }

	@Override
	public void onCreateTaxRateItemAssociations(
            WrappedList<TaxRateItemAssociation> result) {
		if (createTaxRateItemAssociationsCallback != null) {
		    createTaxRateItemAssociationsCallback
		            .onCreateTaxRateItemAssociations(result);
		}
    }

	@Override
	public void onFailCreateTaxRateItemAssociations(Error error) {
		if (createTaxRateItemAssociationsCallback != null) {
		    createTaxRateItemAssociationsCallback
		            .onFailCreateTaxRateItemAssociations(error);
		}
    }

	@Override
	public void onDeleteTaxRateItemAssociations(
            WrappedList<TaxRateItemAssociation> result) {
		if (deleteTaxRateItemAssociationsCallback != null) {
		    deleteTaxRateItemAssociationsCallback
		            .onDeleteTaxRateItemAssociations(result);
		}
    }

	@Override
	public void onFailDeleteTaxRateItemAssociations(Error error) {
		if (deleteTaxRateItemAssociationsCallback != null) {
		    deleteTaxRateItemAssociationsCallback
		            .onFailDeleteTaxRateItemAssociations(error);
		}
    }

	@Override
	public void onGetTaxRateItems(WrappedList<Item> result) {
		if (getTaxRateItemsCallback != null) {
			getTaxRateItemsCallback.onGetTaxRateItems(result);
		}
    }

	@Override
	public void onFailGetTaxRateItems(Error error) {
		if (getTaxRateItemsCallback != null) {
			getTaxRateItemsCallback.onFailGetTaxRateItems(error);
		}
    }

	@Override
	public void onGetModifierGroups(WrappedList<ModifierGroup> result) {
		if (getModifierGroupsCallback != null) {
			getModifierGroupsCallback.onGetModifierGroups(result);
		}
    }

	@Override
	public void onFailGetModifierGroups(Error error) {
		if (getModifierGroupsCallback != null) {
			getModifierGroupsCallback.onFailGetModifierGroups(error);
		}
    }

	@Override
	public void onGetModifierGroup(ModifierGroup result) {
		if (getModifierGroupCallback != null) {
			getModifierGroupCallback.onGetModifierGroup(result);
		}
    }

	@Override
	public void onFailGetModifierGroup(Error error) {
		if (getModifierGroupCallback != null) {
			getModifierGroupCallback.onFailGetModifierGroup(error);
		}
    }

	@Override
	public void onCreateModifierGroup(ModifierGroup result) {
		if (createModifierGroupCallback != null) {
			createModifierGroupCallback.onCreateModifierGroup(result);
		}
    }

	@Override
	public void onFailCreateModifierGroup(Error error) {
		if (createModifierGroupCallback != null) {
			createModifierGroupCallback.onFailCreateModifierGroup(error);
		}
    }

	@Override
	public void onUpdateModifierGroup(ModifierGroup result) {
		if (updateModifierGroupCallback != null) {
			updateModifierGroupCallback.onUpdateModifierGroup(result);
		}
    }

	@Override
	public void onFailUpdateModifierGroup(Error error) {
		if (updateModifierGroupCallback != null) {
			updateModifierGroupCallback.onFailUpdateModifierGroup(error);
		}
    }

	@Override
	public void onDeleteModifierGroup(ModifierGroup result) {
		if (deleteModifierGroupCallback != null) {
			deleteModifierGroupCallback.onDeleteModifierGroup(result);
		}
    }
	
	@Override
	public void onFailDeleteModifierGroup(Error error) {
		if (deleteModifierGroupCallback != null) {
			deleteModifierGroupCallback.onFailDeleteModifierGroup(error);
		}
    }

	@Override
	public void onCreateModifierGroupItemAssociations(
            WrappedList<ModifierGroupItemAssociation> result) {
		if (createModifierGroupItemAssociationsCallback != null) {
		    createModifierGroupItemAssociationsCallback
		            .onCreateModifierGroupItemAssociations(result);
		}
    }

	@Override
	public void onFailCreateModifierGroupItemAssociations(Error error) {
		if (createModifierGroupItemAssociationsCallback != null) {
		    createModifierGroupItemAssociationsCallback
		            .onFailCreateModifierGroupItemAssociations(error);
		}
    }

	@Override
	public void onDeleteModifierGroupItemAssociations(
            WrappedList<ModifierGroupItemAssociation> result) {
		if (deleteModifierGroupItemAssociationsCallback != null) {
		    deleteModifierGroupItemAssociationsCallback
		            .onDeleteModifierGroupItemAssociations(result);
		}
    }

	@Override
	public void onFailDeleteModifierGroupItemAssociations(Error error) {
		if (deleteModifierGroupItemAssociationsCallback != null) {
		    deleteModifierGroupItemAssociationsCallback
		            .onFailDeleteModifierGroupItemAssociations(error);
		}
    }

	@Override
	public void onGetModifiers(WrappedList<Modifier> result) {
		if (getModifiersCallback != null) {
			getModifiersCallback.onGetModifiers(result);
		}
    }
	
	@Override
	public void onFailGetModifiers(Error error) {
		if (getModifiersCallback != null) {
			getModifiersCallback.onFailGetModifiers(error);
		}
    }

	@Override
	public void onGetModifierGroupModifiers(WrappedList<Modifier> result) {
		if (getModifierGroupModifiersCallback != null) {
			getModifierGroupModifiersCallback.onGetModifierGroupModifiers(result);
		}
    }

	@Override
	public void onFailGetModifierGroupModifiers(Error error) {
		if (getModifierGroupModifiersCallback != null) {
		    getModifierGroupModifiersCallback
		            .onFailGetModifierGroupModifiers(error);
		}
    }
	
	@Override
	public void onCreateModifierGroupModifier(Modifier result) {
		if (createModifierGroupModifierCallback != null) {
		    createModifierGroupModifierCallback
		            .onCreateModifierGroupModifier(result);
		}
    }

	@Override
	public void onFailCreateModifierGroupModifier(Error error) {
		if (createModifierGroupModifierCallback != null) {
		    createModifierGroupModifierCallback
		            .onFailCreateModifierGroupModifier(error);   
		}
    }

	@Override
	public void onGetModifierGroupModifier(Modifier result) {
		if (getModifierGroupModifierCallback != null) {
			getModifierGroupModifierCallback.onGetModifierGroupModifier(result);
		}
    }
	
	@Override
	public void onFailGetModifierGroupModifier(Error error) {
		if (getModifierGroupModifierCallback != null) {
			getModifierGroupModifierCallback.onFailGetModifierGroupModifier(error);
		}
    }

	@Override
	public void onUpdateModifierGroupModifier(Modifier result) {
		if (updateModifierGroupModifierCallback != null) {
		    updateModifierGroupModifierCallback
		            .onUpdateModifierGroupModifier(result);
		}
    }

	@Override
	public void onFailUpdateModifierGroupModifier(Error error) {
		if (updateModifierGroupModifierCallback != null) {
		    updateModifierGroupModifierCallback
		            .onFailUpdateModifierGroupModifier(error);
		}
    }

	@Override
	public void onDeleteModifierGroupModifier(Modifier result) {
		if (deleteModifierGroupModifierCallback != null) {
		    deleteModifierGroupModifierCallback
		            .onDeleteModifierGroupModifier(result);
		}
    }

	@Override
	public void onFailDeleteModifierGroupModifier(Error error) {
		if (deleteModifierGroupModifierCallback != null) {
		    deleteModifierGroupModifierCallback
		            .onFailDeleteModifierGroupModifier(error);
		}
    }

	@Override
	public void onGetAttributes(WrappedList<Attribute> result) {
		if (getAttributesCallback != null) {
			getAttributesCallback.onGetAttributes(result);
		}
    }

	@Override
	public void onFailGetAttributes(Error error) {
		if (getAttributesCallback != null) {
			getAttributesCallback.onFailGetAttributes(error);
		}
    }

	@Override
	public void onCreateAttribute(Attribute result) {
		if (createAttributeCallback != null) {
			createAttributeCallback.onCreateAttribute(result);
		}
    }

	@Override
	public void onFailCreateAttribute(Error error) {
		if (createAttributeCallback != null) {
			createAttributeCallback.onFailCreateAttribute(error);
		}
    }
	
	@Override
	public void onGetAttribute(Attribute result) {
		if (getAttributeCallback != null) {
			getAttributeCallback.onGetAttribute(result);
		}
    }

	@Override
	public void onFailGetAttribute(Error error) {
		if (getAttributeCallback != null) {
			getAttributeCallback.onFailGetAttribute(error);
		}
    }

	@Override
	public void onUpdateAttribute(Attribute result) {
		if (updateAttributeCallback != null) {
			updateAttributeCallback.onUpdateAttribute(result);
		}
    }

	@Override
	public void onFailUpdateAttribute(Error error) {
		if (updateAttributeCallback != null) {
			updateAttributeCallback.onFailUpdateAttribute(error);
		}
    }

	@Override
	public void onDeleteAttribute(Attribute result) {
		if (deleteAttributeCallback != null) {
			deleteAttributeCallback.onDeleteAttribute(result);
		}
    }

	@Override
	public void onFailDeleteAttribute(Error error) {
		if (deleteAttributeCallback != null) {
			deleteAttributeCallback.onFailDeleteAttribute(error);
		}
    }

	@Override
	public void onGetOptions(WrappedList<Option> result) {
		if (getOptionsCallback != null) {
			getOptionsCallback.onGetOptions(result);
		}
    }

	@Override
	public void onFailGetOptions(Error error) {
		if (getOptionsCallback != null) {
			getOptionsCallback.onFailGetOptions(error);
		}
    }
	
	@Override
	public void onGetAttributeOptions(WrappedList<Option> result) {
		if (getAttributeOptionsCallback != null) {
			getAttributeOptionsCallback.onGetAttributeOptions(result);
		}
    }

	@Override
	public void onFailGetAttributeOptions(Error error) {
		if (getAttributeOptionsCallback != null) {
			getAttributeOptionsCallback.onFailGetAttributeOptions(error);
		}
    }

	@Override
	public void onCreateAttributeOption(Option result) {
		if (createAttributeOptionCallback != null) {
			createAttributeOptionCallback.onCreateAttributeOption(result);
		}
    }

	@Override
	public void onFailCreateAttributeOption(Error error) {
		if (createAttributeOptionCallback != null) {
			createAttributeOptionCallback.onFailCreateAttributeOption(error);
		}
    }

	@Override
	public void onGetAttributeOption(Option result) {
		if (getAttributeOptionCallback != null) {
			getAttributeOptionCallback.onGetAttributeOption(result);
		}
    }

	@Override
	public void onFailGetAttributeOption(Error error) {
		if (getAttributeOptionCallback != null) {
			getAttributeOptionCallback.onFailGetAttributeOption(error);
		}
    }

	@Override
	public void onUpdateAttributeOption(Option result) {
		if (updateAttributeOptionCallback != null) {
			updateAttributeOptionCallback.onUpdateAttributeOption(result);
		}
    }

	@Override
	public void onFailUpdateAttributeOption(Error error) {
		if (updateAttributeOptionCallback != null) {
			updateAttributeOptionCallback.onFailUpdateAttributeOption(error);
		}
    }

	@Override
	public void onDeleteAttributeOption(Option result) {
		if (deleteAttributeOptionCallback != null) {
			deleteAttributeOptionCallback.onDeleteAttributeOption(result);
		}
    }

	@Override
	public void onFailDeleteAttributeOption(Error error) {
		if (deleteAttributeOptionCallback != null) {
			deleteAttributeOptionCallback.onFailDeleteAttributeOption(error);
		}
    }

	@Override
	public void onCreateOptionItemAssociations(
            WrappedList<OptionItemAssociation> result) {
		if (createOptionItemAssociationsCallback != null) {
		    createOptionItemAssociationsCallback
		            .onCreateOptionItemAssociations(result);
		}
    }

	@Override
	public void onFailCreateOptionItemAssociations(Error error) {
		if (createOptionItemAssociationsCallback != null) {
		    createOptionItemAssociationsCallback
		            .onFailCreateOptionItemAssociations(error);
		}
    }

	@Override
	public void onDeleteOptionItemAssociations(
            WrappedList<OptionItemAssociation> result) {
		if (deleteOptionItemAssociationsCallback != null) {
		    deleteOptionItemAssociationsCallback
		            .onDeleteOptionItemAssociations(result);
		}
    }

	@Override
	public void onFailDeleteOptionItemAssociations(Error error) {
		if (deleteOptionItemAssociationsCallback != null) {
		    deleteOptionItemAssociationsCallback
		            .onFailDeleteOptionItemAssociations(error);
		}
    }

	@Override
	public void onGetItemSummary(ItemSummary result) {
		if (getItemSummaryCallback != null) {
			getItemSummaryCallback.onGetItemSummary(result);
		}
    }

	@Override
	public void onFailGetItemSummary(Error error) {
		if (getItemSummaryCallback != null) {
			getItemSummaryCallback.onFailGetItemSummary(error);
		}
    }

}
