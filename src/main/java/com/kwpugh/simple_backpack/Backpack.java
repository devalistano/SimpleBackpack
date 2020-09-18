package com.kwpugh.simple_backpack;

import com.kwpugh.simple_backpack.backpack.BackpackInventoryInterface;
import com.kwpugh.simple_backpack.backpack.BackpackItem;
import com.kwpugh.simple_backpack.backpack.BackpackScreenHandler;
import com.kwpugh.simple_backpack.enderpack.EnderPackItem;
import com.kwpugh.simple_backpack.voidpack.VoidPackInventoryInterface;
import com.kwpugh.simple_backpack.voidpack.VoidPackItem;
import com.kwpugh.simple_backpack.voidpack.VoidPackScreenHandler;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.container.ContainerProviderRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import net.minecraft.util.registry.Registry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Backpack implements ModInitializer {

    public static final String MOD_ID = "simple_backpack";
    public static final String MOD_NAME = "SimpleBackpack";
    public static final Identifier BACKPACK_IDENTIFIER = new Identifier(MOD_ID, "backpack");
    public static final Identifier BACKPACK_RED_IDENTIFIER = new Identifier(MOD_ID, "backpack_red");
    public static final Identifier BACKPACK_BLACK_IDENTIFIER = new Identifier(MOD_ID, "backpack_black");
    public static final Identifier BACKPACK_BLUE_IDENTIFIER = new Identifier(MOD_ID, "backpack_blue");
    public static final Identifier BACKPACK_CYAN_IDENTIFIER = new Identifier(MOD_ID, "backpack_cyan");
    public static final Identifier BACKPACK_GRAY_IDENTIFIER = new Identifier(MOD_ID, "backpack_gray");
    public static final Identifier BACKPACK_GREEN_IDENTIFIER = new Identifier(MOD_ID, "backpack_green");
    public static final Identifier BACKPACK_LIGHT_BLUE_IDENTIFIER = new Identifier(MOD_ID, "backpack_light_blue");
    public static final Identifier BACKPACK_LIME_IDENTIFIER = new Identifier(MOD_ID, "backpack_lime");
    public static final Identifier BACKPACK_MAGENTA_IDENTIFIER = new Identifier(MOD_ID, "backpack_magenta");
    public static final Identifier BACKPACK_ORANGE_IDENTIFIER = new Identifier(MOD_ID, "backpack_orange");
    public static final Identifier BACKPACK_PINK_IDENTIFIER = new Identifier(MOD_ID, "backpack_pink");
    public static final Identifier BACKPACK_PURPLE_IDENTIFIER = new Identifier(MOD_ID, "backpack_purple");
    public static final Identifier BACKPACK_WHITE_IDENTIFIER = new Identifier(MOD_ID, "backpack_white");
    public static final Identifier BACKPACK_YELLOW_IDENTIFIER = new Identifier(MOD_ID, "backpack_yellow");
    public static final Identifier VOID_PACK_IDENTIFIER = new Identifier(MOD_ID, "void_pack");
    public static final Identifier ENDER_PACK_IDENTIFIER = new Identifier(MOD_ID, "ender_pack");
    public static final String BACKPACK_TRANSLATION_KEY = Util.createTranslationKey("container", BACKPACK_IDENTIFIER);
    public static final String VOID_PACK_TRANSLATION_KEY = Util.createTranslationKey("container", VOID_PACK_IDENTIFIER);
    public static final String ENDER_PACK_TRANSLATION_KEY = Util.createTranslationKey("container", ENDER_PACK_IDENTIFIER);
    public static final Item BACKPACK = new BackpackItem(new Item.Settings().group(Backpack.SIMPLE_BACKPACK_GROUP).maxCount(1));
    public static final ItemGroup SIMPLE_BACKPACK_GROUP = FabricItemGroupBuilder.build(new Identifier(MOD_ID, "simple_backpack_group"), () -> new ItemStack(Backpack.BACKPACK));
    public static final Item BACKPACK_RED = new BackpackItem(new Item.Settings().group(Backpack.SIMPLE_BACKPACK_GROUP).maxCount(1));
    public static final Item BACKPACK_BLACK = new BackpackItem(new Item.Settings().group(Backpack.SIMPLE_BACKPACK_GROUP).maxCount(1));
    public static final Item BACKPACK_BLUE = new BackpackItem(new Item.Settings().group(Backpack.SIMPLE_BACKPACK_GROUP).maxCount(1));
    public static final Item BACKPACK_CYAN = new BackpackItem(new Item.Settings().group(Backpack.SIMPLE_BACKPACK_GROUP).maxCount(1));
    public static final Item BACKPACK_GRAY = new BackpackItem(new Item.Settings().group(Backpack.SIMPLE_BACKPACK_GROUP).maxCount(1));
    public static final Item BACKPACK_GREEN = new BackpackItem(new Item.Settings().group(Backpack.SIMPLE_BACKPACK_GROUP).maxCount(1));
    public static final Item BACKPACK_LIGHT_BLUE = new BackpackItem(new Item.Settings().group(Backpack.SIMPLE_BACKPACK_GROUP).maxCount(1));
    public static final Item BACKPACK_LIME = new BackpackItem(new Item.Settings().group(Backpack.SIMPLE_BACKPACK_GROUP).maxCount(1));
    public static final Item BACKPACK_MAGENTA = new BackpackItem(new Item.Settings().group(Backpack.SIMPLE_BACKPACK_GROUP).maxCount(1));
    public static final Item BACKPACK_ORANGE = new BackpackItem(new Item.Settings().group(Backpack.SIMPLE_BACKPACK_GROUP).maxCount(1));
    public static final Item BACKPACK_PINK = new BackpackItem(new Item.Settings().group(Backpack.SIMPLE_BACKPACK_GROUP).maxCount(1));
    public static final Item BACKPACK_PURPLE = new BackpackItem(new Item.Settings().group(Backpack.SIMPLE_BACKPACK_GROUP).maxCount(1));
    public static final Item BACKPACK_WHITE = new BackpackItem(new Item.Settings().group(Backpack.SIMPLE_BACKPACK_GROUP).maxCount(1));
    public static final Item BACKPACK_YELLOW = new BackpackItem(new Item.Settings().group(Backpack.SIMPLE_BACKPACK_GROUP).maxCount(1));
    public static final Item VOID_PACK = new VoidPackItem(new Item.Settings().group(Backpack.SIMPLE_BACKPACK_GROUP).maxCount(1));
    public static final Item ENDER_PACK = new EnderPackItem(new Item.Settings().group(Backpack.SIMPLE_BACKPACK_GROUP).maxCount(1));
    public static Logger LOGGER = LogManager.getLogger();

    @Override
    public void onInitialize() {
        ContainerProviderRegistry.INSTANCE.registerFactory(BACKPACK_IDENTIFIER, ((syncId, identifier, player, buf) -> {
            final ItemStack stack = buf.readItemStack();
            final Hand hand = buf.readInt() == 0 ? Hand.MAIN_HAND : Hand.OFF_HAND;
            final BackpackInventoryInterface inventory = BackpackItem.getInventory(stack, hand, player);

            return new BackpackScreenHandler(syncId, player.inventory, inventory.getInventory(), inventory.getInventoryWidth(), inventory.getInventoryHeight(), hand);
        }));
        ContainerProviderRegistry.INSTANCE.registerFactory(BACKPACK_RED_IDENTIFIER, ((syncId, identifier, player, buf) -> {
            final ItemStack stack = buf.readItemStack();
            final Hand hand = buf.readInt() == 0 ? Hand.MAIN_HAND : Hand.OFF_HAND;
            final BackpackInventoryInterface inventory = BackpackItem.getInventory(stack, hand, player);

            return new BackpackScreenHandler(syncId, player.inventory, inventory.getInventory(), inventory.getInventoryWidth(), inventory.getInventoryHeight(), hand);
        }));
        ContainerProviderRegistry.INSTANCE.registerFactory(BACKPACK_BLACK_IDENTIFIER, ((syncId, identifier, player, buf) -> {
            final ItemStack stack = buf.readItemStack();
            final Hand hand = buf.readInt() == 0 ? Hand.MAIN_HAND : Hand.OFF_HAND;
            final BackpackInventoryInterface inventory = BackpackItem.getInventory(stack, hand, player);
            return new BackpackScreenHandler(syncId, player.inventory, inventory.getInventory(), inventory.getInventoryWidth(), inventory.getInventoryHeight(), hand);
        }));
        ContainerProviderRegistry.INSTANCE.registerFactory(BACKPACK_BLUE_IDENTIFIER, ((syncId, identifier, player, buf) -> {
            final ItemStack stack = buf.readItemStack();
            final Hand hand = buf.readInt() == 0 ? Hand.MAIN_HAND : Hand.OFF_HAND;
            final BackpackInventoryInterface inventory = BackpackItem.getInventory(stack, hand, player);
            return new BackpackScreenHandler(syncId, player.inventory, inventory.getInventory(), inventory.getInventoryWidth(), inventory.getInventoryHeight(), hand);
        }));
        ContainerProviderRegistry.INSTANCE.registerFactory(BACKPACK_CYAN_IDENTIFIER, ((syncId, identifier, player, buf) -> {
            final ItemStack stack = buf.readItemStack();
            final Hand hand = buf.readInt() == 0 ? Hand.MAIN_HAND : Hand.OFF_HAND;
            final BackpackInventoryInterface inventory = BackpackItem.getInventory(stack, hand, player);
            return new BackpackScreenHandler(syncId, player.inventory, inventory.getInventory(), inventory.getInventoryWidth(), inventory.getInventoryHeight(), hand);
        }));
        ContainerProviderRegistry.INSTANCE.registerFactory(BACKPACK_GRAY_IDENTIFIER, ((syncId, identifier, player, buf) -> {
            final ItemStack stack = buf.readItemStack();
            final Hand hand = buf.readInt() == 0 ? Hand.MAIN_HAND : Hand.OFF_HAND;
            final BackpackInventoryInterface inventory = BackpackItem.getInventory(stack, hand, player);
            return new BackpackScreenHandler(syncId, player.inventory, inventory.getInventory(), inventory.getInventoryWidth(), inventory.getInventoryHeight(), hand);
        }));
        ContainerProviderRegistry.INSTANCE.registerFactory(BACKPACK_GREEN_IDENTIFIER, ((syncId, identifier, player, buf) -> {
            final ItemStack stack = buf.readItemStack();
            final Hand hand = buf.readInt() == 0 ? Hand.MAIN_HAND : Hand.OFF_HAND;
            final BackpackInventoryInterface inventory = BackpackItem.getInventory(stack, hand, player);
            return new BackpackScreenHandler(syncId, player.inventory, inventory.getInventory(), inventory.getInventoryWidth(), inventory.getInventoryHeight(), hand);
        }));
        ContainerProviderRegistry.INSTANCE.registerFactory(BACKPACK_LIGHT_BLUE_IDENTIFIER, ((syncId, identifier, player, buf) -> {
            final ItemStack stack = buf.readItemStack();
            final Hand hand = buf.readInt() == 0 ? Hand.MAIN_HAND : Hand.OFF_HAND;
            final BackpackInventoryInterface inventory = BackpackItem.getInventory(stack, hand, player);
            return new BackpackScreenHandler(syncId, player.inventory, inventory.getInventory(), inventory.getInventoryWidth(), inventory.getInventoryHeight(), hand);
        }));
        ContainerProviderRegistry.INSTANCE.registerFactory(BACKPACK_LIME_IDENTIFIER, ((syncId, identifier, player, buf) -> {
            final ItemStack stack = buf.readItemStack();
            final Hand hand = buf.readInt() == 0 ? Hand.MAIN_HAND : Hand.OFF_HAND;
            final BackpackInventoryInterface inventory = BackpackItem.getInventory(stack, hand, player);
            return new BackpackScreenHandler(syncId, player.inventory, inventory.getInventory(), inventory.getInventoryWidth(), inventory.getInventoryHeight(), hand);
        }));
        ContainerProviderRegistry.INSTANCE.registerFactory(BACKPACK_MAGENTA_IDENTIFIER, ((syncId, identifier, player, buf) -> {
            final ItemStack stack = buf.readItemStack();
            final Hand hand = buf.readInt() == 0 ? Hand.MAIN_HAND : Hand.OFF_HAND;
            final BackpackInventoryInterface inventory = BackpackItem.getInventory(stack, hand, player);
            return new BackpackScreenHandler(syncId, player.inventory, inventory.getInventory(), inventory.getInventoryWidth(), inventory.getInventoryHeight(), hand);
        }));
        ContainerProviderRegistry.INSTANCE.registerFactory(BACKPACK_ORANGE_IDENTIFIER, ((syncId, identifier, player, buf) -> {
            final ItemStack stack = buf.readItemStack();
            final Hand hand = buf.readInt() == 0 ? Hand.MAIN_HAND : Hand.OFF_HAND;
            final BackpackInventoryInterface inventory = BackpackItem.getInventory(stack, hand, player);
            return new BackpackScreenHandler(syncId, player.inventory, inventory.getInventory(), inventory.getInventoryWidth(), inventory.getInventoryHeight(), hand);
        }));
        ContainerProviderRegistry.INSTANCE.registerFactory(BACKPACK_PINK_IDENTIFIER, ((syncId, identifier, player, buf) -> {
            final ItemStack stack = buf.readItemStack();
            final Hand hand = buf.readInt() == 0 ? Hand.MAIN_HAND : Hand.OFF_HAND;
            final BackpackInventoryInterface inventory = BackpackItem.getInventory(stack, hand, player);
            return new BackpackScreenHandler(syncId, player.inventory, inventory.getInventory(), inventory.getInventoryWidth(), inventory.getInventoryHeight(), hand);
        }));
        ContainerProviderRegistry.INSTANCE.registerFactory(BACKPACK_PURPLE_IDENTIFIER, ((syncId, identifier, player, buf) -> {
            final ItemStack stack = buf.readItemStack();
            final Hand hand = buf.readInt() == 0 ? Hand.MAIN_HAND : Hand.OFF_HAND;
            final BackpackInventoryInterface inventory = BackpackItem.getInventory(stack, hand, player);
            return new BackpackScreenHandler(syncId, player.inventory, inventory.getInventory(), inventory.getInventoryWidth(), inventory.getInventoryHeight(), hand);
        }));
        ContainerProviderRegistry.INSTANCE.registerFactory(BACKPACK_WHITE_IDENTIFIER, ((syncId, identifier, player, buf) -> {
            final ItemStack stack = buf.readItemStack();
            final Hand hand = buf.readInt() == 0 ? Hand.MAIN_HAND : Hand.OFF_HAND;
            final BackpackInventoryInterface inventory = BackpackItem.getInventory(stack, hand, player);
            return new BackpackScreenHandler(syncId, player.inventory, inventory.getInventory(), inventory.getInventoryWidth(), inventory.getInventoryHeight(), hand);
        }));
        ContainerProviderRegistry.INSTANCE.registerFactory(BACKPACK_YELLOW_IDENTIFIER, ((syncId, identifier, player, buf) -> {
            final ItemStack stack = buf.readItemStack();
            final Hand hand = buf.readInt() == 0 ? Hand.MAIN_HAND : Hand.OFF_HAND;
            final BackpackInventoryInterface inventory = BackpackItem.getInventory(stack, hand, player);
            return new BackpackScreenHandler(syncId, player.inventory, inventory.getInventory(), inventory.getInventoryWidth(), inventory.getInventoryHeight(), hand);
        }));

        ContainerProviderRegistry.INSTANCE.registerFactory(VOID_PACK_IDENTIFIER, ((syncId, identifier, player, buf) -> {
            final ItemStack stack = buf.readItemStack();
            final Hand hand = buf.readInt() == 0 ? Hand.MAIN_HAND : Hand.OFF_HAND;
            final VoidPackInventoryInterface inventory = VoidPackItem.getInventory(stack, hand, player);

            return new VoidPackScreenHandler(syncId, player.inventory, inventory.getInventory(), inventory.getInventoryWidth(), inventory.getInventoryHeight(), hand);
        }));

        Registry.register(Registry.ITEM, BACKPACK_IDENTIFIER, BACKPACK);
        Registry.register(Registry.ITEM, BACKPACK_RED_IDENTIFIER, BACKPACK_RED);
        Registry.register(Registry.ITEM, BACKPACK_BLACK_IDENTIFIER, BACKPACK_BLACK);
        Registry.register(Registry.ITEM, BACKPACK_BLUE_IDENTIFIER, BACKPACK_BLUE);
        Registry.register(Registry.ITEM, BACKPACK_CYAN_IDENTIFIER, BACKPACK_CYAN);
        Registry.register(Registry.ITEM, BACKPACK_GRAY_IDENTIFIER, BACKPACK_GRAY);
        Registry.register(Registry.ITEM, BACKPACK_GREEN_IDENTIFIER, BACKPACK_GREEN);
        Registry.register(Registry.ITEM, BACKPACK_LIGHT_BLUE_IDENTIFIER, BACKPACK_LIGHT_BLUE);
        Registry.register(Registry.ITEM, BACKPACK_LIME_IDENTIFIER, BACKPACK_LIME);
        Registry.register(Registry.ITEM, BACKPACK_MAGENTA_IDENTIFIER, BACKPACK_MAGENTA);
        Registry.register(Registry.ITEM, BACKPACK_ORANGE_IDENTIFIER, BACKPACK_ORANGE);
        Registry.register(Registry.ITEM, BACKPACK_PINK_IDENTIFIER, BACKPACK_PINK);
        Registry.register(Registry.ITEM, BACKPACK_PURPLE_IDENTIFIER, BACKPACK_PURPLE);
        Registry.register(Registry.ITEM, BACKPACK_WHITE_IDENTIFIER, BACKPACK_WHITE);
        Registry.register(Registry.ITEM, BACKPACK_YELLOW_IDENTIFIER, BACKPACK_YELLOW);

        Registry.register(Registry.ITEM, VOID_PACK_IDENTIFIER, VOID_PACK);
        Registry.register(Registry.ITEM, ENDER_PACK_IDENTIFIER, ENDER_PACK);
    }
}