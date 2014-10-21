package com.kawaiiwolf.kawaiicrops.lib;

import java.util.ArrayList;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

public class RecipeHelper {

	public static boolean register2x2recipie(String recipe)
	{
		String[] parts = recipe.replaceAll("  ", " ").split("[ ]");
		if (parts.length != 6) 
			return false;
		
		// Parse Output Type
		IngredientType outputType = parseIngredientType(parts[0]);
		if (outputType != IngredientType.ITEM && outputType != IngredientType.BLOCK)
			return false;
		
		// Parse Output Number
		int outputNum;
		try {
			outputNum = Integer.parseInt(parts[1]);
		} catch (NumberFormatException e) { return false; }
		if (outputNum < 1 || outputNum > 64) return false;
		
		// Parse Input
		ArrayList<Object> inputParts = new ArrayList<Object>();
		String inputMap = ""; 
		int inputMapIndex = 1;
		for(int i = 2; i < parts.length; i++)
		{
			
			if(parts[i].toLowerCase().equals("nothing"))
			{
				inputMap += " ";
			} 
			else 
			{
				Object param = parseIngredient(parts[i]);
				if (param == null)
					return false;
				
				inputParts.add(param);
				inputMap += "" + (inputMapIndex++);
			}
		}
		
		ArrayList<Object> input = new ArrayList<Object>();
		input.add(inputMap.substring(0, 2));
		input.add(inputMap.substring(2, 4));
		for (int i = 0; i < inputParts.size(); i++)
		{
			input.add(("" + (1 + i)).charAt(0));
			input.add(inputParts.get(i));
		}
		
		ItemStack output = (outputType == IngredientType.ITEM ? 
				new ItemStack(NamespaceHelper.getItemByName(parts[0]),outputNum) : 
				new ItemStack(NamespaceHelper.getBlockByName(parts[0]),outputNum));		
		
		GameRegistry.addRecipe(new ShapedOreRecipe(output, input.toArray()));
		
		return true;
	}

		
	public static boolean register3x3recipie(String recipe)
	{
		String[] parts = recipe.replaceAll("  ", " ").split("[ ]");
		if (parts.length != 11) 
			return false;
		
		// Parse Output Type
		IngredientType outputType = parseIngredientType(parts[0]);
		if (outputType != IngredientType.ITEM && outputType != IngredientType.BLOCK)
			return false;
		
		// Parse Output Number
		int outputNum;
		try {
			outputNum = Integer.parseInt(parts[1]);
		} catch (NumberFormatException e) { return false; }
		if (outputNum < 1 || outputNum > 64) return false;
		
		// Parse Input
		ArrayList<Object> inputParts = new ArrayList<Object>();
		String inputMap = ""; 
		int inputMapIndex = 1;
		for(int i = 2; i < parts.length; i++)
		{
			
			if(parts[i].toLowerCase().equals("nothing"))
			{
				inputMap += " ";
			} 
			else 
			{
				Object param = parseIngredient(parts[i]);
				if (param == null)
					return false;
				
				inputParts.add(param);
				inputMap += "" + (inputMapIndex++);
			}
		}
		
		ArrayList<Object> input = new ArrayList<Object>();
		input.add(inputMap.substring(0, 3));
		input.add(inputMap.substring(3, 6));
		input.add(inputMap.substring(6, 9));
		for (int i = 0; i < inputParts.size(); i++)
		{
			input.add(("" + (1 + i)).charAt(0));
			input.add(inputParts.get(i));
		}
		
		ItemStack output = (outputType == IngredientType.ITEM ? 
				new ItemStack(NamespaceHelper.getItemByName(parts[0]),outputNum) : 
				new ItemStack(NamespaceHelper.getBlockByName(parts[0]),outputNum));		
		
		GameRegistry.addRecipe(new ShapedOreRecipe(output, input.toArray()));
		
		return true;
	}
	
	public static boolean registerShapelessRecipie(String recipe)
	{
		String[] parts = recipe.replaceAll("  ", " ").split("[ ]");
		if (parts.length < 3) 
			return false;
		
		// Parse Output Type
		IngredientType outputType = parseIngredientType(parts[0]);
		if (outputType != IngredientType.ITEM && outputType != IngredientType.BLOCK)
			return false;
		
		// Parse Output Number
		int outputNum;
		try {
			outputNum = Integer.parseInt(parts[1]);
		} catch (NumberFormatException e) { return false; }
		if (outputNum < 1 || outputNum > 64) return false;
		
		// Parse Input
		ArrayList<Object> input = new ArrayList<Object>();
		for(int i = 2; i < parts.length; i++)
		{
			Object param = parseIngredient(parts[i]);
			if (param == null)
				return false;
			input.add(param);
		}

		ItemStack output = (outputType == IngredientType.ITEM ? 
			new ItemStack(NamespaceHelper.getItemByName(parts[0]),outputNum) : 
			new ItemStack(NamespaceHelper.getBlockByName(parts[0]),outputNum));
		
		GameRegistry.addRecipe(new ShapelessOreRecipe(output, input.toArray()));
		
		return true;
	}
	
	public static boolean registerSmeltingRecipie(String recipe)
	{
		String[] parts = recipe.replaceAll("  ", " ").split("[ ]");
		if (parts.length != 3) 
			return false;
		
		// Parse Resulting Type
		IngredientType outputType = parseIngredientType(parts[0]);
		if (outputType != IngredientType.ITEM && outputType != IngredientType.BLOCK)
			return false;
		
		// Parse Output Number
		int outputNum;
		try {
			outputNum = Integer.parseInt(parts[1]);
		} catch (NumberFormatException e) { return false; }
		if (outputNum < 1 || outputNum > 64) return false;
		
		// Parse Input Type
		
		IngredientType inputType = parseIngredientType(parts[2]);
		if (inputType != IngredientType.ITEM && inputType != IngredientType.BLOCK)
			return false;
		
		ItemStack input = (inputType == IngredientType.ITEM ? 
				new ItemStack(NamespaceHelper.getItemByName(parts[2])) : 
				new ItemStack(NamespaceHelper.getBlockByName(parts[2])));

		ItemStack output = (outputType == IngredientType.ITEM ? 
				new ItemStack(NamespaceHelper.getItemByName(parts[0]),outputNum) : 
				new ItemStack(NamespaceHelper.getBlockByName(parts[0]),outputNum));

		GameRegistry.addSmelting(input, output, 0.1F);
		
		return true;
	}

	private enum IngredientType { ITEM, BLOCK, ORE, ERROR };
	
	private static IngredientType parseIngredientType(String name)
	{
		Object o = NamespaceHelper.getItemByName(name);
		if (o != null) 
			return IngredientType.ITEM;

		o = NamespaceHelper.getBlockByName(name);
		if (o != null && o != Blocks.air) 
			return IngredientType.BLOCK;
		
		if (OreDictionary.getOres(name) != null)
			return IngredientType.ORE;
		
		return IngredientType.ERROR;
	}
	
	private static Object parseIngredient(String name)
	{
		Object o = NamespaceHelper.getItemByName(name);
		if (o != null) 
			return o;

		o = NamespaceHelper.getBlockByName(name);
		if (o != null && o != Blocks.air) 
			return o;
		
		if (OreDictionary.getOres(name) != null)
			return name;
		
		return null;
	}
	
}