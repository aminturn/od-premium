package com.trubeacon.cloverandroidapi.inventory.service;

import java.util.List;

import com.trubeacon.cloverandroidapi.client.RESTException;
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

public interface InventoryService {

	public List<Item> getItems(String mId, String token, Object... params) throws RESTException;
	public void getItems(String mId, String token, GetItemsCallback callback, Object... params);
	public Item createItem(String mId, String token, Item item) throws RESTException;
	public void createItem(String mId, String token, Item item, CreateItemCallback callback);
	public Item getItem(String mId, String token, String itemId) throws RESTException;
	public void getItem(String mId, String token, String itemId, GetItemCallback callback);
	public Item updateItem(String mId, String token, String itemId, Item item) throws RESTException;
	public void updateItem(String mId, String token, String itemId, Item item, UpdateItemCallback callback);
	public Item deleteItem(String mId, String token, String itemId) throws RESTException;
	public void deleteItem(String mId, String token, String itemId, DeleteItemCallback callback);
	
	public List<ItemStock> getItemStocks(String mId, String token, Object... params) throws RESTException;
	public void getItemStocks(String mId, String token, GetItemStocksCallback callback, Object... params);
	public ItemStock getItemStock(String mId, String token, String itemId) throws RESTException;
	public void getItemStock(String mId, String token, String itemId, GetItemStockCallback callback);
	public ItemStock updateItemStock(String mId, String token, String itemId, ItemStock itemStock) throws RESTException;
	public void updateItemStock(String mId, String token, String itemId, ItemStock itemStock, UpdateItemStockCallback callback);
	public ItemStock deleteItemStock(String mId, String token, String itemId) throws RESTException;
	public void deleteItemStock(String mId, String token, String itemId, DeleteItemStockCallback callback);
	
	public List<ItemGroup> getItemGroups(String mId, String token, Object... params) throws RESTException;
	public void getItemGroups(String mId, String token, GetItemGroupsCallback callback, Object... params);
	public ItemGroup createItemGroup(String mId, String token, ItemGroup itemGroup) throws RESTException;
	public void createItemGroup(String mId, String token, ItemGroup itemGroup, CreateItemGroupCallback callback);
	public ItemGroup getItemGroup(String mId, String token, String itemGroupId) throws RESTException;
	public void getItemGroup(String mId, String token, String itemGroupId, GetItemGroupCallback callback);
	public ItemGroup updateItemGroup(String mId, String token, String itemGroupId, ItemGroup itemGroup) throws RESTException;
	public void updateItemGroup(String mId, String token, String itemGroupId, ItemGroup itemGroup, UpdateItemGroupCallback callback);
	public ItemGroup deleteItemGroup(String mId, String token, String itemGroupId) throws RESTException;
	public void deleteItemGroup(String mId, String token, String itemGroupId, DeleteItemGroupCallback callback);
	
	public List<Tag> getTags(String mId, String token, Object... params) throws RESTException;
	public void getTags(String mId, String token, GetTagsCallback callback, Object... params);
	public Tag createTag(String mId, String token, Tag tag) throws RESTException;
	public void createTag(String mId, String token, Tag tag, CreateTagCallback callback);
	public Tag getTag(String mId, String token, String tagId) throws RESTException;
	public void getTag(String mId, String token, String tagId, GetTagCallback callback);
	public Tag updateTag(String mId, String token, String tagId, Tag tag) throws RESTException;
	public void updateTag(String mId, String token, String tagId, Tag tag, UpdateTagCallback callback);
	public Tag deleteTag(String mId, String token, String tagId) throws RESTException;
	public void deleteTag(String mId, String token, String tagId, DeleteTagCallback callback);

	public List<Tag> getItemTags(String mId, String token, String itemId, Object... params) throws RESTException;
	public void getItemTags(String mId, String token, String itemId, GetItemTagsCallback callback, Object... params);
	public List<Item> getTagItems(String mId, String token, String tagId, Object... params) throws RESTException;
	public void getTagItems(String mId, String token, String tagId, GetTagItemsCallback callback, Object... params);
	
	// ???: wtf does this return???
	public List<TagItemAssociation> createTagItemAssociations(String mId, String token, TagItemAssociation... associations) throws RESTException;
	public void createTagItemAssociations(String mId, String token, CreateTagItemAssociationsCallback callback, TagItemAssociation... associations);
	public List<TagItemAssociation> deleteTagItemAssociations(String mId, String token, TagItemAssociation... associations) throws RESTException;
	public void deleteTagItemAssociations(String mId, String token, DeleteTagItemAssociationsCallback callback, TagItemAssociation... associations);
	
