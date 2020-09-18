package com.kwpugh.simple_backpack.backpack;

import com.kwpugh.simple_backpack.Backpack;
import net.fabricmc.fabric.api.container.ContainerProviderRegistry;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.text.LiteralText;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;

import java.util.List;

public class BackpackItem extends Item {
    public BackpackItem(Settings settings) {
        super(settings);
    }

    public static BackpackInventory getInventory(ItemStack stack, Hand hand, PlayerEntity player) {
        if (!stack.hasTag()) {
            stack.setTag(new CompoundTag());
        }

        if (!stack.getTag().contains("backpack")) {
            stack.getTag().put("backpack", new CompoundTag());
        }

        return new BackpackInventory(stack.getTag().getCompound("backpack"), hand, player);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (!world.isClient) {
            ContainerProviderRegistry.INSTANCE.openContainer(Backpack.BACKPACK_IDENTIFIER, user, buf -> {
                buf.writeItemStack(user.getStackInHand(hand));
                buf.writeInt(hand == Hand.MAIN_HAND ? 0 : 1);
            });
        }

        return super.use(world, user, hand);
    }

    @Override
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
        CompoundTag tag = stack.getTag();
        CompoundTag compoundTag;
        if (tag != null && tag.contains("backpack", 10)) {
            compoundTag = tag.getCompound("backpack");
        } else if (tag != null) {
            tooltip.add(new LiteralText("Error: Type: " + tag.getType("backpack")));
            return;
        } else {
            tooltip.add(new LiteralText("Nuevo xD"));
            return;
        }

        if (compoundTag != null) {
            DefaultedList<ItemStack> defaultedList = DefaultedList.ofSize(54, ItemStack.EMPTY);

            readItemsFromTag(defaultedList, compoundTag);
            int i = 0;
            int j = 0;

            for (ItemStack itemStack : defaultedList) {
                if (!itemStack.isEmpty()) {
                    ++j;
                    if (i <= 4) {
                        ++i;
                        MutableText mutableText = itemStack.getName().shallowCopy();
                        mutableText.append(" x").append(String.valueOf(itemStack.getCount()));
                        tooltip.add(mutableText);
                    }
                }
            }

            if (j - i > 0) {
                tooltip.add((new TranslatableText("container.shulkerBox.more", new Object[]{j - i})).formatted(Formatting.ITALIC));
            }
        }
    }

    private void readItemsFromTag(DefaultedList<ItemStack> inventory, CompoundTag tag) {
        ListTag listTag = tag.getList("items", 10);

        for (int i = 0; i < listTag.size(); ++i) {
            CompoundTag compoundTag = listTag.getCompound(i);
            int j = compoundTag.getInt("slot");

            if (j >= 0 && j < inventory.size()) {
                inventory.set(j, ItemStack.fromTag(compoundTag));
            }
        }
    }
}