	public List<Category> getCategories(String mId, String token, Object... params) throws RESTException;
	public void getCategories(String mId, String token, GetCategoriesCallback callback, Object... params);
	public Category createCategory(String mId, String token, Category category) throws RESTException;
	public void createCategory(String mId, String token, Category category, CreateCategoryCallback callback);
	public Category getCategory(String mId, String token, String catId) throws RESTException;
	public void getCategory(String mId, String token, String catId, GetCategoryCallback callback);
	public Category updateCategory(String mId, String token, String catId, Category update) throws RESTException;
	public void updateCategory(String mId, String token, String catId, Category update, UpdateCategoryCallback callback);
	public Category deleteCategory(String mId, String token, String catId) throws RESTException;
	public void deleteCategory(String mId, String token, String catId, DeleteCategoryCallback callback);
	
	public List<Item> getCategoryItems(String mId, String token, String catId, Object... params) throws RESTException;
	public void getCategoryItems(String mId, String token, String catId, GetCategoryItemsCallback callback, Object... params);
	public List<Category> getItemCategories(String mId, String token, String itemId, Object... params) throws RESTException;
	public void getItemCategories(String mId, String token, String itemId, GetItemCategoriesCallback callback, Object... params);
	
	// ???: wtf does this return???
	public List<CategoryItemAssociation> createCategoryItemAssociations(String mId, String token, CategoryItemAssociation... associations) throws RESTException;
	public void createCategoryItemAssociations(String mId, String token, CreateCategoryItemAssociationsCallback callback, CategoryItemAssociation... associations);
	public List<CategoryItemAssociation> deleteCategoryItemAssociations(String mId, String token, CategoryItemAssociation... associations) throws RESTException;
	public void deleteCategoryItemAssociations(String mId, String token, DeleteCategoryItemAssociationsCallback callback, CategoryItemAssociation... associations);
	
	// ???: wtf does this return???
	public List<TaxRateItemAssociation> createTaxRateItemAssociations(String mId, String token, TaxRateItemAssociation... associations) throws RESTException;
	public void createTaxRateItemAssociations(String mId, String token, CreateTaxRateItemAssociationsCallback callback, TaxRateItemAssociation... associations);
	public List<TaxRateItemAssociation> deleteTaxRateItemAssociations(String mId, String token, TaxRateItemAssociation... associations) throws RESTException;
	public void deleteTaxRateItemAssociations(String mId, String token, DeleteTaxRateItemAssociationsCallback callback, TaxRateItemAssociation... associations);
		
	public List<Item> getTaxRateItems(String mId, String token, String taxId, Object... params) throws RESTException;
	public void getTaxRateItems(String mId, String token, String taxId, GetTaxRateItemsCallback callback, Object... params);
	
	public List<ModifierGroup> getModifierGroups(String mId, String token, Object... params) throws RESTException;
	public void getModifierGroups(String mId, String token, GetModifierGroupsCallback callback, Object... params);
	public ModifierGroup getModifierGroup(String mId, String token, String modGroupId) throws RESTException;
	public void getModifierGroup(String mId, String token, String modGroupId, GetModifierGroupCallback callback);
	public ModifierGroup createModifierGroup(String mId, String token, ModifierGroup modifierGroup) throws RESTException;
	public void createModifierGroup(String mId, String token, ModifierGroup modifierGroup, CreateModifierGroupCallback callback);
	public ModifierGroup updateModifierGroup(String mId, String token, String modGroupId, ModifierGroup update) throws RESTException;
	public void updateModifierGroup(String mId, String tokne, String modGroupId, ModifierGroup update, UpdateModifierGroupCallback callback);
	public ModifierGroup deleteModifierGroup(String mId, String token, String modGroupId) throws RESTException;
	public void deleteModifierGroup(String mId, String token, String modGroupId, DeleteModifierGroupCallback callback);
	
	// ???: wtf does this return???
	public List<ModifierGroupItemAssociation> createModifierGroupItemAssociations(String mId, String token, ModifierGroupItemAssociation... associations) throws RESTException;
	public void createModifierGroupItemAssociations(String mId, String token, CreateModifierGroupItemAssociationsCallback callback, ModifierGroupItemAssociation... associations);
	public List<ModifierGroupItemAssociation> deleteModifierGroupItemAssociations(String mId, String token, ModifierGroupItemAssociation... associations) throws RESTException;
	public void deleteModifierGroupItemAssociations(String mId, String token, DeleteModifierGroupItemAssociationsCallback callback, ModifierGroupItemAssociation... associations);
	
	public List<Modifier> getModifiers(String mId, String token, Object... params) throws RESTException;
	public void getModifiers(String mId, String token, GetModifiersCallback callback, Object... params);
	public List<Modifier> getModifierGroupModifiers(String mId, String token, String modGroupId, Object... params) throws RESTException;
	public void getModifierGroupModifiers(String mId, String token, String modGroupId, GetModifierGroupModifiersCallback callback, Object... params);
	public Modifier createModifierGroupModifier(String mId, String token, String modGroupId, Modifier modifier) throws RESTException;
	public void createModifierGroupModifier(String mId, String token, String modGroupId, Modifier modifier, CreateModifierGroupModifierCallback callback);
	public Modifier getModifierGroupModifier(String mId, String token, String modGroupId, String modId) throws RESTException;
	public void getModifierGroupModifier(String mId, String token, String modGroupId, String modId, GetModifierGroupModifierCallback callback);
	public Modifier updateModifierGroupModifier(String mId, String token, String modGroupId, String modId, Modifier update) throws RESTException;
	public void updateModifierGroupModifier(String mId, String token, String modGroupId, String modId, Modifier update, UpdateModifierGroupModifierCallback callback);
	public Modifier deleteModifierGroupModifier(String mId, String token, String modGroupId, String modId) throws RESTException;
	public void deleteModifierGroupModifier(String mId, String token, String modGroupId, String modId, DeleteModifierGroupModifierCallback callback);
	
	public List<Attribute> getAttributes(String mId, String token, Object... params) throws RESTException;
	public void getAttributes(String mId, String token, GetAttributesCallback callback, Object... params);
	public Attribute createAttribute(String mId, String token, Attribute attribute) throws RESTException;
	public void createAttribute(String mId, String token, Attribute attribute, CreateAttributeCallback callback);
	public Attribute getAttribute(String mId, String token, String attributeId) throws RESTException;
	public void getAttribute(String mId, String token, String attributeId, GetAttributeCallback callback);
	public Attribute updateAttribute(String mId, String token, String attributeId, Attribute attribute) throws RESTException;
	public void updateAttribute(String mId, String token, String attributeId, Attribute attribute, UpdateAttributeCallback callback);
	public Attribute deleteAttribute(String mId, String token, String attributeId) throws RESTException;
	public void deleteAttribute(String mId, String token, String attributeId, DeleteAttributeCallback callback);
	
	public List<Option> getOptions(String mId, String token, Object... params) throws RESTException;
	public void getOptions(String mId, String token, GetOptionsCallback callback, Object... params);
	public List<Option> getAttributeOptions(String mId, String token, String attributeId, Object... params) throws RESTException;
	public void getAttributeOptions(String mId, String token, String attributeId, GetAttributeOptionsCallback callback, Object... params);
	public Option createAttributeOption(String mId, String token, String attributeId, Option option) throws RESTException;
	public void createAttributeOption(String mId, String token, String attributeId, Option option, CreateAttributeOptionCallback callback);
	public Option getAttributeOption(String mId, String token, String attributeId, String optionId) throws RESTException;
	public void getAttributeOption(String mId, String token, String attributeId, String optionId, GetAttributeOptionCallback callback);
	public Option updateAttributeOption(String mId, String token, String attributeId, String optionId, Option update) throws RESTException;
	public void updateAttributeOption(String mId, String token, String attributeId, String optionId, Option update, UpdateAttributeOptionCallback callback);
	public Option deleteAttributeOption(String mId, String token, String attributeId, String optionId) throws RESTException;
	public void deleteAttributeOption(String mId, String token, String attributeId, String optionId, DeleteAttributeOptionCallback callback);
	
	// ???: wtf does this return???
	public List<OptionItemAssociation> createOptionItemAssociations(String mId, String token, OptionItemAssociation... associations) throws RESTException;
	public void createOptionItemAssociations(String mId, String token, CreateOptionItemAssociationsCallback callback, OptionItemAssociation... associations);
	public List<OptionItemAssociation> deleteOptionItemAssociations(String mId, String token, OptionItemAssociation... associations) throws RESTException;
	public void deleteOptionItemAssociations(String mId, String token, DeleteOptionItemAssociationsCallback callback, OptionItemAssociation... associations);
	
	public ItemSummary getItemSummary(String mId, String token, Object... params) throws RESTException;
	public void getItemSummary(String mId, String token, GetItemSummaryCallback callback, Object... params);
	
}
